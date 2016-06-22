$(function () {
    $('#modal-form').validate({
        rules: {
            email: {
                required: true,
                email: true,
                maxlength: 64,
                remote: {
                    url: ctx + "sys/user/verify-email",
                    type: 'post',
                    data: {
                        'email': $('#email').val(),
                        'oldEmail': $("#old_email").val()
                    }
                }
            },
            realname: {
                required: true,
                rangelength: [1, 32],
                isChineseAndEnglish: true
            },
            password: {
                required: true,
                isPassword: true
            },
            rePassword: {
                required: true,
                equalTo: "#password"
            },
            mobile: {
                isMobile: true,
                remote: {
                    url: ctx + "sys/user/verify-mobile",
                    type: 'post',
                    data: {
                        'mobile': $('#mobile').val(),
                        'oldMobile': $("#old_mobile").val()
                    }
                }
            },
            sign: {
                maxlength: 64
            }
        }
    });
});
