$(function () {
    $('#form').validate({
        submitHandler: function (form) {
            var value = $.trim($(document.getElementsByTagName("iframe")[0].contentWindow.document.body).html());
            if (value == "") {
                Notify.warning("内容必须填写！");
                return;
            }
            $("#body").text(value);
            form.submit();
        }
    });

});
