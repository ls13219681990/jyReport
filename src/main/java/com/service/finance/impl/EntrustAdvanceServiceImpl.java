package com.service.finance.impl;

import com.common.action.JsonPager;
import com.common.dao.BaseDao;
import com.common.service.BaseServiceImpl;
import com.model.EntrustAdvance;
import com.service.finance.EntrustAdvanceService;
import org.springframework.stereotype.Service;

@Service("entrustAdvanceService")
public class EntrustAdvanceServiceImpl extends BaseServiceImpl<EntrustAdvance> implements
        EntrustAdvanceService {
    @Override
    protected void initBaseDAO(BaseDao<EntrustAdvance> baseDao) {

    }

    @Override
    public JsonPager<EntrustAdvance> findJsonPageByCriteria(JsonPager<EntrustAdvance> jp, EntrustAdvance entrustAdvance) {
        return null;
    }

	/*@Override
	@Resource(name = "entrustAdvanceDao")
	protected void initBaseDAO(BaseDao<EntrustAdvance> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<EntrustAdvance> findJsonPageByCriteria(JsonPager<EntrustAdvance> jp,
			EntrustAdvance t) {
		return null;
	}*/

}