package com.controller.common;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.model.BaseDepartment;
import com.service.common.BaseDepartmentService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * 部门
 *
 * @author STKJ-12
 */

@Controller
public class BaseDepartmentAction extends QueryAction<BaseDepartment> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String strBaseDepartment = "";
    //是否为经营部门参数
    private String strIsManagement = "";

    @Autowired
    private BaseDepartmentService baseDepartmentService;

	/*@Action(value = "/baseDepartmentAction", results = {
			@Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/

    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }


    public void saveDepartment() {
        try {
            if (CommonMethod.isNull(strBaseDepartment)) {
                jsonPrint("fail,参数strBaseDepartment不能为空");
                return;
            }
            baseDepartmentService.saveDepartment(getColl(strBaseDepartment), getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public void updateDepartment() {
        try {
            if (CommonMethod.isNull(strBaseDepartment)) {
                jsonPrint("fail,参数strBaseDepartment不能为空");
                return;
            }
            baseDepartmentService.updateDepartment(getColl(strBaseDepartment), getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public void findDepartment() {
        List<BaseDepartment> departmentList = baseDepartmentService.findDepartment(strIsManagement);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(departmentList, jsonConfig);
        jsonPrint(jsonArr);
    }

    public String getStrBaseDepartment() {
        return strBaseDepartment;
    }

    public void setStrBaseDepartment(String strBaseDepartment) {
        this.strBaseDepartment = strBaseDepartment;
    }

    public String getStrIsManagement() {
        return strIsManagement;
    }

    public void setStrIsManagement(String strIsManagement) {
        this.strIsManagement = strIsManagement;
    }
}
