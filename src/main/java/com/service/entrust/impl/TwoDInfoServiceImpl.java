package com.service.entrust.impl;

import com.common.action.JsonPager;
import com.common.dao.BaseDao;
import com.common.service.BaseServiceImpl;
import com.model.TwoDInfo;
import com.service.entrust.TwoDInfoService;
import org.springframework.stereotype.Service;

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