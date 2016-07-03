<tr id="user-${item.id}">
    <td>${item.id}</td>
    <td>${item.name}</td>
    <td title="${item.url}">
        <a href="${item.url}" target="_blank"><@c.substring str="${item.url}" len=30/></a>
    </td>
    <td>${item.createdTime?datetime}</td>
    <td>
        <div class="btn-group">
            <a data-toggle="modal" class="btn btn-xs skin-btn" href="${ctx}pits/page?id=${item.id}"
               data-target="#myModal">详情</a>

            <button data-toggle="dropdown" class="btn btn-xs dropdown-toggle skin-btn">
                <span class="ace-icon fa fa-caret-down icon-only"></span>
            </button>

            <ul class="dropdown-menu dropdown-menu-right">
                <li>
                    <a href="${ctx}pits/page/edit?id=${item.id}" data-toggle="modal" data-target="#myModal"
                       data-backdrop="static" class="skin-hover">编辑页面信息</a>
                </li>
                <li>
                    <a class="delete-item skin-hover" title="${item.name}" data-url="${ctx}pits/page/delete?id=${item.id}">
                        删除页面
                    </a>
                </li>
            </ul>
        </div>
    </td>
</tr>