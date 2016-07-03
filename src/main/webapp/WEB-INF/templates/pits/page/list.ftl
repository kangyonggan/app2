<#assign no_right="">
<#assign title="页面管理">

<@override name="content">
<div class="space-2"></div>

<div class="row">
    <div class="col-xs-12">
        <a href="${ctx}pits/page/create" class="pull-right btn btn-sm skin-btn" data-backdrop="static" data-toggle="modal" data-target="#myModal">添加</a>
    </div>
</div>

<div class="space-10"></div>

<table class="items-table table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th>ID</th>
        <th>名称</th>
        <th>地址</th>
        <th>创建时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
        <#if publicPages??>
            <#list publicPages as item>
                <#include "table-tr.ftl"/>
            </#list>
            <#list pages as item>
                <#include "table-tr.ftl"/>
            </#list>
        <#elseif pages?size gt 0>
            <#list pages as item>
                <#include "table-tr.ftl"/>
            </#list>
        <#else>
        <tr>
            <td colspan="20">
                <div class="empty">暂无查询记录</div>
            </td>
        </tr>
        </#if>
    </tbody>
</table>
</@override>

<@extends name="../../app-layout.ftl"/>
