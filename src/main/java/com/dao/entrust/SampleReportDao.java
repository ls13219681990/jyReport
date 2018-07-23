package com.dao.entrust;

import com.common.dao.BaseDao;
import com.model.SampleReport;

import java.util.List;

public interface SampleReportDao extends BaseDao<SampleReport> {

    void deleteByEntrustId(String entrustId);

    List<SampleReport> showEntrustIdByOrder(final List<String> entrustIdList);
}