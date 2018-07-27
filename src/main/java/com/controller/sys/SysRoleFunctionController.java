package com.controller.sys;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.model.SysRoleFunction;
import com.service.sys.SysRoleFunctionService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("SysRoleFunctionAction")
public class SysRoleFunctionController extends QueryAction<SysRoleFunction> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String strRoleFunction = "";
    //角色ID
    private String strRoleId = "";
    //功能ID
    private String strFunctionId = "";

    @Autowired
    private SysRoleFunctionService sysRoleFunctionService;

    /*@Action(value = "/sysRoleFunctionAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    @RequestMapping(value = "/zxcxzcbaseDepartmentAction")
    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    @RequestMapping("saveSysRoleFunction.action")
    public void saveSysRoleFunction() {
        try {
            if (CommonMethod.isNull(strRoleFunction) || CommonMethod.isNull(strRoleId)) {
                jsonPrint("fail,参数不能为空");
                return;
            }
            sysRoleFunctionService.saveSysRoleFunction(getColl(strRoleFunction), strRoleId);
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @RequestMapping("findFunctionByRole.action")
    public void findFunctionByRole() {
        if (CommonMethod.isNull(strRoleId)) {
            jsonPrint("fail,参数strRoleId不能为空");
            return;
        }
        List<SysRoleFunction> sysRoleFunctionList = sysRoleFunctionService.findFunctionByRole(strRoleId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(sysRoleFunctionList, jsonConfig);
        jsonPrint(jsonArr);
    }

    @RequestMapping("findFuncitonUsed.action")
    public void findFuncitonUsed() {
        if (CommonMethod.isNull(strFunctionId)) {
            jsonPrint("fail,参数strFunctionId不能为空");
            return;
        }
        List<SysRoleFunction> sysRoleFunctionList = sysRoleFunctionService.findFuncitonUsed(strFunctionId);
        if (sysRoleFunctionList != null && sysRoleFunctionList.size() > 0) {
            jsonPrint("true");
        } else {
            jsonPrint("fail");
        }
    }

    public String getStrRoleFunction() {
        return strRoleFunction;
    }

    public void setStrRoleFunction(String strRoleFunction) {
        this.strRoleFunction = strRoleFunction;
    }

    public String getStrRoleId() {
        return strRoleId;
    }

    public void setStrRoleId(String strRoleId) {
        this.strRoleId = strRoleId;
    }

    public String getStrFunctionId() {
        return strFunctionId;
    }

    public void setStrFunctionId(String strFunctionId) {
        this.strFunctionId = strFunctionId;
    }
}
