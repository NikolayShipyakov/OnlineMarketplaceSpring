<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" type="text/css" href="css/history.css" />
</head>
<body>
<div class="datas">
		<div>
			<label class="names">Id item:</label> 
            <label>${listgoods[0].itemId}</label>
		</div>
		<div>
			<label class="names">Title:</label> 
            <label><c:out value="${listgoods[0].title}" default="NO TITLE" escapeXml="true" /></label>
		</div>
		<div>
			<label class="names">Description:</label> 
            <label><c:out value="${listgoods[0].description}" default="NO TITLE" escapeXml="true" /></label>
		</div>
		<div>
			<label class="names">Price:</label> 
            <label>${listgoods[0].startPrice}</label>
		</div>
		<div>
			<label class="names">Seller:</label> 
            <label><c:out value="${listgoods[0].seller}" default="No Seller" escapeXml="true" /></label>
		</div>
	</div>
	<table class="info">
		<tbody>
			<tr>
				<th class="n">N</th>
				<th class="bidder">Bidder</th>
				<th>Bid</th>
			</tr>
			<c:set var="num" scope="page" value="1" />
			<c:forEach items="${listgoods}" var="goods">
				<tr>
					<td class="n">${num}</td>
					<td class="bidder"><c:out value="${goods.bidder}" escapeXml="true" /></td>
					<td><c:out value="${goods.bestOffer}" default="NO TITLE" escapeXml="true" /></td>
				</tr>
				<c:set var="num" scope="page" value="${num+1}" />
			</c:forEach>
		</tbody>
	</table>
</body>
</html>