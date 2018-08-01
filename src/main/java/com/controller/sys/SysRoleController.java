package com.controller.sys;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.model.SysRole;
import com.service.sys.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("sysRoleAction")
public class SysRoleController extends QueryAction<SysRole> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /* private String strRole = "";*/

    @Autowired
    private SysRoleService sysRoleService;

    /*@Action(value = "/sysRoleAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    @RequestMapping(value = "/dbaseDepartmentAction")
    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    @RequestMapping("saveSysRole.action")
    @ResponseBody
    public String saveSysRole(String strRole, String userId) {
        try {
            if (CommonMethod.isNull(strRole)) {
                throw new BusinessException("fail,参数strRole不能为空！", "");
            }
            sysRoleService.saveSysRole(getColl(strRole), userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("updateSysRole.action")
    @ResponseBody
    public String updateSysRole(String strRole) {
        try {
            if (CommonMethod.isNull(strRole)) {
                throw new BusinessException("fail,参数strRole不能为空！", "");
            }
            sysRoleService.updateSysRole(getColl(strRole));
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("findSysRole.action")
    @ResponseBody
    public List<SysRole> findSysRole() {
        List<SysRole> sysRoleList = sysRoleService.findSysRole();
        return sysRoleList;
    }

    /**
     * 获取有效的角色数据
     */
    @RequestMapping("findSysRoleValid.action")
    @ResponseBody
    public List<SysRole> findSysRoleValid() {
        List<SysRole> sysRoleList = sysRoleService.findSysRoleValid();
        return sysRoleList;
    }

   /* public String getStrRole() {
        return strRole;
    }

    public void setStrRole(String strRole) {
        this.strRole = strRole;
    }
*/

}
