package com.service.sys.impl;

import com.common.CommonMethod;
import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.dao.sys.SysFunctionDao;
import com.model.SysFunction;
import com.service.common.impl.BaseServiceImpl;
import com.service.sys.SysFunctionService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Transactional
@Service("sysFunctionService")
public class SysFunctionServiceImpl extends BaseServiceImpl<SysFunction> implements
        SysFunctionService {

    @Autowired
    private SysFunctionDao sysFunctionDao;

/*	@Override
	@Resource(name = "sysFunctionDao")
	protected void initBaseDAO(BaseDao<SysFunction> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<SysFunction> findJsonPageByCriteria(JsonPager<SysFunction> jp,
			SysFunction t) {
		return null;
	}*/

    @Override
    public List<SysFunction> findFunction() {
        DetachedCriteria dc = DetachedCriteria.forClass(SysFunction.class);
        dc.addOrder(Property.forName("functionCode").asc());
        return sysFunctionDao.findByCriteria(dc);
    }

    @Override
    public void saveFunction(Collection<SysFunction> coll) {
        for (SysFunction sysFunction : coll) {
            sysFunction.setFunctionId(CommonMethod.getNewKey());
            sysFunction.setCreatDate(CommonMethod.getCurrentDate());
            sysFunctionDao.save(sysFunction);
        }
    }

    @Override
    public void updateFunction(Collection<SysFunction> coll) {
        for (SysFunction sysFunction : coll) {
            sysFunctionDao.update(sysFunction);
        }
    }

    @Override
    public List<SysFunction> findFunctionValid() {
        DetachedCriteria dc = DetachedCriteria.forClass(SysFunction.class);
        dc.add(Property.forName("status").eq("01"));
        return sysFunctionDao.findByCriteria(dc);
    }

    @Override
    protected void initBaseDAO(BaseDao<SysFunction> baseDao) {

    }

    @Override
    public JsonPager<SysFunction> findJsonPageByCriteria(JsonPager<SysFunction> jp, SysFunction sysFunction) {
        return null;
    }
}