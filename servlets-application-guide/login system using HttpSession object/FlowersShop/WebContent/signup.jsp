<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Flowers shop</title>
</head>
<body>
  <p>${error}</p>
  <h1>Sign up</h1>
  <form action="signup" method="post">
    <table style="with: 50%">
    	<tr>
    		<td>First name</td>
    		<td><input type="text" name="first_name"/></td>
    	</tr>
    	<tr>
    		<td>Last name</td>
    		<td><input type="text" name="last_name"/></td>
    	</tr>
    	<tr>
    		<td>Username</td>
    		<td><input type="text" name="username"/></td>
    	</tr>
    	<tr>
    		<td>Password</td>
    		<td><input type="password" name="password"/></td>
    	</tr>
    	<tr>
    		<td>Address</td>
    		<td><input type="text" name="address"/></td>
    	</tr>
    	<tr>
    		<td>Contact number</td>
    		<td><input type="text" name="contact"/></td>
    	</tr>
    </table>
    <input type="submit" value="Submit" />
  </form>
</body>
</html>