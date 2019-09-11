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
    <title>Grupy - administracja</title>
</head>
<body>
<%@ include file="/WEB-INF/views/jspf/header.jspf" %>
<h3>Zarządzanie grupami użytkowników</h3>
<div class="table">
    <table cellpadding="10">
        <tr class="head">
            <td>Nazwa grupy</td>
            <td>Akcje</td>
        </tr>
        <c:forEach var="group" items="${groups}">
            <tr>
                <td>${group.name}</td>
                <td><a href="editGroup?id=${group.groupId}&name=${group.name}" class="action-link">Edycja</a>
                    <a href="deleteGroup?id=${group.groupId}" class="action-link">Usuń</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td class="noneBorder"></td>
            <td><a href="addGroup" class="action-link">Dodaj nową grupę</a></td>
        </tr>
    </table>
</div>
<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
</body>
</html>
