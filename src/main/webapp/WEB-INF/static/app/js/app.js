var showMessage = function (type, message) {
    $.gritter.add({
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

$('.modal').on('click', '[data-toggle=modal-submit]', function () {
    $('#modal-form').submit();
});

$(document).on('hidden.bs.modal', '.modal', function () {
    $(this).removeData('bs.modal');
});