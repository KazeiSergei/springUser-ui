<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/resources/core/css/home.css" />" rel="stylesheet">
    <title>Title</title>
</head>
<body>
    <form:form method="POST" commandName="student" action="createStudent" class="box login">
        <fieldset class="boxBody">


            <form:label path="firstName">Name:</form:label>
            <form:input path="firstName" />

            <form:label path="secondName">SecondName:</form:label>
            <form:input path="secondName" />


        </fieldset>
        <footer><input type="submit" class="btnLogin" value="Send" > </footer>


    </form:form>

</body>
</html>
