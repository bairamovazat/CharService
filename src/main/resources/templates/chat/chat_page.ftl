<#ftl encoding='UTF-8'>
<#include "../header.ftl">
<script type="text/javascript" src="/js/date.js"></script>
<div class="container-fluid">
    <div class="row">
        <div id="messages" class="col-sm-12 col-md-10 col-lg-10 col-xl-8 offset-md-1 offset-lg-1 offset-xl-2 p-0">
            <#if model.error??>
                <div class="alert alert-danger" role="alert">${model.error}</div>
            </#if>
            <div class="card collapse show" data-toggle="collapse" data-parent="#messages">
                <div class="card-body">
                    <div class="card-title">
                        <h3>${model.chat.name}</h3>
                        <div class="dropdown show">
                            <a class="btn btn-sm btn-secondary dropdown-toggle" href="#" role="button"
                               id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Участников: ${model.chat.members?size}
                            </a>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                <#list model.chat.members as member>
                                    <div class="dropdown-item">
                                        ${member.login}(${member.name})
                                    </div>
                                </#list>
                                <div class="dropdown-item">
                                    <div class="dropdown-item" data-toggle="modal" data-target="#addMember">
                                        Добавить пользователя
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Add user to chat-->
                    <div class="modal fade" id="addMember" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLabel"
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
                                    <input class="d-none" type="text" name="chatId" value="${model.chat.id}">
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
                    <div class="messages" id="messagesList" style="overflow-y: auto; height:calc(100vh - 280px);">
                    <#--<#list model.chat.messages?sort_by("sendDate")?reverse as message>-->
                    <#--<div class="card-text mb-3" id="message-${message.id}">-->
                    <#--<small class="card-text text-left">-->
                    <#--${message.user.name}-->
                    <#--</small>-->
                    <#--<small class="card-text text-muted float-right">-->
                    <#--${message.sendDate}-->
                    <#--</small>-->
                    <#--<div class="card-text">-->
                    <#--${message.text}-->
                    <#--</div>-->
                    <#--</div>-->
                    <#--</#list>-->
                    </div>
                    <!--/Messages-->
                </div>
            </div>
        </div>
    </div>
    <div class="row p-0 m-0" style="position: fixed; bottom: 0px; left: 0px; width: 100%">
        <div class="col-sm-12 col-md-10 col-lg-10 col-xl-8 offset-md-1 offset-lg-1 offset-xl-2 p-0">
            <form class="" id="messageForm" method="post" action="/chats/send">
                <input class="d-none" type="text" name="chatId" value="${model.chat.id}">
                <div style="display: flex; flex-direction: row">
                    <textarea style="width: 80%; resize: none" class="form-control" name="message" placeholder="Сообщение"></textarea>
                    <div style="width: 20%; text-align: center; align-content: center;" type="button" class="btn btn-primary" data-toggle="modal" data-target="#uploadFile">
                        Файл
                    </div>
                </div>
                <input class="form-control" id="messageSubmitBtn" type="button" value="Отправить">
            </form>
        </div>
    </div>


    <!-- Modal -->
    <div class="modal fade" id="uploadFile" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Добавить файл в чат</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <div class="modal-body">
                    <input type="file" id="uploadFileInput" class="btn btn-default">
                </div>

                <div class="modal-footer">
                    <button type="button" id="fileSubmitBtn" data-dismiss="modal" class="btn btn-primary">Загрузить</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        var chatId = ${model.chat.id};
    </script>

    <script>
        var lastMessageId = -1;
    </script>

    <script type="text/javascript" src="/js/chat_page.js"></script>
</div>
</body>
