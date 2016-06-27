<#assign no_right="">
<#assign title="${article.title}">

<@override name="content">
<div class="widget-box transparent">
    <div class="widget-header">
        <div class="article-buttons pull-left">
            <a href="javascript:history.back(-1);" class="skin-color">
                <i class="ace-icon fa fa-arrow-left"></i>
                返回
            </a>
            <a href="${ctx}article/reply?id=${article.id}" class="skin-color">
                <i class="ace-icon fa fa-comment-o"></i>
                评论(1)
            </a>
            <span class="split">|</span>
            <a href="#" class="skin-color">
                <i class="ace-icon fa fa-external-link"></i>
                转发(2)
            </a>
            <span class="split">|</span>
            <a href="${ctx}article/top?id=${article.id}" class="skin-color action">
                <i class="ace-icon fa fa-upload"></i>
                顶(<span class="attion-count">${article.top}</span>)
            </a>
            <span class="split">|</span>
            <a href="${ctx}article/low?id=${article.id}" class="skin-color action">
                <i class="ace-icon fa fa-download"></i>
                踩(<span class="attion-count">${article.low}</span>)
            </a>
            <span class="split">|</span>
            <a href="#" class="skin-color">
                <i class="ace-icon fa fa-star-o"></i>
                收藏(19)
            </a>
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

            <h2 class="text-center">
                <i class="ace-icon fa fa-leaf green"></i>
            ${article.title}
            </h2>

            <div class="space-6"></div>

            <div class="hr hr8 hr-dotted"></div>

            <div class="space-10"></div>

            <div class="well">
                摘要:
                <#if article.summary==''>
                    无
                <#else>
                ${article.summary}
                </#if>
            </div>

            <div class="space-10"></div>

            <div class="row">
                ${article.body}
            </div>
        </div>
    </div>
</div>
</@override>

<@override name="script">

</@override>

<@extends name="../../app-layout.ftl"/>
