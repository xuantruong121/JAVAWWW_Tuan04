<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 9/7/2025
  Time: 10:52 AM
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, iuh.fit.se.lab04_bai1_jsp_mvc.model.User_JPA" %>
<html>
<head>
  <title>Danh sách tài khoản</title>
  <style>
    table { border-collapse: collapse; width: 80%; margin: 20px auto; }
    table, th, td { border: 1px solid black; padding: 8px; text-align: center; }
    th { background-color: #90caf9; }
  </style>
</head>
<body>
<h2 style="text-align:center;">Danh sách tài khoản đã đăng ký</h2>
<table>
  <tr>
    <th>First Name</th><th>Last Name</th><th>Email</th><th>Birthday</th><th>Gender</th>
  </tr>
  <%
    List<User_JPA> users = (List<User_JPA>) request.getAttribute("users");
    if (users != null && !users.isEmpty()) {
      for (User_JPA u : users) {
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
  } else {
  %>
  <tr>
    <td colspan="5">Chưa có tài khoản nào được đăng ký</td>
  </tr>
  <% } %>
</table>
</body>
</html>
