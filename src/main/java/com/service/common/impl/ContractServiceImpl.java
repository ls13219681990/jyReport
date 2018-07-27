package com.service.common.impl;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.dao.common.CapitalAccountDao;
import com.dao.common.ContractDao;
import com.dao.common.EntrustCompanyDao;
import com.dao.entrust.EntrustInfoDao;
import com.dao.finance.ReceivableAcDetailsDao;
import com.dao.finance.ReceivableEnDetailsDao;
import com.dao.page.ContractPage;
import com.model.*;
import com.service.common.ContractService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service("contractService")
public class ContractServiceImpl extends BaseServiceImpl<Agreement> implements
        ContractService {

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private CapitalAccountDao capitalAccountDao;

    @Autowired
    private EntrustInfoDao entrustInfoDao;

    @Autowired
    private ReceivableEnDetailsDao receivableEnDetailsDao;

    @Autowired
    private ReceivableAcDetailsDao receivableAcDetailsDao;
    @Autowired
    private EntrustCompanyDao entrustCompanyDao;

    @Override
    @Resource(name = "contractDao")
    protected void initBaseDAO(BaseDao<Agreement> baseDao) {
        setBaseDao(baseDao);
    }

    @Override
    public JsonPager<Agreement> findJsonPageByCriteria(JsonPager<Agreement> jp,
                                                       Agreement t) {
        return null;
    }

    @Override
    public void saveContract(Collection<ContractPage> coll, String userId) {
        for (ContractPage cap : coll) {
            Agreement ca = new Agreement();
            //合同号已经存在
            if (findContractCode(cap.getContractCode())) {
                throw new BusinessException("输入的合同号已经存在，请重新输入！", "");
            }
            if (CommonMethod.isNull(cap.getEntrustCompanyId())) {
                throw new BusinessException("输入的委托单位不能为空，请确认！", "");
            }
            if (CommonMethod.isNull(cap.getProjectId())) {
                throw new BusinessException("输入的工程不能为空，请确认！", "");
            }
//			//委托单位
//			EntrustCompany eCompany = new EntrustCompany();
//			//委托单位ID
//			String eCompanyId = "";
//			List<EntrustCompany> ecList= entrustCompanyDao.findByProperty("entrustCompanyName", cap.getEntrustCompanyName());
//			if(ecList!=null && ecList.size()>0){
//				eCompanyId = ecList.get(0).getEntrustCompanyId();
//			}else{
//				eCompanyId = CommonMethod.getNewKey();
//				eCompany.setEntrustCompanyId(eCompanyId);
//				eCompany.setEntrustCompanyName(cap.getEntrustCompanyName());
//				eCompany.setEntrustCompanyNo(findEntrustCompanyNo());
//				eCompany.setInputer(userId);
//				eCompany.setInputeTime(CommonMethod.getDate());
//				entrustCompanyDao.save(eCompany);
//			}
//			
//			//工程
//			ProjectInfo pInfo = new ProjectInfo();
//			//工程ID
//			String pInfoId = "";
//			List<ProjectInfo> pjList= projectInfoDao.findByProperty("projectName", cap.getProjectName());
//			if(pjList!=null && pjList.size()>0){
//				pInfoId = pjList.get(0).getProjectId();
//			}else{
//				pInfoId = CommonMethod.getNewKey();
//				pInfo.setProjectId(pInfoId);
//				pInfo.setProjectName(cap.getProjectName());
//				pInfo.setLinkMan(cap.getLinkMan());
//				pInfo.setLinkPhone(cap.getLinkPhone());
//				pInfo.setInputer(userId);
//				pInfo.setInputeTime(CommonMethod.getDate());
//				projectInfoDao.save(pInfo);
//			}
            ca.setContractId(CommonMethod.getNewKey());
            ca.setContractCode(cap.getContractCode());
            ca.setEntrustCompanyId(cap.getEntrustCompanyId());
            ca.setProjectId(cap.getProjectId());
            ca.setCheckArea(cap.getCheckArea());
            ca.setCopiesCount(cap.getCopiesCount());
            ca.setProjectNameTag(cap.getProjectNameTag());
//			ca.setImageProgress(cap.getImageProgress());
            ca.setPaymentNode(cap.getPaymentNode());
            ca.setContractChapter(cap.getContractChapter());
            ca.setLinkMan(cap.getLinkMan());
            ca.setLinkPhone(cap.getLinkPhone());
            ca.setProjectManager(cap.getProjectManager());
            ca.setContractIsReturn(cap.getContractIsReturn());
            ca.setManagementDepartmentId(cap.getManagementDepartmentId());
            ca.setContractType(cap.getContractType());
            ca.setAccountType(cap.getAccountType());
            ca.setReceivedMoney(cap.getReceivedMoney());
            ca.setContractMoney(cap.getContractMoney());
            ca.setRemark(cap.getRemark());
            ca.setInputer(userId);
            ca.setInputeTime(CommonMethod.getDate());
            contractDao.save(ca);
        }
    }

    @Override
    public void updateContract(Collection<ContractPage> coll, String userId) {
        for (ContractPage cap : coll) {
            Agreement ca = contractDao.findById(cap.getContractId());
            if (ca == null) {
                throw new BusinessException("合同ID" + cap.getContractId() + "不存在，请确认！", "");
            }
            DetachedCriteria dc = DetachedCriteria.forClass(Agreement.class);
            dc.add(Property.forName("contractId").ne(cap.getContractId()));
            dc.add(Property.forName("contractCode").eq(cap.getContractCode()));
            List<Agreement> ecList = contractDao.findByCriteria(dc);
            if (ecList != null && ecList.size() > 0) {
                throw new BusinessException("该合同号已经存在！", "");
            }
            if (!ca.getContractCode().equals(cap.getContractCode())) {
                List<CapitalAccount> caList = capitalAccountDao.findByProperty("contractId", cap.getContractId());
                if (caList != null && caList.size() > 0) {
                    for (CapitalAccount cAccount : caList) {
                        cAccount.setContractCode(cap.getContractCode());
                        cAccount.setUpdater(userId);
                        cAccount.setUpdateTime(CommonMethod.getDate());
                        capitalAccountDao.update(cAccount);
                    }
                }
                List<EntrustInfo> eiList = entrustInfoDao.findByProperty("contractCode", ca.getContractCode());
                if (eiList != null && eiList.size() > 0) {
                    for (EntrustInfo entrustInfo : eiList) {
                        entrustInfo.setContractCode(cap.getContractCode());
                        entrustInfo.setUpdater(userId);
                        entrustInfo.setUpdateTime(CommonMethod.getDate());
                        entrustInfoDao.update(entrustInfo);
                    }
                }
            }

            ca.setContractCode(cap.getContractCode());
            ca.setEntrustCompanyId(cap.getEntrustCompanyId());
            ca.setProjectId(cap.getProjectId());
            ca.setCheckArea(cap.getCheckArea());
            ca.setCopiesCount(cap.getCopiesCount());
            ca.setProjectNameTag(cap.getProjectNameTag());
//			ca.setImageProgress(cap.getImageProgress());
            ca.setPaymentNode(cap.getPaymentNode());
            ca.setContractChapter(cap.getContractChapter());
            ca.setLinkMan(cap.getLinkMan());
            ca.setLinkPhone(cap.getLinkPhone());
            ca.setProjectManager(cap.getProjectManager());
            ca.setContractIsReturn(cap.getContractIsReturn());
            ca.setManagementDepartmentId(cap.getManagementDepartmentId());
            ca.setContractType(cap.getContractType());
            ca.setAccountType(cap.getAccountType());
            ca.setReceivedMoney(cap.getReceivedMoney());
            ca.setContractMoney(cap.getContractMoney());
            ca.setRemark(cap.getRemark());
            ca.setUpdater(userId);
            ca.setUpdateTime(CommonMethod.getDate());
            contractDao.update(ca);
        }
    }

    @Override
    public List<ContractPage> findCaList(ContractPage cap, String strSort) {
        List<ContractPage> contractPageList = contractDao.findContractListSQL(cap, strSort);
        for (ContractPage contractPage : contractPageList) {
            double receivedPrice = 0;//合同已收款（统计）
            double remainderPrice = 0;//合同未收款（统计）
            //委托信息
            List<EntrustInfo> eiList = entrustInfoDao.findByProperty("contractCode", contractPage.getContractCode());
            if (eiList != null && eiList.size() > 0) {
                for (EntrustInfo entrustInfo : eiList) {
                    List<ReceivableEntrustDetails> redList = receivableEnDetailsDao.findByProperty("entrustId", entrustInfo.getEntrustId());
                    for (ReceivableEntrustDetails red : redList) {
                        ReceivableAccountDetails rad = receivableAcDetailsDao.findById(red.getAccountDetailId());
                        receivedPrice = receivedPrice + rad.getAccountValue();
                    }
                }
            } else {
                receivedPrice = 0;
            }
            if (contractPage.getContractMoney() == null) {
                remainderPrice = 0;
            } else {
                remainderPrice = contractPage.getContractMoney() - receivedPrice;
            }
            contractPage.setReceivedPrice(receivedPrice);
            contractPage.setRemainderPrice(remainderPrice);

            List<EntrustCompany> ecList = new ArrayList<EntrustCompany>();
            String[] entrustCompanyId = contractPage.getEntrustCompanyId().split("|");
            for (int i = 0; i < entrustCompanyId.length; i++) {
                EntrustCompany ec = entrustCompanyDao.findById(entrustCompanyId[i]);
                ecList.add(ec);
            }
            contractPage.setEcList(ecList);
        }

        return contractPageList;
    }

    public List<ContractPage> findCaListById(ContractPage cap, String strSort) {
        List<ContractPage> contractPageList = contractDao.findContractByIdListSQL(cap, strSort);
        return contractPageList;
    }

    private boolean findContractCode(String contractCode) {
        boolean isCaCode = false;
        DetachedCriteria dc = DetachedCriteria.forClass(Agreement.class);
        dc.add(Property.forName("contractCode").eq(contractCode));
        dc.addOrder(Order.desc("contractCode"));
        List<Agreement> caList = contractDao.findByCriteria(dc);
        if (caList != null && caList.size() > 0) {
            isCaCode = true;
        }
        return isCaCode;
    }

//	private String findEntrustCompanyNo(){
//		String entrustCompanyNo = "";
//		String maxEntrustCompanyNo = entrustCompanyDao.findMaxEntrustCompanyNo(); 
//		if("0".equals(maxEntrustCompanyNo)){
//			entrustCompanyNo = "1";
//		}else{
//			Integer tmpEntrustCompanyNo = Integer.valueOf(maxEntrustCompanyNo)+1;
//			entrustCompanyNo = tmpEntrustCompanyNo.toString();
//		}
//		return entrustCompanyNo;
//	}
}