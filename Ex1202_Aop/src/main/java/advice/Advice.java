package advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

public class Advice {

	//감시객체 Advice
	
		long start;
	
		public void before( JoinPoint jp ) {
			Signature s = jp.getSignature();
			
			//before()가 호출됐을 떄의 시간
			start = System.currentTimeMillis();
			
			System.out.println("--- before : " + s);
		}
		
		public void after( JoinPoint jp ) {
			Signature s = jp.getSignature();
			
			long end = System.currentTimeMillis();
			
			System.out.println("--- after : " + s);
			
			System.out.printf("[수행시간] : %d\n", end - start);
		}
	
}
