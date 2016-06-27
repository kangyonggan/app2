$(function () {
    var $form = $("#modal-form");

    $form.validate({
        rules: {
            email: {
                required: true,
                email: true,
                maxlength: 64
            },
            password: {
                required: true,
                isPassword: true
            },
            captcha: {
                required: true
            }
        },
        messages: {
            captcha: {
                remote: "验证码错误!"
            }
        },
        submitHandler: function () {
            $form.ajaxSubmit({
                dataType: 'json',
                success: function (response) {
                    if (response.status == 'fail') {
                        Notify.error(response.message);
                    } else {
                        window.location.reload();
                    }
                },
                error: function () {
                    Notify.error("服务器内部错误，请稍后再试。");
                }
            });
            return false;
        }
    });
});