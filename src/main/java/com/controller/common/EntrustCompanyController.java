package com.controller.common;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.dao.page.AdvanceMoneyPage;
import com.model.EntrustCompany;
import com.service.common.EntrustCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("entrustCompanyAction")
public class EntrustCompanyController extends QueryAction<EntrustCompany> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

 /*   private String strEntrustCompany = "";

    private String strEntrustCompanyId = "";//单位ID

    private String strEntrustCompanyName = "";//单位名称

    private String strEntrustCompanyNo = "";//单位编号

    private String strAdvanceMoney = "";//预收款充值金额*/

    @Autowired
    private EntrustCompanyService entrustCompanyService;

    /*@Action(value = "/entrustCompanyAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    public String jompSys() {
        return "/jsp/sys/sysCode";
    }

    @RequestMapping("saveEntrustCompany.action")
    @ResponseBody
    public String saveEntrustCompany(String strEntrustCompany, String userId) {
        String entrustCompanyId = null;
        try {
            if (CommonMethod.isNull(strEntrustCompany)) {
                throw new BusinessException("fail,参数strEntrustCompany不能为空");
            }
            entrustCompanyId = entrustCompanyService.saveEntrustCompany(getColl(strEntrustCompany), userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true:" + entrustCompanyId;
    }

    @RequestMapping("updateEntrustCompany.action")
    @ResponseBody
    public String updateEntrustCompany(String strEntrustCompany, String userId) {
        try {
            if (CommonMethod.isNull(strEntrustCompany)) {
                throw new BusinessException("fail,参数strEntrustCompany不能为空");
            }
            entrustCompanyService.updateEntrustCompany(getColl(strEntrustCompany), userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("findEntrustCompany.action")
    @ResponseBody
    public List<EntrustCompany> findEntrustCompany(String strEntrustCompanyId, String strEntrustCompanyName, String strEntrustCompanyNo) {
        List<EntrustCompany> ecList = entrustCompanyService.findEntrustCompany(strEntrustCompanyId, strEntrustCompanyName, strEntrustCompanyNo);
        return ecList;
    }

    @RequestMapping("saveEntrustAdvanceMoney.action")
    @ResponseBody
    public String saveEntrustAdvanceMoney(String strEntrustCompanyId, String strAdvanceMoney, String userId) {
        try {
            if (CommonMethod.isNull(strEntrustCompanyId) || CommonMethod.isNull(strAdvanceMoney)) {
                throw new BusinessException("fail,参数strEntrustCompanyId或者strAdvanceMoney不能为空");
            }
            entrustCompanyService.saveEntrustAdvanceMoney(strEntrustCompanyId, strAdvanceMoney, userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("findEntrustAdvanceMoney.action")
    @ResponseBody
    public List<AdvanceMoneyPage> findEntrustAdvanceMoney(String strEntrustCompanyId) {
        if (CommonMethod.isNull(strEntrustCompanyId)) {
            throw new BusinessException("fail,参数strEntrustCompanyId不能为空");
        }
        List<AdvanceMoneyPage> amPageList = entrustCompanyService.findEntrustAdvanceMoney(strEntrustCompanyId);
        return amPageList;
    }

    /*public String getStrEntrustCompany() {
        return strEntrustCompany;
    }

    public void setStrEntrustCompany(String strEntrustCompany) {
        this.strEntrustCompany = strEntrustCompany;
    }

    public String getStrEntrustCompanyId() {
        return strEntrustCompanyId;
    }

    public void setStrEntrustCompanyId(String strEntrustCompanyId) {
        this.strEntrustCompanyId = strEntrustCompanyId;
    }

    public String getStrEntrustCompanyName() {
        return strEntrustCompanyName;
    }

    public void setStrEntrustCompanyName(String strEntrustCompanyName) {
        this.strEntrustCompanyName = strEntrustCompanyName;
    }

    public String getStrEntrustCompanyNo() {
        return strEntrustCompanyNo;
    }

    public void setStrEntrustCompanyNo(String strEntrustCompanyNo) {
        this.strEntrustCompanyNo = strEntrustCompanyNo;
    }

    public String getStrAdvanceMoney() {
        return strAdvanceMoney;
    }

    public void setStrAdvanceMoney(String strAdvanceMoney) {
        this.strAdvanceMoney = strAdvanceMoney;
    }*/

}
