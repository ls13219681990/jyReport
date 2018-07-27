package com.service.common.impl;

import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.model.ManagementDepartment;
import com.service.common.ManagementDepartmentService;
import org.springframework.stereotype.Service;


@Service("managementDepartmentService")
public class ManagementDepartmentServiceImpl extends BaseServiceImpl<ManagementDepartment> implements
        ManagementDepartmentService {
    @Override
    protected void initBaseDAO(BaseDao<ManagementDepartment> baseDao) {

    }

    @Override
    public JsonPager<ManagementDepartment> findJsonPageByCriteria(JsonPager<ManagementDepartment> jp, ManagementDepartment managementDepartment) {
        return null;
    }

	/*@Override
	@Resource(name = "managementDepartmentDao")
	protected void initBaseDAO(BaseDao<ManagementDepartment> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<ManagementDepartment> findJsonPageByCriteria(JsonPager<ManagementDepartment> jp,
																  ManagementDepartment t) {
		return null;
	}*/

}