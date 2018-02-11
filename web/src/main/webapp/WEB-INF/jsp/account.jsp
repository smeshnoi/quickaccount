<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10.02.2018
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table bgcolor="gray" border="1">
    <tr>
        <td>Enter for search</td>
    </tr>
    <form name="" id="">
        <tr>
            <td>
                <input name="findAccount" type="text" value="${requestScope.text}">
                <%--<input checked type="checkbox">--%>
                <%--<select name="typeAccount">--%>
                    <%--<option value="">All</option>--%>
                    <%--<option value="CREDIT">CREDIT</option>--%>
                    <%--<option value="DEBIT">DEBIT</option>--%>
                <%--</select>--%>

            </td>
        </tr>
        <tr>
            <td>
                Limit of page
                <select name="limitPage">
                    <option value="3">3</option>
                    <option value="5">5</option>
                    <option value="10">10</option>
                </select>
            </td>
            <td>

            </td>
        </tr>
        <tr>
            <td><button type="submit">Search</button></td>
        </tr>
    </form>
    <tr>
        <td>
            <c:forEach var="pages" items="${requestScope.listPages}">
                <a href="/account?findAccount=<%= request.getParameter("findAccount") %>&typeAccount=<%= request.getParameter("typeAccount") %>&limitPage=<%= request.getParameter("limitPage") %>&page=${pages}">
                        ${pages}
                </a>
            </c:forEach>
        </td>
    </tr>
    <c:forEach var="accounts" items="${requestScope.listAccount}">
        <tr>
            <td>
                <a href=""><c:out value="${accounts.accountName}"></c:out></a>
            </td>
        </tr>
    </c:forEach>
    <%--<tr>--%>
        <%--<td>--%>
            <%--<select name="limit">--%>
                <%--<option value="1">1</option>--%>
                <%--<option value="2">2</option>--%>
                <%--<option value="3">3</option>--%>
            <%--</select>--%>
        <%--</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td>--%>
            <%--<a href="/account?limit=${limit}"><input type="button" value="Limit" name=buttonAdd onClick=but1()></a>--%>

        <%--</td>--%>
    <%--</tr>--%>
</table>

</body>
</html>
