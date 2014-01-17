<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" type="text/css" href="css/users.css" />
<title>Insert title here</title>
</head>
<body>
<table class="table">
 <tbody>
  <tr>
   <th class="n">N</th>
   <th class="user">Full name</th>
   <th class="banbuttons">Action</th>
  </tr>
  <c:set var="num" scope="page" value="1" />
  <c:forEach items="${users}" var="user">
   <tr>
    <td class="n">${num}</td>
    <c:choose>
     <c:when test="${user.isBan == true}">
      <td class="banuser"><c:out value="${user.fullName}" default="NO TITLE" escapeXml="true" /></td>
      <td class="banbuttons">
      <form method="post" action="unBan.html"><input class="banbutton"
       type="submit" value="UnBan" /> <input type="hidden" value="${user.id}"
       name="bidder" /></form>
      </td>
     </c:when>
     <c:otherwise>
      <td class="unbanuser"><c:out value="${user.fullName}" default="NO TITLE" escapeXml="true" /></td>
      <td class="banbuttons">
      <form method="post" action="ban.html"><input class="banbutton"
       type="submit" value="Ban" /> <input type="hidden" value="${user.id}"
       name="bidder" /></form>
      </td>
     </c:otherwise>
    </c:choose>
   </tr>
   <c:set var="num" scope="page" value="${num+1}" />
  </c:forEach>
 </tbody>
</table>
</body>
</html>