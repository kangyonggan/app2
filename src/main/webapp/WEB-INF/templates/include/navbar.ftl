<div id="navbar" class="navbar navbar-default navbar-collapse h-navbar">
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
                <li>
                        <a href="${ctx}user/${app_user.id}">
                            <i class="ace-icon fa fa-user bigger-140"></i>
                            个人中心
                        </a>
                </li>

                <li>
                    <a href="javascript:" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="ace-icon fa fa-home bigger-140"></i>
                        我的栏目
                        &nbsp;
                        <i class="ace-icon fa fa-angle-down bigger-140"></i>
                    </a>

                    <ul class="dropdown-menu dropdown-light-blue dropdown-caret">
                        <li>
                            <a href="javascript:">
                                <i class="ace-icon fa fa-eye bigger-120"></i>
                                博客
                            </a>
                        </li>

                        <li>
                            <a href="javascript:">
                                <i class="ace-icon fa fa-user bigger-120"></i>
                                日志
                            </a>
                        </li>

                        <li>
                            <a href="javascript:">
                                <i class="ace-icon fa fa-cog bigger-120"></i>
                                相册
                            </a>
                        </li>

                        <li>
                            <a href="javascript:">
                                <i class="ace-icon fa fa-cog bigger-120"></i>
                                时光轴
                            </a>
                        </li>

                        <li>
                            <a href="javascript:">
                                <i class="ace-icon fa fa-cog bigger-120"></i>
                                留言板
                            </a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="javascript:">
                        <i class="ace-icon fa fa-users bigger-140"></i>
                        我的好友
                        <span class="badge badge-important">5</span>
                    </a>
                </li>

                <@shiro.hasPermission name="sys-manage">
                    <@app_menus>
                        <li>
                            <a href="javascript:" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="${sys_parent_menu.icon}"></i>
                            ${sys_parent_menu.name}
                                &nbsp;
                                <i class="ace-icon fa fa-angle-down bigger-140"></i>
                            </a>

                            <ul class="dropdown-menu dropdown-light-blue dropdown-caret">
                                <#list sys_menus as menu>
                                    <li>
                                        <a href="${ctx}${menu.url}">
                                            <i class="${menu.icon!''}"></i>
                                        ${menu.name}
                                        </a>
                                    </li>
                                </#list>
                            </ul>
                        </li>
                    </@app_menus>
                </@shiro.hasPermission>
            </ul>

            <ul class="nav navbar-nav">
                <li class="transparent">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="javascript:">
                        <i class="ace-icon fa fa-bell icon-animated-bell"></i>
                        <span class="badge badge-warning">9</span>
                    </a>

                    <div class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                        <div class="tabbable">
                            <ul class="nav nav-tabs">
                                <li class="active">
                                    <a data-toggle="tab" href="#navbar-tasks">
                                        Tasks
                                        <span class="badge badge-danger">4</span>
                                    </a>
                                </li>

                                <li>
                                    <a data-toggle="tab" href="#navbar-messages">
                                        Messages
                                        <span class="badge badge-danger">5</span>
                                    </a>
                                </li>
                            </ul><!-- .nav-tabs -->

                            <div class="tab-content">
                                <div id="navbar-tasks" class="tab-pane in active">
                                    <ul class="dropdown-menu-right dropdown-navbar dropdown-menu">
                                        <li class="dropdown-content ace-scroll" style="position: relative;">
                                            <div class="scroll-track" style="display: none;">
                                                <div class="scroll-bar"></div>
                                            </div>
                                            <div class="scroll-content" style="max-height: 200px;">
                                                <ul class="dropdown-menu dropdown-navbar">
                                                    <li>
                                                        <a href="javascript:">
                                                            <div class="clearfix">
                                                                <span class="pull-left">Software Update</span>
                                                                <span class="pull-right">65%</span>
                                                            </div>

                                                            <div class="progress progress-mini">
                                                                <div style="width:65%" class="progress-bar"></div>
                                                            </div>
                                                        </a>
                                                    </li>

                                                    <li>
                                                        <a href="javascript:">
                                                            <div class="clearfix">
                                                                <span class="pull-left">Hardware Upgrade</span>
                                                                <span class="pull-right">35%</span>
                                                            </div>

                                                            <div class="progress progress-mini">
                                                                <div style="width:35%"
                                                                     class="progress-bar progress-bar-danger"></div>
                                                            </div>
                                                        </a>
                                                    </li>

                                                    <li>
                                                        <a href="javascript:">
                                                            <div class="clearfix">
                                                                <span class="pull-left">Unit Testing</span>
                                                                <span class="pull-right">15%</span>
                                                            </div>

                                                            <div class="progress progress-mini">
                                                                <div style="width:15%"
                                                                     class="progress-bar progress-bar-warning"></div>
                                                            </div>
                                                        </a>
                                                    </li>

                                                    <li>
                                                        <a href="javascript:">
                                                            <div class="clearfix">
                                                                <span class="pull-left">Bug Fixes</span>
                                                                <span class="pull-right">90%</span>
                                                            </div>

                                                            <div class="progress progress-mini progress-striped active">
                                                                <div style="width:90%"
                                                                     class="progress-bar progress-bar-success"></div>
                                                            </div>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>

                                        <li class="dropdown-footer">
                                            <a href="javascript:">
                                                See tasks with details
                                                <i class="ace-icon fa fa-arrow-right"></i>
                                            </a>
                                        </li>
                                    </ul>
                                </div><!-- /.tab-pane -->

                                <div id="navbar-messages" class="tab-pane">
                                    <ul class="dropdown-menu-right dropdown-navbar dropdown-menu">
                                        <li class="dropdown-content ace-scroll" style="position: relative;">
                                            <div class="scroll-track" style="display: none;">
                                                <div class="scroll-bar"></div>
                                            </div>
                                            <div class="scroll-content" style="max-height: 200px;">
                                                <ul class="dropdown-menu dropdown-navbar">
                                                    <li>
                                                        <a href="javascript:">
                                                            <img src="${ctx}static/ace/dist/avatars/avatar.png"
                                                                 class="msg-photo" alt="Alex's Avatar">
																<span class="msg-body">
																	<span class="msg-title">
																		<span class="blue">Alex:</span>
																		Ciao sociis natoque penatibus et auctor ...
																	</span>

																	<span class="msg-time">
																		<i class="ace-icon fa fa-clock-o"></i>
																		<span>a moment ago</span>
																	</span>
																</span>
                                                        </a>
                                                    </li>

                                                    <li>
                                                        <a href="javascript:">
                                                            <img src="${ctx}static/ace/dist/avatars/avatar3.png"
                                                                 class="msg-photo" alt="Susan's Avatar">
																<span class="msg-body">
																	<span class="msg-title">
																		<span class="blue">Susan:</span>
																		Vestibulum id ligula porta felis euismod ...
																	</span>

																	<span class="msg-time">
																		<i class="ace-icon fa fa-clock-o"></i>
																		<span>20 minutes ago</span>
																	</span>
																</span>
                                                        </a>
                                                    </li>

                                                    <li>
                                                        <a href="javascript:">
                                                            <img src="${ctx}static/ace/dist/avatars/avatar4.png"
                                                                 class="msg-photo" alt="Bob's Avatar">
																<span class="msg-body">
																	<span class="msg-title">
																		<span class="blue">Bob:</span>
																		Nullam quis risus eget urna mollis ornare ...
																	</span>

																	<span class="msg-time">
																		<i class="ace-icon fa fa-clock-o"></i>
																		<span>3:15 pm</span>
																	</span>
																</span>
                                                        </a>
                                                    </li>

                                                    <li>
                                                        <a href="javascript:">
                                                            <img src="${ctx}static/ace/dist/avatars/avatar2.png"
                                                                 class="msg-photo" alt="Kate's Avatar">
																<span class="msg-body">
																	<span class="msg-title">
																		<span class="blue">Kate:</span>
																		Ciao sociis natoque eget urna mollis ornare ...
																	</span>

																	<span class="msg-time">
																		<i class="ace-icon fa fa-clock-o"></i>
																		<span>1:33 pm</span>
																	</span>
																</span>
                                                        </a>
                                                    </li>

                                                    <li>
                                                        <a href="javascript:">
                                                            <img src="${ctx}static/ace/dist/avatars/avatar5.png"
                                                                 class="msg-photo" alt="Fred's Avatar">
																<span class="msg-body">
																	<span class="msg-title">
																		<span class="blue">Fred:</span>
																		Vestibulum id penatibus et auctor  ...
																	</span>

																	<span class="msg-time">
																		<i class="ace-icon fa fa-clock-o"></i>
																		<span>10:09 am</span>
																	</span>
																</span>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>

                                        <li class="dropdown-footer">
                                            <a href="inbox.html">
                                                See all messages
                                                <i class="ace-icon fa fa-arrow-right"></i>
                                            </a>
                                        </li>
                                    </ul>
                                </div><!-- /.tab-pane -->
                            </div><!-- /.tab-content -->
                        </div><!-- /.tabbable -->
                    </div><!-- /.dropdown-menu -->
                </li>
            </ul>
        </nav>
    </@shiro.user>

        <nav role="navigation" class="navbar-menu pull-right collapse navbar-collapse">
            <ul class="nav navbar-nav">
            <@shiro.user>
                <li>
                    <a href="javascript:">${app_user.realname}</a>
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
                        <i class="ace-icon fa fa-lightbulb-o"></i>
                        注册
                    </a>
                </li>
            </@shiro.guest>
            </ul>
        </nav>
    </div>
</div>