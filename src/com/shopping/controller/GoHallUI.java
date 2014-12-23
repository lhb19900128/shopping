package com.shopping.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.domain.BookBean;
import com.shopping.domain.UserBean;
import com.shopping.service.BookService;
import com.shopping.service.UsersService;

@WebServlet(name="GoHallUI",urlPatterns="/gohallui")
public class GoHallUI extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String id = req.getParameter("id");
		String passwd = req.getParameter("passwd");
		UserBean ub = new UserBean(Integer.parseInt(id),passwd);
		if(new UsersService().checkUser(ub) != null){
			//合法用户，跳转到购物大厅
			//给下一个页面hall.jsp准备要显示的数据
			ArrayList<BookBean> list = new BookService().getAllBook();
			req.setAttribute("books", list);
			req.getRequestDispatcher("/WEB-INF/hall.jsp").forward(req, resp);
		}else {
			req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
		} 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
