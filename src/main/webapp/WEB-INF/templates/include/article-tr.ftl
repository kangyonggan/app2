<div class="widget-main no-padding-bottom">
    <div>
        <div>
            <a href="${ctx}user?id=${article.userId}">
            <#if article.smallAvatar == ''>
                <img class="pull-left user-avator"
                     src="${ctx}static/ace/dist/avatars/avatar5.png"/>
            <#else>
                <img class="pull-left user-avator" src="${ctx}${article.smallAvatar}"/>
            </#if>
            </a>
            <h4>
                <a class="dark" href="${ctx}user?id=${article.userId}"> ${article.realname} </a>
                <small><@c.substring str="${article.sign}" len=30 default="懒惰是一种美德"/></small>
            </h4>
            <small>
                <div class="time">
                    <i class="ace-icon fa fa-clock-o bigger-110"></i>
                ${article.createdTime?datetime}
                </div>
            </small>
        </div>
        <div class="space-6"></div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <p class="text-article">
            <#if article.categoryCode=="word">
                <h4><a href="${ctx}article?id=${article.id}" class="dark no-text-decoration">${article.body}</a></h4>
            <#else>
                <h4><a href="${ctx}article?id=${article.id}" class="dark no-text-decoration">${article.title}</a></h4>
            </#if>
            </p>
            <div class="space-16"></div>
        </div>

        <div class="article-action">
            <div class="article-buttons">
                <a href="${ctx}article/reply?id=${article.id}" class="skin-color article-reply" data-toggle="modal"
                   data-target="#myModal">
                    <i class="ace-icon fa fa-comment-o"></i>
                    评论(${article.reply})
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
                <span class="split">|</span>
                <a href="javascript:" class="skin-color">
                    . . .
                </a>
            </div>
        </div>
    </div>
</div>