package com.dao.entrust;

import com.common.dao.BaseDao;
import com.model.EntrustDetails;

public interface EntrustDetailDao extends BaseDao<EntrustDetails> {

    void deleteByEntrustDetailId(String entrustDetailId);
}