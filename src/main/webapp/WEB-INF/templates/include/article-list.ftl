<div>
    <div class="widget-box skin-border">
        <div class="widget-header widget-header-flat skin-bg">
            <h4 class="widget-title smaller skin-inverse">
            <#if category.icon??>
                <i class="${category.icon}"></i>
            <#else>
                <i class="ace-icon fa fa-th"></i>
            </#if>
            ${category.name}
            </h4>
        </div>

    <#if articles?size == 0>
        <div class="empty">此栏目没有文章</div>
    <#else>
        <div class="widget-body skin-border">
            <div class="widget-main no-padding-bottom">
                <div>
                    <div>
                        <a href="${ctx}user/${articles[0].userId}">
                            <#if articles[0].smallAvatar == ''>
                                <img class="pull-left user-avator" src="${ctx}static/ace/dist/avatars/avatar5.png"/>
                            <#else>
                                <img class="pull-left user-avator" src="${ctx}${articles[0].smallAvatar}"/>
                            </#if>
                        </a>
                        <h4>
                            <a class="dark" href="#"> ${articles[0].realname} </a>
                            <small><@c.substring str="${articles[0].sign}" len=30 default="懒惰是一种美德!"/></small>
                        </h4>
                        <small>
                            <div class="time">
                                <i class="ace-icon fa fa-clock-o bigger-110"></i>
                            ${articles[0].createdTime?datetime}
                            </div>
                        </small>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-12">
                        <p class="text-article">
                        <h4>${articles[0].title}</h4>
                        </p>
                        <div class="space-6"></div>
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
    </#if>
    </div>

<#list articles as article>
    <#if article_index gt 0>
        <div class="space-10"></div>

        <div class="widget-box skin-border">
            <div class="widget-body">
                <div class="widget-main no-padding-bottom">
                    <div>
                        <div>
                            <a href="${ctx}user/${article.userId}">
                                <#if article.smallAvatar == ''>
                                    <img class="pull-left user-avator"
                                         src="${ctx}static/ace/dist/avatars/avatar5.png"/>
                                <#else>
                                    <img class="pull-left user-avator" src="${ctx}${article.smallAvatar}"/>
                                </#if>
                            </a>
                            <h4>
                                <a class="dark" href="#"> ${article.realname} </a>
                                <small><@c.substring str="${article.sign}" len=30 default="懒惰是一种美德!"/></small>
                            </h4>
                            <small>
                                <div class="time">
                                    <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                ${article.createdTime?datetime}
                                </div>
                            </small>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <p class="text-article">
                            <h4>${article.title}</h4>
                            </p>
                            <div class="space-6"></div>
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
    </#if>
</#list>

    <div class="space-10"></div>

    <div class="widget-box">
        <div class="widget-body">
            <div class="widget-main center no-padding">
                <h5>
                    <strong>
                        <i class="ace-icon fa fa-spinner fa-spin orange bigger-125"></i>
                        加载中. . .
                    </strong>
                </h5>
            </div>
        </div>
    </div>

    <div class="space-10"></div>
</div>