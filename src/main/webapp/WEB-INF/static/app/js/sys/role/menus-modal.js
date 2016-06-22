$(function () {
    var $form = $('#modal-form');
    var $modal = $form.parents('.modal');

    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };

    $.fn.zTree.init($("#tree"), setting, zNodes);

    $("#submit").click(function() {
        $form.ajaxSubmit({
            beforeSubmit: function(arr, $form, options) {
                 var str = "";
                 var zTree = $.fn.zTree.getZTreeObj("tree");
                 var nodes = zTree.getCheckedNodes(true);
                 for (var i = 0; i < nodes.length; i++) {
                     if (str != "") {
                         str += ",";
                     }
                     str += nodes[i].id;
                 }
                 arr[0].value = str;
             },
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
