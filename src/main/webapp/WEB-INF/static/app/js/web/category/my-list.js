$(function () {
    $("#startTime, #endTime").datetimepicker({
        language: 'zh-CN',
        autoclose: 1,
        todayBtn: 1,
        pickerPosition: "bottom-left",
        format: 'yyyy-mm-dd',
        minView: 2
    });
});