package com.kh.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	
	//jsp가 실행될 경로
	public static final String VIEW_PATH = "/WEB-INF/views/";	//기본 경로

	public TestController() {
		System.out.println("--TestController의 생성자");
	}
	
	@RequestMapping("/test.do")
	public String test( Model model, HttpServletRequest request ) {	// 클래스 이름 상관x, 반환형 99% String임
	
		String[] msg = {"사과", "오렌지", "배"};
		
		//msg 배열을 model객체에 바인딩
		model.addAttribute("msg", msg);
		
		//접속자 ip 가져오기
		String ip = request.getRemoteAddr();
		model.addAttribute("ip", ip);
		
		//WEB-INF/views/test.jsp로 포워딩
		return VIEW_PATH + "dept/test.jsp";
	}
	
	@RequestMapping("test2.do")
	public ModelAndView test2( HttpServletRequest request ) {
		
		//데이터와 뷰 정보를 하나로  묶어서 전달
		ModelAndView mv = new ModelAndView();
		
		String ip = request.getRemoteAddr();
		String[] fruit = {"바나나", "귤", "딸기"};
		
		mv.addObject("fruit", fruit);
		mv.addObject("ip", ip);
		mv.setViewName(VIEW_PATH + "test2.jsp");
		
		return mv;
	}  
	
	
}






