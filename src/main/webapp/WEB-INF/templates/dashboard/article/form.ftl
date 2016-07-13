<#assign no_right="">
<#assign title="${category.name} - 发表">

<@override name="style">
    <#if category.code=="picture">
    <link rel="stylesheet" href="${ctx}static/ace/dist/css/dropzone.min.css"/>
    <#elseif category.code=="note" || category.code=="share" || category.code=="course">
    <link rel="stylesheet" href="${ctx}static/ace/dist/css/bootstrap-markdown.min.css"/>
    </#if>
</@override>

<@override name="content">
    <#if category.code=="picture">
        <#include "picture.ftl"/>
    <#else>
    <form action="${ctx}dashboard/article/${article.id???string('update', 'save')}" class="form-horizontal" role="form"
          id="form" method="post"
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
        <#elseif category.code=="music">
            <#include "music.ftl"/>
        <#elseif category.code=="video">
            <#include "video.ftl"/>
        <#elseif category.code=="note" || category.code=="share" || category.code=="course">
            <#include "markdown.ftl"/>
        <#else>
            <#include "default.ftl"/>
        </#if>

        <#include "form-actions.ftl"/>
    </form>
    </#if>
</@override>

<@override name="script">
<script src="${ctx}static/libs/kindeditor/kindeditor-min.js"></script>
<script src="${ctx}static/libs/kindeditor/lang/zh_CN.js"></script>
<script src="${ctx}static/app/js/dashboard/article/form.js"></script>
    <#if category.code=="word">
    <script src="${ctx}static/app/js/dashboard/article/word.js"></script>
    <#elseif category.code=="download">
    <script src="${ctx}static/app/js/dashboard/article/download.js"></script>
    <#elseif category.code=="music">
    <script src="${ctx}static/app/js/dashboard/article/music.js"></script>
    <#elseif category.code=="video">
    <script src="${ctx}static/app/js/dashboard/article/video.js"></script>
    <#elseif category.code=="note" || category.code=="share" || category.code=="course">
    <script src="${ctx}static/ace/dist/js/markdown/marked.min.js"></script>
    <script src="${ctx}static/ace/dist/js/markdown/bootstrap-markdown.min.js"></script>
    <script src="${ctx}static/app/js/dashboard/article/markdown.js"></script>
    <#elseif category.code=="picture">
    <script src="${ctx}static/ace/dist/js/dropzone.min.js"></script>
    <script src="${ctx}static/app/js/dashboard/article/picture.js"></script>
    <#else>
    <script src="${ctx}static/app/js/dashboard/article/default.js"></script>
    </#if>
</@override>

<@extends name="../../app-layout.ftl"/>
