<#assign title="登录">

<@override name="content">

<div class="space-28"></div>

<div class="widget-box">
    <div class="widget-header widget-header-flat">
        <h4 class="widget-title smaller dark">
            <i class="ace-icon fa fa-leaf"></i>
            登录
        </h4>
    </div>

    <div class="widget-body">
        <div class="widget-main clearfix">
            <form id="login-form" method="post" action="${ctx}login">
                <div class="form-group clearfix">
                    <label class="col-xs-12 control-label no-padding-right">电子邮箱</label>
                    <div class="col-xs-12">
                    <span class="block input-icon input-icon-right">
                        <input type="text" id="email" name="email" class="form-control" placeholder="请用电子邮箱登录"/>
                        <i class="ace-icon fa fa-envelope"></i>
                        <label class="error hide" for="email"></label>
                    </span>
                    </div>
                </div>

                <div class="space-8"></div>

                <div class="form-group clearfix">
                    <label class="col-xs-12 control-label no-padding-right">密码</label>
                    <div class="col-xs-12">
                    <span class="block input-icon input-icon-right">
                        <input type="password" id="password" name="password" class="form-control" placeholder="请输入密码"/>
                        <i class="ace-icon fa fa-lock"></i>
                        <label class="error hide" for="password"></label>
                    </span>
                    </div>
                </div>

                <div class="space-8"></div>

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

                <div class="space-8"></div>

                <div class="col-xs-12 clearfix">
                    <button class="btn btn-inverse skin-btn btn-sm width-100">登录</button>
                </div>
            </form>
        </div>
    </div>

    <div class="space-8"></div>

    <div class="widget-header widget-header-flat login-actions">
        <div class="col-xs-6">
            <a href="${ctx}forget">
                <i class="ace-icon fa fa-arrow-left skin-inverse"></i>
                <span class="skin-inverse">忘记密码？</span>
            </a>
        </div>
        <div class="col-xs-6">
            <a href="${ctx}register" class="go-register">
                <i class="ace-icon fa fa-arrow-right skin-inverse"></i>
                <span class="skin-inverse">注册</span>
            </a>
        </div>
    </div>
</div>
</@override>

<@override name="script">
<script src="${ctx}static/app/js/web/login/index.js"></script>
</@override>

<@extends name="../auth-layout.ftl"/>
