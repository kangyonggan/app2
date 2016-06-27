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

    <#if page.list?size == 0>
        <div class="empty">没有此栏目的文章</div>
    <#else>
        <div class="widget-body skin-border">
            <#assign article=page.list[0]/>
            <#include "article-tr.ftl"/>
        </div>
    </#if>
    </div>

<#list page.list as article>
    <#if article_index gt 0>
        <div class="space-10"></div>

        <div class="widget-box skin-border">
            <div class="widget-body">
                <#include "article-tr.ftl"/>
            </div>
        </div>
    </#if>
</#list>
<div class="space-10"></div>
</div>