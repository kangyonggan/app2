$(function () {
    var skin_class = ace.storage.get("skin");
    if (skin_class) {
        $.fn.ace.skin(skin_class);
    } else {
        $.fn.ace.skin("skin-3");
    }

    $('#skin-colorpicker').on('change', function () {
        var skin_class = $(this).find('option:selected').data('skin');
        $.fn.ace.skin(skin_class);
        ace.storage.set("skin", skin_class);
    });

    $("#ace-settings-navbar").click(function () {
        if ($(this).is(":checked")) {
            ace.storage.set("navbar-fixed", "checked");
        } else {
            ace.storage.remove("navbar-fixed");
        }
    });
});