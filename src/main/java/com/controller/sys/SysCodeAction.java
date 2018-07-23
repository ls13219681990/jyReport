package com.controller.sys;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.model.SysCode;
import com.service.sys.SysCodeService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SysCodeAction extends QueryAction<SysCode> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String strCode = "";

    private String codeRelation = "";

    @Autowired
    private SysCodeService sysCodeService;

    /*@Action(value = "/sysCodeAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    @RequestMapping(value = "/baseDepartmentAction")
    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    public void saveCode() {
        try {
            if (CommonMethod.isNull(strCode)) {
                jsonPrint("fail,参数strCode不能为空");
                return;
            }
//			SysCode s = (SysCode)toBean(strCode,SysCode.class);
            sysCodeService.saveCode(getColl(strCode));
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public void updateCode() {
        try {
            if (CommonMethod.isNull(strCode)) {
                jsonPrint("fail,参数strCode不能为空");
                return;
            }
            sysCodeService.updateCode(getColl(strCode));
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public void findcode() {
        List<SysCode> sysCodeList = sysCodeService.findSysCodes();
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(sysCodeList, jsonConfig);
        jsonPrint(jsonArr);
    }

    public void findPitchOncode() {
        if (CommonMethod.isNull(codeRelation)) {
            jsonPrint("fail,参数codeRelation不能为空");
            return;
        }
        List<SysCode> sysCodeList = sysCodeService.findPitchOncode(codeRelation);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(sysCodeList, jsonConfig);
        jsonPrint(jsonArr);
    }

    public String getStrCode() {
        return strCode;
    }

    public void setStrCode(String strCode) {
        this.strCode = strCode;
    }

    public String getCodeRelation() {
        return codeRelation;
    }

    public void setCodeRelation(String codeRelation) {
        this.codeRelation = codeRelation;
    }

}
