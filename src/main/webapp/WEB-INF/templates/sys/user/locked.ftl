<#if item.isLocked == 1>
<a href="javascript:" data-role="item-status"
   data-url="${ctx}sys/user/${item.id}/locked/0">
    <span class="label label-danger arrowed-in">已锁定</span>
</a>
<#else>
<a href="javascript:" data-role="item-status"
   data-url="${ctx}sys/user/${item.id}/locked/1">
    <span class="label label-success arrowed-in">未锁定</span>
</a>
</#if>