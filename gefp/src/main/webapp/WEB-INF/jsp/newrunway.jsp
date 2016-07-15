<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a runway</title>
</head>
<body>
	<h3 align="center" class="well text-center">Golden Eagle Flight
		Plan | Add a Runway</h3>
	<div class="container" style="width:700px">
		<form:form modelAttribute="runway">
		<table border="1" class="table table-striped" align="center">
		<tr>
			<td>Enter New Runway Name:</td>
				<td><form:input path="description"
				class="form-control input-sm chat-input"
				placeholder="Enter stage name" /></td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="submit" name="add" value="Add" />
			</td>
		</tr>
		</table>
		</form:form>
	</div>
</body>
</html>