package com.service.entrust.impl;

import com.common.action.JsonPager;
import com.common.dao.BaseDao;
import com.common.service.BaseServiceImpl;
import com.model.EntrustOperationRecord;
import com.service.entrust.EOperationRecordService;
import org.springframework.stereotype.Service;

@Service("eOperationRecordService")
public class EOperationRecordServiceImpl extends BaseServiceImpl<EntrustOperationRecord> implements
        EOperationRecordService {
    @Override
    protected void initBaseDAO(BaseDao<EntrustOperationRecord> baseDao) {

    }

    @Override
    public JsonPager<EntrustOperationRecord> findJsonPageByCriteria(JsonPager<EntrustOperationRecord> jp, EntrustOperationRecord entrustOperationRecord) {
        return null;
    }

	/*@Override
	@Resource(name = "eOperationRecordDao")
	protected void initBaseDAO(BaseDao<EntrustOperationRecord> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<EntrustOperationRecord> findJsonPageByCriteria(JsonPager<EntrustOperationRecord> jp,
			EntrustOperationRecord t) {
		return null;
	}*/

}