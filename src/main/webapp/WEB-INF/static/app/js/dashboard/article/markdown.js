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
            }
        },
        submitHandler: function (form) {
            form.submit();
        }
    });

    $("#md-body").markdown();

    $('.markdown').on('click', '[data-handler=bootstrap-markdown-cmdPreview]', function () {
        $(".markdown a").prop("target", "_blank");
    });
});
