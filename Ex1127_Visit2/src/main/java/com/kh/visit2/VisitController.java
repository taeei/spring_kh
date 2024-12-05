package com.kh.visit2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.BCryptPwd;
import common.Common;
import common.Paging;
import dao.VisitDAO;
import vo.VisitVO;

@Controller
public class VisitController {
	
	BCryptPwd bcp;
	public void setBcp(BCryptPwd bcp) {
		this.bcp = bcp;
	}
	
	VisitDAO visit_dao;
	public void setVisit_dao(VisitDAO visit_dao) {
		this.visit_dao = visit_dao;
	}
	
	//방명록 전체글 보기
	@RequestMapping(value={"/","/list.do"})
	public String list( Model model, Integer page ) {
		
		int nowPage = 1;
		if( page != null ) {
			nowPage = page;
		}
		
		int start = (nowPage - 1) * Common.Visit.BLOCKLIST + 1;
		int end = start + Common.Visit.BLOCKLIST - 1;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		
		List<VisitVO> list = visit_dao.selectList( map );
		
		int row_total = visit_dao.getRowTotal();
		
		//페이지 메뉴( ◀ 1 2 ▶ )생성하기
		String pageMenu = Paging.getPaging(
				"list.do", nowPage, row_total, 
				Common.Visit.BLOCKLIST, 
				Common.Visit.BLOCKPAGE);
		
		model.addAttribute("pageMenu", pageMenu);
		model.addAttribute("list",list);
		return Common.Visit.VIEW_PATH + "visit_list.jsp";
	}
	
	//글쓰기 폼 띄우기
	@RequestMapping("/insert_form.do")
	public String insert_form() {
		return Common.Visit.VIEW_PATH + "visit_insert_form.jsp";
	}
	
	//새 글 작성
	@RequestMapping("/insert.do")
	public String insert( VisitVO vo, HttpServletRequest request ) {
		
		//암호화
		String encodePwd = bcp.encryption( vo.getPwd() );
		vo.setPwd(encodePwd);		
		
		String ip = request.getRemoteAddr();
		vo.setIp(ip);
		
		int res = visit_dao.insert( vo );
		
		return "redirect:list.do";
	}
	
	//게시글 삭제
	//produces : json구조로 한글데이터가 전달될 때 깨지는 것을 방지
	@RequestMapping(value="/delete.do", 
				    produces="application/json;charset=UTF-8")
	@ResponseBody //return값을 포워딩이 아닌, 콜백으로 돌려보내는 어노테이션
	public String delete( VisitVO vo, Integer page, String c_pwd ) {
		
		int nowPage = 1;
		if( page != null ) {
			nowPage = page;
		}
		
		//비밀번호 비교
		boolean isValid = bcp.decryption(vo.getPwd(), c_pwd);
		
		int res = 0;
		
		if( isValid ) {
			res = visit_dao.delete(vo.getIdx());
		}else {
			String pwdCheck = "no_pwd";
			return String.format("[{'res':'%s'}]", pwdCheck);
		}
		
		String result = "아니오";
		if( res != 0 ) {
			result = "네";
		}
		
		String resultStr = String.format("[{'res':'%s'}]", result);
		return resultStr;//[{'res':'yes'}]
	}
	
	//수정을 위한 게시글 조회
	@RequestMapping("/modify_form.do")
	public String modify_form( Model model, int idx, Integer page ) {
		
		int nowPage = 1;
		if( page != null ) {
			nowPage = page;
		}
		
		VisitVO vo = visit_dao.selectOne( idx );
		model.addAttribute("vo", vo);
		
		return Common.Visit.VIEW_PATH + 
				"visit_modify_form.jsp?page="+nowPage;		
	}
	
	//수정
	@RequestMapping("/modify.do")
	public String modify( 
			VisitVO vo, Integer page, HttpServletRequest request) {
		
		int nowPage = 1;
		if( page != null ) {
			nowPage = page;
		}
		
		String encodePwd = bcp.encryption(vo.getPwd());
		vo.setPwd(encodePwd);
		
		String ip = request.getRemoteAddr();
		vo.setIp(ip);
		
		int res = visit_dao.update(vo);
		
		return "redirect:list.do?page="+nowPage;
		
	}
	
	//수정을 위한 비밀번호 체크
	@RequestMapping("/modify_password_chk.do")
	@ResponseBody
	public String chkPwd( String pwd, String c_pwd ) {
		
		BCryptPwd bcp = new BCryptPwd();
		boolean isValid = bcp.decryption(pwd, c_pwd);
		
		String result = "no_pwd";
		if( isValid ) {
			result = "yes_pwd";
		}
		
		return result;
		
	}
	
}


























