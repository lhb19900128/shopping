package com.shopping.utils;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBUtil {
	private Connection con = null;

	public Connection getConnection(){
		try {
			String url = "jdbc:mysql://localhost:3306/shopping";
			String username = "root";
			String password = "root";
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(url, username,
					password);
		} catch (ClassNotFoundException e) {
			System.out.println("没有找到driver");
			e.printStackTrace();
		} catch (SQLException se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		} 
		return con;
	}
}
