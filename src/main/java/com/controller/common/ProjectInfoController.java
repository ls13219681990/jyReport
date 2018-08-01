package com.controller.common;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.model.ProjectInfo;
import com.service.common.ProjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("projectInfoAction")
public class ProjectInfoController extends QueryAction<ProjectInfo> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*private String strProjectInfo = "";

    private String strProjectId = "";

    private String strProjectName = "";*/

    @Autowired
    private ProjectInfoService projectInfoService;

    /*@Action(value = "/projectInfoAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    public String jompSys() {
        return "/jsp/sys/sysCode";
    }

    @RequestMapping("saveProjectInfo.action")
    @ResponseBody
    public String saveProjectInfo(String strProjectInfo, String userId) {
        String projectId = null;
        try {
            if (CommonMethod.isNull(strProjectInfo)) {
                throw new BusinessException("fail,参数strProjectInfo不能为空");
            }
            strProjectInfo = strProjectInfo.replace("OO", "#");
            projectId = projectInfoService.saveProjectInfo(getColl(strProjectInfo), userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true:" + projectId;
    }

    @RequestMapping("updateProjectInfo.action")
    @ResponseBody
    public String updateProjectInfo(String strProjectInfo, String userId) {
        try {
            if (CommonMethod.isNull(strProjectInfo)) {
                throw new BusinessException("fail,参数strProjectInfo不能为空");
            }
            strProjectInfo = strProjectInfo.replace("OO", "#");
            projectInfoService.updateProjectInfo(getColl(strProjectInfo), userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("findProjectInfo.action")
    @ResponseBody
    public List<ProjectInfo> findProjectInfo(String strProjectId, String strProjectName) {
        List<ProjectInfo> piList = projectInfoService.findProjectInfo(strProjectId, strProjectName);
        return piList;
    }

/*    public String getStrProjectInfo() {
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
    }*/

}
