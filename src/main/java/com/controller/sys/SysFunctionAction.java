package com.controller.sys;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.model.SysFunction;
import com.service.sys.SysFunctionService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SysFunctionAction extends QueryAction<SysFunction> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String strFunction = "";

    @Autowired
    private SysFunctionService sysFunctionService;

    /*@Action(value = "/sysFunctionAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    @RequestMapping(value = "/abaseDepartmentAction")
    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    public void saveFunction() {
        try {
            if (CommonMethod.isNull(strFunction)) {
                jsonPrint("fail,参数strFunction不能为空");
                return;
            }
            sysFunctionService.saveFunction(getColl(strFunction));
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public void updateFunction() {
        try {
            if (CommonMethod.isNull(strFunction)) {
                jsonPrint("fail,参数strFunction不能为空");
                return;
            }
            sysFunctionService.updateFunction(getColl(strFunction));
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public void findFunction() {
        List<SysFunction> sysFunctionList = sysFunctionService.findFunction();
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(sysFunctionList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 获取有效的功能数据
     */
    public void findFunctionValid() {
        List<SysFunction> sysFunctionList = sysFunctionService.findFunctionValid();
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(sysFunctionList, jsonConfig);
        jsonPrint(jsonArr);
    }

    public String getStrFunction() {
        return strFunction;
    }

    public void setStrFunction(String strFunction) {
        this.strFunction = strFunction;
    }
}
