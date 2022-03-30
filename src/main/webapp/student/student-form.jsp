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
    <title>User Management Application</title>
</head>
<body bgcolor="#a52a2a">
<center>
    <h1>Student Management</h1>
    <h2>
        <a href="student?action=new">Add New Student</a>
        &nbsp;&nbsp;&nbsp;
        <a href="student?action=list">List All Students</a>

    </h2>
</center>
<div align="center">
    <c:if test="${student != null}">
    <form action="student?action=update" method="post">
        </c:if>
        <c:if test="${student == null}">
        <form action="student?action=insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${student != null}">
                            Edit Student
                        </c:if>
                        <c:if test="${student == null}">
                            Add New Student
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${student != null}">
                    <input type="hidden" name="id" value="<c:out value='${student.getId()}' />" />
                </c:if>
                <tr>
                    <th>Student First Name: </th>
                    <td>
                        <input type="text" name="firstName" size="45"
                               value="<c:out value='${student.getFirstName()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Student Last Name: </th>
                    <td>
                        <input type="text" name="lastName" size="45"
                               value="<c:out value='${student.getLastName()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Student Email: </th>
                    <td>
                        <input type="text" name="email" size="15"
                               value="<c:out value='${student.getEmail()}' />"
                        />
                    </td>
                </tr>
<%--                <tr>--%>
<%--                    <th>Course Id: </th>--%>
<%--                    <td>--%>
<%--                        <input type="number" name="course" size="45"--%>
<%--                               value="<c:out value='${student.getCourse().getCourseId()}' />"--%>
<%--                        />--%>
<%--                    </td>--%>
<%--                </tr>--%>
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
