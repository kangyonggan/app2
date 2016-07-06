<#assign title="发送成功">

<@override name="content">

<div class="space-30"></div>

<h1 class="center skin-color">
    发送成功!!!<br/> 😊请去邮箱激活账号...
</h1>

<div class="space-20"></div>

<h4 class="text-success center">
    正在跳转到登录页...
    <a id="jump-btn" class="center" href="${ctx}login">
        点此直接进入
    </a>
</h4>
</@override>

<@override name="script">
<script>
    setTimeout(function () {
        window.location.href = $("#jump-btn").attr('href');
    }, 5000);
</script>
</@override>
<@extends name="../auth-layout.ftl"/>
