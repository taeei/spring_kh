package advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

public class Advice {
	//감시객체 Advice
	
	public void before( JoinPoint jp ) {
		Signature s = jp.getSignature();
		System.out.println("--- before : " + s);
	}
	
	public void after( JoinPoint jp ) {
		Signature s = jp.getSignature();
		System.out.println("--- after : " + s);
	}
	
}
