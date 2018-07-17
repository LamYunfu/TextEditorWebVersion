package javaExp10.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaExp10.Dao.UserDao;
import javaExp10.po.User;


@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = req.getParameter("userId");
		String pass = req.getParameter("pass");
		User u = new User(userId, pass);
		UserDao ud = new UserDao();
		
		resp.setContentType("text/html;charset=GB2312");
		PrintWriter out = resp.getWriter();
		req.setCharacterEncoding("gb2312");
		
		//如果该用户是存在的
		if(ud.userQuery(u) != null) {
			//跳转到主页面
			req.setAttribute("userId", u.getAccount());
			req.getRequestDispatcher("/main.jsp").forward(req, resp);
		}
		else {
			out.print("<html><body>");
			out.print("用户名和密码不正确！");
			out.print("<a href=\"/javaExp10/login.jsp\">返回登录界面</a>");
			out.print("<html></body>");
		}
	}

}
