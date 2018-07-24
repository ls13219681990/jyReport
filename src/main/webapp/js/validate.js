/*验证字段为数字和字母的组合*/
function isPwd(str) {
    var reg = /^[A-Za-z0-9_]*$/;
    return reg.test(str);
}

/*验证字段为数字的组合*/
function isNumber(str) {
    var reg = /^[0-9]*$/;
    return reg.test(str);
}

/*验证邮箱*/
function isEmail(str) {
    //var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    //var reg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
    var reg = /^[A-Za-z0-9\d]+([-_\.\+]*[A-Za-z0-9\d])*@([a-z0-9\d][a-z0-9\d-]{0,61}[a-z0-9\d]\.)+[a-z\d]{2,6}$/;
    return reg.test(str);
}

/*验证手机号*/
function isPhone(str) {
    var reg = /^(13[0-9]|14[0-9]|15[0-9]|170|177|18[0-9])\d{8}$/;
    return reg.test(str);
}

/*验证固定电话*/
function isTel(str) {
    var reg = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
    return reg.test(str);
}

/***********************身份证验证开始************************/
/*
Author:yufulong
Blog:http://www.xiaoxiaozi.com
*/
var vcity = {
    11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古",
    21: "辽宁", 22: "吉林", 23: "黑龙江", 31: "上海", 32: "江苏",
    33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南",
    42: "湖北", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆",
    51: "四川", 52: "贵州", 53: "云南", 54: "西藏", 61: "陕西", 62: "甘肃",
    63: "青海", 64: "宁夏", 65: "新疆", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外"
};


//检查号码是否符合规范，包括长度，类型
isCardNo = function (card) {

    card = card.replace('x', 'X');

    //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
    //var reg = /(^\d{15}$)|(^\d{17}(\d|X)$)/;
    var reg = /(^\d{17}(\d|X)$)/;
    if (reg.test(card) === false) {
        return false;
    }

    //检查省份
    if (checkProvince(card) === false) {
        return false;
    }

    //校验生日
    if (checkBirthday(card) === false) {
        return false;
    }

    //检验位的检测
    if (checkParity(card) === false) {
        return false;
    }
    return true;
};

//取身份证前两位,校验省份
checkProvince = function (card) {
    var province = card.substr(0, 2);
    if (vcity[province] == undefined) {
        return false;
    }
    return true;
};

//检查生日是否正确
checkBirthday = function (card) {
    var len = card.length;
    //身份证15位时，次序为省（3位）市（3位）年（2位）月（2位）日（2位）校验位（3位），皆为数字
    if (len == '15') {
        var re_fifteen = /^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/;
        var arr_data = card.match(re_fifteen);
        var year = arr_data[2];
        var month = arr_data[3];
        var day = arr_data[4];
        var birthday = new Date('19' + year + '/' + month + '/' + day);
        return verifyBirthday('19' + year, month, day, birthday);
    }
    //身份证18位时，次序为省（3位）市（3位）年（4位）月（2位）日（2位）校验位（4位），校验位末尾可能为X
    if (len == '18') {
        var re_eighteen = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;
        var arr_data = card.match(re_eighteen);
        var year = arr_data[2];
        var month = arr_data[3];
        var day = arr_data[4];
        var birthday = new Date(year + '/' + month + '/' + day);
        return verifyBirthday(year, month, day, birthday);
    }
    return false;
};

//校验日期
verifyBirthday = function (year, month, day, birthday) {
    var now = new Date();
    var now_year = now.getFullYear();
    //年月日是否合理
    if (birthday.getFullYear() == year && (birthday.getMonth() + 1) == month && birthday.getDate() == day) {
        //判断年份的范围（3岁到100岁之间)
        var time = now_year - year;
        if (time >= 3 && time <= 100) {
            return true;
        }
        return false;
    }
    return false;
};

//校验位的检测
checkParity = function (card) {
    //15位转18位
    card = changeFivteenToEighteen(card);
    var len = card.length;
    if (len == '18') {
        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
        var cardTemp = 0, i, valnum;
        for (i = 0; i < 17; i++) {
            cardTemp += card.substr(i, 1) * arrInt[i];
        }
        valnum = arrCh[cardTemp % 11];
        if (valnum == card.substr(17, 1)) {
            return true;
        }
        return false;
    }
    return false;
};

//15位转18位身份证号
changeFivteenToEighteen = function (card) {
    if (card.length == '15') {
        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
        var cardTemp = 0, i;
        card = card.substr(0, 6) + '19' + card.substr(6, card.length - 6);
        for (i = 0; i < 17; i++) {
            cardTemp += card.substr(i, 1) * arrInt[i];
        }
        card += arrCh[cardTemp % 11];
        return card;
    }
    return card;
};

/***********************身份证验证结束************************/

