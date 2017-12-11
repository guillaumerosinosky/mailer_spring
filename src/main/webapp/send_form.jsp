<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html lang="fr">
<head>
    <title><spring:message code="webapp.title"/></title>
    <script type="text/javascript" src="http://code.jquery.com/jquery.js">

    </script>
    <style>
        .error {color : red}
        .errorGlobal {color:red; alignment: center}
    </style>
</head>
<body>
    <div id="formsContent">

            <c:if test="${status.error}">
                <div id="message" class="error">Form has errors</div>
            </c:if>

        <form:form id="form" method="post" modelAttribute="sendFormBean">
            <h2><spring:message code="mail.send"/></h2>
            <br/>

            <fieldset>
                <form:label path="to">
                    <spring:message code="mail.to"/>
                </form:label>
                <form:input path="to"/>
                <form:errors path="to" cssClass="error"/>
                <br/>

                <form:label path="cc">
                    <spring:message code="mail.cc"/>
                </form:label>
                <form:input path="cc"/>
                <form:errors path="cc" cssClass="error" />
                <br/>

                <form:label path="cci">
                    <spring:message code="mail.cci"/>
                </form:label>
                <form:input path="cci"/>
                <form:errors path="cci" cssClass="error" />
                <br/>
                <form:label path="subject">
                    <spring:message code="mail.subject"/>
                </form:label>
                <form:input path="subject"/>
                <form:errors path="subject" cssClass="error" />
                <br/>

                <form:label path="content">
                    <spring:message code="mail.content"/>
                </form:label>
                <form:errors path="content" cssClass="error" />
                <br/>
                <form:textarea path="content"/>
                <br/>

                <p><button type="submit"><spring:message code="mail.send"/></button></p>
            </fieldset>
        </form:form>
    </div>
    <h2 class="errorGlobal">${error}</h2>
</body>
</html>