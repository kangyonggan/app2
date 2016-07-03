<#assign modal_title="页面详情" />

<@override name="modal-body">
<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
    <tbody>
    <tr>
        <th width="30%">页面ID</th>
        <td width="70%">${item.id}</td>
    </tr>
    <tr>
        <th>页面名称</th>
        <td>${item.name}</td>
    </tr>
    <tr>
        <th>页面地址</th>
        <td title="${item.url}"><@c.substring str="${item.url}" len=30/></td>
    </tr>
    <tr>
        <th>类型</th>
        <td>
            <#if item.type=="nav">
                常用导航
            <#else>
                ${item.type}
            </#if>
        </td>
    </tr>
    <tr>
        <th>公/私</th>
        <td>
            <#if item.userId==0>
                公共导航
            <#else>
                私有导航
            </#if>
        </td>
    </tr>
    <tr>
        <th>图标</th>
        <td>${item.icon}</td>
    </tr>
    <tr>
        <th>排序</th>
        <td>${item.sort}</td>
    </tr>
    <tr>
        <th>是否已删除</th>
        <td><@c.textIcon flag=item.isDeleted==0 trueText="未删除" falseText="已删除"/></td>
    </tr>
    <tr>
        <th>创建时间</th>
        <td>${item.createdTime?datetime}</td>
    </tr>
    <tr>
        <th>最后更新时间</th>
        <td>${item.updatedTime?datetime}</td>
    </tr>
    </tbody>
</table>
</@override>

<@override name="modal-footer">
<button class="btn btn-sm" data-dismiss="modal">
    <i class="ace-icon fa fa-remove bigger-110"></i>
${cancel}
</button>
</@override>

<@extends name="../../modal-layout.ftl"/>