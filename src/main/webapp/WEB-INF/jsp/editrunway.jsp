<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Runway</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
</head>
<body>
	<h3 align="center" class="well text-center">Golden Eagle Flight
		Plan | Edit Runway</h3>
	<br />
	<div class="container" style="width:700px">
	<form:form modelAttribute="runway">
		<table border="1" class="table table-striped" align="center">
			
			
			<tr>
				<td>Enter Runway Name:</td>
				<td><input type="text" name="description"
					value="${runway.description}" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="add" value="Save" /></td>
			</tr>
		</table>
	</form:form>
	</div>
</body>
</html>