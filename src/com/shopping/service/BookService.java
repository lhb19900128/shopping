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
}
