<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <link rel="stylesheet" type="text/css" href="css/navigation.css" />
</head>
<body>
 <div class="links">
   <a href="showitems.html?pagenumber=1&direction=1&typesort=title"
    class="links">Show All Items</a>
   <c:if test="${sessionScope.user.id > 0}">
     <a id="myitems"
       href="showMyItems.html?pagenumber=1&direction=1&typesort=title"
       class="links">Show My Items</a>
     <a id="sell" href="insertItem.html" class="links">Sell</a>
     <a id="showusers" href="users.html" class="links">Show
       Users</a>
   </c:if>
 </div>
</body>
</html>