$(function () {
    $('#dashboard-user').addClass('active open');
    $('#dashboard-user-password').addClass('active');

    var $form = $('#user-password-form');

    $form.validate({
        rules: {
            password: {
                required: true,
                rangelength: [6, 30]
            }
        },
        submitHandler: function (form, event) {
            event.preventDefault();
            //form.submit();
            $.post($form.attr("action"), $form.serialize(), callback);
        }
    });
});