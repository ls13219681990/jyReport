package com.controller.finance;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.dao.page.InvoiceDetailPage;
import com.dao.page.ReAccountDetailPage;
import com.model.ReceivableEntrustDetails;
import com.service.finance.ReceivableEnDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("receivableEnDetailAction")
public class ReceivableEnDetailController extends QueryAction<ReceivableEntrustDetails> {

    /**
     * 收款委托关系
     */
    private static final long serialVersionUID = 1L;

    /* private String strReEnDetail = "";

     private String strAccountDetailId = "";//收款明细ID

     private String strInvoiceDetailId = "";//发票明细ID
 */
    @Autowired
    private ReceivableEnDetailsService receivableEnDetailsService;

    /*@Action(value = "/receivableEnDetailAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    @RequestMapping(value = "/aabaseDepartmentAction")
    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    /**
     * 保存收款与委托的关系
     */
    @RequestMapping("saveAcEnDetails.action")
    @ResponseBody
    public String saveAcEnDetails(String strReEnDetail, String strAccountDetailId, String userId) {
        try {
            if (CommonMethod.isNull(strReEnDetail) || CommonMethod.isNull(strAccountDetailId)) {
                throw new BusinessException("fail,参数strReInDetail或者strAccountDetailId不能为空");
            }
            receivableEnDetailsService.saveAcEnDetails(getColl(strReEnDetail), strAccountDetailId, userId);
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
     * 保存发票与委托的关系
     */
    @RequestMapping("saveInEnDetails.action")
    @ResponseBody
    public String saveInEnDetails(String strReEnDetail, String strInvoiceDetailId, String userId) {
        try {
            if (CommonMethod.isNull(strReEnDetail) || CommonMethod.isNull(strInvoiceDetailId)) {
                throw new BusinessException("fail,参数strReInDetail或者strInvoiceDetailId不能为空");
            }
            receivableEnDetailsService.saveInEnDetails(getColl(strReEnDetail), strInvoiceDetailId, userId);
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
     * 根据收款ID查找对应的委托记录
     */
    @RequestMapping("findReEnDetails.action")
    @ResponseBody
    public List<ReceivableEntrustDetails> findReEnDetails(String strAccountDetailId) {
        if (CommonMethod.isNull(strAccountDetailId)) {
            throw new BusinessException("fail,参数strAccountDetailId不能为空！", "");
        }
        List<ReceivableEntrustDetails> ReEnDetailsList = receivableEnDetailsService.findReEnDetails(strAccountDetailId);
        return ReEnDetailsList;
    }

    /**
     * 根据发票ID查找对应的委托记录
     */
    @RequestMapping("findReInDetails.action")
    @ResponseBody
    public List<ReceivableEntrustDetails> findReInDetails(String strInvoiceDetailId) {
        if (CommonMethod.isNull(strInvoiceDetailId)) {
            throw new BusinessException("fail,参数strAccountDetailId不能为空！", "");
        }
        List<ReceivableEntrustDetails> ReEnDetailsList = receivableEnDetailsService.findReInDetails(strInvoiceDetailId);
        return ReEnDetailsList;
    }

    /**
     * 根据收款ID查找对应的发票记录
     */
    @RequestMapping("findInDetail.action")
    @ResponseBody
    public List<InvoiceDetailPage> findInDetail(String strAccountDetailId) {
        if (CommonMethod.isNull(strAccountDetailId)) {
            throw new BusinessException("fail,参数strAccountDetailId不能为空！", "");
        }
        List<ReceivableEntrustDetails> ReEnDetailsList = receivableEnDetailsService.findReEnDetails(strAccountDetailId);
        List<InvoiceDetailPage> iDetailPageList = receivableEnDetailsService.findInDetailPageList(ReEnDetailsList);
        return iDetailPageList;
    }

    /**
     * 根据发票ID查找对应的收款记录
     */
    @RequestMapping("findAcDetail.action")
    @ResponseBody
    public List<ReAccountDetailPage> findAcDetail(String strInvoiceDetailId) {
        if (CommonMethod.isNull(strInvoiceDetailId)) {
            throw new BusinessException("fail,参数strAccountDetailId不能为空！", "");
        }
        List<ReceivableEntrustDetails> ReEnDetailsList = receivableEnDetailsService.findReInDetails(strInvoiceDetailId);
        List<ReAccountDetailPage> reAcDetailsList = receivableEnDetailsService.findAcDetailPageList(ReEnDetailsList);
        return reAcDetailsList;
    }

    /*public String getStrReEnDetail() {
        return strReEnDetail;
    }

    public void setStrReEnDetail(String strReEnDetail) {
        this.strReEnDetail = strReEnDetail;
    }

    public String getStrAccountDetailId() {
        return strAccountDetailId;
    }

    public void setStrAccountDetailId(String strAccountDetailId) {
        this.strAccountDetailId = strAccountDetailId;
    }

    public String getStrInvoiceDetailId() {
        return strInvoiceDetailId;
    }

    public void setStrInvoiceDetailId(String strInvoiceDetailId) {
        this.strInvoiceDetailId = strInvoiceDetailId;
    }*/
}
