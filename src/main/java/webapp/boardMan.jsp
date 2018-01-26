<%@ page import="db.pojo.LoanContract" %>
<%@ page import="db.pojo.LoanRequest" %>
<%@ page import="java.util.*" %>
<%--
  Created by IntelliJ IDEA.
  User: Джамиль
  Date: 19.01.2018
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>dashboard</title>
</head>
<body>
<h3><%= request.getAttribute("error") != null ? request.getAttribute("error") : " "%>
</h3>
<ol>
    <%for (LoanRequest loanRequest : (List<LoanRequest>) request.getAttribute("requests")) {%>

    <%if (loanRequest.getApproved() == false) { %>
    <li><%=loanRequest.getSum()%> <%=loanRequest.getDuration()%><br>
        <%=loanRequest.getClient().getUserData().getUserPersonal().last_name%>
        <%=loanRequest.getClient().getUserData().getUserPersonal().first_name%>
        <%=loanRequest.getClient().getUserData().getUserPersonal().birthday%>
        <%=loanRequest.getClient().getUserData().getUserPersonal().proffesion%>
        <%=loanRequest.getClient().getUserData().getUserPersonal().sex%>
        <form action="/inner/approve">
            <input type="hidden" value="<%=loanRequest.getId()%>">
            <input type="submit" value="approve">
        </form>
    </li>
    <%
            }
        }
    %>
</ol>
<ol>
    <%for (LoanContract loanContract : (List<LoanContract>) request.getAttribute("contracts")) {%>
    <li><%=loanContract.getSum()%> <%=loanContract.getDate_start()%> <%=loanContract.getDate_end()%>
    </li>
    <%}%>
</ol>
</body>
</html>
