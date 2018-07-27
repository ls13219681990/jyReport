package com.dao.common;

import java.util.List;

import com.model.CapitalAccountDetail;
import com.dao.page.CapitalAccountDetailPage;

public interface CapitalAccountDetailDao extends BaseDao<CapitalAccountDetail> {
    public void deleteByCAId(String caId);

    List<CapitalAccountDetailPage> findCASampleDetailsAll();
}