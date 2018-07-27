package com.service.finance;

import com.service.common.BaseService;
import com.dao.page.InvoiceDetailPage;
import com.dao.page.ReAccountDetailPage;
import com.model.ReceivableEntrustDetails;

import java.util.Collection;
import java.util.List;

public interface ReceivableEnDetailsService extends BaseService<ReceivableEntrustDetails> {

    /**
     * 根据收款ID查找对应的委托数据
     *
     * @param strAccountDetailId
     * @return
     */
    public List<ReceivableEntrustDetails> findReEnDetails(String strAccountDetailId);

    /**
     * 根据发票ID查找对应的委托数据
     *
     * @param //strAccountDetailId
     * @return
     */
    public List<ReceivableEntrustDetails> findReInDetails(String strInvoiceDetailId);

    void saveInEnDetails(Collection<ReceivableEntrustDetails> coll, String strInvoiceDetailId, String userId);

    void saveAcEnDetails(Collection<ReceivableEntrustDetails> coll, String strAccountDetailId, String userId);

    public List<InvoiceDetailPage> findInDetailPageList(List<ReceivableEntrustDetails> ReEnDetailsList);

    public List<ReAccountDetailPage> findAcDetailPageList(List<ReceivableEntrustDetails> ReEnDetailsList);
}