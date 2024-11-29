package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.DeptDAO;
import vo.DeptVO;

@Controller
public class DeptController {
		
	DeptDAO dept_dao;
	public DeptController( DeptDAO dept_dao ) {
		this.dept_dao = dept_dao;
	}
	
	public static final String VIEW_PATH = "/WEB-INF/views/dept/";
	
	@RequestMapping(value={"/", "/list_dept.do"})
	public String list( Model model ) {
		
		List<DeptVO> list = dept_dao.selectList();
		
		//바인딩
		model.addAttribute("list", list);
		
		//포워딩
		return VIEW_PATH + "dept_list.jsp";
	}

}
