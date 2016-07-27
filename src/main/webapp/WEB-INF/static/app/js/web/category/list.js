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

    $(".article-link").click(function () {
        if ($(this).find(".need-password")) {
            var val = prompt("输入密码查看文章...");
            if (val == null) {
                return false;
            }
            $(this).attr("href", $(this).attr("href") + "&password=" + val);
        }
    });

});