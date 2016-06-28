<#assign title="${category.name}">

<@override name="content">
    <#include "../../include/article-list.ftl">
    <@c.pagination url="${ctx}category/list" param="code=${category.code}"/>
</@override>

<@extends name="../../app-layout.ftl"/>
