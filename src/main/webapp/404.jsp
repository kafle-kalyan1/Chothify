<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404 Not Found</title>

<style>
    body {
        background-color: #f5f5f5;
        font-family: Arial, sans-serif;
        color: #444;
        text-align: center;
    }
    
    h1 {
        margin-top: 50px;
        font-size: 5em;
        font-weight: bold;
    }
    
    p {
        font-size: 1.5em;
        margin-top: 30px;
        margin-bottom: 50px;
    }
    
    a {
        color: #007bff;
        text-decoration: none;
    }
</style>

</head>
<body>
    <h1>404</h1>
    <p>Sorry, the page you're looking for cannot be found.</p>
    <p><a href="${pageContext.request.contextPath}/View/User/Home.jsp">Go back to the home page</a></p>
</body>
</html>
