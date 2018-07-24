<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>

    <%@ include file="/commPage.jsp" %>
    <script type="text/javascript" src="layout.js"></script>
</head>

<body>
<!--上边的内容开始 -->
<iframe frameborder="0" height="220px" id="top" name="top" width="100%" scrolling="no" marginheight="0"
        marginwidth="0"></iframe>

<!--  中间的内容开始  id作为判断最外层页面的标志不能随意更改 -->
<iframe frameborder="0" height="270px" id="center" name="center" width="100%" scrolling="no" marginheight="0"
        marginwidth="0"></iframe>

<!--下边的内容开始 -->
<iframe frameborder="0" height="220px" id="bottom" name="bottom" width="100%" scrolling="no" marginheight="0"
        marginwidth="0"></iframe>


<form id="alipay" method='POST' target="_blank">

    <input type='hidden' name='service' value=''>
    <input type='hidden' name='partner' value=''>
    <input type='hidden' name='_input_charset' value=''>
    <input type='hidden' name='sign_type' value=''>
    <input type='hidden' name='notify_url' value=''>
    <input type='hidden' name='return_url' value=''>
    <input type='hidden' name='out_trade_no' value=''>
    <input type='hidden' name='subject' value=''>
    <input type='hidden' name='payment_type' value=''>
    <input type='hidden' name='seller_email' value=''>
    <input type='hidden' name='total_fee' value=''>
    <input type='hidden' name='paymethod' value=''>
    <input type='hidden' name='sign' value=''>
    <input type='hidden' name='it_b_pay' value=''>
    <input type='hidden' name='royalty_type' value=''>
    <input type='hidden' name='royalty_parameters' value=''>
    123333333333333333333333333333333333
</form>

</body>
</html>
