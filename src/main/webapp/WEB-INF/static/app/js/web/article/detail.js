$(function () {
    $('#form').validate({
        rules: {
            content: {
                required: true
            }
        },
        submitHandler: function (form) {
            var value = $.trim($(document.getElementsByTagName("iframe")[0].contentWindow.document.body).html());
            if (value == "") {
                Notify.warning("正文必须填写！");
                return;
            }
            $("#content").text(value);
            form.submit();
        }
    });

    KindEditor.ready(function (K) {
        window.editor = K.create('#content', {
            uploadJson: ctx + 'file/editor',
            fileManagerJson: ctx + 'file/manager'
        });
    });

});
