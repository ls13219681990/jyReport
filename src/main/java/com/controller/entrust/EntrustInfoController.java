package com.controller.entrust;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.common.jsonProcessor.TimestampMorpher;
import com.dao.page.*;
import com.model.CapitalAccountDetail;
import com.model.EntrustInfo;
import com.model.SampleReport;
import com.model.TestParameter;
import com.service.common.CapitalAccountDetailService;
import com.service.common.TestParameterService;
import com.service.entrust.EntrustDetailService;
import com.service.entrust.EntrustInfoService;
import com.service.entrust.SampleReportService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("entrustInfoAction")
public class EntrustInfoController extends QueryAction<EntrustInfo> {

    /**
     *
     */
    private final static HttpServletRequest REQUEST = null;

    private static final long serialVersionUID = 1L;
    // 委托单位ID
    private String strECompanyId = "";
    // 工程ID
    private String strProjectId = "";
    // 监理单位ID
    private String strSUnitId = "";
    // 委托资金明细
    private String strEntrustMDetail = "";
    // 委托录入参数
    private String strEntrustInfo = "";
    // 委托ID
    private String strEntrustId = "";
    // 委托ID
    private String entrustDetailId = "";
    // 委托状态
    private String strEntrustStatus = "";
    // 工程应收
    private String strProjectMoney = "";
    // 部门ID
    private String strDepartmentId = "";
    // 样品编号
    private String strSampleNo = "";
    // 产值统计年份
    private String strOutputValueYear = "";

    // 委托资金明细
    private String strEntrustDetail = "";

    private Integer pageNo;// 当前页数
    private Integer pageSize;// 一页条数
    private String departmentId;
    private String entrustSn; // 委托编号
    private String entrustCompanyName; // 委托单位
    private String projectName; // 工程名称
    private String startDate; // 录入时间
    private String endDate; // 结束时间
    private String inputTime; // 录入时间
    private String entrustDate; // 委托时间
    private String projectPart; // 工程部位
    private String sampleAge;// 龄期
    private String reportStatus;// 报告状态
    private String reportNo;// 报告编号
    private String inputerName;// 录入人姓名
    private String auditorName;// 审核人姓名
    private String apprvoerName;// 批准人姓名
    private String printUserName;// 打印人姓名
    private String capitalAccountNo;// 资金账号
    private String entrustStatus;// 委托状态
    private String accountKinds;// 收费类别
    private String testDepartment;// 检测科室
    private String managmentDepartment;// 经营部门
    private String acceptanceMan;// 受理人
    private String sampleName;//样品名称


    private String contractNo;//合同编号
    private String accountType;//收费类型


    @Autowired
    private SampleReportService sampleReportService;

    @Autowired
    private EntrustDetailService entrustDetailService;
    @Autowired
    private EntrustInfoService entrustInfoService;
    @Autowired
    private TestParameterService testParameterService;
    @Autowired
    private CapitalAccountDetailService capitalAccountDetailService;

    //@Action(value = "/entrustInfoAction", results = { @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })
    @RequestMapping(value = "/aaaaaqbaseDepartmentAction")
    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    @RequestMapping("findIsEntrust.action")
    public void findIsEntrust() {
        List<EntrustInfo> eiList = entrustInfoService.findIsEntrust(
                strECompanyId, strProjectId, strSUnitId);
        if (eiList != null && eiList.size() > 0) {// 已经办理了委托
            jsonPrint("false");
        } else {
            jsonPrint("true");
        }
    }

    /**
     * 委托时，获取检测明细的价格
     */
    @RequestMapping("findEntrustMDetail.action")
    public void findEntrustMDetail() {
        if (CommonMethod.isNull(strEntrustMDetail)) {
            jsonPrint("fail,参数strEntrustMDetail不能为空");
            return;
        }
        List<EntrustParameterPage> eppList = new ArrayList<EntrustParameterPage>();

        EntrustParameterPage epPage = (EntrustParameterPage) toBean(
                strEntrustMDetail, EntrustParameterPage.class);

        TestParameter tp = testParameterService.findById(epPage
                .getTestParameterId());
        // 合同号为空，则取检测参数表里的单价，否则取合同账号明细里的单价
        if (CommonMethod.isNull(epPage.getContractId())) {
            epPage.setUnitPrice(tp.getUnitPrice() == null ? "0" : tp
                    .getUnitPrice().toString());
            epPage.setSource("1");
        } else {
            // 在该合同下，用样品和参数去取对应的合同价格
            List<CapitalAccountDetail> caDetailList = capitalAccountDetailService
                    .findCADetails(epPage.getContractId(),
                            epPage.getSampleId(), epPage.getTestParameterId());
            if (caDetailList != null && caDetailList.size() > 0) {// 有样品和参数对应的价格
                CapitalAccountDetail cad = caDetailList.get(0);
                // 取样品和参数对应的价格为实收价
                epPage.setUnitPrice(cad.getRealPrice() == null ? cad.getPrice()
                        .toString() : cad.getRealPrice().toString());
                epPage.setSource("0");
            } else {// 在该合同下，没有样品和参数对应的价格
                // 在该合同下，用样品去取对应的合同价格
                List<CapitalAccountDetail> caSampleDetailList = capitalAccountDetailService
                        .findCASampleDetails(epPage.getContractId(),
                                epPage.getSampleId());
                if (caSampleDetailList != null && caSampleDetailList.size() > 0) {// 有样品对应的价格
                    CapitalAccountDetail cad = caSampleDetailList.get(0);
                    // 取样品和参数对应的价格为实收价
                    epPage.setUnitPrice(cad.getRealPrice() == null ? cad
                            .getPrice().toString() : cad.getRealPrice()
                            .toString());
                    epPage.setSource("0");
                } else {// 取检测参数表里的单价为实收价
                    epPage.setUnitPrice(tp.getUnitPrice() == null ? "0" : tp
                            .getUnitPrice().toString());
                    epPage.setSource("1");
                }
            }
        }
        eppList.add(epPage);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(eppList, jsonConfig);
        jsonPrint(jsonArr);
    }

    @SuppressWarnings({"rawtypes", "unused"})
    @RequestMapping("saveEntrustInfo.action")
    public void saveEntrustInfo() {
        try {
            //HttpServletRequest request = ServletActionContext.getRequest();
            Map parMap = REQUEST.getParameterMap();
            Object uerObj = parMap.get("userId");
            Object eDetailsObj = parMap.get("strEntrustInfo");

            if (CommonMethod.isNull(strEntrustInfo)) {
                jsonPrint("fail,参数strEntrustInfo不能为空");
                return;
            }
            strEntrustInfo = strEntrustInfo.replace("OO", "#");

            Map<String, Class> classMap = new HashMap<String, Class>();
            classMap.put("edReport", EntrustDetailReportPage.class);// 上委托明细
            classMap.put("enPaInfoPageList", EntrustDetailSavePage.class);// 委托金额明细

            EntrustSavePage esPage = new EntrustSavePage();
            JSONObject pJsonObject = JSONObject.fromObject(strEntrustInfo);
            esPage = (EntrustSavePage) JSONObject.toBean(pJsonObject,
                    EntrustSavePage.class, classMap);
            if (CommonMethod.isNull(esPage.getEntrustType())) {
                jsonPrint("fail,参数委托类型不能为空");
                return;
            } else {
                if ("00".equals(esPage.getEntrustType())
                        || "01".equals(esPage.getEntrustType())) {
                } else {
                    jsonPrint("fail,参数委托类型的值只能为00或者01");
                    return;
                }
            }
            // 委托单位不能为空
            if (CommonMethod.isNull(esPage.getEntrustCompanyId())) {
                jsonPrint("fail,参数单位不能为空！");
                return;
            }
            // 工程不能为空
            if (CommonMethod.isNull(esPage.getProjectId())) {
                jsonPrint("fail,工程不能为空！");
                return;
            }
            String entrustId = entrustInfoService.saveEntrustInfo(esPage,
                    getUserId());
            jsonPrint("true:" + entrustId);
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @RequestMapping("updateEntrustInfoBySn.action")
    public void updateEntrustInfoBySn() {
        try {
            if (CommonMethod.isNull(strEntrustInfo)) {
                jsonPrint("fail,参数strEntrustInfo不能为空");
                return;
            }
            strEntrustInfo = strEntrustInfo.replace("OO", "#");

            EntrustSavePage esPage = (EntrustSavePage) toBean(strEntrustInfo,
                    EntrustSavePage.class);
            // 委托单位不能为空
            if (CommonMethod.isNull(esPage.getInvalid())) {
                jsonPrint("fail,作废失败,不能为空！");
                return;
            }
            entrustInfoService.updateEntrustInfoBySn(esPage, getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @RequestMapping("updateEntrustInfo.action")
    public void updateEntrustInfo() {
        try {
            if (CommonMethod.isNull(strEntrustInfo)) {
                jsonPrint("fail,参数strEntrustInfo不能为空");
                return;
            }
            strEntrustInfo = strEntrustInfo.replace("OO", "#");

            EntrustSavePage esPage = (EntrustSavePage) toBean(strEntrustInfo,
                    EntrustSavePage.class);
            // 委托单位不能为空
            if (CommonMethod.isNull(esPage.getEntrustCompanyId())) {
                jsonPrint("fail,参数单位不能为空！");
                return;
            }
            // 工程不能为空
            if (CommonMethod.isNull(esPage.getProjectId())) {
                jsonPrint("fail,工程不能为空！");
                return;
            }
            entrustInfoService.updateEntrustInfo(esPage, getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @SuppressWarnings({"rawtypes", "unused"})
    @RequestMapping("updateEntrustMDetail.action")
    public void updateEntrustMDetail() {
        try {
            //HttpServletRequest request = ServletActionContext.getRequest();
            Map parMap = REQUEST.getParameterMap();
            Object uerObj = parMap.get("userId");
            Object eDetailsObj = parMap.get("strEntrustInfo");

            if (CommonMethod.isNull(strEntrustInfo)) {
                jsonPrint("fail,参数strEntrustInfo不能为空");
                return;
            }

            Map<String, Class> classMap = new HashMap<String, Class>();
            classMap.put("edReport", EntrustDetailReportPage.class);
            classMap.put("enPaInfoPageList", EntrustDetailSavePage.class);// 委托金额明细

            EntrustSavePage esPage = new EntrustSavePage();
            JSONObject pJsonObject = JSONObject.fromObject(strEntrustInfo);
            esPage = (EntrustSavePage) JSONObject.toBean(pJsonObject,
                    EntrustSavePage.class, classMap);

            entrustInfoService.updateEntrustMDetail(esPage, getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @SuppressWarnings({"rawtypes", "unused"})
    @RequestMapping("saveEntrustMDetail.action")
    public void saveEntrustMDetail() {
        try {
            //HttpServletRequest request = ServletActionContext.getRequest();
            Map parMap = REQUEST.getParameterMap();
            Object uerObj = parMap.get("userId");
            Object eDetailsObj = parMap.get("strEntrustInfo");

            if (CommonMethod.isNull(strEntrustInfo)) {
                jsonPrint("fail,参数strEntrustInfo不能为空");
                return;
            }

            Map<String, Class> classMap = new HashMap<String, Class>();
            classMap.put("edReport", EntrustDetailReportPage.class);
            classMap.put("enPaInfoPageList", EntrustDetailSavePage.class);// 委托金额明细

            EntrustSavePage esPage = new EntrustSavePage();
            JSONObject pJsonObject = JSONObject.fromObject(strEntrustInfo);
            esPage = (EntrustSavePage) JSONObject.toBean(pJsonObject,
                    EntrustSavePage.class, classMap);

            entrustInfoService.saveEntrustMDetail(esPage, getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    /**
     * 根据委托条件，查询委托信息
     */
    @RequestMapping("findEntrustInfoByCondition.action")
    public void findEntrustInfoByCondition() {
        EntrustSavePage esPage = new EntrustSavePage();
        if (!CommonMethod.isNull(strEntrustInfo)) {
            strEntrustInfo = strEntrustInfo.replace("OO", "#");
            esPage = (EntrustSavePage) toBean(strEntrustInfo,
                    EntrustSavePage.class);
        }
        List<EntrustSavePage> esPageList = entrustInfoService
                .findEntrustInfoByCondition(esPage);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(esPageList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 根据委托条件，查询委托信息
     */
    @RequestMapping("findEntrustInfoByConditionByAll.action")
    public void findEntrustInfoByConditionByAll() {
        EntrustSavePage esPage = new EntrustSavePage();
        if (!CommonMethod.isNull(strEntrustInfo)) {
            strEntrustInfo = strEntrustInfo.replace("OO", "#");
            esPage = (EntrustSavePage) toBean(strEntrustInfo,
                    EntrustSavePage.class);
        }
        // 根据工程ID,名称,委托编号查询工程下所有委托,根据委托查询所有委托明细
        List<EntrustSavePage> esPageList = entrustInfoService
                .findEntrustInfoByConditionByAll(esPage);

        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(esPageList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 根据委托ID，查询委托金额明细信息
     */
    @RequestMapping("findEntrustDetailInfoById.action")
    public void findEntrustDetailInfoById() {
        if (CommonMethod.isNull(strEntrustId)) {
            jsonPrint("fail,委托ID不能为空");
            return;
        }
        List<EntrustSavePage> esPageList = entrustInfoService
                .findEntrustDetailInfoById(strEntrustId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(esPageList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 根据委托明细ID，查询委托信息
     */
    @RequestMapping("showERDetailInfoById.action")
    public void showERDetailInfoById() {
        if (CommonMethod.isNull(entrustDetailId)) {
            jsonPrint("fail,参数entrustDetailId不能为空");
            return;
        }
        try {
            EntrustInfo esPageList = entrustInfoService
                    .findEntrust(entrustDetailId);
            CommonJsonConfig jsonConfig = new CommonJsonConfig();
            JSONArray jsonArr = JSONArray.fromObject(esPageList, jsonConfig);
            jsonPrint(jsonArr);
        } catch (Exception e) {
            jsonPrint("fail:" + e.getMessage());
        }
    }

    /**
     * 根据委托ID，查询委托明细信息(报告：委托室用)
     */
    @RequestMapping("findERDetailInfoById.action")
    public void findERDetailInfoById() {
        if (CommonMethod.isNull(strEntrustInfo)) {
            jsonPrint("fail,参数strEntrustInfo不能为空");
            return;
        }
        EntrustSavePage esPage = (EntrustSavePage) toBean(strEntrustInfo,
                EntrustSavePage.class);

        List<EntrustSavePage> esPageList = entrustInfoService
                .findERDetailInfoById(esPage);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(esPageList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 根据委托信息，查询委托明细信息(报告：检测科室用)
     */
    @RequestMapping("findERDetailInfo.action")
    public void findERDetailInfo() {
        if (CommonMethod.isNull(strEntrustInfo)) {
            jsonPrint("fail,参数strEntrustInfo不能为空");
            return;
        }
        EntrustReportPage erPage = (EntrustReportPage) toBean(strEntrustInfo,
                EntrustReportPage.class);

        List<EntrustReportPage> erPageList = entrustInfoService
                .findERDetailInfo(erPage);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(erPageList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 查询委托传送单信息
     */
    @RequestMapping("findEntrustEfSheet.action")
    public void findEntrustEfSheet() {
        if (CommonMethod.isNull(strEntrustId)) {
            jsonPrint("fail,参数strEntrustId不能为空");
            return;
        }
        List<EntrustTfSheetPage> eTfSheetList = entrustInfoService
                .findEntrustEfSheet(strEntrustId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(eTfSheetList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 查询检测传送单信息
     */
    @RequestMapping("findTestEfSheet.action")
    public void findTestEfSheet() {
        if (CommonMethod.isNull(strEntrustId)) {
            jsonPrint("fail,参数strEntrustId不能为空");
            return;
        }
        List<TestTfSheetPage> eTfSheetList = entrustInfoService
                .findTestEfSheet(strEntrustId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(eTfSheetList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 根据委托记录ID修改委托记录状态
     */
    @RequestMapping("updateEntrustStatus.action")
    public void updateEntrustStatus() {
        try {
            if (CommonMethod.isNull(strEntrustId)) {
                jsonPrint("fail,参数strEntrustId不能为空");
                return;
            }

            entrustInfoService.updateEntrustStatus(strEntrustId,
                    strEntrustStatus, getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    /**
     * 查询工程应收预览信息
     *
     * @return
     */
    @RequestMapping("findProjectMoney.action")
    public void findProjectMoney() {
        ProjectMoneyPage pMoneyPage = new ProjectMoneyPage();
        if (!CommonMethod.isNull(strProjectMoney)) {
            pMoneyPage = (ProjectMoneyPage) toBean(strProjectMoney,
                    ProjectMoneyPage.class);
        }

        List<ProjectMoneyPage> pMoneyPageList = entrustInfoService
                .findProjectMoney(pMoneyPage);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(pMoneyPageList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 查询工程检测费用统计信息
     *
     * @return
     */
    @RequestMapping("findProjectTestMoney.action")
    public void findProjectTestMoney() {
        if (CommonMethod.isNull(strProjectId)) {
            jsonPrint("fail,参数strProjectId不能为空");
            return;
        }

        List<ProjectTestMoneyPage> ptmPageList = entrustInfoService
                .findProjectTestMoney(strProjectId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(ptmPageList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * (报告：各科室批量审核批准用)
     */
    @SuppressWarnings({"unchecked", "unused", "rawtypes"})
    @RequestMapping("saveAllReportStatus.action")
    public void saveAllReportStatus() {
        try {
            //HttpServletRequest request = ServletActionContext.getRequest();
            Map parMap = REQUEST.getParameterMap();
            Object uerObj = parMap.get("userId");
            Object eDetailsObj = parMap.get("strEntrustInfo");

            if (CommonMethod.isNull(strEntrustInfo)) {
                jsonPrint("fail,参数strEntrustInfo不能为空");
                return;
            }
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(
                    new TimestampMorpher(formats));
            Collection<EntrustReportPage> collPage = JSONArray.toCollection(
                    JSONArray.fromObject(strEntrustInfo),
                    EntrustReportPage.class);

            entrustInfoService.saveAllReportStatus(collPage, getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    /**
     * (报告：判断各科室批量审核批准是否有未通过的)
     */
    @SuppressWarnings({"unchecked", "unused", "rawtypes"})
    @RequestMapping("ifAllReportStatus.action")
    public void ifAllReportStatus() {
        try {
            if (CommonMethod.isNull(strEntrustInfo)) {
                jsonPrint("fail,参数strEntrustInfo不能为空");
                return;
            }
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(
                    new TimestampMorpher(formats));
            Collection<EntrustReportPage> collPage = JSONArray.toCollection(
                    JSONArray.fromObject(strEntrustInfo),
                    EntrustReportPage.class);

            List<String> map = entrustInfoService.ifAllReportStatus(collPage);

            CommonJsonConfig jsonConfig = new CommonJsonConfig();
            JSONArray jsonArr = JSONArray.fromObject(map, jsonConfig);
            jsonPrint(jsonArr);
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    /**
     * 根据委托ID，查询委托明细信息(报告：各科室审核批准用)
     */
    @RequestMapping("findDepartmentDetailInfo.action")
    public void findDepartmentDetailInfo() {
        if (CommonMethod.isNull(strEntrustInfo)) {
            jsonPrint("fail,参数strEntrustInfo不能为空");
            return;
        }
        strEntrustInfo = strEntrustInfo.replace("OO", "#");
        strEntrustInfo = strEntrustInfo.replace("PLUS", "+");
        EntrustReportPage erPage = (EntrustReportPage) toBean(strEntrustInfo,
                EntrustReportPage.class);

        List<EntrustReportPage> erPageList = entrustInfoService
                .findDepartmentDetailInfo(erPage, getUserId());

        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(erPageList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 根据委托ID，查询委托明细信息(报告：各科室录入时用)
     */
    @RequestMapping("findDepartmentDetailInputInfo.action")
    public void findDepartmentDetailInputInfo() {
        if (CommonMethod.isNull(strEntrustInfo)) {
            jsonPrint("fail,参数strEntrustInfo不能为空");
            return;
        }
        EntrustReportPage erPage = (EntrustReportPage) toBean(strEntrustInfo,
                EntrustReportPage.class);

        List<EntrustReportPage> erPageList = entrustInfoService
                .findDepartmentDetailInputInfo(erPage, getUserId());

        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(erPageList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 查询各科室产值统计信息
     *
     * @return
     */
    @RequestMapping("findOutputValueCount.action")
    public void findOutputValueCount() {
        if (CommonMethod.isNull(strOutputValueYear)) {
            jsonPrint("fail,参数strOutputValueYear不能为空");
            return;
        }

        List<OutputValueCountPage> ovcPageList = entrustInfoService
                .findOutputValueCount(strOutputValueYear);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(ovcPageList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 根据部门返回报告列表
     */
    @RequestMapping("findDepartmentReportInfo.action")
    public void findDepartmentReportInfo() {
        List<TestReportInfoPage> findDepartmentReportInfo = entrustInfoService
                .findDepartmentReportInfo(pageNo, pageSize, departmentId,
                        getUserId(), entrustSn, entrustCompanyName,
                        projectName, startDate, endDate, inputTime,
                        entrustDate, projectPart, sampleAge, reportStatus,
                        reportNo, inputerName, auditorName, apprvoerName,
                        printUserName);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(findDepartmentReportInfo,
                jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 应收款统计
     */
    @RequestMapping("findreceivablesStatistics.action")
    public void findreceivablesStatistics() {
        List<AgreementPage> findReceivablesStatistics = entrustInfoService
                .findReceivablesStatistics(pageNo, pageSize, accountType, contractNo, entrustCompanyName, projectName);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(findReceivablesStatistics,
                jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 委托流水统计
     */
    @RequestMapping("findentrustWaterAccountStatistics.action")
    public void findentrustWaterAccountStatistics() {
        System.out.println("进方法" + CommonMethod.getDate());
        List<WaterAccountStatisticsPage> findentrustWaterAccountStatistics = entrustInfoService
                .findentrustWaterAccountStatistics(pageNo, pageSize, entrustSn, startDate, endDate, entrustDate
                        , inputTime, capitalAccountNo, inputerName, entrustCompanyName, projectName
                        , entrustStatus, accountKinds, testDepartment, managmentDepartment, acceptanceMan, sampleName, accountType);
        System.out.println("处理完之后" + CommonMethod.getDate());

        CommonJsonConfig jsonConfig = new CommonJsonConfig();

        JSONArray jsonArr = JSONArray.fromObject(
                findentrustWaterAccountStatistics, jsonConfig);
        jsonPrint(jsonArr);
        System.out.println("返回结果" + CommonMethod.getDate());
    }

    /**
     * 编辑报告列表sampleReport对象数据
     */
    @RequestMapping("sampleReportList.action")
    public void sampleReportList() {
        if (CommonMethod.isNull(strEntrustDetail)) {
            jsonPrint("fail,参数strEntrustDetail不能为空");
            return;
        }
        List<SampleReport> SampleReporList = entrustDetailService.EntrustDetailsInfo(strEntrustDetail);

        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(
                SampleReporList, jsonConfig);
        jsonPrint(jsonArr);
    }


    public String getStrECompanyId() {
        return strECompanyId;
    }

    public void setStrECompanyId(String strECompanyId) {
        this.strECompanyId = strECompanyId;
    }

    public String getStrProjectId() {
        return strProjectId;
    }

    public void setStrProjectId(String strProjectId) {
        this.strProjectId = strProjectId;
    }

    public String getStrSUnitId() {
        return strSUnitId;
    }

    public void setStrSUnitId(String strSUnitId) {
        this.strSUnitId = strSUnitId;
    }

    public String getStrEntrustMDetail() {
        return strEntrustMDetail;
    }

    public void setStrEntrustMDetail(String strEntrustMDetail) {
        this.strEntrustMDetail = strEntrustMDetail;
    }

    public String getStrEntrustInfo() {
        return strEntrustInfo;
    }

    public void setStrEntrustInfo(String strEntrustInfo) {
        this.strEntrustInfo = strEntrustInfo;
    }

    public String getStrEntrustId() {
        return strEntrustId;
    }

    public void setStrEntrustId(String strEntrustId) {
        this.strEntrustId = strEntrustId;
    }

    public String getStrEntrustStatus() {
        return strEntrustStatus;
    }

    public void setStrEntrustStatus(String strEntrustStatus) {
        this.strEntrustStatus = strEntrustStatus;
    }

    public String getStrProjectMoney() {
        return strProjectMoney;
    }

    public void setStrProjectMoney(String strProjectMoney) {
        this.strProjectMoney = strProjectMoney;
    }

    public String getStrDepartmentId() {
        return strDepartmentId;
    }

    public void setStrDepartmentId(String strDepartmentId) {
        this.strDepartmentId = strDepartmentId;
    }

    public String getStrSampleNo() {
        return strSampleNo;
    }

    public void setStrSampleNo(String strSampleNo) {
        this.strSampleNo = strSampleNo;
    }

    public String getStrOutputValueYear() {
        return strOutputValueYear;
    }

    public void setStrOutputValueYear(String strOutputValueYear) {
        this.strOutputValueYear = strOutputValueYear;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getEntrustSn() {
        return entrustSn;
    }

    public void setEntrustSn(String entrustSn) {
        this.entrustSn = entrustSn;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public String getEntrustDate() {
        return entrustDate;
    }

    public void setEntrustDate(String entrustDate) {
        this.entrustDate = entrustDate;
    }

    public String getProjectPart() {
        return projectPart;
    }

    public void setProjectPart(String projectPart) {
        this.projectPart = projectPart;
    }

    public String getSampleAge() {
        return sampleAge;
    }

    public void setSampleAge(String sampleAge) {
        this.sampleAge = sampleAge;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public String getInputerName() {
        return inputerName;
    }

    public void setInputerName(String inputerName) {
        this.inputerName = inputerName;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getApprvoerName() {
        return apprvoerName;
    }

    public void setApprvoerName(String apprvoerName) {
        this.apprvoerName = apprvoerName;
    }

    public String getPrintUserName() {
        return printUserName;
    }

    public void setPrintUserName(String printUserName) {
        this.printUserName = printUserName;
    }

    public String getCapitalAccountNo() {
        return capitalAccountNo;
    }

    public void setCapitalAccountNo(String capitalAccountNo) {
        this.capitalAccountNo = capitalAccountNo;
    }

    public String getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    public String getAccountKinds() {
        return accountKinds;
    }

    public void setAccountKinds(String accountKinds) {
        this.accountKinds = accountKinds;
    }

    public String getTestDepartment() {
        return testDepartment;
    }

    public void setTestDepartment(String testDepartment) {
        testDepartment = testDepartment;
    }

    public String getManagmentDepartment() {
        return managmentDepartment;
    }

    public void setManagmentDepartment(String managmentDepartment) {
        this.managmentDepartment = managmentDepartment;
    }

    public String getAcceptanceMan() {
        return acceptanceMan;
    }

    public void setAcceptanceMan(String acceptanceMan) {
        this.acceptanceMan = acceptanceMan;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }


    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getEntrustCompanyName() {
        return entrustCompanyName;
    }

    public void setEntrustCompanyName(String entrustCompanyName) {
        this.entrustCompanyName = entrustCompanyName;
    }

    public String getStrEntrustDetail() {
        return strEntrustDetail;
    }

    public void setStrEntrustDetail(String strEntrustDetail) {
        this.strEntrustDetail = strEntrustDetail;
    }


}
