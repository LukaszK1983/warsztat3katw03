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
    <title>Home</title>
</head>
<body>
<%@ include file="/WEB-INF/views/jspf/header.jspf" %>
<h3>Ostatnie rozwiązania</h3>
<div class="table">
    <table cellpadding="10">
        <c:forEach var="solution" items="${solutionList}">
            <tr>
                <td>${solution.title}</td>
                <td>${solution.authorName}</td>
                <td>${solution.created}</td>
                <td><a href="showSolution?id=${solution.id}" class="action-link">Szczegóły</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
</body>
</html>
