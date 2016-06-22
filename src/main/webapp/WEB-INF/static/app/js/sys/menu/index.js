$(function () {
    var showRemoveNotify = function (response) {
        Notify.success("菜单删除成功。");
    };

    var beforeRemove = function (treeId, treeNode) {
        if (confirm("确认删除菜单 " + treeNode.name + " 吗？")) {
            $.post(ctx + "sys/menu/" + treeNode.id + "/delete", function (data, status) {
                if (status == "success" && data.status == "success") {
                    return true;
                } else {
                    return false;
                }
            });
        } else {
            return false;
        }
    };

    var addHoverDom = function (treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) {
            return;
        }
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
            + "' title='添加菜单' onfocus='this.blur();'></span>";
        sObj.after(addStr);

        var addBtn = $("#addBtn_" + treeNode.tId);
        if (addBtn) {
            addBtn.bind("click", function () {
                $("#myModal").modal({
                    remote: ctx + 'sys/menu/create?pid=' + treeNode.id
                });
            });
        }

        var editBtn = $("#" + treeNode.tId + "_edit");
        if (editBtn) {
            editBtn.bind("click", function () {
                $("#myModal").modal({
                    remote: ctx + 'sys/menu/' + treeNode.id + '/edit'
                });
            });
        }
    };

    var removeHoverDom = function (treeId, treeNode) {
        $("#addBtn_" + treeNode.tId).unbind().remove();
    };

    var setting = {
        view: {
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom
        },
        edit: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeRemove: beforeRemove,
            onRemove: showRemoveNotify
        }
    };

    $.fn.zTree.init($("#menu_tree"), setting, zNodes);

});