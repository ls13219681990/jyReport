package com.service.entrust.impl;

import com.common.action.JsonPager;
import com.common.dao.BaseDao;
import com.common.service.BaseServiceImpl;
import com.model.SampleReport;
import com.service.entrust.SampleReportService;
import org.springframework.stereotype.Service;

@Service("sampleReportService")
public class SampleReportServiceImpl extends BaseServiceImpl<SampleReport> implements
        SampleReportService {
    @Override
    protected void initBaseDAO(BaseDao<SampleReport> baseDao) {

    }

    @Override
    public JsonPager<SampleReport> findJsonPageByCriteria(JsonPager<SampleReport> jp, SampleReport sampleReport) {
        return null;
    }

	/*@Override
	@Resource(name = "sampleReportDao")
	protected void initBaseDAO(BaseDao<SampleReport> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<SampleReport> findJsonPageByCriteria(JsonPager<SampleReport> jp,
			SampleReport t) {
		return null;
	}*/

}