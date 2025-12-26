<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Inventory</title>
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
        margin-top: 30px;
    }
    .form-container {
        background-color: white;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        margin-bottom: 30px;
    }
    form label {
        display: inline-block;
        width: 120px;
        margin-bottom: 10px;
        color: #555;
        font-weight: bold;
    }
    form input[type="text"], 
    form input[type="number"] {
        padding: 8px;
        border: 1px solid #ddd;
        border-radius: 4px;
        width: 250px;
        margin-bottom: 10px;
    }
    form input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        margin-top: 10px;
    }
    form input[type="submit"]:hover {
        background-color: #45a049;
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
    .view-all-link {
        display: inline-block;
        margin-top: 20px;
        color: #4CAF50;
        text-decoration: none;
        font-weight: bold;
    }
    .view-all-link:hover {
        text-decoration: underline;
    }
</style>
</head>
<body>

    <!-- redirect if not logged in -->
    <c:if test="${empty sessionScope.user}">
        <c:redirect url="login.jsp" />
    </c:if>
    
    <!-- nav links -->
    <nav>
        <a href="index.jsp">Home</a>
        <a href="allproducts">All Products</a>
        <a href="logout">Logout</a>
    </nav>
    
    <h2>Add a New Product</h2>
    
    <div class="form-container">
        <!-- form to add product -->
        <form method="post" action="${pageContext.request.contextPath}/addproduct">
            <div>
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required />
            </div>
            <div>
                <label for="brand">Brand:</label>
                <input type="text" id="brand" name="brand" />
            </div>
            <div>
                <label for="type">Type:</label>
                <input type="text" id="type" name="type" />
            </div>
            <div>
                <label for="price">Price:</label>
                <input type="number" id="price" name="price" step="0.01" />
            </div>
            <div>
                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantityOnHand" />
            </div>
            <div>
                <label></label>
                <input type="submit" value="Add Item" />
            </div>
        </form>
    </div>
    
    <h2>My Inventory (<c:out value="${sessionScope.user.name}" />)</h2>
    
    <!-- user's product table -->
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Brand</th>
            <th>Type</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>
        <!-- loop through user's products -->
        <c:forEach var="p" items="${products}">
            <tr>
                <td><c:out value="${p.id}" /></td>
                <td><c:out value="${p.name}" /></td>
                <td><c:out value="${p.brand}" /></td>
                <td><c:out value="${p.type}" /></td>
                <td><c:out value="${p.price}" /></td>
                <td><c:out value="${p.quantityOnHand}" /></td>
            </tr>
        </c:forEach>
    </table>
    
    <a href="allproducts" class="view-all-link">View All Products from All Users</a>

</body>
</html>