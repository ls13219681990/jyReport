package com.service.finance;

import com.common.service.BaseService;
import com.dao.page.AmReceivablePage;
import com.model.AmountReceivable;

import java.util.Collection;
import java.util.List;

public interface AmountReceivableService extends BaseService<AmountReceivable> {
    void saveAmReceivable(Collection<AmReceivablePage> coll, String userId);

    void saveAmReceivableTable(Collection<AmReceivablePage> coll, String userId);

    void updateAmReceivable(Collection<AmReceivablePage> coll, String userId);

    public List<AmReceivablePage> findAmReceivableList(AmReceivablePage amp);
}