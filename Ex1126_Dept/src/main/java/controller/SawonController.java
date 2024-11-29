package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Common;
import dao.SawonDAO;
import vo.SawonVO;

@Controller
public class SawonController {

	SawonDAO sawon_dao;
	public SawonController(SawonDAO sawon_dao) {
		this.sawon_dao = sawon_dao;
	}
	
	//사원 목록 조회
	@RequestMapping("sawon_list.do")
	public String sawonList(Model model, int deptno) {
		
		List<SawonVO> list = sawon_dao.selectList(deptno);
		model.addAttribute("list", list);
		
		return Common.Sawon.VIEW_PATH + "sawon_list.jsp";
	}
	
	//사원 삭제
	@RequestMapping("sawon_del.do")
	public String sawonDel(SawonVO vo) {
		int res = sawon_dao.delete(vo.getSabun());
		
		//response.sendRedirect("sawon_list.do?deptno=10");
		return "redirect:sawon_list.do?deptno="+vo.getDeptno();
	}
	
	//사원추가 화면으로 전환
	@RequestMapping("insert_sawon_form.do")
	public String insert_form() {
		return Common.Sawon.VIEW_PATH + "insert_form.jsp";
	}
	
	//사원추가
	@RequestMapping("insert.do")
	public String insert(SawonVO vo) {
		int res = sawon_dao.insert(vo);
		return "redirect:sawon_list.do?deptno="+vo.getDeptno();
	}
	
	//사원 수정 화면으로 전환
	@RequestMapping("sawon_upd_form.do")
	public String upd_form(Model model, int sabun) {
		
		SawonVO vo = sawon_dao.selectOne(sabun);
		model.addAttribute("vo", vo);
		
		return Common.Sawon.VIEW_PATH + "update_form.jsp";
	}
	
	//사원 수정
	@RequestMapping("update.do")
	public String update(Model model, SawonVO vo) {
		
		int res = sawon_dao.update(vo);
		
		return "redirect:sawon_list.do?deptno=" + vo.getDeptno();
	}
	
}




