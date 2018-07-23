package com.service.finance;

import com.common.service.BaseService;
import com.dao.page.InvoiceDetailPage;
import com.dao.page.ReAccountDetailPage;
import com.model.ReceivableAccountDetails;

import java.util.List;

public interface ReceivableAcDetailsService extends BaseService<ReceivableAccountDetails> {

    void saveReAcDetails(ReAccountDetailPage reAcDetailPage, String userId);

    void updateReAcDetails(ReAccountDetailPage reAcDetailPage, String userId);

    public List<ReAccountDetailPage> findReAcDetail(ReAccountDetailPage reAcPage, String strEntrustId);

    public List<InvoiceDetailPage> findinDetailPageList(ReceivableAccountDetails reAcDetail);

    public List<ReAccountDetailPage> findReAcDetailInvalidation(ReAccountDetailPage reAcPage, String strEntrustId);
}