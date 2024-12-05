package common;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPwd {
	
	//암호화 메서드
	public String encryption( String pwd ) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodePwd = encoder.encode(pwd);
		return encodePwd;
	}
	
	//복호화 메서드
	public boolean decryption( String encodePwd, String pwd ) {
		boolean isValid = false;
		
		//BCrypt.checkpw(입력한 비번, 암호화 된 비번);
		isValid = BCrypt.checkpw(pwd, encodePwd);
		
		return isValid;
	}
	
}












