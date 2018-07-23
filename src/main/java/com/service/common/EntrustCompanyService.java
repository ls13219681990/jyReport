package com.service.common;

import com.common.service.BaseService;
import com.dao.page.AdvanceMoneyPage;
import com.model.EntrustCompany;

import java.util.Collection;
import java.util.List;

public interface EntrustCompanyService extends BaseService<EntrustCompany> {

    List<EntrustCompany> findEntrustCompany(String strEntrustCompanyId, String strEntrustCompanyName, String strEntrustCompanyNo);

    String saveEntrustCompany(Collection<EntrustCompany> coll, String userId);

    void updateEntrustCompany(Collection<EntrustCompany> coll, String userId);

    void saveEntrustAdvanceMoney(String strEntrustCompanyId, String strAdvanceMoney, String userId);

    List<AdvanceMoneyPage> findEntrustAdvanceMoney(String strEntrustCompanyId);
}