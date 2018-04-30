<#include "header.ftl">

<table>
    <tr>
        <th>ID</th>
        <th>Логин</th>
        <th>Операции</th>
    </tr>
<#list model.users as user>
    <tr>
        <td>${user.id}</td>
        <td>${user.login}</td>
    </tr>
</#list>
</table>
</body>