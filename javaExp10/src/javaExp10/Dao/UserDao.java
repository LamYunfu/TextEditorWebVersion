package javaExp10.Dao;

import java.sql.*;

import javaExp10.po.User;


public class UserDao {
	
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
	
	public String getCurrentLoginID(Connection c){
		PreparedStatement pst = null;
		String sql = "select * from userInfo";
		String currentID = null;
		try{
			pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			String currentLast = null;					//��ǰ���һ��ID����
			if(rs.next()){
				currentLast = rs.getString("loginID");
				currentID = currentLast;
				System.out.println(currentID);
			}
			int next = Integer.parseInt(currentLast);
			currentLast = (++next) + "";
	//		System.out.println(currentLast);
			sql = "update userInfo set loginID = ? where id = 1;";
			pst = c.prepareStatement(sql);
			pst.setString(1, currentLast);
			pst.executeUpdate();
		}catch(SQLException ex){
			System.out.println("�ó���ǰ���ʧ��");
			ex.printStackTrace();
		}finally{
			try{
				if(pst != null){
					pst.close();
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return currentID;
	}
	
	/*
	 * ��ѯuserInfo���ݱ�
	 * ����true�������û�����
	 * ����false: �����û�������
	 */
	public User userQuery(User user){
		Connection c = getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from userInfo where loginID = ? and password = ?;";
		try{
			pst = c.prepareStatement(sql);
			pst.setString(1, user.getAccount());
			pst.setString(2, user.getPassword());
			rs = pst.executeQuery();
			try {
				if(rs.next()){
					user.setId(rs.getInt("id"));
                    user.setName(rs.getString("uName"));
					return user;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}catch(SQLException ex){
			System.out.println("��ѯ����");
			ex.printStackTrace();
		}finally{
			try{
				if(pst != null){
					pst.close();
				}
				if(c != null){
					c.close();
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * ����userInfo���ݱ�
	 * ���һ���û�����Ϣ
	 * ��������û���������Ϣ
	 */
	public User insertUserInfo(User user){
		Connection c = getConnection();
		PreparedStatement pst = null;
		String currentID = getCurrentLoginID(c);
		user.setAccount(currentID);
		String sql = "insert into userInfo (uName, password, loginID) values(?, ?, ?);";
		try{
			pst = c.prepareStatement(sql);
			pst.setString(1, user.getName());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getAccount());
			pst.executeUpdate();
			
		}catch(SQLException ex){
			System.out.println("����û�ʧ��");
			ex.printStackTrace();
			return null;
		}finally{
			try{
				if(pst != null){
					pst.close();
				}
				if(c != null){
					c.close();
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return user;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDao ud = new UserDao();
		User u = new User();
		u.setName("���Ƹ�");
		u.setPassword("123");
	    ud.insertUserInfo(u); 
	    u.setAccount("100000");
		u = ud.userQuery(u);
		System.out.println(u == null);
		System.out.println(u.getId() + " " + u.getAccount());
	}

}
