<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%

Object obj = session.getAttribute("email");
String email = obj != null ? obj.toString() : null;

if(email == null) {
    response.sendRedirect("View/Auth/Login.jsp");
}
else{
    boolean isAdmin = (boolean) session.getAttribute("isAdmin");
	  if (!isAdmin) {
    response.sendRedirect("../User/Home.jsp");
}
}
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Edit Product</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EditProduct.css"/>
<style>
img{
max-height:200px;
}

.preview {
    max-width: 200px;
    max-height: 200px;
    margin-top: 10px;
}
</style>
<body>
<a href="View/Admin/AdminHome.jsp" >Back</a> 
	<h2>Edit Product</h2>
		
					
	<c:if test="${not empty product}">
	
		<form method="post" action="${pageContext.request.contextPath}/UpdateProduct" enctype="multipart/form-data">
				<lable>ID: ${product.id} </lable> <br><br>
			<input type="hidden" name="id" value="${product.id}">
			<label for="name">Name:</label>
			<input type="text" name="name" value="${product.name}" required><br><br>
			<label for="category">Category:</label>
			<input type="text" name="category" value="${product.category}" required><br><br>
			<label for="price">Price:</label>
			<input type="text" name="price" value="${product.price}" required><br><br>
			<label for="quantity">Quantity:</label>
			<input type="text" name="quantity" value="${product.quantity}" required><br><br>
			<label for="brand">Brand:</label>
			<input type="text" name="brand" value="${product.brand}" required><br><br>
			
			<img alt="imagee" src="${pageContext.request.contextPath}/Images/${product.getImageUrl()} " ><br><br>
			<label for="image">Image:</label>
			<input type="file" name="image" accept="image/*" required><br><br>
			
			<input type="submit" value="Save Changes">
		</form>
	</c:if>
	
	<c:if test="${empty product}">
		<p>Invalid Product ID. Please try again.</p>
	</c:if>


	
</body>
</html>
