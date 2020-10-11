package com.flowersshop.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {  
    	req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String username = req.getParameter("username");
//		String password = req.getParameter("password");
//		if(username.isEmpty() || password.isEmpty() ) {
//			RequestDispatcher reqDisp = req.getRequestDispatcher("login.jsp");
//			reqDisp.include(req, resp);
//		}
//		else {
//			RequestDispatcher reqDisp = req.getRequestDispatcher("/profile.jsp");
//			req.setAttribute("username", username);
//			reqDisp.forward(req, resp);
////			req.setAttribute("username", username);
////			resp.sendRedirect(req.getContextPath() + "/profile");
//		}
//	}

}
