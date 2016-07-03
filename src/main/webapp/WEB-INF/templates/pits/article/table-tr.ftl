<tr id="article-${item.id}">
    <td>${item.id}</td>
    <td title="${item.title}"><@c.substring str="${item.title}" len=25/></td>
    <td>${item.top}/${item.low}/${item.star}</td>
    <td>${(item.isDeleted==0)?string('未删除', '已删除')}</td>
    <td>${item.createdTime?datetime}</td>
    <td>
        <div class="btn-group">
            <a target="_blank" class="btn btn-xs skin-btn" href="${ctx}article?id=${item.id}">
                <i class="ace-icon fa fa-eye bigger-120"></i>
            </a>
            <a class="btn btn-xs btn-danger delete-item" title="${item.title}"
               data-url="${ctx}pits/article/delete?id=${item.id}">
                <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </a>
        </div>
    </td>
</tr>