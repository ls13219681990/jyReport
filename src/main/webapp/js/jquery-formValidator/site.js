var VERSION = "3.3";
var UPDATE_TIME = "2009-01-24";

function prt(str) {
    document.write(str);
}

function writeTop() {
    prt("<table class=\"HeaderTable\" width=\"760\" cellpadding=\"4\" align=\"center\">");
    prt("<tr>");
    prt("<td width=\"100%\" height=\"36\">jQuery formValidator����֤���</td> ");
    prt("<td class=\"VersionText\" valign=\"bottom\">" + VERSION + "</td>");
    prt("</tr>");
    prt("</table>");
}


function writeNav() {
    var str = "";
    str += "::����::";
    str += "<ul>";
    str += "<li><a href=\"index.html\">��ҳ</a></li>";
    str += "<li><a href=\"update.html\">���¼�¼</a></li>";
    str += "<li><a href=\"download.html\">����</a></li>";
    str += "<li><a href=\"userguide.html\">�û��ֲ�</a></li>";
    //str+="<li><a href=\"devguide.html\">����ָ��</a></li>";
    str += "<li><a href=\"demo.html\">��ʾ</a></li>";
    str += "<li><a href=\"faq.html\">FAQ</a></li>";
    str += "<li><a href=\"#\">������</a></li>";
    str += "</ul>";

    prt(str);
}

function writeBottom() {
    prt("<table width=\"760\" align=\"center\" cellpadding=\"2\" cellspacing=\"2\" class=\"BottomTable\">");
    prt("  <tr> ");
    prt("    <td align=\"center\"><font size=\"3\">Powered by: è�� Copyright &reg;2008 è��</font> ");
    prt("    </td>");
    prt("  </tr>");
    prt("  <tr>");
    prt("    <td align=\"center\"><font size=\"3\">������ҵĲ��� <a href=\"http://www.cnblogs.com/wzmaodong\">http://www.cnblogs.com/wzmaodong</a> ");
    prt("      �����²��</font></td>");
    prt("  </tr>");
    prt("</table>");
}