package ex06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAppTest {

	public static void main(String[] args) {
		//s1�� ����� �� test()1 ȣ���ϱ�
		String resource = "ex06/collection.xml";
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext(resource);
		Service s1 = ctx.getBean("s1",Service.class);
		
		s1.test1();
		
		ctx.getBean("s2", Service.class).test2();
		ctx.getBean("s3", Service.class).test3();
		ctx.getBean("s4", Service.class).test4();
	}

}
