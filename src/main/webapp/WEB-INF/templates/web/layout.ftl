<@override name="page_style">
    <@block name="style"/>
</@override>

<@override name="page_body">
<div class="main-container" id="main-container">

    <@block name="page_main"/>

    <#include "../footer.ftl">

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div>
</@override>

<@override name="page_script">
    <@block name="script"/>
</@override>

<@extends name="../layout.ftl"/>
