
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/signup-style.css">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/Login.css"/>
</head>
<body>
<div id='container'>

  <div class='signup'>
		<% HttpSession mySession = request.getSession();
		String email = (String) mySession.getAttribute("email");
		
		%>
	<form action="/Clothify/View/Auth/Login" method="post">
	<label for="email">Email</label>
	<input type="email" name="email" placeholder="Please Enter Email" required >
	<label for="password">Password</label>
	

	
	<input type="password" name=password placeholder="Please Enter Password" required >	
	
		
	<input type="submit" placeholder="Submit">
	</form>
     <label>Don't have an account?</label>
      <h2><a href="Register.jsp">Register Now</a></h2>
<div class "top">
<a href="${pageContext.request.contextPath}/View/User/Home.jsp">View Webiste</a>
</div>
  </div>
  

</div>

</body>
</html>