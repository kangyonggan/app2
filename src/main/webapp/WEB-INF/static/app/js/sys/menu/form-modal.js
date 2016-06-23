$(function () {

    $('#modal-form').validate({
        rules: {
            code: {
                required: true,
                maxlength: 64,
                remote: {
                    url: ctx + "sys/menu/verify-code",
                    type: 'post',
                    data: {
                        'code': $("code").val(),
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