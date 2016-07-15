<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="http://csns.calstatela.edu/static/ckeditor-4.2/ckeditor.js"></script>
<script>
$(function(){
   
    $("textarea").each(function(){
        CKEDITOR.replace( $(this).attr("id"), {
          toolbar : "Default",
          height: 200,
          width: 600
        });
    });
});

</script>
</head>
<body>
	<h3 align="center" class="well text-center">Golden Eagle Flight
		Plan | Add A Check Point</h3>
	<br />
	<div class="container" style="width:1000px">
	<form:form modelAttribute="checkpoints">
		<table border="1" class="table table-striped" align="center">
			<tr>
				<td>Runway:</td>

				<td><select name="runway">
						<c:forEach items="${runway}" var="runway">
							<option value="${runway.id}">${runway.description}</option>
						</c:forEach>
				</select></td>



			</tr>
			<tr>
				<td>Stage:</td>
				<td><select name="stage">
						<c:forEach items="${stage}" var="stage">
							<option value="${stage.id}" >${stage.sname}</option>
						</c:forEach>
				</select></td>


			</tr>
			<tr>
				<td>Check Point:</td>
				<td>
				<textarea id="textarea" name="description"></textarea> 
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="add" value="Add"
					class="btn btn-primary btn-md" /></td>
			</tr>
		</table>
	</form:form>
	</div>
</body>
</html>