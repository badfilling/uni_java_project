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
        <table border="2" cellpadding="25">
            <caption><h2>Purchase history</h2></caption>
            <tr>
                <th>Products</th>
                <th>total price</th>
            </tr>
            <c:forEach var="basketItem" items="${listPurchase}">
                <tr>
                    <!-- <td><c:out value="${basketItem.totalPrice}" /></td> -->
                    <td>
                        <table border="1" cellpadding="5">
                            <c:forEach var="prd" items="${basketItem.items}">
                                <tr>
                                    <th>Product id</th>
                                    <th>Products count</th>
                                </tr>
                                <tr>
                                    <td><c:out value="${prd.productId}" /></td>
                                    <td><c:out value="${prd.count}" /></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                    <td><c:out value="${basketItem.totalPrice}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div> 
</body>
</html>