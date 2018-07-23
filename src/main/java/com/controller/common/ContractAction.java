package com.controller.common;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.common.jsonProcessor.TimestampMorpher;
import com.dao.page.ContractPage;
import com.model.Agreement;
import com.service.common.ContractService;
import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.List;

@Controller
public class ContractAction extends QueryAction<Agreement> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String strContract = "";

    private String strSort = "";//排序规则  0：升序   1：降序

    @Autowired
    private ContractService contractService;


    /*@Action(value = "/contractAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    public String jompSys() {
        return "/jsp/sys/sysCode";
    }

    @SuppressWarnings("unchecked")
    public void saveContract() {
        try {
            if (CommonMethod.isNull(strContract)) {
                jsonPrint("fail,参数strContract不能为空");
                return;
            }
            strContract = strContract.replace("OO", "#");
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
            Collection<ContractPage> collPage = JSONArray.toCollection(JSONArray.fromObject(strContract), ContractPage.class);
            contractService.saveContract(collPage, getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void updateContract() {
        try {
            if (CommonMethod.isNull(strContract)) {
                jsonPrint("fail,参数strContract不能为空");
                return;
            }
            strContract = strContract.replace("OO", "#");
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
            Collection<ContractPage> collPage = JSONArray.toCollection(JSONArray.fromObject(strContract), ContractPage.class);
            contractService.updateContract(collPage, getUserId());
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
     * 根据合同ID查询统计合同报告
     */
    public void findContract() {
        ContractPage ca = new ContractPage();
        if (!CommonMethod.isNull(strContract)) {
            ca = (ContractPage) toBean(strContract, ContractPage.class);
        }
        List<ContractPage> capList = contractService.findCaList(ca, strSort);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(capList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 根据合同ID查询合同报告不统计
     */
    public void findContractById() {
        ContractPage ca = new ContractPage();
        if (!CommonMethod.isNull(strContract)) {
            strContract = strContract.replace("OO", "#");
            strContract = strContract.replace("PLUS", "+");
            ca = (ContractPage) toBean(strContract, ContractPage.class);
        }
        List<ContractPage> capList = contractService.findCaListById(ca, strSort);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(capList, jsonConfig);
        jsonPrint(jsonArr);
    }

    public String getStrContract() {
        return strContract;
    }

    public void setStrContract(String strContract) {
        this.strContract = strContract;
    }

    public String getStrSort() {
        return strSort;
    }

    public void setStrSort(String strSort) {
        this.strSort = strSort;
    }

}
