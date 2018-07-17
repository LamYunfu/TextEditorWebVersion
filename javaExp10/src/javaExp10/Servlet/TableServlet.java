package javaExp10.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaExp10.Dao.UserDao;
import javaExp10.Service.FileFolderOperation;
import javaExp10.po.FileFolderEntity;
import javaExp10.po.User;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;


@WebServlet("/TableServlet")
public class TableServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = (String)req.getParameter("userId");
//		System.out.println(userId);
		ArrayList<FileFolderEntity> list = FileFolderOperation.listInforMation(userId);
		/*
		for(FileFolderEntity f: list)
		{
			System.out.println(f.getName());
		}
		*/
		String json = JSON.toJSONString(list,true);
//		System.out.println(json);
		resp.setContentType("application/json; charset=utf-8");  
        resp.setCharacterEncoding("UTF-8"); 
        OutputStream out = resp.getOutputStream();  
        out.write(json.getBytes("UTF-8"));  
        out.flush();  
		
	}

}
