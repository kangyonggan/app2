<#assign modal_title="${item.id???string('编辑菜单信息', '添加新菜单')}" />

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post"
      action="${ctx}sys/menu/${item.id???string('update', 'save')}">
    <input type="hidden" name="pid" value="${parent_item.id!0}">
    <div class="row">
        <#if parent_item.id??>
            <@c.input id="parent_name" label="父菜单名称" val="${parent_item.name}" readonly="true"/>
        </#if>

        <@c.input id="name" label="菜单名称" val="${item.name!''}" required="true"/>

        <@c.input id="code" label="菜单代码" val="${item.code!''}" required="true"/>

        <@c.input id="url" label="路径" val="${item.url!''}"/>

        <@c.input id="icon" label="图标" val="${item.icon!''}"/>

        <@c.input id="sort" label="排序" val="${item.sort!'1'}" required="true"/>

        <input type="hidden" id="old_code" value="${item.code!''}">

    </div>
</form>
</@override>

<@override name="modal-footer">
<button class="btn skin-btn" data-toggle="modal-submit" data-loading-text="正在提交...">
    <i class="ace-icon fa fa-check bigger-110"></i>
${submit}

<script src="${ctx}static/app/js/sys/menu/form-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>