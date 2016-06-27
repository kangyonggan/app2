<#assign title="菜单管理">

<@override name="style">
<link rel="stylesheet" href="${ctx}static/libs/ztree/css/zTreeStyle.css"/>
</@override>

<@override name="content">
<div class="space-2"></div>

<div class="widget-box skin-border">
    <div class="widget-header skin-bg">
        <h4 class="widget-title lighter smaller skin-inverse">菜单管理</h4>
    </div>

    <div class="widget-body">
        <div class="widget-main padding-8">
            <div id="menu_tree" class="ztree"></div>
        </div>
    </div>
</div>

</@override>

<@override name="script">
<script>
    var zNodes = [
        <#list menus as menu>
            {id:${menu.id}, pId:${menu.pid}, name: "${menu.name}", open: true},
        </#list>];
</script>
<script src="${ctx}static/libs/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${ctx}static/libs/ztree/js/jquery.ztree.exedit-3.5.min.js"></script>
<script src="${ctx}static/app/js/sys/menu/list.js"></script>
</@override>

<@extends name="../../app-layout.ftl"/>
