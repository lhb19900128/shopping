package com.shopping.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.domain.BookBean;
import com.shopping.service.BookService;

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
			
			//给下一个页面hall.jsp准备要显示的数据
			ArrayList<BookBean> list = new BookService().getAllBook();
			//返回数据库中的书籍给hall.jsp
			req.setAttribute("books", list);
			req.getRequestDispatcher("/WEB-INF/hall.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
