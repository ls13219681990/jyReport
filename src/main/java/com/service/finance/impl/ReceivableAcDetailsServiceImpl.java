package com.service.finance.impl;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.action.JsonPager;
import com.common.dao.BaseDao;
import com.common.service.BaseServiceImpl;
import com.dao.common.EntrustCompanyDao;
import com.dao.finance.EntrustAdvanceDao;
import com.dao.finance.InvoiceDetailsDao;
import com.dao.finance.ReceivableAcDetailsDao;
import com.dao.finance.ReceivableEnDetailsDao;
import com.dao.page.EntrustInfoPage;
import com.dao.page.InvoiceDetailPage;
import com.dao.page.ReAccountDetailPage;
import com.model.*;
import com.service.finance.ReceivableAcDetailsService;
import com.service.finance.ReceivableInDetailsService;
import com.service.sys.SysCodeService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("receivableAcDetailsService")
public class ReceivableAcDetailsServiceImpl extends BaseServiceImpl<ReceivableAccountDetails> implements
        ReceivableAcDetailsService {

    @Autowired
    private InvoiceDetailsDao invoiceDetailsDao;
    @Autowired
    private ReceivableAcDetailsDao receivableAcDetailsDao;
    @Autowired
    private EntrustAdvanceDao entrustAdvanceDao;
    @Autowired
    private EntrustCompanyDao entrustCompanyDao;
    @Autowired
    private ReceivableEnDetailsDao receivableEnDetailsDao;
    @Autowired
    private SysCodeService sysCodeService;
    @Autowired
    private ReceivableInDetailsService receivableInDetailsService;

	/*@Override
	@Resource(name = "receivableAcDetailsDao")
	protected void initBaseDAO(BaseDao<ReceivableAccountDetails> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<ReceivableAccountDetails> findJsonPageByCriteria(JsonPager<ReceivableAccountDetails> jp,
			ReceivableAccountDetails t) {
		return null;
	}*/

    @Override
    public void saveReAcDetails(ReAccountDetailPage reAcDetailPage, String userId) {
        if (CommonMethod.isNull(reAcDetailPage.getProjectId())) {
            throw new BusinessException("工程不能为空！", "");
        }
        if (CommonMethod.isNull(reAcDetailPage.getEntrustCompanyId())) {
            throw new BusinessException("委托单位不能为空！", "");
        }
        if (CommonMethod.isNull(reAcDetailPage.getAccountType())) {
            throw new BusinessException("收款方式不能为空！", "");
        }
        //委托单位
        EntrustCompany ec = entrustCompanyDao.findById(reAcDetailPage.getEntrustCompanyId());
        //充值预收金额
//		if("01".equals(reAcDetailPage.getAccountType())){
//			EntrustAdvance ea = new EntrustAdvance();
//			ea.setEntrustAdvanceId(CommonMethod.getNewKey());
//			ea.setEntrustCompanyId(reAcDetailPage.getEntrustCompanyId());
//			ea.setProjectId(reAcDetailPage.getProjectId());
//			ea.setAdvanceMoney(reAcDetailPage.getAccountValue());
//			ea.setAdvanceStatus("01");//充值
//			ea.setRemark(reAcDetailPage.getRemark());
//			ea.setInputer(userId);
//			ea.setInputeTime(CommonMethod.getDate());
//			entrustAdvanceDao.save(ea);
//			
//			//委托单位已有预收金额+充值到委托单位的预收金额
//			double advanceMoney = ec.getAdvancesReceived()+reAcDetailPage.getAccountValue();
//			ec.setAdvancesReceived(advanceMoney);
//			entrustCompanyDao.update(ec);
//		}else
        if ("02".equals(reAcDetailPage.getAccountType())) {//预充值金额用于收款
            //委托单位已有预收金额
            double advanceMoney = ec.getAdvancesReceived();
            if (reAcDetailPage.getAccountValue() > advanceMoney) {//收款金额大于委托单位原有金额
                throw new BusinessException("收款金额：" + reAcDetailPage.getAccountValue() + "元，大于该单位拥有金额：" + advanceMoney + "元，不能收款！", "");
            }
            ec.setAdvancesReceived(advanceMoney - reAcDetailPage.getAccountValue());
            entrustCompanyDao.update(ec);

            EntrustAdvance ea = new EntrustAdvance();
            ea.setEntrustAdvanceId(CommonMethod.getNewKey());
            ea.setEntrustCompanyId(reAcDetailPage.getEntrustCompanyId());
            ea.setProjectId(reAcDetailPage.getProjectId());
            ea.setAdvanceMoney(reAcDetailPage.getAccountValue());
            ea.setAdvanceStatus("02");//预充值金额用于收款
            ea.setRemark(reAcDetailPage.getRemark());
            ea.setInputer(userId);
            ea.setInputeTime(CommonMethod.getDate());
            entrustAdvanceDao.save(ea);
        }

        //收款记录
        ReceivableAccountDetails reAcDetail = new ReceivableAccountDetails();
        reAcDetail.setAccountDetailId(CommonMethod.getNewKey());
        reAcDetail.setEntrustCompanyId(reAcDetailPage.getEntrustCompanyId());
        reAcDetail.setProjectId(reAcDetailPage.getProjectId());
        reAcDetail.setAccountType(reAcDetailPage.getAccountType());
        reAcDetail.setAccountDate(reAcDetailPage.getAccountDate());
        reAcDetail.setAccountValue(reAcDetailPage.getAccountValue());
        reAcDetail.setContractCode(reAcDetailPage.getContractCode());
        reAcDetail.setRemark(reAcDetailPage.getRemark());
        reAcDetail.setInputer(userId);
        reAcDetail.setInputeTime(CommonMethod.getDate());
        receivableAcDetailsDao.save(reAcDetail);

        List<EntrustInfoPage> entrustIdList = reAcDetailPage.getEntrustIdList();
        for (EntrustInfoPage enInfoPage : entrustIdList) {
            ReceivableEntrustDetails reEnDetail = new ReceivableEntrustDetails();
            reEnDetail.setReceivableEntrustDetailId(CommonMethod.getNewKey());
            reEnDetail.setAccountDetailId(reAcDetail.getAccountDetailId());
            reEnDetail.setEntrustId(enInfoPage.getEntrustId());
            reEnDetail.setInputer(userId);
            reEnDetail.setInputeTime(CommonMethod.getDate());
            receivableEnDetailsDao.save(reEnDetail);
        }
    }

    @Override
    public void updateReAcDetails(ReAccountDetailPage reAcDetailPage, String userId) {
        if (CommonMethod.isNull(reAcDetailPage.getAccountDetailId())) {
            throw new BusinessException("收款记录ID不能为空！", "");
        }
        if (CommonMethod.isNull(reAcDetailPage.getProjectId())) {
            throw new BusinessException("工程不能为空！", "");
        }
        if (CommonMethod.isNull(reAcDetailPage.getEntrustCompanyId())) {
            throw new BusinessException("委托单位不能为空！", "");
        }
        if (CommonMethod.isNull(reAcDetailPage.getAccountType())) {
            throw new BusinessException("收款方式不能为空！", "");
        }

        //收款记录
        ReceivableAccountDetails reAcDetail = receivableAcDetailsDao.findById(reAcDetailPage.getAccountDetailId());
        if ("01".equals(reAcDetail.getAccountType()) || "02".equals(reAcDetail.getAccountType())) {//充值和收款的记录不能修改
            throw new BusinessException("收款方式为预充值的记录和收款方式为预充值收款的记录不能修改！", "");
        }
        if ("01".equals(reAcDetailPage.getAccountType()) || "02".equals(reAcDetailPage.getAccountType())) {//充值和收款的记录不能修改
            throw new BusinessException("其他收款方式不能修改为预充值或者预充值收款！", "");
        }
        reAcDetail.setEntrustCompanyId(reAcDetailPage.getEntrustCompanyId());
        reAcDetail.setProjectId(reAcDetailPage.getProjectId());
        reAcDetail.setAccountType(reAcDetailPage.getAccountType());
        reAcDetail.setAccountDate(reAcDetailPage.getAccountDate());
        reAcDetail.setAccountValue(reAcDetailPage.getAccountValue());
        reAcDetail.setContractCode(reAcDetailPage.getContractCode());
        reAcDetail.setRemark(reAcDetailPage.getRemark());
        reAcDetail.setUpdater(userId);
        reAcDetail.setUpdateTime(CommonMethod.getDate());
        reAcDetail.setReceivableState(reAcDetailPage.getReceivableState());
        receivableAcDetailsDao.update(reAcDetail);
    }

    @Override
    public List<ReAccountDetailPage> findReAcDetail(ReAccountDetailPage reAcPage, String strEntrustId) {
        List<ReAccountDetailPage> reAcDetailPageList = new ArrayList<ReAccountDetailPage>();

        DetachedCriteria dc = DetachedCriteria.forClass(ReceivableAccountDetails.class);
        if (reAcPage != null) {
            if (!CommonMethod.isNull(reAcPage.getEntrustCompanyId())) {//委托单位
                dc.add(Property.forName("entrustCompanyId").eq(reAcPage.getEntrustCompanyId()));
            }
            if (!CommonMethod.isNull(reAcPage.getProjectId())) {//工程
                dc.add(Property.forName("projectId").eq(reAcPage.getProjectId()));
            }
            if (!CommonMethod.isNull(reAcPage.getAccountType())) {//收款类型
                dc.add(Property.forName("accountType").eq(reAcPage.getAccountType()));
            }
            if (!CommonMethod.isNull(reAcPage.getInvoiceNo())) {//票据号码
                List<String> acDetailIdList = new ArrayList<String>();
                //根据票据号码查找对应的开票记录
                List<InvoiceDetails> idsList = invoiceDetailsDao.findByProperty("invoiceNo", reAcPage.getInvoiceNo());
                if (idsList != null && idsList.size() > 0) {//
                    for (InvoiceDetails iDetail : idsList) {
                        DetachedCriteria dcReAc = DetachedCriteria.forClass(ReceivableEntrustDetails.class);
                        dcReAc.add(Property.forName("invoiceDetailId").eq(iDetail.getInvoiceDetailId()));
                        List<ReceivableEntrustDetails> reEnDetailsListTmp = receivableEnDetailsDao.findByCriteria(dcReAc);
                        if (reEnDetailsListTmp != null && reEnDetailsListTmp.size() > 0) {
                            for (ReceivableEntrustDetails reEnDetailsTmp : reEnDetailsListTmp) {
                                DetachedCriteria dcAc = DetachedCriteria.forClass(ReceivableEntrustDetails.class);
                                dcAc.add(Property.forName("entrustId").eq(reEnDetailsTmp.getEntrustId()));
                                dcAc.add(Property.forName("accountDetailId").isNotNull());
                                List<ReceivableEntrustDetails> riDetailsListTmpIn = receivableEnDetailsDao.findByCriteria(dcAc);
                                if (riDetailsListTmpIn != null && riDetailsListTmpIn.size() > 0) {
                                    for (ReceivableEntrustDetails riDetailsTmpIn : riDetailsListTmpIn) {
                                        acDetailIdList.add(riDetailsTmpIn.getAccountDetailId());
                                    }
                                }
                            }
                        }
                    }
                }
                if (acDetailIdList != null && acDetailIdList.size() > 0) {
                    dc.add(Property.forName("accountDetailId").in(acDetailIdList));
                }
            }
            if (!CommonMethod.isNull(reAcPage.getAccountStartDate())) {//收款开始日期
                dc.add(Property.forName("accountDate").ge(reAcPage.getAccountStartDate()));
            }
            if (!CommonMethod.isNull(reAcPage.getAccountEndDate())) {//收款结束日期
                dc.add(Property.forName("accountDate").le(reAcPage.getAccountEndDate()));
            }
            if (!CommonMethod.isNull(reAcPage.getContractCode())) {//合同号
                dc.add(Property.forName("contractCode").eq(reAcPage.getContractCode()));
            }
        }
        dc.add(Property.forName("receivableState").ne("作废"));
        if (!CommonMethod.isNull(strEntrustId)) {
            List<String> riEntrustAccountList = new ArrayList<String>();
            DetachedCriteria dcEntrust = DetachedCriteria.forClass(ReceivableEntrustDetails.class);
            dcEntrust.add(Property.forName("entrustId").eq(strEntrustId));
            dcEntrust.add(Property.forName("accountDetailId").isNotNull());
            List<ReceivableEntrustDetails> riEntrustDetailsList = receivableEnDetailsDao.findByCriteria(dcEntrust);
            if (riEntrustDetailsList != null && riEntrustDetailsList.size() > 0) {
                for (ReceivableEntrustDetails riEntrustDetails : riEntrustDetailsList) {
                    riEntrustAccountList.add(riEntrustDetails.getAccountDetailId());
                }
            }
            if (riEntrustAccountList != null && riEntrustAccountList.size() > 0) {
                dc.add(Property.forName("accountDetailId").in(riEntrustAccountList));
            }
        }

        List<ReceivableAccountDetails> reAcDetailList = receivableAcDetailsDao.findByCriteria(dc);
        if (reAcDetailList != null && reAcDetailList.size() > 0) {
            for (ReceivableAccountDetails reAcDetails : reAcDetailList) {
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
                DetachedCriteria dcEnIn = DetachedCriteria.forClass(ReceivableEntrustDetails.class);
                dcEnIn.add(Property.forName("accountDetailId").eq(reAcDetails.getAccountDetailId()));
                List<ReceivableEntrustDetails> reEnDetailsListTmp = receivableEnDetailsDao.findByCriteria(dcEnIn);
                if (reEnDetailsListTmp != null && reEnDetailsListTmp.size() > 0) {
                    for (ReceivableEntrustDetails reEnDetailsTmp : reEnDetailsListTmp) {
                        DetachedCriteria dcIn = DetachedCriteria.forClass(ReceivableEntrustDetails.class);
                        dcIn.add(Property.forName("entrustId").eq(reEnDetailsTmp.getEntrustId()));
                        dcIn.add(Property.forName("invoiceDetailId").isNotNull());
                        List<ReceivableEntrustDetails> riDetailsListTmpIn = receivableEnDetailsDao.findByCriteria(dcIn);
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
                reAcDetailPageList.add(reAcDetailsPage);
            }
        }
        return reAcDetailPageList;
    }


    @Override
    public List<ReAccountDetailPage> findReAcDetailInvalidation(ReAccountDetailPage reAcPage, String strEntrustId) {
        List<ReAccountDetailPage> reAcDetailPageList = new ArrayList<ReAccountDetailPage>();

        DetachedCriteria dc = DetachedCriteria.forClass(ReceivableAccountDetails.class);
        if (reAcPage != null) {
            if (!CommonMethod.isNull(reAcPage.getEntrustCompanyId())) {//委托单位
                dc.add(Property.forName("entrustCompanyId").eq(reAcPage.getEntrustCompanyId()));
            }
            if (!CommonMethod.isNull(reAcPage.getProjectId())) {//工程
                dc.add(Property.forName("projectId").eq(reAcPage.getProjectId()));
            }
            if (!CommonMethod.isNull(reAcPage.getAccountType())) {//收款类型
                dc.add(Property.forName("accountType").eq(reAcPage.getAccountType()));
            }
            if (!CommonMethod.isNull(reAcPage.getInvoiceNo())) {//票据号码
                List<String> acDetailIdList = new ArrayList<String>();
                //根据票据号码查找对应的开票记录
                List<InvoiceDetails> idsList = invoiceDetailsDao.findByProperty("invoiceNo", reAcPage.getInvoiceNo());
                if (idsList != null && idsList.size() > 0) {//
                    for (InvoiceDetails iDetail : idsList) {
                        DetachedCriteria dcReAc = DetachedCriteria.forClass(ReceivableEntrustDetails.class);
                        dcReAc.add(Property.forName("invoiceDetailId").eq(iDetail.getInvoiceDetailId()));
                        List<ReceivableEntrustDetails> reEnDetailsListTmp = receivableEnDetailsDao.findByCriteria(dcReAc);
                        if (reEnDetailsListTmp != null && reEnDetailsListTmp.size() > 0) {
                            for (ReceivableEntrustDetails reEnDetailsTmp : reEnDetailsListTmp) {
                                DetachedCriteria dcAc = DetachedCriteria.forClass(ReceivableEntrustDetails.class);
                                dcAc.add(Property.forName("entrustId").eq(reEnDetailsTmp.getEntrustId()));
                                dcAc.add(Property.forName("accountDetailId").isNotNull());
                                List<ReceivableEntrustDetails> riDetailsListTmpIn = receivableEnDetailsDao.findByCriteria(dcAc);
                                if (riDetailsListTmpIn != null && riDetailsListTmpIn.size() > 0) {
                                    for (ReceivableEntrustDetails riDetailsTmpIn : riDetailsListTmpIn) {
                                        acDetailIdList.add(riDetailsTmpIn.getAccountDetailId());
                                    }
                                }
                            }
                        }
                    }
                }
                if (acDetailIdList != null && acDetailIdList.size() > 0) {
                    dc.add(Property.forName("accountDetailId").in(acDetailIdList));
                }
            }
            if (!CommonMethod.isNull(reAcPage.getAccountStartDate())) {//收款开始日期
                dc.add(Property.forName("accountDate").ge(reAcPage.getAccountStartDate()));
            }
            if (!CommonMethod.isNull(reAcPage.getAccountEndDate())) {//收款结束日期
                dc.add(Property.forName("accountDate").le(reAcPage.getAccountEndDate()));
            }
            if (!CommonMethod.isNull(reAcPage.getContractCode())) {//合同号
                dc.add(Property.forName("contractCode").eq(reAcPage.getContractCode()));
            }
        }
        dc.add(Property.forName("receivableState").eq("作废"));
        if (!CommonMethod.isNull(strEntrustId)) {
            List<String> riEntrustAccountList = new ArrayList<String>();
            DetachedCriteria dcEntrust = DetachedCriteria.forClass(ReceivableEntrustDetails.class);
            dcEntrust.add(Property.forName("entrustId").eq(strEntrustId));
            dcEntrust.add(Property.forName("accountDetailId").isNotNull());
            List<ReceivableEntrustDetails> riEntrustDetailsList = receivableEnDetailsDao.findByCriteria(dcEntrust);
            if (riEntrustDetailsList != null && riEntrustDetailsList.size() > 0) {
                for (ReceivableEntrustDetails riEntrustDetails : riEntrustDetailsList) {
                    riEntrustAccountList.add(riEntrustDetails.getAccountDetailId());
                }
            }
            if (riEntrustAccountList != null && riEntrustAccountList.size() > 0) {
                dc.add(Property.forName("accountDetailId").in(riEntrustAccountList));
            }
        }

        List<ReceivableAccountDetails> reAcDetailList = receivableAcDetailsDao.findByCriteria(dc);
        if (reAcDetailList != null && reAcDetailList.size() > 0) {
            for (ReceivableAccountDetails reAcDetails : reAcDetailList) {
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
                DetachedCriteria dcEnIn = DetachedCriteria.forClass(ReceivableEntrustDetails.class);
                dcEnIn.add(Property.forName("accountDetailId").eq(reAcDetails.getAccountDetailId()));
                List<ReceivableEntrustDetails> reEnDetailsListTmp = receivableEnDetailsDao.findByCriteria(dcEnIn);
                if (reEnDetailsListTmp != null && reEnDetailsListTmp.size() > 0) {
                    for (ReceivableEntrustDetails reEnDetailsTmp : reEnDetailsListTmp) {
                        DetachedCriteria dcIn = DetachedCriteria.forClass(ReceivableEntrustDetails.class);
                        dcIn.add(Property.forName("entrustId").eq(reEnDetailsTmp.getEntrustId()));
                        dcIn.add(Property.forName("invoiceDetailId").isNotNull());
                        List<ReceivableEntrustDetails> riDetailsListTmpIn = receivableEnDetailsDao.findByCriteria(dcIn);
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
                reAcDetailPageList.add(reAcDetailsPage);
            }
        }
        return reAcDetailPageList;
    }


    @Override
    public List<InvoiceDetailPage> findinDetailPageList(ReceivableAccountDetails reAcDetail) {
        List<InvoiceDetailPage> inDetailsPageList = new ArrayList<InvoiceDetailPage>();
        DetachedCriteria dc = DetachedCriteria.forClass(InvoiceDetails.class);
        dc.add(Property.forName("entrustCompanyId").eq(reAcDetail.getEntrustCompanyId()));
        dc.add(Property.forName("projectId").eq(reAcDetail.getProjectId()));

        List<InvoiceDetails> inDetailsList = invoiceDetailsDao.findByCriteria(dc);
        if (inDetailsList != null && inDetailsList.size() > 0) {
            for (InvoiceDetails ids : inDetailsList) {
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
                idsPage.setReceivableState(ids.getReceivableState());
                //查询该发票是否已经收款
                List<ReceivableInvoiceDetails> riDetailsList = receivableInDetailsService.findByProperty("invoiceDetailId", ids.getInvoiceDetailId());
                if (riDetailsList != null && riDetailsList.size() > 0) {
                    idsPage.setIsReceivable("1");//已收款
                } else {
                    idsPage.setIsReceivable("0");//未收款
                }
                inDetailsPageList.add(idsPage);
            }
        }
        return inDetailsPageList;
    }

    @Override
    protected void initBaseDAO(BaseDao<ReceivableAccountDetails> baseDao) {

    }

    @Override
    public JsonPager<ReceivableAccountDetails> findJsonPageByCriteria(JsonPager<ReceivableAccountDetails> jp, ReceivableAccountDetails receivableAccountDetails) {
        return null;
    }
}