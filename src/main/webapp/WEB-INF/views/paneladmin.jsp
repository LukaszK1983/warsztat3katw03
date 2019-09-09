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
    <title>Panel Administratora</title>
</head>
<body>
<%@ include file="/WEB-INF/views/jspf/header.jspf"%>
<h3>Panel Administratora</h3>
<p><a href="exerciseList" class="action-link">Lista zadań</a></p>
<p><a href="groupList" class="action-link">Lista grup użytkowników</a></p>
<p><a href="userList" class="action-link">Lista użytkowników</a></p>
<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
</body>
</html>
