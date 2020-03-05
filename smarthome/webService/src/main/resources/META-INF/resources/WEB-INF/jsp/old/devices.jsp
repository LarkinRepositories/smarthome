<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<html>--%>
<%--<head>--%>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
<%--    <link rel="stylesheet" href="resources/css/style.css">--%>
<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">--%>
<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>--%>
<%--    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
<%--    <title>SmartHome</title>--%>
<%--</head>--%>

<%--<body>--%>
<%--<div class="form">--%>
<%--    <header class="header">--%>
<%--        <div class="header-bottom">--%>
<%--            <div class="header-bottom-content">--%>
<%--                <div class="header-left">--%>
<%--                    &lt;%&ndash;                    left-menu-element &ndash;%&gt;--%>
<%--                </div>--%>
<%--                <div class="header-center"> SMART--%>
<%--                    <a class="logo" aria-label="aria_goToHome" href="/web/auth-form">--%>
<%--                        <img class="icon" src="resources/img/house.png"/>--%>
<%--                    </a>--%>
<%--                    HOME--%>
<%--                </div>--%>
<%--                <div class="header-right">--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </header>--%>
<%--    <div class='main'>--%>
<%--        <nav class="container">--%>
<%--            <div class="navitem" onclick="location.href='/web/scenarios'" value="scenarios">Сценарии</div>--%>
<%--            <div class="navitem active" onclick="location.href='/web/devices'" value="devices">Устройства</div>--%>
<%--            <div class="navitem" onclick="location.href='/web/profile'">Профиль</div>--%>
<%--            <div class="navitem" onclick="location.href='/web/exit'">LOGOUT (${name})</div>--%>
<%--        </nav>--%>
        <div style="width: 100%; text-align: right">
            <a href="/web/devices/add">Добавить устройство</a>
        </div>
        <form action="devices" method="post">
            <table class="table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Название устройства</th>
                    <th>Марка</th>
                    <th>Место установки</th>
                    <th>Состояние</th>
                    <th>Действия(просмотр, ред., удаление)</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="device" items="${devices}">
                    <tr>
                        <td scope="row">${device.id}</td>
                        <td>${device.name}</td>
                        <td>${device.brand}</td>
                        <td>${device.place}</td>
                        <td>${device.state}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/showdevice?id=${device.id}">Link</a>                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/editdevice?id=${device.id}">Edit</a>                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/deletedevice?id=${device.id}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
<%--</div>--%>
<%--</body>--%>
