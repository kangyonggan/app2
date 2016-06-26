<#assign title="注册">

<@override name="content">

<div class="space-4"></div>

<div class="widget-box skin-border">
    <div class="widget-header widget-header-flat skin-bg">
        <h4 class="widget-title smaller skin-inverse">
            <i class="ace-icon fa fa-users skin-inverse"></i>
            注册
        </h4>
    </div>

    <div class="widget-body">
        <div class="widget-main clearfix">
            <form id="register-form" method="post" action="${ctx}register">
                <div class="form-group clearfix">
                    <label class="col-xs-12 control-label no-padding-right">电子邮箱<span class="red">*</span></label>
                    <div class="col-xs-12">
                    <span class="block input-icon input-icon-right">
                        <input type="text" id="email" name="email" class="form-control" placeholder="请输入电子邮箱"/>
                        <i class="ace-icon fa fa-envelope"></i>
                        <label class="error hide" for="email"></label>
                    </span>
                    </div>
                </div>

                <div class="form-group clearfix">
                    <label class="col-xs-12 control-label no-padding-right">密码<span class="red">*</span></label>
                    <div class="col-xs-12">
                    <span class="block input-icon input-icon-right">
                        <input type="password" id="password" name="password" class="form-control" placeholder="请输入密码"/>
                        <i class="ace-icon fa fa-lock"></i>
                        <label class="error hide" for="password"></label>
                    </span>
                    </div>
                </div>

                <div class="form-group clearfix">
                    <label class="col-xs-12 control-label no-padding-right">确认密码<span class="red">*</span></label>
                    <div class="col-xs-12">
                    <span class="block input-icon input-icon-right">
                        <input type="password" id="rePassword" name="rePassword" class="form-control" placeholder="请再次输入密码"/>
                        <i class="ace-icon fa fa-lock"></i>
                        <label class="error hide" for="rePassword"></label>
                    </span>
                    </div>
                </div>

                <div class="form-group clearfix">
                    <label class="col-xs-12 control-label no-padding-right">真实姓名<span class="red">*</span></label>
                    <div class="col-xs-12">
                    <span class="block input-icon input-icon-right">
                        <input type="text" id="realname" name="realname" class="form-control" placeholder="用户真实姓名"/>
                        <i class="ace-icon fa fa-lock"></i>
                        <label class="error hide" for="realname"></label>
                    </span>
                    </div>
                </div>

                <div class="form-group clearfix">
                    <label class="col-xs-12 control-label no-padding-right">手机号</label>
                    <div class="col-xs-12">
                    <span class="block input-icon input-icon-right">
                        <input type="text" id="mobile" name="mobile" class="form-control" placeholder="请输入手机号"/>
                        <i class="ace-icon fa fa-lock"></i>
                        <label class="error hide" for="mobile"></label>
                    </span>
                    </div>
                </div>

                <div class="form-group clearfix">
                    <label class="col-xs-12 control-label no-padding-right">验证码<span class="red">*</span></label>
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

                <div class="space-4"></div>

                <div class="col-xs-12 clearfix">
                    <button class="btn btn-sm width-100 skin-btn">注册</button>
                </div>
            </form>
        </div>
    </div>

    <div class="widget-header widget-header-flat login-actions skin-border skin-bg">
        <div class="col-xs-12">
            <a href="${ctx}login" class="infobox-black center skin-a">
                <i class="ace-icon fa fa-arrow-left"></i>
                已有账号？去登录
            </a>
        </div>
    </div>
</div>
</@override>

<@override name="script">
<script src="${ctx}static/app/js/web/register/index.js"></script>
</@override>

<@extends name="../auth-layout.ftl"/>
