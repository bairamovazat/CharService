<#ftl encoding='UTF-8'>
<#include "header.ftl">

<#if model.error.isPresent()>
<div class="alert alert-danger" role="alert">Логин или пароль введены неверно</div>
</#if>
    <form class="form-horizontal" action="/login" method="post">
        <input name="login" placeholder="Логин">
        <input name="password" placeholder="Пароль">
        <label for="remember-me">
            <input type="checkbox" id="remember-me" name="remember-me">Запомнить меня</label> <input type="submit">
    </form>
</body>