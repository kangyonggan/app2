<#assign no_right="">
<#assign title="${user.realname}">

<@override name="style">
<link rel="stylesheet" href="${ctx}static/ace/dist/css/colorbox.min.css"/>
</@override>

<@override name="content">
<h3>基本信息</h3>
<hr>
<div class="row">
    <table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
        <tbody>
        <tr>
            <th width="30%">电子邮箱</th>
            <td width="70%">${user.email}</td>
        </tr>
        <tr>
            <th>真实姓名</th>
            <td>${user.realname}</td>
        </tr>
        <tr>
            <th>手机号</th>
            <td>${user.mobile!''}</td>
        </tr>
        <tr>
            <th>个性签名</th>
            <td><@c.substring str="${user.sign}" len=60 default="懒惰是一种美德"/></td>
        </tr>
        <tr>
            <th>注册时间</th>
            <td>${user.createdTime?datetime}</td>
        </tr>
        </tbody>
    </table>
</div>

    <#if page.list?size gt 0>

    <h3>
        花样相册
        <a class="pull-right skin-color" href="${ctx}user?id=${user.id}&p=${(page.pageNum % page.pages) + 1}"><i
                class="fa fa-refresh bigger-110"></i>&nbsp;换一批</a>
    </h3>
    <hr>
        <#assign attachments=page.list/>
        <#assign pic_size=220/>
        <#include "../../include/pictures.ftl"/>
    </#if>
</@override>

<@override name="script">
<script src="${ctx}static/ace/dist/js/jquery.colorbox.min.js"></script>
<script src="${ctx}static/app/js/web/user/index.js"></script>
</@override>

<@extends name="../../app-layout.ftl"/>
