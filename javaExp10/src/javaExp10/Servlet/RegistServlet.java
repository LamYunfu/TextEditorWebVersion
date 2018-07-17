package javaExp10.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaExp10.Dao.UserDao;
import javaExp10.Service.FileFolderOperation;
import javaExp10.po.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private String username;
	private String pass;
	private String tel;
	private String age;
	
	
	public void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException{
		username = request.getParameter("username");
		pass = request.getParameter("pass");
		tel = request.getParameter("tel");
		age = request.getParameter("age");
		
		UserDao ud = new UserDao();
		User user = new User();
		user.setName(username);
		user.setPassword(pass);
		user = ud.insertUserInfo(user);
		
		response.setContentType("text/html;charset=GB2312");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("gb2312");
		FileFolderOperation.createDirectory(user.getAccount());
		
		out.print("<html><body>");
		out.print("注册成功，你的账号是" + user.getAccount());
		out.print("<a href= \"/javaExp10/login.jsp\">返回登录界面</a>");
		out.print("<html></body>");
	}
	
	public void init() throws ServletException{
		super.init();
	}
}