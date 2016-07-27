$(function () {
    $('#form').validate({
        rules: {
            title: {
                required: true,
                maxlength: 100
            },
            password: {
                maxlength: 20
            }
        },
        submitHandler: function (form) {
            if (ids.length == 0) {
                Notify.warning("还没上传图片！");
                setTimeout(function () {
                    $("button[data-loading-text]").button('reset');
                }, 1000);
                return;
            }
            $("#ids").val(ids);
            form.submit();
        }
    });

    var ids = [];
    try {
        Dropzone.autoDiscover = false;
        var myDropzone = new Dropzone("#dropzone", {
            paramName: "file", // The name that will be used to transfer the file
            maxFilesize: 100, // MB
            addRemoveLinks: true,
            acceptedFiles: "image/jpg, image/jpeg, image/png, image/gif, image/bmp",
            dictInvalidFileType: "不支持此文件类型"
        });

        myDropzone.on("complete", function (file) {
            var response = eval('(' + file.xhr.response + ')');
            if (response.status == "success") {
                ids.push(response.message);
            }
        });

        myDropzone.on("removedfile", function (file) {
            var response = eval('(' + file.xhr.response + ')');
            if (response.status == "success") {
                for (var i = 0; i < ids.length; i++) {
                    var id = ids[i];
                    if (id == response.message) {
                        for (var j = i; j < ids.length - 1; j++) {
                            ids[j] = ids[j + 1];
                        }
                        ids.pop();
                        break;
                    }
                }
            }
        });
    } catch (e) {
        Notify.success("您的浏览器不支持此图片上传插件!");
    }

    $(".form-submit").click(function () {
        console.log("aaa");
    });

});
