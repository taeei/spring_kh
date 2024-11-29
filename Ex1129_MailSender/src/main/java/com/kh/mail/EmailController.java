package com.kh.mail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.MailSendService;

@Controller
public class EmailController {

	MailSendService mss;
	public EmailController(MailSendService mss) {
		this.mss = mss;
	}
	
	@RequestMapping(value={"/", "/start.do"})
	public String start() {
		return "/WEB-INF/views/email_sender.jsp";
	}
	
	@RequestMapping("mailCheck.do")
	@ResponseBody
	public String mailCheck( String email ) {
		String res = mss.joinEmail(email);
		return res;
	}
	
}
