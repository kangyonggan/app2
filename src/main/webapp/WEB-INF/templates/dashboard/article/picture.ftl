<div class="row">
    <div class="col-xs-12">
        <div>
            <div class="space-10"></div>
            <form action="${ctx}file/upload" class="dropzone" id="dropzone">
                <div class="fallback">
                    <input name="file" type="file" multiple=""/>
                </div>
            </form>
            <div class="space-10"></div>
        </div>
    </div>
</div>

<div class="row">
    <form action="${ctx}dashboard/article/save/pics" class="form-horizontal" role="form" id="form" method="post">
    <@c.input id="title" label="标题" empty="描述一下相册吧" required="true"/>
    <@c.input id="password" label="密码" val="${article.password!''}" empty="查看文章的密码"/>
        <input type="hidden" name="ids" id="ids" value=""/>
        <input type="hidden" name="body" value=""/>
        <input type="hidden" name="categoryCode" value="${category.code}"/>
        <input type="hidden" name="categoryName" value="${category.name}"/>
    <#include "form-actions.ftl"/>
    </form>
</div>
