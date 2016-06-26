$(function () {
    $('#form').validate({
        rules: {
            title: {
                required: true,
                maxlength: 200
            }
        },
        submitHandler: function (form) {
            form.submit();
        }
    });

});
