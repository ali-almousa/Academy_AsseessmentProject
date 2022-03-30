<%--
  Created by IntelliJ IDEA.
  User: alooo
  Date: 3/28/2022
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Course Management Application</title>
</head>
<body bgcolor="#a52a2a">
<center>
    <h1>Course Management</h1>
    <h2>
        <a href="course?action=new">Add New Course</a>
        &nbsp;&nbsp;&nbsp;
        <a href="course?action=list">List All Courses</a>

    </h2>
</center>
<div align="center">
    <c:if test="${course != null}">
    <form action="course?action=update" method="post">
        </c:if>
        <c:if test="${course == null}">
        <form action="course?action=insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${course != null}">
                            Edit Course
                        </c:if>
                        <c:if test="${course == null}">
                            Add New Course
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${course != null}">
                    <input type="hidden" name="id" value="<c:out value='${course.getId()}' />" />
                </c:if>
<%--                <tr>--%>
<%--                    <th>Course Id: </th>--%>
<%--                    <td>--%>
<%--                        <input type="text" name="courseId" size="45"--%>
<%--                               value="<c:out value='${course.getCourseId()}' />"--%>
<%--                        />--%>
<%--                    </td>--%>
<%--                </tr>--%>
                <tr>
                    <th>Course Title: </th>
                    <td>
                        <input type="text" name="title" size="45"
                               value="<c:out value='${course.getTitle()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
