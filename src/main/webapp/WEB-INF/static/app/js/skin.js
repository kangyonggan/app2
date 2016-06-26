$(function () {

    function changeSkinPicker(skin) {
        for (var i = 0; i < skins.length; i++) {
            if (skin == skins[i].name) {
                $(".btn-colorpicker").css("background", skins[i].color);
                $(".dropdown-colorpicker ul li a").removeClass("selected");
                $(".dropdown-colorpicker ul li a").each(function () {
                    if ($(this).data("color") == skins[i].color) {
                        $(this).addClass("selected");
                    }
                });
                break;
            }
        }
    }

    var skin = ace.storage.get("skin");
    changeSkinPicker(skin);

    /**
     * 监听切换皮肤事件
     */
    $("#skin-colorpicker").change(function () {
        var val = $(this).val();
        for (var i = 0; i < skins.length; i++) {
            if (val == skins[i].color) {
                setSkin(skins[i]);
                break;
            }
        }
    });

    $("#ace-settings-navbar").change(function () {
        if ($(this).is(":checked")) {
            ace.storage.set("navbar", "on");
        } else {
            ace.storage.remove("navbar");
        }
    });

    /**
     * 设置皮肤
     *
     * @param skin
     */
    function setSkin(skin) {
        ace.storage.set("skin", skin.name);
        window.location.reload();
    }
});

/**
 * 皮肤包
 * @type {*[]}
 */
var skins = [{'name': 'dark', 'color': '#404040'},
    {'name': 'pink', 'color': '#FFC0CB'},
    {'name': 'blue', 'color': '#438eb9'}];

function loadSkinCss(skin) {
    var head = document.getElementsByTagName('head')[0];
    var link = document.createElement('link');
    link.href = ctx + "static/app/css/skin/skin-" + skin + ".css";
    link.rel = 'stylesheet';
    link.type = 'text/css';
    head.appendChild(link);
}
var skin = ace.storage.get("skin");
loadSkinCss(skin);
