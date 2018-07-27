package com.controller.common;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.model.ProjectInfo;
import com.service.common.ProjectInfoService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("ProjectInfoAction")
public class ProjectInfoController extends QueryAction<ProjectInfo> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String strProjectInfo = "";

    private String strProjectId = "";

    private String strProjectName = "";

    @Autowired
    private ProjectInfoService projectInfoService;

    /*@Action(value = "/projectInfoAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    public String jompSys() {
        return "/jsp/sys/sysCode";
    }

    @RequestMapping("saveProjectInfo.action")
    public void saveProjectInfo() {
        try {
            if (CommonMethod.isNull(strProjectInfo)) {
                jsonPrint("fail,参数strProjectInfo不能为空");
                return;
            }
            strProjectInfo = strProjectInfo.replace("OO", "#");
            String projectId = projectInfoService.saveProjectInfo(getColl(strProjectInfo), getUserId());
            jsonPrint("true:" + projectId);
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @RequestMapping("updateProjectInfo.action")
    public void updateProjectInfo() {
        try {
            if (CommonMethod.isNull(strProjectInfo)) {
                jsonPrint("fail,参数strProjectInfo不能为空");
                return;
            }
            strProjectInfo = strProjectInfo.replace("OO", "#");
            projectInfoService.updateProjectInfo(getColl(strProjectInfo), getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @RequestMapping("findProjectInfo.action")
    public void findProjectInfo() {
        List<ProjectInfo> piList = projectInfoService.findProjectInfo(strProjectId, strProjectName);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(piList, jsonConfig);
        jsonPrint(jsonArr);
    }

    public String getStrProjectInfo() {
        return strProjectInfo;
    }

    public void setStrProjectInfo(String strProjectInfo) {
        this.strProjectInfo = strProjectInfo;
    }

    public String getStrProjectId() {
        return strProjectId;
    }

    public void setStrProjectId(String strProjectId) {
        this.strProjectId = strProjectId;
    }

    public String getStrProjectName() {
        return strProjectName;
    }

    public void setStrProjectName(String strProjectName) {
        this.strProjectName = strProjectName;
    }

}
