<%--
  Created by IntelliJ IDEA.
  User: zinkevich
  Date: 20.01.2018
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Message text</title>
</head>
<body>
    <table>
        <tr>
            <td height="50" aria-setsize="20">
                ${requestScope.message}
            </td>
        </tr>
    </table>
</body>
</html>
