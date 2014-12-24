package com.shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.service.CartService;

@WebServlet(name="ForbiddenRefreshServlet",urlPatterns="/refreshservlet")
public class ForbiddenRefreshServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//把要显示的书籍放入req中
		CartService myCart = (CartService) req.getSession().getAttribute("mycart");
		req.setAttribute("buybooks", (Object)myCart.getAllBook());
		req.setAttribute("totalprice",(Object)myCart.calTotalPrice());
		req.getRequestDispatcher("/WEB-INF/shoppingcart.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
