<#assign no_right="">
<#assign title="${category.name} - 发表">

<@override name="content">
<form action="${ctx}article/save" class="form-horizontal" role="form" id="form" method="post"
      enctype="multipart/form-data">
    <input type="hidden" name="categoryCode" value="${category.code}"/>
    <input type="hidden" name="categoryName" value="${category.name}"/>

    <#if category.code=="word">
        <#include "word.ftl"/>
    <#else>
        <#include "default.ftl"/>
    </#if>

    <#include "form-actions.ftl"/>
</form>
</@override>

<@override name="script">
<script src="${ctx}static/libs/kindeditor/kindeditor-min.js"></script>
<script src="${ctx}static/libs/kindeditor/lang/zh_CN.js"></script>
    <#if category.code=="word">
    <script src="${ctx}static/app/js/web/article/word.js"></script>
    <#else>
    </#if>
</@override>

<@extends name="../../app-layout.ftl"/>
