<#include "header.ftl">

<h2>Статистика</h2>
Всего сообщений отправлено: ${model.messageCount}
Всего создано чатов: ${model.chatCount}

<h2>Все пользователи</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Логин</th>
        <th>Роль</th>
        <th>Информация</th>
    </tr>
<#list model.users as user>
    <tr>
        <td>${user.id}</td>
        <td>${user.login}</td>
        <td>${user.role}</td>
        <td><a href="user-info?userId=${user.id}">Просмотреть</a></td>
    </tr>
</#list>
</table>


</body>