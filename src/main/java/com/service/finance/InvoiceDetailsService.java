package com.service.finance;

import com.common.service.BaseService;
import com.dao.page.InvoiceDetailPage;
import com.dao.page.ReAccountDetailPage;
import com.model.InvoiceDetails;

import java.util.List;

public interface InvoiceDetailsService extends BaseService<InvoiceDetails> {

    void saveInvoiceDetails(InvoiceDetailPage iDetailPage, String userId);

    void updateInvoiceDetails(InvoiceDetailPage iDetailPage, String userId);

    public List<InvoiceDetailPage> findInvoiceDetail(InvoiceDetailPage idPage, String strEntrustId);

    public List<ReAccountDetailPage> findReAcDetail(InvoiceDetails iDetails);

    public List<InvoiceDetailPage> findInvoiceDetailInvalidation(InvoiceDetailPage idPage, String strEntrustId);


}