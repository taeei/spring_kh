package com.kh.bbs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.Common;
import dao.CommentDAO;
import util.PagingComment;
import vo.CommentVO;

@Controller
public class CommentController {
	@Autowired
	HttpSession session;
	@Autowired
	HttpServletRequest request;
	
	CommentDAO comment_dao;
	public void setComment_dao(CommentDAO comment_dao) {
		this.comment_dao = comment_dao;
	}
	
	//댓글작성
	@RequestMapping("comment_insert.do")
	@ResponseBody
	public String comment_insert(CommentVO vo) {
		String ip = request.getRemoteAddr(); //ip가져오기
		vo.setIp(ip);

		int res = comment_dao.insert(vo);
		String str = "no";
		if(res == 1) {
			str = "yes";
		}
		
		String resultStr = String.format("[{'result':'%s'}]", str);
		return resultStr;
	}
	
	//댓글 조회
	@RequestMapping("comment_list.do")
	public String comment_list(Model model, Integer page, int idx) {
		//댓글 페이징 처리를 위한 변수
		int nowPage = 1;
		if( page != null ) {
			nowPage = page;
		}
		int start = (nowPage - 1) * Common.Comment.BLOCKLIST + 1;
		int end = start + Common.Comment.BLOCKLIST - 1;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("idx", idx);
		map.put("start", start);
		map.put("end", end);
		
		List<CommentVO> list = comment_dao.selectList(map);
		int row_total = comment_dao.getRowTotal(idx);
		
		//System.out.println("댓글갯수 : " + row_total);
		//페이지 메뉴 생성
		String pageMenu = PagingComment.getPaging("comment_list.do", nowPage, row_total, 
						  						  Common.Comment.BLOCKLIST, 
						  						  Common.Comment.BLOCKPAGE);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMenu", pageMenu);
		
		return Common.Comment.VIEW_PATH + "comment_list.jsp";
	}
	
	//댓글 삭제
	@RequestMapping("comment_del.do")
	@ResponseBody
	public String comment_delete(int c_idx) {
		int res = comment_dao.delete(c_idx);
		
		String str = "no";
		if(res == 1) {
			str = "yes";
		}
		
		String resultStr = String.format("[{'result':'%s'}]", str);
		return resultStr;
	}
}
