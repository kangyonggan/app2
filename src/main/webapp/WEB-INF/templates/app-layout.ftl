<@override name="app-style">
    <@block name="style"/>
</@override>

<@override name="app-content">
    <@shiro.user>
        <#include "include/settings.ftl">
    </@shiro.user>

    <#include "include/header.ftl">

<div class="space-6"></div>

<div class="row">
    <#if no_right??>
        <div class="col-xs-12 col-sm-3">
            <#include "include/sidebar-left.ftl">
        </div>
        <div class="col-xs-12 col-sm-9">
            <@block name="content"/>
        </div>
    <#else>
        <div class="col-xs-6 col-sm-3">
            <#include "include/sidebar-left.ftl">
        </div>
        <#include "include/sidebar-right.ftl">

        <div class="col-xs-12 col-sm-6">
            <@block name="content"/>
        </div>
    </#if>
</div>
</@override>

<@override name="app-script">
    <script src="${ctx}static/app/js/item-status.js"></script>
    <@block name="script"/>
</@override>

<@extends name="layout.ftl"/>
