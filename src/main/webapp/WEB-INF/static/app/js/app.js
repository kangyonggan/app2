$(function () {

    $(".delete-item").click(function () {
        var $trigger = $(this);
        var url = $trigger.data('url');
        var title = $trigger.attr("title");
        $.messager.confirm("警告", "确定删除" + title + "吗?", function () {
            $.get(url, function () {
                if ($trigger.parents('tr')) {
                    $trigger.parents('tr').remove();
                }
            }).success(function () {
                Notify.success("删除成功");
            })
        });
    });

    $('.modal').on('click', '[data-toggle=modal-submit]', function () {
        $('#modal-form').submit();
    });

    $(document).on('hidden.bs.modal', '.modal', function () {
        $(this).removeData('bs.modal');
    });

    //配置jQuery.validator默认的处理方法
    jQuery.validator.setDefaults({
        onkeyup: function (element) {
            validElement(this, element);
        },
        onfocusout: function (element) {
            validElement(this, element);
        },
        showErrors: function (errorMap, errorList) {
            for (var i = 0; i < errorList.length; i++) {
                var $parent = $(errorList[i].element).parents(".form-group").addClass("has-error");
                $parent.find(".fa-times-circle").removeClass("hide");
                $parent.find(".error").removeClass("hide").text(errorList[i].message);
            }
        },
        submitHandler: function (form) {
            var $form = $("#modal-form");
            var $modal = $form.parents('.modal');
            $form.ajaxSubmit({
                dataType: 'json',
                success: function (response) {
                    if (response.status == 'fail') {
                        Notify.error("操作失败。");
                    } else {
                        $modal.modal('hide');
                        window.location.reload();
                    }
                },
                error: function () {
                    Notify.error("服务器内部错误，请稍后再试。");
                }
            });
        }
    });

    function validElement($this, element) {
        $(element).valid();
        if ($this.element(element)) {
            var $parent = $(element).parents(".form-group").removeClass("has-error");
            $parent.find(".fa-times-circle").addClass("hide");
            $parent.find(".error").addClass("hide");
        }
    }

    $.messager.model = {
        cancel: {text: "取消", classed: 'btn-default'},
        ok: {text: "确定", classed: 'btn-success'}
    };
});

var last_gritter;
var showMessage = function (type, message) {
    if (last_gritter) {
        $.gritter.remove(last_gritter);
    }
    last_gritter = $.gritter.add({
        title: '通知',
        text: message,
        class_name: type
    });
};

var Notify = {
    success: function (message) {
        showMessage('gritter-success', message);
    },

    warning: function (message) {
        showMessage('gritter-warning', message);
    },

    error: function (message) {
        showMessage('gritter-error', message);
    },

    info: function (message) {
        showMessage('gritter-info', message);
    }
};

