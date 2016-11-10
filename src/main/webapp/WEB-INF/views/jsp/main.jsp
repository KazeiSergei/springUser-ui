<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Maven + Spring MVC</title>
</head>
 
<body>

		<c:forEach var="student" items="${students}">
            <table>
                <tr><c:url value="/getStudentById?id=${student.id}" var="editLink"/>
                    <a href="${editLink}">
                ${student}</a>
                </tr>
            </table>
        </c:forEach>
        <c:url value="/create" var="createUrl"/>
        <input type="button" value="create"  onClick='location.href="${createUrl}"' />
        <h3>${message}</h3>

</body>
</html>