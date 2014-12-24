package com.shopping.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.shopping.domain.BookBean;
import com.shopping.domain.UserBean;
import com.shopping.utils.DBUtil;

public class OrderService {
	
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	//下订单
	public void submitOrder(CartService myCart,UserBean ub){
		//因为添加订单复杂，不使用SqlHelper
		
		con = new DBUtil().getConnection();
		
		try {
			//为了保证保证订单号是稳定的，所以将其事务隔离级别升级（可串行）
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			String sql="insert into orderform(userid,totalprice) values(?,?)";
			ps = (PreparedStatement)con.prepareStatement(sql);
			ps.setInt(1, ub.getId());
			ps.setFloat(2, myCart.calTotalPrice());
			ps.executeUpdate();
			
			sql = "select last_insert_id()";
			ps = (PreparedStatement)con.prepareStatement(sql);
			rs = ps.executeQuery();
			int orderId = 0;
			while(rs.next()){
				orderId = rs.getInt(1);
			}
			//把订单细节表生成（批量提交）
			ArrayList<BookBean> list = myCart.getAllBook();
			for (int i = 0; i < list.size(); i++) {
				BookBean bb = list.get(i);
				sql="insert into orderdetail(ord_id,s_id,num,price) values(?,?,?,?)";
				ps = (PreparedStatement)con.prepareStatement(sql);
				ps.setInt(1, orderId);
				ps.setInt(2, bb.getId());
				ps.setInt(3, bb.getShoppingNum());
				ps.setFloat(4, bb.getPrice()*bb.getShoppingNum());
			}
			ps.executeUpdate();
			// 手动提交
			con.commit();
			// 设置回自动提交
			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (con != null) {
					//如果出现异常必须回滚，并且要设置成手动提交，Transaction要求必须双发都要完成处理，或者都不完成
					con.rollback();
					con.setAutoCommit(true);
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			throw new RuntimeException("订单失败！");
		} finally{
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (ps != null) {
					ps.close();
					ps = null;
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
	}
}
