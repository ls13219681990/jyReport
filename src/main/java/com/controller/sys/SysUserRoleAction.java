package com.controller.sys;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.model.SysUserRole;
import com.service.sys.SysUserRoleService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SysUserRoleAction extends QueryAction<SysUserRole> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String strUserRole = "";
    //角色ID
    private String strRoleId = "";
    //用户ID
    private String strUserId = "";

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /*@Action(value = "/sysUserRoleAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    @RequestMapping(value = "/aaaaaaabaseDepartmentAction")
    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    public void saveSysUserRole() {
        try {
            if (CommonMethod.isNull(strUserRole) || CommonMethod.isNull(strUserId)) {
                jsonPrint("fail,参数不能为空");
                return;
            }
            sysUserRoleService.saveSysUserRole(getColl(strUserRole), strUserId);
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public void findRoleByUser() {
        if (CommonMethod.isNull(strUserId)) {
            jsonPrint("fail,参数strUserId不能为空");
            return;
        }
        List<SysUserRole> sysRoleFunctionList = sysUserRoleService.findRoleByUser(strUserId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(sysRoleFunctionList, jsonConfig);
        jsonPrint(jsonArr);
    }

    public void findRoleUsed() {
        if (CommonMethod.isNull(strRoleId)) {
            jsonPrint("fail,参数strRoleId不能为空");
            return;
        }
        List<SysUserRole> sysRoleFunctionList = sysUserRoleService.findRoleUsed(strRoleId);
        if (sysRoleFunctionList != null && sysRoleFunctionList.size() > 0) {
            jsonPrint("true");
        } else {
            jsonPrint("fail");
        }
    }

    public String getStrUserRole() {
        return strUserRole;
    }

    public void setStrUserRole(String strUserRole) {
        this.strUserRole = strUserRole;
    }

    public String getStrRoleId() {
        return strRoleId;
    }

    public void setStrRoleId(String strRoleId) {
        this.strRoleId = strRoleId;
    }

    public String getStrUserId() {
        return strUserId;
    }

    public void setStrUserId(String strUserId) {
        this.strUserId = strUserId;
    }
}
