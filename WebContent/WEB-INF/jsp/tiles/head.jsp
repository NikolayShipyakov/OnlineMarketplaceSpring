<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <link rel="stylesheet" type="text/css" href="css/head.css" />
</head>
<body>
	<div class="welcom">
		<div>
			<label class="welcom">You are logged in as : </label>
			<c:choose>
				<c:when test="${sessionScope.user.id > 0}">
					<label id="user" class="user">
						<c:out value="${sessionScope.user.fullName}" default="no sellers" escapeXml="true" /></label>
					<a id="log" href="tologin.html" onclick="logout()">logout</a>
				</c:when>
				<c:otherwise>
					<label class="user"> Guest</label>
					<a href="tologin.html">login</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>