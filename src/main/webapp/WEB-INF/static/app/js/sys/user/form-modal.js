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
                        'email': function () {
                            return $('#email').val();
                        },
                        'isEdit': function () {
                            return isEdit;
                        }
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
                        'mobile': function () {
                            return $('#mobile').val();
                        },
                        'isEdit': function () {
                            return isEdit;
                        }
                    }
                }
            },
            sign: {
                maxlength: 64
            }
        }
    });
});
