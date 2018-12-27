<#include "header.ftl">

<div class="card" style="width: 18rem;">
    <div class="card-header">
        <h3>Информация о пользователе ${model.user.name}</h3>
    </div>
    <ul class="list-group list-group-flush">
        <li class="list-group-item">Участвует в ${model.chatCount} чатах</li>
        <li class="list-group-item">Отправил ${model.messageCount} сообщений</li>
        <li class="list-group-item">id: ${model.user.id}</li>
        <li class="list-group-item">login: ${model.user.login}</li>
        <li class="list-group-item">
            hashPassword(<a href="#" data-toggle="modal" data-target="#changePassword">Изменить</a>):${model.user.hashPassword}
        </li>
        <li class="list-group-item">role: ${model.user.role}
            <#if model.user.role == "ADMIN">
                <a href="set-role?role=USER&userId=${model.user.id}">Изменить на User</a>
            <#else>
                <a href="set-role?role=ADMIN&userId=${model.user.id}">Изменить на Admin</a>
            </#if>
        </li>
        <li class="list-group-item">state: ${model.user.state}</li>
        <li class="list-group-item">email: ${model.user.email}</li>
    </ul>
</div>

<#--<div>-->
    <#--<#if model.user.role == "ADMIN">-->
        <#--<a href="set-role?role=USER&userId=${model.user.id}"> Сделать пользователем</a>-->
    <#--<#else>-->
        <#--<a href="set-role?role=ADMIN&userId=${model.user.id}"> Сделать администратором</a>-->
    <#--</#if>-->
<#--</div>-->


    <!-- Modal -->
    <div class="modal fade" id="changePassword" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Добавить файл в чат</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form method="post" action="change-password">
                    <div class="modal-body">
                        <label>Изменить пароль</label>
                        <input name="userId" hidden value="${model.user.id}">
                        <input name="password" value="">
                    </div>

                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">
                            Изменить
                        </button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
                    </div>
                </form>
            </div>
        </div>
    </div>


</body>