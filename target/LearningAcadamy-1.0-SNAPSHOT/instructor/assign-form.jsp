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

<a href="instructor?action=list">Instructor Management</a>

<div align="center">
    <caption><h2>List of Classes & Available Courses In Each Class</h2></caption>
        <c:forEach var="aClass" items="${classes}">
    <table border="1" cellpadding="3">

            <tr>
                <td colspan="2"><c:out value="${aClass.getName()}"/></td>
            </tr>
            <c:forEach var="course" items="${aClass.getCourses()}">
                <tr>
                    <td><c:out value="${course.getTitle()}"/></td>
                    <td>
                        <a href="instructor?action=assign&courseId=<c:out value='${course.getId()}' />&classId=<c:out value='${aClass.getId()}' />&instructorId=<c:out value='${instructorId}' />">Assign Instructor</a>
                    </td>
                </tr>
            </c:forEach>

    </table>
        <hr>
        </c:forEach>
</div>
</body>
</html>
