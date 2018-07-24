//支付宝调用支付方式，true纯网管支付（选择银行），false即时到账接口
var aliBank = true;

function checkLogin(page) {
    var loginTag = '';
    $.ajax({
        url: "sysUserOverAction!getCurUserJson.action?node=" + page,
        dataType: 'json',
        async: false,
        type: 'post',
        success: function (result) {
            if (result == null) {
                return;
            } else if (page == 'act') {
                loginTag = 'loginTag';
//				return "loginTag";
            } else if (result.userCode == "admin") {
                windowHref('loginAction!mainPage.action');
                //window.location.href='loginAction!mainPage.action';
                return;
            } else {
                goToURL('center', 'jumpAction!loginPage.action');
                //window.location.href='layout.html?defP=dp';
                return;
            }
        },
        error: commerror
    });

    return loginTag;
}

$.messager.defaults = {ok: "确定", cancel: "取消"};

/**
 * 判断是否最外层
 */
function checkOutermost() {
    var tag = false;
    //if($('#center').attr('id')=='center'){//最外层
    var out = false;
    $.each($('iframe'), function (i, n) {
        if (n.id == 'center') {
            out = true;
        }
    });

    if (out) {//最外层
        tag = true;
    } else if (opener != null) {//是弹出窗口
        tag = true;
    } else if ($('#mainJspPage').attr('id') == 'mainJspPage') {//admin登录页面
        tag = true;
    } else if (parent == null) {//最外层
        tag = true;
    }

    //alert(tag);
    //alert($('iframe',parent.document.body).length);
    return tag;
}

/**
 * ajax数据处理提示
 */
var ajaxNums = 0;

function ajaxBefore() {
    if (checkOutermost()) {//最外层
        if ($('.datagrid-mask').attr('class') == 'datagrid-mask') {//还有运行的ajax
            ajaxNums++;
        } else {
            $("<div class=\"datagrid-mask\"></div>").css({
                display: "block",
                width: "100%",
                height: document.documentElement.scrollHeight,
                'z-index': 50001
            }).appendTo("body");
            $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({
                display: "block",
                'z-index': 50001,
                left: ($(document.body).outerWidth(true) - 190) / 2,
                top: ($(window).height() - 45) / 2
            });
        }
    } else {
        parent.ajaxBefore();
    }
}

function ajaxComplete() {
    if (checkOutermost()) {//最外层
        if (ajaxNums == 0) {
            $(".datagrid-mask").remove();
            $(".datagrid-mask-msg").remove();
        } else {
            ajaxNums--;
        }
    } else {
        parent.ajaxComplete();
    }
}

$.ajaxSetup({
    beforeSend: function () {
        ajaxBefore();
    },
    complete: function () {
//		$("<div class=\"datagrid-mask\" id=\"a1\"></div>").css({display:"block",width:"100%",height:"100%"}).appendTo("body");
//		$("<div class=\"datagrid-mask-msg\" id=\"a2\"></div>").html("已处理完，请刷新。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
        ajaxComplete();
    }
});


//动态构建一个表单
function makeForm(url) {
    var submitForm = document.createElement('form');
    document.body.appendChild(submitForm);
    submitForm.setAttribute('method', 'post');
    var uri = '';
    var i = url.indexOf('?');
    if (i == -1) {
        submitForm.action = url;
    } else {
        submitForm.action = url.substring(0, i);
    }

    if (i >= 0 && url.length >= i + 1) {
        uri = url.substring(i + 1, url.length);
    }
    var parameters = uri.split('&');
    for (var i = 0; i < parameters.length; i++) {
        // 获取等号位置
        pos = parameters[i].indexOf('=');
        if (pos == -1) {
            continue;
        }
        // 获取name 和 value
        paraName = parameters[i].substring(0, pos);
        paraValue = parameters[i].substring(pos + 1);
        var spacepos = paraValue.indexOf('+');
        if (spacepos != -1) {
            var paraValue1 = paraValue.substring(0, spacepos);
            var paraValue2 = paraValue.substring(spacepos + 1);
            paraValue = paraValue1 + " " + paraValue2;
        }
        // alert(paraValue);
        var inputObject = document.createElement('input');
        inputObject.setAttribute('type', 'hidden');
        inputObject.setAttribute('name', paraName);
        inputObject.setAttribute('value', paraValue);
        submitForm.appendChild(inputObject);
    }
    submitForm.submit();
}

function hide(str) {
    var obj = document.getElementById(str);
    obj.style.display == '' ? obj.style.display = 'none'
        : obj.style.display = '';
}

function checkIDS() {
    var list = document.forms[0].IDS;
    if (list != null) {
        if (list.length != null) {
            for (var i = 0; i < list.length; i++) {
                if (list[i].checked == true)
                    return true;
            }
        } else {
            if (list.checked == true)
                return true;
        }
    }
    return false;
}

function checkEmpty(obj, str) {
    if (obj.value == '') {
        return str;
    } else {
        return '';
    }
}


function makePage(currentPage, totalPages, pageSize, totalRows) {
    document.write('<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="td_line_dot">');
    document.write('              <tr > ');
    document.write('                <td  align="left"> 页 <strong>' + currentPage + '</strong>/' + totalPages + '&nbsp;&nbsp;&nbsp;&nbsp;显示 ');
    document.write('			  	<select name="ps.pageSize" id="btnSP" class="select">');
    document.write('    <option value="5">5</option>');
    document.write('    <option value="10">10</option>');
    document.write('    <option value="15">15</option>');
    document.write('    <option value="20">20</option>');
    document.write('    <option value="25">25</option>');
    document.write('    <option value="30">30</option>');
    document.write('</select>');
    document.write('                  总共:' + totalRows + '条');
    document.write('                  </td>');
    document.write('                <td align="right">');
    if (currentPage - 0 > 1) {
        document.write('<a  onclick="jumpPage(1);" >首页</a>');
        document.write('&nbsp;<a  onclick="jumpPage(' + (currentPage - 1) + ');" >上一页</a>');
    }
    if (totalPages - 0 > currentPage - 0) {
        document.write('&nbsp;<a  onclick="jumpPage(' + (currentPage + 1) + ');" >下一页</a>');
        document.write('&nbsp;<a  onclick="jumpPage(' + totalPages + ');" >末页</a>');
    }
    document.write('                &nbsp;到 <strong><font color="#000000">');
    document.write('                   <input type="text" name="ps.viewPage" size="1" value="' + currentPage + '" id="viewPage" class="textfield"/>');
    document.write('                   <input type="hidden" name="ps.totalPages" value="' + totalPages + '" id="totalPages"/>');
    document.write('                  <input name="zd" type="button" class="bt_jump" onclick="gotoPage()" value="转到">');
    document.write('                  </font></strong>');
    document.write('                </td>');
    document.write('              </tr><tr  valign="bottom" ><td colspan="2">&nbsp;</td></tr>');
    document.write('            </table>');
    document.getElementById('btnSP').value = pageSize;
}

function gotoPage() {
    var viewPage = document.getElementById("viewPage").value;
    if (viewPage - 0 > document.getElementById("totalPages").value - 0 || viewPage - 0 < 1) {
        document.getElementById("viewPage").value = '';
        alert("请输入正确的页码");
        return;
    }
    jumpPage(viewPage);
}


$().ready(function () {
//	 var indexs=1;
//	 testPage(1);
//	 $("#uppage").click(function(){if(indexs>1){indexs--}testPage(indexs);});
//	 $("#downpage").click(function(){indexs++;testPage(indexs);});
});

function testPage(ind, action) {
    $.ajax({
        type: "post",
        dataType: "json",
        url: "sysRoleAction!findGrid.action",//目标地址(页面代码会在下面呈上)
        data: "start=" + (ind - 1) * 20 + "&limit=20",
        success: function (json) {
            var result = json.root;
            var tbody = "";
            var tbname = "#" + "tbshow" + " tr:gt(0)";
            $(tbname).remove();
            $.each(result, function (i, n) {
                var cla = " onmouseover=this.className='cellChangeColor' onMouseOut=this.className='cellColor_b'";
                if (i % 2 == 0)
                    cla = " class='cellColor' onmouseover=this.className='cellChangeColor' onMouseOut=this.className='cellColor'";
                var tr = "<tr " + cla + " ><td>" + n.roleCode + "</td><td>" + n.roleName + "</td><td>" + n.state + "</td></tr>";
                tbody += tr;
            });
            $("#tbshow tr:gt(0):odd").attr("class", "odd");
            $("#tbshow tr:gt(0):even").attr("class", "even");
            $("#tbshow").append(tbody);
        }
    });
}

function jquerySumbit(val, action) {
    var formid = "#" + val;
    $.ajax(
        {
            url: action,
            data: $(formid).serialize(),
            dataType: 'json',
            type: 'post',
            success: function (result) {
                alert(stringify(result));
                $(formid).form('load', result);
            }
        });
//	$.getJSON(action, $(formid).serialize(),function(result){
//		$(formid).form('load',result);
//	});
}

/**
 * 从后台获取系统参数;
 * @param paramName 参数名
 */
function findSysParam(paramName) {
    var value = '';
    $.ajax({
        url: 'loginAction!findSysParam.action',
        data: 'node=' + paramName,
        dataType: 'json',
        async: false,
        type: 'post',
        success: function (result) {
            value = result.success;
        },
        error: commerror
    });
    return value;
}

/**
 * 从后台获取数据构造下拉列表;
 * @param tablename 表名
 * @param comumnname 字段名
 * @param selectname name属性
 * @param selectval 要选中的值
 * @param headempty 是否有空选择项
 */
function creatselect(tablename, comumnname, selectname, selectval, headempty, funName) {

    var selecthtml = '<select name="' + selectname + '" id="' + selectname + '" class="select_readonly" onchange="' + funName + '(this);">';
    if (headempty) {
        selecthtml += '<option value="">---请选择---</option>';
    }
    $.ajax({
        url: 'loginAction!getSelectJson.action',
        data: 'tableName=' + tablename + '&columnName=' + comumnname,
        dataType: 'json',
        async: false,
        type: 'post',
        success: function (result) {
            $.each(result, function (i, n) {
                if (n.id == selectval) {
                    selecthtml += '<option value="' + n.id + '" selected="selected">' + n.text + '</option>';
                }
                else {
                    selecthtml += '<option value="' + n.id + '">' + n.text + '</option>';
                }
            });
            selecthtml += '</select>';
        },
        error: commerror
    });
    return selecthtml;
}

/**
 * 从后台获取数据构造下拉列表;
 * @param tablename 表名
 * @param comumnname 字段名
 * @param selectname name属性
 * @param selectval 要选中的值
 */
function creatselectByUrl(curl, params, selectname, selectval, idkey, textkey) {
    var selecthtml = '<select name="' + selectname + '" id="' + selectname + '" class="select_readonly">';

    if ('' == params) {
        params = "s=" + Math.random();
    } else {
        params = params + "&s=" + Math.random();
    }

    $.ajax({
        url: curl,
        data: params,
        dataType: 'json',
        async: false,
        type: 'post',
        success: function (result) {
            $.each(result, function (i, n) {
                var cproperty = "";
                for (var key in n) {
                    cproperty += " cproperty_" + key + "='" + n[key] + "' ";
                }
                if (n[idkey] == selectval) {
                    selecthtml += '<option value="' + n[idkey] + '" ' + cproperty + ' selected="selected">' + n[textkey] + '</option>';
                }
                else {
                    selecthtml += '<option value="' + n[idkey] + '" ' + cproperty + '>' + n[textkey] + '</option>';
                }
            });
            selecthtml += '</select>';
        },
        error: commerror
    });
    return selecthtml;
}

/**
 * 从后台获取数据构造下拉列表的选项;
 * @param tablename 表名
 * @param comumnname 字段名
 * @param selectname 页表上已有的select的id
 * @param selectval 要选中的值
 */
function addselect(tablename, comumnname, selectname, selectval, headempty) {
    var selecthtml = "#" + selectname;
    if (headempty) {
        $(selecthtml).append('<option value="">---请选择---</option>');
    }
    $.ajax({
        url: 'loginAction!getSelectJson.action',
        data: 'tableName=' + tablename + '&columnName=' + comumnname,
        dataType: 'json',
        async: false,
        type: 'post',
        success: function (result) {
            //alert(result.length);

            if ("DISCOUNT" == comumnname) {
                for (i = (result.length - 1); i >= 0; i--) {
                    var n = result[i];

                    if (n.id == selectval) {
                        $(selecthtml).append('<option value="' + n.id + '" selected="selected">' + n.text + '</option>');
                    }
                    else {
                        $(selecthtml).append('<option value="' + n.id + '">' + n.text + '</option>');
                    }

                }
            } else {
                for (i = 0; i < result.length; i++) {
                    var n = result[i];

                    if (n.id == selectval) {
                        $(selecthtml).append('<option value="' + n.id + '" selected="selected">' + n.text + '</option>');
                    }
                    else {
                        $(selecthtml).append('<option value="' + n.id + '">' + n.text + '</option>');
                    }

                }
            }

//			$.each(res, function(i, n){
//				if(n.id==selectval){
//					$(selecthtml).append('<option value="'+n.id+'" selected="selected">'+n.text+'</option>');
//				}
//				else{
//					$(selecthtml).append('<option value="'+n.id+'">'+n.text+'</option>');
//				}
//			});
        },
        error: commerror
    });
}

/**
 * 从后台获取数据构造下拉列表的选项;
 * @param url 后台路径
 * @param params 参数名
 * @param selectname 页表上已有的select的id
 * @param selectval 要选中的值
 */
function addselectByUrl(curl, params, selectname, selectval, idkey, textkey, headempty, isEmpty) {
    var selecthtml = "#" + selectname;
    if (headempty) {
        $(selecthtml).append('<option value="">---请选择---</option>');
    }
    else if (isEmpty) {
        $(selecthtml).append('<option value=""></option>');
    }
    $.ajax({
        url: curl,
        data: params + "&s=" + Math.random(),
        dataType: 'json',
        async: false,
        type: 'post',
        success: function (result) {
            $.each(result, function (i, n) {
                var cproperty = "";
                for (var key in n) {
                    cproperty += " cproperty_" + key + "='" + n[key] + "' ";
                }
                if (n[idkey] == selectval) {
                    $(selecthtml).append('<option value="' + n[idkey] + '" ' + cproperty + ' selected="selected">' + n[textkey] + '</option>');
                }
                else {
                    $(selecthtml).append('<option value="' + n[idkey] + '" ' + cproperty + '>' + n[textkey] + '</option>');
                }
            });
        },
        error: commerror
    });
}


function commerror(result) {
    if (result.responseText.indexOf("注册") > -1) {
        window.location.href = "index.htm?newReg=g";
    }
    else if (result.responseText == "") {
        alert("访问信息不正确，请稍后再试或重新登录！");
    }
    else {
        openDialog('openDialog', '出现错误!', 600, 350, result.responseText,
            function () {
            });
    }

}

function sureError() {
    $('#errorWindow').window('close');
}

var stringify = function (obj) {
    //如果是IE8+ 浏览器(ff,chrome,safari都支持JSON对象)，使用JSON.stringify()来序列化
    if (window.JSON) {
        return JSON.stringify(obj);
    }
    var t = typeof (obj);
    if (t != "object" || obj === null) {
        // simple data type
        if (t == "string") obj = '"' + obj + '"';
        return String(obj);
    } else {
        // recurse array or object
        var n, v, json = [], arr = (obj && obj.constructor == Array);

        // fix.
        var self = arguments.callee;

        for (n in obj) {
            v = obj[n];
            t = typeof(v);
            if (obj.hasOwnProperty(n)) {
                if (t == "string") v = '"' + v + '"'; else if (t == "object" && v !== null)
                // v = jQuery.stringify(v);
                    v = self(v);
                json.push((arr ? "" : '"' + n + '":') + String(v));
            }
        }
        return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
    }
};

//获取当前时间:yyyy年MM月dd日 hh:mm:ss week
function getCurDate() {
    var d = new Date();
    var week;
    switch (d.getDay()) {
        case 1:
            week = "星期一";
            break;
        case 2:
            week = "星期二";
            break;
        case 3:
            week = "星期三";
            break;
        case 4:
            week = "星期四";
            break;
        case 5:
            week = "星期五";
            break;
        case 6:
            week = "星期六";
            break;
        default:
            week = "星期天";
    }
    var years = d.getFullYear();
    var month = add_zero(d.getMonth() + 1);
    var days = add_zero(d.getDate());
    var hours = add_zero(d.getHours());
    var minutes = add_zero(d.getMinutes());
    var seconds = add_zero(d.getSeconds());
    var ndate = years + "年" + month + "月" + days + "日 " + hours + ":" + minutes + ":" + seconds + " " + week;
    return ndate;
}

//获取当前时间:yyyy-MM-dd
function getCurTime() {
    var d = new Date();
    var years = d.getFullYear();
    var month = add_zero(d.getMonth() + 1);
    var days = add_zero(d.getDate());

    var ndate = years + "-" + month + "-" + days;
    return ndate;
}

//获取当前时间:yyyy-MM
function getCurTimeM() {
    var d = new Date();
    var years = d.getFullYear();
    var month = add_zero(d.getMonth() + 1);

    var ndate = years + "-" + month
    return ndate;
}

function add_zero(temp) {
    if (temp < 10) return "0" + temp;
    else return temp;
}

//计算弹出窗口的顶部位置
function calcWT(wId) {
    var scrollTop = $(window).scrollTop();
    $(wId).window('move', {
        top: scrollTop + 100
    });
}

//弹出dialog的位置
function calcDT(wId) {
    var scrollTop = $(window).scrollTop();
    $(wId).dialog('move', {
        top: scrollTop + 300
    });
}

var alert = function (obj) {
    if (checkOutermost()) {//最外层
        $.messager.alert('提示', obj);
    } else {
        parent.alert(obj);
    }
};

var messagerConfirm = function (tit, obj, fun) {
    if (checkOutermost()) {//最外层
        $.messager.confirm(tit, obj, fun);
    } else {
        parent.messagerConfirm(tit, obj, fun);
    }
}

function trim(str) {
    return $.trim(str);
}

function replaceAll(str, strOld, strNew) {

    do {
        str = str.replace(strOld, strNew);
    } while (str.indexOf(strOld) != -1);

    return str;
}

function testRgexp(s) {// 参数说明 re 为正则表达式   s 为要判断的字符
    var re = /^[0-9]*[1-9][0-9]*$/;
    return re.test(s)
}

var curUser = '';

function getCurUserJson() {
    $.ajax({
        url: "sysUserAction!getCurUserJson.action",
        dataType: 'json',
        async: false,
        type: 'post',
        success: function (result) {
            curUser = result;
        },
        error: commerror
    });
}


function getCurUser() {
    getCurUserJson();

    var us = curUser.userName;

    if (us == '') {
        us = curUser.userCode;
    }

    return us;
}

/*获取地址栏参数*/
function getParameter(param) {
    var query = window.location.search;
    var iLen = param.length;
    var iStart = query.indexOf(param);
    if (iStart == -1)
        return "";
    iStart += iLen + 1;
    var iEnd = query.indexOf("&", iStart);
    if (iEnd == -1)
        return query.substring(iStart);

    return query.substring(iStart, iEnd);
}

/**
 * 日期加减天数
 * @param dateval 日期字符串
 * @param num 加减天数正负区分
 */
function dateAddOrSub(year, month, day, num) {
    var tempdate = new Date(year, month, day);
    tempdate.setDate(tempdate.getDate() + 1);
    return (tempdate.getFullYear() + "-" + (tempdate.getMonth() + 1) + "-" + tempdate.getDate());
}

function openNewWin(url, targ) {

    window.open(url, target = targ);

}

function findCutRules() {
    var cutText = "";
    $.ajax({
        url: "hotelCutRulesAction!findGrid.action",
        dataType: 'json',
        async: false,
        type: 'post',
        success: function (result) {
            //var cut00 = "1、 退房规则<br/>";
            var cut00 = "退房规则<br/>";
            //var cut11 = "2、 改签规则<br/>";
            $.each(result.root, function (i, n) {

                var type = n.cutRulesType;

                if ('11' == type) {
                    //cut11 += '&nbsp;&nbsp;&nbsp;&nbsp;'+n.remark+'<br/>';
                } else {
                    cut00 += '&nbsp;&nbsp;&nbsp;&nbsp;' + n.remark + '<br/>';
                }

            });
            //cutText = cut00 + cut11 + '<br/>';

            cutText = cut00 + '<br/>';
        },
        error: commerror
    });
    return cutText;
}

function creatImgBut(butId, img1, img2, defImg) {

    butId = "#" + butId;
    $(butId).attr('class', 'butImg');
    if (typeof(defImg) != "undefined" && defImg != '') {
        $(butId).attr("src", defImg);
    } else {
        $(butId).attr("src", img1);
    }

    $(butId).mousemove(function (e) {
        $(butId).attr("src", img2);
    });

    $(butId).mouseout(function () {
        $(butId).attr("src", img1);
    });

}

/*大写键锁定检查*/
function detectCapsLock(event) {
    var e = event || window.event;
    var o = e.target || e.srcElement;
    var oTip = document.getElementById('bigWord');
    var keyCode = e.keyCode || e.which; // 按键的keyCode
    var isShift = e.shiftKey || (keyCode == 16) || false; // shift键是否按住
    if (
        ((keyCode >= 65 && keyCode <= 90) && !isShift) // Caps Lock 打开，且没有按住shift键
        || ((keyCode >= 97 && keyCode <= 122) && isShift)// Caps Lock 打开，且按住shift键
    ) {
        oTip.style.display = '';
    }
    else {
        oTip.style.display = 'none';
    }
}

/*公告*/
var nbTop = 0;
var nbTop2 = 20;
var suspend = "n";
var notsList;
var notNums = 0;
var suspendByM = "n";

function findNotice() {
    $.ajax({
        url: "baseNoticeAction!findGridAll.action",
        dataType: 'json',
        async: false,
        type: 'post',
        success: function (result) {
            notsList = result;

            if (result.length > 1) {
                $('#noticeB').html('<a href="javaScript:" onclick="opNotsListPage();">更多公告</a>');
            }

            setNot('noticeBoard');
            setNot('noticeBoard2');
            changeNB();
        },
        error: commerror
    });
}

function opNotsListPage() {
    popup('notListPage', 'baseNoticeAction!queryList.action', '公告', 800, 600,
        {}, function () {
        });
}

function setNot(id) {
    var allNotCount = notsList.length;
    if (allNotCount < 1) {
        return;
    }
    if (notNums == allNotCount) {
        notNums = 0;
    }
    $('#' + id).html('<a href="javaScript:" onclick="opNotsPage(\'' + notsList[notNums].noticeTitle + '\',\'' + notsList[notNums].remark + '\');">' + notsList[notNums].noticeTitle + '</a>');
    notNums++;
}

function opNotsPage(noTit, noRe) {
    popup('notPage', 'baseNoticeAction!detail.action', '公告', 800, 600,
        {thisNoTit: noTit, thisNoRe: noRe}, function () {
        });
}

function suspendNB(showId, hideId) {
    suspendByM = "y";

    $('#' + showId).css("top", "-20px");
    $('#' + hideId).css("top", "0px");
    setNot(hideId);

}

function changeNB() {
    if (suspendByM == "y") {
        return;
    }
    suspend = "n";
    if (nbTop == -40) {
        nbTop = 0;
        setNot('noticeBoard');
    }
    if (nbTop == -20) {
        suspend = "y";
        setTimeout(function () {
            changeNB();
        }, 2000);
    }

    if (nbTop2 == -40) {
        nbTop2 = 0;
        setNot('noticeBoard2');
    }
    if (nbTop2 == -20) {
        suspend = "y";
        setTimeout(function () {
            changeNB();
        }, 2000);
    }
    $('#noticeBoard').css("top", nbTop + "px");
    $('#noticeBoard2').css("top", nbTop2 + "px");
    nbTop--;
    nbTop2--;
    if (suspend == "n") {
        setTimeout(function () {
            changeNB();
        }, 200);
    }


}

//获取table中数据组成json字符串
function crTabkeParam(vals, rows) {

    var parameters = "[{";
    $.each(vals, function (i, field) {
        var douhao = true;
        if (field.name.indexOf('.') != -1) {

            var front = '';
            var rear = '';
            if (parameters.indexOf('},{') != -1) {
                var params = parameters.split('},{');
                for (var ii = 0; ii < params.length; ii++) {

                    if (ii == (params.length - 1)) {
                        rear = params[ii];
                    } else {
                        front += params[ii] + '},{';
                    }

                }
            } else {
                rear = parameters;
            }


            var subObjName = field.name.substring(0, field.name.indexOf('.'));
            if (rear.indexOf(subObjName) != -1) {
                var subObjCh = field.name.substring(field.name.indexOf('.') + 1, field.name.length) + ":'" + field.value + "',";
                rear = rear.replace(subObjName + ":{", subObjName + ":{" + subObjCh);
                douhao = false;
            } else {
                rear += field.name.substring(0, field.name.indexOf('.')) + ":{";
                rear += field.name.substring(field.name.indexOf('.') + 1, field.name.length) + ":'" + field.value + "'}";
            }

            parameters = front + rear;
        }
        else {
            parameters += field.name + ":'" + field.value + "'";
        }

        if ((i + 1) % rows == 0) {//修改每一行的回传列数
            if ((i + 1) == vals.length) {
                parameters += "}]";
            }
            else {
                parameters += "},{";
            }
        }
        else if (douhao) {
            parameters += ",";
        }

    });

    do {
        parameters = parameters.replace(",}]", "}]");
    } while (parameters.indexOf(",}]") != -1);

    do {
        parameters = parameters.replace(",},{", "},{");
    } while (parameters.indexOf(",},{") != -1);

    return parameters;
}

//页面高度计算
function calcHeigt(childH, increment) {

    if (thisPopup || parent == null) {//如果是弹出窗口则不计算
        return;
    }

    if (parseInt(childH) != childH) {
        childH = $('body > div').height();
    }
    //alert(childH);

    if (checkOutermost()) {//最外层
        $('#center').height(childH + 100);
    } else {
        var inc = 0;
        if (parseInt(increment) == increment) {
            inc = increment;
        }
        childH = childH + inc;
        if ($('#homeCenter').attr('id') == 'homeCenter') {//首页
            $('#homeCenter').height(childH + 50);
        } else if ($('#regCenter').attr('id') == 'regCenter') {//注册页面
            $('#regCenter').height(childH + 50);
        } else if ($('#loginCenter').attr('id') == 'loginCenter') {//登录页面
            $('#loginCenter').height(childH + 50);
        } else if ($('#loginInCenter').attr('id') == 'loginInCenter') {//登录后的页面

            var leftH = $("#leftMenu").contents().find('body > div').height();
            var cenH = $("#loginInCenter").contents().find('body > div').height();

            if (leftH > cenH) {
                childH = leftH + inc;
            } else {
                childH = cenH + inc;
            }

            $('#leftMenu').height(childH + 50);
            $('#loginInCenter').height(childH + 50);
        }

        parent.calcHeigt(childH, increment);
    }
}

//弹出窗口方法
var winParam = {};
var popupNums = 0;
var thisPopup = false;

var popup = function (id, url, title, width, height, params, closeFun, winAttr) {
    if (checkOutermost()) {//最外层
        for (var key in params) {
            winParam[key] = params[key];
        }

        $("body").append('<div id="' + id + '" align="center" style="padding:5px;width:' + width + 'px;height:' + height + 'px;"></div>');

        var windowAttr = {
            title: title,
            iconCls: 'icon-edit',
            minimizable: false,
            maximizable: false,
            collapsible: false,
            resizable: false,
            draggable: false,
            closed: true,
            onClose: function () {
                closeFun();
                $('#' + id).remove();

                popupNums--;

                if (popupNums < 1) {
                    winParam = {};
                    thisPopup = false;
                }
            }
        };
        if ('undefined' != winAttr) {
            for (var key in winAttr) {
                windowAttr[key] = winAttr[key];
            }
        }
        $('#' + id).window(windowAttr);


        thisPopup = true;
        calcWT('#' + id);
        $('#' + id).window('open').window('refresh', url);
        popupNums++;
    } else {
        parent.popup(id, url, title, width, height, params, closeFun, winAttr);
    }
}

//关闭弹出窗口方法
var closePopup = function (id) {

    if (checkOutermost()) {
        $('#' + id).window("close");
    } else {
        parent.closePopup(id);
    }

}


//弹出对话框方法

var openDialog = function (id, title, width, height, html, closeFun, winAttr) {
    if (checkOutermost()) {//最外层

        $("body").append('<div id="' + id + '" align="center" style="padding:5px;width:' + width + 'px;height:' + height + 'px;">' + html + '</div>');

        var windowAttr = {
            title: title,
            iconCls: 'icon-ok',
            minimizable: false,
            maximizable: false,
            collapsible: false,
            resizable: false,
            draggable: false,
            closable: false,
            modal: true,
            closed: true,
            onClose: function () {
                closeFun();
                $('#' + id).remove();
            }
        };
        if ('undefined' != winAttr) {
            for (var key in winAttr) {
                windowAttr[key] = winAttr[key];
            }
        }
        $('#' + id).dialog(windowAttr);

        calcDT('#' + id);
        $('#' + id).dialog('open');
    } else {
        parent.openDialog(id, title, width, height, html, closeFun, winAttr);
    }
}

//关闭弹出对话框方法
var closeDialog = function (id) {

    if (checkOutermost()) {
        $('#' + id).dialog("close");
    } else {
        parent.closeDialog(id);
    }

}


/**
 * 跳转页面
 * @param iframeId
 * @param url
 */
function goToURL(iframeId, url) {

    var out = false;
    $.each($('iframe'), function (i, n) {
        if (n.id == iframeId) {
            out = true;
        }
    });

    if (out) {//本层有iframeId的iframe
        $('#' + iframeId).attr('src', url);
        jumpTop();
    } else if (checkOutermost()) {//最外层
        alert('您传入的iframeId不存在！');
    } else {
        parent.goToURL(iframeId, url);
    }
}

/**
 * 跳到页头位置
 */
function jumpTop() {
    if (checkOutermost()) {
        window.scrollTo(0, 0);
        //window.location.href = '#top';
    } else {
        parent.jumpTop();
    }

}

function windowHref(url) {
    if (checkOutermost()) {
        window.location.href = url;
    } else {
        parent.windowHref(url);
    }
}

/**
 * 判断是否登录并改变页头按钮
 * @param loginPage
 */
function changTop(loginPage) {
    if (checkOutermost()) {
        changLogin(loginPage);
    } else {
        parent.changTop(loginPage);
    }
}

/**
 * 页头时间
 */
var log = '';

function getTime() {
    if (checkOutermost()) {
        $("#top").contents().find('#showTime').html(getCurDate() + log);
        setTimeout(function () {
            getTime();
        }, 100);
    } else {
        parent.getTime();
    }
}

/**
 * 页头时间按钮控制
 */
function changLogin(loginPage) {
    $("#top").contents().find('#outBut').hide();
    if ('loginPage' == loginPage) {//已登录
        var tagShow = true;
        $.ajax({
            url: "sysUserAction!realPrintShow.action",
            dataType: 'json',
            async: false,
            type: 'post',
            success: function (result) {
                tagShow = result;
            },
            error: commerror
        });
        if (tagShow) {
            $("#top").contents().find('#realDataDiv').show();
            refreshPrint = true;
            realPrintShow();
        } else {
            $("#top").contents().find('#realDataDiv').hide();
            refreshPrint = false;
        }
        $("#top").contents().find('#butDiv').hide();
        $("#top").contents().find('#outBut').show();
        $("#top").contents().find('#loginUser').html(getCurUser() + ' | ');
        log = ' | ';
    } else {//未登录
        refreshPrint = false;
        $("#top").contents().find('#realDataDiv').hide();
        $("#top").contents().find('#butDiv').show();
        $("#top").contents().find('#loginUser').html('');
        log = '';
    }

}

var refreshPrint = false;

/**
 * 获取实时订单数据
 */
function realPrintShow() {

    $.ajax({
        url: "jumpAction!realPrintShow.action",
        dataType: 'json',
        async: false,
        type: 'post',
        success: function (result) {
            var ticketNums = result.t;
            $.each(ticketNums, function (i, ticketNum) {
                $("#top").contents().find('#t' + ticketNum.payType).html(ticketNum.nums);
            });
            $("#top").contents().find('#h').html(result.h);
        },
        error: commerror
    });

    if (refreshPrint) {
        setTimeout(function () {
            realPrintShow();
        }, 5000);
    }
}


function creatTicketReminder1(useDate, idCardNumber, phoneNumber, checkCode, paid, indentType, telNumber, customerType, faxNumber) {
    if ("02" == indentType && "01" == customerType && "1" == telNumber && "" == checkFaxNumber(faxNumber)) {
        var paStr = "下单成功，请在付款后";
        var rem = paStr + "于<font color='red' style='font: 14px/35px \"宋体\";font-weight: bold;'>" + useDate + "</font>到景区自助取票机处，";

        if (checkRemark(idCardNumber).length == 18) {
            rem += "凭取票人二代身份证原件<font color='red' style='font: 14px/35px \"宋体\";font-weight: bold;'>“" + idCardNumber + "”</font>取票。二代身份证原件是您唯一的取票凭证，请确保携带。";
        }
        return rem;
    } else {
        return creatTicketReminder(useDate, idCardNumber, phoneNumber, checkCode, paid);
    }
}

function creatTicketReminder(useDate, idCardNumber, phoneNumber, checkCode, paid) {
    var paStr = "下单成功，请在付款后";
//	if("y"==paid){
//		paStr = "购票已成功，";
//	}else{
//		paStr = "购票成功后，";
//	}
    var rem = paStr + "于<font color='red' style='font: 14px/35px \"宋体\";font-weight: bold;'>" + useDate + "</font>到景区自助取票机处，";

    if (checkRemark(idCardNumber).length == 18 && checkRemark(phoneNumber).length > 0) {
        rem += "凭取票人身份证<font color='red' style='font: 14px/35px \"宋体\";font-weight: bold;'>“" + idCardNumber + "”</font>或取票人手机号<font color='red' style='font: 14px/35px \"宋体\";font-weight: bold;'>“" + phoneNumber + "”</font>加验证码<font color='red' style='font: 14px/35px \"宋体\";font-weight: bold;'>“" + checkCode + "”</font>取票。验证码是您唯一的交易凭证，请妥善保管，如有泄漏，后果自负。";
    } else if (checkRemark(idCardNumber).length == 18) {
        rem += "凭取票人身份证<font color='red' style='font: 14px/35px \"宋体\";font-weight: bold;'>“" + idCardNumber + "”</font>取票。如您的二代身份证无法读取请手动录入取票人身份证加验证码<font color='red' style='font: 14px/35px \"宋体\";font-weight: bold;'>“" + checkCode + "”</font>取票。验证码是您唯一的交易凭证，请妥善保管，如有泄漏，后果自负。";
    } else if (checkRemark(phoneNumber).length > 0) {//没有填身份证
        rem += "凭取票人手机号<font color='red' style='font: 14px/35px \"宋体\";font-weight: bold;'>“" + phoneNumber + "”</font>加验证码<font color='red' style='font: 14px/35px \"宋体\";font-weight: bold;'>“" + checkCode + "”</font>取票。验证码是您唯一的交易凭证，请妥善保管，如有泄漏，后果自负。";
    }
    return rem;
}

function checkRemark(remark) {
    if (trim(remark) == 'undefined' || trim(remark) == 'home' || trim(remark) == 'act' || trim(remark) == '&nbsp;') {
        remark = '';
    }
    return remark;
}

function checkFaxNumber(faxNumber) {
    if (trim(faxNumber) == 'undefined' || trim(faxNumber) == '' || trim(faxNumber) == '&nbsp;') {
        faxNumber = '';
    }
    return faxNumber;
}

/**
 * 从后台获取系统时间;
 */
function findDay() {
    var value = '';
    $.ajax({
        url: 'jumpOverAction!findDay.action',
        data: '',
        dataType: 'json',
        async: false,
        type: 'post',
        success: function (result) {
            value = result.success;
        },
        error: commerror
    });
    return value;
}
