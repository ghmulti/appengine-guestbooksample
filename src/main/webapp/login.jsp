<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="page-header">
    <h1>Login</h1>
</div>
<sec:authorize access="isAnonymous()">
    <div class="panel panel-default">
        <div class="panel-body">
            <h2>Login form</h2>
            <c:if test="${param.error eq 'bad_credentials'}">
                <div class="alert alert-danger alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    Login error
                </div>
            </c:if>
            <form action="${pageContext.request.contextPath}/login/authenticate" method="POST" role="form">
                <div class="row">
                    <div id="form-group-email" class="form-group col-lg-4">
                        <label class="control-label" for="user-email">Email:</label>
                        <input id="user-email" name="username" type="text" class="form-control"/>
                    </div>
                </div>

                <div class="row">
                    <div id="form-group-password" class="form-group col-lg-4">
                        <label class="control-label" for="user-password">Password:</label>
                        <input id="user-password" name="password" type="password" class="form-control"/>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-4">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </div>
                </div>
            </form>
            <div class="row">
                <div class="form-group col-lg-4">
                    <a href="${pageContext.request.contextPath}/user/register">Register</a>
                </div>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-body">
            <h2>Social</h2>
            <div class="row social-button-row">
                <div class="col-lg-4">
                    <a href="${pageContext.request.contextPath}/auth/facebook"><button class="btn btn-facebook"><i class="icon-facebook"></i> | Facebook login</button></a>
                </div>
            </div>
            <div class="row social-button-row">
                <div class="col-lg-4">
                    <a href="${pageContext.request.contextPath}/auth/twitter"><button class="btn btn-twitter"><i class="icon-twitter"></i> | Twitter login</button></a>
                </div>
            </div>
        </div>
    </div>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <p>You are authenticated</p>
</sec:authorize>

</body>
</html>