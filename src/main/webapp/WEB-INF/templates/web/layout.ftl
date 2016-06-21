<@override name="app-style">
    <@block name="style"/>
</@override>

<@override name="app-content">
    <@shiro.user>
        <#include "../settings.ftl">
    </@shiro.user>

    <@block name="content"/>

<div class="space-6"></div>

<div class="row">
    <#include "sidebar-left.ftl">

    <#include "sidebar-right.ftl">

    <div class="col-xs-12 col-sm-6">

        <#include "../markdown.ftl">

        <div class="space-6"></div>

        <div class="widget-box">
            <div class="widget-header widget-header-flat">
                <h4 class="widget-title smaller dark">全部动态</h4>

                <div class="widget-toolbar">
                    <label>
                        <small class="green">
                            <b>图片过滤</b>
                        </small>

                        <input id="id-check-horizontal" type="checkbox" class="ace ace-switch ace-switch-6">
                        <span class="lbl middle"></span>
                    </label>
                </div>
            </div>

            <div class="widget-body">
                <div class="widget-main no-padding-bottom">
                    <div>
                        <div>
                            <a href="#">
                                <img class="pull-left user-avator" src="${ctx}static/ace/dist/avatars/avatar4.png"/>
                            </a>
                            <h4>
                                <a class="dark" href="#"> 康永敢 </a>
                                <small>不死总会成功</small>
                            </h4>
                            <small>
                                <div class="time">
                                    <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                    2016年06月20日 19:33:25
                                </div>
                            </small>
                        </div>
                    </div>

                    <div class="space-4"></div>

                    <div class="row">
                        <div class="article-imgs-list">
                            <div class="col-sm-6">
                                <img src="${ctx}static/ace/dist/images/gallery/image-1.jpg"/>
                            </div>
                            <div class="col-sm-6">
                                <img src="${ctx}static/ace/dist/images/gallery/image-1.jpg"/>
                            </div>
                        </div>

                        <div class="col-sm-12">
                            <p class="text-article">
                                这是我的晚餐, 我一个人点了两份, 哈哈哈!!!<br/>不要问我为什么, 因为...
                            </p>
                        </div>

                        <div class="article-action">
                            <div class="article-buttons">
                                <a href="#">
                                    <i class="ace-icon fa fa-comment-o"></i>
                                    评论
                                </a>
                                <span class="split">|</span>
                                <a href="#">
                                    <i class="ace-icon fa fa-external-link"></i>
                                    转发
                                </a>
                                <span class="split">|</span>
                                <a href="#">
                                    <i class="ace-icon fa fa-heart-o"></i>
                                    赞
                                </a>
                                <span class="split">|</span>
                                <a href="#">
                                    <i class="ace-icon fa fa-star-o"></i>
                                    收藏
                                </a>
                                <span class="split">|</span>
                                <a href="#">
                                    . . .
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="space-6"></div>

        <div class="widget-box">
            <div class="widget-body">
                <div class="widget-main no-padding-bottom">
                    <div>
                        <div>
                            <a href="#">
                                <img class="pull-left user-avator" src="${ctx}static/ace/dist/avatars/avatar4.png"/>
                            </a>
                            <h4>
                                <a class="dark" href="#"> 康永敢 </a>
                                <small>不死总会成功</small>
                            </h4>
                            <small>
                                <div class="time">
                                    <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                    2016年06月19日 09:13:54
                                </div>
                            </small>
                        </div>
                    </div>

                    <div class="space-4"></div>

                    <div class="row">
                        <div class="article-imgs-list">
                            <div class="col-sm-6">
                                <img src="${ctx}static/ace/dist/images/gallery/image-1.jpg"/>
                            </div>
                            <div class="col-sm-6">
                                <img src="${ctx}static/ace/dist/images/gallery/image-1.jpg"/>
                            </div>
                        </div>

                        <div class="col-sm-12">
                            <p class="text-article">
                                这是我的晚餐, 我一个人点了两份, 哈哈哈!!!<br/>不要问我为什么, 因为...
                            </p>
                        </div>

                        <div class="article-action">
                            <div class="article-buttons">
                                <a href="#">
                                    <i class="ace-icon fa fa-comment-o"></i>
                                    评论
                                </a>
                                <span class="split">|</span>
                                <a href="#">
                                    <i class="ace-icon fa fa-external-link"></i>
                                    转发
                                </a>
                                <span class="split">|</span>
                                <a href="#">
                                    <i class="ace-icon fa fa-heart-o"></i>
                                    赞
                                </a>
                                <span class="split">|</span>
                                <a href="#">
                                    <i class="ace-icon fa fa-star-o"></i>
                                    收藏
                                </a>
                                <span class="split">|</span>
                                <a href="#">
                                    . . .
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</@override>

<@override name="app-script">
    <@block name="script"/>
</@override>

<@extends name="../layout.ftl"/>
