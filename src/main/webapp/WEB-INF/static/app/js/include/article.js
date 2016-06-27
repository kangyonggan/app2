$(function () {
    $(".article-buttons > a.action").click(function () {

        if (app_who != 1) {
            // TODO 弹出登录窗
            return false;
        }

        var $this = $(this);
        $.get($this.attr("href"), function (result) {
            if (result.status == "success") {
                var count = $this.find(".attion-count").text() * 1;
                $this.find(".attion-count").text(count + 1);
            } else {
                Notify.warning("不带这么玩的");
            }
        });

        return false;
    });
});