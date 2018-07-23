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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author STKJ-12
 * @category 委托
 */
@Controller
public class EntrustDetailsAction extends QueryAction<EntrustDetails> {

    /**
     *
     */
    private final static HttpServletRequest REQUEST = null;

    private static final long serialVersionUID = 1L;
    //部门ID
    private String strDepartmentId = "";
    //样品ID
    private String strSampleId = "";
    //参数ID
    private String strTParameterId = "";
    //委托明细（上委托）
    private String strEDetails = "";
    //委托ID
    private String streEntrustId = "";


    @Autowired
    private EntrustDetailService entrustDetailService;

    /*@Action(value = "/entrustDetailAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    @RequestMapping(value = "/baseDepartmentAction")
    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    public void findIsEntrust() {
//		List<EntrustDetails> edList = entrustDetailService.findIsEntrust(strDepartmentId, strSampleId,strTParameterId);
//		if(edList!=null && edList.size()>0){//已经办理了委托
//			jsonPrint("false");
//		}else{
        jsonPrint("true");
//		}
    }

    @SuppressWarnings({"rawtypes", "unused"})
    public void saveEDetailReport() {
        try {

            Map parMap = REQUEST.getParameterMap();
            Object uerObj = parMap.get("userId");
            Object eDetailsObj = parMap.get("strEDetails");

            if (CommonMethod.isNull(strEDetails)) {
                jsonPrint("fail,参数strEDetails不能为空");
                return;
            }

            Map<String, Class> classMap = new HashMap<String, Class>();
            classMap.put("edReport", EntrustDetailReportPage.class);
            classMap.put("enPaInfoPageList", EntrustParameterInfoPage.class);

            EntrustSavePage esPage = new EntrustSavePage();
            JSONObject pJsonObject = JSONObject.fromObject(strEDetails);
            esPage = (EntrustSavePage) JSONObject.toBean(pJsonObject, EntrustSavePage.class, classMap);

            entrustDetailService.saveEDetailReport(esPage, getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @SuppressWarnings({"rawtypes", "unused"})
    public void updateEDetailReport() {
        try {
            //HttpServletRequest request = ServletActionContext.getRequest();
            Map parMap = REQUEST.getParameterMap();
            Object uerObj = parMap.get("userId");
            Object eDetailsObj = parMap.get("strEDetails");

            if (CommonMethod.isNull(strEDetails)) {
                jsonPrint("fail,参数strEDetails不能为空");
                return;
            }
            Map<String, Class> classMap = new HashMap<String, Class>();
            classMap.put("edReport", EntrustDetailReportPage.class);
            classMap.put("enPaInfoPageList", EntrustParameterInfoPage.class);

            EntrustSavePage esPage = new EntrustSavePage();
            JSONObject pJsonObject = JSONObject.fromObject(strEDetails);
            esPage = (EntrustSavePage) JSONObject.toBean(pJsonObject, EntrustSavePage.class, classMap);

            entrustDetailService.updateEDetailReport(esPage, getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public String getStrDepartmentId() {
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
    }

}
