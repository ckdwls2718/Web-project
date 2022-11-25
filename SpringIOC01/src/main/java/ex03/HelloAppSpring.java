package ex03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
//IOC
public class HelloAppSpring {

	public static void main(String[] args) {
		String config="src/main/java/ex03/applicationContext.xml";
		
		//������ �����̳�
		ApplicationContext ctx = new FileSystemXmlApplicationContext(config);
		
		//DL:(Dependency Lookup) �޸𸮿� �ö� �ִ� ��ü�� �̸����� ã�°�
		MessageBean mb = (MessageBean) ctx.getBean("mbEn");
		mb.sayHello("Spring");
	}

}
