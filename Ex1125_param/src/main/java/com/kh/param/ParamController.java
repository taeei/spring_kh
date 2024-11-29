package com.kh.param;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vo.PersonVO;

@Controller
public class ParamController {
	
	public static final String VIEW_PATH = "/WEB-INF/views/person/";
	
	@RequestMapping(value = {"/", "/insert_form.do"} )
	public String insertForm() {
		return VIEW_PATH + "insert_form.jsp";
	}

	//낱개로 받기
	@RequestMapping("insert_single.do")
	public String insert1( Model model,
						   String name, int age, String tel ) {
		//insert_single.do?name=홍길동&age=20&tel=010-111-1111
		PersonVO vo = new PersonVO();
		vo.setName(name);
		vo.setAge(age);
		vo.setTel(tel); 
		
		model.addAttribute("vo", vo);
		
		return VIEW_PATH + "insert_result.jsp";
	}
	
	//객체로 받기
	@RequestMapping("insert_vo.do")
	public String insert2( Model model, PersonVO vo ) {
		//insert_single.do?name=홍길동&age=20&tel=010-111-1111
		//파라미터로 넘어온 값을 vo에 속성이 일치하는 변수에 자동으로 추가
		
		model.addAttribute("vo", vo);
		
		//public String insert2( Model model, PersonVO vo, String name) {
		//위와 같이 vo에 이미 있는 변수와 같은 이름의 파라미터가 만들어져 있다면
		//실행시 오류가 발생하므로 주의
		//vo에 포함되어 있지 않는 이름이 다른 변수는 받아올 수 있다.
		return VIEW_PATH + "insert_result.jsp";
	}
}






