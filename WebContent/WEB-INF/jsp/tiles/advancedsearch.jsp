<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" type="text/css" href="css/advancedSearch.css" />
  <script type="text/javascript" src="js/advancedSearchListeners.js"></script>
</head>
<body>
 <f:form method="GET" commandName="goods">
  <div class="size">
   <div>
      <label class="name_fields">Item uid:</label> 
      <f:input id="itemId" path="itemId" cssClass="text"  />
   </div>
   <div>
      <label class="name_fields">Title:</label> 
      <f:input id="title" path="title" cssClass="text"  />
   </div>
   <div>
      <label class="name_fields">Description:</label> 
      <f:input id="description" path="description" cssClass="text" />
   </div>
   <div>
      <f:errors path="itemId"/>
   </div> 
   <hr/>
   <div>
      <label class="name_fields">Min.price</label>
      <f:input id="price" path="price" cssClass="text_min"/>
      <label class="max_price">Max.price</label>
      <f:input id="maxPrice" path="maxPrice" cssClass="text_min"/>
      <f:errors path="price"/>
      <f:errors path="maxPrice"/>
   </div>
   <hr/>
    <div>
       <f:checkbox path="buyItNow"/>
       <label>Show Only Buy It Now Items</label>
    </div>
    <hr/> 
    <div>
       <label class="name_fields">Start date:</label>
       <f:input id="startDate" path="startDate" cssClass="text" />
    </div>
    <div>
       <label class="name_fields">Expire date:</label>
       <f:input id="expireDate" path="expireDate" cssClass="text" />
    </div>
    <div>
       <label class="warning_red_text">Format date DD/MM/YYYY HH:MM</label>
    </div>
    <div>
       <f:errors path="startDate"/>
       <f:errors path="expireDate"/>
    </div>
    <hr/>
    <div>
       <label class="name_fields">Bidder count:</label>
       <f:input id="bidderCount" path="bidderCount" cssClass="text_min" />
    </div>
    <div>
      <f:errors path="bidderCount"/>
    </div>
    <br />
    <div class="buttons">
      <input type="submit" value="Search" /> 
      <input class="reset" type="button" value="Clear search" onclick="clearSearch()" /> 
      <input class="reset" type="button" value="Back" onclick="back()" />
   </div>
  </div>
 </f:form>
</body>
</html>