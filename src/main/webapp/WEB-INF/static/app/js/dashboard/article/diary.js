$(function () {
    $('#form').validate({
        rules: {
            title: {
                required: true,
                maxlength: 100
            },
            summary: {
                maxlength: 200
            }
        },
        submitHandler: function (form) {
            var value = $.trim($(document.getElementsByTagName("iframe")[0].contentWindow.document.body).html());
            if (value == "") {
                Notify.warning("正文必须填写！");
                return;
            }
            $("#body").text(value);
            form.submit();
        }
    });

    KindEditor.ready(function (K) {
        window.editor = K.create('#body', {
            uploadJson: ctx + 'file/editor',
            fileManagerJson: ctx + 'file/manager'
        });
    });

});
