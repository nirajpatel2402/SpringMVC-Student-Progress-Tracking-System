<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>


</head>
<body>
<h2 align="center" class="well text-center">Edit your settings</h2>
<form:form modelAttribute="user">
		<table border="1" class="table table-striped" align="center">
			<tr>
				<td>Change Major:</td>

				<td><select name="department">
						<c:forEach items="${department}" var="department">
							<option value="${department.id}" ${current_department.id == department.id ? 'selected="selected"' : ''}>${department.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Change Password:</td>
				<td><form:input path="password" type="password"/> <span style="color: red;"><form:errors path="password" /></span></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="submit" value="Submit"
					class="btn btn-primary btn-md" /></td>
			</tr>
		</table>
	</form:form>
	<div align="left"> 
        <a href="studenthome.html"><h4>Go Back &nbsp;&nbsp;&nbsp;</h4></a>
    </div>
</body>
</html>