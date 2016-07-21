<#assign title="${category.name}">

<@override name="content">
    <#include "../../include/article-list.ftl">
    <@c.pagination url="${ctx}category/list" param="code=${category.code}"/>
</@override>

<@override name="script">
    <script src="${ctx}static/app/js/web/category/list.js"></script>
</@override>

<@extends name="../../app-layout.ftl"/>
