<@override name="app-content">
<div class="row">
    <div class="col-lg-4 col-lg-offset-4 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="space-30"></div>
        <@block name="content"/>
    </div>
</div>
</@override>

<@override name="app-script">
    <@block name="script"/>
</@override>
<@extends name="../layout.ftl"/>
