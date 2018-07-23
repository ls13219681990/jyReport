package com.dao.finance;

import com.common.dao.BaseDao;
import com.model.ReceivableEntrustDetails;

public interface ReceivableEnDetailsDao extends BaseDao<ReceivableEntrustDetails> {

    public void deleteByInDetailId(String strInvoiceDetailId);

    public void deleteByAcDetailId(String strAccountDetailId);
}