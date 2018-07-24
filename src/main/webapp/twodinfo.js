$(function () {
    var twoDInfoId = getParameter('strTwoDInfoId');
    $.ajax({
        url: 'testReportInfoAction!findByTwoDInfoId.action',
        data: 'strTwoDInfoId=' + twoDInfoId,
        dataType: 'json',
        type: 'post',
        async: false,
        success: function (result) {
            fi()
            {
            }
            $('#testAgencyName').html(result.testAgencyName);
            $('#testCategories').html(result.testCategories);
            $('#projectName').html(result.projectName);
            $('#projectAddress').html(result.projectAddress);
            $('#projectParts').html(result.projectParts);
            $('#reportNo').html(result.reportNo);
            $('#entrustCompanyName').html(result.entrustCompanyName);
            $('#reportDate').html(result.reportDate);
            $('#testResult').html(result.testResult);
            $('#reportConclusion').html(result.reportConclusion);
            $('#inspectionMan').html(result.inspectionMan);
            $('#witnessMan').html(result.witnessMan);//+"  "+result.inspectionMan
            $('#standards').html(result.standards);
            $('#qcNumber').html(result.reportConclusion);
            $('#testName').html(result.testName);
            $('#witnessMan').html(result.witnessMan);
            if ("00" == result.isNew) {
                $('#newTitle').hide();
            }
        },
        error: commerror
    });
});