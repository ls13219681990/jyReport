package com.controller.finance;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.model.ReceivableInvoiceDetails;
import com.service.finance.ReceivableInDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("receivableInDetailAction")
public class ReceivableInDetailController extends QueryAction<ReceivableInvoiceDetails> {

    /**
     * 收款发票关系
     */
    private static final long serialVersionUID = 1L;

    /*private String strReInDetail = "";

    private String strInvoiceDetailId = "";//发票明细ID

    private String strAccountDetailId = "";//收款明细ID
*/
    @Autowired
    private ReceivableInDetailsService receivableInDetailsService;

    /*@Action(value = "/receivableInDetailAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    @RequestMapping(value = "/zxcabaseDepartmentAction")
    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    /**
     * 保存发票与收款的关系
     */
    @RequestMapping("saveReInDetails.action")
    @ResponseBody
    public String saveReInDetails(String strReInDetail, String strInvoiceDetailId) {
        try {
            if (CommonMethod.isNull(strReInDetail) || CommonMethod.isNull(strInvoiceDetailId)) {
                throw new BusinessException("fail,参数strReInDetail或者strInvoiceDetailId不能为空");
            }
            receivableInDetailsService.saveReInDetails(getColl(strReInDetail), strInvoiceDetailId);
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
     * 保存收款与发票的关系
     */
    @RequestMapping("saveReAcDetails.action")
    @ResponseBody
    public String saveReAcDetails(String strReInDetail, String strAccountDetailId) {
        try {
            if (CommonMethod.isNull(strReInDetail) || CommonMethod.isNull(strAccountDetailId)) {
                throw new BusinessException("fail,参数strReInDetail或者strAccountDetailId不能为空");
            }
            receivableInDetailsService.saveReAcDetails(getColl(strReInDetail), strAccountDetailId);
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
     * 根据票据ID查找对应的收款记录
     */
    @RequestMapping("findReInDetails.action")
    @ResponseBody
    public List<ReceivableInvoiceDetails> findReInDetails(String strInvoiceDetailId) {
        if (CommonMethod.isNull(strInvoiceDetailId)) {
            throw new BusinessException("fail,参数strInvoiceDetailId不能为空！", "");
        }
        List<ReceivableInvoiceDetails> ReInDetailsList = receivableInDetailsService.findReInDetails(strInvoiceDetailId, "0");
        return ReInDetailsList;
    }

    /**
     * 根据收款ID查找对应的票据记录
     */
    @RequestMapping("findReAcDetails.action")
    @ResponseBody
    public List<ReceivableInvoiceDetails> findReAcDetails(String strAccountDetailId) {
        if (CommonMethod.isNull(strAccountDetailId)) {
            throw new BusinessException("fail,参数strAccountDetailId不能为空！", "");
        }
        List<ReceivableInvoiceDetails> ReInDetailsList = receivableInDetailsService.findReInDetails(strAccountDetailId, "1");
        return ReInDetailsList;
    }

/*
    public String getStrInvoiceDetailId() {
        return strInvoiceDetailId;
    }

    public void setStrInvoiceDetailId(String strInvoiceDetailId) {
        this.strInvoiceDetailId = strInvoiceDetailId;
    }

    public String getStrReInDetail() {
        return strReInDetail;
    }

    public void setStrReInDetail(String strReInDetail) {
        this.strReInDetail = strReInDetail;
    }

    public String getStrAccountDetailId() {
        return strAccountDetailId;
    }

    public void setStrAccountDetailId(String strAccountDetailId) {
        this.strAccountDetailId = strAccountDetailId;
    }
*/

}
