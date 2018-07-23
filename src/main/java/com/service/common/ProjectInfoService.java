package com.service.common;

import com.common.service.BaseService;
import com.model.ProjectInfo;

import java.util.Collection;
import java.util.List;

public interface ProjectInfoService extends BaseService<ProjectInfo> {

    List<ProjectInfo> findProjectInfo(String strProjectId, String strProjectName);

    String saveProjectInfo(Collection<ProjectInfo> coll, String userId);

    void updateProjectInfo(Collection<ProjectInfo> coll, String userId);
}