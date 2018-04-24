<#ftl encoding='UTF-8'>

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
            <h5 class="card-title">${model.userName} ваши диалоги:</h5>
            <#list model.chats as chat>
                <div class="btn btn-light mb-3" data-toggle="collapse" data-target="#colapse-${chat.id}">
                    ${chat.name}
                </div>
            </#list>
            <div class="btn btn-light mb-3" data-toggle="modal" data-target="#addChat">
                Добавить чат
            </div>
        </div>
    </div>
    <div id="messages" class="col-sm-12 col-md-12 col-lg-8 col-xl-9 p-0">

        <#list model.chats as chat>
            <div id="colapse-${chat.id}" class="card collapse" data-parent="#messages">
                <div class="card-body">
                    <div class="card-title">
                        <h3>${chat.name}</h3>
                        <div class="dropdown show">
                            <a class="btn btn-sm btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Настройки
                            </a>

                            <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                <div class="btn" data-toggle="modal" data-target="#addMember-${chat.id}">
                                    Добавить пользователя
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Add user to chat-->

                    <div class="modal fade" id="addMember-${chat.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <form class="modal-content" method="post" action="/chats/add/member">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Добавление пользователя</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <input class="d-none" type="text" name="chatId" value="${chat.id}">
                                    <input class="form-control" name="userName" placeholder="Логин пользователя">
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary">Добавить</button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!--/Add user to chat-->

                    <div class="card-title text-center">
                        ${chat.name}
                    </div>
                    <#list chat.messages?sort_by("sendDate")?reverse as message>
                        <div class="card-text mb-3">
                            <small class="card-text text-left">
                                ${message.user.name}
                            </small>
                            <small class="card-text text-muted float-right">
                                ${message.sendDate}</small>
                            <div class="card-text">
                                ${message.text}
                            </div>
                        </div>
                    </#list>
                    <form method="post" action="/chats/send">
                        <input class="d-none" type="text" name="chatId" value="${chat.id}">
                        <textarea class="form-control" name="message" placeholder="Сообщение"></textarea>
                        <input class="form-control" type="submit" value="Отправить">
                    </form>

                </div>
            </div>
        </#list>

        <div class="modal fade" id="addChat" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <form class="modal-content" method="post" action="/chats/add">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Новый диалог</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <input class="form-control" type="text" name="name" placeholder="Название чата">
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Добавить</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>