<%--
  Created by IntelliJ IDEA.
  User: Джамиль
  Date: 19.01.2018
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
    <form name="f" action="/login" method="post">
       <input type="text" value="vik" name="username"/>
       <input type="text" value="pass" name="userpass"/>
        <input type="submit" value="submit"/>
    </form>
    <p>
        <%=request.getParameter("error")!=null ? request.getParameter("error"):"" %>
    </p>
</body>
</html>
