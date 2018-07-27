package com.controller.finance;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.dao.page.EntrustInfoPage;
import com.dao.page.InvoiceDetailPage;
import com.dao.page.ReAccountDetailPage;
import com.model.ReceivableAccountDetails;
import com.model.ReceivableInvoiceDetails;
import com.service.finance.ReceivableAcDetailsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("receivableAcDetailAction")
public class ReceivableAcDetailController extends QueryAction<ReceivableInvoiceDetails> {

    /**
     * 收款
     */
    private static final long serialVersionUID = 1L;

    private String strReAcDetail = "";

    private String strAccountDetailId = "";//收款明细ID

    private String strEntrustId = "";//委托ID

    private String receivableState = "";


    @Autowired
    private ReceivableAcDetailsService receivableAcDetailsService;


    /*@SuppressWarnings("rawtypes")
    @Action(value = "/receivableAcDetailAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    @RequestMapping(value = "/aqbaseDepartmentAction")
    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    @RequestMapping("saveReAcDetails.action")
    public void saveReAcDetails() {
        try {
            if (CommonMethod.isNull(strReAcDetail)) {
                jsonPrint("fail,参数strReAcDetail不能为空");
                return;
            }

            Map<String, Class> classMap = new HashMap<String, Class>();
            classMap.put("entrustIdList", EntrustInfoPage.class);

            ReAccountDetailPage reAcDetailPage = new ReAccountDetailPage();
            JSONObject pJsonObject = JSONObject.fromObject(strReAcDetail);
            reAcDetailPage = (ReAccountDetailPage) JSONObject.toBean(pJsonObject, ReAccountDetailPage.class, classMap);

            receivableAcDetailsService.saveReAcDetails(reAcDetailPage, getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @RequestMapping("updateInvoiceDetails.action")
    public void updateInvoiceDetails() {
        try {
            if (CommonMethod.isNull(strReAcDetail)) {
                jsonPrint("fail,参数strReAcDetail不能为空");
                return;
            }
            ReAccountDetailPage reAcDetailPage = (ReAccountDetailPage) toBean(strReAcDetail, ReAccountDetailPage.class);
            receivableAcDetailsService.updateReAcDetails(reAcDetailPage, getUserId());
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
     * 根据条件查找对应的收款记录
     */
    @RequestMapping("findReAcDetail.action")
    public void findReAcDetail() {
        ReAccountDetailPage reAcPage = new ReAccountDetailPage();
        if (!CommonMethod.isNull(strReAcDetail)) {
            reAcPage = (ReAccountDetailPage) toBean(strReAcDetail, ReAccountDetailPage.class);
        }

        List<ReAccountDetailPage> reAcPageList = receivableAcDetailsService.findReAcDetail(reAcPage, strEntrustId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(reAcPageList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 根据收款ID查找对应的发票记录
     */
    @RequestMapping("findInDetail.action")
    public void findInDetail() {
        if (CommonMethod.isNull(strAccountDetailId)) {
            jsonPrint("fail,参数strAccountDetailId不能为空");
            return;
        }
        ReceivableAccountDetails reAcDetail = receivableAcDetailsService.findById(strAccountDetailId);
        List<InvoiceDetailPage> iDetailPageList = receivableAcDetailsService.findinDetailPageList(reAcDetail);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(iDetailPageList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 收款作废
     */
    @RequestMapping("receivablesInvalidation.action")
    public void receivablesInvalidation() {
        if (CommonMethod.isNull(strAccountDetailId)) {
            jsonPrint("fail,参数strAccountDetailId不能为空");
            return;
        }
        if (CommonMethod.isNull(receivableState)) {
            jsonPrint("fail,参数receivableState不能为空");
            return;
        }
        ReceivableAccountDetails reAcDetail = receivableAcDetailsService.findById(strAccountDetailId);
        if ("00".equals(receivableState)) {
            reAcDetail.setReceivableState("");
        } else {
            reAcDetail.setReceivableState("作废");
        }
        receivableAcDetailsService.update(reAcDetail);
        jsonPrint("true");
    }

    /**
     * 根据条件查找对应的收款记录(作废)
     */
    @RequestMapping("findReAcDetailInvalidation.action")
    public void findReAcDetailInvalidation() {
        ReAccountDetailPage reAcPage = new ReAccountDetailPage();
        if (!CommonMethod.isNull(strReAcDetail)) {
            reAcPage = (ReAccountDetailPage) toBean(strReAcDetail, ReAccountDetailPage.class);
        }

        List<ReAccountDetailPage> reAcPageList = receivableAcDetailsService.findReAcDetailInvalidation(reAcPage, strEntrustId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(reAcPageList, jsonConfig);
        jsonPrint(jsonArr);
    }


    public String getStrReAcDetail() {
        return strReAcDetail;
    }

    public void setStrReAcDetail(String strReAcDetail) {
        this.strReAcDetail = strReAcDetail;
    }

    public String getStrAccountDetailId() {
        return strAccountDetailId;
    }

    public void setStrAccountDetailId(String strAccountDetailId) {
        this.strAccountDetailId = strAccountDetailId;
    }

    public String getStrEntrustId() {
        return strEntrustId;
    }

    public void setStrEntrustId(String strEntrustId) {
        this.strEntrustId = strEntrustId;
    }

    public String getReceivableState() {
        return receivableState;
    }

    public void setReceivableState(String receivableState) {
        this.receivableState = receivableState;
    }
}
