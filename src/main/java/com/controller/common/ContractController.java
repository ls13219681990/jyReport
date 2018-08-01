package com.controller.common;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.TimestampMorpher;
import com.dao.page.ContractPage;
import com.model.Agreement;
import com.service.common.ContractService;
import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("contractAction")
public class ContractController extends QueryAction<Agreement> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

   /* private String strContract = "";

    private String strSort = "";//排序规则  0：升序   1：降序*/

    @Autowired
    private ContractService contractService;


    /*@Action(value = "/contractAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    public String jompSys() {
        return "/jsp/sys/sysCode";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("saveContract.action")
    @ResponseBody
    public String saveContract(String strContract, String userId) {
        try {
            if (CommonMethod.isNull(strContract)) {
                throw new BusinessException("fail,参数strContract不能为空");
            }
            strContract = strContract.replace("OO", "#");
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
            Collection<ContractPage> collPage = JSONArray.toCollection(JSONArray.fromObject(strContract), ContractPage.class);
            contractService.saveContract(collPage, userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "ture";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("updateContract.action")
    @ResponseBody
    public String updateContract(String strContract, String userId) {
        try {
            if (CommonMethod.isNull(strContract)) {
                throw new BusinessException("fail,参数strContract不能为空");
            }
            strContract = strContract.replace("OO", "#");
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
            Collection<ContractPage> collPage = JSONArray.toCollection(JSONArray.fromObject(strContract), ContractPage.class);
            contractService.updateContract(collPage, userId);
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
     * 根据合同ID查询统计合同报告
     */
    @RequestMapping("findContract.action")
    @ResponseBody
    public List<ContractPage> findContract(String strContract, String strSort) {
        ContractPage ca = new ContractPage();
        if (!CommonMethod.isNull(strContract)) {
            ca = (ContractPage) toBean(strContract, ContractPage.class);
        }
        List<ContractPage> capList = contractService.findCaList(ca, strSort);
        return capList;
    }

    /**
     * 根据合同ID查询合同报告不统计
     */
    @RequestMapping("findContractById.action")
    @ResponseBody
    public List<ContractPage> findContractById(String strContract, String strSort) {
        ContractPage ca = new ContractPage();
        if (!CommonMethod.isNull(strContract)) {
            strContract = strContract.replace("OO", "#");
            strContract = strContract.replace("PLUS", "+");
            ca = (ContractPage) toBean(strContract, ContractPage.class);
        }
        List<ContractPage> capList = contractService.findCaListById(ca, strSort);
        return capList;
    }

    /*public String getStrContract() {
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
    }*/

}
