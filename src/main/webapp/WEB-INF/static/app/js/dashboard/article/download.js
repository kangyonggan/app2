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
            if ("" == $("input[type=file]").val()) {
                Notify.warning("请选择文件");
                setTimeout(function () {
                    $("button[data-loading-text]").button('reset');
                }, 1000);

                return false;
            }
            form.submit();
        }
    });

    $('input[type=file]').ace_file_input()
    .closest('.ace-file-input')
    .addClass('width-90 inline')
    .wrap('<div class="form-group file-input-container"><div class="col-sm-7"></div></div>');

    $('#id-add-attachment')
    .on('click', function(){
        var file = $('<input type="file" name="attachment[]" />').appendTo('#form-attachments');
        file.ace_file_input();

        file.closest('.ace-file-input')
        .addClass('width-90 inline')
        .wrap('<div class="form-group file-input-container"><div class="col-sm-7"></div></div>')
        .parent().append('<div class="action-buttons pull-right col-xs-1">\
            <a href="#" data-action="delete" class="middle">\
                <i class="ace-icon fa fa-trash-o red bigger-130 middle"></i>\
            </a>\
        </div>')
        .find('a[data-action=delete]').on('click', function(e){
            e.preventDefault();
            $(this).closest('.file-input-container').hide(300, function(){ $(this).remove() });
        });
    });
});
