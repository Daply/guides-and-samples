# ServletsApplicationGuide
Creating by phases a simple web application based on java servlets<br>
Technologies: Eclipse, Java, Servlets, JSP

-------------------------------------------------------------------------------------------------------------------------------

Creating a login system

1) All 'login system using HttpSession object' guide

2) It is needed to download MySql Server and MySql Workbench

Through MySql Installer:

Here MySql Workbench is installed already<br>
![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/mysql_installer.png)

Click *Add* and choose *MySql Server*

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/mysql_installer_server.png)

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/mysql_installer_server_processing.png)

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/mysql_installer_server_processing1.png)

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/mysql_installer_server_processing2.png)

Turn on *Show Advanced and Logging Options* checkbox for setting password

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/mysql_installer_server_processing3.png)

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/mysql_installer_server_processing4.png)

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/mysql_installer_server_processing5_login_options.png)

Clicking *Next* till *Finish*

Open a MySql Workbench and click *Connect to Database...*

Main MySql Workbench page

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/mysql_new_model.png)

Connect to database

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/mysql_connect_to_database.png)

Password entering

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/mysql_connect_to_database2_ask_password.png)

3) Schema and table creation

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/mysql_new_schema_create.png)

Modifying the schema (renaming)

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/mysql_new_schema_create1.png)

Schema creation as code window will popup

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/mysql_new_schema_create2.png)

Adding the table

*Right Click on Tables -> Create Table...*

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/mysql_new_schema_create_new_table.png)

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/mysql_new_schema_create_new_table_user.png)

New table creation as code window will popup

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/mysql_new_schema_create_new_table_user_sql_code.png)

Result:

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/mysql_new_schema_create_new_table_user_result.png)

The schema and the table user are created

4) Add dependency in pom.xml

```java
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.16</version>
</dependency>
```

5) New package com.flowersshop.config and DatabaseUtils class inside for saving configurations for database

```java
public class DatabaseUtils {
	
	private static String hostName = "localhost";
	private static String dbName = "flowersshop";
	private static String userName = "root";
	private static String password = "root";
	
	public static Connection getMySQLConnection()
	         throws ClassNotFoundException, SQLException {
	    return getMySQLConnection(hostName, dbName, userName, password);
	}
	  
	private static Connection getMySQLConnection(String hostName, String dbName,
	        String userName, String password) throws SQLException,
	        ClassNotFoundException {
	    
	    Class.forName("com.mysql.jdbc.Driver");
	    String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
	  
	    Connection conn = DriverManager.getConnection(connectionURL, userName, password);
	    return conn;
	}
	
}
```

6) New package com.flowersshop.dao and UserDao class inside for managing user (inserting new one in sign up and selecting existing in login)

```java
public class UserDao {
	
	public UserDao() {

	}
	
	public void insertUser(User user) throws SQLException, ClassNotFoundException {
		insertUser(DatabaseUtils.getMySQLConnection(), user);
	}
	
	public User findUser(String username, String password) throws SQLException, ClassNotFoundException {
		return findUser(DatabaseUtils.getMySQLConnection(), username, password);
	}
	
	private boolean insertUser(Connection conn, User user) throws SQLException {
		 
        String sql = "insert into user (first_name, last_name, username, email, password, address, contact) "
        		+ "values (?, ?, ?, ?, ?, ?, ?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, user.getFirstName());
        pstm.setString(2, user.getLastName());
        pstm.setString(3, user.getUsername());
        pstm.setString(4, user.getEmail());
        pstm.setString(5, user.getPassword());
        pstm.setString(6, user.getAddress());
        pstm.setString(7, user.getContact());

        return pstm.execute();
    }
	
	public User findUser(Connection conn, String username, String password) throws SQLException {
 
        String sql = "select * from user where username = ? and password= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("first_name");
            String email = rs.getString("email");
            String address = rs.getString("address");
            String contact = rs.getString("contact");
            User user = new User(firstName, lastName, username, 
					email, password, address, contact);
            return user;
        }
        return null;
    }
	
}
```

7) Changing post method in login servlet for selecting user from database (if exists)

```java
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		HttpSession session = req.getSession(); 
		
		if (username == null || password == null ||
				username.isEmpty() || password.isEmpty()) {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
		else {
			UserDao userDao = new UserDao();
			User user = null;
			try {
				user = userDao.findUser(username, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (user != null) {
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
```

8) Changing post method in sign up servlet for inserting new user to database

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
		   email.isEmpty() || password.isEmpty()) {
			RequestDispatcher reqDisp = req.getRequestDispatcher("signup.jsp");
			req.setAttribute("error", "Wrong username or password!");
			reqDisp.include(req, resp);
		}
		else {
			User user = new User(firstName, lastName, username, 
					email, password, address, contact);
			session.setAttribute("loggedUser", user);  
			UserDao userDao = new UserDao();
			try {
				userDao.insertUser(user);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/profile");
		}
	}
```

9) Result:

Sign up

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/signup.png)

Sign up result

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/signup_result.png)

Sign up result in database (new user is inserted)

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/database_signup_result.png)

Login

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/login.png)

Login result 

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/adding%20database/screenshots/login_result.png)
