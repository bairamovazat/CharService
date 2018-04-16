<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
</head>
<body>
<c:if test="${userName!=null}">
    Hi ${userName} !<br>
    <a href="/messages">Сообщения</a><br>
    <a href="/logout">Выйти</a><br>
</c:if>

<c:if test="${userName==null}">
    <a href="/auth">Авторизация</a><br>
    <a href="/registration">Регистрация</a><br>
</c:if>

</body>
</html>