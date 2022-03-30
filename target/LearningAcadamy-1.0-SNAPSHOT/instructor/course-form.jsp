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
    <title>Instructor Management Application</title>
</head>
<body bgcolor="#a52a2a">
<center>
    <h1>Instructor Management</h1>
    <h2>
        <a href="instructor?action=new">Add New Instructor</a>
        &nbsp;&nbsp;&nbsp;
        <a href="instructor?action=list">List All Instructor</a>

    </h2>
</center>
<div align="center">
    <c:if test="${instructor != null}">
    <form action="instructor?action=update" method="post">
        </c:if>
        <c:if test="${instructor == null}">
        <form action="instructor?action=insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${instructor != null}">
                            Edit Instructor
                        </c:if>
                        <c:if test="${instructor == null}">
                            Add New Instructor
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${instructor != null}">
                    <input type="hidden" name="id" value="<c:out value='${instructor.getId()}' />" />
                </c:if>
                <tr>
                    <th>instructor First Name: </th>
                    <td>
                        <input type="text" name="firstName" size="45"
                               value="<c:out value='${instructor.getFirstName()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>instructor Last Name: </th>
                    <td>
                        <input type="text" name="lastName" size="45"
                               value="<c:out value='${instructor.getLastName()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>instructor Email: </th>
                    <td>
                        <input type="text" name="email" size="15"
                               value="<c:out value='${instructor.getEmail()}' />"
                        />
                    </td>
                </tr>
                <%--                <tr>--%>
                <%--                    <th>Course Id: </th>--%>
                <%--                    <td>--%>
                <%--                        <input type="number" name="course" size="45"--%>
                <%--                               value="<c:out value='${instructor.getCourse().getCourseId()}' />"--%>
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