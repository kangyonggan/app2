<#assign no_right="">
<#assign title="角色管理">

<#assign name = RequestParameters.name!'' />

<@override name="content">
<div class="space-2"></div>

<form class="form-inline" method="get" novalidate>
    <div class="form-group">
        <input type="text" class="form-control" name="name" value="${name}" placeholder="角色名称"/>
    </div>

    <button class="btn btn-purple btn-sm">
        搜索
        <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
    </button>

    <div class="pull-right">
        <a href="#" class="btn btn-purple btn-sm">添加</a>
    </div>
</form>

<div class="space-10"></div>

<table id="role-table" class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th>ID</th>
        <th>角色名称</th>
        <th>角色代码</th>
        <th>创建时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
        <#if (page.list)?size gt 0>
            <#list page.list as item>
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
<@c.pagination url="${ctx}sys/user" param="name=${name}"/>
</@override>

<@extends name="../../app-layout.ftl"/>
