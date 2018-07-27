package com.service.finance.impl;

import com.common.CommonMethod;
import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.service.common.impl.BaseServiceImpl;
import com.dao.finance.InvoiceDetailsDao;
import com.dao.finance.ReceivableAcDetailsDao;
import com.dao.finance.ReceivableEnDetailsDao;
import com.dao.page.InvoiceDetailPage;
import com.dao.page.ReAccountDetailPage;
import com.model.InvoiceDetails;
import com.model.ReceivableAccountDetails;
import com.model.ReceivableEntrustDetails;
import com.service.finance.ReceivableEnDetailsService;
import com.service.sys.SysCodeService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("receivableEnDetailsService")
public class ReceivableEnDetailsServiceImpl extends BaseServiceImpl<ReceivableEntrustDetails> implements
        ReceivableEnDetailsService {

    @Autowired
    private ReceivableEnDetailsDao receivableEnDetailsDao;
    @Autowired
    private InvoiceDetailsDao invoiceDetailsDao;
    @Autowired
    private ReceivableAcDetailsDao receivableAcDetailsDao;
    @Autowired
    private SysCodeService sysCodeService;
	
	/*@Override
	@Resource(name = "receivableEnDetailsDao")
	protected void initBaseDAO(BaseDao<ReceivableEntrustDetails> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<ReceivableEntrustDetails> findJsonPageByCriteria(JsonPager<ReceivableEntrustDetails> jp,
			ReceivableEntrustDetails t) {
		return null;
	}*/

    @Override
    public void saveInEnDetails(Collection<ReceivableEntrustDetails> coll, String strInvoiceDetailId, String userId) {
        receivableEnDetailsDao.deleteByInDetailId(strInvoiceDetailId);
        for (ReceivableEntrustDetails ReEnDetails : coll) {
            ReEnDetails.setReceivableEntrustDetailId(CommonMethod.getNewKey());
            ReEnDetails.setInputer(userId);
            ReEnDetails.setInputeTime(CommonMethod.getDate());
            receivableEnDetailsDao.save(ReEnDetails);
        }
    }

    @Override
    public void saveAcEnDetails(Collection<ReceivableEntrustDetails> coll, String strAccountDetailId, String userId) {
        receivableEnDetailsDao.deleteByAcDetailId(strAccountDetailId);
        for (ReceivableEntrustDetails ReEnDetails : coll) {
            ReEnDetails.setReceivableEntrustDetailId(CommonMethod.getNewKey());
            ReEnDetails.setInputer(userId);
            ReEnDetails.setInputeTime(CommonMethod.getDate());
            receivableEnDetailsDao.save(ReEnDetails);
        }
    }

    @Override
    public List<ReceivableEntrustDetails> findReEnDetails(String strAccountDetailId) {
        DetachedCriteria dc = DetachedCriteria.forClass(ReceivableEntrustDetails.class);
        dc.add(Property.forName("accountDetailId").eq(strAccountDetailId));

        return receivableEnDetailsDao.findByCriteria(dc);
    }

    @Override
    public List<ReceivableEntrustDetails> findReInDetails(String strInvoiceDetailId) {
        DetachedCriteria dc = DetachedCriteria.forClass(ReceivableEntrustDetails.class);
        dc.add(Property.forName("invoiceDetailId").eq(strInvoiceDetailId));

        return receivableEnDetailsDao.findByCriteria(dc);
    }

    @Override
    public List<InvoiceDetailPage> findInDetailPageList(List<ReceivableEntrustDetails> ReEnDetailsList) {
        List<InvoiceDetailPage> inDetailsPageList = new ArrayList<InvoiceDetailPage>();
        //收款ID对应的委托
        for (ReceivableEntrustDetails reEntrustDetails : ReEnDetailsList) {
            //根据委托ID查找对应的发票ID
            DetachedCriteria dc = DetachedCriteria.forClass(ReceivableEntrustDetails.class);
            dc.add(Property.forName("entrustId").eq(reEntrustDetails.getEntrustId()));
            dc.add(Property.forName("invoiceDetailId").isNotNull());
            List<ReceivableEntrustDetails> inDetailsList = receivableEnDetailsDao.findByCriteria(dc);
            if (inDetailsList != null && inDetailsList.size() > 0) {
                for (ReceivableEntrustDetails inDetails : inDetailsList) {
                    InvoiceDetails ids = invoiceDetailsDao.findById(inDetails.getInvoiceDetailId());
                    InvoiceDetailPage idsPage = new InvoiceDetailPage();
                    idsPage.setInvoiceDetailId(ids.getInvoiceDetailId());
                    idsPage.setEntrustCompanyId(ids.getEntrustCompanyId());
                    idsPage.setProjectId(ids.getProjectId());
                    idsPage.setInvoiceDate(ids.getInvoiceDate());
                    idsPage.setInvoiceType(ids.getInvoiceDetailId());
                    idsPage.setInvoiceTypeName(sysCodeService.findCodeName("INVOICE_TYPE", ids.getInvoiceType()));
                    idsPage.setInvoiceValue(ids.getInvoiceValue());
                    idsPage.setInvoiceNo(ids.getInvoiceNo());
                    idsPage.setRemark(ids.getRemark());
                    idsPage.setInputer(ids.getInputer());
                    idsPage.setInputeTime(ids.getInputeTime());
                    idsPage.setUpdater(ids.getUpdater());
                    idsPage.setUpdateTime(ids.getUpdateTime());
                    //查询该发票是否已经收款
                    String strCollected = "0";//已收款标识
                    String strNotCollected = "0";//未收款标识
                    List<ReceivableEntrustDetails> reEnDetailsListTmp = findReInDetails(inDetails.getInvoiceDetailId());
                    if (reEnDetailsListTmp != null && reEnDetailsListTmp.size() > 0) {
                        for (ReceivableEntrustDetails reEnDetailsTmp : reEnDetailsListTmp) {
                            DetachedCriteria dcAc = DetachedCriteria.forClass(ReceivableEntrustDetails.class);
                            dcAc.add(Property.forName("entrustId").eq(reEnDetailsTmp.getEntrustId()));
                            dcAc.add(Property.forName("accountDetailId").isNotNull());
                            List<ReceivableEntrustDetails> riDetailsListTmpIn = receivableEnDetailsDao.findByCriteria(dcAc);
                            if (riDetailsListTmpIn != null && riDetailsListTmpIn.size() > 0) {
                                strCollected = "1";
                            } else {
                                strNotCollected = "1";
                            }
                        }
                    } else {
                        idsPage.setIsReceivable("0");//未收款
                    }
                    if ("1".equals(strCollected) && "0".equals(strNotCollected)) {
                        idsPage.setIsReceivable("1");//已收款
                    } else if ("1".equals(strCollected) && "1".equals(strNotCollected)) {
                        idsPage.setIsReceivable("2");//部分收款
                    } else {
                        idsPage.setIsReceivable("0");//未收款
                    }
                    inDetailsPageList.add(idsPage);
                }
            }
        }
        return inDetailsPageList;
    }


    @Override
    public List<ReAccountDetailPage> findAcDetailPageList(List<ReceivableEntrustDetails> ReEnDetailsList) {
        List<ReAccountDetailPage> reAcDetailsPageList = new ArrayList<ReAccountDetailPage>();
        //发票ID对应的委托
        for (ReceivableEntrustDetails reEntrustDetails : ReEnDetailsList) {
            //根据委托ID查找对应的收款ID
            DetachedCriteria dc = DetachedCriteria.forClass(ReceivableEntrustDetails.class);
            dc.add(Property.forName("entrustId").eq(reEntrustDetails.getEntrustId()));
            dc.add(Property.forName("accountDetailId").isNotNull());
            List<ReceivableEntrustDetails> acDetailsList = receivableEnDetailsDao.findByCriteria(dc);
            if (acDetailsList != null && acDetailsList.size() > 0) {
                for (ReceivableEntrustDetails acDetails : acDetailsList) {
                    ReceivableAccountDetails reAcDetails = receivableAcDetailsDao.findById(acDetails.getAccountDetailId());
                    ReAccountDetailPage reAcDetailsPage = new ReAccountDetailPage();
                    reAcDetailsPage.setAccountDetailId(reAcDetails.getAccountDetailId());
                    reAcDetailsPage.setEntrustCompanyId(reAcDetails.getEntrustCompanyId());
                    reAcDetailsPage.setProjectId(reAcDetails.getProjectId());
                    reAcDetailsPage.setAccountType(reAcDetails.getAccountType());
                    reAcDetailsPage.setAccountTypeName(sysCodeService.findCodeName("FINANCE_ACCOUNT_TYPE", reAcDetails.getAccountType()));
                    reAcDetailsPage.setAccountDate(reAcDetails.getAccountDate());
                    reAcDetailsPage.setAccountValue(reAcDetails.getAccountValue());
                    reAcDetailsPage.setContractCode(reAcDetails.getContractCode());
                    reAcDetailsPage.setRemark(reAcDetails.getRemark());
                    reAcDetailsPage.setInputer(reAcDetails.getInputer());
                    reAcDetailsPage.setInputeTime(reAcDetails.getInputeTime());
                    reAcDetailsPage.setUpdater(reAcDetails.getUpdater());
                    reAcDetailsPage.setUpdateTime(reAcDetails.getUpdateTime());
                    //收款对应的发票号码
                    String allInvoiceNo = "";
                    //根据收款ID查找对应的发票记录
                    List<ReceivableEntrustDetails> reEnDetailsListTmp = findReEnDetails(acDetails.getAccountDetailId());
                    if (reEnDetailsListTmp != null && reEnDetailsListTmp.size() > 0) {
                        for (ReceivableEntrustDetails reEnDetailsTmp : reEnDetailsListTmp) {
                            DetachedCriteria dcIn = DetachedCriteria.forClass(ReceivableEntrustDetails.class);
                            dcIn.add(Property.forName("entrustId").eq(reEnDetailsTmp.getEntrustId()));
                            dcIn.add(Property.forName("invoiceDetailId").isNotNull());
                            List<ReceivableEntrustDetails> riDetailsListTmpIn = receivableEnDetailsDao.findByCriteria(dcIn);
                            ;
                            if (riDetailsListTmpIn != null && riDetailsListTmpIn.size() > 0) {
                                for (ReceivableEntrustDetails riDetailsTmpIn : riDetailsListTmpIn) {
                                    InvoiceDetails inDetails = invoiceDetailsDao.findById(riDetailsTmpIn.getInvoiceDetailId());
                                    if ("".equals(allInvoiceNo)) {
                                        allInvoiceNo = inDetails.getInvoiceNo();
                                    } else {
                                        allInvoiceNo = allInvoiceNo + "," + inDetails.getInvoiceNo();
                                    }
                                }
                            }
                        }
                    }
                    reAcDetailsPage.setAllInvoiceNo(allInvoiceNo);
                    reAcDetailsPageList.add(reAcDetailsPage);
                }
            }
        }
        return reAcDetailsPageList;
    }

    @Override
    protected void initBaseDAO(BaseDao<ReceivableEntrustDetails> baseDao) {

    }

    @Override
    public JsonPager<ReceivableEntrustDetails> findJsonPageByCriteria(JsonPager<ReceivableEntrustDetails> jp, ReceivableEntrustDetails receivableEntrustDetails) {
        return null;
    }
}