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
	
	//�¶���
	public void submitOrder(CartService myCart,UserBean ub){
		//��Ϊ��Ӷ������ӣ���ʹ��SqlHelper
		
		con = new DBUtil().getConnection();
		
		try {
			//Ϊ�˱�֤��֤���������ȶ��ģ����Խ���������뼶���������ɴ��У�
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
			//�Ѷ���ϸ�ڱ����ɣ������ύ��
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
			// �ֶ��ύ
			con.commit();
			// ���û��Զ��ύ
			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (con != null) {
					//��������쳣����ع�������Ҫ���ó��ֶ��ύ��TransactionҪ�����˫����Ҫ��ɴ������߶������
					con.rollback();
					con.setAutoCommit(true);
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			throw new RuntimeException("����ʧ�ܣ�");
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
