package com.controller.common;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.dao.page.AdvanceMoneyPage;
import com.model.EntrustCompany;
import com.service.common.EntrustCompanyService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("EntrustCompanyAction")
public class EntrustCompanyController extends QueryAction<EntrustCompany> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String strEntrustCompany = "";

    private String strEntrustCompanyId = "";//单位ID

    private String strEntrustCompanyName = "";//单位名称

    private String strEntrustCompanyNo = "";//单位编号

    private String strAdvanceMoney = "";//预收款充值金额

    @Autowired
    private EntrustCompanyService entrustCompanyService;

    /*@Action(value = "/entrustCompanyAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    public String jompSys() {
        return "/jsp/sys/sysCode";
    }

    @RequestMapping("saveEntrustCompany.action")
    public void saveEntrustCompany() {
        try {
            if (CommonMethod.isNull(strEntrustCompany)) {
                jsonPrint("fail,参数strEntrustCompany不能为空");
                return;
            }
            String entrustCompanyId = entrustCompanyService.saveEntrustCompany(getColl(strEntrustCompany), getUserId());
            jsonPrint("true:" + entrustCompanyId);
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @RequestMapping("updateEntrustCompany.action")
    public void updateEntrustCompany() {
        try {
            if (CommonMethod.isNull(strEntrustCompany)) {
                jsonPrint("fail,参数strEntrustCompany不能为空");
                return;
            }
            entrustCompanyService.updateEntrustCompany(getColl(strEntrustCompany), getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @RequestMapping("findEntrustCompany.action")
    public void findEntrustCompany() {
        List<EntrustCompany> ecList = entrustCompanyService.findEntrustCompany(strEntrustCompanyId, strEntrustCompanyName, strEntrustCompanyNo);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(ecList, jsonConfig);
        jsonPrint(jsonArr);
    }

    @RequestMapping("saveEntrustAdvanceMoney.action")
    public void saveEntrustAdvanceMoney() {
        try {
            if (CommonMethod.isNull(strEntrustCompanyId) || CommonMethod.isNull(strAdvanceMoney)) {
                jsonPrint("fail,参数strEntrustCompanyId或者strAdvanceMoney不能为空");
                return;
            }
            entrustCompanyService.saveEntrustAdvanceMoney(strEntrustCompanyId, strAdvanceMoney, getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @RequestMapping("findEntrustAdvanceMoney.action")
    public void findEntrustAdvanceMoney() {
        if (CommonMethod.isNull(strEntrustCompanyId)) {
            jsonPrint("fail,参数strEntrustCompanyId不能为空");
            return;
        }
        List<AdvanceMoneyPage> amPageList = entrustCompanyService.findEntrustAdvanceMoney(strEntrustCompanyId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(amPageList, jsonConfig);
        jsonPrint(jsonArr);
    }

    public String getStrEntrustCompany() {
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
    }

}
