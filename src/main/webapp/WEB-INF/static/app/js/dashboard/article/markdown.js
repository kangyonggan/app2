$(function () {
    $('#form').validate({
        ignore: "",
        rules: {
            title: {
                required: true,
                maxlength: 100
            },
            summary: {
                maxlength: 200
            },
            body: {
                required: true
            },
            password: {
                maxlength: 20
            }
        },
        submitHandler: function (form) {
            form.submit();
        }
    });

    $("#md-body").markdown({resize: 'vertical'});

    $('.markdown').on('click', '[data-handler=bootstrap-markdown-cmdPreview]', function () {
        $(".markdown a").prop("target", "_blank");
    });
});
