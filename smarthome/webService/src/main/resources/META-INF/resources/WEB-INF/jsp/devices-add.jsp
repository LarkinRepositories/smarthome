<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <base href="http://localhost:8762/web/">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>SmartHome</title>
    <%@ page isELIgnored="false" %>
</head>

<body>
<div class="form">
    <header class="header">
        <div class="header-bottom">
            <div class="header-bottom-content">
                <div class="header-left">
                    <%--                    left-menu-element --%>
                </div>
                <div class="header-center"> SMART
                    <a class="logo" aria-label="aria_goToHome" href="/web/auth-form">
                        <img class="icon" src="resources/img/house.png"/>
                    </a>
                    HOME
                </div>
                <div class="header-right">
                </div>
            </div>
        </div>
    </header>
    <div class='main'>
        <nav class="container">
            <div class="navitem" onclick="location.href='/web/scenarios'" value="scenarios">Сценарии</div>
            <div class="navitem active" onclick="location.href='/web/devices'" value="devices">Устройства</div>
            <div class="navitem" onclick="location.href='/web/profile'">Профиль</div>
            <div class="navitem" onclick="location.href='/web/exit'">LOGOUT (${name})</div>
        </nav>
        <div style="width: 100%; text-align: right">
            <a href="/web/devices">Обратно к списку устройств</a>
        </div>
        <c:if test="${success!=''}">
            <div class="alert alert-success" role="alert">${success}</div>
        </c:if>
        <form action="devices/add" method="post" class="form-horizontal">
            <fieldset>

                <!-- Form Name -->
                <legend>Добавить устройство</legend>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="alias">Alias</label>
                    <div class="col-md-4">
                        <input id="alias" name="alias" type="text" placeholder="Название устройства на латиннице"
                               class="form-control input-md" required="">

                    </div>
                </div>

                <!-- Button (Double) -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="button1id"></label>
                    <div class="col-md-8">
                        <button type="submit" id="button1id" name="button1id" class="btn btn-success">Добавить</button>
                        <button id="button2id" name="button2id" class="btn btn-danger">Вернуться к списку</button>
                    </div>
                </div>

            </fieldset>
        </form>

    </div>
</div>
</body>
