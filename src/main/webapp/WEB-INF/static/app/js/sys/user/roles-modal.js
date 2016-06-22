$(function () {
    var $form = $("#modal-form");
    var $modal = $form.parents('.modal');

    $('#submit').click(function() {
        $form.ajaxSubmit({
            dataType: 'json',
            success: function (response) {
                if (response.status == 'fail') {
                    Notify.error("操作失败。");
                } else {
                    $modal.modal('hide');
                    Notify.success("操作成功。");
                }
            },
            error: function () {
                Notify.error("服务器内部错误，请稍后再试。");
            }
        })
    });
});
