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
<body class="container-fluid">
<div class="row">
    <div class="card col-sm-12 col-md-5 col-lg-4 col-xl-3">
        <div class="card-body">
            <h5 class="card-title">Диалоги:</h5>
            <div class="btn btn-light mb-3" data-toggle="collapse" data-target="#1">
                Bairamovazat
            </div>
            <div class="btn btn-light mb-3" data-toggle="collapse" data-target="#addChat">
                Добавить чат
            </div>
        </div>
    </div>
    <div id="messages" class="col-sm-12 col-md-7 col-lg-8 col-xl-9 p-0">
        <div id="1" class="card collapse show" data-toggle="collapse" data-parent="#messages">
            <div class="card-body">
                <div class="card-title text-center">
                    bairamovazat
                </div>
                <!-- Add user to chat-->
                <h1 class="btn btn-primary" data-toggle="modal" data-target="#addMember">
                    Добавить пользователя
                </h1>
                <div class="modal fade" id="addMember" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <form class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Добавление пользователя</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <input class="d-none" type="text" name="chatId" value="chatId">
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

                <div class="card-text mb-3">
                    <small class="card-text text-left">
                        bairamovazat
                    </small>
                    <small class="card-text text-muted float-right">
                        Март вторник 12:12 13.12.2018
                    </small>
                    <div class="card-text">
                        Привет
                    </div>
                </div>

                <form method="post" action="/messages">
                    <input class="d-none" type="text" name="user-id" value="${companion.getId()}">
                    <textarea class="form-control" name="text" placeholder="Сообщение"></textarea>
                    <input class="form-control" type="submit" value="Отправить">
                </form>
            </div>
        </div>
        <div id="addChat" class="card-body collapse" data-toggle="collapse" data-parent="#messages">
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