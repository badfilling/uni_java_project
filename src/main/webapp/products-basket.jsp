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
            <caption><h2>List of Products in basket</h2></caption>
            <tr>
                <th>Title</th>
                <th>Total price</th>
            </tr>
            <c:forEach var="basketItem" items="${basketItems}">
                <tr>
                    <td><c:out value="${basketItem.product.title}" /></td>
                    <td><c:out value="${basketItem.totalPrice}" /></td>
                </tr>
            </c:forEach>
        </table>

        <form action="purchase" method="post">
        <input type="submit" value="Purchase products"/>
        </form>
    </div> 
</body>
</html>