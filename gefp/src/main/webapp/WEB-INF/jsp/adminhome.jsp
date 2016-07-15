<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

</head>
<body>
<h2 align="center" class="well text-center">Welcome <security:authentication property="principal.username"/></h2>  

 <div align="right"> 
 		<security:authorize access="hasRole('advisor')">
				<a href="advisorhome.html"><h3>Advisor Section</h3></a>
		</security:authorize>
 		
        <a href="j_spring_security_logout"><h3>Logout &nbsp;&nbsp;&nbsp;</h3></a>
    </div>
    <div class="container" style="width:1100px">
<table border="1" class="table table-striped" align="center">
<tr><th>id</th><th>Department</th><th>Current FlightPlan</th><th>Operation</th><th>Create</th><th>Plan</th></tr>
<c:forEach items="${department}" var="department">
<tr>
  <td>${department.id}</td>
  <td>${department.name}</td>
  <td>${department.currentPlan.name}</td>
  <td><a href="adminview.html?id=${department.currentPlan.id}">View current plan</a>
  <td><a href="createplan.html?id=${department.id}">Create New Plan</a>
  <td><a href="departmenthome.html?id=${department.id}">View all plans</a>
</tr>
</c:forEach>
</table></div>
</body>
</html>