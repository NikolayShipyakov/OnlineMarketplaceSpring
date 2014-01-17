<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
  <script type="text/javascript" src="js/functions.js"></script>
  <script type="text/javascript" src="js/registrationListeners.js"></script>
  <script type="text/javascript" src="js/stringFunction.js"></script>
  <link rel="stylesheet" type="text/css" href="css/registration.css" />
</head>
<body>
	<div class="head">
		<label class="label">Online marketplace. Registration</label>
	</div>
	<f:form method="POST" commandName="user"
		onsubmit="return validUserData();">
		<div class="size">
			<div>
				<label class="name_fields">Full name:</label> 
                <f:input id="name" path="fullName" cssClass="text"  />
			</div>
			<div>
				<label class="name_fields">Billing address:</label> 
                <f:input id="adress" path="address" cssClass="text"  />
			</div>
			<div>
				<label class="name_fields">Mail:</label> 
                <f:input id="mail" path="mail" cssClass="text" />
			</div>
			<div>
				<label class="name_fields">Telephone:</label> 
                <f:input cssClass="text" id="phone" path="phone" />
			</div>
			<br />
			<div>
				<label class="name_fields">Login:</label> 
                <f:input cssClass="text" id="login" path="login" />
			</div>
			<div>
				<label class="name_fields">Password:</label> 
                <f:password cssClass="text" id="password" path="password" /> <br />
			</div>
			<div>
				<label class="warning">6 character minimum</label>
			</div>
			<div>
				<label class="name_fields">Re-Enter Password:</label> 
                <f:password id="repassword" cssClass="text" path="repassword" />
			</div>
			<br />
			<div class="buttons">
				<input type="submit" value="Registration" /> 
                <input class="reset" type="button" value="Reset" onclick="reset()" /> 
                <input class="reset" type="button" value="Back" onclick="back()" />
			</div>
		</div>
	</f:form>
	<div class="warningmessage">
		<label id="warningtext" > 
         <c:if test="${error != null}">
           ${error}
        </c:if> </label>
        <f:form commandName="user">
          <f:errors path="login"/><br/>
          <f:errors path="password"/><br/>
          <f:errors path="mail" /><br/>
          <f:errors path="phone" /><br/>
        </f:form>
	</div>
</body>
</html>