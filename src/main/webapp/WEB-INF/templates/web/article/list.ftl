<#assign key = RequestParameters.key!'' />

<#assign title="${key}">

<@override name="content">
    <#include "../../include/article-list.ftl">
    <@c.pagination url="${ctx}article/search" param="key=${key}"/>
</@override>

<@override name="script">
    <script src="${ctx}static/app/js/web/category/list.js"></script>
</@override>

<@extends name="../../app-layout.ftl"/>
