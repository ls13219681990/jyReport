package com.service.entrust.impl;

import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.model.EntrustOperationRecord;
import com.service.common.impl.BaseServiceImpl;
import com.service.entrust.EOperationRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
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