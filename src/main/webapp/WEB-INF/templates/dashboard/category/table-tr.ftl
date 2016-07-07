<tr id="article-${item.id}">
    <td>${item.id}</td>
<#if category.code=="word">
    <td class="body-img">${item.body}</td>
<#else>
    <td title="${item.title}"><@c.substring str="${item.title}" len=25/></td>
</#if>
    <td>${item.top}/${item.low}/${item.star}</td>
    <td>${item.createdTime?datetime}</td>
    <td>
        <div class="btn-group">
            <a class="btn btn-xs skin-btn" href="${ctx}article?id=${item.id}">
                <i class="ace-icon fa fa-eye bigger-120"></i>
            </a>
        <#if item.categoryCode=="word" || item.categoryCode=="diary" || item.categoryCode=="note" || item.categoryCode=="course" || item.categoryCode=="share">
            <a class="btn btn-xs skin-btn" href="${ctx}dashboard/article/edit?id=${item.id}">
                <i class="ace-icon fa fa-pencil bigger-120"></i>
            </a>
        </#if>
            <a class="btn btn-xs btn-danger delete-item" title="${item.title}"
               data-url="${ctx}dashboard/article/delete?id=${item.id}">
                <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </a>
        </div>
    </td>
</tr>