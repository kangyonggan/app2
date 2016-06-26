<tr id="article-${item.id}">
    <td>${item.id}</td>
    <td title="${item.title}"><@c.substring str="${item.title}" len=25/></td>
    <td>${item.top}/${item.low}</td>
    <td>${item.createdTime?datetime}</td>
    <td>
        <div class="btn-group">
            <a class="btn btn-xs skin-btn" href="${ctx}article/${item.id}">
                <i class="ace-icon fa fa-eye bigger-120"></i>
            </a>
            <a class="btn btn-xs skin-btn" href="${ctx}dashboard/article/${item.id}/edit">
                <i class="ace-icon fa fa-pencil bigger-120"></i>
            </a>
            <a class="btn btn-xs btn-danger delete-item" title="${item.title}" data-url="${ctx}dashboard/article/${item.id}/delete">
                <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </a>
        </div>
    </td>
</tr>