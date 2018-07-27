package com.service.sys.impl;

import com.common.CommonMethod;
import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.service.common.impl.BaseServiceImpl;
import com.dao.sys.SysUserRoleDao;
import com.model.SysRoleFunction;
import com.model.SysUserRole;
import com.service.sys.SysUserRoleService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole>
        implements SysUserRoleService {

    @Autowired
    private SysUserRoleDao sysUserRoleDao;
	
	/*@Override
	@Resource(name = "sysUserRoleDao")
	protected void initBaseDAO(BaseDao<SysUserRole> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<SysUserRole> findJsonPageByCriteria(
			JsonPager<SysUserRole> jp, SysUserRole t) {
		// TODO Auto-generated method stub
		return null;
	}*/

    @Override
    public List<SysUserRole> findRoleByUser(String strUserId) {
        DetachedCriteria dc = DetachedCriteria.forClass(SysRoleFunction.class);
        dc.add(Property.forName("userId").eq(strUserId));
        return sysUserRoleDao.findByCriteria(dc);
    }

    @Override
    public void saveSysUserRole(Collection<SysUserRole> coll, String strUserId) {
        sysUserRoleDao.deleteByUser(strUserId);
        for (SysUserRole sysUserRole : coll) {
            sysUserRole.setUserRoleId(CommonMethod.getNewKey());
            sysUserRoleDao.save(sysUserRole);
        }
    }

    @Override
    public List<SysUserRole> findRoleUsed(String strRoleId) {
        DetachedCriteria dc = DetachedCriteria.forClass(SysRoleFunction.class);
        dc.add(Property.forName("roleId").eq(strRoleId));
        return sysUserRoleDao.findByCriteria(dc);
    }

    @Override
    protected void initBaseDAO(BaseDao<SysUserRole> baseDao) {

    }

    @Override
    public JsonPager<SysUserRole> findJsonPageByCriteria(JsonPager<SysUserRole> jp, SysUserRole sysUserRole) {
        return null;
    }
}