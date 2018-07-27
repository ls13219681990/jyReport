package com.service.common.impl;


import com.common.BusinessException;
import com.common.CommonMethod;
import com.dao.common.BaseDepartmentDao;
import com.model.BaseDepartment;
import com.service.common.BaseDepartmentService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service("baseDepartmentService")
public abstract class BaseDepartmentServiceImpl extends BaseServiceImpl<BaseDepartment> implements
        BaseDepartmentService {

    @Autowired
    private BaseDepartmentDao baseDepartmentDao;
	/*@Override
	@Resource(name = "baseDepartmentDao")
	protected void initBaseDAO(BaseDao<BaseDepartment> baseDao) {
		setBaseDao(baseDao);
	}
*/

    @Override
    public List<BaseDepartment> findDepartment(String strIsManagement) {
        DetachedCriteria dc = DetachedCriteria.forClass(BaseDepartment.class);
        if (!CommonMethod.isNull(strIsManagement)) {
            dc.add(Property.forName("isManagementRight").eq(strIsManagement));
        }
        return baseDepartmentDao.findByCriteria(dc);
    }

    @Override
    public void saveDepartment(Collection<BaseDepartment> coll, String userId) {
        for (BaseDepartment baseDepartment : coll) {
            List<BaseDepartment> bdList = baseDepartmentDao.findByProperty("departmentName", baseDepartment.getDepartmentName());
            if (bdList != null && bdList.size() > 0) {
                throw new BusinessException("该部门名称已经存在！", "");
            }
            baseDepartment.setDepartmentId(CommonMethod.getNewKey());
            baseDepartment.setInputeTime(CommonMethod.getDate());
            baseDepartment.setInputer(userId);
            baseDepartmentDao.save(baseDepartment);
        }
    }

    @Override
    public void updateDepartment(Collection<BaseDepartment> coll, String userId) {
        for (BaseDepartment baseDepartment : coll) {
            BaseDepartment bDepartment = baseDepartmentDao.findById(baseDepartment.getDepartmentId());
            if (bDepartment == null) {
                throw new BusinessException("需要更新的部门不存在，请确认！", "");
            }
            DetachedCriteria dc = DetachedCriteria.forClass(BaseDepartment.class);
            dc.add(Property.forName("departmentId").ne(baseDepartment.getDepartmentId()));
            dc.add(Property.forName("departmentName").eq(baseDepartment.getDepartmentName()));
            List<BaseDepartment> bdList = baseDepartmentDao.findByCriteria(dc);
            if (bdList != null && bdList.size() > 0) {
                throw new BusinessException("该部门名称已经存在！", "");
            }
            bDepartment.setDepartmentName(baseDepartment.getDepartmentName());
            bDepartment.setIsManagementRight(baseDepartment.getIsManagementRight());
            bDepartment.setIsTestDept(baseDepartment.getIsTestDept());
            bDepartment.setRemark(baseDepartment.getRemark());
            bDepartment.setUpdater(userId);
            bDepartment.setUpdateTime(CommonMethod.getDate());
            bDepartment.setSpecialRule(baseDepartment.getSpecialRule());
            baseDepartmentDao.update(bDepartment);
        }
    }


}