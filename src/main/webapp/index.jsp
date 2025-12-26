<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOOZE Inventory System</title>
<style>
    body {
        font-family: Arial, sans-serif;
        max-width: 800px;
        margin: 0 auto;
        padding: 20px;
        background-color: #f5f5f5;
    }
    .container {
        background-color: white;
        padding: 40px;
        border-radius: 8px;
        box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        text-align: center;
    }
    h1 {
        color: #333;
        margin-bottom: 30px;
        border-bottom: 3px solid #4CAF50;
        padding-bottom: 15px;
    }
    .links {
        margin-top: 30px;
    }
    .links a {
        display: inline-block;
        background-color: #4CAF50;
        color: white;
        text-decoration: none;
        padding: 12px 24px;
        margin: 10px;
        border-radius: 5px;
        transition: background-color 0.3s;
        font-weight: bold;
    }
    .links a:hover {
        background-color: #45a049;
    }
</style>
</head>
<body>
    <!-- main landing page -->
    <div class="container">
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <!-- User is logged in -->
                <h1>Welcome back, <c:out value="${sessionScope.user.name}" />!</h1>
                
                <div class="links">
                    <a href="inventory">My Inventory</a>
                    <a href="allproducts">View All Products</a>
                    <a href="logout">Log Out</a>
                </div>
            </c:when>
            <c:otherwise>
                <!-- User is not logged in -->
                <h1>Welcome to the BOOZE Inventory System</h1>
                
                <div class="links">
                    <a href="login.jsp">Log in</a>
                    <a href="register.jsp">Register</a>
                    <a href="allproducts">View All Products</a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

</body>
</html>