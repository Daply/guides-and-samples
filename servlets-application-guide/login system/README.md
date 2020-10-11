# ServletsApplicationGuide
Creating by phases a simple web application based on java servlets<br>
Technologies: Eclipse, Java, Servlets, JSP

-------------------------------------------------------------------------------------------------------------------------------

Creating a login system

1) All 'base project' guide

2) Creating a <a href="https://github.com/Daply/ServletsApplicationGuide/blob/master/login%20system/FlowersShop/WebContent/signup.jsp">signup.jsp</a> file, for making a signup, user will enter First Name, Last Name, User Name, Password, Address and Contact number.
*Right Click on WebContent folder -> New -> JSP file*

3) Creating a sign up servlet
*Right Click on package servlet -> New -> Servlet*

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
				
		if(firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || 
		   password.isEmpty() || address.isEmpty() || contact.isEmpty()) {
				RequestDispatcher disReq = req.getRequestDispatcher("signup.jsp");
				disReq.include(req, resp);
		}
		else {
		   RequestDispatcher disReq = req.getRequestDispatcher("home.jsp");
		   req.setAttribute("name", firstName + " " + lastName);
		   disReq.forward(req, resp);
		}
	}

}
```
4) Add buttons for sign up and login to <a href="https://github.com/Daply/ServletsApplicationGuide/blob/master/login%20system/FlowersShop/WebContent/home.jsp">home.jsp</a>

5) Add <a href="https://github.com/Daply/ServletsApplicationGuide/blob/master/login%20system/FlowersShop/WebContent/profile.jsp">profile.jsp</a> for redirecting after successful signup

6) Result:

<b>Home page</b>

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/login%20system/screenshots/signup_login.png)

<b>Sign up form</b>

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/login%20system/screenshots/signup_form.png)

<b>Successful signup</b>

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/login%20system/screenshots/signup_login_success.png)

8) Creating a login servlet (user enters only username and password)
```java
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
		if(username.isEmpty() || password.isEmpty() )
		{
			RequestDispatcher reqDisp = req.getRequestDispatcher("login.jsp");
			reqDisp.include(req, resp);
		}
		else
		{
			RequestDispatcher reqDisp = req.getRequestDispatcher("profile.jsp");
			req.setAttribute("username", username);
			reqDisp.forward(req, resp);
		}
	}

}
```

7) Add <a href="https://github.com/Daply/ServletsApplicationGuide/blob/master/login%20system/FlowersShop/WebContent/login.jsp">login.jsp</a>

8) Result: 

<b>Home page</b>

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/login%20system/screenshots/signup_login.png)

<b>Login form</b>

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/login%20system/screenshots/login_form.png)

<b>Successful login</b>

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/login%20system/screenshots/signup_login_success.png)



