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
    <title>Course Management Application</title>
</head>
<body bgcolor="#a52a2a">
    <a href="/">Home Page</a>
<center>
    <h1>Course Management</h1>
    <h2>
        <a href="course?action=new">Add New Course</a>
        &nbsp;&nbsp;&nbsp;
        <a href="course?action=list">List All Course</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="3">
        <caption><h2>List of Courses</h2></caption>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="course" items="${courses}">
            <tr>
                <td><c:out value="${course.getId()}"/></td>
                <td><c:out value="${course.getTitle()}"/></td>
                <td>
                    <a href="course?action=edit&id=<c:out value='${course.getId()}' />">Edit</a>
                    |
                    <a href="course?action=delete&id=<c:out value='${course.getId()}' />">Delete</a>
                    |
                    <a href="course?action=assignForm&id=<c:out value='${course.getId()}' />">Assign To Class</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
