package ex07;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAppTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		
		Emp e1 = ctx.getBean("e1", Emp.class);
		System.out.println(e1);
		
		Emp e2 = ctx.getBean("empInfo2",Emp.class);
		System.out.println(e2);
		
		Emp e3 = ctx.getBean("empInfo3",Emp.class);
		System.out.println(e3);
		
		Service s1 = ctx.getBean("serviceBean",Service.class);
		s1.test1();
	}

}
