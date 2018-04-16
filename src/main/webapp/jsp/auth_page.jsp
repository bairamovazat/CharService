<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
</head>
<body>

<form method="post" action="/auth">
    Имя<br>
    <input type="name" name="name" placeholder="Имя"><br>
    Пароль<br>
    <%
        String error = request.getParameter("error");
        if (error != null) {
            %><%=error%><%
        }
    %>
    <input type="name" name="password" placeholder="Пароль"><br>
    <input type="submit" value="Авторизоваться">
</form>
</body>
</html>