if (typeof Validator != 'undefined') {
    Validator.messageSource['zh-cn'] = [
        ['gt-notblank','值过小'],
        ['lt-notblank','值过大'],
        ['require-value','请选择值'],
        ['accuracy',function(v,elm,args,metadata) {
            var num;
            if (document.getElementById(args[0])) {
                var object = document.getElementById(args[0]);
                var value = object.value;
                /*if (value == '' || value == null || value < 0) {
                    value = 7;
                }*/
                if (/[0-7]/.test(value)) {
                   num=value;
                }
                else {
                    num=7;
                }
            } else {
                if (/[0-7]/.test(args[0])) {
                    num=args[0];
                }
                else {
                    num=7;
                }
            }
            return ValidationUtils.format("小数位数不能多于%s位",[num]);
        }]
    ];
    Validation.add('gt-notblank', function(v, elm, args, metadata) {
        var reg = /^(-?\d+)(\.\d+)?$/;
        if ($.trim($("#" + args[0]).val()) != "" && reg.test($("#" + args[0]).val()) && $.trim($(elm).val()) != "") {
            var result = (parseFloat(v) - parseFloat($.trim($("#" + args[0]).val())) >= 0);
            if ($("#" + args[0]).hasClass("validation-failed") && !$(elm).hasClass("validation-failed") && result) {
                $("#" + args[0]).focus();
                $("#" + args[0]).blur();
            }
            return result;
        }
        if ($.trim($("#" + args[0]).val()) != "") {
            $("#" + args[0]).focus();
            $("#" + args[0]).blur();
        }
        return true;
    }, {depends:['validate-number',"max-length"],ignoreEmptyValue:false});
    Validation.add('lt-notblank', function(v, elm, args, metadata) {
        var reg = /^(-?\d+)(\.\d+)?$/;
        if ($.trim($("#" + args[0]).val()) != "" && reg.test($("#" + args[0]).val()) && $.trim($(elm).val()) != "") {
            var result = (parseFloat(v) - parseFloat($.trim($("#" + args[0]).val())) <= 0);
            if ($("#" + args[0]).hasClass("validation-failed") && !$(elm).hasClass("validation-failed") && result) {
                $("#" + args[0]).focus();
                $("#" + args[0]).blur();
            }
            return result;
        }
        if ($.trim($("#" + args[0]).val()) != "") {
            $("#" + args[0]).focus();
            $("#" + args[0]).blur();
        }
        return true;
    }, {depends:['validate-number',"max-length"],ignoreEmptyValue:false});

    Validation.add('require-value', function(v, elm, args, metadata) {
        return !((v == null) || (v.length == 0) || /^[\s|\u3000]+$/.test(v) || v == $(elm).attr("primitive"));
    }, {ignoreEmptyValue:false});

    Validation.add('accuracy', function(v, elm, args, metadata) {
        var reg;
        if (document.getElementById(args[0])) {
            var object = document.getElementById(args[0]);
            var value = object.value;
            if (value == '' || value == null || value < 0) {
                value = 7;
            }
            if (/[0-7]/.test(value)) {
                reg = "/^\\d+(.[0-9]{0," + value + "})?$/";
            }
            else {
                return false;
            }
        } else {
            if (/[0-7]/.test(args[0])) {
                reg = "/^\\d+(.[0-9]{0," + args[0] + "})?$/";
            }
            else {
                return false;
            }
        }
        var re = eval(reg);
        return re.test(v);
    }, {depends:['validate-number',"max-length"],ignoreEmptyValue:true});
}
/**
 * 将公式转换成数组
 * @param str 公式
 */
function parse(str) {
    var strArray = new Array();
    while (str.length > 0) {
        if (str.charAt(str.length - 1) == "#") {
            var variable = str.charAt(str.length - 1);
            str = str.substr(0, str.length - 1)
            var position = str.lastIndexOf("#")
            variable = str.substr(str.lastIndexOf("#")) + variable;
            str = str.substr(0, str.lastIndexOf("#"))
            strArray.push(variable);
        } else {
            strArray.push(str.charAt(str.length - 1));
            str = str.substr(0, str.length - 1);
        }
    }

    return strArray.reverse();
}
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
function _formatDate(record,row,value,column) {
    if (/^[1-9]+[0-9]*]*$/.test(value)) {
        var date = new Date();
        date.setTime(value);
        return date.format("yyyy-MM-dd");
    }
}
