package com.shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.service.CartService;


//控制用户购买商品的请求
@WebServlet(name="CartServlet",urlPatterns="/mycart")
public class CartServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String type = req.getParameter("type");
		//把要显示的书籍放入req中
		CartService myCart = (CartService) req.getSession().getAttribute("mycart");

		if(type.equals("add")){
			//接收用户想购买的商品id
			String id = req.getParameter("id");
			myCart.addBook(id);
			
			req.setAttribute("buybooks", (Object)myCart.getAllBook());
			req.setAttribute("totalprice",(Object)myCart.calTotalPrice());
			//跳转到显示我的购物车
			req.getRequestDispatcher("gohallui").forward(req, resp);
		}else if(type.equals("del")){
			//接收用户想删除的商品id
			String id = req.getParameter("id");
			myCart.delBook(id);
			
			req.setAttribute("buybooks", (Object)myCart.getAllBook());
			req.setAttribute("totalprice",(Object)myCart.calTotalPrice());
			//跳转到显示我的购物车
			req.getRequestDispatcher("/WEB-INF/shoppingcart.jsp").forward(req, resp);
		}else if(type.equals("updata")){
			//用户更新的书号和数量
			String[] ids = req.getParameterValues("id");
			String[] nums = req.getParameterValues("booknum"); 
			myCart.updataBook(ids, nums);
			
			req.setAttribute("buybooks", (Object)myCart.getAllBook());
			req.setAttribute("totalprice",(Object)myCart.calTotalPrice());
			//跳转到显示我的购物车
			req.getRequestDispatcher("/WEB-INF/shoppingcart.jsp").forward(req, resp);
		}else if(type.equals("pay")){
			//接收用户想购买的商品id
			String id = req.getParameter("id");
			myCart.addBook(id);
			
//			req.setAttribute("buybooks", (Object)myCart.getAllBook());
//			req.setAttribute("totalprice",(Object)myCart.calTotalPrice());
//			//跳转到显示我的购物车
//			req.getRequestDispatcher("/WEB-INF/shoppingcart.jsp").forward(req, resp);
			resp.sendRedirect("refreshservlet");
		}else if(type.equals("gocart")){
			req.setAttribute("buybooks", (Object)myCart.getAllBook());
			req.setAttribute("totalprice",(Object)myCart.calTotalPrice());
			//跳转到显示我的购物车
			req.getRequestDispatcher("/WEB-INF/shoppingcart.jsp").forward(req, resp);
		}else if(type.equals("order")){
			
//			//用户更新的书号和数量
//			String[] ids = req.getParameterValues("id");
//			String[] nums = req.getParameterValues("booknum"); 
//			myCart.updataBook(ids, nums);
			
			float totalPrice = myCart.calTotalPrice();
			req.setAttribute("orderinfo",myCart.getAllBook());
			req.setAttribute("totalprice", totalPrice);
			req.getRequestDispatcher("/WEB-INF/myorder.jsp").forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

	
}
