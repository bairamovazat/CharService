<#ftl encoding='UTF-8'>
<#include "../header.ftl">
<div class="container-fluid">
    <div class="row">
        <div id="messages" class="col-sm-12 col-md-10 col-lg-10 col-xl-8 offset-md-1 offset-lg-1 offset-xl-2 p-0" >
            <div class="card collapse show" data-toggle="collapse" data-parent="#messages">
                <div class="card-body">
                    <div class="card-title">
                        <h3>${model.chat.name}</h3>
                        <div class="dropdown show">
                            <a class="btn btn-sm btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Настройки
                            </a>

                            <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                <div class="btn" data-toggle="modal" data-target="#addMember">
                                    Добавить пользователя
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Add user to chat-->
                    <div class="modal fade" id="addMember" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
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
                                    <input class="d-none" type="text" name="chatId" value=""${model.chat.id}">
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
                    <!--Messages-->
                    <div class="messages" style="overflow-y: auto; height:calc(100vh - 280px);">
                       <#list model.chat.messages?sort_by("sendDate")?reverse as message>
                        <div class="card-text mb-3" id="message-${message.id}">
                            <small class="card-text text-left">
                                ${message.user.name}
                            </small>
                            <small class="card-text text-muted float-right">
                                ${message.sendDate}
                            </small>
                            <div class="card-text">
                                ${message.text}
                            </div>
                        </div>
                       </#list>
                    </div>
                    <!--/Messages-->
                </div>
            </div>

        </div>
    </div>
    <div class="row p-0 m-0" style="position: fixed; bottom: 0px; left: 0px; width: 100%">
        <div class="col-sm-12 col-md-10 col-lg-10 col-xl-8 offset-md-1 offset-lg-1 offset-xl-2 p-0" >
            <form class="" method="post" action="/chats/send">
                <input class="d-none" type="text" name="chatId" value="${model.chat.id}">
                <textarea class="form-control" name="message" placeholder="Сообщение"></textarea>
                <input class="form-control" type="submit" value="Отправить">
            </form>
        </div>
    </div>

</div>
</body>