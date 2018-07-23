package com.dao.finance;

import com.common.dao.BaseDao;
import com.model.AmountReceivable;
import com.dao.page.AmReceivablePage;

import java.util.List;

public interface AmountReceivableDao extends BaseDao<AmountReceivable> {

    public List<AmReceivablePage> findAmReceivableListSQL(AmReceivablePage amp);
}