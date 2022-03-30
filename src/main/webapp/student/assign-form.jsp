<%--
  Created by IntelliJ IDEA.
  User: alooo
  Date: 3/29/2022
  Time: 8:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body bgcolor="#a52a2a">

<a href="student?action=list">Student Management</a>

<div align="center">
    <table border="1" cellpadding="3">
        <caption><h2>List of Classes</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Start Month</th>
            <th>End Month</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="aClass" items="${classes}">
            <tr>
                <td><c:out value="${aClass.getId()}"/></td>
                <td><c:out value="${aClass.getName()}"/></td>
                <td><c:out value="${aClass.getStartMonth()}"/></td>
                <td><c:out value="${aClass.getEndMonth()}"/></td>
                <td>
                    <a href="student?action=assign&id=<c:out value='${aClass.getId()}' />&studentId=<c:out value='${studentId}' />">Assign</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
