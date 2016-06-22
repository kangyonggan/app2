<#assign modal_title="角色详情" />

<@override name="modal-body">
<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
    <tbody>
    <tr>
        <th width="30%">角色代码</th>
        <td width="70%">${item.code}</td>
    </tr>
    <tr>
        <th>角色名称</th>
        <td>${item.name}</td>
    </tr>
    <tr>
        <th>是否已删除</th>
        <td>${(item.isDeleted==1)?string('已删除', '未删除')}</td>
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