package com.dao.finance;

import com.common.dao.BaseDao;
import com.model.ReceivableInvoiceDetails;

public interface ReceivableInDetailsDao extends BaseDao<ReceivableInvoiceDetails> {
    public void deleteByInDetailId(String strInvoiceDetailId);

    public void deleteByAcDetailId(String strAccountDetailId);
}