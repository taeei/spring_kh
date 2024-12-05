package com.kh.auto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.aop.service.TestService;

import dao.DeptDAO;
import vo.DeptVO;

@Controller
public class DeptController {
	
	DeptDAO dept_dao;
	TestService testService;
	
	@Autowired
	public DeptController( TestService testService, DeptDAO dept_dao ) {
		this.testService = testService;
		System.out.println("--DeptController의 생성자--");
	}
	
	@RequestMapping(value={"/", "/list.do"})
	public String dept_list( Model model ) {
		
		testService.test();
		
		List<DeptVO> list = dept_dao.selectList();
		model.addAttribute("list", list);
		return "/WEB-INF/views/dept_list.jsp";
	}
	
}
