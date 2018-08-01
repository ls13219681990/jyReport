package com.controller.sys;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.model.SysFunction;
import com.service.sys.SysFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("sysFunctionAction")
public class SysFunctionController extends QueryAction<SysFunction> {

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

    @RequestMapping("saveFunction.action")
    @ResponseBody
    public String saveFunction(String strFunction) {
        try {
            if (CommonMethod.isNull(strFunction)) {
                throw new BusinessException("fail,参数strFunction不能为空！", "");
            }
            sysFunctionService.saveFunction(getColl(strFunction));
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("updateFunction.action")
    @ResponseBody
    public String updateFunction(String strFunction) {
        try {
            if (CommonMethod.isNull(strFunction)) {
                throw new BusinessException("fail,参数strFunction不能为空！", "");
            }
            sysFunctionService.updateFunction(getColl(strFunction));
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("findFunction.action")
    @ResponseBody
    public List<SysFunction> findFunction() {
        List<SysFunction> sysFunctionList = sysFunctionService.findFunction();
        return sysFunctionList;
    }

    /**
     * 获取有效的功能数据
     */
    @RequestMapping("findFunctionValid.action")
    @ResponseBody
    public List<SysFunction> findFunctionValid() {
        List<SysFunction> sysFunctionList = sysFunctionService.findFunctionValid();
        return sysFunctionList;
    }

    public String getStrFunction() {
        return strFunction;
    }

    public void setStrFunction(String strFunction) {
        this.strFunction = strFunction;
    }
}
