package board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
import user.model.UserVO;

public class BoardWriteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		
		//1. 파일 업로드 처리 ==> 업로드 디렉터리의 절대경로 얻어오자
		ServletContext application = req.getServletContext();
		String updir = application.getRealPath("upload");
		
		System.out.println("updir: "+updir);
		
		HttpSession session = req.getSession();
		UserVO user = (UserVO) session.getAttribute("loginUser");
		if(user==null) {
			req.setAttribute("msg", "로그인 후 이용하세요");
			req.setAttribute("loc", "login.do");
			
			setViewPage("message.jsp");
			setRedirect(false);
			return;
		}
		
		MultipartRequest mr = null;
		
		try {
			mr = new MultipartRequest(req, updir, 100*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			System.out.println("파일 업로드 성공");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		req.setCharacterEncoding("utf-8");
		//subject, content, userid=hong
		String subject = mr.getParameter("subject");
		String content = mr.getParameter("content");
		String userid = user.getUserid();
		String filename = mr.getFilesystemName("filename");
		File file = mr.getFile("filename");
		long filesize = 0;
		if(file != null) {
			filesize = file.length();
		}
		
		if(subject == null || content == null || userid == null || subject.isBlank()) {
			this.setViewPage("boardWrite.do");
			this.setRedirect(true);
			return;
		}
		
		BoardVO vo = new BoardVO(0,userid,subject,content,null,filename,filesize);
		
		BoardDAOMyBatis dao = new BoardDAOMyBatis();
		
		int n = dao.insertBoard(vo);
		
		String str = (n>0) ? "글 쓰기 성공" : "글쓰기 실패";
		String loc = (n>0) ? "boardList.do" : "javascript:history.back()";
		
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);
		setViewPage("/message.jsp");
		setRedirect(false);

	}

}
