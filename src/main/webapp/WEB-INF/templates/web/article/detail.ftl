<#assign no_right="">
<#assign title="${article.title}">

<@override name="content">
<div class="widget-box transparent">
    <div class="widget-header">
        <div class="article-buttons pull-left">
            <a href="${ctx}category/list?code=${article.categoryCode}" class="skin-color">
                <i class="ace-icon fa fa-arrow-left"></i>
                返回
            </a>
            <#include "../../include/article-buttons.ftl"/>
        </div>

        <div class="widget-toolbar invoice-info">
            <span class="invoice-info-label">来源:</span>
            <span class="red">${article.realname}</span>

            <br>
            <span class="invoice-info-label">时间:</span>
            <span>${article.createdTime?datetime}</span>
        </div>
    </div>

    <div class="widget-body">
        <div class="widget-main padding-24">

            <#if article.categoryCode!='word'>
                <h2 class="text-center">
                    <i class="ace-icon fa fa-leaf green"></i>
                ${article.title}
                </h2>

                <div class="space-6"></div>

                <div class="hr hr8 hr-dotted"></div>

                <div class="space-10"></div>

                <div class="row well">
                    摘要:
                    <#if article.summary==''>
                        无
                    <#else>
                    ${article.summary}
                    </#if>
                </div>

                <div class="space-10"></div>

            </#if>

            <div class="row">
            ${article.body}
            </div>

            <div class="space-10"></div>

            <div class="row">
                <#list replies as reply>
                    <div class="profile-activity clearfix">
                        <div>
                            <#if reply.smallAvatar == ''>
                                <img class="pull-left user-avator"
                                     src="${ctx}static/ace/dist/avatars/avatar5.png"/>
                            <#else>
                                <img class="pull-left user-avator" src="${ctx}${reply.smallAvatar}"/>
                            </#if>
                            <a class="user" href="#"> ${reply.realname} </a>
                            ${reply_index + 1} / F

                            <div class="time">
                                <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                ${reply.createdTime?datetime}
                            </div>

                            <div class="space-6"></div>

                            <p class="reply-content">
                            ${reply.content}
                            </p>
                        </div>

                        <div class="tools action-buttons">
                            <#if app_who=='1' && reply.userId==app_user.id>
                                <a href="${ctx}article/reply/delete?id=${reply.id}" class="red reply-delete" title="删除评论">
                                    <i class="ace-icon fa fa-times bigger-125"></i>
                                </a>
                            </#if>
                        </div>
                    </div>
                </#list>
            </div>

            <#if app_who=="1">
                <div class="row">
                    <form class="form-horizontal" role="form" id="form" method="post" enctype="multipart/form-data"
                          action="${ctx}article/reply2">
                        <input type="hidden" name="articleId" value="${article.id}"/>
                        <div class="form-group">
                            <label class="col-xs-12 align-left">评论内容<span class="red">&nbsp;*</span></label>
                            <div class="col-xs-12">
                        <span class="block input-icon input-icon-right">
                        <textarea id="content" rows="10" name="content" class="form-control"
                                  placeholder="我也来说一句..."></textarea>
                            <i class="ace-icon fa fa-times-circle hide"></i>
                            <label class="error hide" for="content"></label>
                        </span>
                            </div>
                        </div>
                        <#include "../../dashboard/article/form-actions.ftl">
                    </form>
                </div>
            <#else>
                <div class="row">
                    <a href="javascript:" class="article-reply">
                        <h4><i class="fa fa-lock bigger-130 skin-color"></i>&nbsp; 登录后评论</h4>
                    </a>
                </div>
            </#if>
        </div>
    </div>
</div>
</@override>

<@override name="script">
<script src="${ctx}static/libs/kindeditor/kindeditor-min.js"></script>
<script src="${ctx}static/libs/kindeditor/lang/zh_CN.js"></script>
<script src="${ctx}static/app/js/web/article/detail.js"></script>
</@override>

<@extends name="../../app-layout.ftl"/>
