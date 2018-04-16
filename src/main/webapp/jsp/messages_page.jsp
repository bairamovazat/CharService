<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-3">
            <h1>Беседы:</h1>
            <c:forEach items="${messages.keySet()}" var="companion">
                <div class="btn btn-light" data-toggle="collapse" data-target="#${companion.getId()}">
                    Пользователь: ${companion.getName()}
                </div>
            </c:forEach>
            <div class="btn btn-light" data-toggle="collapse" data-target="#addChat">
                Добавить чат
            </div>
        </div>
        <div id="messages" class="col-9">
            <h1>Сообщения</h1><br>
            <c:forEach items="${messages.keySet()}" var="companion">

            <c:if test="${activeMessages != null && activeMessages == companion.getId()}">
            <div id="${companion.getId()}" class="collapse show" data-parent="#messages">
            </c:if>
            <c:if test="${activeMessages == null || activeMessages != companion.getId()}">
            <div id="${companion.getId()}" class="collapse" data-parent="#messages">
            </c:if>
                <div>
                    Пользователь: ${companion.getName()}
                </div>
                    <c:forEach items="${messages.get(companion)}" var="messages">
                        <div>
                                ${messages.getSendDate()} : ${messages.getFromUser().getName()}
                            <div>${messages.getText()}</div>
                        </div>
                    </c:forEach>
                    <form method="post" action="/messages">
                        <input class="d-none" type="text" name="user-id" value="${companion.getId()}"><br>
                        <textarea name="text" placeholder="Сообщение"></textarea><br>
                        <input type="submit" value="Отправить">
                    </form>
                </div>
                </c:forEach>
                <div id="addChat" class="collapse" data-parent="#messages">
                    <form method="post" action="/messages">
                        <input  type="text" name="user-name" placeholder="Ник пользователя"><br>
                        <textarea name="text" placeholder="Сообщение"></textarea><br>
                        <input type="submit" value="Отправить">
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>