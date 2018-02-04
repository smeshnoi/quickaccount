<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.02.2018
  Time: 19:15
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
    <c:forEach var="currency" items="${requestScope.currency}">
        <tr>
            <td><c:out value="${currency.currency}"></c:out></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
