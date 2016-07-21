$(function () {
    var bodys = $(".body-img");
    for (var i = 0; i < bodys.length; i++) {
        var html = $(bodys[i]).html();
        var index = html.indexOf("<img src=");
        var lastIndex = html.lastIndexOf("<img src=");
        if (index != lastIndex) {
            $(bodys[i]).find("img").css("max-width", "49%");
        }
    }
});