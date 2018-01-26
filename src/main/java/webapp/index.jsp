<%--
  Created by IntelliJ IDEA.
  User: Джамиль
  Date: 19.01.2018
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%if ( session.getAttribute("user") == null) {%>
<form action="/login.jsp" method="post">
    <input type="submit" value="ВОЙТИ">
</form>
<%} else {%>
<form action="/logout" method="post">
    <input type="submit" value="ВЫЙТИ">
</form>
<%}%>
<form action="/reg" method="post">
    <input type="submit" value="Регистрация">
</form>
<form action="/inner/dashboard" method="post">
    <input type="submit" value="Посмотреть свои контракты и заявки" name="contract">
</form>
</body>
</html>
