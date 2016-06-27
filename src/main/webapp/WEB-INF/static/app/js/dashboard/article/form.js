$(function () {

    KindEditor.ready(function (K) {
        window.editor = K.create('#body', {
            uploadJson: ctx + 'file/editor',
            fileManagerJson: ctx + 'file/manager'
        });
    });

});
