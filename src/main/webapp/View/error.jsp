<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
<style>
    .container {
        margin-top: 50px;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        font-size: 24px;
    }
    .error-message {
        margin-bottom: 20px;
        color: red;
        font-weight: bold;
    }
    .back-button {
        padding: 10px 20px;
        background-color: #4CAF50;
        color: white;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 18px;
        border-radius: 5px;
    }
</style>
</head>
<body>
    <div class="container">
        <div class="error-message">${errorMessage}</div>
        <a href="${pageContext.request.contextPath}/" class="back-button">Go back to Home Page</a>
    </div>
</body>
</html>
