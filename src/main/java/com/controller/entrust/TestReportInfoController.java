package com.controller.entrust;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.UuidUtil;
import com.common.jsonProcessor.TimestampMorpher;
import com.dao.page.ReportTemplateInfoPage;
import com.dao.page.SampleReportPage;
import com.dao.page.SampleReportRelationPage;
import com.dao.page.TestReportInfoPage;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.model.*;
import com.service.common.BaseSampleService;
import com.service.entrust.*;
import com.service.sys.SysCodeService;
import com.service.sys.SysUserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
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
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("testReportInfoAction")
public class TestReportInfoController extends QueryAction<TestReportInfo> {

    /**
     *
     */

    @Autowired
    private HttpServletRequest request;

    private static final long serialVersionUID = 1L;
   /* // 委托明细ID
    private String strEDetailId = "";
    // 检测报告信息
    private String strTReportInfo = "";
    // 二维码ID
    private String strTwoDInfoId = "";
    // 报告ID
    private String strReportId = "";
    // 报告流程状态
    private String strStatus = "";
    // 模板对象ID
    private String strTemplateInfoId = "";

    private String backStatus;

    private String reportId;// 报告ID
    private String printNum;// 打印次数
    private String distributeTime;// 打印时间
    private String reportStatus;// 报告状态

    private String reportNumber;*/

    @Autowired
    private TestReportInfoService testReportInfoService;
    @Autowired
    private BaseSampleService baseSampleService;
    @Autowired
    private TwoDInfoService twoDInfoService;
    @Autowired
    private SampleReportService sampleReportService;
    @Autowired
    private EntrustDetailService entrustDetailService;
    @Autowired
    private EntrustInfoService entrustInfoService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysCodeService sysCodeService;

    //@Action(value = "/testReportInfoAction", results = { @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })

    @RequestMapping(value = "/zxaqebaseDepartmentAction")
    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    @RequestMapping("findReportInfo.action")
    @ResponseBody

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public List<TestReportInfoPage> findReportInfo(String strEDetailId, String strStatus) {
        List<TestReportInfoPage> triPageList = testReportInfoService
                .findReportInfo(strEDetailId, strStatus);

        return triPageList;
    }

    /***
     * 查询报告头部内容
     */
    @RequestMapping("findReportTitleInfo.action")
    @ResponseBody
    public List<ReportTemplateInfoPage> findReportTitleInfo(String strReportId) {
        if (CommonMethod.isNull(strReportId)) {
            throw new BusinessException("fail,参数strReportId不能为空！", "");
        }
        List<ReportTemplateInfoPage> rtInfoPageList = testReportInfoService
                .findReportTitleInfo(strReportId);
        return rtInfoPageList;
    }

    /***
     * 打回报告修改报告状态
     */
    @RequestMapping("updateTestReportInfoByStatus.action")
    @ResponseBody
    public String updateTestReportInfoByStatus(String userId, String backStatus, String strTReportInfo) {

        try {
            if (CommonMethod.isNull(strTReportInfo)) {
                throw new BusinessException("fail,参数strTReportInfo不能为空！", "");
            }
            if (CommonMethod.isNull(backStatus)) {
                throw new BusinessException("fail,参数backStatus不能为空！", "");
            }
            TestReportInfoPage collPage = (TestReportInfoPage) toBean(
                    strTReportInfo, TestReportInfoPage.class);
            testReportInfoService.updateTestReportByReportStatus(collPage,
                    userId, backStatus);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    /***
     * 根据样品编号组合保存报告数据
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("saveSampleReportInfo.action")
    @ResponseBody
    public String saveSampleReportInfo(String strTReportInfo, String userId) {
        try {
            if (CommonMethod.isNull(strTReportInfo)) {
                throw new BusinessException("fail,参数strTReportInfo不能为空！", "");
            }
            strTReportInfo = strTReportInfo.replace("PERCENT", "%");
            Map<String, Class> classMap = new HashMap<String, Class>();
            classMap.put("sampleReportRelationList", SampleReportPage.class);// 样品关系明细
            classMap.put("testReportInfoPageList", TestReportInfoPage.class);// 报告明细
            classMap.put("sampleReportList", SampleReportPage.class);// 样品报告关系明细

            SampleReportRelationPage srrPage = new SampleReportRelationPage();
            JSONObject pJsonObject = JSONObject.fromObject(strTReportInfo);
            srrPage = (SampleReportRelationPage) JSONObject.toBean(pJsonObject,
                    SampleReportRelationPage.class, classMap);

            testReportInfoService.saveSampleReportInfo(srrPage, userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    /***
     * 修改报告数据
     */
    @RequestMapping("updateTestReportInfo.action")
    @ResponseBody
    public String updateTestReportInfo(String strTReportInfo, String userId) {
        try {
            if (CommonMethod.isNull(strTReportInfo)) {
                throw new BusinessException("fail,参数strTReportInfo不能为空！", "");
            }
            strTReportInfo = strTReportInfo.replace("OO", "#");
            TestReportInfoPage collPage = (TestReportInfoPage) toBean(
                    strTReportInfo, TestReportInfoPage.class);
            testReportInfoService.updateTestReport(collPage, userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    /***
     * 修改样品报告关系
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("updateSampleReportByReportId.action")
    @ResponseBody
    public String updateSampleReportByReportId(String strTReportInfo, String userId) {
        try {
            if (CommonMethod.isNull(strTReportInfo)) {
                throw new BusinessException("fail,参数strTReportInfo不能为空！", "");
            }

            Map<String, Class> classMap = new HashMap<String, Class>();
            classMap.put("testReportInfoPageList", TestReportInfoPage.class);// 报告明细
            classMap.put("sampleReportList", SampleReportPage.class);// 样品报告关系明细

            SampleReportRelationPage srrPage = new SampleReportRelationPage();
            JSONObject pJsonObject = JSONObject.fromObject(strTReportInfo);
            srrPage = (SampleReportRelationPage) JSONObject.toBean(pJsonObject,
                    SampleReportRelationPage.class, classMap);

            testReportInfoService.updateSampleReportByReportId(srrPage,
                    userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    /***
     * 修改样品数据
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("updateSampleReportInfo.action")
    @ResponseBody
    public String updateSampleReportInfo(String strTReportInfo, String userId) {
        try {
            if (CommonMethod.isNull(strTReportInfo)) {
                throw new BusinessException("fail,参数strTReportInfo不能为空！", "");
            }

            Map<String, Class> classMap = new HashMap<String, Class>();
            classMap.put("testReportInfoPageList", TestReportInfoPage.class);// 报告明细
            classMap.put("sampleReportRelationList", SampleReportPage.class);// 样品报告关系明细

            SampleReportRelationPage srrPage = new SampleReportRelationPage();
            JSONObject pJsonObject = JSONObject.fromObject(strTReportInfo);
            srrPage = (SampleReportRelationPage) JSONObject.toBean(pJsonObject,
                    SampleReportRelationPage.class, classMap);

            testReportInfoService.updateSampleReportInfo(srrPage, userId);

        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("saveTReportInfo.action")
    @ResponseBody
    public String saveTReportInfo(@RequestParam MultipartFile[] file, String strTReportInfo, String userId) {
        try {
            if (CommonMethod.isNull(strTReportInfo)) {
                throw new BusinessException("fail,参数strTReportInfo不能为空！", "");
            }
            strTReportInfo = strTReportInfo.replace("OO", "#");
            strTReportInfo = strTReportInfo.replace("PLUS", "+");
            TestReportInfoPage triPage = (TestReportInfoPage) toBean(
                    strTReportInfo, TestReportInfoPage.class);

            if (CommonMethod.isNull(triPage.getReportId())) {
                throw new BusinessException("fail,报告ID不能为空");
            }
            if (CommonMethod.isNull(triPage.getEntrustDetailId())) {
                throw new BusinessException("fail,委托报告明细ID不能为空");
            }
            if ("03".equals(triPage.getReportStatus())
                    || "06".equals(triPage.getReportStatus())
                    || "07".equals(triPage.getReportStatus())) {// 报告未批准或者审核不通过或者发放报告时，不用操作报告文件
                testReportInfoService.saveTReportInfo(triPage, userId);
				/*jsonPrint("true");
				return;*/
            }
            // 报告数据
            TestReportInfo tri = testReportInfoService.findById(triPage
                    .getReportId());
            // 报告样品关系
            List<SampleReport> srList = sampleReportService.findByProperty(
                    "reportId", tri.getReportId());
            String entrustDetailId = srList.get(0).getEntrustDetailId();
            // 上委托明细
            EntrustDetails ed = entrustDetailService.findById(entrustDetailId);
            // 样品数据
            BaseSample bs = baseSampleService.findById(ed.getSampleId());

            for (MultipartFile sample : file) {
                File reportFile = (File) sample;

                String[] names = sample.getName().split("\\.");

                boolean gs = false;
                String hz = "";
                if (names.length > 1) {
                    hz = names[names.length - 1];
                    if (!"xls".equalsIgnoreCase(hz)
                            && !"xlsx".equalsIgnoreCase(hz)) {
                        gs = true;
                    }
                } else {
                    gs = true;
                }
                if (gs) {// 文件必须为xls、xlsx格式
                    throw new BusinessException("报告格式不对，不能上传！", "");
                }
                FileInputStream fis = null;
                FileOutputStream out = null;
                try {
                    fis = new FileInputStream(reportFile);
                    String realAdd = "report" + "/" + bs.getDepartmentId()
                            + "/" + ed.getSampleId() + "/"
                            + CommonMethod.getCurrentDate();
                    String savePath = request.getSession().getServletContext().getRealPath("/") + realAdd;
                    File newFile = new File(savePath.toString());
                    if ((!newFile.exists()) && (!newFile.isDirectory())) {
                        newFile.mkdirs();
                    }
                    if (!CommonMethod.isNull(tri.getReportPath())) {
                        boolean isSuccess = true;
                        File oldFile = new File(tri.getReportPath());
                        if (oldFile.exists()) {
                            isSuccess = oldFile.delete();
                        }
                        if (!isSuccess) {
                            throw new BusinessException("文件上传失败！", "");
                        }
                    }
                    String newFileName = CommonMethod.getNewKey();
                    out = new FileOutputStream(savePath.toString() + "\\"
                            + newFileName + "." + hz);
                    byte buffer[] = new byte[new Long(reportFile.length())
                            .intValue()];
                    int len = 0;

                    while ((len = fis.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    fis.close();
                    // 关闭输出流
                    out.close();
                    triPage.setReportPath(realAdd + "\\" + newFileName + "."
                            + hz);
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

            testReportInfoService.saveTReportInfo(triPage, userId);

        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("updateTReportInfo.action")
    @ResponseBody
    public String updateTReportInfo(@RequestParam MultipartFile[] template, String strTReportInfo, String userId) {
        try {
            if (CommonMethod.isNull(strTReportInfo)) {
                throw new BusinessException("fail,参数strTReportInfo不能为空！", "");
            }
            TestReportInfoPage triPage = (TestReportInfoPage) toBean(
                    strTReportInfo, TestReportInfoPage.class);

            if (CommonMethod.isNull(triPage.getReportId())) {
                throw new BusinessException("fail,报告ID不能为空");
            }
            if (CommonMethod.isNull(triPage.getEntrustDetailId())) {
                throw new BusinessException("fail,委托报告明细ID不能为空");
            }
            // 报告数据
            TestReportInfo tri = testReportInfoService.findById(triPage
                    .getReportId());
            // 报告样品关系
            List<SampleReport> srList = sampleReportService.findByProperty(
                    "reportId", tri.getReportId());
            String entrustDetailId = srList.get(0).getEntrustDetailId();
            // 上委托明细
            EntrustDetails ed = entrustDetailService.findById(entrustDetailId);
            // 样品数据
            BaseSample bs = baseSampleService.findById(ed.getSampleId());

            for (MultipartFile sample : template) {
                File reportFile = (File) sample;
                String[] names = sample.getName().split("\\.");

                boolean gs = false;
                String hz = "";
                if (names.length > 1) {
                    hz = names[names.length - 1];
                    if (!"xls".equalsIgnoreCase(hz)
                            && !"xlsx".equalsIgnoreCase(hz)) {
                        gs = true;
                    }
                } else {
                    gs = true;
                }
                if (gs) {// 文件必须为xls、xlsx格式
                    throw new BusinessException("报告格式不对，不能上传！", "");
                }
                FileInputStream fis = null;
                FileOutputStream out = null;
                try {
                    fis = new FileInputStream(reportFile);

                    String realAdd = "report" + "/" + bs.getDepartmentId()
                            + "/" + ed.getSampleId() + "/"
                            + CommonMethod.getCurrentDate();
                    String savePath = request.getSession().getServletContext().getRealPath("/") + realAdd;
                    File newFile = new File(savePath.toString());
                    if ((!newFile.exists()) && (!newFile.isDirectory())) {
                        newFile.mkdirs();
                    }
                    if (!CommonMethod.isNull(tri.getReportPath())) {
                        boolean isSuccess = true;
                        File oldFile = new File(tri.getReportPath());
                        if (oldFile.exists()) {
                            isSuccess = oldFile.delete();
                        }
                        if (!isSuccess) {
                            throw new BusinessException("文件上传失败！", "");
                        }
                    }
                    String newFileName = CommonMethod.getNewKey();
                    out = new FileOutputStream(savePath.toString() + "\\"
                            + newFileName + "." + hz);
                    // out = new FileOutputStream(savePath.toString() + "\\" +
                    // "test123" + "." +hz);
                    byte buffer[] = new byte[new Long(reportFile.length())
                            .intValue()];
                    int len = 0;

                    while ((len = fis.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    fis.close();
                    // 关闭输出流
                    out.close();
                    triPage.setReportPath(realAdd + "\\" + newFileName + "."
                            + hz);
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
            tri.setReportPath(triPage.getReportPath());
            tri.setUpdater(userId);
            tri.setUpdateTime(CommonMethod.getDate());
            testReportInfoService.update(tri);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    /**
     * 批量批准或者审核或者发放
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("saveAllReportApAndAu.action")
    @ResponseBody
    public String saveAllReportApAndAu(String strTReportInfo, String userId) {
        try {
            if (CommonMethod.isNull(strTReportInfo)) {
                throw new BusinessException("fail,参数strTReportInfo不能为空！", "");
            }
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(
                    new TimestampMorpher(formats));
            Collection<TestReportInfoPage> collPage = JSONArray.toCollection(
                    JSONArray.fromObject(strTReportInfo),
                    TestReportInfoPage.class);

            testReportInfoService.saveAllReportApAndAu(collPage, userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    /**
     * 批量批准或者审核或者发放 新版使用 以前的使用出错 05-09
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("saveReportPrintInfo.action")
    @ResponseBody
    public String saveReportPrintInfo(String reportId, String printNum, String distributeTime, String reportStatus, String userId) {
        try {
            if (CommonMethod.isNull(reportId)) {
                throw new BusinessException("fail,参数reportId不能为空！", "");
            }
            if (CommonMethod.isNull(printNum)) {
                throw new BusinessException("fail,参数printNum不能为空！", "");
            }
            if (CommonMethod.isNull(distributeTime)) {
                throw new BusinessException("fail,参数distributeTime不能为空！", "");
            }
            if (CommonMethod.isNull(reportStatus)) {
                throw new BusinessException("fail,参数reportStatus不能为空！", "");
            }
            testReportInfoService.saveReportPrintInfo(reportId, printNum,
                    distributeTime, reportStatus, userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    /**
     * 修改报告打印次数
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("saveReportPrintNum.action")
    @ResponseBody
    public String saveReportPrintNum(String strTReportInfo, String userId) {
        try {
            if (CommonMethod.isNull(strTReportInfo)) {
                throw new BusinessException("fail,参数strTReportInfo不能为空！", "");
            }

            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(
                    new TimestampMorpher(formats));
            Collection<TestReportInfoPage> collPage = JSONArray.toCollection(
                    JSONArray.fromObject(strTReportInfo),
                    TestReportInfoPage.class);

            testReportInfoService.saveReportPrintNum(collPage, userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("findByTwoDInfoId.action")
    @ResponseBody
    public TwoDInfo findByTwoDInfoId(String strTwoDInfoId) {

        TwoDInfo tdInfo = twoDInfoService.findById(strTwoDInfoId);
        try {


            // 报告信息
            TestReportInfo tri = testReportInfoService.findById(tdInfo
                    .getReportId());
            // 报告样品关系
            List<SampleReport> srList = sampleReportService.findByProperty(
                    "reportId", tri.getReportId());
            if (srList.size() <= 0) {
                jsonPrint("当前报告已失效");
            }
            SampleReport sr = srList.get(0);

            String entrustDetailId = sr.getEntrustDetailId();
            // 委托明细信息
            EntrustDetails eDetails = entrustDetailService
                    .findById(entrustDetailId);
            // 委托信息
            EntrustInfo ei = entrustInfoService.findById(eDetails
                    .getEntrustId());
            // 工程信息
            // ProjectInfo pj = projectInfoService.findById(ei.getProjectId());
            // //委托单位信息
            // EntrustCompany eCompany =
            // entrustCompanyService.findById(ei.getEntrustCompanyId());
            /*
             * if(!CommonMethod.isNull(ei.getInspectionMan())){ //送检人
             * List<SysUser> suInspectionMan =
             * sysUserService.findByProperty("userName", ei.getInspectionMan());
             * if(suInspectionMan.size() > 0){
             * tdInfo.setInspectionMan(suInspectionMan.get(0).getUserName()); }
             * }
             */
            if (!CommonMethod.isNull(ei.getWitnessMan())) {
                // 见证人
                // SysUser suWitnessMan =
                // sysUserService.findById(ei.getWitnessMan());
                tdInfo.setWitnessMan(ei.getWitnessMan());
            }

            tdInfo.setTestAgencyName(tdInfo.getTestAgencyName());
            tdInfo.setTestCategories(sysCodeService.findCodeName(
                    "SAMPLE_SOURCES", eDetails.getSampleSource()));
            tdInfo.setProjectName(tri.getReportProjectName());
            if (ei.getProjectAddress() != null) {
                tdInfo.setProjectAddress(ei.getProjectAddress());
            }
            tdInfo.setProjectParts(sr.getProjectPart());
            tdInfo.setReportNo(tri.getReportNo());
            tdInfo.setReportDate(tri.getReportDate());
            tdInfo.setTestResult(tri.getTestResult());
            tdInfo.setReportConclusion(tri.getQcNumber());
            tdInfo.setStandards(tri.getReportTestData());
            tdInfo.setEntrustCompanyName(tri.getReportCompanyName());
            tdInfo.setTestName(tri.getReportName());
            tdInfo.setIsNew(ei.getEntrustType());
            String str = null;
            /*
             * if(ei.getWitnessMan() != null && !"".equals(ei.getWitnessMan())
             * && ei.getLinkMan() != null && !"".equals(ei.getLinkMan())){ str =
             * ei.getWitnessMan()+"、"+ei.getLinkMan(); }else
             * if(ei.getWitnessMan() != null && !"".equals(ei.getWitnessMan())){
             * str = ei.getWitnessMan(); }else if(ei.getLinkMan() != null &&
             * !"".equals(ei.getLinkMan())){ str = ei.getLinkMan(); }
             */
            str = ei.getWitnessMan();
            tdInfo.setWitnessMan(str);
            //jsonPrintOnlyProperty(tdInfo);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return tdInfo;
    }

    /**
     * 查询报告编号是否重复
     */
    @RequestMapping("findReportNumber.action")
    @ResponseBody
    public String findReportNumber(String reportNumber) {
        if (CommonMethod.isNull(reportNumber)) {
            throw new BusinessException("fail,参数reportNumber不能为空！", "");
        }
        List<TestReportInfo> number = testReportInfoService.findByProperty(
                "reportNo", reportNumber);
        List<String> result = new ArrayList<String>();
        if (number.size() > 0) {
            result.add("true");
        } else {
            result.add("false");
        }
        return result.get(0).toString();
    }

    /**
     * 报告列表样品报告关系表数据
     */
    @RequestMapping("sampleReportList.action")
    @ResponseBody
    public List<TestReportInfoPage> sampleReportList(String strTReportInfo) {
        TestReportInfoPage tesPage = (TestReportInfoPage) toBean(
                strTReportInfo, TestReportInfoPage.class);

        List<TestReportInfoPage> sampleReportList = testReportInfoService
                .sampleReportList(tesPage);
        return sampleReportList;
    }

    /**
     * 快速发报告
     */
    @RequestMapping("fastReleaseReport.action")
    @ResponseBody
    public void fastReleaseReport(@RequestParam MultipartFile[] template, String strTReportInfo) {

        TestReportInfoPage triPage = (TestReportInfoPage) toBean(
                strTReportInfo, TestReportInfoPage.class);

        // 报告数据
        TestReportInfo tri = testReportInfoService.findById(triPage
                .getReportId());
        // 报告样品关系
        List<SampleReport> srList = sampleReportService.findByProperty(
                "reportId", tri.getReportId());
        String entrustDetailId = srList.get(0).getEntrustDetailId();
        // 上委托明细
        EntrustDetails ed = entrustDetailService.findById(entrustDetailId);
        // 样品数据
        BaseSample bs = baseSampleService.findById(ed.getSampleId());


        for (MultipartFile sample : template) {

            File reportFile = (File) sample;
            String[] names = sample.getName().split("\\.");
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
            if (gs) {// 文件必须为xls、xlsx格式
                throw new BusinessException("报告格式不对，不能上传！", "");
            }
            FileInputStream fis = null;
            FileOutputStream out = null;
            try {
                fis = new FileInputStream(reportFile);

                String realAdd = "report" + "/" + bs.getDepartmentId() + "/"
                        + ed.getSampleId() + "/"
                        + CommonMethod.getCurrentDate();
                String savePath = request.getSession().getServletContext().getRealPath("/") + realAdd;
                File newFile = new File(savePath.toString());
                if ((!newFile.exists()) && (!newFile.isDirectory())) {
                    newFile.mkdirs();
                }
                if (!CommonMethod.isNull(tri.getReportPath())) {
                    boolean isSuccess = true;
                    File oldFile = new File(tri.getReportPath());
                    if (oldFile.exists()) {
                        isSuccess = oldFile.delete();
                    }
                    if (!isSuccess) {
                        throw new BusinessException("文件上传失败！", "");
                    }
                }
                String newFileName = CommonMethod.getNewKey();
                out = new FileOutputStream(savePath.toString() + "\\"
                        + newFileName + "." + hz);
                // out = new FileOutputStream(savePath.toString() + "\\" +
                // "test123" + "." +hz);
                byte buffer[] = new byte[new Long(reportFile.length())
                        .intValue()];
                int len = 0;

                while ((len = fis.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                fis.close();
                // 关闭输出流
                out.close();
                triPage.setReportPath(realAdd + "\\" + newFileName + "." + hz);
            } catch (Exception e) {
                if (null != fis) {
                    try {
                        fis.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if (null != out) {
                    try {
                        out.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                e.printStackTrace();
            } finally {
                if (null != fis) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (null != out) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        testReportInfoService.update(tri);
    }

    /**
     * 修改报告
     */
    @RequestMapping("updateReport.action")
    @ResponseBody
    public void updateReport(@RequestParam MultipartFile[] template, String userId, String strTReportInfo) {
        TestReportInfoPage triPage = (TestReportInfoPage) toBean(
                strTReportInfo, TestReportInfoPage.class);
        // 报告数据
        TestReportInfo tri1 = testReportInfoService.findById(triPage
                .getReportId());
        tri1.setReportStatus("08");
        testReportInfoService.update(tri1);

        // 报告数据
        TestReportInfo tri = testReportInfoService.findById(triPage
                .getReportId());
        // 报告样品关系
        List<SampleReport> srList = sampleReportService.findByProperty(
                "reportId", tri.getReportId());
        String entrustDetailId = srList.get(0).getEntrustDetailId();
        // 上委托明细
        EntrustDetails ed = entrustDetailService.findById(entrustDetailId);
        // 样品数据
        BaseSample bs = baseSampleService.findById(ed.getSampleId());


        for (MultipartFile sample : template) {
            File reportFile = (File) sample;
            String[] names = sample.getName().split("\\.");

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
            if (gs) {// 文件必须为xls、xlsx格式
                throw new BusinessException("报告格式不对，不能上传！", "");
            }
            FileInputStream fis = null;
            FileOutputStream out = null;
            try {
                fis = new FileInputStream(reportFile);

                String realAdd = "report" + "/" + bs.getDepartmentId() + "/"
                        + ed.getSampleId() + "/"
                        + CommonMethod.getCurrentDate();
                String savePath = request.getSession().getServletContext().getRealPath("/") + realAdd;
                File newFile = new File(savePath.toString());
                if ((!newFile.exists()) && (!newFile.isDirectory())) {
                    newFile.mkdirs();
                }

                if (!CommonMethod.isNull(tri.getReportPath())) {
                    boolean
                            isSuccess = true;
                    File oldFile = new
                            File(tri.getReportPath());
                    if (oldFile.exists()) {
                        isSuccess =
                                oldFile.delete();
                    }
                    if (!isSuccess) {
                        throw new
                                BusinessException("文件上传失败！", "");
                    }
                }
                //*
                String newFileName = CommonMethod.getNewKey();
                out = new FileOutputStream(savePath.toString() + "\\"
                        + newFileName + "." + hz);
                // out = new FileOutputStream(savePath.toString() + "\\" +
                // "test123" + "." +hz);
                byte buffer[] = new byte[new Long(reportFile.length())
                        .intValue()];
                int len = 0;

                while ((len = fis.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                fis.close();
                // 关闭输出流
                out.close();
                triPage.setReportPath(realAdd + "\\" + newFileName + "." + hz);

            } catch (Exception e) {
                if (null != fis) {
                    try {
                        fis.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if (null != out) {
                    try {
                        out.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                e.printStackTrace();
            } finally {
                if (null != fis) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (null != out) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            tri.setReportId(UuidUtil.getUUID());
            tri.setReportPath(triPage.getReportPath());
            tri.setUpdater(userId);
            tri.setUpdateTime(CommonMethod.getDate());
            tri.setReportStatus(triPage.getReportStatus());
            tri.setSourcesReportId(triPage.getReportId());
            testReportInfoService.save(tri);
            // 更改报告编号
            TestReportInfo tri2 = testReportInfoService.findById(tri
                    .getReportId());
            List<TestReportInfo> info = testReportInfoService.findByProperty(
                    "sourcesReportId", triPage.getReportId());
            for (Integer i1 = 1; i1 <= info.size() + 1; i1++) {
                tri.setReportNo(triPage.getReportNo() + "-0" + i1);
            }
            List<TestReportInfo> report = testReportInfoService.findByProperty(
                    "sourcesReportId", triPage.getReportId());
            testReportInfoService.update(tri2);
        }
    }

    /**
     * 修改报告
     */
    @RequestMapping("twoInfo.action")
    @ResponseBody
    public List<TestReportInfoPage> twoInfo(String strTReportInfo) {

        TestReportInfoPage triPage = (TestReportInfoPage) toBean(
                strTReportInfo, TestReportInfoPage.class);

        List<TestReportInfoPage> saveTwoReportList = testReportInfoService.saveTwoReportList(triPage);

        return saveTwoReportList;
    }


    /*public String getStrEDetailId() {
        return strEDetailId;
    }

    public void setStrEDetailId(String strEDetailId) {
        this.strEDetailId = strEDetailId;
    }

    public String getStrTReportInfo() {
        return strTReportInfo;
    }

    public void setStrTReportInfo(String strTReportInfo) {
        this.strTReportInfo = strTReportInfo;
    }

    public String getStrTwoDInfoId() {
        return strTwoDInfoId;
    }

    public void setStrTwoDInfoId(String strTwoDInfoId) {
        this.strTwoDInfoId = strTwoDInfoId;
    }

    public String getStrReportId() {
        return strReportId;
    }

    public void setStrReportId(String strReportId) {
        this.strReportId = strReportId;
    }

    public String getStrStatus() {
        return strStatus;
    }

    public void setStrStatus(String strStatus) {
        this.strStatus = strStatus;
    }

    public String getStrTemplateInfoId() {
        return strTemplateInfoId;
    }

    public void setStrTemplateInfoId(String strTemplateInfoId) {
        this.strTemplateInfoId = strTemplateInfoId;
    }

    public String getBackStatus() {
        return backStatus;
    }

    public void setBackStatus(String backStatus) {
        this.backStatus = backStatus;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getPrintNum() {
        return printNum;
    }

    public void setPrintNum(String printNum) {
        this.printNum = printNum;
    }

    public String getDistributeTime() {
        return distributeTime;
    }

    public void setDistributeTime(String distributeTime) {
        this.distributeTime = distributeTime;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }*/

}
