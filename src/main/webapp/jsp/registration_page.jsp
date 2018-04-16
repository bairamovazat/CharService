<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
</head>
<body>
<form method="post" action="/registration">
    <c:if test="${error!=null}">
        Ошибка: ${error}<br>
    </c:if>
    Имя<br>
    <input type="name" name="name" placeholder="Имя"><br>
    Пароль<br>
    <input type="name" name="password" placeholder="Пароль"><br>

    <input type="submit" value="Зарегистрируй меня!">
</form>
</body>
</html>