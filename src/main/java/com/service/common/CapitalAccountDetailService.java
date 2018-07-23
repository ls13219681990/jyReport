package com.service.common;

import com.common.service.BaseService;
import com.dao.page.CapitalAccountDetailPage;
import com.model.CapitalAccountDetail;

import java.util.Collection;
import java.util.List;

public interface CapitalAccountDetailService extends BaseService<CapitalAccountDetail> {

    void saveCADetailTable(Collection<CapitalAccountDetailPage> coll, String userId);

    List<CapitalAccountDetail> findCADetails(String capitalAccountId, String sampleId, String testParameterId);

    List<CapitalAccountDetailPage> findCADetailList(String capitalAccountId);

    List<CapitalAccountDetail> findCASampleDetails(String capitalAccountId, String sampleId);

    List<CapitalAccountDetailPage> findCASampleDetailsAll(String contractId);
}