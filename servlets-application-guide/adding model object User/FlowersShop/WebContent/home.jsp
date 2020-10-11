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