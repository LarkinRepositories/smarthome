<%@ page import="ru.innopolis.webService.device_dto.DeviceDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="resources/css/style.css">
    <script type="text/javascript" src="/resources/js/app.js"></script>
    <title>SmartHome</title>
</head>
<body>

<jsp:include page="top1.jsp"></jsp:include>

<h1>device page</h1>
<table class="table" border="1">
    <thead>
    <tr>
        <th>Название устройства</th>
        <th>Ip:port</th>
        <th>Статус</th>
        <th>Состояние</th>
        <th>UserId</th>
        <th>Type</th>
        <th>Do</th>
<%--        <th>Действия(просмотр, ред., удаление)</th>--%>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th><c:out value="${DevName}" /></th>
        <th><c:out value="${DevIp}" />:<c:out value="${DevPort}" /></th>
        <th><c:out value="${DevStatus}" /></th>
        <th><c:out value="${DevState}" /></th>
        <th><c:out value="${DevUser}" /></th>
        <th><c:out value="${DevType}" />
        </th>
        <th><form name="frm" method="post" action="http://localhost:8762/api/device/devices/on/?id=${DevId}">
            <input type="hidden" name="hdnbt" />
            <input type="button" name="bt" value="on" onclick="{document.frm.hdnbt.value=this.value;document.frm.submit();}" />
        </form> / <form name="frm1" method="post" action="http://localhost:8762/api/device/devices/off/?id=${DevId}">
            <input type="hidden" name="hdnbt" />
            <input type="button" name="bt1" value="off" onclick="{document.frm1.hdnbt.value=this.value;document.frm1.submit();}" />
        </form> </th>
    </tr>
    </tbody>
</table>
<%--    <c:forEach var="device" items="${devices}">--%>
<%--        <tr>--%>
<%--            <td scope="row">${device.id}</td>--%>
<%--            <td>${device.name}</td>--%>
<%--            <td>${device.brand}</td>--%>
<%--            <td>${device.place}</td>--%>
<%--            <td>${device.state}</td>--%>
<%--            <td>--%>
<%--                <a href="${pageContext.request.contextPath}/showdevice?id=${device.id}">Link</a>                            &nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--                <a href="${pageContext.request.contextPath}/editdevice?id=${device.id}">Edit</a>                            &nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--                <a href="${pageContext.request.contextPath}/deletedevice?id=${device.id}">Delete</a>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>

<%--<jsp:include page="_footer.jsp"></jsp:include>--%>

</body>
</html>