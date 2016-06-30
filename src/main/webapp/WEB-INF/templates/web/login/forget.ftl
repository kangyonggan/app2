<#assign title="找回密码">

<@override name="content">

<div class="space-30"></div>

<div class="position-relative">
    <div id="forgot-box" class="forgot-box widget-box fa-border skin-border">
        <div class="widget-body">
            <div class="widget-main clearfix">
                <h4 class="header lighter bigger skin-color">
                    <i class="ace-icon fa fa-key skin-color"></i>
                    找回密码
                </h4>

                <div class="space-14"></div>

                <form id="reset-form" method="post" action="${ctx}reset">
                    <div class="form-group clearfix">
                        <label class="col-xs-12 control-label no-padding-right">输入电子邮箱找回密码</label>
                        <div class="col-xs-12">
                            <span class="block input-icon input-icon-right">
                                <input type="text" id="email" name="email" class="form-control" placeholder="请输入电子邮箱"/>
                                <i class="ace-icon fa fa-envelope"></i>
                                <label class="error hide" for="email"></label>
                            </span>
                        </div>
                    </div>

                    <div class="space-14"></div>

                    <div class="form-group clearfix">
                        <label class="col-xs-12 control-label no-padding-right">验证码</label>
                        <div class="col-xs-7">
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

                    <div class="space-14"></div>

                    <div class="col-xs-4 col-xs-offset-8">
                        <button class="btn btn-sm width-100 skin-btn" data-loading-text="正在提交...">发送</button>
                    </div>
                </form>
            </div>

            <div class="toolbar center skin-bg skin-border">
                <a href="${ctx}login" data-target="#login-box" class="back-to-login-link skin-a">
                    去登录
                    <i class="ace-icon fa fa-arrow-right"></i>
                </a>
            </div>
        </div>
    </div>
</div>
</@override>

<@override name="script">
<script src="${ctx}static/app/js/web/login/forget.js"></script>
</@override>

<@extends name="../auth-layout.ftl"/>
