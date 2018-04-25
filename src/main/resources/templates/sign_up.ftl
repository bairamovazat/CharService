<#ftl encoding='UTF-8'>
<#include "header.ftl">
<#if error??>
<div class="alert alert-danger" role="alert">${error}</div>
</#if>
<div class="content-block">
    <form class="form-horizontal" action="/signUp" method="post">
        <input name="name" placeholder="Имя">
        <input name="login" placeholder="Логин">
        <input name="password" placeholder="Пароль">
        <input name="email" placeholder="Почта">
        <input name="phone" placeholder="Телефон">
        <input type="submit">
    </form>
</div>
</body>