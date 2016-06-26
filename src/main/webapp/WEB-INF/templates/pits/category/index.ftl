<#assign title="栏目管理">

<@override name="style">
<link rel="stylesheet" href="${ctx}static/libs/ztree/css/zTreeStyle.css"/>
</@override>

<@override name="content">
<div class="space-2"></div>

<div class="widget-box widget-color-dark">
    <div class="widget-header">
        <h4 class="widget-title lighter smaller">栏目管理</h4>
    </div>

    <div class="widget-body">
        <div class="widget-main padding-8">
            <div id="category_tree" class="ztree"></div>
        </div>
    </div>
</div>

</@override>

<@override name="script">
<script>
    var zNodes = [
        <#list categories as category>
            {id:${category.id}, pId:${category.pid}, name: "${category.name}", open: true},
        </#list>];
</script>
<script src="${ctx}static/libs/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${ctx}static/libs/ztree/js/jquery.ztree.exedit-3.5.min.js"></script>
<script src="${ctx}static/app/js/pits/category/index.js"></script>
</@override>

<@extends name="../../app-layout.ftl"/>
