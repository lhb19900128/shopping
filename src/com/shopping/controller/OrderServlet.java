package com.shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.shopping.domain.UserBean;
import com.shopping.service.CartService;
import com.shopping.service.OrderService;

//处理查看订单
@WebServlet(name="OrderServlet",urlPatterns="/order")
public class OrderServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		try {
			OrderService orderService = new OrderService();
			CartService myCart = (CartService) req.getSession().getAttribute("mycart");
			UserBean ub = (UserBean) req.getSession().getAttribute("userinfo");
			orderService.submitOrder(myCart, ub);
		} catch (RuntimeException e) {
			req.getRequestDispatcher("WEB-INF/errorinfo.jsp");
		}
		req.getRequestDispatcher("WEB-INF/orderfinish.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
