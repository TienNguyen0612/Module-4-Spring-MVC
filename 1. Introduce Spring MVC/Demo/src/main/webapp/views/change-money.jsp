<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2/17/2022
  Time: 8:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Money</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/change" method="post">
    <h1>Chuyển đổi tiền tệ</h1>
    <label for="usd">
        USD:
    </label>
    <input type="text" name="usd" id="usd"/>
    <br>
    <br>
    <label for="rate">
        Rate:
    </label>
    <input type="text" name="rate" id="rate" value="23000"/>
    <button type="submit">Change</button>
</form>
<h1>Result: ${result} VND</h1>
</body>
</html>
