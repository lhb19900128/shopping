package com.shopping.utils;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.shopping.domain.BookBean;
import com.shopping.domain.UserBean;

public class SqlHelper {

	private Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	public ArrayList<BookBean> executeQuery(String sql){
		ArrayList<BookBean> list = new ArrayList<BookBean>();

		con = new DBUtil().getConnection();
		try {
			stmt = (PreparedStatement)con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				BookBean bb = new BookBean();
				bb.setId(rs.getInt(1));
				bb.setName(rs.getString(2));
				bb.setAuthor(rs.getString(3));
				bb.setPublishHouse(rs.getString(4));
				bb.setPrice(Float.parseFloat(rs.getString(5)));
				bb.setNums(rs.getInt(6));
				list.add(bb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (con != null) {
					con.close();
					con = null;
					System.out.println("database closed!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList<UserBean> executeQuery(String sql,String[] paras){
		ArrayList<UserBean> list = new ArrayList<UserBean>();

		con = new DBUtil().getConnection();
		try {
			stmt = (PreparedStatement)con.prepareStatement(sql);
			for(int i = 0;i<paras.length;++i){
				stmt.setString(i+1, paras[i]);
			}
			rs = stmt.executeQuery();
			
			while(rs.next()){
				UserBean ub = new UserBean();
				ub.setId(rs.getInt(1));
				ub.setName(rs.getString(2));
				ub.setPasswd(rs.getString(3));
				ub.setEmail(rs.getString(4));
				ub.setTel(rs.getString(5));
				ub.setGrade(rs.getInt(6));
				list.add(ub);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (con != null) {
					con.close();
					con = null;
					System.out.println("database closed!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList<BookBean> executeBookQuery(String sql,String[] paras){
		ArrayList<BookBean> list = new ArrayList<BookBean>();

		con = new DBUtil().getConnection();
		try {
			stmt = (PreparedStatement)con.prepareStatement(sql);
			for(int i = 0;i<paras.length;++i){
				stmt.setString(i+1, paras[i]);
			}
			rs = stmt.executeQuery();
			
			while(rs.next()){
				BookBean ub = new BookBean();
				ub.setId(rs.getInt(1));
				ub.setName(rs.getString(2));
				ub.setAuthor(rs.getString(3));
				ub.setPublishHouse(rs.getString(4));
				ub.setPrice(Float.parseFloat(rs.getString(5)));
				ub.setNums(rs.getInt(6));
				list.add(ub);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (con != null) {
					con.close();
					con = null;
					System.out.println("database closed!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
