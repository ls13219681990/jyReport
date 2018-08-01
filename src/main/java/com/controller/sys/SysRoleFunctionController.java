package com.controller.sys;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.model.SysRoleFunction;
import com.service.sys.SysRoleFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("sysRoleFunctionAction")
public class SysRoleFunctionController extends QueryAction<SysRoleFunction> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

   /* private String strRoleFunction = "";
    //角色ID
    private String strRoleId = "";
    //功能ID
    private String strFunctionId = "";*/

    @Autowired
    private SysRoleFunctionService sysRoleFunctionService;

    /*@Action(value = "/sysRoleFunctionAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    @RequestMapping(value = "/zxcxzcbaseDepartmentAction")
    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    @RequestMapping("saveSysRoleFunction.action")
    @ResponseBody
    public String saveSysRoleFunction(String strRoleFunction, String strRoleId) {
        try {
            if (CommonMethod.isNull(strRoleFunction) || CommonMethod.isNull(strRoleId)) {
                throw new BusinessException("fail,参数不能为空");
            }
            sysRoleFunctionService.saveSysRoleFunction(getColl(strRoleFunction), strRoleId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("findFunctionByRole.action")
    @ResponseBody
    public List<SysRoleFunction> findFunctionByRole(String strRoleId) {
        if (CommonMethod.isNull(strRoleId)) {
            throw new BusinessException("fail,参数strRoleId不能为空！", "");
        }
        List<SysRoleFunction> sysRoleFunctionList = sysRoleFunctionService.findFunctionByRole(strRoleId);
        return sysRoleFunctionList;
    }

    @RequestMapping("findFuncitonUsed.action")
    @ResponseBody
    public String findFuncitonUsed(String strFunctionId) {
        if (CommonMethod.isNull(strFunctionId)) {
            throw new BusinessException("fail,参数strFunctionId不能为空！", "");
        }
        List<SysRoleFunction> sysRoleFunctionList = sysRoleFunctionService.findFuncitonUsed(strFunctionId);
        List<String> re = new ArrayList<String>();
        if (sysRoleFunctionList != null && sysRoleFunctionList.size() > 0) {
            re.add("true");
        } else {
            re.add("fail");
        }
        return re.get(0).toString();
    }

 /*   public String getStrRoleFunction() {
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
    }*/
}
