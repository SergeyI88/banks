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
TEST
<h3><%= request.getAttribute("error") != null ? request.getAttribute("error") : " "%>
</h3>
<ol>
    <%for (LoanRequest loanRequest : (List<LoanRequest>) request.getAttribute("requests")) {%>
    <li><%=loanRequest.getSum()%> <%=loanRequest.getApproved()%> <%=loanRequest.getDuration()%>
        <%if (loanRequest.getId_manager() != 0) {%>
        Ваш менеджер id:
        <%=loanRequest.getId_manager()%>
        <form action="/inner/accept" method="post"><input type="submit" value="accept"
        /><input type="hidden" value="<%=loanRequest.getId()%>"
                 name="id_request"/>
        </form>
        <%} else {%> Еще обрабатывается!
        <form action="/inner/cancell"><input type="submit" value="cancell"
        /><input type="hidden" value="<%=loanRequest.getId()%>"
                 name="id_request"/></form>
        <%
            }%>
    </li>
    <%
        }
    %>
</ol>
<ol>
    <%for (LoanContract loanContract : (List<LoanContract>) request.getAttribute("contracts")) {%>
    <li><%=loanContract.getSum()%> <%=loanContract.getDate_start()%> <%=loanContract.getDate_end()%>
        <form action="/inner/pay"><input type="hidden" value="<%=loanContract.getId()%>" name="id_contract"/> <input
                type="submit"
                value="pay"/></form>
    </li>
    <%}%>
</ol>
</body>
</html>
