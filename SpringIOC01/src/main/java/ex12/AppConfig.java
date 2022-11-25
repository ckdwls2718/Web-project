package ex12;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class AppConfig {
	
	//admin.properties파일에 기술딘 adminId와 adminPwd값을 주입
	@Value("${adminId}")
	private String id;
	
	@Value("${adminPwd}")
	private String pwd;
	
	
	//외부에 있는 properties데이터 얻어오기
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer conf = new PropertySourcesPlaceholderConfigurer();
		
		Resource[] resources = new Resource[1];
		resources[0] = new ClassPathResource("admin.properties");
		
		conf.setLocations(resources);
		return conf;
	}
	
	@Bean
	public AdminVO adminInfo() {
		
		AdminVO vo = new AdminVO();
		vo.setAdminId(id);
		vo.setAdminPwd(pwd);
		return vo; 
	}

}
