<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>SmartHome</title>
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
            <div class="navitem" onclick="location.href='/web/devices'" value="devices">Устройства</div>
            <div class="navitem active" onclick="location.href='/web/profile'">Профиль</div>
            <div class="navitem" onclick="location.href='/web/exit'">LOGOUT (${name})</div>
        </nav>

        <form method="post" action="${pageContext.request.contextPath}/editprofile" autocomplete="off">
            <input type="hidden" name="id" value="<c:out value='${editingUser.id}' />" />
            <div class="form-group">
                <label for="name">Имя</label>
                <input name="name" type="text" class="form-control" id="name" value="<c:out value='${editingUser.name}' />"/>
            </div>
            <div class="form-group">
                <label for="address">Адрес</label>
                <input name="address" type="text" class="form-control" id="address" value="<c:out value='${editingUser.address}' />"/>
            </div>
            <div class="form-group">
                <label for="phone">Телефон</label>
                <input name="phone" type="text" class="form-control" id="phone" value="<c:out value='${editingUser.phone}' />"/>
            </div>
            <div class="form-group">
                <label for="telegram">Телеграмм</label>
                <input name="telegram" type="text" class="form-control" id="telegram" value="<c:out value='${editingUser.telegram}' />" />
            </div>
            <button type="submit" class="btn btn-primary">Сохранить</button>
        </form>
    </div>
</div>
</body>
