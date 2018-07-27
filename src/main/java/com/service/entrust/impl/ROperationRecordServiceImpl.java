package com.service.entrust.impl;

import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.service.common.impl.BaseServiceImpl;
import com.model.ReportOperationRecord;
import com.service.entrust.ROperationRecordService;
import org.springframework.stereotype.Service;

@Service("rOperationRecordService")
public class ROperationRecordServiceImpl extends BaseServiceImpl<ReportOperationRecord> implements
        ROperationRecordService {
    @Override
    protected void initBaseDAO(BaseDao<ReportOperationRecord> baseDao) {

    }

    @Override
    public JsonPager<ReportOperationRecord> findJsonPageByCriteria(JsonPager<ReportOperationRecord> jp, ReportOperationRecord reportOperationRecord) {
        return null;
    }

	/*@Override
	@Resource(name = "rOperationRecordDao")
	protected void initBaseDAO(BaseDao<ReportOperationRecord> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<ReportOperationRecord> findJsonPageByCriteria(JsonPager<ReportOperationRecord> jp,
			ReportOperationRecord t) {
		return null;
	}*/

}