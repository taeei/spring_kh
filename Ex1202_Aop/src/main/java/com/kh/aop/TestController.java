package com.kh.aop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.aop.service.TestService;

@Controller
public class TestController {

	TestService testService;
	public void setTestService(TestService testService) {
		this.testService = testService;
	}
	
	@RequestMapping(value={"/", "/list.do"})
	public String selectList() {
		//db작업
		
		testService.test();
		
		return "/WEB-INF/views/aopTest.jsp";
	}
}
