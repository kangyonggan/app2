$(function () {
    var skin_class = ace.cookie.get("skin");
    if (skin_class) {
        $.fn.ace.skin(skin_class);
    }

    $('#skin-colorpicker').on('change', function () {
        var skin_class = $(this).find('option:selected').data('skin');
        $.fn.ace.skin(skin_class);
        ace.cookie.set("skin", skin_class, 604800000);
    });

    $("#ace-settings-navbar").click(function () {
        if (ace.cookie.get("navbar-fixed")) {
            ace.cookie.remove("navbar-fixed");
        } else {
            ace.cookie.set("navbar-fixed", "checked", 604800000);
        }
    });
});