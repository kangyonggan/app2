<#assign no_right="">
<#assign title="${category.name} - 发表">

<@override name="content">
<form action="${ctx}dashboard/article/${article.id???string('update', 'save')}" class="form-horizontal" role="form" id="form" method="post"
      enctype="multipart/form-data">
    <#if article.id??>
        <input type="hidden" name="id" value="${article.id}"/>
    </#if>
    <input type="hidden" name="categoryCode" value="${category.code}"/>
    <input type="hidden" name="categoryName" value="${category.name}"/>

    <#if category.code=="word">
        <#include "word.ftl"/>
    <#elseif category.code=="download">
        <#include "download.ftl"/>
    <#else>
        <#include "default.ftl"/>
    </#if>

    <#include "form-actions.ftl"/>
</form>
</@override>

<@override name="script">
<script src="${ctx}static/libs/kindeditor/kindeditor-min.js"></script>
<script src="${ctx}static/libs/kindeditor/lang/zh_CN.js"></script>
<script src="${ctx}static/app/js/dashboard/article/form.js"></script>
    <#if category.code=="word">
    <script src="${ctx}static/app/js/dashboard/article/word.js"></script>
    <#elseif category.code=="download">
    <script src="${ctx}static/app/js/dashboard/article/download.js"></script>
    <#else>
    <script src="${ctx}static/app/js/dashboard/article/default.js"></script>
    </#if>
</@override>

<@extends name="../../app-layout.ftl"/>
