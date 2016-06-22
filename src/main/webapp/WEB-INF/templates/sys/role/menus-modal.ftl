<#assign modal_title="设置菜单" />

<link rel="stylesheet" href="${ctx}static/libs/ztree/css/zTreeStyle.css"/>

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post" action="${ctx}sys/role/${id}/menus">
    <input type="hidden" name="menus"/>
    <div class="control-group">
        <div>
            <ul id="tree" class="ztree"></ul>
        </div>
    </div>
</form>
</@override>

<@override name="modal-footer">
<button id="submit" class="btn btn-inverse" data-loading-text="正在提交...">
    <i class="ace-icon fa fa-check bigger-110"></i>
${submit}
</button>

<script>
    var zNodes = [
        <#list all_menus as menu>
            {id:${menu.id}, pId:${menu.pid}, name:"${menu.name}", open:true ${(role_menus?? && role_menus?seq_contains(menu.code))?string(", checked:true", "")}},
        </#list>];
</script>
<script src="${ctx}static/libs/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${ctx}static/libs/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script src="${ctx}static/app/js/sys/role/menus-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>