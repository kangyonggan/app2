<div id="navbar" class="navbar navbar-default navbar-collapse h-navbar">
    <script>
        if (ace.storage.get("navbar-fixed") != null) {
            $("#ace-settings-navbar").attr("checked", "checked");
            $("#navbar").addClass("navbar-fixed-top");
        }
    </script>
    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="${ctx}" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                ${appName}
                </small>
            </a>

            <button class="pull-right navbar-toggle collapsed" type="button" data-toggle="collapse"
                    data-target=".navbar-menu">
                <span class="sr-only">导航条开关</span>

                <span class="icon-bar"></span>

                <span class="icon-bar"></span>

                <span class="icon-bar"></span>
            </button>
        </div>

    <@shiro.user>
        <nav role="navigation" class="navbar-menu pull-left collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <#list app_category.childrens as category>
                    <#assign hasChildren=category.childrens?size gt 0>
                    <li>
                        <a href="${ctx}user/${app_user.id}/category/${category.code}"<#if hasChildren>
                           class="dropdown-toggle"
                           data-toggle="dropdown"</#if>>
                            <i class="${category.icon} bigger-140"></i>
                        ${category.name}
                            <#if hasChildren>
                                &nbsp;
                                <i class="ace-icon fa fa-angle-down bigger-140"></i>
                            </#if>
                        </a>
                        <#if hasChildren>
                            <ul class="dropdown-menu dropdown-light-blue dropdown-caret">
                                <#list category.childrens as c>
                                    <li>
                                        <a href="${ctx}category/${c.code}/user/${app_user.id}">
                                            <i class="${c.icon} bigger-120"></i>
                                        ${c.name}
                                        </a>
                                    </li>
                                </#list>
                            </ul>
                        </#if>
                    </li>
                </#list>

                <@shiro.hasPermission name="sys">
                    <@app_menus>
                        <li>
                            <a href="javascript:" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="${sys_parent_menu.icon} bigger-140"></i>
                            ${sys_parent_menu.name}
                                &nbsp;
                                <i class="ace-icon fa fa-angle-down bigger-140"></i>
                            </a>

                            <ul class="dropdown-menu dropdown-light-blue dropdown-caret">
                                <#list sys_menus as menu>
                                    <li>
                                        <a href="${ctx}${menu.url}">
                                            <i class="${menu.icon} bigger-120"></i>
                                        ${menu.name}
                                        </a>
                                    </li>
                                </#list>
                            </ul>
                        </li>
                    </@app_menus>
                </@shiro.hasPermission>
            </ul>
        </nav>
    </@shiro.user>

        <nav role="navigation" class="navbar-menu pull-right collapse navbar-collapse">
            <ul class="nav navbar-nav">
            <@shiro.user>
                <li>
                    <a href="${ctx}user/${app_user.id}">${app_user.realname}</a>
                </li>
                <li>
                    <a href="${ctx}logout">
                        <i class="ace-icon fa fa-power-off"></i>
                        退出
                    </a>
                </li>
            </@shiro.user>
            <@shiro.guest>
                <li>
                    <a href="${ctx}login">
                        <i class="ace-icon fa fa-sign-in"></i>
                        登录
                    </a>
                </li>
                <li>
                    <a href="${ctx}register">
                        <i class="ace-icon fa fa-users"></i>
                        注册
                    </a>
                </li>
            </@shiro.guest>
            </ul>
        </nav>
    </div>
</div>