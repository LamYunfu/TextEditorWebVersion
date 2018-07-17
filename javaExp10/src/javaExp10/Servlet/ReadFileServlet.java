package javaExp10.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaExp10.Service.FileFolderOperation;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;


@WebServlet("/ReadFileServlet")
public class ReadFileServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getParameter("path");
		FileFolderOperation fp = new FileFolderOperation();
		byte[] content = fp.getFileContent(path);
	//	System.out.println(Arrays.toString(content));
		
	//	System.out.println(content.toString());
		
		resp.setContentType("application/text; charset=utf-8");  
        resp.setCharacterEncoding("UTF-8");
        OutputStream out = resp.getOutputStream();  
        out.write(content);  
        out.flush();  
	}

}
