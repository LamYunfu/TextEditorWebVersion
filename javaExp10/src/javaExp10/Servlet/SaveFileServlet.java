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


@WebServlet("/SaveFileServlet")
public class SaveFileServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getParameter("path");
		String content = req.getParameter("content");
		FileFolderOperation fp = new FileFolderOperation();
		fp.saveFile(path, content);
	}

}
