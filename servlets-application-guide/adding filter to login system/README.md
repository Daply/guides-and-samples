# ServletsApplicationGuide
Creating by phases a simple web application based on java servlets<br>
Technologies: Eclipse, Java, Servlets, JSP

-------------------------------------------------------------------------------------------------------------------------------

Adding filter to login system

1) All 'login system project' guide

2) Create new package for filters and new filter LoginFilter<br>
*New -> Class* or *New -> Filter*

Here is added filter for profile page (to resctrict this resource for not authorized users), it checks if user tries to access profile resource without authorization (username == null or password == null, means that user even not submitted login form, redirects to home page), if user's username is 'JohnSmith' and his password is '12345678' (successful authorization, redirects to profile page) and if user submitted wrong credentials (it returns to login page with error message).

```java
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
```

3) Comment post method in login servlet (or delete it for a while)

4) Add error message to login.jsp, for displaying in case of wrong credentials

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
   <form action="profile" method="post">
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

5) Result:

Home page

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20filter%20to%20login%20system/screenshots/login.png)

If we will write in address string http://localhost:8080/FlowersShop/profile,

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20filter%20to%20login%20system/screenshots/trying_to_get_to_profile.png)

it will redirect back to home page.

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20filter%20to%20login%20system/screenshots/redirect_to_home.png)

Case of wrong credentials

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20filter%20to%20login%20system/screenshots/wrong_credentials.png)

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20filter%20to%20login%20system/screenshots/wrong_credentials_result.png)

Case of right credentials

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20filter%20to%20login%20system/screenshots/right_credentials.png)

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20filter%20to%20login%20system/screenshots/right_credentials_result.png)
