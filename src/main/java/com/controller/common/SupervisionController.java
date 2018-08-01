package com.controller.common;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.model.SupervisionUnit;
import com.service.common.SupervisionUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("supervisionUnitAction")
public class SupervisionController extends QueryAction<SupervisionUnit> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

   /* private String strSupervisionUnit = "";

    private String strSupervisionUnitId = "";

    private String strSupervisionUnitName = "";

    private String strWitnesses = "";//见证人*/


    @Autowired
    private SupervisionUnitService supervisionUnitService;

    /*@Action(value = "/supervisionUnitAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    public String jompSys() {
        return "/jsp/sys/sysCode";
    }

    @RequestMapping("saveSupervisionUnit.action")
    @ResponseBody
    public String saveSupervisionUnit(String strSupervisionUnit, String userId) {
        String supervisionUnitId = null;
        try {
            if (CommonMethod.isNull(strSupervisionUnit)) {
                throw new BusinessException("fail,参数strSupervisionUnit不能为空！", "");
            }
            supervisionUnitId = supervisionUnitService.saveSupervisionUnit(getColl(strSupervisionUnit), userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true:" + supervisionUnitId;
    }

    @RequestMapping("updateSupervisionUnit.action")
    @ResponseBody
    public String updateSupervisionUnit(String strSupervisionUnit, String userId) {
        try {
            if (CommonMethod.isNull(strSupervisionUnit)) {
                throw new BusinessException("fail,参数strSupervisionUnit不能为空！", "");
            }
            supervisionUnitService.updateSupervisionUnit(getColl(strSupervisionUnit), userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("findSupervisionUnit.action")
    @ResponseBody
    public List<SupervisionUnit> findSupervisionUnit(String strSupervisionUnitId, String strSupervisionUnitName, String strWitnesses) {
        List<SupervisionUnit> suList = supervisionUnitService.findSupervisionUnit(strSupervisionUnitId, strSupervisionUnitName, strWitnesses);
        return suList;
    }

    @RequestMapping("findSupervisionUnitName.action")
    @ResponseBody
    public List<SupervisionUnit> findSupervisionUnitName(String strSupervisionUnitId) {
        if (CommonMethod.isNull(strSupervisionUnitId)) {
            throw new BusinessException("fail,参数strSupervisionUnitId不能为空！", "");
        }
        List<SupervisionUnit> suList = supervisionUnitService.findSupervisionUnitName(strSupervisionUnitId);
        return suList;
    }

    /*public String getStrSupervisionUnit() {
        return strSupervisionUnit;
    }

    public void setStrSupervisionUnit(String strSupervisionUnit) {
        this.strSupervisionUnit = strSupervisionUnit;
    }

    public String getStrSupervisionUnitId() {
        return strSupervisionUnitId;
    }

    public void setStrSupervisionUnitId(String strSupervisionUnitId) {
        this.strSupervisionUnitId = strSupervisionUnitId;
    }

    public String getStrSupervisionUnitName() {
        return strSupervisionUnitName;
    }

    public void setStrSupervisionUnitName(String strSupervisionUnitName) {
        this.strSupervisionUnitName = strSupervisionUnitName;
    }

    public String getStrWitnesses() {
        return strWitnesses;
    }

    public void setStrWitnesses(String strWitnesses) {
        this.strWitnesses = strWitnesses;
    }*/

}
