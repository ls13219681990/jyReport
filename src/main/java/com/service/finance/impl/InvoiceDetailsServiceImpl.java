package com.service.finance.impl;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.dao.finance.InvoiceDetailsDao;
import com.dao.finance.ReceivableAcDetailsDao;
import com.dao.finance.ReceivableEnDetailsDao;
import com.dao.page.EntrustInfoPage;
import com.dao.page.InvoiceDetailPage;
import com.dao.page.ReAccountDetailPage;
import com.model.InvoiceDetails;
import com.model.ReceivableAccountDetails;
import com.model.ReceivableEntrustDetails;
import com.model.ReceivableInvoiceDetails;
import com.service.common.impl.BaseServiceImpl;
import com.service.finance.InvoiceDetailsService;
import com.service.finance.ReceivableInDetailsService;
import com.service.sys.SysCodeService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("invoiceDetailsService")
public class InvoiceDetailsServiceImpl extends BaseServiceImpl<InvoiceDetails> implements
        InvoiceDetailsService {

    @Autowired
    private InvoiceDetailsDao invoiceDetailsDao;
    @Autowired
    private ReceivableAcDetailsDao receivableAcDetailsDao;
    @Autowired
    private SysCodeService sysCodeService;
    @Autowired
    private ReceivableInDetailsService receivableInDetailsService;
    @Autowired
    private ReceivableEnDetailsDao receivableEnDetailsDao;
	
	/*@Override
	@Resource(name = "invoiceDetailsDao")
	protected void initBaseDAO(BaseDao<InvoiceDetails> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<InvoiceDetails> findJsonPageByCriteria(JsonPager<InvoiceDetails> jp,
			InvoiceDetails t) {
		return null;
	}*/

    @Override
    public void saveInvoiceDetails(InvoiceDetailPage iDetailPage, String userId) {
        if (CommonMethod.isNull(iDetailPage.getProjectId())) {
            throw new BusinessException("工程不能为空！", "");
        }
        if (CommonMethod.isNull(iDetailPage.getEntrustCompanyId())) {
            throw new BusinessException("委托单位不能为空！", "");
        }
        InvoiceDetails iDetails = new InvoiceDetails();
        iDetails.setInvoiceDetailId(CommonMethod.getNewKey());
        iDetails.setEntrustCompanyId(iDetailPage.getEntrustCompanyId());
        iDetails.setProjectId(iDetailPage.getProjectId());
        iDetails.setInvoiceDate(iDetailPage.getInvoiceDate());
        iDetails.setInvoiceType(iDetailPage.getInvoiceType());
        iDetails.setInvoiceValue(iDetailPage.getInvoiceValue());
        iDetails.setInvoiceNo(iDetailPage.getInvoiceNo());
        iDetails.setRemark(iDetailPage.getRemark());
        iDetails.setInputer(userId);
        iDetails.setInputeTime(CommonMethod.getDate());
        invoiceDetailsDao.save(iDetails);
        List<EntrustInfoPage> entrustIdList = iDetailPage.getEntrustIdList();
        for (EntrustInfoPage enInfoPage : entrustIdList) {
            ReceivableEntrustDetails reEnDetail = new ReceivableEntrustDetails();
            reEnDetail.setReceivableEntrustDetailId(CommonMethod.getNewKey());
            reEnDetail.setInvoiceDetailId(iDetails.getInvoiceDetailId());
            reEnDetail.setEntrustId(enInfoPage.getEntrustId());
            reEnDetail.setInputer(userId);
            reEnDetail.setInputeTime(CommonMethod.getDate());
            receivableEnDetailsDao.save(reEnDetail);
        }
    }

    @Override
    public void updateInvoiceDetails(InvoiceDetailPage iDetailPage, String userId) {
        if (CommonMethod.isNull(iDetailPage.getInvoiceDetailId())) {
            throw new BusinessException("开票ID不能为空！", "");
        }
        if (CommonMethod.isNull(iDetailPage.getProjectId())) {
            throw new BusinessException("工程不能为空！", "");
        }
        if (CommonMethod.isNull(iDetailPage.getEntrustCompanyId())) {
            throw new BusinessException("委托单位不能为空！", "");
        }
        InvoiceDetails iDetails = invoiceDetailsDao.findById(iDetailPage.getInvoiceDetailId());
        iDetails.setEntrustCompanyId(iDetailPage.getEntrustCompanyId());
        iDetails.setProjectId(iDetailPage.getProjectId());
        iDetails.setInvoiceDate(iDetailPage.getInvoiceDate());
        iDetails.setInvoiceType(iDetailPage.getInvoiceType());
        iDetails.setInvoiceValue(iDetailPage.getInvoiceValue());
        iDetails.setInvoiceNo(iDetailPage.getInvoiceNo());
        iDetails.setRemark(iDetailPage.getRemark());
        iDetails.setInputer(userId);
        iDetails.setInputeTime(CommonMethod.getDate());
        invoiceDetailsDao.update(iDetails);
    }

    @Override
    public List<InvoiceDetailPage> findInvoiceDetail(InvoiceDetailPage idPage, String strEntrustId) {
        List<InvoiceDetailPage> idList = new ArrayList<InvoiceDetailPage>();
        DetachedCriteria dc = DetachedCriteria.forClass(InvoiceDetails.class);
        if (idPage != null) {
            if (!CommonMethod.isNull(idPage.getEntrustCompanyId())) {//委托单位
                dc.add(Property.forName("entrustCompanyId").eq(idPage.getEntrustCompanyId()));
            }
            if (!CommonMethod.isNull(idPage.getProjectId())) {//工程
                dc.add(Property.forName("projectId").eq(idPage.getProjectId()));
            }
            if (!CommonMethod.isNull(idPage.getInvoiceType())) {//票据类型
                dc.add(Property.forName("invoiceType").eq(idPage.getInvoiceType()));
            }
            if (!CommonMethod.isNull(idPage.getInvoiceNo())) {//票据号码
                dc.add(Property.forName("invoiceNo").eq(idPage.getInvoiceNo()));
            }
            if (!CommonMethod.isNull(idPage.getInvoiceStartDate())) {//开票开始日期
                dc.add(Property.forName("invoiceDate").ge(idPage.getInvoiceStartDate()));
            }
            if (!CommonMethod.isNull(idPage.getInvoiceEndDate())) {//开票结束日期
                dc.add(Property.forName("invoiceDate").le(idPage.getInvoiceEndDate()));
            }
            dc.add(Property.forName("receivableState").ne("作废"));
        }
        if (!CommonMethod.isNull(strEntrustId)) {
            List<String> riEntrustInvoiceList = new ArrayList<String>();
            DetachedCriteria dcEntrust = DetachedCriteria.forClass(ReceivableEntrustDetails.class);
            dcEntrust.add(Property.forName("entrustId").eq(strEntrustId));
            dcEntrust.add(Property.forName("invoiceDetailId").isNotNull());
            List<ReceivableEntrustDetails> riEntrustDetailsList = receivableEnDetailsDao.findByCriteria(dcEntrust);
            if (riEntrustDetailsList != null && riEntrustDetailsList.size() > 0) {
                for (ReceivableEntrustDetails riEntrustDetails : riEntrustDetailsList) {
                    riEntrustInvoiceList.add(riEntrustDetails.getInvoiceDetailId());
                }
            }
            if (riEntrustInvoiceList != null && riEntrustInvoiceList.size() > 0) {
                dc.add(Property.forName("invoiceDetailId").in(riEntrustInvoiceList));
            }
        }

        List<InvoiceDetails> idsList = invoiceDetailsDao.findByCriteria(dc);
        if (idsList != null && idsList.size() > 0) {
            for (InvoiceDetails ids : idsList) {
                InvoiceDetailPage idsPage = new InvoiceDetailPage();
                idsPage.setInvoiceDetailId(ids.getInvoiceDetailId());
                idsPage.setEntrustCompanyId(ids.getEntrustCompanyId());
                idsPage.setProjectId(ids.getProjectId());
                idsPage.setInvoiceDate(ids.getInvoiceDate());
                idsPage.setInvoiceType(ids.getInvoiceType());
                idsPage.setInvoiceTypeName(sysCodeService.findCodeName("INVOICE_TYPE", ids.getInvoiceType()));
                idsPage.setInvoiceValue(ids.getInvoiceValue());
                idsPage.setInvoiceNo(ids.getInvoiceNo());
                idsPage.setRemark(ids.getRemark());
                idsPage.setInputer(ids.getInputer());
                idsPage.setInputeTime(ids.getInputeTime());
                idsPage.setUpdater(ids.getUpdater());
                idsPage.setUpdateTime(ids.getUpdateTime());
                idsPage.setReceivableState(ids.getReceivableState());
                //查询该发票是否已经收款
                String strCollected = "0";//已收款标识
                String strNotCollected = "0";//未收款标识
                DetachedCriteria dcReAc = DetachedCriteria.forClass(ReceivableEntrustDetails.class);
                dcReAc.add(Property.forName("invoiceDetailId").eq(ids.getInvoiceDetailId()));
                List<ReceivableEntrustDetails> reEnDetailsListTmp = receivableEnDetailsDao.findByCriteria(dcReAc);
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
//				List<ReceivableInvoiceDetails> riDetailsList = receivableInDetailsService.findByProperty("invoiceDetailId", ids.getInvoiceDetailId());
//				if(riDetailsList!=null && riDetailsList.size()>0){
//					idsPage.setIsReceivable("1");//已收款
//				}else{
//					idsPage.setIsReceivable("0");//未收款
//				}
                idList.add(idsPage);
            }
        }
        return idList;
    }


    @Override
    public List<ReAccountDetailPage> findReAcDetail(InvoiceDetails iDetails) {
        List<ReAccountDetailPage> reAcDetailsPageList = new ArrayList<ReAccountDetailPage>();
        DetachedCriteria dc = DetachedCriteria.forClass(ReceivableAccountDetails.class);
        if (!CommonMethod.isNull(iDetails.getEntrustCompanyId())) {//委托单位
            dc.add(Property.forName("entrustCompanyId").eq(iDetails.getEntrustCompanyId()));
        }
        if (!CommonMethod.isNull(iDetails.getProjectId())) {//工程
            dc.add(Property.forName("projectId").eq(iDetails.getProjectId()));
        }

        List<ReceivableAccountDetails> reAcDetailsList = receivableAcDetailsDao.findByCriteria(dc);
        if (reAcDetailsList != null && reAcDetailsList.size() > 0) {
            for (ReceivableAccountDetails reAcDetails : reAcDetailsList) {
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
                reAcDetailsPage.setReceivableState(reAcDetails.getReceivableState());
                //收款对应的发票号码
                String allInvoiceNo = "";
                //根据收款ID查找对应的发票记录
                List<ReceivableInvoiceDetails> ReInDetailsList = receivableInDetailsService.findReInDetails(reAcDetails.getAccountDetailId(), "1");
                if (ReInDetailsList != null && ReInDetailsList.size() > 0) {
                    for (ReceivableInvoiceDetails riDetails : ReInDetailsList) {
                        InvoiceDetails inDetails = invoiceDetailsDao.findById(riDetails.getInvoiceDetailId());
                        if ("".equals(allInvoiceNo)) {
                            allInvoiceNo = inDetails.getInvoiceNo();
                        } else {
                            allInvoiceNo = allInvoiceNo + "," + inDetails.getInvoiceNo();
                        }
                    }
                }
                reAcDetailsPage.setAllInvoiceNo(allInvoiceNo);
                reAcDetailsPageList.add(reAcDetailsPage);
            }
        }
        return reAcDetailsPageList;
    }

    @Override
    public List<InvoiceDetailPage> findInvoiceDetailInvalidation(
            InvoiceDetailPage idPage, String strEntrustId) {
        List<InvoiceDetailPage> idList = new ArrayList<InvoiceDetailPage>();
        DetachedCriteria dc = DetachedCriteria.forClass(InvoiceDetails.class);
        if (idPage != null) {
            if (!CommonMethod.isNull(idPage.getEntrustCompanyId())) {//委托单位
                dc.add(Property.forName("entrustCompanyId").eq(idPage.getEntrustCompanyId()));
            }
            if (!CommonMethod.isNull(idPage.getProjectId())) {//工程
                dc.add(Property.forName("projectId").eq(idPage.getProjectId()));
            }
            if (!CommonMethod.isNull(idPage.getInvoiceType())) {//票据类型
                dc.add(Property.forName("invoiceType").eq(idPage.getInvoiceType()));
            }
            if (!CommonMethod.isNull(idPage.getInvoiceNo())) {//票据号码
                dc.add(Property.forName("invoiceNo").eq(idPage.getInvoiceNo()));
            }
            if (!CommonMethod.isNull(idPage.getInvoiceStartDate())) {//开票开始日期
                dc.add(Property.forName("invoiceDate").ge(idPage.getInvoiceStartDate()));
            }
            if (!CommonMethod.isNull(idPage.getInvoiceEndDate())) {//开票结束日期
                dc.add(Property.forName("invoiceDate").le(idPage.getInvoiceEndDate()));
            }
        }

        dc.add(Property.forName("receivableState").eq("作废"));
        if (!CommonMethod.isNull(strEntrustId)) {
            List<String> riEntrustInvoiceList = new ArrayList<String>();
            DetachedCriteria dcEntrust = DetachedCriteria.forClass(ReceivableEntrustDetails.class);
            dcEntrust.add(Property.forName("entrustId").eq(strEntrustId));
            dcEntrust.add(Property.forName("invoiceDetailId").isNotNull());
            List<ReceivableEntrustDetails> riEntrustDetailsList = receivableEnDetailsDao.findByCriteria(dcEntrust);
            if (riEntrustDetailsList != null && riEntrustDetailsList.size() > 0) {
                for (ReceivableEntrustDetails riEntrustDetails : riEntrustDetailsList) {
                    riEntrustInvoiceList.add(riEntrustDetails.getInvoiceDetailId());
                }
            }
            if (riEntrustInvoiceList != null && riEntrustInvoiceList.size() > 0) {
                dc.add(Property.forName("invoiceDetailId").in(riEntrustInvoiceList));
            }
        }

        List<InvoiceDetails> idsList = invoiceDetailsDao.findByCriteria(dc);
        if (idsList != null && idsList.size() > 0) {
            for (InvoiceDetails ids : idsList) {
                InvoiceDetailPage idsPage = new InvoiceDetailPage();
                idsPage.setInvoiceDetailId(ids.getInvoiceDetailId());
                idsPage.setEntrustCompanyId(ids.getEntrustCompanyId());
                idsPage.setProjectId(ids.getProjectId());
                idsPage.setInvoiceDate(ids.getInvoiceDate());
                idsPage.setInvoiceType(ids.getInvoiceType());
                idsPage.setInvoiceTypeName(sysCodeService.findCodeName("INVOICE_TYPE", ids.getInvoiceType()));
                idsPage.setInvoiceValue(ids.getInvoiceValue());
                idsPage.setInvoiceNo(ids.getInvoiceNo());
                idsPage.setRemark(ids.getRemark());
                idsPage.setInputer(ids.getInputer());
                idsPage.setInputeTime(ids.getInputeTime());
                idsPage.setUpdater(ids.getUpdater());
                idsPage.setUpdateTime(ids.getUpdateTime());
                idsPage.setReceivableState(ids.getReceivableState());
                //查询该发票是否已经收款
                String strCollected = "0";//已收款标识
                String strNotCollected = "0";//未收款标识
                DetachedCriteria dcReAc = DetachedCriteria.forClass(ReceivableEntrustDetails.class);
                dcReAc.add(Property.forName("invoiceDetailId").eq(ids.getInvoiceDetailId()));
                List<ReceivableEntrustDetails> reEnDetailsListTmp = receivableEnDetailsDao.findByCriteria(dcReAc);
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
//				List<ReceivableInvoiceDetails> riDetailsList = receivableInDetailsService.findByProperty("invoiceDetailId", ids.getInvoiceDetailId());
//				if(riDetailsList!=null && riDetailsList.size()>0){
//					idsPage.setIsReceivable("1");//已收款
//				}else{
//					idsPage.setIsReceivable("0");//未收款
//				}
                idList.add(idsPage);
            }
        }
        return idList;
    }

    @Override
    protected void initBaseDAO(BaseDao<InvoiceDetails> baseDao) {

    }

    @Override
    public JsonPager<InvoiceDetails> findJsonPageByCriteria(JsonPager<InvoiceDetails> jp, InvoiceDetails invoiceDetails) {
        return null;
    }
}