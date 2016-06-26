<#assign no_right="">
<#assign title="${article.title}">

<@override name="content">
<div class="widget-box skin-border">
    <div class="widget-body">
        <div class="widget-main no-padding-bottom">
            <div class="row">
                <div class="col-sm-12">
                    <p class="text-article">
                    <div class="pull-left">
                        <a href="javascript:history.back()" class="no-text-decoration skin-color">
                            <h4>
                                <i class="ace-icon fa fa-arrow-left"></i>
                                返回
                            </h4>
                        </a>
                    </div>
                    <h3 class="center">
                        <@c.substring str="${article.title}" len=30/>
                    </h3>
                    <div class="center">
                        康永敢
                        <em>${article.createdTime?datetime}</em>
                    </div>
                    </p>
                    <div class="space-10"></div>
                    <hr>
                    <div class="space-10"></div>
                    <div>
                    ${article.body}
                        <div class="space-20"></div>
                    </div>
                </div>

                <div class="article-action">
                    <div class="article-buttons">
                        <a href="#" class="skin-color">
                            <i class="ace-icon fa fa-comment-o"></i>
                            评论
                        </a>
                        <span class="split">|</span>
                        <a href="#" class="skin-color">
                            <i class="ace-icon fa fa-external-link"></i>
                            转发
                        </a>
                        <span class="split">|</span>
                        <a href="#" class="skin-color">
                            <i class="ace-icon fa fa-heart-o"></i>
                            赞
                        </a>
                        <span class="split">|</span>
                        <a href="#" class="skin-color">
                            <i class="ace-icon fa fa-star-o"></i>
                            收藏
                        </a>
                        <span class="split">|</span>
                        <a href="#" class="skin-color">
                            . . .
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</@override>

<@override name="script">

</@override>

<@extends name="../../app-layout.ftl"/>
