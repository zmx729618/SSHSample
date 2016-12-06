function zTreeBeforeCheck(treeId, treeNode) {
    if (treeNode.isParent == true && treeNode.open == false) {
        var treeObj = $.fn.zTree.getZTreeObj(treeId);
        if (treeObj.setting.check.chkStyle == 'radio')
            return true;
        treeObj.expandNode(treeNode, true);
        treeObj.checkNode(treeNode, !treeNode.checked, true, true);
    }
    return true;
}
function onSingleClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId);
    zTree.expandNode(treeNode);
}
function asyncNodes(treeId, nodes, target, containParent) {
    var zTree = $.fn.zTree.getZTreeObj(treeId);
    var tar = $("#" + target);
    var ids = tar.val().replace(/\s/g, "");
    var reg = /,{2,}/;
    ids = ids.replace(reg, ",");
//    if (reg.test(ids)) {
    var reg2 = /^,|,$/;
    ids = ids.replace(reg2, "");
//    }
    var a = ids.split(',');
    for (var i = 0; i < nodes.length; i++) {
//        alert("这里是这样的:" + _inArray(nodes[i].id, a, false, 0));
//        alert(containParent || !nodes[i].isParent);
        if (_inArray(nodes[i].id, a, false, 0) && (containParent || !nodes[i].isParent)) {
            zTree.checkNode(nodes[i], true, true, true);
        }
        if (nodes[i].isParent && nodes[i].zAsync) {
            asyncNodes(treeId, nodes[i].children, target, containParent);
        }
        else {
            zTree.reAsyncChildNodes(nodes[i], "refresh", true);
        }
    }
}
function showtree(treerender, target, title, url, width, height, ids, containParent, jsonArray, cr) {
    cr = (typeof cr == 'undefined') ? 'checkbox' : cr;
    if (url.indexOf("?") == -1) {
        url = url + "?time=" + new Date().getTime();
    }
    else {
        url = url + "&time=" + new Date().getTime();
    }
    var settings = {
        view: {
            expandSpeed: "fast",
            dblClickExpand: false
        },
        async:{
            enable:true,
            url:url,
            autoParam:["id", "name","type","year"],
            type:"post"
        },
        callback: {
            beforeCheck:zTreeBeforeCheck,
            onClick:onSingleClick,
            onNodeCreated:function(event, treeId, treeNode) {
                if (treeNode.parentTId == null && treeNode.level == 0 && treeNode.isLastNode) {
                    var zTree = $.fn.zTree.getZTreeObj(treeId);
                    var tar = $("#" + target);
                    var ids = tar.val().replace(/\s/g, "");
                    var reg = /,{2,}/;
                    ids = ids.replace(reg, ",");
//                    if (reg.test(ids)) {
                    var reg2 = /^,|,$/;
                    ids = ids.replace(reg2, "");
//                    }
                    var a = ids.split(',');

                    if (treeNode.id != null && _inArray(treeNode.id, a, false, 0)) {
                        zTree.checkNode(treeNode, true, true, true);
                    }
                    asyncNodes(treeId, zTree.getNodes(), target, containParent);
                }
            },
            onAsyncSuccess:function onAsyncSuccess(event, treeId, treeNode, msg) {
                if (treeNode != null)
                    asyncNodes(treeId, treeNode.children, target, containParent);
            }
        },
        check: {
            enable: true,
            chkStyle: cr,
            chkboxType: { "Y": "ps", "N": "ps" },
            radioType:"all"
        }
    }
    $.fn.zTree.init($("#" + treerender), settings);
    art("#" + treerender + '_btn').dialog({
//    art.dialog({
        id:treerender,
        drag: false,
//        zIndex: 9999,
        resize:false,
//        follow:document.getElementById(treerender + '_btn'),
        title:title,
        padding:0,
        width:width,
        height:height,
        content:document.getElementById(treerender + '_win'),
        okVal:"确定",
        ok:function() {
            if (typeof jsonArray != 'undefined' && jsonArray != null)
                jsonArray.length = 0;
            var treeObj = $.fn.zTree.getZTreeObj(treerender);
            var nodes = treeObj.getCheckedNodes(true);
            var id_str = '',name_str = '';
            for (var i = 0, l = nodes.length; i < l; i++) {
                if (nodes[i].isParent == false || containParent) {
                    id_str = id_str + nodes[i].id + ',';
                    name_str = name_str + nodes[i].name + ',';
//                    resp.push({id:nodes[i].id,name:nodes[i].name});
                    if (typeof jsonArray != 'undefined' && jsonArray != null)
                        jsonArray.push({id:nodes[i].id,name:nodes[i].name});
                }
            }

            var _target_id = $('#' + target);
            var _target_name = $('#' + target + '_name');
            if (_target_id != null && typeof _target_id != 'undefined' && document.getElementById(target) != null) {
                if (document.getElementById(target).tagName.toLowerCase() == 'div')
                    _target_id.text(id_str.substr(0, id_str.length - 1));
                else
                    _target_id.val(id_str.substr(0, id_str.length - 1));
            }
            if (_target_name != null && typeof _target_name != 'undefined' && document.getElementById(target + "_name") != null) {
                if (document.getElementById(target + "_name").tagName.toLowerCase() == 'div')
                    _target_name.text(name_str.substr(0, name_str.length - 1));
                else {
                    _target_name.val(name_str.substr(0, name_str.length - 1));
                    _target_name.focus();
                    _target_name.blur();
                }
            }
        }
    });
}
