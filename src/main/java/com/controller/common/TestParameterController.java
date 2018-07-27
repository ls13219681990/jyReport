package com.controller.common;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.model.TestParameter;
import com.service.common.BaseSampleService;
import com.service.common.TestParameterService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("testParameterAction")
public class TestParameterController extends QueryAction<TestParameter> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String strTestParameter = "";

    private String strTestParameterId = "";

    private String strBaseSampleId = "";

    private String strDepartmentId = "";

    private String strReportSn = "";//编号规则

    @Autowired
    private TestParameterService testParameterService;
    @Autowired
    private BaseSampleService baseSampleService;

    /*@Action(value = "/testParameterAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    public String jompSys() {
        return "/jsp/sys/sysCode";
    }

    public void saveTeParameter() {
        try {
            if (CommonMethod.isNull(strTestParameter)) {
                jsonPrint("fail,参数strTestParameter不能为空");
                return;
            }
            testParameterService.saveTeParameter(getColl(strTestParameter), getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @RequestMapping("updateTeParameter.action")
    public void updateTeParameter() {
        try {
            if (CommonMethod.isNull(strTestParameter)) {
                jsonPrint("fail,参数strTestParameter不能为空");
                return;
            }
            testParameterService.updateTeParameter(getColl(strTestParameter), getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @RequestMapping("updateReportSn.action")
    public void updateReportSn() {
        try {
            if (CommonMethod.isNull(strBaseSampleId) || CommonMethod.isNull(strReportSn)) {
                jsonPrint("fail,参数strBaseSampleId或者strReportSn不能为空");
                return;
            }
            testParameterService.updateReportSn(strBaseSampleId, strReportSn, getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @RequestMapping("uploadReport.action")
    public void uploadReport() {
        System.out.println("------------------------------文件上传开始");
		/*try {
			response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		    
		    if(CommonMethod.isNull(strTestParameterId)){
		    	throw new BusinessException("fail,参数strTestParameterId不能为空！","");
			}
			HttpServletRequest request = ServletActionContext.getRequest();
			
			MultiPartRequestWrapper multipartRequest = (MultiPartRequestWrapper) request;
			String[] fileNames = multipartRequest.getFileNames("file");
			File[] reportFiles = multipartRequest.getFiles("file");

		    for(int i=0;i<reportFiles.length;i++){
		    	File reportFile =  reportFiles[i];
		    	String fileName = fileNames[i];
		    	String[] names = fileName.split("\\.");
		    	
		    	if(reportFile.length()>(1024 * 1024)){//文件大于1M
					throw new BusinessException("模板文件太大，不能上传！","");
				}
		    	boolean gs = false;
		    	String hz = "";
				if(names.length>1){
					hz = names[names.length-1];
					if(!"xls".equalsIgnoreCase(hz) && !"xlsx".equalsIgnoreCase(hz)){
						gs = true;
					}
				}else{
					gs = true;
				}
				if(gs){//文件必须为xls、xlsx格式
					throw new BusinessException("模板格式不对，不能上传！","");
				}
				FileInputStream fis = null;
				FileOutputStream out = null;
				try{
					fis = new FileInputStream(reportFile);
					//参数数据
					TestParameter tp = testParameterService.findById(strTestParameterId);
					//样品数据
					BaseSample bs = baseSampleService.findById(tp.getSampleId());
					String realAdd = "upload"+"/"+bs.getDepartmentId()+"/"+tp.getSampleId();
					String savePath = ServletActionContext.getServletContext().getRealPath("/"+realAdd);
					System.out.println("------------------------------文件保存路径:"+savePath);
					File newFile = new File(savePath.toString());
					if((!newFile.exists()) && (!newFile.isDirectory())){
						newFile.mkdirs(); 
					}
					boolean isSuccess =true;
					File oldFile = new File(savePath.toString(),strTestParameterId + "." +hz);
					if(oldFile.exists()){
						isSuccess = oldFile.delete();
					}
					if(!isSuccess){
						throw new BusinessException("文件上传失败！","");
					}
					out = new FileOutputStream(savePath.toString() + "\\" + strTestParameterId + "." +hz);
//					out = new FileOutputStream(savePath.toString() + "\\" + "test123" + "." +hz);
					byte buffer[] = new byte[new Long(reportFile.length()).intValue()];
					int len = 0;
					
					while((len=fis.read(buffer))>0){
						out.write(buffer, 0, len);
					}
					fis.close();
					//关闭输出流
					out.close();
					System.out.println("------------------------------文件上传结束");
					//模板路径
					tp.setTemplatePath(realAdd);
					//模板名称
					tp.setTemplateName(strTestParameterId + "." +hz);
					//更新人
					tp.setUpdater(getUserId());
					//更新时间
					tp.setUpdateTime(CommonMethod.getDate());
					testParameterService.update(tp);
					System.out.println("------------------------------数据处理结束");
				}catch (Exception e) {
					if (null != fis) {
						fis.close();
					}
					if ( null != out) {
						out.close();
					}
					e.printStackTrace();
				}finally {
					if (null != fis) {
						fis.close();
					}
					if ( null != out) {
						out.close();
					}
				}


		    }
			jsonPrint("true");
		}*//*catch (BusinessException e) {
			e.printStackTrace();
			jsonPrint("fail:"+e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			jsonPrint("error:"+e.getMessage());
		}*/
    }

    @RequestMapping("findTeParameter.action")
    public void findTeParameter() {
        List<TestParameter> teParameterList = testParameterService.findTeParameter(strBaseSampleId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(teParameterList, jsonConfig);
        jsonPrint(jsonArr);
    }

    @RequestMapping("findAllParameter.action")
    public void findAllParameter() {

        if (CommonMethod.isNull(strDepartmentId) || CommonMethod.isNull(strDepartmentId)) {
            jsonPrint("fail,参数strDepartmentId不能为空");
            return;
        }
        List<TestParameter> teParameterList = testParameterService.findAllParameter(strDepartmentId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(teParameterList, jsonConfig);
        jsonPrint(jsonArr);
    }

    public String getStrDepartmentId() {
        return strDepartmentId;
    }

    public void setStrDepartmentId(String strDepartmentId) {
        this.strDepartmentId = strDepartmentId;
    }

    public String getStrTestParameter() {
        return strTestParameter;
    }

    public void setStrTestParameter(String strTestParameter) {
        this.strTestParameter = strTestParameter;
    }

    public String getStrTestParameterId() {
        return strTestParameterId;
    }

    public void setStrTestParameterId(String strTestParameterId) {
        this.strTestParameterId = strTestParameterId;
    }

    public String getStrBaseSampleId() {
        return strBaseSampleId;
    }

    public void setStrBaseSampleId(String strBaseSampleId) {
        this.strBaseSampleId = strBaseSampleId;
    }

    public String getStrReportSn() {
        return strReportSn;
    }

    public void setStrReportSn(String strReportSn) {
        this.strReportSn = strReportSn;
    }
}
