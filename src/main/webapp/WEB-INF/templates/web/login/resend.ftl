<#assign title="重发邮件">

<@override name="content">

<div class="space-28"></div>

<div class="widget-box skin-border">
    <div class="widget-header widget-header-flat skin-bg">
        <h4 class="widget-title smaller skin-inverse">
            <i class="ace-icon fa fa-leaf skin-inverse"></i>
            重发邮件
        </h4>
    </div>

    <div class="widget-body">
        <div class="widget-main clearfix">
            <form id="resend-form" method="post" action="${ctx}resend">
                <input type="hidden" name="email" value="${email}"/>
                <div class="form-group clearfix">
                    <label class="col-xs-12 control-label no-padding-right">验证码</label>
                    <div class="col-xs-7">
                        <span class="block input-icon input-icon-right">
                            <input value="" type="text" id="captcha" name="captcha" class="form-control"
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
                    <button class="btn btn-sm width-100 skin-btn" data-loading-text="正在提交...">重发</button>
                </div>
            </form>
        </div>
    </div>

    <div class="space-8"></div>

    <div class="widget-header widget-header-flat login-actions skin-bg">
        <div class="col-xs-6">
            <a href="${ctx}forget" class="skin-a">
                <i class="ace-icon fa fa-arrow-left"></i>
                忘记密码？
            </a>
        </div>
        <div class="col-xs-6">
            <a href="${ctx}register" class="go-register skin-a">
                <i class="ace-icon fa fa-arrow-right"></i>
                注册
            </a>
        </div>
    </div>
</div>
</@override>

<@override name="script">
<script src="${ctx}static/app/js/web/login/resend.js"></script>
</@override>

<@extends name="../auth-layout.ftl"/>
