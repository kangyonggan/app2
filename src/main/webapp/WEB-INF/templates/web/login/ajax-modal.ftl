<#assign modal_title="请先登录"/>

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post" action="${ctx}login">
    <@c.input id="email" label="电子邮箱" empty="请用电子邮箱登录" required="true"/>
    <@c.password id="password" label="密码" empty="请输入密码" required="true"/>
    <div class="form-group clearfix">
        <label class="col-xs-10 col-xs-offset-1 pull-left">验证码<span class="red">*</span></label>
        <div class="col-xs-6 col-xs-offset-1 ">
            <span class="block input-icon input-icon-right">
                <input value="开发阶段先不验" type="text" id="captcha" name="captcha" class="form-control"
                       placeholder="请输入图片中的数字"/>
                <i class="ace-icon fa fa-times-circle hide"></i>
                <label class="error hide" for="captcha"></label>
            </span>
        </div>
        <div class="col-xs-5">
            <img id="captcha-img" onclick="this.src='${ctx}captcha?' + Math.random();"
                 src="${ctx}captcha"/>
        </div>
    </div>
</form>
</@override>

<@override name="modal-footer">
<button class="btn skin-btn" data-toggle="modal-submit" data-loading-text="正在提交...">
登录
</button>
<script src="${ctx}static/app/js/web/login/ajax-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>