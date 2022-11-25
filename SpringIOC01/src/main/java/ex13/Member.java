package ex13;

import org.springframework.beans.factory.annotation.Value;

public class Member {
	
	@Value("±èÃ¶¼ö")
	private String name;
	@Value("kim")
	private String userid;
	@Value("010-1234-5678")
	private String tel;
	
	public void showInfo() {
		System.out.println("name = "+name);
		System.out.println("userid = "+userid);
		System.out.println("tel = "+tel);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
}
