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
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		String contact = req.getParameter("contact");
		
		HttpSession session = req.getSession();  
				
		if(firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || 
		   email.isEmpty() || password.isEmpty() || address.isEmpty() || contact.isEmpty()) {
			RequestDispatcher reqDisp = req.getRequestDispatcher("signup.jsp");
			req.setAttribute("error", "Wrong username or password!");
			reqDisp.include(req, resp);
		}
		else {
			User user = new User(firstName, lastName, username, 
					email, password, address, contact);
			session.setAttribute("loggedUser", user);  
			resp.sendRedirect(req.getContextPath() + "/profile");
		}
	}

}
