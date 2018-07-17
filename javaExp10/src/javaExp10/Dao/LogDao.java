package javaExp10.Dao;

import java.sql.*;
import java.util.ArrayList;

import javaExp10.po.LogEntity;;
public class LogDao {
	public static Connection getConnection(){
		//�������ݿ�
		Connection c = null;
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");
	      c = DriverManager.getConnection("jdbc:mysql://localhost:3306/terminaldb", "root", "testpassword");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	  //  System.out.println("Opened database successfully");    
	    return c;
	}
	
	/**
	 * 插入一条用户操作的记录
	 * @param userID
	 * @param operation
	 */
	public static void insertLog(String userID, String operation) {
		Connection con = getConnection();
		
		java.util.Date date = new java.util.Date();
		Timestamp time = new Timestamp(date.getTime());
		
		String sql = "insert into log(userID, time, operation)values(?, ?, ?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, userID);
			pst.setTimestamp(2, time);
			pst.setString(3, operation);
			
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 通过用户的id来找到用户所有的操作记录
	 * @param userID
	 * @return
	 */
	public static ArrayList<LogEntity> findLogsByUserID(String userID){
		Connection con = getConnection();
		String sql = "select time, operation from log where userID = ?";
		ResultSet rs = null;
		ArrayList<LogEntity> list = new ArrayList<LogEntity>();
		
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				LogEntity log = new LogEntity(rs.getString("operation"), rs.getTimestamp("time"));
				list.add(log);
			}
			pst.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
