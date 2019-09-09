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
    <title>Szczegóły użytkownika</title>
</head>
<body>
<%@ include file="/WEB-INF/views/jspf/header.jspf" %>
<h3>Szczegóły użytkownika: ${param.name}</h3>
<p>Nazwa: <b>${param.name}</b></p>
<p>E-mail: ${param.email}</p>
<br>
<p><b>Dodane rozwiązania zadań:</b></p>
<table cellpadding="10">
    <tr>
        <td>Tytuł zadania</td>
        <td>Data dodania</td>
    </tr>
    <c:forEach var="solution" items="${solutions}">
        <tr>
            <td>${solution.title}</td>
            <td>${solution.created}</td>
            <td><a href="showSolution?id=${solution.id}" class="action-link">Szczegóły</a></td>
        </tr>
    </c:forEach>
</table>
<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
</body>
</html>
