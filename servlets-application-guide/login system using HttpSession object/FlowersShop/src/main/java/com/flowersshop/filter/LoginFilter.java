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
import javax.servlet.http.HttpSession;


@WebFilter("/profile")
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpServletResponse httpResp = (HttpServletResponse) resp;
		String username = new String();
		HttpSession session = httpReq.getSession(false);  
        if (session != null) {  
        	username = (String)session.getAttribute("username");
        }

		// trying to pass to /profile url without authentication
		if (username == null || username.isBlank()) {
			httpResp.sendRedirect(httpReq.getContextPath() + "/");
		}
		// successful authorization and authentication
		else {
			RequestDispatcher rd = req.getRequestDispatcher("profile.jsp"); 
	    	req.setAttribute("username", username);
	    	rd.include(req, resp);  
	    	chain.doFilter(req, resp); 
	    }  
	}
}
