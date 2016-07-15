<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create plan</title>
</head>
<body>
<h3 align="center" class="well text-center">Golden Eagle Flight
		Plan | Create new FlightPlan</h3>
		<div class="col-md-offset-5 col-md-2">
		<form:form modelAttribute="flightplan">
FlightPlan: <form:input path="name" class="form-control input-sm chat-input"
				placeholder="Enter plan name" />
			<br />
			<input type="submit" name="submit" value="Create" />
			<br />
		</form:form>
	</div>
</body>
</html>