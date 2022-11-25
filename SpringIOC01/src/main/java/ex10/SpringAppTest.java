package ex10;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringAppTest {

	public static void main(String[] args) {
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("ex10/application.xml");
		ApplicationContext ctx = new GenericXmlApplicationContext("classpath:ex10/application.xml");
		
		MessageBean mb = ctx.getBean("m1", MessageBean.class);
		mb.sayHello();
	}

}
