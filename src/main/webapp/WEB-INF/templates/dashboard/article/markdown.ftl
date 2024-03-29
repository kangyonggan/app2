<@c.input id="title" label="标题" val="${article.title!''}" empty="有标题可以让别人更容易看到..." required="true"/>
<@c.input id="summary" label="摘要" val="${article.summary!''}" empty="用简短的话概括主要内容..."/>

<div class="form-group">
    <label class="col-xs-10 col-xs-offset-1 align-left">
        内容<span class="red">&nbsp;*</span>
    </label>

    <div class="col-xs-10 col-xs-offset-1 markdown">
        <span class="block input-icon input-icon-right">
            <#if article.body??>
                <textarea name="body" id="md-body" rows="10"
                          class="form-control">${article.body?replace('</textarea>', '&lt;textarea&gt;')}</textarea>
            <#else>
                <textarea name="body" id="md-body" rows="10" class="form-control"></textarea>
            </#if>

                <label class="error hide" for="md-body"></label>
        </span>
    </div>
</div>

<#if !article.id??>
<div class="hr hr-18 dotted"></div>

<div class="form-group">
    <label class="col-xs-10 col-xs-offset-1 pull-left">文件</label>

    <div class="col-xs-10 col-xs-offset-1">
        <div id="form-attachments">
            <input type="file" name="attachment[]"/>
        </div>
    </div>
</div>

<div class="align-right">
    <button id="id-add-attachment" type="button" class="btn btn-sm btn-danger">
        <i class="ace-icon fa fa-paperclip bigger-140"></i>
        添加更多
    </button>
</div>
</#if>
