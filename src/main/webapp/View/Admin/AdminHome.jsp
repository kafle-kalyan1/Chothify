<%@ page import="java.util.List" %>
<%@ page import="model.Products" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>

<%@ page import="javax.servlet.http.HttpSession" %>
<%

Object obj = session.getAttribute("email");
String email = obj != null ? obj.toString() : null;

System.out.println(session.getAttribute("email"));
    if(email == null) {
        response.sendRedirect("../Auth/Login.jsp");
    }
    else{
        boolean isAdmin = (boolean) session.getAttribute("isAdmin");
    	  if (!isAdmin) {
        response.sendRedirect("../User/Home.jsp?msg=Ad_Err");
    }
    }
   
%>
<%
    // Retrieve all products from the database
    List<Products> productsList = null;
    try {
        productsList = Products.getAllProducts();
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/AdminHome.css"/>
<body>
    <div class="container">
        <br>
        <h2>Welcome Admin, <%=session.getAttribute("email")%></h2>
        <a href="../Auth/logout.jsp">Logout</a>
        <hr>
        <br>

        <a href="${pageContext.request.contextPath}/View/Admin/AddProduct.jsp" class="btn">Add Product</a>
        <a href="${pageContext.request.contextPath}/users" class="btn">See Users</a>
		<a href="${pageContext.request.contextPath}/orderList" class="btn">Ordered Items</a>
		<a href="${pageContext.request.contextPath}/View/User/Home.jsp" class="btn">View website</a>
        <br><br>

        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Rating</th>
                    <th>Brand</th>
                    <th>Image</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% for (Products product : productsList) { %>
                    <tr>
                        <td><%= product.getId() %></td>
                        <td><%= product.getName() %></td>
                        <td><%= product.getCategory() %></td>
                        <td>$ <%= product.getPrice() %></td>
                        <td><%= product.getQuantity() %></td>
                        <td><%= product.getRating() %></td>
                        <td><%= product.getBrand() %></td>
                        <td><img src="${pageContext.request.contextPath}/Images/<%= product.getImageUrl() %>" alt="${pageContext.request.contextPath}/Images/${product.getImageUrl()}"></td>
                        <td>
                           <a href="${pageContext.request.contextPath}/EditProduct?id=<%=product.getId()%>" class="btn">Edit</a>
                         
                           
                            <a href="${pageContext.request.contextPath}/DeleteProduct?id=<%=product.getId()%>" class="btn" onclick="return confirm('Are you sure you want to delete this product?')">Delete</a>
                        </td>
                    </tr>
                    
                <% } %>
            </tbody>
        </table>

    </div>
    	

	
    </body>
    </html>