<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 9/7/2025
  Time: 10:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>User Registration Form</title>
</head>
<body>
<h2>User Registration Form</h2>
<form action="user" method="POST">
    First Name: <input type="text" name="firstName"/><br/><br/>
    Last Name: <input type="text" name="lastName"/><br/><br/>
    Your Email: <input type="email" name="email"/><br/><br/>
    Re-enter Email: <input type="email" name="reEmail"/><br/><br/>
    New Password: <input type="password" name="password"/><br/><br/>

    Birthday:
    <select name="month">
        <option value="01">January</option>
        <option value="02">February</option>
        <option value="03">March</option>
        <option value="04">April</option>
        <option value="05">May</option>
        <option value="06">June</option>
        <option value="07">July</option>
        <option value="08">August</option>
        <option value="09">September</option>
        <option value="10">October</option>
        <option value="11">November</option>
        <option value="12">December</option>
    </select>

    <select name="day">
        <% for (int i = 1; i <= 31; i++) { %>
        <option value="<%=i%>"><%=i%>
        </option>
        <% } %>
    </select>
    <select name="year">
        <% for (int y = 1980; y <= 2025; y++) { %>
        <option value="<%=y%>"><%=y%>
        </option>
        <% } %>
    </select>
    <br/><br/>

    Gender:
    <input type="radio" name="gender" value="Female"/> Female
    <input type="radio" name="gender" value="Male"/> Male
    <br/><br/>

    <input type="submit" value="Sign Up"/>
</form>
</body>
</html>

