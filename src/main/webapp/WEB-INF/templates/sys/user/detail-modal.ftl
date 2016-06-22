<#assign modal_title="用户详情" />

<@override name="modal-body">
<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
    <tbody>
    <tr>
        <th width="30%">电子邮箱</th>
        <td width="70%">${item.email}</td>
    </tr>
    <tr>
        <th>邮箱是否校验</th>
        <td>
            <@c.textIcon flag=item.isVerified==1 trueText="已校验" falseText="未校验"/>
        </td>
    </tr>
    <tr>
        <th>真实姓名</th>
        <td>${item.realname}</td>
    </tr>
    <tr>
        <th>手机号</th>
        <td>${item.mobile}</td>
    </tr>
    <tr>
        <th>个性签名</th>
        <td title="${item.sign}"><@c.substring str="${item.sign}" len=20/></td>
    </tr>
    <tr>
        <th>是否已锁定</th>
        <td><@c.textIcon flag=item.isLocked==0 trueText="未锁定" falseText="已锁定"/></td>
    </tr>
    <tr>
        <th>是否已删除</th>
        <td><@c.textIcon flag=item.isDeleted==0 trueText="未删除" falseText="已删除"/></td>
    </tr>
    <tr>
        <th>创建时间</th>
        <td>${item.createdTime?datetime}</td>
    </tr>
    <tr>
        <th>最后更新时间</th>
        <td>${item.updatedTime?datetime}</td>
    </tr>
    <tr>
        <th>最近登录时间</th>
        <td>${item.loginTime?datetime}</td>
    </tr>
    </tbody>
</table>
</@override>

<@override name="modal-footer">
<button class="btn btn-sm" data-dismiss="modal">
    <i class="ace-icon fa fa-remove bigger-110"></i>
${cancel}
</button>
</@override>

<@extends name="../../modal-layout.ftl"/>