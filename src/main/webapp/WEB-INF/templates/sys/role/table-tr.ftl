<tr>
    <td>${item.id}</td>
    <td>${item.code}</td>
    <td>${item.name}</td>
    <td>${item.createdTime?datetime}</td>
    <td>
        <div class="btn-group">
            <a data-toggle="modal" class="btn btn-xs skin-btn" href="${ctx}sys/role/${item.id}"
               data-target="#myModal">详情</a>

            <button data-toggle="dropdown" class="btn btn-xs dropdown-toggle skin-btn">
                <span class="ace-icon fa fa-caret-down icon-only"></span>
            </button>

            <ul class="dropdown-menu dropdown-menu-right">
                <li>
                    <a href="${ctx}sys/role/${item.id}/edit" data-toggle="modal" data-target="#myModal"
                       data-backdrop="static" class="skin-hover">编辑角色信息</a>
                </li>
                <li>
                    <a class="delete-item skin-hover" title="${item.name}" data-url="${ctx}sys/role/${item.id}/delete">
                        删除角色
                    </a>
                </li>
                <li>
                    <a href="${ctx}sys/role/${item.id}/menus" data-toggle="modal" data-target="#myModal"
                       data-backdrop="static" class="skin-hover">设置菜单</a>
                </li>
            </ul>
        </div>
    </td>
</tr>