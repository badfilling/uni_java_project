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
            <form action="buying" method="post">
                Select product:&nbsp;
            <select name="product">
                <c:forEach items="${listProduct}" var="product">
                    <c:choose>
                        <c:when test="${selectedProduct != null && product.id == selectedProduct.id}">
                            <option value="${product.id}" selected="selected">${selectedProduct.title}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${product.id}">${product.title}</option>
                        </c:otherwise>
                    </c:choose>
                    <!-- <c:if test="${product == null}">
                        <option value="${product.id}" selected>${product.title}</option>
                    </c:if>
                    <option value="${product.id}">${product.title}</option> -->
                </c:forEach>
            </select>
            <br/><br/>
            <input type="submit" value="Submit" />
            </form>
        </table>
    </div> 
</body>
</html>