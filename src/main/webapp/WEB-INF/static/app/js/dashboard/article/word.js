$(function () {
    $('#form').validate({
        rules: {
            password: {
                maxlength: 20
            }
        },
        submitHandler: function (form) {
            var value = $.trim($(document.getElementsByTagName("iframe")[0].contentWindow.document.body).html());
            if (value == "") {
                Notify.warning("内容必须填写！");
                setTimeout(function () {
                    $("button[data-loading-text]").button('reset');
                }, 1000);
                return;
            }
            $("#body").text(value);
            form.submit();
        }
    });

});
