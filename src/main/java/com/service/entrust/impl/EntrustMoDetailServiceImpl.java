package com.service.entrust.impl;

import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.service.common.impl.BaseServiceImpl;
import com.model.EntrustMoneyDetails;
import com.service.entrust.EntrustMoDetailService;
import org.springframework.stereotype.Service;

@Service("entrustMoDetailService")
public class EntrustMoDetailServiceImpl extends BaseServiceImpl<EntrustMoneyDetails> implements
        EntrustMoDetailService {

	/*@Override
	@Resource(name = "entrustMoDetailDao")
	protected void initBaseDAO(BaseDao<EntrustMoneyDetails> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<EntrustMoneyDetails> findJsonPageByCriteria(JsonPager<EntrustMoneyDetails> jp,
			EntrustMoneyDetails t) {
		return null;
	}*/


    @Override
    protected void initBaseDAO(BaseDao<EntrustMoneyDetails> baseDao) {

    }

    @Override
    public JsonPager<EntrustMoneyDetails> findJsonPageByCriteria(JsonPager<EntrustMoneyDetails> jp, EntrustMoneyDetails entrustMoneyDetails) {
        return null;
    }
}