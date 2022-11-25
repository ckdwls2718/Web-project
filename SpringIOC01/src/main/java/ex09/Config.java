package ex09;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;

/*
 * xml에 설정된 빈을 java Config로 가져오기
 * ==> @ImportResource 어노테이션을 붙여준다.
 * */

//Config 클래스를 환경설정으로 사용한다는 의미의 어노테이션
@Configuration
@ImportResource("classpath:ex09/applicationContext.xml")
public class Config {
	
	@Bean(name="e1")
	@Scope(value="prototype")
	//@Scope(value="singleton") ==> 디폴트	
	public Emp empInfo() {
		return new Emp("Kim","Research",5000);
	}
	
	//name 속성을 주지않으면 빈의 이름은 메서드 이름이 빈 name이 된다
	// <bean id="empInfo2" class="ex07.Emp" />
	@Bean
	public Emp empInfo2() {
		Emp e = this.empInfo();
		e.setName("Miller");
		e.setDept("Operation");
		e.setSalary(4000);
		return e;
	}
	
	@Bean
	public Emp empInfo3() {
		return new Emp("Scott","Analyst",3000);
	}
	
	@Bean
	public ServiceImpl serviceBean() {
		ServiceImpl s = new ServiceImpl();
		s.setEmp(this.empInfo3());
		return s;
	}
}
