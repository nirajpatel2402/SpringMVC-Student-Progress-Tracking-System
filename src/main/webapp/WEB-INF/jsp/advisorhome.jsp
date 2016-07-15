<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Advisor Home</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"/>
<link  rel="stylesheet" type="text/css" href="http://csns.calstatela.edu/static/jqueryui-1.11.0/jquery-ui.min.css" />
<style type="text/css">
a.right {
	float: right;
}
</style>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js"></script>

<script type="text/javascript">
$(function(){
    $("#search").autocomplete({
        source: "<c:url value='/autocomplete.html' />",
        select: function(event, ui) {
            if( ui.item )
                window.location.href =  "advisorview.html?searchuser=" + ui.item.id+"&user=advisor";
        }
    })
});

</script>
</head>
<body>
	<h2 align="center" class="well text-center">Welcome
		<security:authentication property="principal.username"/></h2>
	<div align="right">
		<security:authorize access="hasRole('administrator')">
				<a href="adminhome.html"><h3>Admin Section</h3></a>
		</security:authorize>
		<h3>
			<a href="j_spring_security_logout" class="right">Logout&nbsp;&nbsp;</a>
		</h3>
	</div>
	<div>
		<form id="searchUser" action="advisorhome.html" method="post">
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="search" type="text" style="width: 20em;" name="userid"
					class="forminput" placeholder="Enter name/cin/email"/> <input type="submit" name="submit"
					class="subbutton" value="Search" />
			</p>
		</form>
	</div>



	<c:if test="${userSearched != null}">
		<h3>${userSearched.username}found!!</h3>

		<table border="1" class="table table-striped" align="center">
			<tr>
				<th>CIN</th>
				<td>${userSearched.cin}</td>
			</tr>
			<tr>
				<th>Name</th>
				<td>${userSearched.first_name} ${userSearched.last_name}</td>
			</tr>
			<tr>
				<th>Username</th>
				<td>${userSearched.username}</td>
			</tr>
			<tr>
				<th>Email</th>
				<td>${userSearched.email}</td>
			</tr>
			<tr>
				<th>FlightPlan</th>
				<td><a
					href="advisorview.html?searchuser=${userSearched.id }&user=advisor">View</a></td>
			</tr>
		</table>

	</c:if>
	<c:if test="${usersSearched != null}">
		<table border="1" class="table table-striped" align="center">
			<tr>
				<th>CIN</th>
				<th>Name</th>
				<th>Username</th>
				<th>Email</th>
				<th>FlightPlan</th>
			</tr>
			<c:forEach items="${usersSearched}" var="usersSearched">
				<tr>
					<td>${usersSearched.cin}</td>
					<td>${usersSearched.first_name } ${usersSearched.last_name}</td>
					<td>${usersSearched.username}</td>
					<td>${usersSearched.email}</td>
					<td><a
						href="advisorview.html?searchuser=${usersSearched.id }&user=advisor">View</a></td>
				</tr>
			</c:forEach>
		</table>

	</c:if>

</body>
</html>