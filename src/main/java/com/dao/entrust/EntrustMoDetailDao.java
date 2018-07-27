package com.dao.entrust;

import com.dao.common.BaseDao;
import com.model.EntrustMoneyDetails;

public interface EntrustMoDetailDao extends BaseDao<EntrustMoneyDetails> {

    void deleteByEntrustDetailId(String entrustDetailId);
}