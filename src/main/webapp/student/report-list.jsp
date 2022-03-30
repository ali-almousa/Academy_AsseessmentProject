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
<a href="/">Home Page</a>


<div align="center">
    <caption><h2>Full Report</h2></caption>
        <c:forEach var="aClass" items="${classes}">
    <table border="1" cellpadding="3">

            <tr bgcolor="#708090">
                <td colspan="3" align="center" style="color: wheat"> <b><c:out value="${aClass.getName()}"/></b></td>
            </tr>

            <tr bgcolor="#a9a9a9">
                <td colspan="3" align="center" style="color: ghostwhite">Instructors</td>
            </tr>
            <c:forEach var="instructor" items="${aClass.getInstructors()}">
                <tr>
                    <td><c:out value="${instructor.getFirstName()}"/></td>
                    <td><c:out value="${instructor.getLastName()}"/></td>
                    <td><c:out value="${instructor.getEmail()}"/></td>
                </tr>

<%--                <c:forEach var="course" items="${instructor.getCourses()}">--%>
<%--                    <tr>--%>
<%--                        <td><c:out value="${course.getTitle()}"/></td>--%>
<%--                        <td>--%>
<%--                            <a href="instructor?action=assign&courseId=<c:out value='${course.getId()}' />&classId=<c:out value='${aClass.getId()}' />&instructorId=<c:out value='${instructorId}' />">Assign Instructor</a>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>

            </c:forEach>


            <tr bgcolor="#a9a9a9">
                <td colspan="3" align="center" style="color: ghostwhite">Courses</td>
            </tr>
            <c:forEach var="course" items="${aClass.getCourses()}">
                <tr>
                    <td colspan="3" align="center"><c:out value="${course.getTitle()}"/></td>
                </tr>
            </c:forEach>

            <tr bgcolor="#a9a9a9">
                <td colspan="3" align="center" style="color: ghostwhite">Students</td>
            </tr>
            <c:forEach var="student" items="${aClass.getStudents()}">
                <tr>
                    <td><c:out value="${student.getFirstName()}"/></td>
                    <td><c:out value="${student.getLastName()}"/></td>
                    <td><c:out value="${student.getEmail()}"/></td>
                </tr>
            </c:forEach>
        <hr>
    </table>
        </c:forEach>
</div>
</body>
</html>
