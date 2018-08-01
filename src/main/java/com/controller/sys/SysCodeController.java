package com.controller.sys;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.model.SysCode;
import com.service.sys.SysCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sysCodeAction")
public class SysCodeController extends QueryAction<SysCode> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

   /* private String strCode = "";

    private String codeRelation = "";*/

    @Autowired
    private SysCodeService sysCodeService;

    /*@Action(value = "/sysCodeAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    @RequestMapping(value = "/aaabaseDepartmentAction")
    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    @RequestMapping("saveCode.action")
    @ResponseBody
    public String saveCode(String strCode) {
        try {
            if (CommonMethod.isNull(strCode)) {
                throw new BusinessException("fail,参数strCode不能为空！", "");
            }
//			SysCode s = (SysCode)toBean(strCode,SysCode.class);
            sysCodeService.saveCode(getColl(strCode));
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "ture";
    }

    @RequestMapping("updateCode.action")
    @ResponseBody
    public String updateCode(String strCode) {
        try {
            if (CommonMethod.isNull(strCode)) {
                throw new BusinessException("fail,参数strCode不能为空！", "");
            }
            sysCodeService.updateCode(getColl(strCode));
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("findcode.action")
    @ResponseBody
    public CommonJsonConfig findcode() {
        List<SysCode> sysCodeList = sysCodeService.findSysCodes();
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        return jsonConfig;
    }

    @RequestMapping("findPitchOncode.action")
    @ResponseBody
    public List<SysCode> findPitchOncode(String codeRelation) {
        if (CommonMethod.isNull(codeRelation)) {
            throw new BusinessException("fail,参数codeRelation不能为空！", "");
        }
        List<SysCode> sysCodeList = sysCodeService.findPitchOncode(codeRelation);
        return sysCodeList;
    }

    /*public String getStrCode() {
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
    }*/

}
