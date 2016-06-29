<#assign title="邮箱验证结果">

<@override name="content">
<div class="widget-box no-border">
    <div class="widget-body">
        <div class="widget-main">
            <h4 class="header skin-color lighter bigger">
                <i class="ace-icon fa fa-envelope"></i>
            ${message}
            </h4>

            <div class="row">
                <div class="col-xs-12">
                    <p class="alert alert-info">${message}。</p>
                </div>
            </div>
        </div>
    </div>
</div>
</@override>

<@extends name="auth-layout.ftl"/>
