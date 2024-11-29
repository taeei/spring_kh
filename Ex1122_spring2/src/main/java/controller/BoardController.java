package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import service.BoardService;

//↓↓↓ 어노테이션(프로그래밍 주석) : 무조건 작성
@Controller
public class BoardController {
	BoardService service;
	
	public BoardController() {
		System.out.println("보드 컨트롤러 생성자");
	}
	
	//세터인젝션으로 받기 위해 생성
	public void setService(BoardService service) {
		this.service = service;
		System.out.println("--BoardController의 set메서드");
	}
	
	//url매핑
	@RequestMapping("list.do")
	public String list( Model model ) {
		//model은 서블릿과 컨트롤러의 중간 매개체 역할을 한다
		//바인딩을 목적으로 사용하는 객체
		
		List<String> list = service.selectList();
		
		//list객체 바인딩
		model.addAttribute("list", list);
		
		//disp.forward(request, response);
		return "board_list";
	}
	
}
