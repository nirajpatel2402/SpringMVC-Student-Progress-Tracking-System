<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<title>Login</title>
</head>
<body >
<form:form modelAttribute="user" align="center" action="j_spring_security_check" method="post">
<div style="margin-top:200px" class="well text-center">
    <div class="row">
        <div class="col-md-offset-5 col-md-3">
            <div class="form-login">
            <h2>Golden Eagle Flight Plan</h2>
            <c:if test="${param.error != null }">
            <font size="3" color="red">
            Failed to Login<br/>
            <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null }"> 
            Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}">
            
            </c:out>
            </c:if>
            </font>
            </c:if>
            <form:input path="username" class="form-control input-sm chat-input" placeholder="username"/>
            <br/>
            <form:input path="password" class="form-control input-sm chat-input" placeholder="password"/>
            <br/>
            <div class="wrapper">
            <span class="group-btn">    
            <input type="submit" name="save" value="Login" class="btn btn-primary btn-md"/> <i class="fa fa-sign-in"></i>
            </span>
            </div>
            </div>
        </div>
    </div>
</div>
</form:form>
</body>
</html>