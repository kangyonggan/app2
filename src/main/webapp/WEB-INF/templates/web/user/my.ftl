<#assign title="${category.name}">

<@override name="content">
    <#include "../../include/article-list.ftl">

    <@c.pagination url="${ctx}user/article/${category.code}"/>
</@override>

<@extends name="../../app-layout.ftl"/>
