<#assign title="首页">

<@override name="style">
<link rel="stylesheet" href="${ctx}static/app/css/web/index/index.css"/>
</@override>

<@override name="page_main">

<div id="introduction" class="center">
    <a href="${ctx}">
        <img src="${ctx}static/app/imgs/logo.png" class="logo"/>
    </a>
    <a href="${ctx}" class="btn btn-purple btn-lg introduction">
        <i class="ace-icon fa fa-info-circle bigger-110"></i>
        我的简介
    </a>
</div>

<div id="blog" class="row app-module">
    <div class="col-xs-6 col-sm-3 pricing-box">
        <div class="widget-box widget-color-dark">
            <div class="widget-header">
                <h5 class="widget-title bigger lighter">
                    <i class="ace-icon fa fa-book bigger-110"></i>
                    学习笔记
                </h5>
            </div>

            <div class="widget-body">
                <div class="widget-main">
                    <ul class="list-unstyled spaced2">
                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>

                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>

                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>

                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>

                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>

                        <li>
                            <i class="ace-icon fa fa-times red"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>
                    </ul>

                    <hr>
                    <h4 class="text-center">新知识、冷知识、备忘录</h4>
                </div>

                <div>
                    <a href="#" class="btn btn-block btn-inverse">
                        <i class="ace-icon fa fa-list bigger-110"></i>
                        <span>更多</span>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <div class="col-xs-6 col-sm-3 pricing-box">
        <div class="widget-box widget-color-orange">
            <div class="widget-header">
                <h5 class="widget-title bigger lighter">
                    <i class="ace-icon fa fa-picture-o bigger-110"></i>
                    图文教程
                </h5>
            </div>

            <div class="widget-body">
                <div class="widget-main">
                    <ul class="list-unstyled spaced2">
                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>

                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>

                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>

                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>

                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>

                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>
                    </ul>

                    <hr>
                    <h4 class="text-center">安装、配置、环境、框架</h4>
                </div>

                <div>
                    <a href="#" class="btn btn-block btn-warning">
                        <i class="ace-icon fa fa-list bigger-110"></i>
                        <span>更多</span>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <div class="col-xs-6 col-sm-3 pricing-box">
        <div class="widget-box widget-color-blue">
            <div class="widget-header">
                <h5 class="widget-title bigger lighter">
                    <i class="ace-icon fa fa-code bigger-110"></i>
                    代码片段
                </h5>
            </div>

            <div class="widget-body">
                <div class="widget-main">
                    <ul class="list-unstyled spaced2">
                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>

                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>

                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>

                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>

                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>

                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>
                    </ul>

                    <hr>
                    <h4 class="text-center">配置文件、工具类、算法</h4>
                </div>

                <div>
                    <a href="#" class="btn btn-block btn-primary">
                        <i class="ace-icon fa fa-list bigger-110"></i>
                        <span>更多</span>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <div class="col-xs-6 col-sm-3 pricing-box">
        <div class="widget-box widget-color-green">
            <div class="widget-header">
                <h5 class="widget-title bigger lighter">
                    <i class="ace-icon fa fa-bug bigger-110"></i>
                    BUG库
                </h5>
            </div>

            <div class="widget-body">
                <div class="widget-main">
                    <ul class="list-unstyled spaced2">
                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>

                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>

                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>

                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>

                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>

                        <li>
                            <i class="ace-icon fa fa-check green"></i>
                            <a href="#">JDK1.8新特新学习笔记</a>
                        </li>
                    </ul>

                    <hr>
                    <h4 class="text-center">已错、易错</h4>
                </div>

                <div>
                    <a href="#" class="btn btn-block btn-success">
                        <i class="ace-icon fa fa-list bigger-110"></i>
                        <span>更多</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="diary" class="row app-module">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <div class="align-right">
            <label class="pull-right inline">
                <small class="muted">时间线:</small>

                <input id="gritter-light" checked="" type="checkbox" class="ace ace-switch ace-switch-5">
                <span class="lbl middle"></span>
            </label>
        </div>

        <div id="timeline-1" class="">
            <div class="row">
                <div class="col-xs-12 col-sm-12">
                    <div class="timeline-container">
                        <div class="timeline-label">
                            <span class="label label-primary arrowed-in-right label-lg">
                                <b>今天</b>
                            </span>
                        </div>

                        <div class="timeline-items">
                            <div class="timeline-item clearfix">
                                <div class="timeline-info">
                                    <img alt="Susan't Avatar" src="${ctx}static/ace/assets/avatars/avatar1.png">
                                    <span class="label label-info label-sm">16:22</span>
                                </div>

                                <div class="widget-box transparent">
                                    <div class="widget-header widget-header-small">
                                        <h5 class="widget-title smaller">
                                            <a href="#" class="blue">Susan</a>
                                            <span class="grey">reviewed a product</span>
                                        </h5>

                                        <span class="widget-toolbar no-border">
                                            <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                            16:22
                                        </span>

                                        <span class="widget-toolbar">
                                            <a href="#" data-action="reload">
                                                <i class="ace-icon fa fa-refresh"></i>
                                            </a>

                                            <a href="#" data-action="collapse">
                                                <i class="ace-icon fa fa-chevron-up"></i>
                                            </a>
                                        </span>
                                    </div>

                                    <div class="widget-body">
                                        <div class="widget-main">
                                            Anim pariatur cliche reprehenderit, enim eiusmod
                                            <span class="red">high life</span>

                                            accusamus terry richardson ad squid …
                                            <div class="space-6"></div>

                                            <div class="widget-toolbox clearfix">
                                                <div class="pull-left">
                                                    <i class="ace-icon fa fa-hand-o-right grey bigger-125"></i>
                                                    <a href="#" class="bigger-110">Click to read …</a>
                                                </div>

                                                <div class="pull-right action-buttons">
                                                    <a href="#">
                                                        <i class="ace-icon fa fa-check green bigger-130"></i>
                                                    </a>

                                                    <a href="#">
                                                        <i class="ace-icon fa fa-pencil blue bigger-125"></i>
                                                    </a>

                                                    <a href="#">
                                                        <i class="ace-icon fa fa-times red bigger-125"></i>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="timeline-item clearfix">
                                <div class="timeline-info">
                                    <i class="timeline-indicator ace-icon fa fa-cutlery btn btn-success no-hover"></i>
                                </div>

                                <div class="widget-box transparent">
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            Going to cafe for lunch
                                            <div class="pull-right">
                                                <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                                12:30
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="timeline-item clearfix">
                                <div class="timeline-info">
                                    <i class="timeline-indicator ace-icon fa fa-star btn btn-warning no-hover green"></i>
                                </div>

                                <div class="widget-box transparent">
                                    <div class="widget-header widget-header-small">
                                        <h5 class="widget-title smaller">New logo</h5>

                                        <span class="widget-toolbar no-border">
                                            <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                            9:15
                                        </span>

                                        <span class="widget-toolbar">
                                            <a href="#" data-action="reload">
                                                <i class="ace-icon fa fa-refresh"></i>
                                            </a>

                                            <a href="#" data-action="collapse">
                                                <i class="ace-icon fa fa-chevron-up"></i>
                                            </a>
                                        </span>
                                    </div>

                                    <div class="widget-body">
                                        <div class="widget-main">
                                            Designed a new logo for our website. Would appreciate feedback.
                                            <div class="space-6"></div>

                                            <div class="widget-toolbox clearfix">
                                                <div class="pull-right action-buttons">
                                                    <div class="space-4"></div>

                                                    <div>
                                                        <a href="#">
                                                            <i class="ace-icon fa fa-heart red bigger-125"></i>
                                                        </a>

                                                        <a href="#">
                                                            <i class="ace-icon fa fa-facebook blue bigger-125"></i>
                                                        </a>

                                                        <a href="#">
                                                            <i class="ace-icon fa fa-reply light-green bigger-130"></i>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="timeline-item clearfix">
                                <div class="timeline-info">
                                    <i class="timeline-indicator ace-icon fa fa-flask btn btn-default no-hover"></i>
                                </div>

                                <div class="widget-box transparent">
                                    <div class="widget-body">
                                        <div class="widget-main"> Took the final exam. Phew!</div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- /.timeline-items -->
                    </div><!-- /.timeline-container -->

                    <div class="timeline-container">
                        <div class="timeline-label">
                            <span class="label label-success arrowed-in-right label-lg">
                                <b>昨天</b>
                            </span>
                        </div>

                        <div class="timeline-items">
                            <div class="timeline-item clearfix">
                                <div class="timeline-info">
                                    <i class="timeline-indicator ace-icon fa fa-beer btn btn-inverse no-hover"></i>
                                </div>

                                <div class="widget-box transparent">
                                    <div class="widget-header widget-header-small">
                                        <h5 class="widget-title smaller">Haloween party</h5>

                                        <span class="widget-toolbar">
                                            <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                            1 hour ago
                                        </span>
                                    </div>

                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <div class="clearfix">
                                                <div class="pull-left">
                                                    Lots of fun at Haloween party.
                                                    <br>
                                                    Take a look at some pics:
                                                </div>

                                                <div class="pull-right">
                                                    <i class="ace-icon fa fa-chevron-left blue bigger-110"></i>

                                                    &nbsp;
                                                    <img alt="Image 4" width="36"
                                                         src="${ctx}static/ace/assets/images/gallery/thumb-4.jpg">
                                                    <img alt="Image 3" width="36"
                                                         src="${ctx}static/ace/assets/images/gallery/thumb-3.jpg">
                                                    <img alt="Image 2" width="36"
                                                         src="${ctx}static/ace/assets/images/gallery/thumb-2.jpg">
                                                    <img alt="Image 1" width="36"
                                                         src="${ctx}static/ace/assets/images/gallery/thumb-1.jpg">
                                                    &nbsp;
                                                    <i class="ace-icon fa fa-chevron-right blue bigger-110"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="timeline-item clearfix">
                                <div class="timeline-info">
                                    <i class="timeline-indicator ace-icon fa fa-trophy btn btn-pink no-hover green"></i>
                                </div>

                                <div class="widget-box transparent">
                                    <div class="widget-header widget-header-small">
                                        <h5 class="widget-title smaller">Lorum Ipsum</h5>
                                    </div>

                                    <div class="widget-body">
                                        <div class="widget-main">
                                            Anim pariatur cliche reprehenderit, enim eiusmod
                                            <span class="green bolder">high life</span>
                                            accusamus terry richardson ad squid …
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="timeline-item clearfix">
                                <div class="timeline-info">
                                    <i class="timeline-indicator ace-icon fa fa-cutlery btn btn-success no-hover"></i>
                                </div>

                                <div class="widget-box transparent">
                                    <div class="widget-body">
                                        <div class="widget-main"> Going to cafe for lunch</div>
                                    </div>
                                </div>
                            </div>

                            <div class="timeline-item clearfix">
                                <div class="timeline-info">
                                    <i class="timeline-indicator ace-icon fa fa-bug btn btn-danger no-hover"></i>
                                </div>

                                <div class="widget-box widget-color-red2">
                                    <div class="widget-header widget-header-small">
                                        <h5 class="widget-title smaller">Critical security patch released</h5>

                                        <span class="widget-toolbar no-border">
                                            <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                            9:15
                                        </span>

                                        <span class="widget-toolbar">
                                            <a href="#" data-action="reload">
                                                <i class="ace-icon fa fa-refresh"></i>
                                            </a>

                                            <a href="#" data-action="collapse">
                                                <i class="ace-icon fa fa-chevron-up"></i>
                                            </a>
                                        </span>
                                    </div>

                                    <div class="widget-body">
                                        <div class="widget-main">
                                            Please download the new patch for maximum security.
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- /.timeline-items -->
                    </div><!-- /.timeline-container -->

                    <div class="timelin e-container">
                        <div class="timeline-label">
                            <span class="label label-grey arrowed-in-right label-lg">
                                <b>6月20</b>
                            </span>
                        </div>

                        <div class="timeline-items">
                            <div class="timeline-item clearfix">
                                <div class="timeline-info">
                                    <i class="timeline-indicator ace-icon fa fa-leaf btn btn-primary no-hover green"></i>
                                </div>

                                <div class="widget-box transparent">
                                    <div class="widget-header widget-header-small">
                                        <h5 class="widget-title smaller">Lorum Ipsum</h5>

																<span class="widget-toolbar no-border">
																	<i class="ace-icon fa fa-clock-o bigger-110"></i>
																	10:22
																</span>

																<span class="widget-toolbar">
																	<a href="#" data-action="reload">
                                                                        <i class="ace-icon fa fa-refresh"></i>
                                                                    </a>

																	<a href="#" data-action="collapse">
                                                                        <i class="ace-icon fa fa-chevron-up"></i>
                                                                    </a>
																</span>
                                    </div>

                                    <div class="widget-body">
                                        <div class="widget-main">
                                            Anim pariatur cliche reprehenderit, enim eiusmod
                                            <span class="blue bolder">high life</span>
                                            accusamus terry richardson ad squid …
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- /.timeline-items -->
                    </div><!-- /.timeline-container -->

                    <!-- /section:pages/timeline -->
                </div>
            </div>
        </div>

        <div id="timeline-2" class="hide">
            <div class="row">
                <div class="col-xs-12 col-sm-12">
                    <!-- #section:pages/timeline.style2 -->
                    <div class="timeline-container timeline-style2">
												<span class="timeline-label">
													<b>Today</b>
												</span>

                        <div class="timeline-items">
                            <div class="timeline-item clearfix">
                                <div class="timeline-info">
                                    <span class="timeline-date">11:15 pm</span>

                                    <i class="timeline-indicator btn btn-info no-hover"></i>
                                </div>

                                <div class="widget-box transparent">
                                    <div class="widget-body">
                                        <div class="widget-main no-padding">
																	<span class="bigger-110">
																		<a href="#" class="purple bolder">Susan</a>
																		reviewed a product
																	</span>

                                            <br>
                                            <i class="ace-icon fa fa-hand-o-right grey bigger-125"></i>
                                            <a href="#">Click to read …</a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="timeline-item clearfix">
                                <div class="timeline-info">
                                    <span class="timeline-date">12:30 pm</span>

                                    <i class="timeline-indicator btn btn-info no-hover"></i>
                                </div>

                                <div class="widget-box transparent">
                                    <div class="widget-body">
                                        <div class="widget-main no-padding">
                                            Going to
                                            <span class="green bolder">veg cafe</span>
                                            for lunch
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="timeline-item clearfix">
                                <div class="timeline-info">
                                    <span class="timeline-date">11:15 pm</span>

                                    <i class="timeline-indicator btn btn-info no-hover"></i>
                                </div>

                                <div class="widget-box transparent">
                                    <div class="widget-body">
                                        <div class="widget-main no-padding">
                                            Designed a new logo for our website. Would appreciate feedback.
                                            <a href="#">
                                                Click to see
                                                <i class="ace-icon fa fa-search-plus blue bigger-110"></i>
                                            </a>

                                            <div class="space-2"></div>

                                            <div class="action-buttons">
                                                <a href="#">
                                                    <i class="ace-icon fa fa-heart red bigger-125"></i>
                                                </a>

                                                <a href="#">
                                                    <i class="ace-icon fa fa-facebook blue bigger-125"></i>
                                                </a>

                                                <a href="#">
                                                    <i class="ace-icon fa fa-reply light-green bigger-130"></i>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="timeline-item clearfix">
                                <div class="timeline-info">
                                    <span class="timeline-date">9:00 am</span>

                                    <i class="timeline-indicator btn btn-info no-hover"></i>
                                </div>

                                <div class="widget-box transparent">
                                    <div class="widget-body">
                                        <div class="widget-main no-padding"> Took the final exam. Phew!</div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- /.timeline-items -->
                    </div><!-- /.timeline-container -->

                    <!-- /section:pages/timeline.style2 -->
                    <div class="timeline-container timeline-style2">
												<span class="timeline-label">
													<b>Yesterday</b>
												</span>

                        <div class="timeline-items">
                            <div class="timeline-item clearfix">
                                <div class="timeline-info">
                                    <span class="timeline-date">9:00 am</span>

                                    <i class="timeline-indicator btn btn-success no-hover"></i>
                                </div>

                                <div class="widget-box transparent">
                                    <div class="widget-body">
                                        <div class="widget-main no-padding">
                                            <div class="clearfix">
                                                <div class="pull-left">
                                                    <span class="orange2 bolder">Haloween party</span>

                                                    Lots of fun at Haloween party.
                                                    <br>
                                                    Take a look at some pics:
                                                </div>

                                                <div class="pull-right">
                                                    <i class="ace-icon fa fa-chevron-left blue bigger-110"></i>

                                                    &nbsp;
                                                    <img alt="Image 4" width="36"
                                                         src="${ctx}static/ace/assets/images/gallery/thumb-4.jpg">
                                                    <img alt="Image 3" width="36"
                                                         src="${ctx}static/ace/assets/images/gallery/thumb-3.jpg">
                                                    <img alt="Image 2" width="36"
                                                         src="${ctx}static/ace/assets/images/gallery/thumb-2.jpg">
                                                    <img alt="Image 1" width="36"
                                                         src="${ctx}static/ace/assets/images/gallery/thumb-1.jpg">
                                                    &nbsp;
                                                    <i class="ace-icon fa fa-chevron-right blue bigger-110"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="timeline-item clearfix">
                                <div class="timeline-info">
                                    <span class="timeline-date">9:00 am</span>

                                    <i class="timeline-indicator btn btn-success no-hover"></i>
                                </div>

                                <div class="widget-box transparent">
                                    <div class="widget-body">
                                        <div class="widget-main no-padding">
                                            Anim pariatur cliche reprehenderit, enim eiusmod
                                            <span class="pink2 bolder">high life</span>
                                            accusamus terry richardson ad squid …
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="timeline-item clearfix">
                                <div class="timeline-info">
                                    <span class="timeline-date">9:00 am</span>

                                    <i class="timeline-indicator btn btn-success no-hover"></i>
                                </div>

                                <div class="widget-box transparent">
                                    <div class="widget-body">
                                        <div class="widget-main no-padding"> Going to cafe for lunch</div>
                                    </div>
                                </div>
                            </div>

                            <div class="timeline-item clearfix">
                                <div class="timeline-info">
                                    <span class="timeline-date">9:00 am</span>

                                    <i class="timeline-indicator btn btn-success no-hover"></i>
                                </div>

                                <div class="widget-box transparent">
                                    <div class="widget-body">
                                        <div class="widget-main no-padding">
                                            <span class="red bolder">Critical security patch released</span>

                                            <br>
                                            Please download the new patch for maximum security.
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- /.timeline-items -->
                    </div><!-- /.timeline-container -->

                    <div class="timeline-container timeline-style2">
												<span class="timeline-label">
													<b>May 17</b>
												</span>

                        <div class="timeline-items">
                            <div class="timeline-item clearfix">
                                <div class="timeline-info">
                                    <span class="timeline-date">9:00 am</span>

                                    <i class="timeline-indicator btn btn-grey no-hover"></i>
                                </div>

                                <div class="widget-box transparent">
                                    <div class="widget-body">
                                        <div class="widget-main no-padding">
                                            <span class="bolder blue">Lorum Ipsum</span>
                                            Anim pariatur cliche reprehenderit, enim eiusmod
                                            <span class="purple bolder">high life</span>
                                            accusamus terry richardson ad squid …
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- /.timeline-items -->
                    </div><!-- /.timeline-container -->
                </div>
            </div>
        </div>

        <!-- PAGE CONTENT ENDS -->
    </div><!-- /.col -->
</div>

<div id="collection" class="row app-module">
    <div class="col-xs-12 col-sm-3">
        <ul>
            <li><a href="#"><img src="${ctx}static/app/imgs/003.jpeg"/></a></li>
            <li><a href="#"><img src="${ctx}static/app/imgs/003.jpeg"/></a></li>
            <li><a href="#"><img src="${ctx}static/app/imgs/003.jpeg"/></a></li>
            <li><a href="#"><img src="${ctx}static/app/imgs/003.jpeg"/></a></li>
            <li><a href="#"><img src="${ctx}static/app/imgs/003.jpeg"/></a></li>
            <li><a href="#"><img src="${ctx}static/app/imgs/003.jpeg"/></a></li>
        </ul>
    </div>
    <div class="col-xs-12 col-sm-6">
        <ul>
            <li><a href="#"><img src="${ctx}static/app/imgs/003.jpeg"/></a></li>
            <li><a href="#"><img src="${ctx}static/app/imgs/003.jpeg"/></a></li>
            <li><a href="#"><img src="${ctx}static/app/imgs/003.jpeg"/></a></li>
        </ul>
    </div>
    <div class="col-xs-12 col-sm-3">
        <ul>
            <li><a href="#"><img src="${ctx}static/app/imgs/003.jpeg"/></a></li>
            <li><a href="#"><img src="${ctx}static/app/imgs/003.jpeg"/></a></li>
            <li><a href="#"><img src="${ctx}static/app/imgs/003.jpeg"/></a></li>
            <li><a href="#"><img src="${ctx}static/app/imgs/003.jpeg"/></a></li>
            <li><a href="#"><img src="${ctx}static/app/imgs/003.jpeg"/></a></li>
            <li><a href="#"><img src="${ctx}static/app/imgs/003.jpeg"/></a></li>
        </ul>
    </div>

    <a href="#" class="btn btn-success">
        <i class="ace-icon fa fa-refresh bigger-110"></i>
        加载更多
    </a>
</div>

</@override>

<@override name="script">

</@override>

<@extends name="../layout.ftl"/>
