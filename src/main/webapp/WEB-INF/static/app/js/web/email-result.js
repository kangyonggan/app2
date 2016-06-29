$(function () {
    $('#form').validate({
        rules: {
            password: {
                required: true,
                isPassword: true
            },
            rePassword: {
                required: true,
                equalTo: "#password"
            }
        },
        submitHandler: function (form) {
            form.submit();
        }
    });

    $(".form-submit").click(function () {
        $('#form').submit();
    });

    $("#resend").click(function () {
        $.get($(this).attr("href"), function (result) {
            if (result.status == "success") {
                Notify.success("重发邮件成功， 请前往邮箱查看！");
            } else {
                Notify.warning(result.message);
            }
        });
        return false;
    });
});
