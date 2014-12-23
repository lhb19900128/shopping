package com.shopping.service;

import java.util.ArrayList;

import com.shopping.domain.UserBean;
import com.shopping.utils.SqlHelper;

//处理和users表相关的业务逻辑
public class UsersService {
	
	//验证用户是否合法
	public UserBean checkUser(UserBean ub){
		UserBean result = null;
		String sql = "select * from users where id=? and passwd=?";
		String paras[] = {new Integer(ub.getId()).toString(),ub.getPasswd()};
		ArrayList<UserBean> list = new SqlHelper().executeQuery(sql, paras);
		if(!list.isEmpty()) {
			result = new UserBean();
			result = list.get(0);
		}
		return result;
	}
}
