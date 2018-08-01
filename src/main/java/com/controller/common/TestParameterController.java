package com.controller.common;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.model.BaseSample;
import com.model.TestParameter;
import com.service.common.BaseSampleService;
import com.service.common.TestParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

@Controller
@RequestMapping("testParameterAction")
public class TestParameterController extends QueryAction<TestParameter> {

    /**
     *
     */
    @Autowired
    private HttpServletRequest request;


    private static final long serialVersionUID = 1L;

    /*private String strTestParameter = "";

    private String strTestParameterId = "";

    private String strBaseSampleId = "";

    private String strDepartmentId = "";

    private String strReportSn = "";//编号规则*/

    @Autowired
    private TestParameterService testParameterService;
    @Autowired
    private BaseSampleService baseSampleService;

    /*@Action(value = "/testParameterAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    public String jompSys() {
        return "/jsp/sys/sysCode";
    }

    @RequestMapping("saveTeParameter.action")
    @ResponseBody
    public String saveTeParameter(String strTestParameter, String userId) {
        try {
            if (CommonMethod.isNull(strTestParameter)) {
                throw new BusinessException("fail,参数strTestParameter不能为空！", "");
            }
            testParameterService.saveTeParameter(getColl(strTestParameter), userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("updateTeParameter.action")
    @ResponseBody
    public String updateTeParameter(String strTestParameter, String userId) {
        try {
            if (CommonMethod.isNull(strTestParameter)) {
                throw new BusinessException("fail,参数strTestParameter不能为空！", "");
            }
            testParameterService.updateTeParameter(getColl(strTestParameter), userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("updateReportSn.action")
    @ResponseBody
    public String updateReportSn(String strBaseSampleId, String userId, String strReportSn) {
        try {
            if (CommonMethod.isNull(strBaseSampleId) || CommonMethod.isNull(strReportSn)) {
                throw new BusinessException("fail,参数strBaseSampleId不能为空！", "");
            }
            testParameterService.updateReportSn(strBaseSampleId, strReportSn, userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("uploadReport.action")
    @ResponseBody
    public String uploadReport(@RequestParam MultipartFile[] template, String strTestParameterId, String userId) {
        System.out.println("------------------------------文件上传开始");
        try {
			response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		    
		    if(CommonMethod.isNull(strTestParameterId)){
		    	throw new BusinessException("fail,参数strTestParameterId不能为空！","");
			}


            for (MultipartFile sample : template) {
                File reportFile = (File) sample;
                String[] names = sample.getName().split("\\.");

		    	
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
                    String savePath = request.getSession().getServletContext().getRealPath("/") + realAdd;
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
                    tp.setUpdater(userId);
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
        } catch (BusinessException e) {
			e.printStackTrace();
			jsonPrint("fail:"+e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			jsonPrint("error:"+e.getMessage());
        }
        return "true";
    }

    @RequestMapping("findTeParameter.action")
    @ResponseBody
    public List<TestParameter> findTeParameter(String strBaseSampleId) {
        List<TestParameter> teParameterList = testParameterService.findTeParameter(strBaseSampleId);
        return teParameterList;
    }

    @RequestMapping("findAllParameter.action")
    @ResponseBody
    public List<TestParameter> findAllParameter(String strDepartmentId) {

        if (CommonMethod.isNull(strDepartmentId) || CommonMethod.isNull(strDepartmentId)) {
            throw new BusinessException("fail,参数strDepartmentId不能为空！", "");
        }
        List<TestParameter> teParameterList = testParameterService.findAllParameter(strDepartmentId);
        return teParameterList;
    }

    /*public String getStrDepartmentId() {
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
    }*/
}
