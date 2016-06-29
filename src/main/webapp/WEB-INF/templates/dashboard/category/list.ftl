<#assign no_right="">
<#assign title="${category.name}">

<#assign startTime = RequestParameters.startTime!'' />
<#assign endTime = RequestParameters.endTime!'' />
<#assign title2 = RequestParameters.title!'' />
<#assign code = RequestParameters.code!'' />

<@override name="style">
<link rel="stylesheet" href="${ctx}static/ace/dist/css/bootstrap-datetimepicker.min.css"/>
</@override>

<@override name="content">
<div class="space-2"></div>

<form class="form-inline items-form" method="get" novalidate>

    <input type="hidden" name="code" value="${code}"/>

    <div class="form-group">
        <input type="datetime" name="startTime" id="startTime" value="${startTime}" class="form-control app-date-input"
               placeholder="开始日期" readonly/>
    </div>

    <div class="form-group">
        <input type="datetime" name="endTime" id="endTime" value="${endTime}" class="form-control app-date-input"
               placeholder="结束日期" readonly/>
    </div>

    <#if category.code!='word'>
        <div class="form-group">
            <input type="text" class="form-control" name="title" value="${title2}" placeholder="标题"/>
        </div>
    </#if>

    <button class="btn btn-sm skin-btn">
        搜索
        <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
    </button>

    <div class="pull-right">
        <a href="${ctx}dashboard/article/create?code=${category.code}" class="btn btn-sm skin-btn">发表</a>
    </div>
</form>

<div class="space-10"></div>

<table class="items-table table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th>ID</th>
        <#if category.code=="word">
            <th>内容</th>
        <#else>
            <th>标题</th>
        </#if>
        <th>顶/踩/收藏</th>
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
    <@c.pagination url="${ctx}dashboard/category/list" param="code=${category.code}&title=${title2}&startTime=${startTime}&endTime=${endTime}"/>
</@override>

<@override name="script">
<script src="${ctx}static/libs/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
<script src="${ctx}static/libs/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctx}static/app/js/dashboard/category/list.js"></script>
</@override>

<@extends name="../../app-layout.ftl"/>
