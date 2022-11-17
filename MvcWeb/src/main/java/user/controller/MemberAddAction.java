package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import user.model.UserDAOMyBatis;
import user.model.UserVO;

public class MemberAddAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("utf-8");
		
		String userid = req.getParameter("userid");
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		String hp1 = req.getParameter("hp1");
		String hp2 = req.getParameter("hp2");
		String hp3 = req.getParameter("hp3");
		String post = req.getParameter("post");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		
		if(userid==null || name==null || pwd == null || userid.isBlank() || name.isBlank() || pwd.isBlank()) {
			setRedirect(true);
			setViewPage("joinForm.do");
			return;
		}
		
		UserVO vo = new UserVO(0,name,userid,pwd,hp1,hp2,hp3,post,addr1,addr2,null,1000,0,"");
		
		UserDAOMyBatis dao = new UserDAOMyBatis();
		
		int n = dao.insertUser(vo);
		
		String str = (n>0)?"회원가입완료" : "회원가입 실패";
		String loc =(n>0)?"login.do":"javascript:history.back()";
			
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);
		
		setRedirect(false);
		setViewPage("message.jsp");
	}

}
