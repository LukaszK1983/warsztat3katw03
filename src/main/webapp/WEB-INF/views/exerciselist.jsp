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
    <title>Zadania - administracja</title>
</head>
<body>
<%@ include file="/WEB-INF/views/jspf/header.jspf" %>
<h3>Zarządzanie zadaniami</h3>
<div class="table">
    <table cellpadding="10">
        <tr class="head">
            <td>Tytuł zadania</td>
            <td>Akcje</td>
        </tr>
        <c:forEach var="exercise" items="${exercises}">
            <tr>
                <td>${exercise.title}</td>
                <td><a href="editExercise?id=${exercise.id}&title=${exercise.title}
        &desc=${exercise.description}" class="action-link">Edycja</a> <a href="deleteExercise?id=${exercise.id}" class="action-link">Usuń</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td class="noneBorder"></td>
            <td><a href="addExercise" class="action-link">Dodaj nowe zadanie</a></td>
        </tr>
    </table>
</div>
<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
</body>
</html>
