<%--
  Created by IntelliJ IDEA.
  User: myszamisiek
  Date: 31/08/2019
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/views/jspf/head-imports.jspf" %>
    <title>Użytkownicy</title>
</head>
<body>
<%@ include file="/WEB-INF/views/jspf/header.jspf" %>
<h3>Lista użytkowników grupy: ${param.name}</h3>
<table cellpadding="10">
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.name}</td>
            <td><a href="showUser?id=${user.id}" class="action-link">Szczegóły</a></td>
        </tr>
    </c:forEach>
</table>
<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
</body>
</html>
