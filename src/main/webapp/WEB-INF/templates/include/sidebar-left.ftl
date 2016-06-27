<div class="widget-box skin-border">
    <div class="widget-header widget-header-flat skin-bg skin-inverse">
        <i class="ace-icon fa fa-bullhorn bigger-140"></i>
        <h4 class="widget-title">热门推荐</h4>
    </div>

    <div class="widget-body">
        <div class="widget-main">
            <ul class="sidebar-list">
                <li>
                    <a href="${ctx}category/list">
                        <i class="ace-icon fa fa-th dark"></i>
                        全部栏目
                    </a>
                </li>
                <li></li>
            <@shiro.user>
                <li class="active">
                    <a href="#">
                        <i class="ace-icon fa fa-rss"></i>
                        我的订阅
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="ace-icon fa fa-bell purple"></i>
                        与我相关
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="ace-icon fa fa-heart red"></i>
                        特别关心
                    </a>
                </li>
                <li></li>
            </@shiro.user>
            <#assign colors=["dark", "purple", "green", "red2"]/>
            <#list app_category.childrens as category>
                <#assign hasChildren=category.childrens?size gt 0>
                <#if hasChildren>
                    <#list category.childrens as c>
                        <li>
                            <a href="${ctx}category/list?code=${c.code}">
                                <i class="${c.icon} ${colors[c_index%colors?size]}"></i>
                            ${c.name}
                            </a>
                        </li>
                    </#list>
                    <li></li>
                </#if>
            </#list>
            </ul>
        </div>
    </div>
</div>
