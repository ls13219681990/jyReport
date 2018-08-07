package com.controller.sys;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.dao.page.UserRolePage;
import com.model.SysUser;
import com.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("sysUserAction")
public class SysUserController extends QueryAction<SysUser> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

  /*  private String strUser = "";

    private String oldPassword = "";

    private String newPassword = "";*/

    @Autowired
    private SysUserService sysUserService;

    /*@Action(value = "/sysUserAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    @RequestMapping(value = "/asdbaseDepartmentAction")
    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    @RequestMapping("saveSysUser.action")
    @ResponseBody
    public String saveSysUser(String strUser, String userId) {
        try {
            if (CommonMethod.isNull(strUser)) {
                throw new BusinessException("fail,参数strUser不能为空！", "");
            }
            sysUserService.saveSysUser(getColl(strUser), userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("updateSysUser.action")
    @ResponseBody
    public String updateSysUser(String strUser, String userId) {
        try {
            if (CommonMethod.isNull(strUser)) {
                throw new BusinessException("fail,参数strUser不能为空！", "");
            }
            sysUserService.updateSysUser(getColl(strUser), userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("updateSysPassword.action")
    @ResponseBody
    public String updateSysPassword(String oldPassword, String newPassword, String userId) {
        try {
//			if(CommonMethod.isNull(oldPassword)){
//				jsonPrint("fail,参数oldPassword不能为空");
//				return;
//			}
//			if(CommonMethod.isNull(newPassword)){
//				jsonPrint("fail,参数newPassword不能为空");
//				return;
//			}
            sysUserService.updateSysPassword(oldPassword, newPassword, userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("resetSysPassword.action")
    @ResponseBody
    public String resetSysPassword(String userId) {
        try {
            if (CommonMethod.isNull(userId)) {
                throw new BusinessException("fail,参数userId不能为空！", "");
            }
            sysUserService.resetSysPassword(userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("findSysUser.action")
    @ResponseBody
    public List<UserRolePage> findSysUser() {
        List<UserRolePage> sysUserList = sysUserService.findSysUser();
        return sysUserList;
    }

    /**
     * 获取有效的用户数据
     */
    @RequestMapping("findSysUserValid.action")
    @ResponseBody
    public List<UserRolePage> findSysUserValid() {
        List<UserRolePage> sysUserList = sysUserService.findSysUserValid();
        return sysUserList;
    }

    @RequestMapping("test")
    @ResponseBody
    public List<SysUser> find() {
        List<SysUser> all = sysUserService.findAll();
        return all;
    }





/*    public String getStrUser() {
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
    }*/
}
