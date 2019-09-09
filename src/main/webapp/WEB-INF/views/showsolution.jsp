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
    <title>Solution</title>
</head>
<body>
<%@ include file="/WEB-INF/views/jspf/header.jspf"%>
<h3>Szczegóły rozwiązania zadania:</h3>
<table cellpadding="10">
        <tr><td><c:out value="${solution.description}"></c:out></td></tr>
</table>
<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
</body>
</html>
