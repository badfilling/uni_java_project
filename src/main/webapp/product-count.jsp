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
        <div>
            <p>Product title: <c:out value="${selectedProduct.title}" /></p>
            <p>Product price: <c:out value="${selectedProduct.price}" /></p>
        </div>
        <table border="1" cellpadding="5">
            <form action="buying-count" method="post">
                Provide products count:&nbsp;
            <input type="text" name="count" size="45"
                   value="<c:out value='${productsCount}' />"
            />
            <br/><br/>
            <input type="submit" value="Submit" />
            </form>
        </table>
    </div> 
</body>
</html>