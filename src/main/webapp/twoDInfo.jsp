<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>检测报告展示</title>

    <%@ include file="/commPage.jsp" %>
    <script type="text/javascript" src="twodinfo.js"></script>
    <style type="text/css">
        .tdStyle {
            text-align: left;
            font-size: 42px;
            height: 110px;
            border-bottom: 1px solid #999999;
            line-height: 60px;
        }

        .tdStyle span {
            font-size: 42px;
        }
    </style>
</head>

<body>
<!--上边的内容开始 -->
<iframe frameborder="0" height="1px" id="top" name="top" width="100%" scrolling="no" marginheight="0"
        marginwidth="0"></iframe>

<!--  中间的内容开始  id作为判断最外层页面的标志不能随意更改 -->
<iframe frameborder="0" height="1px" id="center" name="center" width="100%" scrolling="no" marginheight="0"
        marginwidth="0"></iframe>

<!--下边的内容开始 -->
<iframe frameborder="0" height="1px" id="bottom" name="bottom" width="100%" scrolling="no" marginheight="0"
        marginwidth="0"></iframe>


<%--		<div style="border: 1px solid #52A0EA;" cellspacing="0"  border="0" width="100%">--%>
<%--		四川省建设工程质量安全监督总站--%>
<%--            <tr>--%>
<%--            	<td style="text-align: center;"><b><font color="red">四川省建设工程质量安全监督总站</font></b></td>--%>
<%--            </tr>--%>
<%--		</div>--%>

<table width="100%" style="color:#A8A8A8; ">
    <tr style="height:88px;">
        <td style="text-align: left;font-size: 42px; border-bottom: 1px solid #999999;ackground: url(js/table_bg.png) repeat;background-size:cover;position: relative;">
            检测报告详情
            <div style="border-radius:100px;background-color: #16C500;font-size: 40px;line-height:60px;width: 240px;color: #FFFFFF;text-align: center;right: 10px;top:12px;position: absolute;">
                正常报告
            </div>
        </td>
    </tr>
    <%--<tr id="newTitle" style="height:110px;">
        <td style="text-align: center;font-size: 48px;color: red;font-weight: bold; border-bottom: 1px solid #999999;">四川省建设工程质量安全监督总站</td>
    </tr>--%>
    <tr>
        --%>
        <td class="tdStyle">机构名称：
            <span id="testAgencyName"></span></td>
    </tr>
    <tr>
        <td class="tdStyle">工程名称：
            <span id="projectName"/></td>
    </tr>
    <tr>
        <td class="tdStyle">报告编号：
            <span id="reportNo"/></td>
    </tr>
    <tr>
        <td class="tdStyle">报告日期：
            <span id="reportDate"/></td>
    </tr>
    <tr>
        <td class="tdStyle">检测资质类别：
            <span id="testCategories"/></td>
    </tr>
    <tr>
        <td class="tdStyle">检测结果：
            <span id="testResult"/></td>
    </tr>
    <tr>
        <td class="tdStyle">委托单位：
            <span id="entrustCompanyName"/></td>
    </tr>
    <tr>
        <td class="tdStyle">见证送检人：
            <span id="witnessMan"/></td>
    </tr>
    <tr>
        <td class="tdStyle">检测数据：
            <span id="standards"/></td>
    </tr>
    <tr>
        <td class="tdStyle">质量监督号：
            <span id="qcNumber"/></td>
    </tr>
    <tr>
        <td class="tdStyle">工程部位：
            <span id="projectParts"/></td>
    </tr>
    <tr>
        <td class="tdStyle">检测项目：
            <span id="testName"/></td>
    </tr>
    <tr>
        <td class="tdStyle">描述：
            <span id="description"/></td>
    </tr>
</table>


<%--        <table cellspacing="0" cellpadding="3" border="0.1" >--%>
<%--            <tbody>--%>
<%--            <tr>--%>
<%--                <td style="text-align: right;">机构名称：</td>--%>
<%--                <td><span id="testAgencyName"></span></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td style="text-align: right;">检测类别：</td>--%>
<%--                <td><span id="testCategories"></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td style="text-align: right;">工程名称：</td>--%>
<%--                <td><span id="projectName"></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td style="text-align: right;">工程地址：</td>--%>
<%--                <td><span id="projectAddress"></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td style="text-align: right;">工程部位：</td>--%>
<%--                <td><span id="projectParts"></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td style="text-align: right;">报告编号：</td>--%>
<%--                <td><span id="reportNo"></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td style="text-align: right;">委托单位：</td>--%>
<%--                <td><span id="entrustCompanyName"></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td style="text-align: right;">报告日期：</td>--%>
<%--                <td><span id="reportDate"></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td style="text-align: right;">报告结论：</td>--%>
<%--                <td><span id="testResult"></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td style="text-align: right;">检测结果：</td>--%>
<%--                <td><span id="reportConclusion"></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td style="text-align: right;">送检人：</td>--%>
<%--                <td><span id="inspectionMan"></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td style="text-align: right;">见证人：</td>--%>
<%--                <td><span id="witnessMan"></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td style="text-align: right;">依据标准：</td>--%>
<%--                <td><span id="standards"></td>--%>
<%--            </tr>--%>
<%--        </tbody>--%>
<%--        </table>--%>


</body>
</html>
