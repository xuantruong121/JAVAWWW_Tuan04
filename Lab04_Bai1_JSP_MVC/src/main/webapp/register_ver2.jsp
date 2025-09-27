<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 9/7/2025
  Time: 10:50 AM
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
  <title>User Registration Form</title>
</head>
<body>
<h2 style="text-align:center;">User Registration Form</h2>

<% String error = (String) request.getAttribute("error");
  if (error != null) { %>
<p style="color:red; text-align:center;"><%= error %></p>
<% } %>

<form action="user" method="POST" style="width: 400px; margin: auto;">
  First Name: <input type="text" name="firstName" required/><br/><br/>
  Last Name: <input type="text" name="lastName" required/><br/><br/>
  Your Email: <input type="email" name="email" required/><br/><br/>
  Re-enter Email: <input type="email" name="reEmail" required/><br/><br/>
  New Password: <input type="password" name="password" required/><br/><br/>

  Birthday:<br/>
  <select name="day">
    <% for (int i = 1; i <= 31; i++) { %>
    <option value="<%=i%>"><%=i%></option>
    <% } %>
  </select>
  <select name="month">
    <% String[] months = {"01","02","03","04","05","06","07","08","09","10","11","12"};
      String[] monthNames = {"January","February","March","April","May","June","July",
              "August","September","October","November","December"};
      for (int i=0; i<months.length; i++) { %>
    <option value="<%=months[i]%>"><%=monthNames[i]%></option>
    <% } %>
  </select>
  <select name="year">
    <% for (int y = 1980; y <= 2025; y++) { %>
    <option value="<%=y%>"><%=y%></option>
    <% } %>
  </select>
  <br/><br/>

  Gender:
  <input type="radio" name="gender" value="Female" required/> Female
  <input type="radio" name="gender" value="Male"/> Male
  <br/><br/>

  <input type="submit" value="Sign Up"/>
</form>
</body>
</html>
