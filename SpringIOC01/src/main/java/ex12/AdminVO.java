package ex12;

public class AdminVO {
	private String adminId;
	private String adminPwd;
	

	public void info() {
		System.out.println("AdminVO [adminId=" + adminId + ", adminPwd=" + adminPwd + "]");
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminPwd() {
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	
	
}
