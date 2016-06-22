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
                        'code': $("code").val(),
                        'oldCode': $("#old_code").val()
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
