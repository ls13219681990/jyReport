package com.service.finance.impl;

import com.common.CommonMethod;
import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.service.common.impl.BaseServiceImpl;
import com.dao.finance.ReceivableInDetailsDao;
import com.model.ReceivableInvoiceDetails;
import com.service.finance.ReceivableInDetailsService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service("receivableInDetailsService")
public class ReceivableInDetailsServiceImpl extends BaseServiceImpl<ReceivableInvoiceDetails> implements
        ReceivableInDetailsService {

    @Autowired
    private ReceivableInDetailsDao receivableInDetailsDao;
	
	/*@Override
	@Resource(name = "receivableInDetailsDao")
	protected void initBaseDAO(BaseDao<ReceivableInvoiceDetails> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<ReceivableInvoiceDetails> findJsonPageByCriteria(JsonPager<ReceivableInvoiceDetails> jp,
			ReceivableInvoiceDetails t) {
		return null;
	}*/

    @Override
    public void saveReInDetails(Collection<ReceivableInvoiceDetails> coll, String strInvoiceDetailId) {
        receivableInDetailsDao.deleteByInDetailId(strInvoiceDetailId);
        for (ReceivableInvoiceDetails ReInDetails : coll) {
            ReInDetails.setReceivableInvoiceDetailId(CommonMethod.getNewKey());
            receivableInDetailsDao.save(ReInDetails);
        }
    }

    @Override
    public void saveReAcDetails(Collection<ReceivableInvoiceDetails> coll, String strAccountDetailId) {
        receivableInDetailsDao.deleteByAcDetailId(strAccountDetailId);
        for (ReceivableInvoiceDetails ReInDetails : coll) {
            ReInDetails.setReceivableInvoiceDetailId(CommonMethod.getNewKey());
            receivableInDetailsDao.save(ReInDetails);
        }
    }

    @Override
    public List<ReceivableInvoiceDetails> findReInDetails(String strInOrAcId, String searchType) {
        DetachedCriteria dc = DetachedCriteria.forClass(ReceivableInvoiceDetails.class);
        if ("0".equals(searchType)) {
            dc.add(Property.forName("invoiceDetailId").eq(strInOrAcId));
        } else {
            dc.add(Property.forName("accountDetailId").eq(strInOrAcId));
        }

        return receivableInDetailsDao.findByCriteria(dc);
    }

    @Override
    protected void initBaseDAO(BaseDao<ReceivableInvoiceDetails> baseDao) {

    }

    @Override
    public JsonPager<ReceivableInvoiceDetails> findJsonPageByCriteria(JsonPager<ReceivableInvoiceDetails> jp, ReceivableInvoiceDetails receivableInvoiceDetails) {
        return null;
    }
}