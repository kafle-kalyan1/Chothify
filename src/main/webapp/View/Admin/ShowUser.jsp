<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User List</title>
</head>
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/ShowUsers.css"/>
<body>
<a href="View/Admin/AdminHome.jsp" >Back</a> 
    <h1>User List</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Image</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.fullName}</td>
                <td>${user.email}</td>
                <td>${user.address}</td>
                <td>${user.phone}</td>
                <td><img src="${pageContext.request.contextPath}/Images/${user.image}" height="50px"></td>
                <td> <button> Delete user</button>  </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
