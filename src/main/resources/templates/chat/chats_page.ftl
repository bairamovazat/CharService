<#ftl encoding='UTF-8'>
<#include "../header.ftl">
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-md-12 col-lg-2 col-xl-2 p-0 float-right">
            <div class="card" data-toggle="modal" data-target="#addChat">
                <div class="card-body text-center">
                    <p class="card-text">Создайте новый чат</p>
                    <div class="btn btn-primary btn-block">Добавить чат</div>
                </div>
            </div>
        </div>
        <div class="col-sm-12 col-md-12 col-lg-10 col-xl-10 p-0">
            <#list model.chats as chat>
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"><a href="/chat/${chat.id}">${chat.name}</a></h5>
                        <#if chat.messages[0]??>
                        <p class="card-text">${chat.messages[0].user.name}: ${chat.messages[0].text}
                            <small class="text-muted float-right">${chat.messages[0].sendDate}</small>
                        </p>
                        <#else>
                            <p class="card-text">Пока нет сообщений.</p>
                        </#if>
                    </div>
                </div>
            </#list>
        </div>

    </div>
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
</body>