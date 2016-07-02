<@c.input id="title" label="标题" val="${article.title!''}" empty="有标题可以让别人更容易看到..." required="true"/>

<input type="hidden" name="body" value=""/>
<div class="hr hr-18 dotted"></div>

<div class="form-group">
    <label class="col-xs-10 col-xs-offset-1 pull-left">视频<span class="red">*</span></label>

    <div class="col-xs-10 col-xs-offset-1">
        <div id="form-attachments">
            <input type="file" name="attachment[]" />
        </div>
    </div>
</div>

<div class="align-right">
    <button id="id-add-attachment" type="button" class="btn btn-sm btn-danger">
        <i class="ace-icon fa fa-paperclip bigger-140"></i>
        添加更多
    </button>
</div>
