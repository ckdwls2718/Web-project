package board.controller;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;

public class UploadEndAction extends AbstractAction {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//컨텐트 타입, 파일크기
		String ctype = req.getContentType();
		long len = req.getContentLengthLong();
		
		req.setCharacterEncoding("utf-8");
		
		ServletInputStream in = req.getInputStream();
		
		byte[] data = new byte[1024];
		int n =0,cnt=0;
		String content ="<xmp>";
		while((n=in.read(data)) != -1) {
			String str = new String(data, 0, n);
			content += str;
			cnt+= n;
		}
		content +="</xmp>";
		
		req.setAttribute("ctype", ctype);
		req.setAttribute("len", len);
		req.setAttribute("content", content);
		req.setAttribute("count", cnt);
		
		in.close();
		
		setViewPage("board/uploadResult.jsp");
		setRedirect(false);
	}

}
