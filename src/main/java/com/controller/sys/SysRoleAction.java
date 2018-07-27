package com.controller.sys;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.model.SysRole;
import com.service.sys.SysRoleService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SysRoleAction extends QueryAction<SysRole> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String strRole = "";

    @Autowired
    private SysRoleService sysRoleService;

    /*@Action(value = "/sysRoleAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    @RequestMapping(value = "/dbaseDepartmentAction")
    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    public void saveSysRole() {
        try {
            if (CommonMethod.isNull(strRole)) {
                jsonPrint("fail,参数strRole不能为空");
                return;
            }
            sysRoleService.saveSysRole(getColl(strRole), getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public void updateSysRole() {
        try {
            if (CommonMethod.isNull(strRole)) {
                jsonPrint("fail,参数strRole不能为空");
                return;
            }
            sysRoleService.updateSysRole(getColl(strRole));
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public void findSysRole() {
        List<SysRole> sysRoleList = sysRoleService.findSysRole();
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(sysRoleList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 获取有效的角色数据
     */
    public void findSysRoleValid() {
        List<SysRole> sysRoleList = sysRoleService.findSysRoleValid();
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(sysRoleList, jsonConfig);
        jsonPrint(jsonArr);
    }

    public String getStrRole() {
        return strRole;
    }

    public void setStrRole(String strRole) {
        this.strRole = strRole;
    }


}
