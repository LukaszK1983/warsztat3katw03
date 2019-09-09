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
    <%@ include file="/WEB-INF/views/jspf/head-imports.jspf"%>
    <title>Zadania - administracja</title>
</head>
<body>
<%@ include file="/WEB-INF/views/jspf/header.jspf"%>
<h3>Edycja zadania ${param.title}</h3>
<table cellpadding="10">
    <form action="exerciseList" method="post">
        <tr><td>Nowy tytuł: </td><td><input type="text" name="title" value="${param.title}" /></td></tr>
        <tr><td>Nowa treść: </td><td><input type="text" name="desc" value="${param.desc}" /></td></tr>
        <tr><td></td><td><input type="hidden" name="id" value="${param.id}" /></td></tr>
        <tr><td></td><td><input type="submit" /></td></tr>
    </form>
</table>
<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
</body>
</html>
