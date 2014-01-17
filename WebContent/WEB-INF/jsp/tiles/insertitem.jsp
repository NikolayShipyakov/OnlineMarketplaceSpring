<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" type="text/css" href="css/insertItem.css" />
  <script type="text/javascript" src="js/insertItemListeners.js"></script>
</head>
<body>
 <div class="inputfields">
  <f:form id="dataForm" method="post" commandName="goods">
   <div class="size">
    <div>
      <label>Category</label> 
      <f:select id="category" path="category" cssStyle="categorys">
      <c:forEach items="${sessionScope.categorys}" var="category">
         <option>${category.fullName}</option>
      </c:forEach>
     </f:select>
    </div>
    <div>
       <label>Title of item:</label> 
       <f:input id="title" path="title"/>
    </div>
    <div>
       <label>Description:</label>
       <f:textarea id="description" path="description" cols="33" rows="3"></f:textarea>
    </div>
    <div>
       <label>Start price:</label> 
       <f:input id="startprice" path="price"/>
    </div>
    <div>
       <label>Bid increment:</label> 
       <f:input id="bidincrement" path="bidIncr" />
    </div>
    <div>
       <label>Time left:</label> 
       <f:input id="timeleft" path="timeLeft" />
    </div>
    <div>
       <label>Buy it now:</label> 
       <f:checkbox id="buyitnow" path="buyItNow" onclick="buyCheck()" />
    </div>
   </div>
  </f:form>
  <div class="buttons">
     <input id="save" type="button" value="Publish/Save" onclick="save()" />
     <input class="margenleft" id="reset" type="button" value="Reset"
       onclick="reset()" />
     <input id="back" class="margenleft" type="button" value="Back" 
            onclick="back()" />
  </div>
 </div>
   <f:form commandName="goods">
      <div class="buttons">
        <span id="resultop"> 
           <f:errors path="title"/><br/>
           <f:errors path="description"/><br/>
           <f:errors path="price"/><br/>
           <f:errors path="timeLeft"/><br/>
           <f:errors path="buyItNow"/><br/>
        </span>
      </div>
   </f:form>
</body>
</html>