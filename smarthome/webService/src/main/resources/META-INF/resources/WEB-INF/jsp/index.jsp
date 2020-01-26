<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" href="/resources/css/style.css">
    <script type="text/javascript" src="/resources/js/app.js"></script>

    <title>SmartHome</title>
</head>

<body>
<h1>SmartHome web application</h1>
<hr>
<div class="form">
    <form action="hello" method="post" onsubmit="return validate()">
        <table>
            <tr>
                <td>Enter your name</td>
                <td><input id="name" name="name"></td>
                <td><input type="submit" value="Submit"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>