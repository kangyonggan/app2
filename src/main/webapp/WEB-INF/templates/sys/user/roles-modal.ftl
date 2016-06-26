<#assign modal_title="设置角色" />

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post" action="${ctx}sys/user/${id}/roles">
    <div class="control-group">
    <#list all_roles as role>
        <div class="checkbox">
            <label>
                <input name="roles" type="checkbox" value="${role.id}" class="ace" ${user_roles?seq_contains(role.code)?string("checked", "")} />
                <span class="lbl"> ${role.name} </span>
            </label>
        </div>
    </#list>
    </div>
</form>
</@override>

<@override name="modal-footer">
<button id="submit" class="btn skin-btn" data-loading-text="正在提交...">
    <i class="ace-icon fa fa-check bigger-110"></i>
${submit}
</button>

<script src="${ctx}static/app/js/sys/user/roles-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>