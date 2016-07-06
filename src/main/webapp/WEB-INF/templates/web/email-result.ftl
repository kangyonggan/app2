<#assign title="${header}">

<@override name="content">
<div class="widget-box no-border">
    <div class="widget-body">
        <div class="widget-main">
            <h4 class="header skin-color lighter bigger">
                <i class="ace-icon fa fa-leaf"></i>
            ${message}
            </h4>

            <div class="row">
                <div class="col-xs-12">
                    <#if userId??>
                        <form class="form-horizontal" role="form" id="form" method="post"
                              action="${ctx}user/password/update">
                            <input type="hidden" value="${userId}" name="id"/>
                            <@c.password id="password" label="密码" required="true"/>
                            <@c.password id="rePassword" label="确认密码" required="true"/>
                            <div class="space-20"></div>
                            <div class="form-group">
                                <div class="col-xs-10 col-xs-offset-1">
                                    <button class="form-submit btn width-100 skin-btn">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                    ${submit}
                                    </button>
                                </div>
                            </div>
                        </form>
                        <hr>
                    <#else>
                        <p class="alert alert-info">${message}。</p>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
</@override>

<@override name="script">
<script src="${ctx}static/app/js/web/email-result.js"></script>
</@override>

<@extends name="auth-layout.ftl"/>
