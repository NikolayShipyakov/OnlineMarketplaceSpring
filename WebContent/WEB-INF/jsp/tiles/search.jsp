<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <link rel="stylesheet" type="text/css" href="css/search.css" />
</head>
<body>
	<form id="searchForm" method="get" action="searchitems.html">
		<div class="search">
			<label class="parameters">Search parameters</label> <br /> <label
				class="keyword">Keyword:</label>
		</div>
		<div class="buttonssearch">
			<input type="hidden" name="pagenumber" value="1" /> <input
				type="hidden" name="direction" value="0" /> <input type="hidden"
				name="typesort" value="title" /> <input id="keyword" type="text"
				name="keyword" class="textsearch" /> <select id="typeSearch"
				class="typesearch" name="typesearch">
				<option>Title</option>
				<option>Description</option>
				<option>Id</option>
			</select> <input type="button" value="Search" onclick="search();" />
            <a href="advancedsearch.html" class = "advancedSearch">Advanced search</a>
		</div>
	</form>
	<form id="searchCategoryForm" method="get" action="searchitems.html">
		<div class="search">
			<label class="parameters">Search on category</label> <br />
		</div>
		<div class="buttonssearch">
			<input type="hidden" name="pagenumber" value="1" /> <input
				type="hidden" name="direction" value="0" /> <input type="hidden"
				name="typesort" value="title" /> <input type="hidden"
				name="typesearch" value="Category" /> <select id="categorys"
				class="keyword" name="keyword">
				<c:forEach items="${sessionScope.categorys}" var="category">
					<option>${category.fullName}</option>
				</c:forEach>
			</select> <input type="button" value="Search" onclick="searchCategorys('');" />
		</div>
	</form>
</body>
</html>