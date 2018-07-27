package com.dao.common;

import com.model.EntrustCompany;
import com.dao.page.AdvanceMoneyPage;

import java.util.List;

public interface EntrustCompanyDao extends BaseDao<EntrustCompany> {

    public List<AdvanceMoneyPage> findEntrustAdvanceMoney(String strEntrustCompanyId);

    String findMaxEntrustCompanyNo();
}