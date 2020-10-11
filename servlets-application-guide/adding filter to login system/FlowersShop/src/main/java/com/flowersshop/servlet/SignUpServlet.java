package com.flowersshop.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {  
    	req.getRequestDispatcher("signup.jsp").forward(req, resp);
    }
       
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String firstName = req.getParameter("first_name");
		String lastName = req.getParameter("last_name");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		String contact = req.getParameter("contact");
				
		if(firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || 
		   password.isEmpty() || address.isEmpty() || contact.isEmpty()) {
				RequestDispatcher disReq = req.getRequestDispatcher("signup.jsp");
				disReq.include(req, resp);
		}
		else {
		   RequestDispatcher disReq = req.getRequestDispatcher("profile.jsp");
		   req.setAttribute("username", username);
		   disReq.forward(req, resp);
		}
	}

}
