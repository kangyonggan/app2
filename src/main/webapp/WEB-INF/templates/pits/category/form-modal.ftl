<#assign modal_title="${item.id???string('编辑栏目信息', '添加新栏目')}" />

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post"
      action="${ctx}pits/category/${item.id???string('update', 'save')}">
    <input type="hidden" name="pid" value="${parent_item.id!0}">
    <div class="row">
        <#if parent_item.id??>
            <@c.input id="parent_name" label="父栏目名称" val="${parent_item.name}" readonly="true"/>
        </#if>

        <@c.input id="name" label="栏目名称" val="${item.name!''}" required="true"/>

        <@c.input id="code" label="栏目代码" val="${item.code!''}" required="true"/>

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

    <script src="${ctx}static/app/js/pits/category/form-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>