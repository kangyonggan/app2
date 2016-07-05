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

});
