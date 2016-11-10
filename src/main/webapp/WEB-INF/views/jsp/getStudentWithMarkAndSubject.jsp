<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:if test="${students.size() == 0}">
        <h1>У студента нет оценок</h1>
    </c:if>
        <c:if test="${students.size()!=0}">
            <form method="post" action="deleteMark/${id}">
                <table>
                    <c:forEach var="student" items="${students}">

                            <tr>
                                <td>${student}</td>
                                <td><input align="right" name="markId" type="checkbox" value="${student.markId}">
                            </tr>


                    </c:forEach>
                    <tr><td><input type="submit" value="Delete selected marks"></td></tr>
                </table>
            </form>
        </c:if>
    <form method="post" action="insertMark/${id}">
        <table border="1" cellspacing="0"><coption> Insert mark</coption>
            <tr>
                <td>Subject</td>
                <td> <select name="subject">
                    <c:forEach var="subject" items="${subjects}">
                        <option value="${subject.id}">${subject.name}</option>
                    </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Mark</td>
                <td>
                    <select name="mark">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="right" colspan="2"><input type="submit" value="Insert"/></td>
            </tr>
        </table>
    </form>
</body>
</html>
