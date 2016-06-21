<#if user.isLocked == 1>
<a href="javascript:" data-role="status-user"
   data-url="${ctx}sys/user/${user.id}/unlock">
    <span class="label label-success arrowed-in">已锁定</span>
</a>
<#else>
<a href="javascript:" data-role="status-user"
   data-url="${ctx}sys/user/${user.id}/locked">
    <span class="label label-danger arrowed-in">未锁定</span>
</a>
</#if>