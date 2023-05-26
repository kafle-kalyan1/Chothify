<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Add New Product</title>
	
</head>
<%

String email = (String) session.getAttribute("email");
if(email == null) {
    response.sendRedirect("../Auth/Login.jsp");
}
else{
    boolean isAdmin = (boolean) session.getAttribute("isAdmin");
	  if (!isAdmin) {
    response.sendRedirect("../User/Home.jsp");
}
}
%>
<body>
<a href="AdminHome.jsp" >Back</a> 
	<h2>Add New Product</h2>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/AddProduct.css"/>
	

	<form method="post" action="/Clothify/AddProduct" enctype="multipart/form-data">
		
		<div class="input-group">
			<label for="productName">Product Name:</label>
			<input type="text" id="productName" name="productName" required>
		</div>
		
		<div class="input-group">
			<label for="category">Category:</label>
			<input type="text" id="category" name="category" required>
		</div>

		<div class="input-group">
			<label for="price">Price:</label>
			<input type="text" id="price" name="price"  required>
		</div>

		<div class="input-group">
			<label for="quantity">Quantity:</label>
			<input type="text" id="quantity" name="quantity" required>
		</div>

		<div class="input-group">
			<label for="brand">Brand:</label>
			<input type="text" id="brand" name="brand" required>
		</div>

		<div class="input-group">
			<label for="image">Image:</label>
			<input type="file" id="image" name="image" accept="image/*" required>
		</div>

		<button type="submit">Add Product <i class="far"></i></button>
	</form>

</body>
</html>
