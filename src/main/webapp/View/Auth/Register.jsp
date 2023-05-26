<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Signup</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/Register.css"/>
</head>

<body>
<div id='container'>

  	<div class='signup'>

  		<form action="/Clothify/Register" method='post' enctype="multipart/form-data">

  			<label for="fullName">Full Name:</label>
  			<input type="text" name="fullName" id="fullName" required>
  			<br>
  			<br>
  			<label for="email">Email:</label>
  			<input type="email" name="email" id="email" required>
  			<br>
  			<br>
  			<label for="address">Address:</label>
  			<input type="text" name="address" id="address" required>
  			<br>
  			<br>
  			<label for="phone">Phone Number:</label>
  			<input type="text" name="phone" id="phone" required>
  			<br>
  			<br>
  			<label for="image">Profile Picture:</label>
  			<input type="file" name="image" id="image"
  				accept="image/*" required>
  			<br>
  			<br>
  			<label for="password">Password:</label>
  			<input type="password" name="password" id="password"
  				required>
  			<br>
  			<br>
  			<label for="confirmPassword">Confirm Password:</label>
  			<input type="password" name="confirmPassword"
  				id="confirmPassword" required>
  			<br>
  			<br>
  			<input type="submit" value="Register Now">
  		</form>
  		

  		<h2>
  		<label>Already have an account? </label>
  			<a href="Login.jsp">Login</a>
  		</h2>
  	</div>
</body>
</html>
