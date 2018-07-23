package com.service.common.impl;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.action.JsonPager;
import com.common.dao.BaseDao;
import com.common.service.BaseServiceImpl;
import com.dao.common.ProjectInfoDao;
import com.model.ProjectInfo;
import com.service.common.ProjectInfoService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;


@Service("projectInfoService")
public class ProjectInfoServiceImpl extends BaseServiceImpl<ProjectInfo> implements
        ProjectInfoService {

    @Autowired
    private ProjectInfoDao projectInfoDao;

    @Override
    @Resource(name = "projectInfoDao")
    protected void initBaseDAO(BaseDao<ProjectInfo> baseDao) {
        setBaseDao(baseDao);
    }

    @Override
    public JsonPager<ProjectInfo> findJsonPageByCriteria(JsonPager<ProjectInfo> jp,
                                                         ProjectInfo t) {
        return null;
    }

    @Override
    public List<ProjectInfo> findProjectInfo(String strProjectId, String strProjectName) {
        DetachedCriteria dc = DetachedCriteria.forClass(ProjectInfo.class);
        if (!CommonMethod.isNull(strProjectId)) {
            dc.add(Property.forName("projectId").eq(strProjectId));
        }
        if (!CommonMethod.isNull(strProjectName)) {
            strProjectName = strProjectName.replace("OO", "#");
            dc.add(Property.forName("projectName").like(strProjectName, MatchMode.ANYWHERE));
        }
        dc.addOrder(Order.desc("inputeTime"));
        return projectInfoDao.findByCriteria(dc);
    }

    @Override
    public String saveProjectInfo(Collection<ProjectInfo> coll, String userId) {
        String projectId = "";
        for (ProjectInfo projectInfo : coll) {
            List<ProjectInfo> piList = projectInfoDao.findByProperty("projectName", projectInfo.getProjectName());
            if (piList != null && piList.size() > 0) {
                throw new BusinessException("该工程名称已经存在！", "");
            }
            projectInfo.setProjectId(CommonMethod.getNewKey());
            projectInfo.setInputer(userId);
            projectInfo.setInputeTime(CommonMethod.getDate());
            projectInfoDao.save(projectInfo);

            projectId = projectInfo.getProjectId();
        }
        return projectId;
    }

    @Override
    public void updateProjectInfo(Collection<ProjectInfo> coll, String userId) {
        for (ProjectInfo projectInfo : coll) {
            DetachedCriteria dc = DetachedCriteria.forClass(ProjectInfo.class);
            dc.add(Property.forName("projectId").ne(projectInfo.getProjectId()));
            dc.add(Property.forName("projectName").eq(projectInfo.getProjectName()));
            List<ProjectInfo> piList = projectInfoDao.findByCriteria(dc);
            if (piList != null && piList.size() > 0) {
                throw new BusinessException("该工程单位名称已经存在！", "");
            }
            ProjectInfo pInfo = projectInfoDao.findById(projectInfo.getProjectId());
            pInfo.setProjectName(projectInfo.getProjectName());
            pInfo.setProjectAddress(projectInfo.getProjectAddress());
            pInfo.setLinkMan(projectInfo.getLinkMan());
            pInfo.setLinkPhone(projectInfo.getLinkPhone());
            pInfo.setRemark(projectInfo.getRemark());
            pInfo.setUpdater(userId);
            pInfo.setUpdateTime(CommonMethod.getDate());
            projectInfoDao.update(pInfo);
        }
    }

}