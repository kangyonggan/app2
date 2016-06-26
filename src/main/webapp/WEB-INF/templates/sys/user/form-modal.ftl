<#assign modal_title="${item.id???string('编辑用户信息', '添加新用户')}" />

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post"
      action="${ctx}sys/user/<#if item.id??>${item.id}/update<#else>save</#if>">
    <div class="row">
        <@c.input id="email" label="电子邮件" val="${item.email!''}" required="true"/>
        <@c.input id="realname" label="真实姓名" val="${item.realname!''}" required="true"/>
        <#if !item.id??>
            <@c.passwod id="password" label="密码" required="true"/>
            <@c.passwod id="rePassword" label="确认密码" required="true"/>
        </#if>
        <@c.input id="mobile" label="手机号" val="${item.mobile!''}"/>
        <@c.input id="sign" label="签名" val="${item.sign!''}"/>

        <input type="hidden" value="${item.email!''}" id="old_email"/>
        <input type="hidden" value="${item.mobile!''}" id="old_mobile"/>
    </div>
</form>
</@override>

<@override name="modal-footer">
<button class="btn btn-inverse skin-btn" data-toggle="modal-submit" data-loading-text="正在提交...">
    <i class="ace-icon fa fa-check bigger-110"></i>
${submit}
</button>

<script src="${ctx}static/app/js/sys/user/form-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>