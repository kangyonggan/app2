<tr id="article-${item.id}">
    <td>${item.id}</td>
    <td>${item.title}</td>
    <td>${item.top}/${item.low}</td>
    <td>${item.createdTime?datetime}</td>
    <td>
        <div class="hidden-sm hidden-xs btn-group">
            <a class="btn btn-xs skin-btn" href="${ctx}article/${item.id}">
                <i class="ace-icon fa fa-eye bigger-120"></i>
            </a>

            <a class="btn btn-xs btn-danger delete-item" title="${item.title}" data-url="${ctx}article/${item.id}/delete">
                <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </a>
        </div>
    </td>
</tr>