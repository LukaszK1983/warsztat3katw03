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
    <title>Użytkownicy - administracja</title>
</head>
<body>
<%@ include file="/WEB-INF/views/jspf/header.jspf" %>
<h3>Zarządzanie użytkownikami</h3>
<div class="table">
    <table cellpadding="10">
        <tr class="head">
            <td>Nazwa użytkownika</td>
            <td>E-mail</td>
            <td>Akcje</td>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td><a href="editUser?id=${user.id}&name=${user.name}&email=${user.email}"
                       class="action-link">Edycja</a>
                    <a href="deleteUser?id=${user.id}" class="action-link">Usuń</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td class="noneBorder2"></td>
            <td class="noneBorder"></td>
            <td><a href="addUser" class="action-link">Dodaj nowego użytkownika</a></td>
        </tr>
    </table>
</div>
<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
</body>
</html>
