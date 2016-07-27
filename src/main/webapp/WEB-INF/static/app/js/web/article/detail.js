$(function () {
    $('#form').validate({
        rules: {
            content: {
                required: true
            }
        },
        submitHandler: function (form) {
            var value = $.trim($(document.getElementsByTagName("iframe")[0].contentWindow.document.body).html());
            if (value == "") {
                Notify.warning("内容必须填写！");
                setTimeout(function () {
                    $("button[data-loading-text]").button('reset');
                }, 1000);
                return;
            }
            $("#content").text(value);
            form.submit();
        }
    });

    KindEditor.ready(function (K) {
        window.editor = K.create('#content', {
            uploadJson: ctx + 'file/editor',
            fileManagerJson: ctx + 'file/manager'
        });
    });

    $(".reply-delete").click(function () {
        $this = $(this);
        $.get($this.attr("href"), function (result) {
            if (result.status == "success") {
                window.location.reload();
            }
        });

        return false;
    });

    var $overflow = '';
    var colorbox_params = {
        rel: 'colorbox',
        reposition: true,
        scalePhotos: true,
        scrolling: false,
        previous: '<i class="ace-icon fa fa-arrow-left"></i>',
        next: '<i class="ace-icon fa fa-arrow-right"></i>',
        close: '&times;',
        current: '{current} / {total}',
        maxWidth: '100%',
        maxHeight: '100%',
        onOpen: function () {
            $overflow = document.body.style.overflow;
            document.body.style.overflow = 'hidden';
        },
        onClosed: function () {
            document.body.style.overflow = $overflow;
        },
        onComplete: function () {
            $.colorbox.resize();
        }
    };

    $('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
    $("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange fa-spin'></i>");//let's add a custom loading icon


    $(document).one('ajaxloadstart.page', function (e) {
        $('#colorbox, #cboxOverlay').remove();
    });

    $(".attachment-delete").click(function () {
        $.get($(this).attr("href"), function () {
            window.location.reload();
        });

        return false;
    });

    var password = window.location.href;
    password = password.substring(password.lastIndexOf("password") + 9);

    $.get(ctx + "article/body?id=" + articleId + "&password=" + password, function(result) {
        $("#article-body").html(marked(result));
    });

    $(".markdown a").prop("target", "_blank");
});
