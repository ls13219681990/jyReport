<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>出错提示页</title>
    <base href="<%=basePath %>">
</head>
<body>
<TABLE width="510px" align="center" CELLSPACING=0 background="images/bodybg.jpg">
    <tr>
        <td height="39" valign=top>
            <br>
            <div align="center"><font color="#FF0000" size="+1"><b>系统处理过程中发生了一个错误，信息如下：</b></font></div>
        </td>
    </tr>
    <tr>
        <td height="100" align="center" valign=top>
            <br><br>
            <%
                Object obj = request.getAttribute("exception.message");
                String msg = "";
                if (obj != null) {
                    msg = obj.toString();
                }
                if (msg.equals("重新登录")) {
            %>
            <a href="<%=basePath%>index.jsp" target="_parent">
                用户信息过期请<s:property value="exception.message" escape="false"/>
                <s:property value="exception.cause.message" escape="false"/>
            </a>
            <%} else {%>
            <s:property value="exception.message" escape="false"/>
            <s:property value="exception.cause.message" escape="false"/>
            <input type="button" name="aa" value="确定" onclick="closeDialog('openDialog');">
            <%}%>
        </td>
    </tr>
    <tr>
        <td valign=top>
            <div align="center" style="font:10pt">请您先核对输入，如果再次出现该错误，请与系统管理员联系。</div>
            <br></td>
    </tr>
</table>
</body>
</html>