$(function () {
    $('#modal-form').validate({
        rules: {
            code: {
                required: true,
                maxlength: 64,
                remote: {
                    url: ctx + "sys/role/verify-code",
                    type: 'post',
                    data: {
                        'code': function () {
                            return $('#code').val();
                        },
                        'oldCode': function () {
                            return $("#old_code").val();
                        }
                    }
                }
            },
            name: {
                required: true,
                rangelength: [1, 32]
            }
        }
    });
});
