package com.controller.finance;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.dao.page.InvoiceDetailPage;
import com.dao.page.ReAccountDetailPage;
import com.model.ReceivableEntrustDetails;
import com.service.finance.ReceivableEnDetailsService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("receivableEnDetailAction")
public class ReceivableEnDetailController extends QueryAction<ReceivableEntrustDetails> {

    /**
     * 收款委托关系
     */
    private static final long serialVersionUID = 1L;

    private String strReEnDetail = "";

    private String strAccountDetailId = "";//收款明细ID

    private String strInvoiceDetailId = "";//发票明细ID

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
    public void saveAcEnDetails() {
        try {
            if (CommonMethod.isNull(strReEnDetail) || CommonMethod.isNull(strAccountDetailId)) {
                jsonPrint("fail,参数strReInDetail或者strAccountDetailId不能为空");
                return;
            }
            receivableEnDetailsService.saveAcEnDetails(getColl(strReEnDetail), strAccountDetailId, getUserId());
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
     * 保存发票与委托的关系
     */
    @RequestMapping("saveInEnDetails.action")
    public void saveInEnDetails() {
        try {
            if (CommonMethod.isNull(strReEnDetail) || CommonMethod.isNull(strInvoiceDetailId)) {
                jsonPrint("fail,参数strReInDetail或者strInvoiceDetailId不能为空");
                return;
            }
            receivableEnDetailsService.saveInEnDetails(getColl(strReEnDetail), strInvoiceDetailId, getUserId());
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
     * 根据收款ID查找对应的委托记录
     */
    @RequestMapping("findReEnDetails.action")
    public void findReEnDetails() {
        if (CommonMethod.isNull(strAccountDetailId)) {
            jsonPrint("fail,参数strAccountDetailId不能为空");
            return;
        }
        List<ReceivableEntrustDetails> ReEnDetailsList = receivableEnDetailsService.findReEnDetails(strAccountDetailId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(ReEnDetailsList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 根据发票ID查找对应的委托记录
     */
    @RequestMapping("findReInDetails.action")
    public void findReInDetails() {
        if (CommonMethod.isNull(strInvoiceDetailId)) {
            jsonPrint("fail,参数strInvoiceDetailId不能为空");
            return;
        }
        List<ReceivableEntrustDetails> ReEnDetailsList = receivableEnDetailsService.findReInDetails(strInvoiceDetailId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(ReEnDetailsList, jsonConfig);
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
        List<ReceivableEntrustDetails> ReEnDetailsList = receivableEnDetailsService.findReEnDetails(strAccountDetailId);
        List<InvoiceDetailPage> iDetailPageList = receivableEnDetailsService.findInDetailPageList(ReEnDetailsList);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(iDetailPageList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 根据发票ID查找对应的收款记录
     */
    @RequestMapping("findAcDetail.action")
    public void findAcDetail() {
        if (CommonMethod.isNull(strInvoiceDetailId)) {
            jsonPrint("fail,参数strInvoiceDetailId不能为空");
            return;
        }
        List<ReceivableEntrustDetails> ReEnDetailsList = receivableEnDetailsService.findReInDetails(strInvoiceDetailId);
        List<ReAccountDetailPage> reAcDetailsList = receivableEnDetailsService.findAcDetailPageList(ReEnDetailsList);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(reAcDetailsList, jsonConfig);
        jsonPrint(jsonArr);
    }

    public String getStrReEnDetail() {
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
    }
}
