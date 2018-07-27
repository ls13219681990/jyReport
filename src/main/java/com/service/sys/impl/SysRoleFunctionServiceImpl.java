package com.service.sys.impl;

import com.common.CommonMethod;
import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.service.common.impl.BaseServiceImpl;
import com.dao.sys.SysRoleFunctionDao;
import com.model.SysRoleFunction;
import com.service.sys.SysRoleFunctionService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author Administrator
 */
@Service("sysRoleFunctionService")
public class SysRoleFunctionServiceImpl extends
        BaseServiceImpl<SysRoleFunction> implements SysRoleFunctionService {

    @Autowired
    private SysRoleFunctionDao sysRoleFunctionDao;
	
	/*@Override
	@Resource(name = "sysRoleFunctionDao")
	protected void initBaseDAO(BaseDao<SysRoleFunction> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<SysRoleFunction> findJsonPageByCriteria(
			JsonPager<SysRoleFunction> jp, SysRoleFunction t) {
		// TODO Auto-generated method stub
		return null;
	}*/

    @Override
    public List<SysRoleFunction> findFunctionByRole(String strRoleId) {
        DetachedCriteria dc = DetachedCriteria.forClass(SysRoleFunction.class);
        dc.add(Property.forName("roleId").eq(strRoleId));
        return sysRoleFunctionDao.findByCriteria(dc);
    }

    @Override
    public void saveSysRoleFunction(Collection<SysRoleFunction> coll, String strRoleId) {
        sysRoleFunctionDao.deleteByRole(strRoleId);
        for (SysRoleFunction sysRoleFunction : coll) {
            sysRoleFunction.setRoleFunctionId(CommonMethod.getNewKey());
            sysRoleFunctionDao.save(sysRoleFunction);
        }
    }

    @Override
    public List<SysRoleFunction> findFuncitonUsed(String strFunctionId) {
        DetachedCriteria dc = DetachedCriteria.forClass(SysRoleFunction.class);
        dc.add(Property.forName("functionId").eq(strFunctionId));
        return sysRoleFunctionDao.findByCriteria(dc);
    }

    @Override
    protected void initBaseDAO(BaseDao<SysRoleFunction> baseDao) {

    }

    @Override
    public JsonPager<SysRoleFunction> findJsonPageByCriteria(JsonPager<SysRoleFunction> jp, SysRoleFunction sysRoleFunction) {
        return null;
    }
}