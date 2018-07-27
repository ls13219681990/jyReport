package com.service.entrust;

import com.service.common.BaseService;
import com.dao.page.EntrustSavePage;
import com.model.EntrustDetails;
import com.model.SampleReport;

import java.util.List;

public interface EntrustDetailService extends BaseService<EntrustDetails> {

    List<EntrustDetails> findIsEntrust(String strDepartmentId, String strSampleId, String strTParameterId);

    void saveEDetailReport(EntrustSavePage esPage, String userId);

    void updateEDetailReport(EntrustSavePage esPage, String userId);

    List<SampleReport> EntrustDetailsInfo(String EntrustDetailsId);
}