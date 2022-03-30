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
    <title>Student Management Application</title>
</head>
<body bgcolor="#a52a2a">
        <a href="/">Home Page</a>
<center>
    <h1>Student Management</h1>
    <h2>
        <a href="student?action=new">Add New Student</a>
        <a href="student?action=list">List All Student</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Students</h2></caption>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="student" items="${students}">
            <tr>
                <td><c:out value="${student.getId()}"/></td>
                <td><c:out value="${student.getFirstName()}"/></td>
                <td><c:out value="${student.getLastName()}"/></td>
                <td><c:out value="${student.getEmail()}"/></td>
                <td>
                    <a href="student?action=edit&id=<c:out value='${student.getId()}' />">Edit</a>
                    |
                    <a href="student?action=delete&id=<c:out value='${student.getId()}' />">Delete</a>
                    |
                    <a href="student?action=assignForm&id=<c:out value='${student.getId()}' />">Assign To Class</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
