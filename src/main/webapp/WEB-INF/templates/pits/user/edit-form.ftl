<#assign no_right="">
<#assign title="资料修改">

<@override name="content">
    <form action="${ctx}pits/user/update" class="form-horizontal" role="form" id="form" method="post">
        <input type="hidden" name="id" value="${item.id}"/>
        <@c.input id="mobile" label="手机号" val="${item.mobile!''}" empty="请输入手机号"/>
        <@c.input id="sign" label="个性签名" val="${item.sign!''}" empty="彰显性格的签名..."/>

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
<script src="${ctx}static/app/js/pits/user/edit-form.js"></script>
</@override>

<@extends name="../../app-layout.ftl"/>
