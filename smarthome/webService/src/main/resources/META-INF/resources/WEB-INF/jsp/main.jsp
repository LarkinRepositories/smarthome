<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="resources/css/style.css">
    <script type="text/javascript" src="/resources/js/app.js"></script>
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
                    <a class="logo" aria-label="aria_goToHome">
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
            <div class="navitem" onclick="location.href='/web/scenarios'" value="scenarios" >Сценарии</div>
            <div class="navitem" onclick="location.href='/web/devices'" value="devices" >Устройства</div>
            <div class="navitem" onclick="location.href='/web/profile'" >Профиль</div>
            <div class="navitem" onclick="location.href='/web/exit'" >LOGOUT (${name})</div>
        </nav>

        <form action="hello" method="post" onsubmit="return validate()">
            <%--            <table>--%>
            <%--                <tr>--%>
            <%--                    <td>Какие-то данные</td>--%>
            <%--                    <td><input id="name" name="name"></td>--%>
            <%--                </tr>--%>
            <%--                <tr>--%>
            <%--                    <td>....</td>--%>
            <%--                    <td><input id="password" name="password"></td>--%>
            <%--                </tr>--%>
            <%--                <tr>--%>
            <%--                    <td><input type="submit" value="Дальше"></td>--%>
            <%--                </tr>--%>
            <%--            </table>--%>

            <div class="onoffswitch">
                <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch" checked>
                <label class="onoffswitch-label" for="myonoffswitch">
                    <span class="onoffswitch-inner"></span>
                    <span class="onoffswitch-switch"></span>
                </label>
            </div>


            <table style="height: 345px; width: 174px;" border="1" cellspacing="0" cellpadding="2" bgcolor="#FFFFFF">
                <tbody>
                <tr style="height: 9px;">
                    <td style="width: 168px; height: 9px;">LAST ACTIONS</td>
                </tr>
                <tr style="height: 223px;">
                    <td style="width: 168px; height: 223px;">&nbsp;</td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
</div>
</body>
</html>