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

    console.log("222");

    $(".form-submit").click(function () {
        console.log(111);
        $('#form').submit();
    });
});
