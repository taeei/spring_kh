package com.kh.dbs;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.Common;
import dao.BoardDAO;
import util.Paging;
import vo.BoardVO;

@Controller
public class BoardController {

	@Autowired
	HttpSession session;
	
	@Autowired
	HttpServletRequest request;
	
	BoardDAO board_dao;
	public void setBoard_dao(BoardDAO board_dao) {
		this.board_dao = board_dao;
	}
	
	//전체 게시글 조회
	@RequestMapping(value= {"/", "list.do"})
	public String list( Model model, String search, String search_text, Integer page ) {
		
		//list.do?page=1
		//list.do? <--- null
		//list.do?page= <--- empty 
		//정상적으로 파라미터가 넘어오면 페이지 지정
		int nowPage = 1;
		if( page != null ){
			nowPage = page;
		}
		
		//한 페이지에 표시할 게시글의 시작과 끝번호 계산
		//1page = 1부터 시작 ~ 10
		//2page = 11부터 시작 ~ 20
		int start = (nowPage - 1) * Common.Board.BLOCKLIST + 1;
		int end = start + Common.Board.BLOCKLIST - 1;
		
		//start와 end를 map에 저장
		//vo에 없고 묶어서 보내야 할 경우 map사용
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		
		//검색어 관련 
		if( search != null && !search.equals("all") ) {
			switch( search ) {
			case "name_subject_content":
				map.put("name", search_text);
				map.put("subject", search_text);
				map.put("content", search_text);
				break;
				
			case "name":
				map.put("name", search_text);
				break;
				
			case "subject": 
				map.put("subject", search_text);
				break;
				
			case "content": 
				map.put("content", search_text);
				break;
			}//switch
		}
	
		
		//전체 게시글 조회
		List<BoardVO> list = board_dao.selectList( map );
	
		//전체 게시글 수. 검색된 리스트의 paging처리를 다시 해야되기 때문에 map을 보냄
		int row_total = board_dao.getRowTotal( map );
		
		String search_param = String.format(
				"search=%s&search_text=%s",	search, search_text);
		
		//페이지 메뉴 생성
		String pageMenu = Paging.getPaging(
				"list.do", nowPage, row_total,
				search_param,
				Common.Board.BLOCKLIST,
				Common.Board.BLOCKPAGE );
		
		
		
		//list를 바인딩
		model.addAttribute("list", list);
		//페이지 메뉴 바인딩
		model.addAttribute("pageMenu", pageMenu);
		
		//조회수 기록을 위해 세션에 저장해뒀던 show정보를 없앤다
		session.removeAttribute("show");
		
		return Common.Board.VIEW_PATH + "board_list.jsp";
		
	}
	
	
	//게시글 상세보기
	@RequestMapping("/view.do")
	public String view(Model model, int idx) {
		
		//idx에 해당하는 게시글 한 건 가져오기
		BoardVO vo = board_dao.selectOne(idx);
		
		//조회수 증가
		String show = (String)session.getAttribute("show");
		
		if( show == null ) {
			int res = board_dao.update_readhit(idx);
			session.setAttribute("show", "y");
		}
		
		model.addAttribute("vo", vo);
		
		return Common.Board.VIEW_PATH + "board_view.jsp";
	}//view()
	
	
	//새 글 작성 페이지 전환
	@RequestMapping("/write_form.do")
	public String writeForm() {
		return Common.Board.VIEW_PATH + "board_write.jsp";
	}
	
	
	//게시글 등록
	@RequestMapping("insert.do")
	public String insert(BoardVO vo) {
		
		String ip = request.getRemoteAddr();
		vo.setIp(ip);
		
		int res = board_dao.insert(vo);
		
		return "redirect:list.do";
	}//insert()
	
	
	@RequestMapping("/del.do")
	@ResponseBody
	public String delete(int idx) {
		
		int res = board_dao.del_update(idx);
		
		String result = "fail";
		if( res == 1 ) {
			result = "succ";
		}
		
		String resStr = String.format("[{'result':'%s'}]", result);
		return resStr;
		
	}//delete()
	
	
	//댓글 작성 페이지로 전환
	@RequestMapping("/reply_form.do")
	public String replyForm(
			int idx, int page, 
			String search, String search_text) {
		
		return Common.Board.VIEW_PATH + 
				"board_reply.jsp?idx="+idx+
					    		 "&page="+page+
					    		 "&search="+search+
					    		 "&search_text="+search_text;
	}//replyForm()
	
	
	//댓글 작성
	@RequestMapping("/reply.do")
	public String reply( String page, String search, String search_text, BoardVO vo ) {

		String ip = request.getRemoteAddr();
		vo.setIp(ip);
		
		//기준글의 idx를 이용하여 게시글 정보 얻어오기
		BoardVO baseVO = board_dao.selectOne(vo.getIdx());
		
		//기준글의 step보다 큰 값을 +1처리
		int res = board_dao.update_step(baseVO);
		
		vo.setRef( baseVO.getRef() );
		vo.setStep( baseVO.getStep() + 1 );
		vo.setDepth( baseVO.getDepth() + 1 );
		
		//댓글 등록
		res = board_dao.reply(vo);
		
		//검색어가 인코딩 되지 않아서 잘못 넘어갈 수 있어서 작성
		//String encode = URLEncoder.encode(search_text, "UTF-8");
	
		return "redirect:list.do?page="+page+"&search="+search+"&search_text="+search_text;
	}
	
	
	//수정화면으로 전환
	@RequestMapping("upd_form.do")
	public String upd_form(Model model, int idx, String page) {
		
		BoardVO vo = board_dao.selectOne(idx);
		
		model.addAttribute("vo", vo);
		
		return Common.Board.VIEW_PATH + "board_upd_form.jsp?idx="+idx+"page="+page;
	}
	
	//게시글 수정
	@RequestMapping("modify.do")
	@ResponseBody
	public String modify(BoardVO vo) {
		
		
		int res = board_dao.modify(vo);
		
		String str = "fail";
		
		if( res == 1 ) {
			str = "succ";
		}
		
		String result = String.format("[{'param':'%s'}]", str);
		return result;
		
	}
	
	
	
}




