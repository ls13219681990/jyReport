package com.controller.finance;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.dao.page.EntrustInfoPage;
import com.dao.page.InvoiceDetailPage;
import com.dao.page.ReAccountDetailPage;
import com.model.InvoiceDetails;
import com.service.finance.InvoiceDetailsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("InvoiceDetailAction")
public class InvoiceDetailController extends QueryAction<InvoiceDetails> {

    /**
     * 发票
     */
    private static final long serialVersionUID = 1L;

    private String strInvoiceDetail = "";

    private String strInvoiceDetailId = "";//开票明细ID

    private String strEntrustId = "";//委托ID

    private String receivableState = "";


    @Autowired
    private InvoiceDetailsService invoiceDetailsService;

    /*@SuppressWarnings("rawtypes")
    @Action(value = "/invoiceDetailAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    @RequestMapping(value = "/asdsadsabaseDepartmentAction")
    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    @RequestMapping("saveInvoiceDetails.action")
    public void saveInvoiceDetails() {
        try {
            if (CommonMethod.isNull(strInvoiceDetail)) {
                jsonPrint("fail,参数strInvoiceDetail不能为空");
                return;
            }

            Map<String, Class> classMap = new HashMap<String, Class>();
            classMap.put("entrustIdList", EntrustInfoPage.class);

            InvoiceDetailPage iDetailPage = new InvoiceDetailPage();
            JSONObject pJsonObject = JSONObject.fromObject(strInvoiceDetail);
            iDetailPage = (InvoiceDetailPage) JSONObject.toBean(pJsonObject, InvoiceDetailPage.class, classMap);

            invoiceDetailsService.saveInvoiceDetails(iDetailPage, getUserId());
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
            if (CommonMethod.isNull(strInvoiceDetail)) {
                jsonPrint("fail,参数strInvoiceDetail不能为空");
                return;
            }
            InvoiceDetailPage iDetailPage = (InvoiceDetailPage) toBean(strInvoiceDetail, InvoiceDetailPage.class);
            invoiceDetailsService.updateInvoiceDetails(iDetailPage, getUserId());
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
     * 根据条件查找对应的发票记录
     */
    @RequestMapping("findInvoiceDetail.action")
    public void findInvoiceDetail() {
        InvoiceDetailPage idPage = new InvoiceDetailPage();
        if (!CommonMethod.isNull(strInvoiceDetail)) {
            idPage = (InvoiceDetailPage) toBean(strInvoiceDetail, InvoiceDetailPage.class);
        }

        List<InvoiceDetailPage> amList = invoiceDetailsService.findInvoiceDetail(idPage, strEntrustId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(amList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 根据发票ID查找对应的收款记录
     */
    @RequestMapping("findReAcDetail.action")
    public void findReAcDetail() {
        if (CommonMethod.isNull(strInvoiceDetailId)) {
            jsonPrint("fail,参数strInvoiceDetailId不能为空");
            return;
        }
        InvoiceDetails iDetails = invoiceDetailsService.findById(strInvoiceDetailId);
        List<ReAccountDetailPage> reAcDetailsList = invoiceDetailsService.findReAcDetail(iDetails);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(reAcDetailsList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 发票作废
     */
    @RequestMapping("invoiceInvalidation.action")
    public void invoiceInvalidation() {
        if (CommonMethod.isNull(strInvoiceDetailId)) {
            jsonPrint("fail,参数strInvoiceDetailId不能为空");
            return;
        }
        if (CommonMethod.isNull(receivableState)) {
            jsonPrint("fail,参数receivableState不能为空");
            return;
        }

        InvoiceDetails invoiceDetails = invoiceDetailsService.findById(strInvoiceDetailId);
        if ("00".equals(receivableState)) {
            invoiceDetails.setReceivableState("");
        } else {
            invoiceDetails.setReceivableState("作废");
        }
        invoiceDetailsService.update(invoiceDetails);
        jsonPrint("true");
    }


    /**
     * 根据条件查找对应的发票记录（作废）
     */
    @RequestMapping("findInvoiceDetailInvalidation.action")
    public void findInvoiceDetailInvalidation() {
        InvoiceDetailPage idPage = new InvoiceDetailPage();
        if (!CommonMethod.isNull(strInvoiceDetail)) {
            idPage = (InvoiceDetailPage) toBean(strInvoiceDetail, InvoiceDetailPage.class);
        }

        List<InvoiceDetailPage> amList = invoiceDetailsService.findInvoiceDetailInvalidation(idPage, strEntrustId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(amList, jsonConfig);
        jsonPrint(jsonArr);
    }


    public String getStrInvoiceDetail() {
        return strInvoiceDetail;
    }

    public void setStrInvoiceDetail(String strInvoiceDetail) {
        this.strInvoiceDetail = strInvoiceDetail;
    }

    public String getStrInvoiceDetailId() {
        return strInvoiceDetailId;
    }

    public void setStrInvoiceDetailId(String strInvoiceDetailId) {
        this.strInvoiceDetailId = strInvoiceDetailId;
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
