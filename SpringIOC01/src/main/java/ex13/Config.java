package ex13;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Config {
	
	@Bean(name="emp1")
	public Emp getBean() {
		return new Emp("������","�빫��",4500);
	}
	
	@Bean(name="emp2")
	public Emp getBean2() {
		return new Emp("ȫ�μ�","�λ��",5500);
	}
	
	@Bean
	public Member member() {
		return new Member();
	}
	
	@Bean
	public ServiceImpl service() {
		return new ServiceImpl();
	}
}
