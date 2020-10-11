# ServletsApplicationGuide
Creating by phases a simple web application based on java servlets<br>
Technologies: Eclipse, Java, Servlets, JSP

-------------------------------------------------------------------------------------------------------------------------------


Creating a basic application using java servlets

1) Creating project

Create a dynamic web project in eclipse <br>
*FIle -> New -> Dynamic Web Project*

Before creation project, Tomcat has to be downloaded, so it can be just added in Target Runtime

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/base/screenshots/creating_dynamic_web_project.png)

Converted to maven project:<br>
*RightClick on project -> Configure -> Convert to Maven project*

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/base/screenshots/convert_to_maven_project.png)

As a result it is created a web project with pom.xml.

2) Adding to pom.xml dependency for using servlets

```java
   <dependencies>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
        </dependency>
    </dependencies>
```
<a href="https://github.com/Daply/ServletsApplicationGuide/blob/master/base/base%20with%20web.xml/FlowersShop/pom.xml">pom.xml</a>

3) Creating servlet

Main source folders:<br>
src/main/java           Application/Library sources <br>
src/main/resources      Application/Library resources <br>
src/main/filters        Resource filter files <br>
src/main/webapp         Web application sources <br>

if there is no folders in src, so it is needed to add source folders:

- create main package in src <br>
- create main.java package in src <br>
- create main.resources package in src <br>

configure build path
*RightClick on project -> Build Path -> Configure Build Path...*

Remove existing folder src and click Add Folder...
![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/base/screenshots/configure_build_path_delete_folder.png)
![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/base/screenshots/configure_build_path_select.png)

Result:
![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/base/screenshots/configure_build_path_result.png)

Create servlet:
*src -> main -> java -> your_package.servlets -> class ServletName extends HttpServlet*

<a href="https://github.com/Daply/ServletsApplicationGuide/blob/master/base/base%20with%20web.xml/FlowersShop/src/main/java/com/flowersshop/servlet/MainServlet.java">pom.xml</a>

4) Add methods to Servlet 

```java
package com.flowersshop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        super.doGet(req, resp);
        PrintWriter out = resp.getWriter();
        out.print("<h1>Hello Servlet</h1>");
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        super.doPost(req, resp);
    }
    
}
```

5) Creating web.xml (if it wasn't added in project creation)

*Dynamic Web Project –> RightClick –> Java EE Tools –> Generate Deployment Descriptor Stub.*

or just create new xml file in WebContent -> WEB-INF

describing servlet in web.xml
```java
  <servlet>
      <servlet-name>mainServlet</servlet-name>
      <servlet-class>com.flowersshop.servlet.MainServlet</servlet-class>
  </servlet>

  <servlet-mapping>
      <servlet-name>mainServlet</servlet-name>
      <url-pattern>/</url-pattern>
  </servlet-mapping>
```

<a href="https://github.com/Daply/ServletsApplicationGuide/blob/master/base/base%20with%20web.xml/FlowersShop/WebContent/WEB-INF/web.xml">web.xml</a>

6) Building project using Maven

*RightClick on project -> Run As -> Maven clean*
*RightClick on project -> Run As -> Maven install*

7) Tomcat deploy

Add dependency to web.xml
```java
<dependency>
	<groupId>jstl</groupId>
	<artifactId>jstl</artifactId>
	<version>1.2</version>
</dependency>
```

*RightClick on project -> Run As -> Run on server*

Result:

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/base/screenshots/tomcat_run_result.png)

9) Adding JSP

*RightClick on WebContent -> New -> JSP File*

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
</body>
</html>
```

10) Adding JSP to servlet 

```java
@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {  
    	req.getRequestDispatcher("home.jsp").forward(req, resp);
}
```

11) Launch

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/base/screenshots/run_app_after_adding_jsp.png)

12) Passing data from Servlet to JSP

```java
@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("name", "John Smith");
        req.getRequestDispatcher("home.jsp").forward(req, resp);

    }
```

in JSP

```java
 <h1>Hello ${name}</h1>
```

13) Result

![alt text](https://github.com/Daply/ServletsApplicationGuide/blob/master/base/screenshots/jsp_pass_data_result.png)

All project: <a href="https://github.com/Daply/ServletsApplicationGuide/blob/master/base/base%20with%20web.xml/FlowersShop">FlowersShop base project</a>

-------------------------------------------------------------------------------------------------------------------------------

Deleting web.xml from base project

1) Add WebServlet annotation to servlet

```java
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {  
    	req.setAttribute("name", "John Smith");
    	req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

    }

}
```

2) Delete web.xml and launch, there will be the same result
