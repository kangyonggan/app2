<#assign modal_title="${item.id???string('编辑页面信息', '添加新页面')}" />

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post"
      action="${ctx}pits/page/${item.id???string('update', 'save')}">
    <div class="row">
        <#if item.id??>
            <input type="hidden" name="id" value="${item.id}"/>
        </#if>
        <@c.input id="name" label="页面名称" val="${item.name!''}" required="true"/>
        <@c.input id="url" label="页面路径" val="${item.url!''}" required="true"/>
        <@c.select id="type" label="类型" items=[{"name":"nav", "disp":"常用导航"}] key="name" val="disp" selected="${item.type!''}"/>
        <@c.input id="sort" label="排序" val="${item.sort!'1'}" required="true"/>
        <@c.input id="icon" label="页面图标" val="${item.icon!'ace-icon fa fa-bookmark-o'}" required="true"/>
        <@shiro.hasRole name="ROLE_ADMIN">
            <@c.select id="userId" label="公/私" items=[{"name":"-1", "disp":"私有"}, {"name":"0", "disp":"公共"}] key="name" val="disp" selected="${item.userId!'0'}"/>
        </@shiro.hasRole>
    </div>
</form>
</@override>

<@override name="modal-footer">
<button class="btn skin-btn" data-toggle="modal-submit" data-loading-text="正在提交...">
    <i class="ace-icon fa fa-check bigger-110"></i>
${submit}
</button>

<script src="${ctx}static/app/js/pits/page/form-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>