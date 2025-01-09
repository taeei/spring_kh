package com.kh.bbs;

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
	@RequestMapping(value= {"/", "/list.do"})
	public String list(Model model, String search, String search_text, Integer page) {
		
		//list.do?page=1
		//list.do? 		<--null
		//list.do?page=	<-- empty 상태
		int nowPage = 1;
		
		if( page != null ) {
			nowPage = page;
		}
		
		//한 페이지에 표시할 게시글의 시작과 끝번호를 계산
		//1page = 시작값 1 ~ 끝번호 10
		//2page = 시작값 11 ~ 끝번호 20
		int start = (nowPage - 1) * Common.Board.BLOCKLIST + 1;
		int end = start + Common.Board.BLOCKLIST - 1;
		
		//start와 end를 map에 저장
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("start", start);
		map.put("end", end);
		
		//검색어 조건
		//search값이 "all"이 아닌 경우 = 검색을 해야하는 경우
		if(search != null && !search.equals("all")) {
			switch ( search ) {
			case "name_subject_content":
				map.put("name", search_text);
				map.put("subject", search_text);
				map.put("content", search_text);
				break;
				
			case "name":
				map.put("name", search_text);
				
			case "subject":
				map.put("subject", search_text);
				
			case "content":
				map.put("content", search_text);
			} //switch
			
		} //if
		
		//전체 게시글 조회
		List<BoardVO> list = null;
		list = board_dao.selectList( map );
		
		//전체 게시글 수 조회
		int row_total = board_dao.getRowTotal( map );
		
		String search_param = String.format("search=%s&search_text=%s", 
											 search, search_text);
		
		//페이지 메뉴를 생성
		String pageMenu = Paging.getPaging("list.do", nowPage, row_total, 
											search_param,
											Common.Board.BLOCKLIST,
											Common.Board.BLOCKPAGE);
		
		//list를 바인딩 (페이지메뉴도 함께)
		model.addAttribute("list", list);
		model.addAttribute("pageMenu", pageMenu);
		
		//조회수 기록을 위해 세션에 저장해뒀던 show정보를 없앤다
		session.removeAttribute("show");
		
		return Common.Board.VIEW_PATH + "board_list.jsp";
	} //list
	
	//게시글 상세정보 조회
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
	} //view
	
	//새 글 작성 페이지로 전환
	@RequestMapping("write_form.do")
	public String write_form() {
		return Common.Board.VIEW_PATH + "board_write.jsp";
	} //write_form
	
	//게시글 등록
	@RequestMapping("insert.do")
	public String insert(BoardVO vo) {
		
		String ip = request.getRemoteAddr();//접속자의 ip
		vo.setIp(ip);

		int res = board_dao.insert(vo);
		return "redirect:list.do";
	} //insert
	
	//게시글삭제
	@RequestMapping("del.do")
	@ResponseBody //콜백으로 결과값을 보내야 함
	public String delete(int idx) {
	
		int res = board_dao.del_update(idx);
		
		String result = "fail";
		if( res == 1 ) {
			result = "succ";
		}

		String resStr = String.format("[{'result':'%s'}]", result);
		return resStr;
	}
	
	//댓글 작성 페이지로 전환
	@RequestMapping("reply_form.do")
	public String reply_form(int idx, int page, String search, String search_text) {
		return Common.Board.VIEW_PATH + 
			   "board_reply.jsp?idx="+idx+"&page="+page
			   					+"&search="+search
			   					+"&search_text="+search_text;
	} 
	
	//댓글 작성
	@RequestMapping("reply.do")
	public String reply(String page, String search, String search_text, BoardVO vo) {
		String ip = request.getRemoteAddr();
		
		//기준글의 idx를 이용하여 게시글 정보 얻어오기
		BoardVO baseVO = board_dao.selectOne(vo.getIdx());
		
		//기준글의 step보다 큰 값을 +1처리
		int res = board_dao.update_step(baseVO);
		vo.setIp(ip);
		vo.setRef( baseVO.getRef() );
		vo.setStep( baseVO.getStep() + 1 );
		vo.setDepth( baseVO.getDepth() + 1 );
		
		//댓글 등록
		res = board_dao.reply(vo);
		
		//search_text utf-8로 인코딩
		//String encode = URLEncoder.encode(search_text, "UTF8");
		
		return "redirect:list.do?page="+page+"&search="+search+"&search_text="+search_text;
	}
	
	//게시글 수정 페이지로 전환
	@RequestMapping("modify_form.do")
	public String modify_form(Model model, int idx, String page) {
		//modify_form.do?idx=18
		BoardVO vo = board_dao.selectOne(idx);
		
		model.addAttribute("vo", vo);
		return Common.Board.VIEW_PATH + "board_modify.jsp?page="+page;
	}
	
	//게시글 수정
	@RequestMapping("modify.do")
	public String modify(BoardVO vo, String page) {
		
		int res = board_dao.update(vo);
		return "redirect:list.do?page="+page;
	}
}
