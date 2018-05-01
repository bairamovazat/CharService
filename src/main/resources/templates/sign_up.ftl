<#ftl encoding='UTF-8'>
<#include "header.ftl">

    <div class="col-6 offset-1">
        <#if error??>
            <div class="alert alert-danger" role="alert">${error}</div>
        </#if>
        <form action="/signUp" method="post">
            <div class="form-group">
                <label for="login">Имя</label>
                <input type="text" name="name" class="form-control" id="login" aria-describedby="login-label"
                       placeholder="Введите имя" required="required">
                <small id="login-label" class="form-text text-muted">Введите имя</small>
            </div>
            <div class="form-group">
                <label for="login">Логин</label>
                <input type="text" name="login" class="form-control" id="login" aria-describedby="login-label"
                       placeholder="Введите логин" required="required">
                <small id="login-label" class="form-text text-muted">Введите свой логин от сервиса</small>
            </div>
            <div class="form-group">
                <label for="password">Пароль</label>
                <input type="password" name="password" class="form-control" id="password"
                       aria-describedby="password-label"
                       placeholder="Введите пароль" required="required">
                <small id="password-label" class="form-text text-muted">Введите свой пароль от сервиса</small>
            </div>
            <div class="form-group">
                <label for="login">Email</label>
                <input type="email" name="email" class="form-control" id="login" aria-describedby="login-label"
                       placeholder="Введите email" required="required">
                <small id="login-label" class="form-text text-muted">Введите свой Email от сервиса</small>
            </div>
            <button type="submit" class="btn btn-primary">Отправить</button>
        </form>
    </div>

<#include "footer.ftl">
</body>