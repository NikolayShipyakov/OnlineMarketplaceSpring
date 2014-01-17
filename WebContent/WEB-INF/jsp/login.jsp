<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
  <link rel="stylesheet" type="text/css" href="css/login.css" />
  <script type="text/javascript" src="js/stringFunction.js"></script>
  <script type="text/javascript" src="js/functions.js"></script>
  <script type="text/javascript" src="js/loginListeners.js"></script>
</head>
<body>
	<div>
		<div class="head">
			<label>Online marketplace</label>
		</div>
		<f:form id="registrationForm" method="post"
			commandName="userTransfer">
			<div id="registration" class="size">
				<div>
					<c:if test="${auth == false}">
						<label class="error">Error login/password</label>
					</c:if>
				</div>
				<div class="inputfields">
					<div>
						<label class="log_pass">Login</label> 
                        <f:input id="login" path="login" /> 
                        <a id="sign" href="#" onclick=sign();>Sign in</a>
					</div>
					<div>
						<label class="log_pass">Password</label> 
                        <f:password id="password" path="password" /> <a
							href="showitems.html">Enter as guest</a>
					</div>
					<div>
						<a class="register" href="registration.html">Register</a>
					</div>
				</div>
				<div>
					<label id="warning" class="errorhidden">Authorization
						error. The length Login or Password doesn't correspond to the
						minimum.</label>
				</div>
			</div>
		</f:form>
	</div>
</body>
</html>