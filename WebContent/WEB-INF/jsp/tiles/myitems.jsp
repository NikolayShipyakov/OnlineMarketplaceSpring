<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <link rel="stylesheet" type="text/css" href="css/showMyItems.css" />
  <script type="text/javascript" src="js/showItemsListeners.js"></script>
</head>
<body>
<div class="table">
  <div class="aftertable">
   <label>Items</label> 
            <label>${pagenumber} page in ${countpage}</label>
  </div>
  <table>
   <tbody id="items">
    <tr class="head">
     <th class="uid">UID</th>
     <th onclick="fill('${command}?pagenumber=1&direction=${1-direction}&typesort=title');">
                       Title
      <c:choose>
       <c:when test="${direction==0 && typesort=='title'}">
        <img id="imgTitle" src="imgs/down.png" alt="sort" /> 
       </c:when>
       <c:when test="${direction==1 && typesort=='title'}">
        <img id="imgTitle" src="imgs/up.png" alt="sort" /> 
       </c:when>
       <c:otherwise>
         <img id="imgTitle" src="imgs/updown.png" alt="sort" /> 
       </c:otherwise>
       </c:choose>
     </th>
     <th>Description</th>
     <th>Category</th>
     <th class="seller">Seller</th>
     <th class="price">Start Price</th>
     <th class="increment">Bid inc</th>
     <th class="bestoffer" onclick="fill('${command}?pagenumber=1&direction=${1-direction}&typesort=Bid');">
                      Best offer
        <c:choose>
        <c:when test="${direction==0 && typesort=='Bid'}">
         <img id="imgTitle" src="imgs/down.png" alt="sort" />
        </c:when>
        <c:when test="${direction==1 && typesort=='Bid'}">
         <img id="imgTitle" src="imgs/up.png" alt="sort" />
        </c:when>
        <c:otherwise>
         <img id="imgOffer" src="imgs/updown.png" alt="sort" />
        </c:otherwise>
        </c:choose>
     </th>
     <th class="bidder">Bidder</th>
     <th class="date">Stop date</th>
     <th class="action">Action</th>
    </tr>
    <c:forEach items="${listgoods}" var="goods">
     <tr>
      <td class="uid">${goods.itemId}</td>
      <td><c:out value="${goods.title}" default="no title" escapeXml="true" /></td>
      <td><c:out value="${goods.description}" default="no description" escapeXml="true" /></td>
      <td><c:out value="${goods.categoryName}" default="no category" escapeXml="true" /></td>
      <td class="seller"><c:out value="${goods.seller}" escapeXml="true" /></td>
      <td class="price">${goods.startPrice}</td>
      <td class="increment">${goods.bidIncrement}</td>
      <td class="bestoffer">${goods.bestOffer}</td>
      <td class="bidder"><c:out value="${goods.bidder}" escapeXml="true" /></td>
      <td class="date">${goods.formattingStopDate}</td>
      <c:choose>
       <c:when test="${goods.isSold == true}">
        <td class="text">Sold</td>
       </c:when>
       <c:when test="${goods.isTimeExpired == true}">
        <td class="text">Time is up</td>
       </c:when>
       <c:when test="${goods.sellerId > 0 && goods.sellerId != user.id}">
        <td class="text">Bid</td>
       </c:when>
       <c:otherwise>
        <td class="action">
         <div>
          <input class="deleteedit" type="button" value="edit"
           onclick="editItem(${goods.itemId})" /> <input
           class="deleteedit" type="button" value="del"
           onclick="delItem(${goods.itemId})" />
         </div></td>
       </c:otherwise>
      </c:choose>
     </tr>
    </c:forEach>
   </tbody>
  </table>
  <c:if test="${prevpage == true}">
   <a class="firstpage"
    href="${command}?pagenumber=1&direction=${direction}&typesort=${typesort}&">
    first page</a>
   <a class="prevpage"
    href="${command}?pagenumber=${pagenumber-1}&direction=${direction}&typesort=${typesort}">previous
    page</a>
  </c:if>
  <c:if test="${nextpage == true}">
   <a class="lastpage"
    href="${command}?pagenumber=${countpage}&direction=${direction}&typesort=${typesort}">last
    page</a>
   <a class="nextpage"
    href="${command}?pagenumber=${pagenumber+1}&direction=${direction}&typesort=${typesort}">next
    page</a>
  </c:if>
 </div>
 <c:remove var="prevpage" />
 <c:remove var="nextpage" />
</body>
</html>