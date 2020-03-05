<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

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
    </div>
</div>
</body>
</html>