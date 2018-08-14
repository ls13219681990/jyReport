package com.controller.entrust;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Transactional
@Controller
@RequestMapping("entrustInfoAction")
public class EntrustInfoController extends QueryAction<EntrustInfo> {

    /**
     *
     */
    private final static HttpServletRequest REQUEST = null;

    private static final long serialVersionUID = 1L;
   /* // 委托单位ID
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
    private String accountType;//收费类型*/


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
    @ResponseBody
    public String findIsEntrust(String strECompanyId, String strProjectId, String strSUnitId) {
        List<EntrustInfo> eiList = entrustInfoService.findIsEntrust(
                strECompanyId, strProjectId, strSUnitId);
        List<String> reulst = new ArrayList<String>();
        if (eiList != null && eiList.size() > 0) {// 已经办理了委托
            reulst.add("false");
        } else {
            reulst.add("true");
        }
        return reulst.get(0).toString();
    }

    /**
     * 委托时，获取检测明细的价格
     */
    @RequestMapping("findEntrustMDetail.action")
    @ResponseBody
    public List<EntrustParameterPage> findEntrustMDetail(String strEntrustMDetail) {
        if (CommonMethod.isNull(strEntrustMDetail)) {
            throw new BusinessException("fail,参数strEntrustMDetail不能为空！", "");
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
        return eppList;
    }

    @SuppressWarnings({"rawtypes", "unused"})
    @RequestMapping("saveEntrustInfo.action")
    @ResponseBody
    public String saveEntrustInfo(String userId, String strEntrustInfo, HttpServletRequest request, @RequestParam Map<String, String> map) {
        String entrustId = null;
        try {
            //HttpServletRequest request = ServletActionContext.getRequest();

            //Map parMap = request.getParameterMap();
            Object uerObj = map.get("userId");
            Object eDetailsObj = map.get("strEntrustInfo");

            if (CommonMethod.isNull(strEntrustInfo)) {
                throw new BusinessException("fail,参数strEntrustInfo不能为空！", "");
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
                throw new BusinessException("fail,参数委托类型不能为空");
            } else {
                if ("00".equals(esPage.getEntrustType())
                        || "01".equals(esPage.getEntrustType())) {
                } else {
                    throw new BusinessException("fail,参数委托类型的值只能为00或者01");
                }
            }
            // 委托单位不能为空
            if (CommonMethod.isNull(esPage.getEntrustCompanyId())) {
                throw new BusinessException("fail,参数单位不能为空！");
            }
            // 工程不能为空
            if (CommonMethod.isNull(esPage.getProjectId())) {
                throw new BusinessException("fail,工程不能为空！");
            }
            entrustId = entrustInfoService.saveEntrustInfo(esPage,
                    userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true:" + entrustId;
    }

    @RequestMapping("updateEntrustInfoBySn.action")
    @ResponseBody
    public String updateEntrustInfoBySn(String userId, String strEntrustInfo) {
        try {
            if (CommonMethod.isNull(strEntrustInfo)) {
                throw new BusinessException("fail,参数strEntrustInfo不能为空！", "");
            }
            strEntrustInfo = strEntrustInfo.replace("OO", "#");

            EntrustSavePage esPage = (EntrustSavePage) toBean(strEntrustInfo,
                    EntrustSavePage.class);
            // 委托单位不能为空
            if (CommonMethod.isNull(esPage.getInvalid())) {
                throw new BusinessException("fail,作废失败,不能为空！");
            }
            entrustInfoService.updateEntrustInfoBySn(esPage, userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("updateEntrustInfo.action")
    @ResponseBody
    public String updateEntrustInfo(String userId, String strEntrustInfo) {
        try {
            if (CommonMethod.isNull(strEntrustInfo)) {
                throw new BusinessException("fail,参数strEntrustInfo不能为空！", "");
            }
            strEntrustInfo = strEntrustInfo.replace("OO", "#");

            EntrustSavePage esPage = (EntrustSavePage) toBean(strEntrustInfo,
                    EntrustSavePage.class);
            // 委托单位不能为空
            if (CommonMethod.isNull(esPage.getEntrustCompanyId())) {
                throw new BusinessException("fail,参数单位不能为空！");
            }
            // 工程不能为空
            if (CommonMethod.isNull(esPage.getProjectId())) {
                throw new BusinessException("fail,工程不能为空！");
            }
            entrustInfoService.updateEntrustInfo(esPage, userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @SuppressWarnings({"rawtypes", "unused"})
    @RequestMapping("updateEntrustMDetail.action")
    @ResponseBody
    public String updateEntrustMDetail(String userId, String strEntrustInfo) {
        try {
            //HttpServletRequest request = ServletActionContext.getRequest();
            Map parMap = REQUEST.getParameterMap();
            Object uerObj = parMap.get("userId");
            Object eDetailsObj = parMap.get("strEntrustInfo");

            if (CommonMethod.isNull(strEntrustInfo)) {
                throw new BusinessException("fail,参数strEntrustInfo不能为空！", "");
            }

            Map<String, Class> classMap = new HashMap<String, Class>();
            classMap.put("edReport", EntrustDetailReportPage.class);
            classMap.put("enPaInfoPageList", EntrustDetailSavePage.class);// 委托金额明细

            EntrustSavePage esPage = new EntrustSavePage();
            JSONObject pJsonObject = JSONObject.fromObject(strEntrustInfo);
            esPage = (EntrustSavePage) JSONObject.toBean(pJsonObject,
                    EntrustSavePage.class, classMap);

            entrustInfoService.updateEntrustMDetail(esPage, userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @SuppressWarnings({"rawtypes", "unused"})
    @RequestMapping("saveEntrustMDetail.action")
    @ResponseBody
    public String saveEntrustMDetail(String userId, String strEntrustInfo) {
        try {
            //HttpServletRequest request = ServletActionContext.getRequest();
            Map parMap = REQUEST.getParameterMap();
            Object uerObj = parMap.get("userId");
            Object eDetailsObj = parMap.get("strEntrustInfo");

            if (CommonMethod.isNull(strEntrustInfo)) {
                throw new BusinessException("fail,参数strEntrustInfo不能为空！", "");
            }

            Map<String, Class> classMap = new HashMap<String, Class>();
            classMap.put("edReport", EntrustDetailReportPage.class);
            classMap.put("enPaInfoPageList", EntrustDetailSavePage.class);// 委托金额明细

            EntrustSavePage esPage = new EntrustSavePage();
            JSONObject pJsonObject = JSONObject.fromObject(strEntrustInfo);
            esPage = (EntrustSavePage) JSONObject.toBean(pJsonObject,
                    EntrustSavePage.class, classMap);

            entrustInfoService.saveEntrustMDetail(esPage, userId);
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
     * 根据委托条件，查询委托信息
     */
    @RequestMapping("findEntrustInfoByCondition.action")
    @ResponseBody
    public List<EntrustSavePage> findEntrustInfoByCondition(String strEntrustInfo) {
        EntrustSavePage esPage = new EntrustSavePage();
        if (!CommonMethod.isNull(strEntrustInfo)) {
            strEntrustInfo = strEntrustInfo.replace("OO", "#");
            esPage = (EntrustSavePage) toBean(strEntrustInfo,
                    EntrustSavePage.class);
        }
        List<EntrustSavePage> esPageList = entrustInfoService
                .findEntrustInfoByCondition(esPage);
        return esPageList;
    }

    /**
     * 根据委托条件，查询委托信息
     */
    @RequestMapping("findEntrustInfoByConditionByAll.action")
    @ResponseBody
    public List<EntrustSavePage> findEntrustInfoByConditionByAll(String strEntrustInfo) {
        EntrustSavePage esPage = new EntrustSavePage();
        if (!CommonMethod.isNull(strEntrustInfo)) {
            strEntrustInfo = strEntrustInfo.replace("OO", "#");
            esPage = (EntrustSavePage) toBean(strEntrustInfo,
                    EntrustSavePage.class);
        }
        // 根据工程ID,名称,委托编号查询工程下所有委托,根据委托查询所有委托明细
        List<EntrustSavePage> esPageList = entrustInfoService
                .findEntrustInfoByConditionByAll(esPage);
        return esPageList;

    }

    /**
     * 根据委托ID，查询委托金额明细信息
     */
    @RequestMapping("findEntrustDetailInfoById.action")
    @ResponseBody

    public List<EntrustSavePage> findEntrustDetailInfoById(String strEntrustId) {
        if (CommonMethod.isNull(strEntrustId)) {
            throw new BusinessException("fail,委托ID不能为空");
        }
        List<EntrustSavePage> esPageList = entrustInfoService
                .findEntrustDetailInfoById(strEntrustId);
        for (EntrustSavePage es : esPageList) {
            if (es.getAccountValue() == null) {
                es.setAccountValue(0.0d);
            }
            if (es.getInvolidMoney() == null) {
                es.setInvolidMoney(0.0d);
            }
        }
        return esPageList;
        //return JSON.toJSONString(esPageList,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullNumberAsZero);
    }

    /**
     * 根据委托明细ID，查询委托信息
     */
    @RequestMapping("showERDetailInfoById.action")
    @ResponseBody
    public EntrustInfo showERDetailInfoById(String entrustDetailId) {
        EntrustInfo esPageList = new EntrustInfo();
        if (CommonMethod.isNull(entrustDetailId)) {
            throw new BusinessException("fail,参数entrustDetailId不能为空！", "");
        }
        try {
            esPageList = entrustInfoService
                    .findEntrust(entrustDetailId);
        } catch (Exception e) {
            e.getMessage();
        }
        return esPageList;
    }

    /**
     * 根据委托ID，查询委托明细信息(报告：委托室用)
     */
    @RequestMapping("findERDetailInfoById.action")
    @ResponseBody
    public List<EntrustSavePage> findERDetailInfoById(String strEntrustInfo) {
        if (CommonMethod.isNull(strEntrustInfo)) {
            throw new BusinessException("fail,参数strEntrustInfo不能为空！", "");
        }
        EntrustSavePage esPage = (EntrustSavePage) toBean(strEntrustInfo,
                EntrustSavePage.class);

        List<EntrustSavePage> esPageList = entrustInfoService
                .findERDetailInfoById(esPage);
        return esPageList;
    }

    /**
     * 根据委托信息，查询委托明细信息(报告：检测科室用)
     */
    @RequestMapping("findERDetailInfo.action")
    @ResponseBody
    public List<EntrustReportPage> findERDetailInfo(String strEntrustInfo) {
        if (CommonMethod.isNull(strEntrustInfo)) {
            throw new BusinessException("fail,参数strEntrustInfo不能为空！", "");
        }
        EntrustReportPage erPage = (EntrustReportPage) toBean(strEntrustInfo,
                EntrustReportPage.class);

        List<EntrustReportPage> erPageList = entrustInfoService
                .findERDetailInfo(erPage);
        return erPageList;
    }

    /**
     * 查询委托传送单信息
     */
    @RequestMapping("findEntrustEfSheet.action")
    @ResponseBody
    public List<EntrustTfSheetPage> findEntrustEfSheet(String strEntrustId) {
        if (CommonMethod.isNull(strEntrustId)) {
            throw new BusinessException("fail,参数strEntrustId不能为空！", "");
        }
        List<EntrustTfSheetPage> eTfSheetList = entrustInfoService
                .findEntrustEfSheet(strEntrustId);
        return eTfSheetList;
    }

    /**
     * 查询检测传送单信息
     */
    @RequestMapping("findTestEfSheet.action")
    @ResponseBody
    public List<TestTfSheetPage> findTestEfSheet(String strEntrustId) {
        if (CommonMethod.isNull(strEntrustId)) {
            throw new BusinessException("fail,参数strEntrustId不能为空！", "");
        }
        List<TestTfSheetPage> eTfSheetList = entrustInfoService
                .findTestEfSheet(strEntrustId);
        return eTfSheetList;
    }

    /**
     * 根据委托记录ID修改委托记录状态
     */
    @ResponseBody
    @RequestMapping("updateEntrustStatus.action")
    public String updateEntrustStatus(String strEntrustId, String userId, String strEntrustStatus) {
        try {
            if (CommonMethod.isNull(strEntrustId)) {
                throw new BusinessException("fail,参数strEntrustId不能为空！", "");
            }

            entrustInfoService.updateEntrustStatus(strEntrustId,
                    strEntrustStatus, userId);

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
     * 查询工程应收预览信息
     *
     * @return
     */
    @RequestMapping("findProjectMoney.action")
    @ResponseBody
    public List<ProjectMoneyPage> findProjectMoney(String strProjectMoney) {
        ProjectMoneyPage pMoneyPage = new ProjectMoneyPage();
        if (!CommonMethod.isNull(strProjectMoney)) {
            pMoneyPage = (ProjectMoneyPage) toBean(strProjectMoney,
                    ProjectMoneyPage.class);
        }

        List<ProjectMoneyPage> pMoneyPageList = entrustInfoService
                .findProjectMoney(pMoneyPage);
        return pMoneyPageList;
    }

    /**
     * 查询工程检测费用统计信息
     *
     * @return
     */
    @RequestMapping("findProjectTestMoney.action")
    @ResponseBody
    public List<ProjectTestMoneyPage> findProjectTestMoney(String strProjectId) {
        if (CommonMethod.isNull(strProjectId)) {
            throw new BusinessException("fail,参数strProjectId不能为空！", "");
        }

        List<ProjectTestMoneyPage> ptmPageList = entrustInfoService
                .findProjectTestMoney(strProjectId);
        return ptmPageList;
    }

    /**
     * (报告：各科室批量审核批准用)
     */
    @SuppressWarnings({"unchecked", "unused", "rawtypes"})
    @RequestMapping("saveAllReportStatus.action")
    @ResponseBody
    public String saveAllReportStatus(String userId, String strEntrustInfo, @RequestParam Map<String, String> map) {
        try {
            //HttpServletRequest request = ServletActionContext.getRequest();
            //Map parMap = REQUEST.getParameterMap();
            Object uerObj = map.get("userId");
            Object eDetailsObj = map.get("strEntrustInfo");

            if (CommonMethod.isNull(strEntrustInfo)) {
                throw new BusinessException("fail,参数strEntrustInfo不能为空！", "");
            }
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(
                    new TimestampMorpher(formats));
            Collection<EntrustReportPage> collPage = JSONArray.toCollection(
                    JSONArray.fromObject(strEntrustInfo),
                    EntrustReportPage.class);

            entrustInfoService.saveAllReportStatus(collPage, userId);

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
     * (报告：判断各科室批量审核批准是否有未通过的)
     */
    @SuppressWarnings({"unchecked", "unused", "rawtypes"})
    @RequestMapping("ifAllReportStatus.action")
    @ResponseBody
    public List<String> ifAllReportStatus(String strEntrustInfo) {
        List<String> map = new ArrayList<String>();

        try {
            if (CommonMethod.isNull(strEntrustInfo)) {
                throw new BusinessException("fail,参数strEntrustInfo不能为空！", "");
            }
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(
                    new TimestampMorpher(formats));
            Collection<EntrustReportPage> collPage = JSONArray.toCollection(
                    JSONArray.fromObject(strEntrustInfo),
                    EntrustReportPage.class);
            map = entrustInfoService.ifAllReportStatus(collPage);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return map;
    }

    /**
     * 根据委托ID，查询委托明细信息(报告：各科室审核批准用)
     */
    @RequestMapping("findDepartmentDetailInfo.action")
    @ResponseBody
    public List<EntrustReportPage> findDepartmentDetailInfo(String userId, String strEntrustInfo) {
        if (CommonMethod.isNull(strEntrustInfo)) {
            throw new BusinessException("fail,参数strEntrustInfo不能为空！", "");
        }
        strEntrustInfo = strEntrustInfo.replace("OO", "#");
        strEntrustInfo = strEntrustInfo.replace("PLUS", "+");
        EntrustReportPage erPage = (EntrustReportPage) toBean(strEntrustInfo,
                EntrustReportPage.class);

        List<EntrustReportPage> erPageList = entrustInfoService
                .findDepartmentDetailInfo(erPage, userId);
        return erPageList;

    }

    /**
     * 根据委托ID，查询委托明细信息(报告：各科室录入时用)
     */
    @RequestMapping("findDepartmentDetailInputInfo.action")
    @ResponseBody
    public List<EntrustReportPage> findDepartmentDetailInputInfo(String userId, String strEntrustInfo) {
        if (CommonMethod.isNull(strEntrustInfo)) {
            throw new BusinessException("fail,参数strEntrustInfo不能为空！", "");
        }
        EntrustReportPage erPage = (EntrustReportPage) toBean(strEntrustInfo,
                EntrustReportPage.class);

        List<EntrustReportPage> erPageList = entrustInfoService
                .findDepartmentDetailInputInfo(erPage, userId);

        return erPageList;
    }

    /**
     * 查询各科室产值统计信息
     *
     * @return
     */
    @RequestMapping("findOutputValueCount.action")
    @ResponseBody
    public List<OutputValueCountPage> findOutputValueCount(String strOutputValueYear) {
        if (CommonMethod.isNull(strOutputValueYear)) {
            throw new BusinessException("fail,参数strOutputValueYear不能为空！", "");
        }

        List<OutputValueCountPage> ovcPageList = entrustInfoService
                .findOutputValueCount(strOutputValueYear);
        return ovcPageList;
    }

    /**
     * 根据部门返回报告列表
     */
    @RequestMapping("findDepartmentReportInfo.action")
    @ResponseBody
    public List<TestReportInfoPage> findDepartmentReportInfo(Integer pageNo, Integer pageSize, String departmentId, String userId, String entrustSn, String entrustCompanyName, String projectName, String startDate, String endDate, String inputTime, String entrustDate, String projectPart, String sampleAge, String reportStatus, String reportNo, String inputerName, String auditorName, String apprvoerName, String printUserName) {
        List<TestReportInfoPage> findDepartmentReportInfo = entrustInfoService
                .findDepartmentReportInfo(pageNo, pageSize, departmentId,
                        userId, entrustSn, entrustCompanyName,
                        projectName, startDate, endDate, inputTime,
                        entrustDate, projectPart, sampleAge, reportStatus,
                        reportNo, inputerName, auditorName, apprvoerName,
                        printUserName);
        return findDepartmentReportInfo;
    }

    /**
     * 应收款统计
     */
    @RequestMapping("findreceivablesStatistics.action")
    @ResponseBody
    public List<AgreementPage> findreceivablesStatistics(Integer pageNo, Integer pageSize, String accountType, String contractNo, String entrustCompanyName, String projectName) {
        List<AgreementPage> findReceivablesStatistics = entrustInfoService
                .findReceivablesStatistics(pageNo, pageSize, accountType, contractNo, entrustCompanyName, projectName);
        return findReceivablesStatistics;
    }

    /**
     * 委托流水统计
     */
    @RequestMapping("findentrustWaterAccountStatistics.action")
    @ResponseBody
    public List<WaterAccountStatisticsPage> findentrustWaterAccountStatistics(
            Integer pageNo, Integer pageSize, String entrustSn,
            String startDate, String endDate, String entrustDate,
            String inputTime, String capitalAccountNo, String inputerName,
            String entrustCompanyName, String projectName,
            String entrustStatus, String accountKinds, String testDepartment,
            String managmentDepartment, String acceptanceMan, String sampleName, String accountType) {

        List<WaterAccountStatisticsPage> findentrustWaterAccountStatistics = entrustInfoService
                .findentrustWaterAccountStatistics(pageNo, pageSize, entrustSn, startDate, endDate, entrustDate
                        , inputTime, capitalAccountNo, inputerName, entrustCompanyName, projectName
                        , entrustStatus, accountKinds, testDepartment, managmentDepartment, acceptanceMan, sampleName, accountType);

        return findentrustWaterAccountStatistics;


    }

    /**
     * 编辑报告列表sampleReport对象数据
     */
    @RequestMapping("sampleReportList.action")
    @ResponseBody
    public List<SampleReport> sampleReportList(String strEntrustDetail) {
        if (CommonMethod.isNull(strEntrustDetail)) {
            throw new BusinessException("fail,参数strEntrustDetail不能为空！", "");
        }
        List<SampleReport> SampleReporList = entrustDetailService.EntrustDetailsInfo(strEntrustDetail);

        return SampleReporList;
    }


    /*public String getStrECompanyId() {
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

*/
}
