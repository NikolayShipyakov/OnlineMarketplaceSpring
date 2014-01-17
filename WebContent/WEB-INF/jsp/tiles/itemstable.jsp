<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <link rel="stylesheet" type="text/css" href="css/itemstable.css" />
   <script type="text/javascript" src="js/showItemsListeners.js"></script>
</head>
<body>
 <div class="table">
  <c:if test="${sessionScope.biderror != null}">
   <div class="error">${sessionScope.biderror}</div>
  </c:if>
  <div class="aftertable">
   <label>Items</label> <label>${pagenumber} page in
    ${countpage}</label>
  </div>
  <table>
   <tbody id="items">
    <tr class="head">
     <th class="uid">UID</th>
                    <c:choose>
                     <c:when test="${command != null}">
      <th onclick="fill('${command}?pagenumber=1&direction=${1-direction}&typesort=title');">Title
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
      </c:when>
      <c:otherwise>
       <th>Title<img id="imgTitle" src="imgs/updown.png" alt="sort" /></th>
      </c:otherwise>
      </c:choose>
     <th>Description</th>
     <th>Category</th>
     <th class="seller">Seller</th>
     <th class="price">Start Price</th>
     <th class="increment">Bid inc</th>
                    <c:choose>
      <c:when test="${command != null}">
                      <th class="bestoffer"
        onclick="fill('${command}?pagenumber=1&direction=${1-direction}&typesort=Bid');">Best
        offer
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
                     </c:when>
                     <c:otherwise>
                       <th class="bestoffer">Bestoffer<img id="imgOffer" src="imgs/updown.png" alt="sort" />
                       </th>
                     </c:otherwise>
                    </c:choose>
     <th class="bidder">Bidder</th>
     <th class="date">Stop date</th>
     <th class="bidding">Bidding</th>
    </tr>
    <c:forEach items="${listgoods}" var="goods">
     <tr>
      <td class="uid">${goods.itemId}</td>
      <td><a href="infoofitem.html?itemid=${goods.itemId}"
       class="links"><c:out value="${goods.title}" default="NO TITLE" escapeXml="true" /></a>
      </td>
      <td><c:out value="${goods.description}" default="NO DESCRIPTION" escapeXml="true" /></td>
      <td><c:out value="${goods.categoryName}" escapeXml="true" /></td>
      <td class="seller"><c:out value="${goods.seller}" escapeXml="true" /></td>
      <td id="price${goods.itemId}" class="price">${goods.startPrice}</td>
      <td class="increment">${goods.bidIncrement}</td>
      <td class="bestoffer">${goods.bestOffer}</td>
      <td class="bidder"><c:out value="${goods.bidder}" escapeXml="true" /></td>
      <td class="date">${goods.formattingStopDate}</td>
      <c:choose>
       <c:when test="${goods.isBan == true}">
        <td class="saleexpired">Ban</td>
       </c:when>
       <c:when test="${goods.isSaleProceeds == true}">
        <td class="saleexpired"><c:choose>
          <c:when test="${goods.isSold == true}">
                                     Sold
                                 </c:when>
          <c:when test="${goods.isTimeExpired == true}">
                                     Time is up
                                 </c:when>
         </c:choose></td>
       </c:when>
       <c:otherwise>
        <td class="bidding"><c:if test="${user.id > 0}">
          <c:choose>
           <c:when
            test="${goods.sellerLogin != sessionScope.user.login}">
            <c:choose>
             <c:when test="${goods.buyItNow == true}">
              <form method="post" action="buy.html">
               <input type="hidden" name="id" value="${goods.itemId}" />
               <input type="hidden" name="bid"
                value="${goods.startPrice}" /> <input type="submit"
                class="buybutton" value="Buy" />
                <input type="hidden" name="sellerId" value="${goods.sellerId}" />
              </form>
             </c:when>
             <c:when test="${sessionScope.user.id > 0}">
              <form method="post" action="bid.html"
               onsubmit="return buy(${goods.itemId}, ${goods.bestOffer})">
               <div class="biddiv">
                <input type="hidden" name="id" value="${goods.itemId}" />
                <input type="hidden" name="sellerId" value="${goods.sellerId}" />
                <input type="text" id="bid${goods.itemId}" name="bid" />
                <input type="submit" class="bidbutton" value="Bid" />
               </div>
              </form>
             </c:when>
            </c:choose>
           </c:when>
           <c:otherwise>
            <div>
             <input class="deleteedit" type="button" value="edit"
              onclick="editItem(${goods.itemId})" /> <input
              class="deleteedit" type="button" value="del"
              onclick="delItem(${goods.itemId})" />
            </div>
           </c:otherwise>
          </c:choose>
         </c:if></td>
       </c:otherwise>
      </c:choose>
     </tr>
    </c:forEach>
   </tbody>
  </table>
  <c:if test="${prevpage == true}">
   <a class="firstpage"
    href="${command}?pagenumber=1&direction=${direction}&typesort=${typesort}&typesearch=${typesearch}">
    first page</a>
   <a class="prevpage"
    href="${command}?pagenumber=${pagenumber-1}&direction=${direction}&typesort=${typesort}&typesearch=${typesearch}">previous
    page</a>
  </c:if>
  <c:if test="${nextpage == true}">
   <a class="lastpage"
    href="${command}?pagenumber=${countpage}&direction=${direction}&typesort=${typesort}&typesearch=${typesearch}">last
    page</a>
   <a class="nextpage"
    href="${command}?pagenumber=${pagenumber+1}&direction=${direction}&typesort=${typesort}&typesearch=${typesearch}">next
    page</a>
  </c:if>
 </div>
 <c:remove var="prevpage" />
 <c:remove var="nextpage" />
 <c:remove var="biderror" />
</body>
</html>