# ServletsApplicationGuide
Creating by phases a simple web application based on java servlets<br>
Technologies: Eclipse, Java, Servlets, JSP

-------------------------------------------------------------------------------------------------------------------------------

Creating a login system

1) All 'adding filter to login system' guide

2) Creating profile servlet <br>
*Right Click on package servlet -> New -> Servlet*

```java
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {  
    	req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }

}
```

3) Changing post method in login servlet to manage authentication, session saves username as attribute (to know that user is logged in) 

```java
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		HttpSession session = req.getSession(); 
		session.setAttribute("username", username);  
		
		if (username == null || password == null ||
				username.isEmpty() || password.isEmpty()) {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
		else if (username.equals("JohnSmith") && password.equals("12345678")) {
			resp.sendRedirect(req.getContextPath() + "/profile");
		}
		else {
			RequestDispatcher reqDisp = req.getRequestDispatcher("login.jsp");
			req.setAttribute("error", "Wrong username or password!");
			reqDisp.include(req, resp);
		}
	}
```

4) Changing <a href="https://github.com/Daply/ServletsApplicationGuide/blob/master/login%20system%20using%20HttpSession%20object/FlowersShop/WebContent/login.jsp">login.jsp</a> in form action="login" (in 'adding filter to login system' guide it was 'profile')

```java
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   <title>Flowers shop</title>
</head>
<body>
   <p>${error}</p>
   <form action="login" method="post">
      <table style="with: 50%">
         <tr>
    		<td>Username</td>
    		<td><input type="text" name="username"/></td>
    	 </tr>
    	 <tr>
    		<td>Password</td>
    		<td><input type="password" name="password"/></td>
    	 </tr>
      </table>
      <input type="submit" value="Login"/>
   </form>
</body>
</html>
```

5) Changing login filter (by checking if session has username attribute, it can check if user is logged in and filter to what pages it is allowed to redirect)

```java
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
```

6) Adding logout servlet (session.invalidate() removes the session)

```java
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 HttpSession session = req.getSession();  
         session.invalidate();  
         resp.sendRedirect(req.getContextPath() + "/");
	}
}
```

7) Adding logout button in <a href="https://github.com/Daply/ServletsApplicationGuide/blob/master/login%20system%20using%20HttpSession%20object/FlowersShop/WebContent/profile.jsp">profile.jsp</a>

```java
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flowers shop</title>
</head>
<body>
     <h1>Welcome to flowers shop</h1>
     <h1>Hello ${username}</h1>
     <form action="logout" method="post">
      <input type="submit" value="Logout"/>
   </form>
</body>
</html>
```

8) Changing sign up servlet

```java
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
		
		HttpSession session = req.getSession(); 
		session.setAttribute("username", username);  
				
		if(firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || 
		   password.isEmpty() || address.isEmpty() || contact.isEmpty()) {
			RequestDispatcher reqDisp = req.getRequestDispatcher("signup.jsp");
			req.setAttribute("error", "Wrong username or password!");
			reqDisp.include(req, resp);
		}
		else {
			resp.sendRedirect(req.getContextPath() + "/profile");
		}
	}

}
```

9) Adding error to <a href="https://github.com/Daply/ServletsApplicationGuide/blob/master/login%20system%20using%20HttpSession%20object/FlowersShop/WebContent/signup.jsp">signup.jsp</a>

10) Result, if user will follow to profile without login, it will redirect to home page, but if user is logged in and tries to follow from home page to profile, it will redirect to profile page (the same with signup):

Home page

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/login%20system%20using%20HttpSession%20object/screenshots/home.png)

Wrong login

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/login%20system%20using%20HttpSession%20object/screenshots/wrong_login.png)

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/login%20system%20using%20HttpSession%20object/screenshots/wrong_login_result.png)

Right login

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/login%20system%20using%20HttpSession%20object/screenshots/right_login.png)

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/login%20system%20using%20HttpSession%20object/screenshots/right_login_result.png)

Try to go to profile page when logged in

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/login%20system%20using%20HttpSession%20object/screenshots/try_to_go_to_profile.png)

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/login%20system%20using%20HttpSession%20object/screenshots/right_login_result.png)

