<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student's Plan</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script type="text/javascript" src="javascript/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	jQuery(function() {
		jQuery('input[type = checkbox]').bind('click', function() {

			var checkpointId = "";
			var searchUser = "";
			if (jQuery(this).is(':checked')) {
				checkpointId = jQuery(this).val();
				var reqUrl = "checkpoint.html";
				searchUser = $("#userid").val();
			} else {
				checkpointId = jQuery(this).val();
				var reqUrl = "checkpointremove.html";
				searchUser = $("#userid").val();
			}

			sendAjaxRequest(reqUrl, checkpointId, searchUser);
		});
	});

	function sendAjaxRequest(reqUrl, checkpointId, searchUser) {
		jQuery.ajax({

			url : reqUrl,
			type : 'POST',
			data : {
				'checkpointId' : checkpointId,
				'searchuser'	: searchUser
			},
			success : function(data) {
			},
			error : function(errorData) {
			}
		});
	}
</script>
</head>
<body>

	<h3 align="center" class="well text-center">${searchUser.username}'s FlightPlan</h3>
	<input type="hidden" id="userid" name="userid" value="${searchUser.id}" />
	<table border="1" class="table table-striped" align="center">
		<tr>
			<th></th>
			<c:forEach items="${runway}" var="runway">
				<th>${runway.description}</th>
			</c:forEach>
		</tr>

		<tr>
			<c:forEach items="${stage}" var="stage">
				<tr>
					<td>${stage.sname}</td>
					<c:forEach items="${runway}" var="runway">
						<td><c:forEach items="${cell }" var="cell">
								<c:if
									test="${cell.stage.id == stage.id && cell.runway.id == runway.id}">
									<c:forEach items="${checkpoints }" var="checkpoints">
										<c:if test="${cell.id == checkpoints.cell.id}">
											<input class="mycb" type="checkbox" name="name" id="cpoint"
												<c:if test="${checkpoints.ischecked}">checked='checked'</c:if>
												value="${checkpoints.id}"> ${checkpoints.description}</input>
											<br />
										</c:if>
									</c:forEach>
								</c:if>
							</c:forEach></td>
					</c:forEach>
				</tr>
			</c:forEach>
		</tr>
	</table>
	<h3><a href="advisorhome.html">Go Back to Search</a></h3>
</body>
</html>