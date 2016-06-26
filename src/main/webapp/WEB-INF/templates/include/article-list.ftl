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
        <div class="empty">没有此栏目的文章</div>
    <#else>
        <div class="widget-body skin-border">
            <#assign article=articles[0]/>
            <#include "article-tr.ftl"/>
        </div>
    </#if>
    </div>

<#list articles as article>
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