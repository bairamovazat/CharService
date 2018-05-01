<#ftl encoding='UTF-8'>
<#include "header.ftl">

<div class="row">
    <div class="col-6 offset-1">
         <#if model.error.isPresent()>
        <div class="alert alert-danger" role="alert">Логин или пароль введены неверно</div>
         </#if>
        <form action="/login" method="post">
            <div class="form-group">
                <label for="login">Логин</label>
                <input type="text" name="login" class="form-control" id="login" aria-describedby="login-label"
                       placeholder="Введите логин">
                <small id="login-label" class="form-text text-muted">Введите свой логин от сервиса</small>
            </div>

            <div class="form-group">
                <label for="password">Пароль</label>
                <input type="password" name="password" class="form-control" id="password"
                       aria-describedby="password-label"
                       placeholder="Введите пароль">
                <small id="password-label" class="form-text text-muted">Введите свой пароль от сервиса</small>
            </div>

            <div class="form-check">
                <label class="form-check-label">
                    <input type="checkbox" class="form-check-input">
                    Запомнить меня
                </label>
            </div>
            <button type="submit" class="btn btn-primary">Отправить</button>
        </form>
    </div>
</div>

<#include "footer.ftl">
