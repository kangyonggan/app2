<tr id="role-${item.id}">
    <td>${item.id}</td>
    <td>${item.name}</td>
    <td>${item.code}</td>
    <td>${item.createdTime?datetime}</td>
    <td>
        <div class="btn-group">
            <a data-toggle="modal" class="btn btn-xs btn-inverse" href="${ctx}sys/role/${item.id}"
               data-target="#myModal">详情</a>

            <button data-toggle="dropdown" class="btn btn-xs btn-inverse dropdown-toggle">
                <span class="ace-icon fa fa-caret-down icon-only"></span>
            </button>

            <ul class="dropdown-menu dropdown-menu-right dropdown-inverse">
                <li>
                    <a href="${ctx}sys/role/${item.id}/edit" data-toggle="modal" data-target="#myModal"
                       data-backdrop="static">编辑角色信息</a>
                </li>
                <li>
                    <a title="${item.name}" data-role="delete-item" data-url="${ctx}sys/role/${item.id}/delete">
                        删除角色
                    </a>
                </li>
                <li>
                    <a href="${ctx}sys/role/${item.id}/menus" data-toggle="modal" data-target="#myModal"
                       data-backdrop="static">设置菜单</a>
                </li>
            </ul>
        </div>
    </td>
</tr>