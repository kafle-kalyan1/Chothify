<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/Cart.css"/>

</head>
<body>
    <div>
        <a href="${pageContext.request.contextPath}/View/User/Home.jsp" class="back-button">Back to Products</a>
    </div>
    <h1>My Cart</h1>
  <c:if test="${not empty cartItems}">
    <form action="${pageContext.request.contextPath}/OrderServlet" method="post">
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Image</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total Price</th>
                    <th>View</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${cartItems}" var="item">
                    <tr>
                        <td>${item.name}</td>
                        <td><img alt="${item.name}" src="${pageContext.request.contextPath}/Images/${item.image}"></td>
                        <td>${item.quantity}</td>
                        <td>${item.price}</td>
                        <td>${item.totalPrice}</td>
                        <td><a href="${pageContext.request.contextPath}/ProductDetail?id=${item.productId}">View</a></td>
                        <td><a href="${pageContext.request.contextPath}/RemoveFromCartServlet?id=${item.id}">Remove From Cart</a></td>
                    	<td>                    <input type="hidden" name="productId" value="${item.productId}">
                    <input type="hidden" name="quantity" value="${item.quantity}">
                    <input type="hidden" name="totalPrice" value="${item.totalPrice}">
                    <input type="hidden" name="cartId" value="${item.id}">
                    <button type="submit">Order</button></td>
                    </tr>

                </c:forEach>
                <tr>
                    <td colspan="5" align="right"><b>Total:</b></td>
                    <td>${total}</td>
                    <td></td>
                </tr>
            </tbody>
        </table>
        
    </form>
</c:if>
    <c:if test="${empty cartItems}">
        <p>Your cart is empty</p>
    </c:if>
</body>
</html>
