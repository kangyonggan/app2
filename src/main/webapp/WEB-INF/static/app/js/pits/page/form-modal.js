$(function () {
    $('#modal-form').validate({
        rules: {
            name: {
                required: true,
                isChineseAndEnglish: true,
                maxlength: 64
            },
            url: {
                required: true,
                url: true,
                maxlength: 128
            },
            icon: {
                required: true,
                maxlength: 64
            },
            sort: {
                required: true,
                number: true,
                range: [1, 1000]
            }
        }
    });
});
