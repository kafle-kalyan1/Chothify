
<%@ page import="java.sql.SQLException" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/ProductDetail.css"/>
<html>
<head>
    <title>Product Details - ${product.name}</title>
   
</head>
<body>
    <div class="container">
    
       <div class="product">
            <div class="product-image">
                <img src="${pageContext.request.contextPath}/Images/${product.getImageUrl()} " alt="${product.name} ">
            </div>
            <div class="product-details">
                <h1>${product.name} </h1>
                <p><strong>Category:</strong> ${product.category}</p>
                <p><strong>Price: $</strong>${product.price}  </p>
                <p><strong>Brand:</strong> ${product.brand}   </p>
                <p><strong>Quantity:</strong> ${product.quantity}   </p>
                <p> <strong>Rating:</strong> ${product.rating}/5</p>
                    <form method="POST" action="${pageContext.request.contextPath}/AddToCartServlet">
        <input type="hidden" name="userID" value="${sessionScope.Uid}">
  <input type="hidden" name="productId" value="${product.id}">
  <input type="number" name="quantity" value="1" min="1">
  <input type="hidden" name="price" value="${product.price}">
        
        <button type="submit">Add to cart</button>
    </form>
            </div>
            
        </div>
        <a href="${pageContext.request.contextPath}/View/User/Home.jsp" class="back-button">Back to Products</a>
    </div>
</body>
</html>
