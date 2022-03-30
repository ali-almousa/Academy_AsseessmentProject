
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
    <title>Instructor Management Application</title>
</head>
<body bgcolor="#a52a2a">
<a href="/">Home Page</a>
<center>
    <h1>Instructor Management</h1>
    <h2>
        <a href="instructor?action=new">Add New Instructor</a>
        <a href="instructor?action=list">List All Instructor</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Instructors</h2></caption>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="instructor" items="${instructors}">
            <tr>
                <td><c:out value="${instructor.getId()}"/></td>
                <td><c:out value="${instructor.getFirstName()}"/></td>
                <td><c:out value="${instructor.getLastName()}"/></td>
                <td><c:out value="${instructor.getEmail()}"/></td>
                <td>
                    <a href="instructor?action=edit&id=<c:out value='${instructor.getId()}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="instructor?action=delete&id=<c:out value='${instructor.getId()}' />">Delete</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="instructor?action=assignForm&id=<c:out value='${instructor.getId()}' />">Assign To Class</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

