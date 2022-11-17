package common.controller;

public abstract class AbstractAction implements Action{
	
	//execute() 추상 메서드 가지고 있음
	private String viewPage; //보여줄 viewPage 이름
	private boolean isRedirect; // true : redirect 방식, false : forward 방식
	
	public String getViewPage() {
		return viewPage;
	}
	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	
}
