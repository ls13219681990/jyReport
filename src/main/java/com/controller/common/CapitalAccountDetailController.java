package com.controller.common;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.TimestampMorpher;
import com.dao.page.CapitalAccountDetailPage;
import com.model.CapitalAccountDetail;
import com.service.common.CapitalAccountDetailService;
import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("capitalAccountDetailAction")
public class CapitalAccountDetailController extends QueryAction<CapitalAccountDetail> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*private String strCADetail = "";

    private String strCapitalAccountId = "";

    private String contractId;*/

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
    @RequestMapping("saveCADetailTable.action")
    @ResponseBody
    public String saveCADetailTable(String strCADetail, String userId) {
        try {
            if (CommonMethod.isNull(strCADetail)) {
                throw new BusinessException("fail,参数strCADetail不能为空");
            }
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
            Collection<CapitalAccountDetailPage> collPage = JSONArray.toCollection(JSONArray.fromObject(strCADetail), CapitalAccountDetailPage.class);
            capitalAccountDetailService.saveCADetailTable(collPage, userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("findCADetailList.action")
    @ResponseBody
    public List<CapitalAccountDetailPage> findCADetailList(String strCapitalAccountId) {
        if (CommonMethod.isNull(strCapitalAccountId)) {
            throw new BusinessException("fail,参数strCapitalAccountId不能为空");
        }
        List<CapitalAccountDetailPage> cadPageList = capitalAccountDetailService.findCADetailList(strCapitalAccountId);
        return cadPageList;
    }

    @RequestMapping("findCADetailListAll.action")
    @ResponseBody
    public List<CapitalAccountDetailPage> findCADetailListAll(String contractId) {
        if (CommonMethod.isNull(contractId)) {
            throw new BusinessException("fail,参数contractId不能为空");
        }
        List<CapitalAccountDetailPage> findCASampleDetailsAll = capitalAccountDetailService.findCASampleDetailsAll(contractId);
        return findCASampleDetailsAll;
    }

    @RequestMapping("saveCADetailListAll.action")
    @ResponseBody
    public List<CapitalAccountDetailPage> saveCADetailListAll(String contractId) {
        if (CommonMethod.isNull(contractId)) {
            throw new BusinessException("fail,参数contractId不能为空");
        }
        List<CapitalAccountDetailPage> findCASampleDetailsAll = capitalAccountDetailService.findCASampleDetailsAll(contractId);
        return findCASampleDetailsAll;
    }

    /*public String getStrCADetail() {
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
    }*/

}
