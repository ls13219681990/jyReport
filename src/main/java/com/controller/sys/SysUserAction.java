package com.controller.sys;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.dao.page.UserRolePage;
import com.model.SysUser;
import com.service.sys.SysUserService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SysUserAction extends QueryAction<SysUser> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String strUser = "";

    private String oldPassword = "";

    private String newPassword = "";

    @Autowired
    private SysUserService sysUserService;

    /*@Action(value = "/sysUserAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    @RequestMapping(value = "/asdbaseDepartmentAction")
    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    public void saveSysUser() {
        try {
            if (CommonMethod.isNull(strUser)) {
                jsonPrint("fail,参数strUser不能为空");
                return;
            }
            sysUserService.saveSysUser(getColl(strUser), getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public void updateSysUser() {
        try {
            if (CommonMethod.isNull(strUser)) {
                jsonPrint("fail,参数strUser不能为空");
                return;
            }
            sysUserService.updateSysUser(getColl(strUser), getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public void updateSysPassword() {
        try {
//			if(CommonMethod.isNull(oldPassword)){
//				jsonPrint("fail,参数oldPassword不能为空");
//				return;
//			}
//			if(CommonMethod.isNull(newPassword)){
//				jsonPrint("fail,参数newPassword不能为空");
//				return;
//			}
            sysUserService.updateSysPassword(oldPassword, newPassword, getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public void resetSysPassword() {
        try {
            if (CommonMethod.isNull(getUserId())) {
                jsonPrint("fail,参数userId不能为空");
                return;
            }
            sysUserService.resetSysPassword(getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public void findSysUser() {
        List<UserRolePage> sysUserList = sysUserService.findSysUser();
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(sysUserList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 获取有效的用户数据
     */
    public void findSysUserValid() {
        List<UserRolePage> sysUserList = sysUserService.findSysUserValid();
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(sysUserList, jsonConfig);
        jsonPrint(jsonArr);
    }

    public String getStrUser() {
        return strUser;
    }

    public void setStrUser(String strUser) {
        this.strUser = strUser;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
