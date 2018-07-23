package com.service.finance;

import com.common.service.BaseService;
import com.model.ReceivableInvoiceDetails;

import java.util.Collection;
import java.util.List;

public interface ReceivableInDetailsService extends BaseService<ReceivableInvoiceDetails> {

    /**
     * 查找票据ID或者收款ID对应的数据
     *
     * @param strInOrAcId
     * @param searchType  0：根据票据ID查找对应的收款记录，1：根据收款ID查找对应的发票记录
     * @return
     */
    public List<ReceivableInvoiceDetails> findReInDetails(String strInOrAcId, String searchType);

    void saveReInDetails(Collection<ReceivableInvoiceDetails> coll, String strInvoiceDetailId);

    void saveReAcDetails(Collection<ReceivableInvoiceDetails> coll, String strAccountDetailId);
}