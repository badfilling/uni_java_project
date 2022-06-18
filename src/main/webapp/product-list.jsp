<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
 <title>Shop Application</title>
</head>
<body>
 <center>
  <h1>Product Management</h1>
        <h2>
         <a href="new">Add New Product</a>
         &nbsp;&nbsp;&nbsp;
         <a href="list">List All Products</a>
         &nbsp;&nbsp;&nbsp;
         <a href="buying">Shopping</a>
         &nbsp;&nbsp;&nbsp;
         <a href="basket">Basket</a>
         &nbsp;&nbsp;&nbsp;
         <a href="purchase-history">Purchase History</a>
        </h2>
 </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Products</h2></caption>
            <tr>
                <th>ID</th>
                <th>title</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="Product" items="${listProduct}">
                <tr>
                    <td><c:out value="${Product.id}" /></td>
                    <td><c:out value="${Product.title}" /></td>
                    <td><c:out value="${Product.price}" /></td>
                    <td>
                     <a href="edit?id=<c:out value='${Product.id}' />">Edit</a>
                     &nbsp;&nbsp;&nbsp;&nbsp;
                     <a href="delete?id=<c:out value='${Product.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div> 
</body>
</html>