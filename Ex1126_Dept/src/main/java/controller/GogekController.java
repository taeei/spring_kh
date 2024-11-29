package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Common;
import dao.GogekDAO;
import vo.GogekVO;

@Controller
public class GogekController {
	
	GogekDAO gogek_dao;
	public GogekController(GogekDAO gogek_dao) {
		this.gogek_dao = gogek_dao;
	}
	
	@RequestMapping("gogek_list.do")
	public String gogekList(Model model, int sabun) {
		List<GogekVO> list = gogek_dao.selectList(sabun);
		
		model.addAttribute("list", list);
		
		return Common.Gogek.VIEW_PATH + "gogek_list.jsp";
	}
	
}
