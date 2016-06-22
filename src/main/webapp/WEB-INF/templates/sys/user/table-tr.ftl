<tr>
    <td>${item.id}</td>
    <td>${item.realname}</td>
    <td>${item.mobile}</td>
    <td><#include "locked.ftl"></td>
    <td>${item.loginTime?datetime}</td>
    <td>
        <div class="btn-group">
            <a data-toggle="modal" class="btn btn-xs btn-inverse" href="${ctx}sys/user/${item.id}"
               data-target="#myModal">详情</a>

            <button data-toggle="dropdown" class="btn btn-xs btn-inverse dropdown-toggle">
                <span class="ace-icon fa fa-caret-down icon-only"></span>
            </button>

            <ul class="dropdown-menu dropdown-menu-right dropdown-inverse">
                <li>
                    <a href="${ctx}sys/user/${item.id}/edit" data-toggle="modal" data-target="#myModal"
                       data-backdrop="static">编辑用户信息</a>
                </li>
                <li>
                    <a class="delete-item" title="${item.realname}" data-url="${ctx}sys/user/${item.id}/delete">
                        删除用户
                    </a>
                </li>
                <li>
                    <a href="${ctx}sys/user/${item.id}/roles" data-toggle="modal" data-target="#myModal"
                       data-backdrop="static">设置角色</a>
                </li>
            </ul>
        </div>
    </td>
</tr>