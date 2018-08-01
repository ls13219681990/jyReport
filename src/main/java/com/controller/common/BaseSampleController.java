package com.controller.common;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.dao.page.BaseSamplePage;
import com.dao.page.TemplateInfoPage;
import com.dao.page.TemplateLocationPage;
import com.dao.page.TestDataInfoPage;
import com.model.BaseSample;
import com.model.TemplateInfo;
import com.service.common.BaseSampleService;
import com.service.common.TemplateInfoService;
import com.service.common.TemplateLocationService;
import net.sf.json.JSONObject;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "baseSampleAction")
public class BaseSampleController extends QueryAction<BaseSample> {
    private final static HttpServletRequest REQUEST = null;
    /**
     *
     */

    @Autowired
    private HttpServletRequest request;


   /* private static final long serialVersionUID = 1L;

    private String strBaseSample = "";

    private String strDepartmentId = "";

    private String strBaseSampleId = "";

    private String strlogName = "";

    private String strTemplateLocation = "";//坐标对象

    private String strTemplateInfo = "";//模板信息对象

    private String strTemplateInfoId = "";//模板信息ID*/

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

    @RequestMapping("saveSample.action")
    @ResponseBody
    public String saveSample(String strBaseSample, String userId) {
        try {
            if (CommonMethod.isNull(strBaseSample)) {
                throw new BusinessException("fail,参数strBaseSample不能为空");

            }
            baseSampleService.saveSample(getColl(strBaseSample), userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping("updateSample.action")
    @ResponseBody
    public String updateSample(String strBaseSample, String strTemplateLocation, String userId) {
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

            baseSampleService.updateSample(bs, tLocation, userId, updateFlag);

        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "trun";
    }

    @RequestMapping("findSample.action")
    @ResponseBody
    public List<BaseSamplePage> findSample(String strDepartmentId) {
        List<BaseSamplePage> sampleList = baseSampleService.findSample(strDepartmentId);
        return sampleList;
    }

    @RequestMapping("findTemplateLocation.action")
    @ResponseBody
    public List<TemplateLocationPage> findTemplateLocation(String strTemplateInfoId) {
        List<TemplateLocationPage> tLocationList = templateLocationService.findTLocationPageList(strTemplateInfoId);
        return tLocationList;
    }

    /**
     * 保存模板（根据客户2017年4月10日要求将模板保存从参数表迁移到样品）
     */
    @RequestMapping("uploadSampleReport.action")
    @ResponseBody
    public String uploadSampleReport(@RequestParam MultipartFile[] template, String strTemplateInfoId, String userId) {
        System.out.println("------------------------------文件上传开始");
        try {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");

            if (CommonMethod.isNull(strTemplateInfoId)) {
                throw new BusinessException("fail,参数strTemplateInfoId不能为空！", "");
            }

            for (MultipartFile sample : template) {
                if (sample.isEmpty()) {

                    File reportFile = (File) sample;
                    String[] names = sample.getName().split("\\.");
                    if (sample.getSize() > (1024 * 1024)) {//文件大于1M
                        throw new BusinessException("模板文件太大，不能上传！", "");
                    }
                    boolean gs = false;
                    String hz = "";
                    if (names.length > 1) {
                        hz = names[names.length - 1];
                        if (!"xls".equalsIgnoreCase(hz) && !"xlsx".equalsIgnoreCase(hz)) {
                            gs = true;
                        }
                    } else {
                        gs = true;
                    }
                    if (gs) {//文件必须为xls、xlsx格式
                        throw new BusinessException("模板格式不对，不能上传！", "");
                    }
                    FileInputStream fis = null;
                    FileOutputStream out = null;
                    try {
                        fis = new FileInputStream(reportFile);
                        TemplateInfo ti = templateInfoService.findById(strTemplateInfoId);
                        if (ti == null) {
                            throw new BusinessException("模板信息记录为空，不能上传！", "");
                        }
                        //样品数据
                        BaseSample bs = baseSampleService.findById(ti.getSampleId());
                        String realAdd = "upload" + "/" + bs.getDepartmentId();
                        String savePath = request.getSession().getServletContext().getRealPath("/") + realAdd;
                        System.out.println("------------------------------文件保存路径:" + savePath);
                        File newFile = new File(savePath.toString());
                        if ((!newFile.exists()) && (!newFile.isDirectory())) {
                            newFile.mkdirs();
                        }
                        boolean isSuccess = true;
                        File oldFile = new File(savePath.toString(), strTemplateInfoId + "." + hz);
                        if (oldFile.exists()) {
                            isSuccess = oldFile.delete();
                        }
                        if (!isSuccess) {
                            throw new BusinessException("文件上传失败！", "");
                        }
                        out = new FileOutputStream(savePath.toString() + "\\" + strTemplateInfoId + "." + hz);
                        byte buffer[] = new byte[new Long(reportFile.length()).intValue()];
                        int len = 0;

                        while ((len = fis.read(buffer)) > 0) {
                            out.write(buffer, 0, len);
                        }
                        fis.close();
                        //关闭输出流
                        out.close();
                        System.out.println("------------------------------文件上传结束");
                        //模板路径
                        ti.setTemplatePath(realAdd);
                        //模板名称
                        ti.setTemplateName(strTemplateInfoId + "." + hz);
                        //更新人
                        ti.setInputer(userId);
                        //更新时间
                        ti.setInputeTime(CommonMethod.getDate());
                        templateInfoService.update(ti);
                        System.out.println("------------------------------数据处理结束");
                    } catch (Exception e) {
                        if (null != fis) {
                            fis.close();
                        }
                        if (null != out) {
                            out.close();
                        }
                        e.printStackTrace();
                    } finally {
                        if (null != fis) {
                            fis.close();
                        }
                        if (null != out) {
                            out.close();
                        }
                    }

                }
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
        return "true";
    }


    /**
     * 保存日志（根据客户2017年5月10日要求增加日志保存功能）
     */
    @RequestMapping("saveReportLogs.action")
    @ResponseBody
    public String saveReportLogs(@RequestParam MultipartFile[] template, String strlogName) {
        System.out.println("------------------------------日志上传开始");
        try {
            for (MultipartFile sample : template) {
                File reportFile = (File) sample;
                String[] names = sample.getName().split("\\.");
                String hz = names[names.length - 1];

                FileInputStream fis = null;
                FileOutputStream out = null;
                try {
                    fis = new FileInputStream(reportFile);
                    String yearMonth = CommonMethod.getCurrentMonth();
                    String yearMonthDay = CommonMethod.getCurrentDate();

                    String realAdd = "reportLogs" + "/" + yearMonth + "/" + yearMonthDay;
                    String savePath = request.getSession().getServletContext().getRealPath("/") + realAdd;
                    System.out.println("------------------------------日志保存路径:" + savePath);
                    File newFile = new File(savePath.toString());
                    if ((!newFile.exists()) && (!newFile.isDirectory())) {
                        newFile.mkdirs();
                    }
                    boolean isSuccess = true;
                    File oldFile = new File(savePath.toString(), strlogName + "." + hz);
                    if (oldFile.exists()) {
                        isSuccess = oldFile.delete();
                    }
                    if (!isSuccess) {
                        throw new BusinessException("文件上传失败！", "");
                    }
                    out = new FileOutputStream(savePath.toString() + "\\" + strlogName + "." + hz);
                    byte buffer[] = new byte[new Long(reportFile.length()).intValue()];
                    int len = 0;

                    while ((len = fis.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    fis.close();
                    //关闭输出流
                    out.close();
                    System.out.println("------------------------------日志上传结束");
                } catch (Exception e) {
                    if (null != fis) {
                        fis.close();
                    }
                    if (null != out) {
                        out.close();
                    }
                    e.printStackTrace();
                } finally {
                    if (null != fis) {
                        fis.close();
                    }
                    if (null != out) {
                        out.close();
                    }
                }
            }

        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("saveTemplateInfo.action")
    @ResponseBody
    public String saveTemplateInfo(String userId, String strTemplateInfo) {
        String templateInfoId = null;
        try {
            if (CommonMethod.isNull(strTemplateInfo)) {
                throw new BusinessException("fail,参数strTemplateInfo不能为空");
            }

            TemplateInfoPage esPage = new TemplateInfoPage();
            JSONObject pJsonObject = JSONObject.fromObject(strTemplateInfo);
            esPage = (TemplateInfoPage) JSONObject.toBean(pJsonObject, TemplateInfoPage.class);

            templateInfoId = baseSampleService.saveTemplateInfo(esPage, userId);

            //jsonPrint("true:" + templateInfoId);

        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true" + templateInfoId;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping("updateTemplateInfo.action")
    @ResponseBody
    public String updateTemplateInfo(String strTemplateInfo, String strTemplateLocation, String userId) {
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

            baseSampleService.updateTemplateInfo(esPage, userId, tLocation, updateFlag);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("deleteTemplateInfo.action")
    @ResponseBody
    public String deleteTemplateInfo(String strTemplateInfo, String userId) {
        try {
            if (CommonMethod.isNull(strTemplateInfo)) {
                throw new BusinessException("fail,参数strTemplateInfo不能为空");
            }

            TemplateInfoPage esPage = new TemplateInfoPage();
            JSONObject pJsonObject = JSONObject.fromObject(strTemplateInfo);
            esPage = (TemplateInfoPage) JSONObject.toBean(pJsonObject, TemplateInfoPage.class);

            baseSampleService.deleteTemplateInfo(esPage, userId);

        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("findTemplateInfo.action")
    @ResponseBody
    public List<TemplateInfoPage> findTemplateInfo(String strBaseSampleId) {
        List<TemplateInfoPage> tInfoList = templateInfoService.findTInfoPageList(strBaseSampleId);
        return tInfoList;
    }

   /* public String getStrBaseSample() {
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
    }*/
}
