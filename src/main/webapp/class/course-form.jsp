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
    <title>Class Management Application</title>
</head>
<body bgcolor="#a52a2a">
<a href="class?action=list">Class Management</a>
<center>
    <h1>Class Management</h1>
    <h2>
        <a href="class?action=new">Add New Class</a>
        &nbsp;&nbsp;&nbsp;
        <a href="class?action=list">List All Class</a>

    </h2>
</center>
<div align="center">
    <c:if test="${aClass != null}">
    <form action="class?action=update" method="post">
        </c:if>
        <c:if test="${aClass == null}">
        <form action="class?action=insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${aClass != null}">
                            Edit Class
                        </c:if>
                        <c:if test="${aClass == null}">
                            Add New Class
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${aClass != null}">
                    <input type="hidden" name="id" value="<c:out value='${aClass.getId()}' />" />
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
                    <th>Class Name: </th>
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value='${aClass.getName()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Class Start Month: </th>
                    <td>
                        <input type="text" name="startMonth" size="45"
                               value="<c:out value='${aClass.getStartMonth()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Class End Month: </th>
                    <td>
                        <input type="text" name="endMonth" size="45"
                               value="<c:out value='${aClass.getEndMonth()}' />"
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
