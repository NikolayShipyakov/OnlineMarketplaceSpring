<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" type="text/css" href="css/insertItem.css" />
  <script type="text/javascript" src="js/editItemListeners.js"></script>
</head>
<body>
<c:choose>
		<c:when test="${goods.isSold == false}">
			<div class="inputfields">
				<f:form id="dataForm" method="post" commandName="goods">
					<div class="size">
						<f:hidden path="itemId" />
						<c:choose>
							<c:when test="${goods.bidder == 0}">
								<div>
									<label>Title of item:</label> 
                                    <f:input id="title" path="title"/>
								</div>
								<div>
									<label>Category</label> 
                                    <f:select path="category">
										<c:forEach items="${categorys}" var="category">
											<c:choose>
												<c:when test="${category.fullName == goods.category}">
													<option selected="selected">${category.name}</option>
												</c:when>
												<c:otherwise>
													<option>${category.fullName}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</f:select>
								</div>
							</c:when>
							<c:otherwise>
								<div>
									<label>Title of item:</label> 
                                    <f:input id="title" path="title" readonly="readonly" />
								</div>
							</c:otherwise>
						</c:choose>
						<div>
							<label>Description:</label>
							<f:textarea id="description" path="description" cols="33" rows="3"/>
						</div>
						<c:choose>
							<c:when test="${goods.bidder == null}">
								<div>
									<label>Start price:</label> 
                                    <f:input id="startprice" path="price" />
								</div>
							</c:when>
							<c:otherwise>
								<div>
									<label>Start price:</label> 
                                    <f:input id="startprice" path="price"
										readonly="readonly" />
								</div>
							</c:otherwise>
						</c:choose>
						<div>
							<label>Bid increment:</label>
							<c:choose>
								<c:when test="${goods.buyItNow != true}">
									<f:input id="bidincrement" path="bidIncr" />
								</c:when>
								<c:otherwise>
									<f:input id="bidincrement" path="bidIncr" 
                                      disabled="true" />
								</c:otherwise>
							</c:choose>
						</div>
						<div>
							<label>Time left:</label> 
                            <f:input id="timeleft" path="timeLeft" />
						</div>
						<div>
							<label>Buy it now:</label>
							<c:choose>
								<c:when test="${goods.bidder == null && goods.buyItNow != true}">
									<label>Buy it now:</label>
									<f:checkbox id="buyitnow" onclick="buyCheck()" path="buyItNow"/>
								</c:when>
								<c:when test="${goods.bidder == null && goods.buyItNow == true}">
									<f:checkbox id="buyitnow" path="buyItNow" onclick="buyCheck()" value="checked"/>
								</c:when>
								<c:otherwise>
									<f:checkbox id="buyitnow" path="buyItNow"  onclick="buyCheck()" disabled="disabled" />
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</f:form>
				<div class="buttons">
					<input id="save" type="button" value="Save" onclick="save()" /> 
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
		</c:when>
		<c:otherwise>
			<div>
				This item is sold 
                <input id="back" type="button" value="Back"	onclick="back()" />
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>