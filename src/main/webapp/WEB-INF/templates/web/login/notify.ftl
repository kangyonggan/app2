<#assign title="注册成功">

<@override name="content">

<div class="space-30"></div>

<h1 class="center skin-color">
    您的邮箱尚未激活， 请去邮箱激活账号！<br/>
    <div class="col-xs-12">
        <div class="space-20"></div>
    </div>

    <div class="col-xs-12">
        <a id="resend" href="${ctx}resend?email=${email}" class="btn skin-btn width-100">重送邮件</a>
    </div>
</h1>

</@override>

<@extends name="../auth-layout.ftl"/>
