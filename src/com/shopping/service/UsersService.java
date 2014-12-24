package com.shopping.service;

import java.util.ArrayList;

import com.shopping.domain.UserBean;
import com.shopping.utils.SqlHelper;

//�����users����ص�ҵ���߼�
public class UsersService {
	
	//��֤�û��Ƿ�Ϸ�
	public boolean checkUser(UserBean ub){
		boolean result = false;
		String sql = "select * from users where id=? and passwd=?";
		String paras[] = {new Integer(ub.getId()).toString(),ub.getPasswd()};
		ArrayList<UserBean> list = new SqlHelper().executeQuery(sql, paras);
		if(!list.isEmpty()) {
			UserBean ubTemp = list.get(0);
			ub.setName(ubTemp.getName());
			ub.setPasswd(ubTemp.getPasswd());
			ub.setTel(ubTemp.getTel());
			ub.setEmail(ubTemp.getEmail());
			ub.setGrade(ubTemp.getGrade());
			result = true;
		}
		return result;
	}
}
