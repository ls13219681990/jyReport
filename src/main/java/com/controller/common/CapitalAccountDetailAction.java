package com.controller.common;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.common.jsonProcessor.TimestampMorpher;
import com.dao.page.CapitalAccountDetailPage;
import com.model.CapitalAccountDetail;
import com.service.common.CapitalAccountDetailService;
import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.List;

@Controller
public class CapitalAccountDetailAction extends QueryAction<CapitalAccountDetail> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String strCADetail = "";

    private String strCapitalAccountId = "";

    private String contractId;

    @Autowired
    private CapitalAccountDetailService capitalAccountDetailService;


    /*@Action(value = "/capitalAccountDetailAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    public String jompSys() {
        return "/jsp/sys/sysCode";
    }

    /***
     * 导入资金账号明细表
     */
    @SuppressWarnings("unchecked")
    public void saveCADetailTable() {
        try {
            if (CommonMethod.isNull(strCADetail)) {
                jsonPrint("fail,参数strCADetail不能为空");
                return;
            }
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
            Collection<CapitalAccountDetailPage> collPage = JSONArray.toCollection(JSONArray.fromObject(strCADetail), CapitalAccountDetailPage.class);
            capitalAccountDetailService.saveCADetailTable(collPage, getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public void findCADetailList() {
        if (CommonMethod.isNull(strCapitalAccountId)) {
            jsonPrint("fail,参数strCapitalAccountId不能为空");
            return;
        }
        List<CapitalAccountDetailPage> cadPageList = capitalAccountDetailService.findCADetailList(strCapitalAccountId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(cadPageList, jsonConfig);
        jsonPrint(jsonArr);
    }

    public void findCADetailListAll() {
        if (CommonMethod.isNull(contractId)) {
            jsonPrint("fail,参数contractId不能为空");
            return;
        }
        List<CapitalAccountDetailPage> findCASampleDetailsAll = capitalAccountDetailService.findCASampleDetailsAll(contractId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(findCASampleDetailsAll, jsonConfig);
        jsonPrint(jsonArr);
    }

    public void saveCADetailListAll() {
        if (CommonMethod.isNull(contractId)) {
            jsonPrint("fail,参数contractId不能为空");
            return;
        }
        List<CapitalAccountDetailPage> findCASampleDetailsAll = capitalAccountDetailService.findCASampleDetailsAll(contractId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(findCASampleDetailsAll, jsonConfig);
        jsonPrint(jsonArr);
    }

    public String getStrCADetail() {
        return strCADetail;
    }


    public void setStrCADetail(String strCADetail) {
        this.strCADetail = strCADetail;
    }

    public String getStrCapitalAccountId() {
        return strCapitalAccountId;
    }

    public void setStrCapitalAccountId(String strCapitalAccountId) {
        this.strCapitalAccountId = strCapitalAccountId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

}
