<%--
  Created by IntelliJ IDEA.
  User: alooo
  Date: 3/28/2022
  Time: 11:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Class Management Application</title>
</head>
<body bgcolor="#a52a2a">
    <a href="/">Home Page</a>
<center>
    <h1>Class Management</h1>
    <h2>
        <a href="class?action=new">Add New Class</a>
        &nbsp;&nbsp;&nbsp;
        <a href="class?action=list">List All Class</a>

    </h2>
</center>
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
                    <a href="class?action=edit&id=<c:out value='${aClass.getId()}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="class?action=delete&id=<c:out value='${aClass.getId()}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
