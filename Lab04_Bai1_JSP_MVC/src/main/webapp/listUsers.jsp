<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 9/7/2025
  Time: 10:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, iuh.fit.se.lab04_bai1_jsp_mvc.model.User" %>
<html>
<head>
    <title>Danh sách tài khoản</title>
    <style>
        table { border-collapse: collapse; }
        table, th, td { border: 1px solid black; padding: 5px; }
        th { background-color: #90caf9; }
    </style>
</head>
<body>
<h2>Danh sách tài khoản đã đăng ký</h2>
<table>
    <tr>
        <th>First Name</th><th>Last Name</th><th>Email</th><th>Birthday</th><th>Gender</th>
    </tr>
    <%
        List<User> users = (List<User>) request.getAttribute("users");
        if (users != null) {
            for (User u : users) {
    %>
    <tr>
        <td><%= u.getFirstName() %></td>
        <td><%= u.getLastName() %></td>
        <td><%= u.getEmail() %></td>
        <td><%= u.getDateOfBirth() %></td>
        <td><%= u.getGender() %></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>

