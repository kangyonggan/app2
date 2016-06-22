<#assign modal_title="${item.id???string('编辑角色信息', '添加新角色')}" />

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post"
      action="${ctx}sys/role/<#if item.id??>${item.id}/update<#else>save</#if>">
    <div class="row">
        <@c.input id="code" label="菜单代码" val="${item.code!''}" required="true"/>
        <@c.input id="name" label="菜单名称" val="${item.name!''}" required="true"/>
        <#if item.id??>
            <input type="hidden" value="${item.code}" id="old_code"/>
        </#if>
    </div>
</form>
</@override>

<@override name="modal-footer">
<button class="btn btn-inverse" data-toggle="modal-submit" data-loading-text="正在提交...">
    <i class="ace-icon fa fa-check bigger-110"></i>
${submit}
</button>

<script src="${ctx}static/app/js/sys/role/form-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>