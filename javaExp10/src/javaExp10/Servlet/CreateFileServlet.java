package javaExp10.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaExp10.Dao.LogDao;
import javaExp10.Service.FileFolderOperation;
import javaExp10.Service.Tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/CreateFileServlet")
public class CreateFileServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String filePath = req.getParameter("path");
		FileFolderOperation fp = new FileFolderOperation();
		//System.out.println(filePath);
		fp.createFile(filePath);
	}

}
