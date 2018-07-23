package com.controller.common;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.model.SupervisionUnit;
import com.service.common.SupervisionUnitService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SupervisionUnitAction extends QueryAction<SupervisionUnit> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String strSupervisionUnit = "";

    private String strSupervisionUnitId = "";

    private String strSupervisionUnitName = "";

    private String strWitnesses = "";//见证人


    @Autowired
    private SupervisionUnitService supervisionUnitService;

    /*@Action(value = "/supervisionUnitAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    public String jompSys() {
        return "/jsp/sys/sysCode";
    }

    public void saveSupervisionUnit() {
        try {
            if (CommonMethod.isNull(strSupervisionUnit)) {
                jsonPrint("fail,参数strSupervisionUnit不能为空");
                return;
            }
            String supervisionUnitId = supervisionUnitService.saveSupervisionUnit(getColl(strSupervisionUnit), getUserId());
            jsonPrint("true:" + supervisionUnitId);
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public void updateSupervisionUnit() {
        try {
            if (CommonMethod.isNull(strSupervisionUnit)) {
                jsonPrint("fail,参数strSupervisionUnit不能为空");
                return;
            }
            supervisionUnitService.updateSupervisionUnit(getColl(strSupervisionUnit), getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public void findSupervisionUnit() {
        List<SupervisionUnit> suList = supervisionUnitService.findSupervisionUnit(strSupervisionUnitId, strSupervisionUnitName, strWitnesses);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(suList, jsonConfig);
        jsonPrint(jsonArr);
    }

    public void findSupervisionUnitName() {
        if (CommonMethod.isNull(strSupervisionUnitId)) {
            jsonPrint("fail,参数strSupervisionUnitId不能为空");
            return;
        }
        List<SupervisionUnit> suList = supervisionUnitService.findSupervisionUnitName(strSupervisionUnitId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(suList, jsonConfig);
        jsonPrint(jsonArr);
    }

    public String getStrSupervisionUnit() {
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
    }

}
