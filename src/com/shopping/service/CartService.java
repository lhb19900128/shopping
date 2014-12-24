package com.shopping.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


import com.shopping.domain.BookBean;

public class CartService {
	
	private HashMap<String, BookBean>  hm = null;
	
	public CartService() {
		hm = new HashMap<String, BookBean>();
	}

	
	//�����
	public void addBook(String id){
		
		if (hm.containsKey(id)) {
			BookBean book = hm.get(id);
			book.setShoppingNum(book.getShoppingNum()+1);
		}else {
			hm.put(id, new BookService().getBookById(id));
		}
		
	}
	
	//ɾ����
	public void delBook(String id) {
		hm.remove(id);
	}
	
	//������
	public void updataBook(String[] ids, String[] nums) {
		for (int i = 0; i < ids.length; i++) {
			BookBean bean = hm.get(ids[i]);
			bean.setShoppingNum(Integer.parseInt(nums[i]));
		}
	}
	
	//�����
	public void cleaBook(){
		hm.clear();
	}
	
	public ArrayList<BookBean> getAllBook(){
		ArrayList<BookBean> list = new ArrayList<BookBean>();
		
		Iterator<String> iterator = hm.keySet().iterator();
		while (iterator.hasNext()) {
			BookBean bookBean  = hm.get(iterator.next());
			list.add(bookBean);
		}
		
		return list;
	}
	
	public float calTotalPrice(){
		float totalPrice = 0;
		for(Iterator<String> iterator = hm.keySet().iterator();iterator.hasNext();){
			BookBean bookBean = hm.get(iterator.next());
			totalPrice += bookBean.getPrice()*bookBean.getShoppingNum();
		}
		return totalPrice;
	}
	
}
