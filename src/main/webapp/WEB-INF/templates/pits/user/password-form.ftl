<#assign no_right="">
<#assign title="更新密码">

<@override name="content">
<form action="${ctx}pits/user/password/update" class="form-horizontal" role="form" id="form" method="post">
    <input type="hidden" name="id" value="${item.id}"/>
    <@c.input id="password" label="新密码" empty="请输入新密码..."/>
    <@c.input id="rePassword" label="确认密码" empty="请再次输入密码..."/>

    <div class="clearfix form-actions">
        <div class="center">
            <button class="btn skin-btn" data-toggle="form-submit" data-loading-text="正在提交...">
                <i class="ace-icon fa fa-check bigger-110"></i>
                提交
            </button>

            &nbsp; &nbsp; &nbsp;
            <button class="btn" type="reset">
                <i class="ace-icon fa fa-undo bigger-110"></i>
                重置
            </button>
        </div>
    </div>
</form>
</@override>

<@override name="script">
<script src="${ctx}static/app/js/pits/user/password-form.js"></script>
</@override>

<@extends name="../../app-layout.ftl"/>
