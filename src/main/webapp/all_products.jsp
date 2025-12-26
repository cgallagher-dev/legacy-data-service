<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Products</title>
<style>
    body {
        font-family: Arial, sans-serif;
        max-width: 1200px;
        margin: 0 auto;
        padding: 20px;
        background-color: #f5f5f5;
    }
    nav {
        background-color: #333;
        padding: 10px;
        border-radius: 5px;
        margin-bottom: 20px;
    }
    nav a {
        color: white;
        text-decoration: none;
        padding: 8px 15px;
        margin: 0 5px;
        border-radius: 3px;
        transition: background-color 0.3s;
    }
    nav a:hover {
        background-color: #555;
    }
    h2 {
        color: #333;
        border-bottom: 2px solid #4CAF50;
        padding-bottom: 10px;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        background-color: white;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        border-radius: 5px;
        overflow: hidden;
    }
    th {
        background-color: #4CAF50;
        color: white;
        padding: 12px;
        text-align: left;
    }
    td {
        padding: 10px;
        border-bottom: 1px solid #ddd;
    }
    tr:hover {
        background-color: #f9f9f9;
    }
</style>
</head>
<body>

    <!-- nav links -->
    <nav>
        <a href="index.jsp">Home</a>
        <a href="inventory">My Inventory</a>
    </nav>
    
    <h2>All Products (Public List)</h2>
    
    <!-- product table -->
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Brand</th>
            <th>Type</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Owner User ID</th>
        </tr>
        <!-- loop through all products -->
        <c:forEach var="prod" items="${allProducts}">
            <tr>
                <td><c:out value="${prod.id}" /></td>
                <td><c:out value="${prod.name}" /></td>
                <td><c:out value="${prod.brand}" /></td>
                <td><c:out value="${prod.type}" /></td>
                <td><c:out value="${prod.price}" /></td>
                <td><c:out value="${prod.quantityOnHand}" /></td>
                <td><c:out value="${prod.userId}" /></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>