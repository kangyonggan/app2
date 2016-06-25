$(".items-table").on('click', 'a[data-role=item-status]', function () {
    var $this = $(this);
    var url = $this.data('url');
    $.get(url, function (html) {
        var $tr = $(html);
        $('#' + $tr.attr('id')).replaceWith($tr);
    })
});