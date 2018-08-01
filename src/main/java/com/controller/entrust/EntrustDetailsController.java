package com.controller.entrust;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.dao.page.EntrustDetailReportPage;
import com.dao.page.EntrustParameterInfoPage;
import com.dao.page.EntrustSavePage;
import com.model.EntrustDetails;
import com.service.entrust.EntrustDetailService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author STKJ-12
 * @category 委托
 */
@Controller
@RequestMapping("entrustDetailAction")
public class EntrustDetailsController extends QueryAction<EntrustDetails> {

    /**
     *
     */
    private final static HttpServletRequest REQUEST = null;

    private static final long serialVersionUID = 1L;
    /*//部门ID
    private String strDepartmentId = "";
    //样品ID
    private String strSampleId = "";
    //参数ID
    private String strTParameterId = "";
    //委托明细（上委托）
    private String strEDetails = "";
    //委托ID
    private String streEntrustId = "";*/


    @Autowired
    private EntrustDetailService entrustDetailService;

    /*@Action(value = "/entrustDetailAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    @RequestMapping(value = "/ccccbaseDepartmentAction")
    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    @RequestMapping("findIsEntrust.action")
    @ResponseBody
    public String findIsEntrust(String strDepartmentId, String strSampleId, String strTParameterId) {
        List<EntrustDetails> edList = entrustDetailService.findIsEntrust(strDepartmentId, strSampleId, strTParameterId);
        List<String> reulst = new ArrayList<String>();
        if (edList != null && edList.size() > 0) {//已经办理了委托
            reulst.add("flase");
            //jsonPrint("false");
        } else {
            reulst.add("true");
        }
        return reulst.get(0).toString();
    }

    @SuppressWarnings({"rawtypes", "unused"})
    @RequestMapping("saveEDetailReport.action")
    @ResponseBody
    public String saveEDetailReport(String userId, String strEDetails) {
        try {

            Map parMap = REQUEST.getParameterMap();
            Object uerObj = parMap.get("userId");
            Object eDetailsObj = parMap.get("strEDetails");

            if (CommonMethod.isNull(strEDetails)) {
                throw new BusinessException("fail,参数strEDetails不能为空！", "");
            }

            Map<String, Class> classMap = new HashMap<String, Class>();
            classMap.put("edReport", EntrustDetailReportPage.class);
            classMap.put("enPaInfoPageList", EntrustParameterInfoPage.class);

            EntrustSavePage esPage = new EntrustSavePage();
            JSONObject pJsonObject = JSONObject.fromObject(strEDetails);
            esPage = (EntrustSavePage) JSONObject.toBean(pJsonObject, EntrustSavePage.class, classMap);

            entrustDetailService.saveEDetailReport(esPage, userId);

        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @SuppressWarnings({"rawtypes", "unused"})
    @RequestMapping("updateEDetailReport.action")
    @ResponseBody
    public String updateEDetailReport(String userId, String strEDetails) {
        try {
            //HttpServletRequest request = ServletActionContext.getRequest();
            Map parMap = REQUEST.getParameterMap();
            Object uerObj = parMap.get("userId");
            Object eDetailsObj = parMap.get("strEDetails");

            if (CommonMethod.isNull(strEDetails)) {
                throw new BusinessException("fail,参数strEDetails不能为空！", "");
            }
            Map<String, Class> classMap = new HashMap<String, Class>();
            classMap.put("edReport", EntrustDetailReportPage.class);
            classMap.put("enPaInfoPageList", EntrustParameterInfoPage.class);

            EntrustSavePage esPage = new EntrustSavePage();
            JSONObject pJsonObject = JSONObject.fromObject(strEDetails);
            esPage = (EntrustSavePage) JSONObject.toBean(pJsonObject, EntrustSavePage.class, classMap);

            entrustDetailService.updateEDetailReport(esPage, userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

   /* public String getStrDepartmentId() {
        return strDepartmentId;
    }

    public void setStrDepartmentId(String strDepartmentId) {
        this.strDepartmentId = strDepartmentId;
    }

    public String getStrSampleId() {
        return strSampleId;
    }

    public void setStrSampleId(String strSampleId) {
        this.strSampleId = strSampleId;
    }

    public String getStrTParameterId() {
        return strTParameterId;
    }

    public void setStrTParameterId(String strTParameterId) {
        this.strTParameterId = strTParameterId;
    }

    public String getStrEDetails() {
        return strEDetails;
    }

    public void setStrEDetails(String strEDetails) {
        this.strEDetails = strEDetails;
    }

    public String getStreEntrustId() {
        return streEntrustId;
    }

    public void setStreEntrustId(String streEntrustId) {
        this.streEntrustId = streEntrustId;
    }*/

}
