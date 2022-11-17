package user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import user.model.NotUserException;
import user.model.UserDAOMyBatis;
import user.model.UserVO;

public class LoginEndAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("utf-8");
		
		String userid = req.getParameter("userid");
		String pwd = req.getParameter("pwd");
		String saveId = req.getParameter("saveId");
		
		if(userid==null||pwd==null|userid.isBlank()||pwd.isBlank()) {
			setRedirect(true);
			setViewPage("login.do");
			return;
		}
		
		UserDAOMyBatis dao = new UserDAOMyBatis();
		try {
			UserVO loginUser = dao.loginCheck(userid,pwd);
			HttpSession session = req.getSession();
			
			if(loginUser !=null) {
				session.setAttribute("loginUser", loginUser);
				//아이디저장 체크박스에 체크했다면 => 쿠키에 해당 아이디를 저장하자
				Cookie ck = new Cookie("uid",loginUser.getUserid());
				if(saveId!=null) {
					ck.setMaxAge(7*24*60*60); //7일간 유효
				} else {
					ck.setMaxAge(0);
				}
				ck.setPath("/");
				
				res.addCookie(ck);	
			}
			setRedirect(true);
			setViewPage("index.do");
			
		} catch (NotUserException e) {
			req.setAttribute("msg", e.getMessage());
			req.setAttribute("loc", "javascript:history.back()");
			setViewPage("message.jsp");
			setRedirect(false);
		}
		
		

	}

}
