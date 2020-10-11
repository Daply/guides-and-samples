package com.flowersshop.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/profile")
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpServletResponse httpResp = (HttpServletResponse) resp;

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		// trying to pass to /profile url without authentication
		if (username == null || password == null) {
			httpResp.sendRedirect(httpReq.getContextPath() + "/");
		}
		// successful authorization and authentication
		else if (username.equals("JohnSmith") && password.equals("12345678")){
			RequestDispatcher rd = req.getRequestDispatcher("profile.jsp"); 
	    	req.setAttribute("username", username);
	    	rd.include(req, resp);  
	    	chain.doFilter(req, resp); 
	    }  
		// NOT successful authorization and authentication
	    else{  
	    	RequestDispatcher rd = req.getRequestDispatcher("login.jsp"); 
	    	req.setAttribute("error", "Wrong username or password!");
	    	rd.include(req, resp); 
	    }  
	}
}
