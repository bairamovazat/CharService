<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
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
<body class="container-fluid">
<div class="row">
    <div class="card col-sm-12 col-md-12 col-lg-4 col-xl-3 p-0">
        <div class="card-body">
            <h5 class="card-title">${userName} ваши диалоги:</h5>
            <c:forEach items="${messages.keySet()}" var="companion">
                <div class="btn btn-light mb-3" data-toggle="collapse" data-target="#colapse-${companion.getId()}">
                        ${companion.getName()}
                </div>
            </c:forEach>
            <div class="btn btn-light mb-3" data-toggle="collapse" data-target="#addChat">
                Добавить чат
            </div>
        </div>
    </div>
    <div id="messages" class="col-sm-12 col-md-12 col-lg-8 col-xl-9 p-0">

        <c:forEach items="${messages.keySet()}" var="companion">
        <c:if test="${activeMessages != null && activeMessages == companion.getId()}">
        <div id="colapse-${companion.getId()}" class="card collapse show" data-parent="#messages">
        </c:if>
        <c:if test="${activeMessages == null || activeMessages != companion.getId()}">
        <div id="colapse-${companion.getId()}" class="card collapse" data-parent="#messages">
        </c:if>
            <div class="card-body">
                <div class="card-title text-center">
                        ${companion.getName()}
                </div>
                <c:forEach items="${messages.get(companion)}" var="message">
                     <div class="card-text mb-3">
                         <small class="card-text text-left">
                             <c:if test="${message.getUser().getName().equals(userName)}">
                             Вы
                             </c:if>
                             <c:if test="${!message.getUser().getName().equals(userName)}">
                                 ${message.getUser().getName()}
                             </c:if>

                         </small>
                         <small class="card-text text-muted float-right">
                            ${message.getSendDate()}</small>
                         <div class="card-text">
                            ${message.getText()}
                         </div>
                     </div>
                </c:forEach>
                <form method="post" action="/messages">
                    <input class="d-none" type="text" name="user-id" value="${companion.getId()}">
                    <textarea class="form-control" name="text" placeholder="Сообщение"></textarea>
                    <input class="form-control" type="submit" value="Отправить">
                </form>
            </div>
        </div>
        </c:forEach>

        <div id="addChat" class="card-body collapse" data-parent="#messages">
            <div class="card-title text-center">
                Добавить диалог
            </div>
            <form method="post" action="/messages">
                <input class="form-control" type="text" name="user-name" placeholder="Ник пользователя">
                <textarea class="form-control" name="text" placeholder="Сообщение"></textarea>
                <input class="form-control" type="submit" value="Отправить">
            </form>
        </div>
    </div>
</div>
</body>
</html>