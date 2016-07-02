<@c.input id="title" label="标题" val="${article.title!''}" empty="有标题可以让别人更容易看到..." required="true"/>
<@c.input id="summary" label="摘要" val="${article.summary!''}" empty="用简短的话概括主要内容..."/>

<div class="form-group">
    <label class="col-xs-10 col-xs-offset-1 align-left">
        内容<span class="red">&nbsp;*</span>
        <a href="#" class="btn btn-sm pull-right skin-btn md-prev">
            <i class="fa fa-eye"></i>
            预览
        </a>
    </label>

    <div class="col-xs-10 col-xs-offset-1 ">
    <span class="block input-icon input-icon-right">
        <textarea name="body" id="md-body" rows="10" class="width-100"></textarea>
        <div class="fa-border hide width-100" id="md-prev-content"></div>
            <i class="ace-icon fa fa-times-circle hide"></i>
        <label class="error hide" for="md-body"></label>
    </span>
    </div>
</div>
