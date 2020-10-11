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

import com.flowersshop.model.User;


@WebFilter("/profile")
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpServletResponse httpResp = (HttpServletResponse) resp;
		User user = null;
		HttpSession session = httpReq.getSession(false);  
        if (session != null) {  
        	user = (User) session.getAttribute("loggedUser");
        }

		// trying to pass to /profile url without authentication
		if (user == null) {
			httpResp.sendRedirect(httpReq.getContextPath() + "/");
		}
		// successful authorization and authentication
		else {
			RequestDispatcher rd = req.getRequestDispatcher("profile.jsp"); 
	    	rd.include(req, resp);  
	    	chain.doFilter(req, resp); 
	    }  
	}
}
