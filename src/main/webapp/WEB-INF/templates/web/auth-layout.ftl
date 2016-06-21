<@override name="app-content">
<div class="row">
    <div class="col-sm-offset-4 col-sm-4 col-sm-offset-4">
        <div class="space-30"></div>
        <@block name="content"/>
    </div>
</div>
</@override>

<@override name="app-script">
    <@block name="script"/>
</@override>
<@extends name="../layout.ftl"/>
