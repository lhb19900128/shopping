package com.shopping.service;

import java.util.ArrayList;

import com.shopping.domain.BookBean;
import com.shopping.utils.SqlHelper;

public class BookService {

	public ArrayList<BookBean> getAllBook(){
		String sql = "select * from book";
		ArrayList<BookBean> list = new SqlHelper().executeQuery(sql);
		return list;
	}
	
	public BookBean getBookById(String id){
		String sql="select * from book where id = ?";
		String[] paras = {id};
		ArrayList<BookBean> list = new SqlHelper().executeBookQuery(sql,paras);
		if(list.size() == 1){
			return list.get(0);
		}
		return null;
	}
}
