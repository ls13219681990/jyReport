$(function () {
    $.ajax({
//		url:'loginAction!logon.action',
        url: 'sysCodeAction!findcode.action',
//		data:'sysUser.userCode=333',
//		data:'sysCode.codeName=555&sysCode.codeId=D5E15BD8FF074CC98BA1B6F484E6D975',
//		data:'sysCode.codeName=333',
        dataType: 'json',
        type: 'post',
        success: function (result) {
            alert(result);
        },
        error: commerror
    });
});