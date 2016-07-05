$(function () {
    $('#form').validate({
        rules: {
            mobile: {
                required: false,
                maxlength: 100
            },
            sign: {
                required: false,
                maxlength: 100
            }
        },
        submitHandler: function (form) {
            form.submit();
        }
    });

});
