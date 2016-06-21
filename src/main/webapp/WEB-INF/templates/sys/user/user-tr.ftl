<tr id="user-${user.id}">
    <td>${user.id}</td>
    <td>${user.realname}</td>
    <td>${user.mobile}</td>
    <td><#include "locked.ftl"></td>
    <td>${user.loginTime?datetime}</td>
    <td>
        <div class="btn-group">
            <a data-toggle="modal" class="btn btn-xs btn-inverse" href="${ctx}sys/user/${user.id}"
               data-target="#myModal">详情</a>

            <button data-toggle="dropdown" class="btn btn-xs btn-inverse dropdown-toggle">
                <span class="ace-icon fa fa-caret-down icon-only"></span>
            </button>

            <ul class="dropdown-menu dropdown-menu-right dropdown-inverse">
                <li>
                    <a href="${ctx}sys/user/${user.id}/edit" data-toggle="modal" data-target="#myModal"
                       data-backdrop="static">编辑用户信息</a>
                </li>
                <li>
                    <a title="${user.realname}" data-role="delete-user" data-url="${ctx}sys/user/${user.id}/delete">
                        删除用户
                    </a>
                </li>
                <li>
                    <a href="${ctx}sys/user/${user.id}/roles" data-toggle="modal" data-target="#myModal"
                       data-backdrop="static">设置角色</a>
                </li>
            </ul>
        </div>
    </td>
</tr>