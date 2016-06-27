$(function () {
    $(".article-buttons > a.action").click(function () {

        if (app_who != 1) {
            $("#myModal").modal({
                remote: ctx + 'login/ajax'
            });
            return false;
        }

        var $this = $(this);
        $.get($this.attr("href"), function (result) {
            if (result.status == "success") {
                var count = $this.find(".attion-count").text() * 1;
                $this.find(".attion-count").text(count + 1);
            } else {
                Notify.warning("机会只有那么一次, 稍纵即逝...");
            }
        });

        return false;
    });

    $(".article-reply").click(function () {
        if (app_who != 1) {
            $("#myModal").modal({
                remote: ctx + 'login/ajax'
            });
            return false;
        }
    });
});