<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%

Object obj = session.getAttribute("email");
String email = obj != null ? obj.toString() : null;

if(email == null) {
    response.sendRedirect("View/Auth/Login.jsp");
}

%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Edit Profile</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/Profile.css"/>
<body>
<a href="View/Admin/AdminHome.jsp" >Back</a> 
 <div class="container">
	<h2>Edit Profile</h2>
	<% String userId = request.getParameter("id"); %>

	
	<c:if test="${not empty user}">
	<img src="${pageContext.request.contextPath}/Images/${user.image}" alt="${user.fullName}'s Photo">
		<form method="post" action="${pageContext.request.contextPath}/SaveProfile" enctype="multipart/form-data">
			<input type="hidden" name="sysPassword" value = "${user.password }">
			<label for="name">Name:</label>
			<input type="text" name="name" value="${user.fullName}"><br><br>
			<label for="email">Email:</label>
			<input type="text" name="email" value="${user.email}"><br><br>
			<label for="address">Address:</label>
			<input type="text" name="address" value="${user.address}"><br><br>
			<label for="phone">Phone:</label>
			<input type="text" name="phone" value="${user.phone}"><br><br>
			
			<button  onclick="promptPassword()">Save Changes</button>
		</form>
		
		<script>
		function promptPassword() {
			var password = prompt("Please enter your password:", "");
			
			if (password != null) {
				var form = document.forms[0];
				var passwordInput = document.createElement("input");
				passwordInput.type = "hidden";
				passwordInput.name = "password";
				passwordInput.value = password;
				form.appendChild(passwordInput);
				form.submit();
			
		}
		}
		</script>
		
		 
	</c:if>
	
	<c:if test="${empty user}">
		<p>Invalid User ID. Please try again.</p>
	</c:if>
	</div>
	
	
</body>
</html>
