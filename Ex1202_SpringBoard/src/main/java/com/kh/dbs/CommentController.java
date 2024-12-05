package com.kh.dbs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.Common;
import dao.CommentDAO;
import util.PagingComment;
import vo.CommentVO;

@Controller
public class CommentController {
	
	@Autowired
	HttpServletRequest request;
	
	CommentDAO comment_dao;
	public void setComment_dao(CommentDAO comment_dao) {
		this.comment_dao = comment_dao;
	}
	
	//댓글 작성
	@RequestMapping("/comment_insert.do")
	@ResponseBody
	public String comment_insert(CommentVO vo) {
		
		String ip = request.getRemoteAddr();
		vo.setIp(ip);
		
		int res = comment_dao.insert(vo);
		
		String str = "no";
		if( res == 1 ) {
			str = "yes";
		}
		
		String resultStr = String.format("[{'result':'%s'}]", str);
		return resultStr;
	}//comment_insert()
	
	
	//댓글 목록
	@RequestMapping("/comment_list.do")
	public String commentList(Model model, Integer page, int idx) {
		//댓글 페이징 처리를 위한 변수
		int nowPage = 1;
		if( page != null ) {
			nowPage = page;
		}
		
		int start = (nowPage - 1) * Common.Comment.BLOCKLIST + 1;
		int end = start + Common.Comment.BLOCKLIST - 1;
		
		//댓글 페이지를 위한 변수를 map에 저장
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("idx", idx);
		map.put("start", start);
		map.put("end", end);
				
		List<CommentVO> list = comment_dao.selectList(map);
		
		int row_total = comment_dao.getRowTotal(idx);
		//System.out.println( "댓글 수 : " + row_total);
		
		String pageMenu = 
				PagingComment.getPaging(
							"comment_list.do", 
							nowPage, 
							row_total, 
							Common.Comment.BLOCKLIST, 
							Common.Comment.BLOCKPAGE);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMenu", pageMenu);
		
		return Common.Comment.VIEW_PATH + "comment_list.jsp";
	}//commentList()
	
	@RequestMapping("comment_del.do")
	@ResponseBody
	public String comment_del(int c_idx) {

		int res = comment_dao.delete(c_idx);

		String str = "no";
		if (res == 1) {
			str = "yes";
		}

		String resultStr = String.format("[{'result':'%s'}]", str);
		return resultStr;
	}
}
