package com.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CodeGenerator {
    private static String fileName = "HotelActivityPriceLimit.java";//需要生成文件的javabean ****************操作这里
    private static String projectPath = "e:\\Workspaces\\booking\\";//工程路径如：E:\\lzpwork\\gxmms\\
    private String daoPath = projectPath + "src\\dao\\comm\\";//生成dao的路径
    private String servicePath = projectPath + "src\\service\\comm\\";//生成service的路径
    private String actionPath = projectPath + "src\\action\\hotel\\";
    private String jspPath = projectPath + "\\WebRoot\\jsp\\";
    private String jsPath = jspPath;


    private String fileJavaName = fileName.substring(0, fileName.indexOf('.'));
    private String packageName = fileJavaName.replaceFirst(fileName.substring(0, 1), fileName.substring(0, 1).toLowerCase());// 把第一个字母换成小写

    public static void main(String[] args) throws Exception {
        CodeGenerator cg = new CodeGenerator();
        try {
//			cg.generateDao(cg);
//			cg.generateService(cg);
            cg.generateAction(cg);
//			cg.generateQuery(cg);
//			cg.generateAllInfo(cg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateAllInfo(CodeGenerator cg) throws Exception {
        //allInfoJsp
        String allInfoJspName = "allInfo.jsp";
        StringBuffer allInfoJspContent = new StringBuffer();
        String allInfoJspFileFullName = jspPath + packageName + "\\" + allInfoJspName;
        allInfoJspContent.append("<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>" + "\n");
        allInfoJspContent.append("<%@ taglib prefix=\"s\" uri=\"/struts-tags\"%>" + "\n");
        allInfoJspContent.append("<%" + "\n");
        allInfoJspContent.append("String path = request.getContextPath();" + "\n");
        allInfoJspContent.append("String basePath = request.getScheme()+\"://\"+request.getServerName()+\":\"+request.getServerPort()+path+\"/\";" + "\n");
        allInfoJspContent.append("%>" + "\n");
        allInfoJspContent.append("<html>" + "\n");
        allInfoJspContent.append("<head>" + "\n");
        allInfoJspContent.append("<base href=\"<%=basePath%>\">" + "\n");
        allInfoJspContent.append("<title>详细信息</title>" + "\n");
        allInfoJspContent.append("<meta http-equiv=\"pragma\" content=\"no-cache\">" + "\n");
        allInfoJspContent.append("<meta http-equiv=\"cache-control\" content=\"no-cache\">" + "\n");
        allInfoJspContent.append("<meta http-equiv=\"expires\" content=\"0\">" + "\n");
        allInfoJspContent.append("<link href=\"css/css.css\" rel=\"stylesheet\" type=\"text/css\">" + "\n");
        allInfoJspContent.append("<link type=\"text/css\" rel=\"stylesheet\"	href=\"js/jquery-formValidator/style/validatorAuto.css\"></link>" + "\n");
        allInfoJspContent.append("<script type=\"text/JavaScript\" src=\"js/CommMethod.js\"></script>" + "\n");
        allInfoJspContent.append("<script type=\"text/javascript\" src=\"js/jquery-1.3.2.min.js\"></script>" + "\n");
        allInfoJspContent.append("<script type=\"text/javascript\" src=\"js/jquery-formValidator/formValidator.js\" charset=\"UTF-8\"></script>" + "\n");
        allInfoJspContent.append("<script type=\"text/javascript\" src=\"js/jquery-formValidator/datepicker/WdatePicker.js\" defer=\"defer\"></script>" + "\n");
        allInfoJspContent.append("<script type=\"text/javascript\" src=\"js/jquery-formValidator/formValidatorRegex.js\"  charset=\"UTF-8\"></script>" + "\n");
        allInfoJspContent.append("<script type=\"text/javascript\" LANGUAGE=\"JavaScript\" src=\"jsp/" + packageName + "/allInfo.js\"></script>" + "\n");
        allInfoJspContent.append("</head>" + "\n");
        allInfoJspContent.append("<body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" style=\"overflow-y:auto\">" + "\n");
        allInfoJspContent.append("<s:form action=\"" + packageName + "Action\"  theme=\"simple\" id=\"allInfo\">" + "\n");
        allInfoJspContent.append("<s:hidden id=\"" + packageName + "Id\" name=\"" + packageName + "." + packageName + "Id\"/>" + "\n");
        allInfoJspContent.append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" + "\n");
        allInfoJspContent.append("  <tr> " + "\n");
        allInfoJspContent.append("    <td class=\"toptdfirst\"></td>" + "\n");
        allInfoJspContent.append("    <td class=\"toptdsecond\">" + "\n");
        allInfoJspContent.append("	  <input id=\"btnSave\" type=\"submit\" class=\"bt_save\" value=\"保存\">" + "\n");
        allInfoJspContent.append("      <input id=\"btnClose\" type=\"button\" class=\"bt_close\" value=\"关闭\">" + "\n");
        allInfoJspContent.append("    </td>" + "\n");
        allInfoJspContent.append("  </tr>" + "\n");
        allInfoJspContent.append("</table>" + "\n");
        allInfoJspContent.append("<s:actionmessage cssClass=\"actionmessage\" />" + "\n");
        allInfoJspContent.append("&nbsp;基本信息" + "\n");
        allInfoJspContent.append("<br>" + "\n");
        allInfoJspContent.append("	<table width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"  id=\"basicInfo\" class=\"table_grid\" style=\"display:\">" + "\n");
        allInfoJspContent.append("          <tr>" + "\n");
        allInfoJspContent.append("            <td class=\"td_text\" style=\"width: 20%\"> </td>" + "\n");
        allInfoJspContent.append("            <td style=\"width: 30%; \">" + "\n");
        allInfoJspContent.append("            </td>" + "\n");
        allInfoJspContent.append("            <td class=\"td_text\" style=\"width: 20%\"> </td>" + "\n");
        allInfoJspContent.append("            <td style=\"width: 30%; \">" + "\n");
        allInfoJspContent.append("            </td>" + "\n");
        allInfoJspContent.append("          </tr>" + "\n");
        allInfoJspContent.append("  </table>" + "\n");
        allInfoJspContent.append("</s:form>              " + "\n");
        allInfoJspContent.append("</body>" + "\n");
        allInfoJspContent.append("</html>" + "\n");

        cg.deleteFile(allInfoJspFileFullName);
        cg.CreateFile(allInfoJspFileFullName);
        cg.writeFile(allInfoJspFileFullName, allInfoJspContent.toString());

        //allInfoJs
        String allInfoJsName = "allInfo.js";
        StringBuffer allInfoJsContent = new StringBuffer();
        String allInfoJsFileFullName = jsPath + packageName + "\\" + allInfoJsName;

        allInfoJsContent.append("$(document).ready(function(){		" + "\n");
        allInfoJsContent.append("	$.formValidator.initConfig({" + "\n");
        allInfoJsContent.append("		formid :\"allInfo\",autotip:true," + "\n");
        allInfoJsContent.append("		onerror:function(msg){alert(msg)}," + "\n");
        allInfoJsContent.append("		onsuccess:function(){" + "\n");
        allInfoJsContent.append("			if(confirm(\"你确定要保存吗？\")){" + "\n");
        allInfoJsContent.append("				return true;" + "\n");
        allInfoJsContent.append("			}else{" + "\n");
        allInfoJsContent.append("				return false;" + "\n");
        allInfoJsContent.append("			}" + "\n");
        allInfoJsContent.append("		}" + "\n");
        allInfoJsContent.append("	});" + "\n");
        allInfoJsContent.append("	$(\"#btnSave\").bind(\"click\",function(){" + "\n");
        allInfoJsContent.append("		if($(\"#" + packageName + "Id\").val()==''){" + "\n");
        allInfoJsContent.append("			$(\"#allInfo\").attr(\"action\",\"actions/" + packageName + "Action!save\");" + "\n");
        allInfoJsContent.append("		}else{" + "\n");
        allInfoJsContent.append("			$(\"#allInfo\").attr(\"action\",\"actions/" + packageName + "Action!update\");" + "\n");
        allInfoJsContent.append("		}" + "\n");
        allInfoJsContent.append("	});" + "\n");
        allInfoJsContent.append("	$(\"#btnClose\").bind(\"click\",function(e){" + "\n");
        allInfoJsContent.append("		if(confirm(\"你确定要关闭吗？\")){" + "\n");
        allInfoJsContent.append("			window.location = 'actions/" + packageName + "Action!close';" + "\n");
        allInfoJsContent.append("		}" + "\n");
        allInfoJsContent.append("	});" + "\n");
        allInfoJsContent.append("});" + "\n");


        cg.deleteFile(allInfoJsFileFullName);
        cg.CreateFile(allInfoJsFileFullName);
        cg.writeFile(allInfoJsFileFullName, allInfoJsContent.toString());
    }

    private void generateQuery(CodeGenerator cg) throws Exception {
        //queryJsp
        String queryJspName = "query.jsp";
        StringBuffer queryJspContent = new StringBuffer();
        String queryJspFileFullName = jspPath + packageName + "\\" + queryJspName;
        queryJspContent.append("<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>" + "\n");
        queryJspContent.append("<%@ taglib prefix=\"s\" uri=\"/struts-tags\"%>" + "\n");
        queryJspContent.append("<%" + "\n");
        queryJspContent.append("String path = request.getContextPath();" + "\n");
        queryJspContent.append("String basePath = request.getScheme()+\"://\"+request.getServerName()+\":\"+request.getServerPort()+path+\"/\";" + "\n");
        queryJspContent.append("%>" + "\n");
        queryJspContent.append("<html>" + "\n");
        queryJspContent.append("<head>" + "\n");
        queryJspContent.append("<base href=\"<%=basePath%>\">" + "\n");
        queryJspContent.append("<title>查询</title>" + "\n");
        queryJspContent.append("<link href=\"css/css.css\" rel=\"stylesheet\" type=\"text/css\">" + "\n");
        queryJspContent.append("<script type=\"text/JavaScript\" src=\"js/CommMethod.js\"></script>" + "\n");
        queryJspContent.append("<script type=\"text/javascript\" src=\"js/jquery-1.3.2.min.js\"></script>" + "\n");
        queryJspContent.append("<script type=\"text/javascript\" src=\"js/jquery-formValidator/formValidator.js\" charset=\"UTF-8\"></script>" + "\n");
        queryJspContent.append("<script type=\"text/javascript\" src=\"js/jquery-formValidator/datepicker/WdatePicker.js\" defer=\"defer\"></script>" + "\n");
        queryJspContent.append("<script type=\"text/javascript\" src=\"js/jquery-formValidator/formValidatorRegex.js\"  charset=\"UTF-8\"></script>" + "\n");
        queryJspContent.append("<script src=\"jsp/" + packageName + "/query.js\" language=\"JavaScript\" type=\"text/JavaScript\"></script>" + "\n");
        queryJspContent.append("</head>" + "\n");
        queryJspContent.append("<body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" style=\"overflow-y:auto\">" + "\n");
        queryJspContent.append("<s:form action=\"" + packageName + "Action\" theme=\"simple\" id=\"query\">" + "\n");
        queryJspContent.append("<table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">" + "\n");
        queryJspContent.append("  <tr>" + "\n");
        queryJspContent.append("    <td height=\"25\">	" + "\n");
        queryJspContent.append("	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" + "\n");
        queryJspContent.append("		<tr> " + "\n");
        queryJspContent.append("          <td class=\"toptdfirst\"></td>" + "\n");
        queryJspContent.append("          <td class=\"toptdsecond\">" + "\n");
        queryJspContent.append("          	&nbsp;<input id=\"btnQuery\" type=\"button\" class=\"bt_query\" value=\"查询\">" + "\n");
        queryJspContent.append("            <input id=\"btnHigh\" type=\"button\" class=\"bt_gaojiquery\" onclick=\"hide('queryCondition');\" value=\"查询条件\">" + "\n");
        queryJspContent.append("            <input id=\"btnClear\" type=\"button\" class=\"bt_clear\"  value=\"清空\">" + "\n");
        queryJspContent.append("          	<input id=\"btnNew\" type=\"button\" class=\"bt_addnew\" value=\"新建\">" + "\n");
        queryJspContent.append("            <input id=\"btnDel\" type=\"button\" class=\"bt_delet\" value=\"删除\">" + "\n");
        queryJspContent.append("          </td>" + "\n");
        queryJspContent.append("        </tr>" + "\n");
        queryJspContent.append("      </table>" + "\n");
        queryJspContent.append("	</td>" + "\n");
        queryJspContent.append("  </tr>" + "\n");
        queryJspContent.append("  <tr>" + "\n");
        queryJspContent.append("    <td valign=\"top\">" + "\n");
        queryJspContent.append("      <table width=\"100%\"  border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">" + "\n");
        queryJspContent.append("      	<tr>" + "\n");
        queryJspContent.append("    <td valign=\"top\">" + "\n");
        queryJspContent.append("      <table id=\"queryCondition\"  style=\"display:none\" width=\"99%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_grid\">" + "\n");
        queryJspContent.append("        <tr> " + "\n");
        queryJspContent.append("          <td colspan=\"4\" height=\"25\">查询条件</td>          " + "\n");
        queryJspContent.append("        </tr>" + "\n");
        queryJspContent.append("        <tr> " + "\n");
        queryJspContent.append("        </tr>" + "\n");
        queryJspContent.append("        </table>" + "\n");
        queryJspContent.append("	</td>" + "\n");
        queryJspContent.append("  </tr>" + "\n");
        queryJspContent.append("          <tr > " + "\n");
        queryJspContent.append("            <td >" + "\n");
        queryJspContent.append("            <table width=\"99%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_grid\">" + "\n");
        queryJspContent.append("              <tr class=\"table_grid_top\">" + "\n");
        queryJspContent.append("                <td width=\"3%\"><input type=\"checkbox\" id=\"checkAllPage\"/></td>" + "\n");
        queryJspContent.append("                <td width=\"3%\">序号</td>" + "\n");
        queryJspContent.append("                <td width=\"10%\"></td>" + "\n");
        queryJspContent.append("                <td width=\"10%\"></td>" + "\n");
        queryJspContent.append("                <td width=\"10%\"></td>" + "\n");
        queryJspContent.append("                <td width=\"10%\"></td>" + "\n");
        queryJspContent.append("                <td width=\"8%\"></td>" + "\n");
        queryJspContent.append("                <td width=\"8%\">创建时间</td>" + "\n");
        queryJspContent.append("              </tr>" + "\n");
        queryJspContent.append("             <s:iterator  value=\"ps.results\" status=\"seq\"> " + "\n");
        queryJspContent.append("              <tr>" + "\n");
        queryJspContent.append("                <td align=\"center\">" + "\n");
        queryJspContent.append("					<input type=\"checkbox\" name=\"IDS\" value='<s:property value=\"" + packageName + "Id\" />'/>					" + "\n");
        queryJspContent.append("				</td>" + "\n");
        queryJspContent.append("				<td align=\"center\">" + "\n");
        queryJspContent.append("					<s:property value=\"#seq.index+1\"/>" + "\n");
        queryJspContent.append("				</td>" + "\n");
        queryJspContent.append("                <td class=\"table_td_center\">" + "\n");
        queryJspContent.append("                	<a href='actions/" + packageName + "Action!edit?" + packageName + "." + packageName + "Id=<s:property value=\"" + packageName + "Id\"/>'>" + "\n");
        queryJspContent.append("                		<s:property value=\"" + packageName + "Code\"/>" + "\n");
        queryJspContent.append("                	</a>" + "\n");
        queryJspContent.append("                </td>" + "\n");
        queryJspContent.append("                <td class=\"table_td_center\"></td>" + "\n");
        queryJspContent.append("                <td class=\"table_td_center\">" + "\n");
        queryJspContent.append("				</td>" + "\n");
        queryJspContent.append("                <td class=\"table_td_center\">" + "\n");
        queryJspContent.append("                </td>" + "\n");
        queryJspContent.append("                <td class=\"table_td_center\">" + "\n");
        queryJspContent.append("                </td>" + "\n");
        queryJspContent.append("				<td class=\"table_td_center\">" + "\n");
        queryJspContent.append("					<s:date name=\"createDate\" format=\"yyyy-MM-dd\" />" + "\n");
        queryJspContent.append("				</td>" + "\n");
        queryJspContent.append("               </tr>" + "\n");
        queryJspContent.append("              </s:iterator>" + "\n");
        queryJspContent.append("        </table> " + "\n");
        queryJspContent.append("	</td>" + "\n");
        queryJspContent.append("  </tr>" + "\n");
        queryJspContent.append("</table>" + "\n");
        queryJspContent.append("<script type=\"text/javascript\">" + "\n");
        queryJspContent.append("makePage(<s:property value=\"ps.currentPage\" />,<s:property value=\"ps.totalPages\" />,<s:property value=\"ps.pageSize\" />,<s:property value=\"ps.totalRows\" />)" + "\n");
        queryJspContent.append("</script>" + "\n");
        queryJspContent.append("</s:form>" + "\n");
        queryJspContent.append("</body>" + "\n");
        queryJspContent.append("</html>" + "\n");
        cg.deleteFile(queryJspFileFullName);
        cg.CreateFile(queryJspFileFullName);
        cg.writeFile(queryJspFileFullName, queryJspContent.toString());
        //queryJs
        String queryJsName = "query.js";
        StringBuffer queryJsContent = new StringBuffer();
        queryJsContent.append("$(document).ready(function() {" + "\n");
        queryJsContent.append("			$.formValidator.initConfig({" + "\n");
        queryJsContent.append("						formid : \"query\"," + "\n");
        queryJsContent.append("						alertmessage : true," + "\n");
        queryJsContent.append("						onerror : function(msg) {" + "\n");
        queryJsContent.append("							alert(msg)" + "\n");
        queryJsContent.append("						}" + "\n");
        queryJsContent.append("					});" + "\n");
        queryJsContent.append("			// 查询" + "\n");
        queryJsContent.append("			$(\"#btnQuery\").bind(\"click\", query);" + "\n");
        queryJsContent.append("			$(\"#btnSP\").bind(\"change\", query);" + "\n");
        queryJsContent.append("			function query() {" + "\n");
        queryJsContent.append("				$(\"#query\").attr(\"action\", \"actions/" + packageName + "Action!query\");" + "\n");
        queryJsContent.append("				$(\"#query\").submit();" + "\n");
        queryJsContent.append("			}" + "\n");
        queryJsContent.append("			// 新建" + "\n");
        queryJsContent.append("			$(\"#btnNew\").bind(\"click\", function(e) {" + "\n");
        queryJsContent.append("						$(\"#query\").attr(\"action\"," + "\n");
        queryJsContent.append("								\"actions/" + packageName + "Action!create\");" + "\n");
        queryJsContent.append("						$(\"#query\").submit();" + "\n");
        queryJsContent.append("					});" + "\n");
        queryJsContent.append("			// 清空查询条件" + "\n");
        queryJsContent.append("			$(\"#btnClear\").bind(\"click\", function(e) {" + "\n");
        queryJsContent.append("						$(\"#queryCondition :text\").val(\"\");" + "\n");
        queryJsContent.append("						$(\"#queryCondition select\").val(\"\");" + "\n");
        queryJsContent.append("						$(\"#queryCondition input[type='hidden']\").val(\"\");" + "\n");
        queryJsContent.append("					});" + "\n");
        queryJsContent.append("			// 删除" + "\n");
        queryJsContent.append("			$(\"#btnDel\").bind(\"click\", function(e) {" + "\n");
        queryJsContent.append("						if (!checkIDS()) {" + "\n");
        queryJsContent.append("							alert(\"请选择要删除的项!\")" + "\n");
        queryJsContent.append("							return;" + "\n");
        queryJsContent.append("						}" + "\n");
        queryJsContent.append("						if (confirm(\"你确定要删除吗？\")) {" + "\n");
        queryJsContent.append("							$(\"#query\").attr(\"action\"," + "\n");
        queryJsContent.append("									\"actions/" + packageName + "Action!deletes\");" + "\n");
        queryJsContent.append("							$(\"#query\").submit();" + "\n");
        queryJsContent.append("						}" + "\n");
        queryJsContent.append("					});" + "\n");
        queryJsContent.append("			// 全选" + "\n");
        queryJsContent.append("			$(\"#checkAllPage\").click(function() {" + "\n");
        queryJsContent.append("						if ($(this).attr(\"checked\") == true) { // 全选" + "\n");
        queryJsContent.append("							$(\"input[name='IDS']\").each(function() {" + "\n");
        queryJsContent.append("										$(this).attr(\"checked\", true);" + "\n");
        queryJsContent.append("									});" + "\n");
        queryJsContent.append("						} else { // 取消全选" + "\n");
        queryJsContent.append("							$(\"input[name='IDS']\").each(function() {" + "\n");
        queryJsContent.append("										$(this).attr(\"checked\", false);" + "\n");
        queryJsContent.append("									});" + "\n");
        queryJsContent.append("						}" + "\n");
        queryJsContent.append("					});" + "\n");
        queryJsContent.append("			$(\"#viewPage\").formValidator({" + "\n");
        queryJsContent.append("						automodify : true" + "\n");
        queryJsContent.append("					}).inputValidator({" + "\n");
        queryJsContent.append("						min : 1," + "\n");
        queryJsContent.append("						onerror : \"页码不能为空\"" + "\n");
        queryJsContent.append("					}).regexValidator({" + "\n");
        queryJsContent.append("						regexp : \"intege1\"," + "\n");
        queryJsContent.append("						datatype : \"enum\"," + "\n");
        queryJsContent.append("						onerror : \"请输入正确的页码\"" + "\n");
        queryJsContent.append("					});" + "\n");
        queryJsContent.append("		});" + "\n");
        queryJsContent.append("// 翻页" + "\n");
        queryJsContent.append("function jumpPage(viewPage) {" + "\n");
        queryJsContent.append("	$(\"#viewPage\").attr(\"value\", viewPage);" + "\n");
        queryJsContent.append("	$(\"#query\").attr(\"action\", \"actions/" + packageName + "Action!query\");" + "\n");
        queryJsContent.append("	$(\"#query\").submit();" + "\n");
        queryJsContent.append("}" + "\n");
        String queryJsFileFullName = jsPath + packageName + "\\" + queryJsName;
        cg.deleteFile(queryJsFileFullName);
        cg.CreateFile(queryJsFileFullName);
        cg.writeFile(queryJsFileFullName, queryJsContent.toString());
    }

    private void generateAction(CodeGenerator cg) throws Exception {
        //action
        String actionName = fileJavaName + "Action.java";
        StringBuffer actionContent = new StringBuffer();
        actionContent.append("package action." + packageName + ";" + "\n");

        actionContent.append("import org.apache.struts2.convention.annotation.Action;" + "\n");
        actionContent.append("import org.apache.struts2.convention.annotation.ParentPackage;" + "\n");
        actionContent.append("import org.apache.struts2.convention.annotation.Result;" + "\n");
        actionContent.append("import org.springframework.beans.factory.annotation.Autowired;" + "\n");

        actionContent.append("import service." + packageName + "." + fileJavaName + "Service;" + "\n");

        actionContent.append("import common.QueryAction;" + "\n");

        actionContent.append("import com.model." + fileJavaName + ";" + "\n");
        actionContent.append("@ParentPackage(\"action\")" + "\n");
        actionContent.append("public class " + fileJavaName + "Action extends QueryAction<" + fileJavaName + "> {" + "\n");
        actionContent.append("private static final long serialVersionUID = 1L;" + "\n");
        actionContent.append("@Autowired" + "\n");
        actionContent.append("private " + fileJavaName + "Service " + packageName + "Service;" + "\n");
        actionContent.append("private " + fileJavaName + " " + packageName + " = new " + fileJavaName + "();" + "\n");

        actionContent.append("@Action(value = \"/" + packageName + "Action\", results = {" + "\n");
        actionContent.append("	@Result(name = QUERY, location = \"/jsp/" + packageName + "/query.jsp\")," + "\n");
        actionContent.append("	@Result(name = ALLINFO, location = \"/jsp/" + packageName + "/allInfo.jsp\") })" + "\n");
        actionContent.append("/**" + "\n");
        actionContent.append("* 翻页查询" + "\n");
        actionContent.append("*/" + "\n");
        actionContent.append("public String query() {" + "\n");
        actionContent.append("	setPs(" + packageName + "Service.findPageByCriteria(getPs(), " + packageName + "));" + "\n");
        actionContent.append("	return QUERY;" + "\n");
        actionContent.append("}" + "\n");

        actionContent.append("/**" + "\n");
        actionContent.append("* 创建" + "\n");
        actionContent.append("* " + "\n");
        actionContent.append("* @return" + "\n");
        actionContent.append("*/" + "\n");
        actionContent.append("public String create() {" + "\n");
        actionContent.append("	" + packageName + " = new " + fileJavaName + "();" + "\n");
        actionContent.append("	return ALLINFO;" + "\n");
        actionContent.append("}" + "\n");

        actionContent.append("/**" + "\n");
        actionContent.append("* 编辑" + "\n");
        actionContent.append("* " + "\n");
        actionContent.append("* @return" + "\n");
        actionContent.append("*/" + "\n");
        actionContent.append("public String edit() {" + "\n");
        actionContent.append("	" + packageName + " = " + packageName + "Service.findById(" + packageName + ".get" + fileJavaName + "Id());" + "\n");
        actionContent.append("	return ALLINFO;" + "\n");
        actionContent.append("}" + "\n");

        actionContent.append("/**" + "\n");
        actionContent.append("* 保存" + "\n");
        actionContent.append("* " + "\n");
        actionContent.append("* @return" + "\n");
        actionContent.append("*/" + "\n");
        actionContent.append("public String save() {" + "\n");
        actionContent.append("	" + packageName + "Service.save(" + packageName + ");" + "\n");
        actionContent.append("	addActionMessage(\"保存成功\");" + "\n");
        actionContent.append("	return ALLINFO;" + "\n");
        actionContent.append("}" + "\n");

        actionContent.append("/**" + "\n");
        actionContent.append(" * 修改" + "\n");
        actionContent.append(" * " + "\n");
        actionContent.append(" * @return" + "\n");
        actionContent.append(" */" + "\n");
        actionContent.append("public String update() {" + "\n");
        actionContent.append("	" + packageName + "Service.update(" + packageName + ");" + "\n");
        actionContent.append("	addActionMessage(\"修改成功\");" + "\n");
        actionContent.append("return ALLINFO;" + "\n");
        actionContent.append("	}" + "\n");

        actionContent.append("/**" + "\n");
        actionContent.append(" * 删除" + "\n");
        actionContent.append(" * " + "\n");
        actionContent.append(" * @return" + "\n");
        actionContent.append(" */" + "\n");
        actionContent.append("public String deletes() {" + "\n");
        actionContent.append("	" + packageName + "Service.deletes(getIDS());" + "\n");
        actionContent.append("	return query();" + "\n");
        actionContent.append("}" + "\n");

        actionContent.append("/**" + "\n");
        actionContent.append(" * 关闭" + "\n");
        actionContent.append(" * " + "\n");
        actionContent.append(" * @return" + "\n");
        actionContent.append(" */" + "\n");
        actionContent.append("public String close() {" + "\n");
        actionContent.append("	" + packageName + " = new " + fileJavaName + "();" + "\n");
        actionContent.append("	return query();" + "\n");
        actionContent.append("}" + "\n");

        actionContent.append("public " + fileJavaName + " get" + fileJavaName + "() {" + "\n");
        actionContent.append("	return " + packageName + ";" + "\n");
        actionContent.append("}" + "\n");

        actionContent.append("public void set" + fileJavaName + "(" + fileJavaName + " " + packageName + ") {" + "\n");
        actionContent.append("	this." + packageName + " = " + packageName + ";" + "\n");
        actionContent.append("}" + "\n");

        actionContent.append("}" + "\n");


        String actionFileFullName = actionPath + packageName + "\\" + actionName;
        cg.deleteFile(actionFileFullName);
        cg.CreateFile(actionFileFullName);
        cg.writeFile(actionFileFullName, actionContent.toString());
    }

    private void generateService(CodeGenerator cg) throws Exception {
        //service
        String serviceName = fileJavaName + "Service.java";
        StringBuffer serviceContent = new StringBuffer();
        serviceContent.append("package service." + packageName + ";\n");
        serviceContent.append("import common.service.BaseService;\n");
        serviceContent.append("import com.model." + fileJavaName + ";\n");
        serviceContent.append("public interface " + fileJavaName + "Service extends BaseService<" + fileJavaName + "> {" + "\n");
        serviceContent.append("\n");
        serviceContent.append("}");
        String serviceFileFullName = servicePath + packageName + "\\" + serviceName;
        cg.deleteFile(serviceFileFullName);
        cg.CreateFile(serviceFileFullName);
        cg.writeFile(serviceFileFullName, serviceContent.toString());

        //serviceImpl
        String serviceImplName = fileJavaName + "ServiceImpl.java";
        StringBuffer serviceImplContent = new StringBuffer();
        serviceImplContent.append("package service." + packageName + ";\n");
        serviceImplContent.append("import javax.annotation.Resource;\n");
        serviceImplContent.append("import org.hibernate.criterion.DetachedCriteria;" + "\n");
        serviceImplContent.append("import org.hibernate.criterion.Order;" + "\n");
        serviceImplContent.append("import org.springframework.beans.factory.annotation.Autowired;" + "\n");
        serviceImplContent.append("import org.springframework.stereotype.Service;\n");
        serviceImplContent.append("import common.action.PaginationSupport;" + "\n");
        serviceImplContent.append("import common.dao.BaseDao;\n");
        serviceImplContent.append("import common.service.BaseServiceImpl;\n");
        serviceImplContent.append("import com.model." + fileJavaName + ";\n");
        serviceImplContent.append("import dao." + packageName + "." + fileJavaName + "Dao;" + "\n");
        serviceImplContent.append("@Service(\"" + packageName + "Service\")\n");
        serviceImplContent.append("public class " + fileJavaName + "ServiceImpl extends BaseServiceImpl<" + fileJavaName + "> implements " + fileJavaName + "Service {");
        serviceImplContent.append("\n");
        serviceImplContent.append("@Autowired" + "\n");
        serviceImplContent.append("private " + fileJavaName + "Dao " + packageName + "Dao;" + "\n");
        serviceImplContent.append("	@Override\n");
        serviceImplContent.append("	@Resource(name=\"" + packageName + "Dao\")\n");
        serviceImplContent.append("	protected void initBaseDAO(BaseDao<" + fileJavaName + "> baseDao) {\n");
        serviceImplContent.append("		setBaseDao(baseDao);\n");
        serviceImplContent.append("	}\n");
        serviceImplContent.append("@Override" + "\n");
        serviceImplContent.append("public PaginationSupport<" + fileJavaName + "> findPageByCriteria(PaginationSupport<" + fileJavaName + "> ps, " + fileJavaName + " t) {" + "\n");
        serviceImplContent.append("	DetachedCriteria dc = DetachedCriteria.forClass(" + fileJavaName + ".class);" + "\n");
        serviceImplContent.append("	return " + packageName + "Dao.findPageByCriteria(ps, Order.desc(\"" + packageName + "Id\"), dc);" + "\n");
        serviceImplContent.append("}" + "\n");
        serviceImplContent.append("@Override" + "\n");
        serviceImplContent.append("public void save(" + fileJavaName + " t) {" + "\n");
        serviceImplContent.append("	t.set" + fileJavaName + "Id(getNextKey(\"" + fileJavaName + "\".toUpperCase(), 1));" + "\n");
        serviceImplContent.append("	" + packageName + "Dao.save(t);" + "\n");
        serviceImplContent.append("}" + "\n");
        serviceImplContent.append("}");
        String serviceImplFileFullName = servicePath + packageName + "\\" + serviceImplName;
        cg.deleteFile(serviceImplFileFullName);
        cg.CreateFile(serviceImplFileFullName);
        cg.writeFile(serviceImplFileFullName, serviceImplContent.toString());
    }

    private void generateDao(CodeGenerator cg) throws Exception {
        //dao
        String daoName = fileJavaName + "Dao.java";
        StringBuffer daoContent = new StringBuffer();
        daoContent.append("package dao." + packageName + ";\n");
        daoContent.append("import common.dao.BaseDao;\n");
        daoContent.append("import com.model." + fileJavaName + ";\n");
        daoContent.append("public interface " + fileJavaName + "Dao extends BaseDao<" + fileJavaName + "> {" + "\n");
        daoContent.append("\n");
        daoContent.append("}");
        String daoFileFullName = daoPath + packageName + "\\" + daoName;
        cg.deleteFile(daoFileFullName);
        cg.CreateFile(daoFileFullName);
        cg.writeFile(daoFileFullName, daoContent.toString());

        //daoImpl
        String daoImplName = fileJavaName + "DaoImpl.java";
        StringBuffer daoImplContent = new StringBuffer();
        daoImplContent.append("package dao." + packageName + ";\n");
        daoImplContent.append("import org.springframework.stereotype.Repository;\n");
        daoImplContent.append("import common.dao.BaseDaoImpl;\n");
        daoImplContent.append("import com.model." + fileJavaName + ";\n");
        daoImplContent.append("@Repository(\"" + packageName + "Dao\")\n");
        daoImplContent.append("public class " + fileJavaName + "DaoImpl extends BaseDaoImpl<" + fileJavaName + "> implements " + fileJavaName + "Dao {");
        daoImplContent.append("\n");
        daoImplContent.append("\n");
        daoImplContent.append("}");
        String daoImplFileFullName = daoPath + packageName + "\\" + daoImplName;
        cg.deleteFile(daoImplFileFullName);
        cg.CreateFile(daoImplFileFullName);
        cg.writeFile(daoImplFileFullName, daoImplContent.toString());
    }

    public void writeFile(String fileName, String fileContent) throws Exception {
        File file = new File(fileName);
        // true 是添加到文件末尾
        FileOutputStream fos = new FileOutputStream(file, true);

        try {
            fos.write(fileContent.getBytes());
        } finally {
            fos.close();
        }
    }

    /**
     * 创建单个文件
     *
     * @param destFileName 文件名
     * @return 创建成功返回true，否则返回false
     */
    public boolean CreateFile(String destFileName) {

        File file = new File(destFileName);

        if (file.exists()) {
            System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
            return false;
        }

        if (destFileName.endsWith(File.separator)) {
            System.out.println("创建单个文件" + destFileName + "失败，目标不能是目录！");
            return false;
        }

        if (!file.getParentFile().exists()) {
            System.out.println("目标文件所在路径不存在，准备创建。。。");
            if (!file.getParentFile().mkdirs()) {

                System.out.println("创建目录文件所在的目录失败！");
                return false;
            }
        }

        // 创建目标文件
        try {
            if (file.createNewFile()) {
                System.out.println("创建单个文件" + destFileName + "成功！");
                return true;
            } else {
                System.out.println("创建单个文件" + destFileName + "失败！");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("创建单个文件" + destFileName + "失败！");
            return false;
        }

    }

    /**
     * 创建目录
     *
     * @param destDirName 目标目录名
     * @return 目录创建成功返回true，否则返回false
     */
    public boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            System.out.println("创建目录" + destDirName + "失败，目标目录已存在！");
            return false;
        }
        if (!destDirName.endsWith(File.separator))
            destDirName = destDirName + File.separator;
        // 创建单个目录
        if (dir.mkdirs()) {
            System.out.println("创建目录" + destDirName + "成功！");
            return true;
        } else {
            System.out.println("创建目录" + destDirName + "成功！");
            return false;
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

}
