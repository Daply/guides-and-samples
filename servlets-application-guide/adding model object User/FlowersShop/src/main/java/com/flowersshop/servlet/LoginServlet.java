package com.flowersshop.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flowersshop.model.User;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {  
    	req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		HttpSession session = req.getSession(); 
		
		if (username == null || password == null ||
				username.isEmpty() || password.isEmpty()) {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
		else if (username.equals("JohnSmith") && password.equals("12345678")) {
			User user = new User( username, password);
			session.setAttribute("loggedUser", user); 
			resp.sendRedirect(req.getContextPath() + "/profile");
		}
		else {
			RequestDispatcher reqDisp = req.getRequestDispatcher("login.jsp");
			req.setAttribute("error", "Wrong username or password!");
			reqDisp.include(req, resp);
		}
	}

}
