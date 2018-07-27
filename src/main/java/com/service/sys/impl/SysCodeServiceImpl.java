package com.service.sys.impl;

import com.common.CommonMethod;
import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.service.common.impl.BaseServiceImpl;
import com.dao.sys.SysCodeDao;
import com.model.SysCode;
import com.service.sys.SysCodeService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service("sysCodeService")
public class SysCodeServiceImpl extends BaseServiceImpl<SysCode> implements
        SysCodeService {

    @Autowired
    private SysCodeDao sysCodeDao;

	/*@Override
	@Resource(name = "sysCodeDao")
	protected void initBaseDAO(BaseDao<SysCode> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<SysCode> findJsonPageByCriteria(JsonPager<SysCode> jp,
			SysCode t) {
		return null;
	}*/

    @Override
    public List<SysCode> findSysCodes() {
        DetachedCriteria dc = DetachedCriteria.forClass(SysCode.class);
        dc.addOrder(Order.asc("codeRelation"));
        dc.addOrder(Order.asc("codeValue"));
        return sysCodeDao.findByCriteria(dc);
    }

    @Override
    public List<SysCode> findPitchOncode(String codeRelation) {
        DetachedCriteria dc = DetachedCriteria.forClass(SysCode.class);
        dc.add(Property.forName("codeRelation").eq(codeRelation));
        dc.add(Property.forName("status").eq("01"));
        dc.addOrder(Order.asc("codeRelation"));
        dc.addOrder(Order.asc("codeValue"));
        return sysCodeDao.findByCriteria(dc);
    }

    @Override
    public String findCodeName(String codeRelation, String codeValue) {
        String codeName = "";
        DetachedCriteria dc = DetachedCriteria.forClass(SysCode.class);
        dc.add(Property.forName("codeRelation").eq(codeRelation));
        dc.add(Property.forName("codeValue").eq(codeValue));
        dc.add(Property.forName("status").eq("01"));
        List<SysCode> sysCodeList = sysCodeDao.findByCriteria(dc);
        if (sysCodeList != null && sysCodeList.size() > 0) {
            codeName = sysCodeList.get(0).getCodeName();
        }
        return codeName;
    }

    @Override
    public void saveCode(Collection<SysCode> coll) {
        for (SysCode sysCode : coll) {
            sysCode.setCodeId(CommonMethod.getNewKey());
            sysCodeDao.save(sysCode);
        }
    }

    @Override
    public void updateCode(Collection<SysCode> coll) {
        for (SysCode sysCode : coll) {
            sysCodeDao.update(sysCode);
        }
    }

    @Override
    protected void initBaseDAO(BaseDao<SysCode> baseDao) {

    }

    @Override
    public JsonPager<SysCode> findJsonPageByCriteria(JsonPager<SysCode> jp, SysCode sysCode) {
        return null;
    }
}