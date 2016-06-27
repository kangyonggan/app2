<#assign modal_title="正在评论来自${article.realname}的${article.categoryName}" />

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post" enctype="multipart/form-data" action="${ctx}article/reply">
<@c.textarea id="content" label="评论内容" rows="8" empty="随手评论一句吧..." required="true"/>
    <input type="hidden" name="articleId" value="${article.id}"/>
</form>
</@override>

<@override name="modal-footer">
<button class="btn skin-btn" data-toggle="modal-submit" data-loading-text="正在提交...">
    <i class="ace-icon fa fa-check bigger-110"></i>
${submit}
</button>

<script src="${ctx}static/app/js/web/article/reply-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>