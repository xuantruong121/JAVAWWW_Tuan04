<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="cart" scope="session" class="iuh.fit.se.lab04_bai2_jsp_session.beans.CartBean" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><title>Shopping Cart</title></head>
<body>

<p><a href="DSSP">Product List</a></p>
<table width="100%" border="1">
    <tr bgcolor="#CCCCCC">
        <td>Model Description</td>
        <td>Quantity</td>
        <td>Unit Price</td>
        <td>Total</td>
    </tr>

    <c:if test="${cart.lineItemCount == 0}">
        <tr>
            <td colspan="3">Cart is currently empty <br /></td>
        </tr>
    </c:if>

    <c:forEach var="cartItem" items="${cart.cartItems}" varStatus="counter">
        <form name="item" method="POST" action="CartController">
            <tr>
                <td>
                    <c:out value="${cartItem.partNumber}" /><br />
                    <c:out value="${cartItem.modelDescription}" />
                </td>

                <td>
                    <input type="hidden" name="itemIndex" value="${counter.count}" />
                    <input type="text" name="quantity" value="${cartItem.quantity}" size="2" />
                    <input type="submit" name="action" value="update" />
                    <input type="submit" name="action" value="delete" />
                </td>

                <td>$<c:out value="${cartItem.unitCost}" /></td>
                <td>$<c:out value="${cartItem.totalCost}" /></td>
            </tr>
        </form>
    </c:forEach>

    <tr>
        <td colspan="2"></td>
        <td>Subtotal: $<c:out value="${cart.orderTotal}" /></td>
    </tr>
</table>

</body>
</html>
