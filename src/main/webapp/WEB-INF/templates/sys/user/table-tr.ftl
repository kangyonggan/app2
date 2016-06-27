<tr id="user-${item.id}">
    <td>${item.id}</td>
    <td>${item.realname}</td>
    <td>${item.email}</td>
    <td>${item.mobile}</td>
    <td><#include "locked.ftl"></td>
    <td>
        <div class="btn-group">
            <a data-toggle="modal" class="btn btn-xs skin-btn" href="${ctx}sys/user?id=${item.id}"
               data-target="#myModal">详情</a>

            <button data-toggle="dropdown" class="btn btn-xs dropdown-toggle skin-btn">
                <span class="ace-icon fa fa-caret-down icon-only"></span>
            </button>

            <ul class="dropdown-menu dropdown-menu-right">
                <li>
                    <a href="${ctx}sys/user/edit?id=${item.id}" data-toggle="modal" data-target="#myModal"
                       data-backdrop="static" class="skin-hover">编辑用户信息</a>
                </li>
                <li>
                    <a class="delete-item skin-hover" title="${item.realname}" data-url="${ctx}sys/user/delete?id=${item.id}">
                        删除用户
                    </a>
                </li>
                <li>
                    <a href="${ctx}sys/user/roles?id=${item.id}" data-toggle="modal" data-target="#myModal"
                       data-backdrop="static" class="skin-hover">设置角色</a>
                </li>
            </ul>
        </div>
    </td>
</tr>