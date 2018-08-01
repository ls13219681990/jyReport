package com.controller.finance;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.dao.page.EntrustInfoPage;
import com.dao.page.InvoiceDetailPage;
import com.dao.page.ReAccountDetailPage;
import com.model.InvoiceDetails;
import com.service.finance.InvoiceDetailsService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("invoiceDetailAction")
public class InvoiceDetailController extends QueryAction<InvoiceDetails> {

    /**
     * 发票
     */
    private static final long serialVersionUID = 1L;

  /*  private String strInvoiceDetail = "";

    private String strInvoiceDetailId = "";//开票明细ID

    private String strEntrustId = "";//委托ID

    private String receivableState = "";*/


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
    @ResponseBody
    public String saveInvoiceDetails(String strInvoiceDetail, String userId) {
        try {
            if (CommonMethod.isNull(strInvoiceDetail)) {
                throw new BusinessException("fail,参数strInvoiceDetail不能为空！", "");
            }

            Map<String, Class> classMap = new HashMap<String, Class>();
            classMap.put("entrustIdList", EntrustInfoPage.class);

            InvoiceDetailPage iDetailPage = new InvoiceDetailPage();
            JSONObject pJsonObject = JSONObject.fromObject(strInvoiceDetail);
            iDetailPage = (InvoiceDetailPage) JSONObject.toBean(pJsonObject, InvoiceDetailPage.class, classMap);

            invoiceDetailsService.saveInvoiceDetails(iDetailPage, userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("updateInvoiceDetails.action")
    @ResponseBody
    public String updateInvoiceDetails(String strInvoiceDetail, String userId) {
        try {
            if (CommonMethod.isNull(strInvoiceDetail)) {
                throw new BusinessException("fail,参数strInvoiceDetail不能为空！", "");
            }
            InvoiceDetailPage iDetailPage = (InvoiceDetailPage) toBean(strInvoiceDetail, InvoiceDetailPage.class);
            invoiceDetailsService.updateInvoiceDetails(iDetailPage, userId);
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
     * 根据条件查找对应的发票记录
     */
    @RequestMapping("findInvoiceDetail.action")
    @ResponseBody
    public List<InvoiceDetailPage> findInvoiceDetail(String strInvoiceDetail, String strEntrustId) {
        InvoiceDetailPage idPage = new InvoiceDetailPage();
        if (!CommonMethod.isNull(strInvoiceDetail)) {
            idPage = (InvoiceDetailPage) toBean(strInvoiceDetail, InvoiceDetailPage.class);
        }
        List<InvoiceDetailPage> amList = invoiceDetailsService.findInvoiceDetail(idPage, strEntrustId);
        return amList;
    }

    /**
     * 根据发票ID查找对应的收款记录
     */
    @RequestMapping("findReAcDetail.action")
    @ResponseBody
    public List<ReAccountDetailPage> findReAcDetail(String strInvoiceDetailId) {
        if (CommonMethod.isNull(strInvoiceDetailId)) {
            throw new BusinessException("fail,参数strInvoiceDetailId不能为空！", "");
        }
        InvoiceDetails iDetails = invoiceDetailsService.findById(strInvoiceDetailId);
        List<ReAccountDetailPage> reAcDetailsList = invoiceDetailsService.findReAcDetail(iDetails);
        return reAcDetailsList;
    }

    /**
     * 发票作废
     */
    @RequestMapping("invoiceInvalidation.action")
    @ResponseBody
    public String invoiceInvalidation(String strInvoiceDetailId, String receivableState) {
        if (CommonMethod.isNull(strInvoiceDetailId)) {
            throw new BusinessException("fail,参数strAmountReceivable不能为空！", "");
        }
        if (CommonMethod.isNull(receivableState)) {
            throw new BusinessException("fail,参数strAmountReceivable不能为空！", "");
        }

        InvoiceDetails invoiceDetails = invoiceDetailsService.findById(strInvoiceDetailId);
        if ("00".equals(receivableState)) {
            invoiceDetails.setReceivableState("");
        } else {
            invoiceDetails.setReceivableState("作废");
        }
        invoiceDetailsService.update(invoiceDetails);
        return "true";
    }


    /**
     * 根据条件查找对应的发票记录（作废）
     */
    @RequestMapping("findInvoiceDetailInvalidation.action")
    public List<InvoiceDetailPage> findInvoiceDetailInvalidation(String strInvoiceDetail, String strEntrustId) {
        InvoiceDetailPage idPage = new InvoiceDetailPage();
        if (!CommonMethod.isNull(strInvoiceDetail)) {
            idPage = (InvoiceDetailPage) toBean(strInvoiceDetail, InvoiceDetailPage.class);
        }

        List<InvoiceDetailPage> amList = invoiceDetailsService.findInvoiceDetailInvalidation(idPage, strEntrustId);
        return amList;
    }


    /*public String getStrInvoiceDetail() {
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
    }*/

}
