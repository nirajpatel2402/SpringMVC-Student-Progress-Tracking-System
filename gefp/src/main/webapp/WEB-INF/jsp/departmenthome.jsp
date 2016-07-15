<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FlightPlans</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

</head>
<body>
<h3 align="center" class="well text-center">Flight Plans of <%= session.getAttribute("departmentname")%></h3>  

	<table border="1" class="table table-striped" align="center">
		<tr>
			<th>id</th>
			<th>FlightPlan</th>
			<th>Operation</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${planByDepartment}" var="planByDepartment">
			<tr>
				<td>${planByDepartment.id}</td>
				<td>${planByDepartment.name}</td>
				<td><a href="adminview.html?id=${planByDepartment.id}">View</a>
				<td><a href="adminhome.html?action=changeplan&departmentid=${departmentid}&planid=${planByDepartment.id}">Set as current plan</a>
			</tr>
		</c:forEach>
	</table>
</body>
</html>