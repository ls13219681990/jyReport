package com.service.entrust.impl;

import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.model.SampleReport;
import com.service.common.impl.BaseServiceImpl;
import com.service.entrust.SampleReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
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