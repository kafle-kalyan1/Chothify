<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.jasper.JasperException" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="model.Products" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    // Retrieve all products from the database
    List<Products> productsList = null;
    try {
        productsList = Products.getAllProducts();
    } catch (SQLException | ClassNotFoundException| NullPointerException  e) {
    	request.setAttribute("errorMessage", "Cann't Show products");
        request.getRequestDispatcher("View/error.jsp").forward(request, response);
    }
  
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/Home.css"/>
</head>
  
<body>
    <header>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/View/User/Home.jsp">Home</a></li>
            
                <li><a href="#">Product</a></li>
                <li><a href="#">Contact Us</a></li>
            </ul>
            <label class="search-label">
    <input type="text" name="text" class="input" required="" placeholder="Type here...">
    <kbd class="slash-icon">/</kbd>
    <svg class="search-icon" xmlns="http://www.w3.org/2000/svg" version="1.1" xmlns:xlink="http://www.w3.org/1999/xlink" width="512" height="512" x="0" y="0" viewBox="0 0 56.966 56.966" style="enable-background:new 0 0 512 512" xml:space="preserve">
      <g>
        <path d="M55.146 51.887 41.588 37.786A22.926 22.926 0 0 0 46.984 23c0-12.682-10.318-23-23-23s-23 10.318-23 23 10.318 23 23 23c4.761 0 9.298-1.436 13.177-4.162l13.661 14.208c.571.593 1.339.92 2.162.92.779 0 1.518-.297 2.079-.837a3.004 3.004 0 0 0 .083-4.242zM23.984 6c9.374 0 17 7.626 17 17s-7.626 17-17 17-17-7.626-17-17 7.626-17 17-17z" fill="currentColor" data-original="#000000" class=""></path>
      </g>
    </svg>
  </label>
            <div class="right-nav">
                            <div>
                            <a href="${pageContext.request.contextPath}/CartServlet" class="cart">Cart</a>
                            </div>
                <c:choose>
                    <c:when test="${sessionScope.email != null}">
                      Hi,  <a href="${pageContext.request.contextPath}/UpdateProfile" class="profile-name"> ${sessionScope.email}</a>
                        <div>
                            <a href="${pageContext.request.contextPath}/View/Auth/logout.jsp" type="submit">Logout</a>
                        </div>
                            
                      
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/View/Auth/Login.jsp" class="button">Login</a>
                    </c:otherwise>
                </c:choose>
                
            </div>
        </nav>
    </header>

    <main>
        <section id="featured-products">
            <h2>Featured Products</h2>
            <div class="products-container">
                <% for (Products product : productsList) { %>
                   <div class="product">
                   
    <a href="${pageContext.request.contextPath}/ProductDetail?id=<%= product.getId() %>">
        <img src="${pageContext.request.contextPath}/Images/<%= product.getImageUrl() %>" alt="<%= product.getImageUrl() %>">
        <h3><%= product.getName() %></h3>
        <p class="price">Price $<%= product.getPrice() %></p>
        <p> Brand: <%= product.getBrand() %></p>
    </a>
<form method="post" action="${pageContext.request.contextPath}/AddToCartServlet">
<input type="hidden" name="userID" value="${sessionScope.Uid}">
  <input type="hidden" name="productId" value="<%= product.getId() %>">
  <input type="number" name="quantity" value="1" min="1">
  <input type="hidden" name="price" value="<%= product.getPrice() %>">
   <c:choose>
                    <c:when test="${sessionScope.email != null}">
                        <button type="submit">Add to Cart</button>
                        
                    </c:when>
                    <c:otherwise>
                        <button onclick = "notLogin()" type="button" >Add to Cart</button>
                        
                    </c:otherwise>
                </c:choose>
  
  
</form>

</div>

                <% } %>
            </div>
        </section>
    </main>
</body>
<script>
function notLogin(){
	alert("Please login to do this");
}
</script>
</html>
