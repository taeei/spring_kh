package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.SawonDAO;
import vo.SawonVO;

@Controller
public class SawonController {
	
	SawonDAO sawon_dao;
	public SawonController(SawonDAO sawon_dao) {
		this.sawon_dao = sawon_dao;
	}
	
	public static final String VIEW_PATH = "/WEB-INF/views/sawon/";
	
	//부서목록 조회
	@RequestMapping(value= {"/", "/list_sawon.do"})
	public String selectList(Model model) {
		
		List<SawonVO> list = sawon_dao.selectList();
		
		model.addAttribute("list", list);
		
		return VIEW_PATH + "sawon_list.jsp";
	}
	
	//사원 추가 폼
	@RequestMapping("insert_form.do")
	public String insert_form() {
		return VIEW_PATH + "insert_form.jsp";
	}
	
	//사원 추가
	@RequestMapping("insert.do")
	public String insert(SawonVO vo) {	
		
		int res = sawon_dao.insert(vo);
		
		return "redirect:list_sawon.do";
	}

}
