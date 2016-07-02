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
            var text = window.markdown.toHTML($("#md-body").val());
            $("#md-body").val(text);
            form.submit();
        }
    });

    var html, isPrev = false;

    $(".md-prev").click(function () {
        var text = html;
        if (!isPrev) {
            html = $("#md-body").val();
            text = window.markdown.toHTML(html);
            $("#md-prev-content").html(text);
            $("#md-body").addClass("hide");
            $("#md-prev-content").removeClass("hide");
        } else {
            $("#md-body").val(text);
            $("#md-prev-content").addClass("hide");
            $("#md-body").removeClass("hide");
        }
        isPrev = !isPrev;
        return false;
    });

});
