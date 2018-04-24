<#ftl encoding = 'UTF-8'>
<#import "spring.ftl" as spring/>
<body>
<form action="/registration" method="post">
    Name:
    <input type="text" name="name">
    <br>
    Login:
    <input type="text" name="login">
    <br>
    Password:
    <input type="password" name="password">
    <br>
    Phone:
    <input type="text" name="phone">
    <br>
    Email:
    <input type="email" name="email">
    <br>

    <input type="submit">
</form>
</body>