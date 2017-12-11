<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>
        <spring:message code="webapp.title"/>
    </title>
    <style>
        .error {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #a94442;
            background-color: #f2dede;
            border-color: #ebccd1;
        }

        .msg {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #31708f;
            background-color: #d9edf7;
            border-color: #bce8f1;
        }

        #login-box {
            width: 300px;
            padding: 20px;
            margin: 100px auto;
            background: #fff;
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border: 1px solid #000;
        }
    </style>
</head>
<body onload='document.loginForm.username.focus();'>

<h1><spring:message code="webapp.title"/></h1>

<div id="login-box">

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
    </c:if>

    <form name='loginForm'
          action="<c:url value='/login' />" method='POST'>

        <table>
            <tr>
                <td><spring:message code="webapp.username"/></td>
                <td><input type='text' name='username' value=''></td>
            </tr>
            <tr>
                <td><spring:message code="webapp.password"/></td>
                <td><input type='password' name='password' /></td>
            </tr>
            <tr>
                <td colspan='2'>
                    <input name="submit" type="submit" value='<spring:message code="webapp.login"/>'/>
                </td>
            </tr>
        </table>

        <input type="hidden"
               name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
</div>

</body>
</html>