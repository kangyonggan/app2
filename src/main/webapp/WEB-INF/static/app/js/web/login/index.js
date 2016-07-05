$(function () {
    function validElement($this, element) {
        $(element).valid();
        if ($this.element(element)) {
            var $parent = $(element).parents(".form-group").removeClass("has-error");
            $parent.find(".error").addClass("hide");
            $parent.find(".fa-times-circle").addClass("hide");
        }
    }

    var $form = $("#login-form");

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
        onkeyup: function (element) {
            validElement(this, element);
        },
        onfocusout: function (element) {
            validElement(this, element);
        },
        showErrors: function (errorMap, errorList) {
            for (var i = 0; i < errorList.length; i++) {
                var $parent = $(errorList[i].element).parents(".form-group").addClass("has-error");
                $parent.find(".error").removeClass("hide").text(errorList[i].message);
                $parent.find(".fa-times-circle").removeClass("hide");
            }
        },
        submitHandler: function () {
            $form.ajaxSubmit({
                dataType: 'json',
                success: function (response) {
                    if (response.status == 'fail') {
                        Notify.error(response.message);
                        $("button[data-loading-text]").button('reset');
                    } else {
                        window.location.href = response.message;
                    }
                },
                error: function () {
                    Notify.error("服务器内部错误，请稍后再试。");
                    $("button[data-loading-text]").button('reset');
                }
            });
        }
    });
});