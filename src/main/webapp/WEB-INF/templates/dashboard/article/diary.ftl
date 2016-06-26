<div class="form-group">
    <div class="col-xs-12">
        <label class="col-xs-12 align-left">标题<span class="red">&nbsp;*</span></label>

        <input id="title" name="title" value="${article.title!''}" class="form-control" placeholder="有标题可以让别人更容易看到..."/>

        <label class="error hide" for="title"></label>
    </div>
</div>

<div class="form-group">
    <div class="col-xs-12">
        <label class="col-xs-12 align-left">内容<span class="red">&nbsp;*</span></label>

        <textarea id="body" rows="16" name="body" class="form-control" placeholder="随手写一句吧...">${article.body!''}</textarea>

        <label class="error hide" for="body"></label>
    </div>
</div>
