var addevents;

function loadGrid(tableid, params, ind) {
    var totalNumVal = 0;
    var thisJson = "";

    addevents = new Array();

    var cid = "#" + tableid;
    if (!$(cid).attr('data-options')) {
        return;
    }
    var jsonTableOptions = eval("({" + $(cid).attr('data-options') + "})");
    var pd = "";
    if (jsonTableOptions.paging) {//需要翻页
        if (!ind) {//无页码时
            ind = $(cid).attr("cp");//取保存当前页码
        }

        if (typeof(ind) == "undefined") {//无页码显示第一页
            pd = "start=0&limit=" + jsonTableOptions.paging + "&" + params;
        }
        else {
            if (ind <= 0) {
                ind = 1;
            }
            pd = "start=" + (ind - 1) * jsonTableOptions.paging + "&limit=" + jsonTableOptions.paging + "&" + params;
        }
        $(cid).attr("cp", ind);
    }
    else {//无需翻页
        pd = "start=0&limit=500&" + params;
    }
    var optionsTr = 0;
    if (typeof(jsonTableOptions.optionsTr) != "undefined") {
        optionsTr = jsonTableOptions.optionsTr;
    }

    var tds = $(cid).find("tr:eq(" + optionsTr + ") th");
    var trOptions = "";
    if ($(cid).find("tr:eq(0)").attr('data-options')) {
        trOptions = eval("({" + $(cid).find("tr:eq(0)").attr('data-options') + "})");
    }
    var totalFileds;
    var totalCn = 100;
    var footer = "";
    var footerJson;
    if (jsonTableOptions.showFooter) {//合计
        if (jsonTableOptions.footerFiled) {
            totalFileds = jsonTableOptions.footerFiled.split(",");
            $.each(totalFileds, function (i, n) {
                $.each(tds, function (j, t) {
                    var tdOptions = eval("({" + $(this).attr("data-options") + "})");
                    if (tdOptions.hidden) {
                        $(this).attr("style", "display: none");
                    }
                    if (tdOptions.filed && n == tdOptions.filed && totalCn > j) {
                        totalCn = j;
                    }
                });
                footer += n + ":0,";
            });
        }
        footerJson = eval("({" + footer.substring(0, footer.length - 1) + "})");
    }
    else {
        $.each(tds, function (j, t) {
            var tdOptions = eval("({" + $(this).attr("data-options") + "})");
            if (tdOptions.hidden) {
                $(this).attr("style", "display: none");
            }
        });
    }
    if (totalCn == 100) {
        totalCn = 0;
    }

    $.ajax({
        type: "post",
        dataType: "json",
        url: jsonTableOptions.url,// 目标地址
        data: pd,
        async: false,
        success: function (json) {
            thisJson = json;
            var result = json.root;
            var tbody = "";
            var tbname = cid + " tr:gt(" + optionsTr + ")";
            $(tbname).remove();
            var tboname = cid + " tbody:gt(" + optionsTr + ")";
            $(tboname).remove();
            tbody += "<tbody>";
            $.each(
                result,
                function (i, n) {
                    var cla = " class='cellColor_b' onmouseover=this.className='cellChangeColor' onMouseOut=this.className='cellColor_b'";
                    if (i % 2 == 0)
                        cla = " class='cellColor' onmouseover=this.className='cellChangeColor' onMouseOut=this.className='cellColor'";

                    var tr = "<tr id='row_" + (i + 1) + "' " + cla;
                    if (trOptions && trOptions != "" && trOptions.titleId) {
                        tr += " title='" + getFiledVal(n, trOptions.titleId) + "'";
                    }
                    tr += " >";

                    $.each(tds, function (j) {
                        var tdOptions = eval("({" + $(this).attr("data-options") + "})");
                        if (tdOptions.img || tdOptions.edit || tdOptions.del || tdOptions.control || tdOptions.render) {
                            tr += "<td align='center' class='time_nu'";
                        }
                        else {
                            tr += "<td";
                        }

                        if (tdOptions.css) {
                            tr += " class='" + tdOptions.css + "'";
                        }
                        //align属性设置
                        if (tdOptions.align) {
                            tr += " align='" + tdOptions.align + "'";
                        }
                        else if (tdOptions.dataType == "money") {
                            tr += " align='right'";
                        }
                        else if (tdOptions.rownumbers) {
                            tr += " align='center'";
                        }


                        if (tdOptions.hidden) {//是否hidden
                            tr += " style='display: none'";
                        }
                        tr += ">";

                        var cval = getFiledVal(n, tdOptions.filed);//当前值

                        if (tdOptions.render) {
                            cval = eval(tdOptions.render)((i + 1), n);
                        }
                        if (tdOptions.edit) {
                            tr += "<a href='javascript:' onclick='" + tdOptions.editFun + "(" + (i + 1) + ")'>修改</a>";
                        }
                        if (tdOptions.del) {
                            tr += "    <a href='javascript:' onclick='" + tdOptions.delFun + "(" + getFiledVal(n, tdOptions.del) + ")'>删除</a>";
                        }
                        if (tdOptions.img) {
                            var imgName = tdOptions.imgName;
                            imgName += cval + '.gif';
                            if (cval != "") {
                                tr += '<img src="img/' + imgName + '" border="0"/>';
                            }
                            cval = "";
                        }
                        if (tdOptions.control) {
                            $.each(tdOptions.control, function (k, ct) {
                                if (ct.controlId) {
                                    ct.controlId = getFiledVal(n, ct.controlId);
                                }
                                tr += "    " + getEditor(ct, cval, i);
                            });
                        }
                        else {
                            if (tdOptions.dataType == "date") {
                                cval = cval.substring(0, 10);
                            }
                            else if (tdOptions.dataType == "money") {
                                if (parseFloat(cval) != cval) {
                                    cval = 0;
                                }
                                cval = parseFloat(cval).toFixed(2);
                            }
                            if (tdOptions.rownumbers) {
                                cval = parseInt(i + 1);
                            }
                            if (cval == "") {//ie6下无数据的td不会产生导致样式变形，需添加空数据让td生成出来
                                cval = "&nbsp;";
                            }
                            if (tdOptions.showEditor) {
                                var editorObj = getEditor(tdOptions.editor, cval, i, tableid);
                                tr += editorObj;
                                if (tdOptions.editor.easyuiEvent) {
                                    addEasyuiEvent(editorObj, tdOptions.editor, cval, i);
                                }
                            }
                            else {
                                tr += cval;
                            }
                            if (jsonTableOptions.showFooter && isFooter(tdOptions.filed, totalFileds)) {
                                if (parseFloat(cval) != cval) {
                                    cval = 0;
                                }
                                footerJson[tdOptions.filed] += parseFloat(cval);
                            }
                        }

                        tr += "</td>";
                    });
                    tr += "</tr>";
                    tbody += tr;
                });
            tbody += "</tbody>";
            if (jsonTableOptions.autoTr == "auto") {
                tbody += "<tbody id='autoTr'></tbody>";
            }
            if (jsonTableOptions.showFooter) {
                tbody += "<tr class='cellFooter'>";
                var hiddennum = 0;


                $.each(tds, function (i, n) {
                    var tdOptions = eval("({" + $(this).attr("data-options") + "})");
                    if (i < (totalCn - 1) && !tdOptions.hidden) {
                        tbody += "<td>&nbsp;</td>";//ie6下无数据的td不会产生导致样式变形，需添加空数据让td生成出来
                    } else if (i == totalCn - 1 && !tdOptions.hidden) {//显示合计文字列
                        if (tdOptions.align) {
                            tbody += "<td align='" + tdOptions.align + "'";
                        }
                        else if (tdOptions.dataType == "money") {
                            tbody += "<td align='right'";
                        }
                        else {
                            tbody += "<td align='center'";
                        }
                        tbody += ">合计</td>";
                    }
                    else {
                        tbody += "<td";
                        if (tdOptions.hidden) {//是否hidden
                            tbody += " style='display: none'";
                        }
                        //设置align属性
                        if (tdOptions.align) {
                            tbody += " align='" + tdOptions.align + "'";
                        }
                        else if (tdOptions.dataType == "money") {
                            tbody += " align='right'";
                        }

                        tbody += ">";
                        if (isFooter(tdOptions.filed, totalFileds)) {
                            if (isNaN(trim(footerJson[tdOptions.filed] + "")) && tdOptions.dataType == "money") {
                                tbody += "<font style=\"font: bold 14px '黑体';color: #fe7600;\" id='" + tableid + "_footer_" + tdOptions.filed + "'>" + parseFloat(0).toFixed(2) + "</font>";
                            }
                            else if (tdOptions.dataType == "money") {
                                tbody += "<font style=\"font: bold 14px '黑体';color: #fe7600;\" id='" + tableid + "_footer_" + tdOptions.filed + "'>" + parseFloat(trim(footerJson[tdOptions.filed] + "")).toFixed(2) + "</font>"
                            }
                            else {
                                tbody += "<font style=\"font: bold 14px '黑体';color: #fe7600;\" id='" + tableid + "_footer_" + tdOptions.filed + "'>" + trim(footerJson[tdOptions.filed] + "") + "</font>"
                            }
                        }
                        else {
                            tbody += "&nbsp;"
                        }
                        tbody += "</td>";
                    }
                });
                tbody += "</tr>";
            }
            $(cid).append(tbody);
            //添加翻页
            if (jsonTableOptions.paging) {
                var pagecontent = "";
                pagtecontent = getPagecontent(json, tableid, params);
                $("#" + jsonTableOptions.pageId).html(pagtecontent);
            }

            totalNumVal = json.total;

            for (var int = 0; int < addevents.length; int++) {//添加easyui的事件
                var eventStr = addevents[int];
                eval(eventStr);
            }

            calcHeigt();
        },
        error: commerror
    });

    var returnJson = {
        totalNum: totalNumVal,
        json: thisJson

    };

    if (typeof(jsonTableOptions.loadEndFun) != "undefined") {
        eval(jsonTableOptions.loadEndFun)(returnJson);
    }


    return returnJson;
}

/**
 * 判断该字段是否需要合计
 * @param val 字段名
 * @param totalFileds 统计字段集合
 */
function isFooter(filed, totalFileds) {
    var rtval = false;
    if (filed) {
        $.each(totalFileds, function (i, n) {
            if (filed == n) {
                rtval = true;
            }
        });
    }
    return rtval;
}

/**
 * 通过属性从json中取出数据
 * @param jsonobj
 * @param filed
 * @returns {String}
 */
function getFiledVal(jsonobj, filed) {
    if (!filed) {
        return "";
    }
    var fileds = filed.split(".");
    var rtval = jsonobj;
    $.each(fileds, function (i, n) {
        if (rtval[fileds[i]] || rtval[fileds[i]] == 0) {
            rtval = rtval[fileds[i]];
        }
        else {
            rtval = "";
        }
    });
    return rtval;
}

/**
 * 获取编辑对象
 * @param editor json对象
 * @param val 显示值
 * @param rowid 行号
 * @returns {String}
 */
function getEditor(editor, val, rowid, ctid) {
    var cval = val;
    var rteditor = "";
    if (typeof(val) == "undefined") {
        cval = "";
    }
    //前
    if (editor.type == 'text') {
        rteditor += "<input type='text' ";
    }
    else if (editor.type == 'a') {
        rteditor += "<a ";
    }
    else if (editor.type == 'img') {
        rteditor += "<img ";
    }
    //中
    for (var key in editor) {
        if (key == 'css') {
            rteditor += " class='" + editor[key] + "'";
        }
        else if (key == 'fun') {
            if (editor.event) {
                if (editor.controlId) {
                    rteditor += " " + editor.event + "='" + editor[key] + "(\"" + editor.controlId + "\"," + (rowid + 1) + ")'";
                }
                else {
                    rteditor += " " + editor.event + "='" + editor[key] + "(" + (rowid + 1) + ")'";
                }
            }
            else {
                if (editor.controlId) {
                    rteditor += " onclick='" + editor[key] + "(\"" + editor.controlId + "\"," + (rowid + 1) + ")'";
                }
                else {
                    rteditor += " onclick='" + editor[key] + "(" + (rowid + 1) + ")'";
                }
            }
        }
        else if (key == 'onchange') {
            if (editor.controlId) {
                rteditor += " onchange='" + editor[key] + "(\"" + editor.controlId + "\"," + (rowid + 1) + ")'";
            }
            else {
                rteditor += " onchange='" + editor[key] + "(" + (rowid + 1) + ")'";
            }
        }
        else if (key == 'type') {
        }
        else if (key == 'controlId') {
        }
        else if (key == 'event') {
        }
        else if (key == 'text') {
        }
        else if (key == 'easyuiEvent') {
        }
        else if (key == 'options') {
            rteditor += " data-options='" + editor[key] + "'";
        }
        else if (key == 'id') {
            if (typeof(ctid) == "undefined") {
                rteditor += " id='" + editor[key] + "_" + (rowid + 1) + "'";
            }
            else {
                rteditor += " id='" + ctid + "_" + editor[key] + "_" + (rowid + 1) + "'";
            }
        }
        else {
            rteditor += " " + key + "=" + editor[key];
        }
    }
    //后
    if (editor.type == 'text') {
        rteditor += " value='" + cval + "' />";
    }
    else if (editor.type == 'a') {
        if (editor.text) {
            rteditor += ">" + editor.text + "</a>";
        }
        else {
            rteditor += ">" + cval + "</a>";
        }
    }
    else if (editor.type == 'img') {
        rteditor += " />";
    }


    return rteditor;
}

/**
 * 为easyui添加事件响应
 * @param editor json
 * @param val 当前值
 * @param rowid 行号
 */
function addEasyuiEvent(obj, editor, val, rowid) {
    var cval = val;
    var rteditor = "";
    if (typeof(val) == "undefined") {
        cval = "";
    }
    var jsstr = "";

    if (editor.css == 'easyui-numberspinner') {
        jsstr += '$("#' + $(obj).attr("id") + '").numberspinner({';
        for (var key in editor.easyuiEvent) {
            jsstr += key + ':function(){';
            if (editor.controlId) {
                rteditor += " " + editor.event + "='" + editor[key] + "(\"" + editor.controlId + "\"," + (rowid + 1) + ")'";
                jsstr += editor.fun + '("' + editor.controlId + '",' + (rowid + 1) + ');' +
                    '}';
            }
            else {
                jsstr += editor.fun + '(' + (rowid + 1) + ');' +
                    '},';
            }
        }
        jsstr = jsstr.substring(0, jsstr.length - 1);
        jsstr += '});';
    }
    addevents.push(jsstr);
}


/**
 * 翻页
 * @param json
 * @param tableid
 * @param params
 * @returns {String}
 */
function getPagecontent(json, tableid, params) {
    var pagecontent = "";
    var start = json.start;
    var total = json.total;
    var limit = json.limit;
    var num = parseInt(total / limit);//总页数
    var numy = total % limit;//余数
    var pagenum = 7;//显示翻页数
    var cp = parseInt(start / limit) + 1;
    var pageshow = parseInt((cp - 1) / pagenum);
    if (numy > 0) {
        num = num + 1;
    }
    var first = "";
    var before = "";
    var center = "";

    if (cp > 1) {
        first = "<img src='img/z1.gif' width='14' height='12' onclick='loadGrid(\"" + tableid + "\",\"" + params + "\"," + 1 + ")'/>";
        before = "<img src='img/z2.gif' width='14' height='12' onclick='loadGrid(\"" + tableid + "\",\"" + params + "\"," + (cp - 1) + ")'/>";
    }
    //for ( var int = pageshow*pagenum; int < (pageshow+1)*pagenum; int++) {
    var staP = cp - 4;

    if (staP < 0) {
        staP = 0;
    }
    var endP = staP + pagenum;
    //alert(staP+'::'+endP);
    for (var int = staP; int < endP; int++) {
//		alert(cp);
//		if((int+1) < 1){
//			break;
//		}
        if ((int + 1) > num) {
            break;
        }
        if ((int + 1) == cp) {
            center += "<a href='javaScript:' style=' color:#148aac; font-weight:bold' onclick='loadGrid(\"" + tableid + "\",\"" + params + "\"," + (int + 1) + ")'>[ " + (int + 1) + " ]</a>";
        }
        else {
            center += "<a href='javaScript:' onclick='loadGrid(\"" + tableid + "\",\"" + params + "\"," + (int + 1) + ")'>[ " + (int + 1) + " ]</a>";
        }
    }
    var after = "";
    var last = "";
    if (cp < num) {
        var after = "<img src='img/y1.gif' width='14' height='12' onclick='loadGrid(\"" + tableid + "\",\"" + params + "\"," + (cp + 1) + ")'/>";
        var last = "<img src='img/y2.gif' width='14' height='12' onclick='loadGrid(\"" + tableid + "\",\"" + params + "\"," + num + ")'/>";
    }

    var totalJl = "<span style='margin-right: 20px;margin-left: 50px;color:#148aac; font-weight:bold;'>总计 " + total + " 条记录</span>";

    pagecontent = first + before + center + after + last + totalJl;
    return pagecontent;
}

/**
 * 求合计
 * @param tableid
 */
function totalGrid(tableid) {
    var cid = "#" + tableid;
    if (!$(cid).attr('data-options')) {
        return;
    }
    var jsonTableOptions = eval("({" + $(cid).attr('data-options') + "})");
    var ths = $(cid).find("tr:eq(0) th");
    var trnum = $(cid).find("tr").length;
    var trs = $(cid).find("tr:gt(0):lt(" + trnum + ")");
    var footer = "";
    var footerJson;
    if (jsonTableOptions.showFooter) {//合计
        if (jsonTableOptions.footerFiled) {
            totalFileds = jsonTableOptions.footerFiled.split(",");
            $.each(totalFileds, function (i, n) {
                footer += n + ":0,";
            });
        }
        footerJson = eval("({" + footer.substring(0, footer.length - 1) + "})");
    }
    $.each(trs, function (i, n) {
        var tds = $(this).find("td");
        $.each(ths, function (j, t) {
            var tdOptions = eval("({" + $(t).attr("data-options") + "})");
            if (tdOptions.filed) {
                if (typeof(footerJson[tdOptions.filed]) != "undefined") {//合计值
                    var cval = 0;
                    if (parseFloat($(tds[j]).html())) {
                        cval = $(tds[j]).html();
                    }
                    if (parseFloat($(tds[j]).find("input").val())) {
                        cval = $(tds[j]).find("input").val();
                    }
                    if (parseFloat(cval) != cval) {
                        cval = 0;
                    }
                    footerJson[tdOptions.filed] = parseFloat(footerJson[tdOptions.filed]) + parseFloat(cval);
                    if (tdOptions.dataType == "money") {//money保留小数两位
                        footerJson[tdOptions.filed] = parseFloat(trim(footerJson[tdOptions.filed] + "")).toFixed(2)
                    }
                }
            }
        })
    });
    for (var key in footerJson) {
        var fid = "#" + tableid + "_footer_" + key;
        $(fid).html(footerJson[key]);
    }
}



