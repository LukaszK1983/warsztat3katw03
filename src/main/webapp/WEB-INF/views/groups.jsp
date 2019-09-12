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
    <title>Grupy</title>
</head>
<body>
<%@ include file="/WEB-INF/views/jspf/header.jspf" %>
<h3>Lista grup</h3>
<div class="table">
    <table cellpadding="10">
        <c:forEach var="group" items="${groups}">
            <tr>
                <td>${group.name}</td>
                <td><a href="showUsers?id=${group.groupId}" class="action-link">Użytkownicy</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
</body>
</html>
