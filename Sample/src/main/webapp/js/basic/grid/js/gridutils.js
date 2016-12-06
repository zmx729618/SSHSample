function decodejson(property, object) {
    if (property.indexOf(".") != -1) {
        var objs = property.split(".")
        var index = property.indexOf(".");
        if (objs.length >= 2) {
            var obj1 = eval(object[objs[0]]);
            if (obj1 != null && typeof obj1 != 'undefined') {
                return decodejson(property.substr(index + 1, property.length), obj1);
            } else
                return "";
        }
        else
            return "";
    }
    else
        return (typeof object[property] == 'undefined') ? "" : object[property];
}
function _isArray(value) {
    return Object.prototype.toString.apply(value) === '[object Array]'
}
function _inArray(elem, array, strict, index) {
    if (strict == null || typeof strict === 'undefined') {
        strict = false;
    }
    if (index >= array.length)
        return false;
    if (typeof index === 'undefined')
        index = 0;
    for (var i = index; i < array.length; i++) {
        if (strict) {
            if (elem === array[i]) return true;
        }
        else {
            if (elem == array[i]) return true;
        }
    }
    return false;
}
Array.prototype.contains = function(obj) {
    var i = this.length;
    while (i--) {
        if (this[i] === obj) {
            return true;
        }
    }
    return false;
}
Date.prototype.format = function(format) {
    var o = {
        "M+" : this.getMonth() + 1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth() + 3) / 3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
        (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)if (new RegExp("(" + k + ")").test(format))
        format = format.replace(RegExp.$1,
            RegExp.$1.length == 1 ? o[k] :
                ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}
function _formatDate(tdDiv, pid) {
    var value = $(tdDiv).html();
    if (/^[1-9]+[0-9]*]*$/.test(value)) {
        var date = new Date();
        date.setTime(value);
        $(tdDiv).html(date.format("yyyy-MM-dd"));
    }
}

function _moreformatDate(tdDiv, pid) {
    var value = $(tdDiv).html();
    if (/^[1-9]+[0-9]*]*$/.test(value)) {
        var date = new Date();
        date.setTime(value);
        $(tdDiv).html(date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
    }
}

function _formatBoolean(tdDiv, pid) {
    var value = $(tdDiv).html();
    if (value == "true") {
        var date = new Date();
        date.setTime(value);
        $(tdDiv).html(date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
    }
}

function _formatBoolean(tdDiv, pid) {
    var value = $(tdDiv).html();
    if (value == "true") {
        $(tdDiv).html("是");
    } else {
        $(tdDiv).html("否");
    }
}
function _isSeedORmaterial(tdDiv, pid) {
    var value = $(tdDiv).html();
    if (value == "true") {
        $(tdDiv).html("稳定材料");
    } else if (value == "false") {
        $(tdDiv).html("中间材料");
    }
}

function _formatDouble(tdDiv, pid) {
    var value = $(tdDiv).html();
    if (!isNaN(value)) {
        $(tdDiv).html(Math.round(value * Math.pow(10, 2)) / Math.pow(10, 2));
    }
}

function _formatDouble5(tdDiv, pid) {
    var value = $(tdDiv).html();
    if (!isNaN(value)) {
        $(tdDiv).html(Math.round(value * Math.pow(10, 5)) / Math.pow(10, 5));
    }
}
//去除两端的空格
String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
//删除字符串左边的空格回车
String.prototype.ltrim = function() {
    return this.replace(/(^\s*)/g, "");
}
//删除客串尾的空格与回车
String.prototype.rtrim = function() {
    return this.replace(/(\s*$)/g, "");
}
//删除所有的空格
String.prototype.trimAll = function() {
    return this.replace(/\s/g, "");
}
//全角转换成半角
String.prototype.dbc2sbc = function() {
    return this.replace(/[\uff01-\uff5e]/g,
        function(a) {
            return String.fromCharCode(a.charCodeAt(0) - 65248);
        }).replace(/\u3000/g, " ");
}

function getElementLeft(element) {
    var actualLeft = element.offsetLeft;
    var current = element.offsetParent;
    while (current !== null) {
        actualLeft += current.offsetLeft;
        current = current.offsetParent;
    }
    return actualLeft;
}
function getElementTop(element) {
    var actualTop = element.offsetTop;
    var current = element.offsetParent;
    while (current !== null) {
        actualTop += current.offsetTop;
        current = current.offsetParent;
    }
    return actualTop;
}
