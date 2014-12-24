package com.shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.domain.UserBean;
import com.shopping.service.CartService;
import com.shopping.service.UsersService;

@WebServlet(name="LoginCheckServlet",urlPatterns="/logincheck")
public class LoginCheckServlet extends HttpServlet{
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
		System.out.println(id+":"+passwd);
		UserBean ub = new UserBean(Integer.parseInt(id),passwd);
		if(new UsersService().checkUser(ub)){
			System.out.println(true);
			//合法用户，跳转到购物大厅
			//将用户信息保存在session
			req.getSession().setAttribute("userinfo", ub);
			//为用户创建一个购物车
			CartService cs = new CartService();
			req.getSession().setAttribute("mycart", cs);
			resp.sendRedirect("gohallui");
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
