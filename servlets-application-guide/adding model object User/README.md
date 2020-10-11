# ServletsApplicationGuide
Creating by phases a simple web application based on java servlets<br>
Technologies: Eclipse, Java, Servlets, JSP

-------------------------------------------------------------------------------------------------------------------------------

Creating a login system

1) All 'login system using HttpSession object' guide

2) Create new package com.flowersshop.model and new class <a href="https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20model%20object%20User/FlowersShop/src/main/java/com/flowersshop/model/User.java">User</a> inside

3) Changing post method in sign up servlet (creating User object and putting it as attribute 'loggedUser' to session)

```java
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
```

4) Changing post method in login servlet (for a while creating new User object and putting it as attribute 'loggedUser' to session, in future it is needed to be get from database) 

```java
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		HttpSession session = req.getSession(); 
		
		if (username == null || password == null ||
				username.isEmpty() || password.isEmpty()) {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
		else if (username.equals("JohnSmith") && password.equals("12345678")) {
			User user = new User(username, password);
			session.setAttribute("loggedUser", user); 
			resp.sendRedirect(req.getContextPath() + "/profile");
		}
		else {
			RequestDispatcher reqDisp = req.getRequestDispatcher("login.jsp");
			req.setAttribute("error", "Wrong username or password!");
			reqDisp.include(req, resp);
		}
	}
```
5) Adding 'My profile' button in home page, which is can be only view if user is logged in (if loggedUser object is not null), condition is provided by jstl

```java
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flowers shop</title>
</head>
<body>
   <form action="signup" method="get">
      <input type="submit" value="Sign Up"/>
   </form>
   <form action="login" method="get">
      <input type="submit" value="Login"/>
   </form>
   <c:if test="${loggedUser != null}">
       <form action="profile" method="get">
          <input type="submit" value="My profile"/>
       </form>
   </c:if>
</body>
</html>
```
6) Adding home button in profile page, for redirecting to home page

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
     <h1>Hello ${loggedUser.getUsername()}</h1>
     <form action="logout" method="post">
      <input type="submit" value="Logout"/>
     </form>
	 <form action="home" method="get">
	  <input type="submit" value="Home"/>
	 </form>
</body>
</html>
```

7) For redirecting adding several paths to MainServlet (@WebServlet({"/",  "/home"}))

```java
@WebServlet({"/",  "/home"})
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {  
    	req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

    }

}
```

8) Result:

When user is not logged in

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20model%20object%20User/screenshots/home_not_logged_in.png)

Logging in

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20model%20object%20User/screenshots/logging_in.png)

Logged in

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20model%20object%20User/screenshots/logged_in.png)

If logged in and redirect to home page

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20model%20object%20User/screenshots/home_logged_in.png)

Signing up

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20model%20object%20User/screenshots/signing_up.png)

Signed up

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20model%20object%20User/screenshots/signed_up.png)

The same for signed up user as for logged in
