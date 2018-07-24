/**
 * 查询地区
 */
function setAreaCode() {
    var areaCode = $('#county').val();
    if (areaCode == null || '' == areaCode || areaCode.length != 6) {
        areaCode = $('#city').val();
    }
    if (areaCode == null || '' == areaCode || areaCode.length != 6) {
        areaCode = $('#province').val();
    }
    if (areaCode == null || '' == areaCode || areaCode.length != 6) {
        areaCode = '';
    }
    $('#areaCode').val(areaCode);
}

function findProvince(areaCode) {
    $('#province').html("");
    $.ajax(
        {
            url: 'sysAreaAction!findProvince.action?s=' + Math.random(),
            dataType: 'json',
            type: 'post',
            async: false,
            success: function (result) {
                var htmlStr = "";
                $.each(result, function (i, n) {
                    var str = "";
                    if ('' != areaCode && areaCode.length == 6) {
                        var value = areaCode.substr(0, 2) + '0000';
                        if (n.areaCode == value) {
                            str = " selected='selected'";
                            findCity(n.areaCode, areaCode);
                        }
                    } else {
                        if (i == 0) {
                            findCity(n.areaCode);
                        }
                    }

                    htmlStr += "<option value='" + n.areaCode + "'" + str + ">" + n.areaName + "</option>";

                });
                $('#province').html(htmlStr);
                var width = calcASelectW('#province');
                $('#province').width(width);
            }
        });
}

function findCity(parentCode, areaCode) {
    $('#city').html("");
    $.ajax(
        {
            url: 'sysAreaAction!findCity.action?s=' + Math.random(),
            dataType: 'json',
            data: 'node=' + parentCode,
            type: 'post',
            async: false,
            success: function (result) {
                var htmlStr = "";
                $.each(result, function (i, n) {
                    var str = "";
                    if (areaCode != null && '' != areaCode && areaCode.length == 6) {
                        var value = areaCode.substr(0, 4) + '00';
                        if (n.areaCode == value) {
                            str = " selected='selected'";
                            findCounty(n.areaCode, areaCode);
                        }
                    } else {
                        if (i == 0) {
                            findCounty(n.areaCode);
                        }
                    }
                    htmlStr += "<option value='" + n.areaCode + "'" + str + ">" + n.areaName + "</option>";

                });
                $('#city').html(htmlStr);
                var width = calcASelectW('#city');
                $('#city').width(width);
                $('#city').attr('style', 'width: ' + width + 'px;');
                if (htmlStr == "") {
                    $('#city').hide();
                    $('#city').val("");
                    $('#county').html("");
                    $('#county').hide();
                    $('#county').val("");
                } else {
                    $('#city').show();
                }
            }
        });

    //$('#city').attr('style','width: 70px;');
}

function findCounty(parentCode, areaCode) {
    $('#county').html("");
    $.ajax(
        {
            url: 'sysAreaAction!findCounty.action?s=' + Math.random(),
            dataType: 'json',
            data: 'node=' + parentCode,
            type: 'post',
            async: false,
            success: function (result) {
                var htmlStr = "";
                $.each(result, function (i, n) {
                    var str = "";
                    if (areaCode != null && '' != areaCode && areaCode.length == 6) {
                        if (n.areaCode == areaCode) {
                            str = " selected='selected'";
                        }
                    }
                    htmlStr += "<option value='" + n.areaCode + "'" + str + ">" + n.areaName + "</option>";
                });
                $('#county').html(htmlStr);
                var width = calcASelectW('#county');
                $('#county').width(width);
                $('#county').attr('style', 'width: ' + width + 'px;');
                if (htmlStr == "") {
                    $('#county').hide();
                    $('#county').val("");
                } else {
                    $('#county').show();
                }
                setAreaCode();
            }
        });
    //$('#county').attr('style','width: 70px;');
}

function calcASelectW(sId) {
    var width = 0;
    $.each($(sId + " > option"), function (i, n) {
        //var nWidth = n.innerHTML.length*17;
        var nWidth = n.innerHTML.length * 16;
        if (parseInt(nWidth) > parseInt(width)) {
            width = nWidth;
        }
    });
    return width + 20;
}


function selectAreaByCode(areaCode) {
    var re = '';
    $.ajax(
        {
            url: 'sysAreaAction!findArea.action?s=' + Math.random(),
            dataType: 'json',
            data: 'node=' + areaCode,
            type: 'post',
            async: false,
            success: function (result) {
                re = result.area;
            }
        });
    return re;
}

