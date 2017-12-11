<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="fr">
<head>
    <title>
        <spring:message code="webapp.title"/>
    </title>
</head>
<body>
<a href='<spring:url value="/login?logout"/>'>
    <spring:message code="webapp.logout"/>
</a>
<a href='<spring:url value="/sendmail" /> '>
    <spring:message code="mail.send"/>
</a>
<br/>
<table>
    <!-- here should go some titles... -->
    <tr>
        <th><spring:message code="mail.sender"/></th>
        <th><spring:message code="mail.subject"/></th>
        <th><spring:message code="mail.content"/></th>
    </tr>
    <c:forEach items="${mails}" var="mail">
        <tr>
            <td>${mail.from}</td>
            <td>${mail.subject}</td>
            <td>${mail.content}</td>
        </tr>
    </c:forEach>
</table>
<br/>
${message}
</body>

</html>