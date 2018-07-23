package com.controller.common;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.dao.page.BaseSamplePage;
import com.dao.page.TemplateInfoPage;
import com.dao.page.TemplateLocationPage;
import com.dao.page.TestDataInfoPage;
import com.model.BaseSample;
import com.service.common.BaseSampleService;
import com.service.common.TemplateInfoService;
import com.service.common.TemplateLocationService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BaseSampleAction extends QueryAction<BaseSample> {
    private final static HttpServletRequest REQUEST = null;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String strBaseSample = "";

    private String strDepartmentId = "";

    private String strBaseSampleId = "";

    private String strlogName = "";

    private String strTemplateLocation = "";//坐标对象

    private String strTemplateInfo = "";//模板信息对象

    private String strTemplateInfoId = "";//模板信息ID

    @Autowired
    private BaseSampleService baseSampleService;
    @Autowired
    private TemplateLocationService templateLocationService;
    @Autowired
    private TemplateInfoService templateInfoService;

    /*@Action(value = "/baseSampleAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    public String jompSys() {
        return "/jsp/sys/sysCode";
    }


    public void saveSample() {
        try {
            if (CommonMethod.isNull(strBaseSample)) {
                jsonPrint("fail,参数strBaseSample不能为空");
                return;
            }
            baseSampleService.saveSample(getColl(strBaseSample), getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @SuppressWarnings("rawtypes")
    public void updateSample() {
        try {
            String updateFlag = "";
            BaseSample bs = new BaseSample();
            if (!CommonMethod.isNull(strBaseSample)) {
                JSONObject pJsonObject = JSONObject.fromObject(strBaseSample);
                bs = (BaseSample) JSONObject.toBean(pJsonObject, BaseSample.class);
            } else {
                updateFlag = "0";
            }

            TemplateLocationPage tLocation = new TemplateLocationPage();
            if (!CommonMethod.isNull(strTemplateLocation)) {
                Map<String, Class> classMap = new HashMap<String, Class>();
                classMap.put("testDataInfoList", TestDataInfoPage.class);//模板检测数据List

                JSONObject tJsonObject = JSONObject.fromObject(strTemplateLocation);
                tLocation = (TemplateLocationPage) JSONObject.toBean(tJsonObject, TemplateLocationPage.class, classMap);
            } else {
                updateFlag = "1";
            }

            baseSampleService.updateSample(bs, tLocation, getUserId(), updateFlag);
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public void findSample() {
        List<BaseSamplePage> sampleList = baseSampleService.findSample(strDepartmentId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(sampleList, jsonConfig);
        jsonPrint(jsonArr);
    }

    public void findTemplateLocation() {
        List<TemplateLocationPage> tLocationList = templateLocationService.findTLocationPageList(strTemplateInfoId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(tLocationList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 保存模板（根据客户2017年4月10日要求将模板保存从参数表迁移到样品）
     */
    public void uploadSampleReport() {
        System.out.println("------------------------------文件上传开始");
		/*try {
			response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		    
		    if(CommonMethod.isNull(strTemplateInfoId)){
		    	throw new BusinessException("fail,参数strTemplateInfoId不能为空！","");
			}

			MultiPartRequestWrapper multipartRequest = (MultiPartRequestWrapper) REQUEST;
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
					TemplateInfo ti = templateInfoService.findById(strTemplateInfoId);
					if(ti == null){
						throw new BusinessException("模板信息记录为空，不能上传！","");
					}
					//样品数据
					BaseSample bs = baseSampleService.findById(ti.getSampleId());
					String realAdd = "upload"+"/"+bs.getDepartmentId();
					String savePath = REQUEST.getServletContext().getRealPath("/"+realAdd);
					System.out.println("------------------------------文件保存路径:"+savePath);
					File newFile = new File(savePath.toString());
					if((!newFile.exists()) && (!newFile.isDirectory())){
						newFile.mkdirs(); 
					}
					boolean isSuccess =true;
					File oldFile = new File(savePath.toString(),strTemplateInfoId + "." +hz);
					if(oldFile.exists()){
						isSuccess = oldFile.delete();
					}
					if(!isSuccess){
						throw new BusinessException("文件上传失败！","");
					}
					out = new FileOutputStream(savePath.toString() + "\\" + strTemplateInfoId + "." +hz);
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
					ti.setTemplatePath(realAdd);
					//模板名称
					ti.setTemplateName(strTemplateInfoId + "." +hz);
					//更新人
					ti.setInputer(getUserId());
					//更新时间
					ti.setInputeTime(CommonMethod.getDate());
					templateInfoService.update(ti);
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
		    }*/
        jsonPrint("true");
		/*}catch (BusinessException e) {
			e.printStackTrace();
			jsonPrint("fail:"+e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			jsonPrint("error:"+e.getMessage());
		}*/
    }


    /**
     * 保存日志（根据客户2017年5月10日要求增加日志保存功能）
     */
    public void saveReportLogs() {
        System.out.println("------------------------------日志上传开始");
		/*try {
			response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		    
			HttpServletRequest request = ServletActionContext.getRequest();
			MultiPartRequestWrapper multipartRequest = (MultiPartRequestWrapper) request;
			String[] fileNames = multipartRequest.getFileNames("file");
			File[] reportFiles = multipartRequest.getFiles("file");

			for(int i=0;i<reportFiles.length;i++){
				File reportFile =  reportFiles[i];
		    	String fileName = fileNames[i];
		    	String[] names = fileName.split("\\.");
		    	
		    	String hz = names[names.length-1];
				
				FileInputStream fis = null;
				FileOutputStream out = null;
				try{
					fis = new FileInputStream(reportFile);
					String yearMonth = CommonMethod.getCurrentMonth();
					String yearMonthDay = CommonMethod.getCurrentDate();

					String realAdd = "reportLogs"+"/"+yearMonth+"/"+yearMonthDay;
					String savePath = REQUEST.getServletContext().getRealPath("/"+realAdd);
					System.out.println("------------------------------日志保存路径:"+savePath);
					File newFile = new File(savePath.toString());
					if((!newFile.exists()) && (!newFile.isDirectory())){
						newFile.mkdirs(); 
					}
					boolean isSuccess =true;
					File oldFile = new File(savePath.toString(),strlogName + "." +hz);
					if(oldFile.exists()){
						isSuccess = oldFile.delete();
					}
					if(!isSuccess){
						throw new BusinessException("文件上传失败！","");
					}
					out = new FileOutputStream(savePath.toString() + "\\" + strlogName + "." +hz);
					byte buffer[] = new byte[new Long(reportFile.length()).intValue()];
					int len = 0;

					while((len=fis.read(buffer))>0){
						out.write(buffer, 0, len);
					}
					fis.close();
					//关闭输出流
					out.close();
					System.out.println("------------------------------日志上传结束");
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
		    }*/
        jsonPrint("true");
		/*}catch (BusinessException e) {
			e.printStackTrace();
			jsonPrint("fail:"+e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			jsonPrint("error:"+e.getMessage());
		}*/
    }

    public void saveTemplateInfo() {
        try {
            if (CommonMethod.isNull(strTemplateInfo)) {
                jsonPrint("fail,参数strTemplateInfo不能为空");
                return;
            }

            TemplateInfoPage esPage = new TemplateInfoPage();
            JSONObject pJsonObject = JSONObject.fromObject(strTemplateInfo);
            esPage = (TemplateInfoPage) JSONObject.toBean(pJsonObject, TemplateInfoPage.class);

            String templateInfoId = baseSampleService.saveTemplateInfo(esPage, getUserId());
            jsonPrint("true:" + templateInfoId);
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @SuppressWarnings("rawtypes")
    public void updateTemplateInfo() {
        try {
            String updateFlag = "";
            TemplateInfoPage esPage = new TemplateInfoPage();
            if (!CommonMethod.isNull(strTemplateInfo)) {
                JSONObject pJsonObject = JSONObject.fromObject(strTemplateInfo);
                esPage = (TemplateInfoPage) JSONObject.toBean(pJsonObject, TemplateInfoPage.class);
            } else {
                updateFlag = "0";
            }

            TemplateLocationPage tLocation = new TemplateLocationPage();
            if (!CommonMethod.isNull(strTemplateLocation)) {
                Map<String, Class> classMap = new HashMap<String, Class>();
                classMap.put("testDataInfoList", TestDataInfoPage.class);//模板检测数据List

                JSONObject tJsonObject = JSONObject.fromObject(strTemplateLocation);
                tLocation = (TemplateLocationPage) JSONObject.toBean(tJsonObject, TemplateLocationPage.class, classMap);
            } else {
                updateFlag = "1";
            }

            baseSampleService.updateTemplateInfo(esPage, getUserId(), tLocation, updateFlag);
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public void deleteTemplateInfo() {
        try {
            if (CommonMethod.isNull(strTemplateInfo)) {
                jsonPrint("fail,参数strTemplateInfo不能为空");
                return;
            }

            TemplateInfoPage esPage = new TemplateInfoPage();
            JSONObject pJsonObject = JSONObject.fromObject(strTemplateInfo);
            esPage = (TemplateInfoPage) JSONObject.toBean(pJsonObject, TemplateInfoPage.class);

            baseSampleService.deleteTemplateInfo(esPage, getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public void findTemplateInfo() {
        List<TemplateInfoPage> tInfoList = templateInfoService.findTInfoPageList(strBaseSampleId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(tInfoList, jsonConfig);
        jsonPrint(jsonArr);
    }

    public String getStrBaseSample() {
        return strBaseSample;
    }

    public void setStrBaseSample(String strBaseSample) {
        this.strBaseSample = strBaseSample;
    }

    public String getStrDepartmentId() {
        return strDepartmentId;
    }

    public void setStrDepartmentId(String strDepartmentId) {
        this.strDepartmentId = strDepartmentId;
    }

    public String getStrBaseSampleId() {
        return strBaseSampleId;
    }

    public void setStrBaseSampleId(String strBaseSampleId) {
        this.strBaseSampleId = strBaseSampleId;
    }

    public String getStrlogName() {
        return strlogName;
    }

    public void setStrlogName(String strlogName) {
        this.strlogName = strlogName;
    }

    public String getStrTemplateLocation() {
        return strTemplateLocation;
    }

    public void setStrTemplateLocation(String strTemplateLocation) {
        this.strTemplateLocation = strTemplateLocation;
    }

    public String getStrTemplateInfo() {
        return strTemplateInfo;
    }

    public void setStrTemplateInfo(String strTemplateInfo) {
        this.strTemplateInfo = strTemplateInfo;
    }

    public String getStrTemplateInfoId() {
        return strTemplateInfoId;
    }

    public void setStrTemplateInfoId(String strTemplateInfoId) {
        this.strTemplateInfoId = strTemplateInfoId;
    }
}
