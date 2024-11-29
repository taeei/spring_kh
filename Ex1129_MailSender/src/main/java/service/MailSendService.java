package service;

import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailSendService {
	
	private JavaMailSender javaMailSender;
	private int authNumber = 0;
	
	public MailSendService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	//인증번호 생성 메서드
	public void makeRadomNumber() {
		authNumber = new Random().nextInt(999999 - 111111 + 1) + 111111;
		System.out.println("인증번호 : " + authNumber);
	}
	
	//이메일 양식
	public String joinEmail( String email ) {
		makeRadomNumber();
		
		String setFrom = "rlaxogml1014@naver.com"; //보내는 사람의 메일 주소
		String toMail = email; //받을 사람의 메일 주소
		
		String title = "회원 가입 인증 이메일 입니다.";
		
		String content = "인증번호 <b>" + authNumber + "</b>입니다.";
		
		try {
			MimeMessage mail = javaMailSender.createMimeMessage();
			MimeMessageHelper mailHelper = 
					new MimeMessageHelper(mail, true, "utf-8");
			mailHelper.setFrom(setFrom);
			mailHelper.setTo(toMail);
			mailHelper.setSubject(title);
			mailHelper.setText(content, true);
			
			javaMailSender.send(mail);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "" + authNumber;
	}

}
