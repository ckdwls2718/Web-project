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

public class BoardEditAction extends AbstractAction {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("utf-8");
		
		HttpSession session = req.getSession();
		UserVO user = (UserVO) session.getAttribute("loginUser");
		if(user==null) {
			req.setAttribute("msg", "로그인 후 이용하세요");
			req.setAttribute("loc", "login.do");
			
			setViewPage("message.jsp");
			setRedirect(false);
			return;
		}
		
		ServletContext application = req.getServletContext();
		String updir = application.getRealPath("upload");
		
		System.out.println("updir: "+updir);
		
		MultipartRequest mr = null;
		
		try {
			mr = new MultipartRequest(req, updir, 100*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			System.out.println("파일 업로드 성공");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		String numStr = mr.getParameter("num");
		String subject = mr.getParameter("subject");
		String content = mr.getParameter("content");
		String filename = mr.getFilesystemName("filename");
		String userid = user.getUserid();
		File file = mr.getFile("filename");
		long filesize = 0;
		if(file != null) { // 새로 첨부한 파일이 있다면
			filesize = file.length();
			
			// 예전에 첨부한 파일명 얻기
			String old_file = mr.getParameter("old_file");
			if(old_file != null && !old_file.isBlank()) {
				//서버에서 예전 파일 지우기
				File delFile = new File(updir, old_file);
				if(delFile != null) {
					boolean b = delFile.delete();
					System.out.println("파일 삭제 여부 : "+b);
				}
			}
		}
		
		if(numStr == null || subject == null || userid==null ||numStr.isBlank() || subject.isBlank() || userid.isBlank()) {
			setRedirect(true);
			setViewPage("boardList.do");
			return;
		}
		
		BoardVO vo = new BoardVO(Integer.parseInt(numStr),userid ,subject,content,null,filename,filesize);
		
		BoardDAOMyBatis dao = new BoardDAOMyBatis();
		
		int n = dao.updateBoard(vo);
		
		String str = (n>0) ? "글 수정 성공" : "글 수정 실패";
		String loc = "boardList.do";
		
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);
		
		setViewPage("message.jsp");
		setRedirect(false);

	}

}
