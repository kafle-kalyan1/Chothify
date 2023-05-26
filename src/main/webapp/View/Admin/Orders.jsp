<%
HttpSession mySession = request.getSession();
String email = (String) session.getAttribute("email");
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/Orders.css"/>

    <script>
        function showDeliveryAlert() {
            alert("Order has been delivered successfully!");
        }
    </script>
</head>
<body>

    <h1>Order List</h1>
    
    <c:if test="${not empty orderList}">
        <table>
            <thead>

                <tr>
                    <th>ID</th>
                    <th>User ID</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Total Price</th>
                    <th>Order Date</th>
                    <th>Image</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${orderList}" var="order">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.userId}</td>
                        <td>${order.productName}</td>
                        <td>${order.quantity}</td>
                        <td>${order.totalPrice}</td>
                        <td>${order.orderDate}</td>
                        
                        <td>
                            <img alt="${order.productName}" src="${pageContext.request.contextPath}/Images/${order.productImage}">
                        </td>
                          <td>
                          <button class="delivery-button" onclick="showDeliveryAlert()">Deliver</button>
                          </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
      
    </c:if>
    <c:if test="${empty orderList}">
        <p>No orders found.</p>
    </c:if>
    <div id="bck">
    <a href="${pageContext.request.contextPath}/View/Admin/AdminHome.jsp">Go Back</a>
    </div>
</body>
</html>
