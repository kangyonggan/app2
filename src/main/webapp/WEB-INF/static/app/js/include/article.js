$(function () {
    $(".article-buttons > a.action").click(function () {
        $.get($(this).attr("href"));

        var count = $(this).find(".attion-count").text() * 1;
        $(this).find(".attion-count").text(count + 1);

        return false;
    });
});