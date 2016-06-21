<#assign no_right="">
<#assign title="用户管理">

<#assign realname = RequestParameters.realname!'' />
<#assign email = RequestParameters.email!'' />

<@override name="content">
<div class="space-2"></div>

<form class="form-inline" method="get" novalidate>
    <div class="form-group">
        <input type="text" class="form-control" name="email" value="${email}" placeholder="邮箱"/>
    </div>

    <div class="form-group">
        <input type="text" class="form-control" name="realname" value="${realname}" placeholder="真实姓名"/>
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

<table id="user-table" class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th>ID</th>
        <th>电子邮箱</th>
        <th>真实姓名</th>
        <th>手机号</th>
        <th>是否锁定</th>
        <th>最近登录时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
        <#if (page.list)?size gt 0>
            <#list page.list as user>
                <#include "user-tr.ftl"/>
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
<@c.pagination url="${ctx}sys/user" param="realname=${realname}&email=${email}"/>
</@override>

<@extends name="../../app-layout.ftl"/>
