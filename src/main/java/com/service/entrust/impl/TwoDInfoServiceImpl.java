package com.service.entrust.impl;

import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.model.TwoDInfo;
import com.service.common.impl.BaseServiceImpl;
import com.service.entrust.TwoDInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("twoDInfoService")
public class TwoDInfoServiceImpl extends BaseServiceImpl<TwoDInfo> implements
        TwoDInfoService {
    @Override
    protected void initBaseDAO(BaseDao<TwoDInfo> baseDao) {

    }

    @Override
    public JsonPager<TwoDInfo> findJsonPageByCriteria(JsonPager<TwoDInfo> jp, TwoDInfo twoDInfo) {
        return null;
    }

/*	@Override
	@Resource(name = "twoDInfoDao")
	protected void initBaseDAO(BaseDao<TwoDInfo> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<TwoDInfo> findJsonPageByCriteria(JsonPager<TwoDInfo> jp,
			TwoDInfo t) {
		return null;
	}*/

}