function _beforeCheck(treeId, treeNode) {
//        alert(treeId);
    if (treeNode.isParent == true && treeNode.open == false) {
        var treeObj = $.fn.zTree.getZTreeObj(treeId);
        if (treeObj.setting.check.chkStyle == 'radio')
            return true;
        treeObj.expandNode(treeNode, true);
        treeObj.checkNode(treeNode, !treeNode.checked, true, true);
    }
    return true;
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
_jsontree = function () {
    var p = {};
    this.extend = function(destination, source) {
        for (var property in source) {
            destination[property] = source[property];
        }
        return destination;
    }
    this.init = function(p1) {
        p = this.extend({
            id:'',
            renderTo:'',
            target:'',
            title:'',
            url:'',
            width:'auto',
            height:'auto',
            containParent:false,
            jsonArray:null,
            cr:'checkbox',
            enableChk:true,
            radioType:"all",
            chkboxType: { "Y": "ps", "N": "ps" },
            dragable:false,
            resizable:false,
            follow:null,
            zIndex:100,
            otherParam:null
        }, p1);
        if (p.url.indexOf("?") == -1) {
            p.url = p.url + "?time=" + new Date().getTime();
        }
        else {
            p.url = p.url + "&time=" + new Date().getTime();
        }
        var _settings = {
            view: {
                expandSpeed: "fast"
            },
            async:{
//                dataType:'json',
                otherParam:p.otherParam,
                enable:true,
                url:p.url,
                autoParam:["id", "name","type","year"],
                type:"post"
            },
            callback: {
                beforeCheck:_beforeCheck,
                    onNodeCreated:function(event, treeId, treeNode) {
                if (treeNode.parentTId == null && treeNode.level == 0 && treeNode.isLastNode) {
                    var zTree = $.fn.zTree.getZTreeObj(treeId);
                    var tar = $("#" + p.target);
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
                    asyncNodes(treeId, zTree.getNodes(), p.target, p.containParent);
                }
            },
            onAsyncSuccess:function onAsyncSuccess(event, treeId, treeNode, msg) {
                if (treeNode != null)
                    asyncNodes(treeId, treeNode.children, p.target, p.containParent);
            }
            },
            check: {
                nocheckInherit: false,
                enable: p.enableChk,
                chkStyle: p.cr,
                chkboxType:p.chkboxType,
                radioType:p.radioType
            }
        };
        $.fn.zTree.init($("#" + p.renderTo), _settings);
    }

    this.zTreeBeforeCollapse = function(treeId, treeNode) {
        return false;
    }

    function inArray(elem, array) {
        var len;
        if (array) {
            if ($.isArray(array)) {
                len = array.length;
                for (var i = 0; i < len; i++) {
                    // Skip accessing in sparse arrays
                    if (array[i] == elem) {
                        return true;
                    }
                }
            } else {
                return elem == array;
            }
        }
        return false;
    }

    this._initDialog = function() {
//        var obj = $.fn.zTree.getZTreeObj(p.renderTo);
//        obj.reAsyncChildNodes(null, "refresh",true);
//        alert(obj.expandAll(true));
//        alert(obj.getNodes());
//        alert(obj.getNodesByParam("level", 0, null).length);
    }
    this.show = function(pnode) {
        this.init(pnode);
        var follow = (p.follow == null ? (p.renderTo + '_btn') : p.follow);
        art.dialog({
            id:p.id,
            drag: p.dragable,
            zIndex: p.zIndex,
            resize:p.resizable,
            follow:document.getElementById(follow),
            title:p.title,
            padding:0,
            init:this._initDialog,
            width:p.width,
            height:p.height,
            content:document.getElementById(p.renderTo + '_win'),
            okVal:"确定",
            ok:function() {
                if (typeof p.jsonArray != 'undefined' && p.jsonArray != null)
                    p.jsonArray.length = 0;
                var treeObj = $.fn.zTree.getZTreeObj(p.renderTo);
                var nodes = treeObj.getCheckedNodes(true);
                var id_str = '',name_str = '';
                for (var i = 0, l = nodes.length; i < l; i++) {
                    if (nodes[i].isParent == false || p.containParent) {
                        id_str = id_str + nodes[i].id + ',';
                        name_str = name_str + nodes[i].name + ',';
//                    resp.push({id:nodes[i].id,name:nodes[i].name});
                        if (typeof p.jsonArray != 'undefined' && p.jsonArray != null)
                            p.jsonArray.push({id:nodes[i].id,name:nodes[i].name});
                    }
                }

                var _target_id = $('#' + p.target);
                var _target_name = $('#' + p.target + '_name');
                if (_target_id != null && typeof _target_id != 'undefined' && document.getElementById(p.target) != null) {
                    if (document.getElementById(p.target).tagName.toLowerCase() == 'div')
                        _target_id.text(id_str.substr(0, id_str.length - 1));
                    else
                        _target_id.val(id_str.substr(0, id_str.length - 1));
                }
                if (_target_name != null && typeof _target_name != 'undefined' && document.getElementById(p.target + "_name") != null) {
                    if (document.getElementById(p.target + "_name").tagName.toLowerCase() == 'div')
                        _target_name.text(name_str.substr(0, name_str.length - 1));
                    else {
                        _target_name.val(name_str.substr(0, name_str.length - 1));
//                        _target_name.focus();
                        _target_name.blur();
                    }
                }
                return true;
            }
        });
    }
}
