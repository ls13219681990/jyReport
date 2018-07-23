package com.service.common.impl;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.action.JsonPager;
import com.common.dao.BaseDao;
import com.common.service.BaseServiceImpl;
import com.dao.common.*;
import com.dao.entrust.EntrustInfoDao;
import com.dao.page.CapitalAccountLinkmpPage;
import com.dao.page.CapitalAccountPage;
import com.model.*;
import com.service.common.CapitalAccountService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service("capitalAccountService")
public class CapitalAccountServiceImpl extends BaseServiceImpl<CapitalAccount>
        implements CapitalAccountService {

    @Autowired
    private CapitalAccountDao capitalAccountDao;

    @Autowired
    private EntrustCompanyDao entrustCompanyDao;

    @Autowired
    private ProjectInfoDao projectInfoDao;

    @Autowired
    private SupervisionUnitDao supervisionUnitDao;

    @Autowired
    private CapitalAccountLinkmpDao capitalAccountLinkmpDao;
    @Autowired
    private EntrustInfoDao entrustInfoDao;

    @Override
    @Resource(name = "capitalAccountDao")
    protected void initBaseDAO(BaseDao<CapitalAccount> baseDao) {
        setBaseDao(baseDao);
    }

    @Override
    public JsonPager<CapitalAccount> findJsonPageByCriteria(
            JsonPager<CapitalAccount> jp, CapitalAccount t) {
        return null;
    }

    @Override
    public String saveCapitalAccount(Collection<CapitalAccountPage> coll,
                                     String userId) {
        String capitalAccountId = "";
        for (CapitalAccountPage cap : coll) {
            CapitalAccount ca = new CapitalAccount();
            // 资金账号为空
            if (CommonMethod.isNull(cap.getCapitalAccountCode())) {
                ca.setCapitalAccountCode(findNewCaCode());
            } else {
                // 资金账号已经存在
                if (findCaCode(cap.getCapitalAccountCode())) {
                    throw new BusinessException("输入的资金账号已经存在，请重新输入！", "");
                }
            }
            DetachedCriteria dc = DetachedCriteria
                    .forClass(CapitalAccount.class);
            dc.add(Property.forName("entrustCompanyId").eq(
                    cap.getEntrustCompanyId()));
            dc.add(Property.forName("projectId").eq(cap.getProjectId()));
            List<CapitalAccount> caList = capitalAccountDao.findByCriteria(dc);
            if (caList != null && caList.size() > 0) {
                throw new BusinessException("该资金账号已经存在！", "");
            }

            ca.setCapitalAccountId(CommonMethod.getNewKey());
            ca.setEntrustCompanyId(cap.getEntrustCompanyId());
            ca.setProjectId(cap.getProjectId());
            ca.setSupervisionUnitId(cap.getSupervisionUnitId());
            ca.setAccountType2(cap.getAccountType2());
            ca.setAccountKinds(cap.getAccountKinds());
            ca.setContractId(cap.getContractId());
            ca.setContractCode(cap.getContractCode());
            ca.setPrintNum(cap.getPrintNum() == null ? 0 : Integer.valueOf(cap
                    .getPrintNum()));
            ca.setRemark(cap.getRemark());
            ca.setInputer(userId);
            ca.setInputeTime(CommonMethod.getDate());
            ca.setLinkMan(cap.getEntrustLinkMan());
            ca.setLinkPhone(cap.getEntrustLinkPhone());
            ca.setProjectRemark(cap.getProjectRemark());
            capitalAccountDao.save(ca);

            capitalAccountId = ca.getCapitalAccountId();
        }
        return capitalAccountId;
    }

    @Override
    public void saveCapitalAccountLinkmp(CapitalAccountLinkmpPage calPage) {
        CapitalAccountLinkmp cal = new CapitalAccountLinkmp();

        if (CommonMethod.isNull(calPage.getCapitalAccountLinkmpId())) {
            cal.setCapitalAccountLinkmpId(CommonMethod.getNewKey());
            cal.setCapitalAccountId(calPage.getCapitalAccountId());
            cal.setLinkMan(calPage.getLinkMan());
            cal.setLinkPhone(calPage.getLinkPhone());
            capitalAccountLinkmpDao.save(cal);
        } else {
            cal = capitalAccountLinkmpDao.findById(calPage
                    .getCapitalAccountLinkmpId());
            cal.setLinkMan(calPage.getLinkMan());
            cal.setLinkPhone(calPage.getLinkPhone());
            capitalAccountLinkmpDao.update(cal);
        }

    }

    @Override
    public void saveCapitalAccountTable(Collection<CapitalAccountPage> coll,
                                        String userId) {
        for (CapitalAccountPage cap : coll) {
            // 委托单位
            EntrustCompany eCompany = new EntrustCompany();
            // 委托单位ID
            String eCompanyId = "";
            List<EntrustCompany> ecList = entrustCompanyDao.findByProperty(
                    "entrustCompanyName", cap.getEntrustCompanyName());
            if (ecList != null && ecList.size() > 0) {
                eCompanyId = ecList.get(0).getEntrustCompanyId();
            } else {
                eCompanyId = CommonMethod.getNewKey();
                eCompany.setEntrustCompanyId(eCompanyId);
                eCompany.setEntrustCompanyName(cap.getEntrustCompanyName());
                eCompany.setEntrustCompanyNo(findEntrustCompanyNo());
                eCompany.setInputer(userId);
                eCompany.setInputeTime(CommonMethod.getDate());
                entrustCompanyDao.save(eCompany);
            }

            // 工程
            ProjectInfo pInfo = new ProjectInfo();
            // 工程ID
            String pInfoId = "";
            List<ProjectInfo> pjList = projectInfoDao.findByProperty(
                    "projectName", cap.getProjectName());
            if (pjList != null && pjList.size() > 0) {
                pInfoId = pjList.get(0).getProjectId();
            } else {
                pInfoId = CommonMethod.getNewKey();
                pInfo.setProjectId(pInfoId);
                pInfo.setProjectName(cap.getProjectName());
                pInfo.setLinkMan(cap.getLinkMan());
                pInfo.setLinkPhone(cap.getLinkPhone());
                pInfo.setInputer(userId);
                pInfo.setInputeTime(CommonMethod.getDate());
                projectInfoDao.save(pInfo);
            }

            // 监理单位
            SupervisionUnit sUnit = new SupervisionUnit();
            // 监理单位ID
            String sUnitId = "";
            List<SupervisionUnit> suList = supervisionUnitDao.findByProperty(
                    "supervisionUnitName", cap.getSupervisionUnitName());
            if (suList != null && suList.size() > 0) {
                sUnitId = suList.get(0).getSupervisionUnitId();
            } else {
                sUnitId = CommonMethod.getNewKey();
                sUnit.setSupervisionUnitId(sUnitId);
                sUnit.setSupervisionUnitName(cap.getSupervisionUnitName());
                sUnit.setWitnesses(cap.getWitness());
                sUnit.setInputer(userId);
                sUnit.setInputeTime(CommonMethod.getDate());
                supervisionUnitDao.save(sUnit);
            }

            // 资金账号
            String capitalAccountCode = "";
            // 资金账号为空
            if (CommonMethod.isNull(cap.getCapitalAccountCode())) {
                capitalAccountCode = findNewCaCode();
            } else {
                capitalAccountCode = cap.getCapitalAccountCode();
                // 资金账号已经存在
                if (findCaCode(cap.getCapitalAccountCode())) {
                    throw new BusinessException("输入的资金账号已经存在，请重新输入！", "");
                }
            }
            CapitalAccount ca = new CapitalAccount();
            ca.setCapitalAccountCode(capitalAccountCode);
            ca.setCapitalAccountId(CommonMethod.getNewKey());
            ca.setEntrustCompanyId(eCompanyId);
            ca.setProjectId(pInfoId);
            ca.setSupervisionUnitId(sUnitId);
            ca.setAccountType2(cap.getAccountType2());
            ca.setAccountKinds(cap.getAccountKinds());
            ca.setContractId(cap.getContractId());
            ca.setContractCode(cap.getContractCode());
            ca.setPrintNum(cap.getPrintNum() == null ? 0 : Integer.valueOf(cap
                    .getPrintNum()));
            ca.setInputer(userId);
            ca.setInputeTime(CommonMethod.getDate());
            ca.setLinkMan(cap.getEntrustLinkMan());
            ca.setLinkPhone(cap.getEntrustLinkPhone());
            ca.setProjectRemark(cap.getProjectRemark());
            capitalAccountDao.save(ca);
        }
    }

    @Override
    public void updateCapitalAccount(CapitalAccountPage cap, String userId) {
        // for(CapitalAccountPage cap:coll){
        CapitalAccount ca = capitalAccountDao.findById(cap
                .getCapitalAccountId());
        if (ca == null) {
            throw new BusinessException("资金账号ID" + cap.getCapitalAccountId()
                    + "不存在，请确认！", "");
        }
        DetachedCriteria dc = DetachedCriteria.forClass(CapitalAccount.class);
        dc.add(Property.forName("capitalAccountId").ne(
                cap.getCapitalAccountId()));
        dc.add(Property.forName("capitalAccountCode").eq(
                cap.getCapitalAccountCode()));
        List<CapitalAccount> ecList = capitalAccountDao.findByCriteria(dc);
        if (ecList != null && ecList.size() > 0) {
            throw new BusinessException("该资金账号已经存在！", "");
        }
        ca.setEntrustCompanyId(cap.getEntrustCompanyId());
        ca.setProjectId(cap.getProjectId());
        ca.setSupervisionUnitId(cap.getSupervisionUnitId());
        ca.setAccountType2(cap.getAccountType2());
        ca.setAccountKinds(cap.getAccountKinds());
        ca.setContractId(cap.getContractId());
        ca.setContractCode(cap.getContractCode());
        ca.setPrintNum(cap.getPrintNum() == null ? 0 : Integer.valueOf(cap
                .getPrintNum()));
        ca.setRemark(cap.getRemark());
        ca.setUpdater(userId);
        ca.setUpdateTime(CommonMethod.getDate());
        ca.setLinkMan(cap.getEntrustLinkMan());
        ca.setLinkPhone(cap.getEntrustLinkPhone());
        ca.setProjectRemark(cap.getProjectRemark());
        ca.setSupervisionUnitId(cap.getSupervisionUnitId());
        capitalAccountDao.update(ca);
        // }
    }

    @Override
    public String findNewCaCode() {
        String newCaCode = "";
        String maxCaCode = capitalAccountDao.findMaxCapitalAccountCode();

        if ("0".equals(maxCaCode) || CommonMethod.isNull(maxCaCode)) {
            newCaCode = "1";
        } else {
            Integer tmpCaCode = Integer.valueOf(maxCaCode) + 1;
            newCaCode = tmpCaCode.toString();
        }
        return newCaCode;
    }

    private boolean findCaCode(String caCode) {
        boolean isCaCode = false;
        DetachedCriteria dc = DetachedCriteria.forClass(CapitalAccount.class);
        dc.add(Property.forName("capitalAccountCode").eq(caCode));
        dc.addOrder(Order.desc("capitalAccountCode"));
        List<CapitalAccount> caList = capitalAccountDao.findByCriteria(dc);
        if (caList != null && caList.size() > 0) {
            isCaCode = true;
        }
        return isCaCode;
    }

    @Override
    public String findByCompanyIdProjectId(String CompanyId, String projectId) {
        String capitalAccountId = "";
        DetachedCriteria dc = DetachedCriteria.forClass(CapitalAccount.class);
        dc.add(Property.forName("entrustCompanyId").eq(CompanyId));
        dc.add(Property.forName("projectId").eq(projectId));
        List<CapitalAccount> caList = capitalAccountDao.findByCriteria(dc);
        if (caList != null && caList.size() > 0) {
            capitalAccountId = caList.get(0).getCapitalAccountId();
        }
        return capitalAccountId;
    }

    @Override
    public List<CapitalAccountPage> findCaList(CapitalAccountPage cap) {
        List<CapitalAccountPage> caPageList = capitalAccountDao
                .findCaListSQL(cap);
        for (CapitalAccountPage caPage : caPageList) {
            List<CapitalAccountLinkmpPage> caLinkmpPageList = new ArrayList<CapitalAccountLinkmpPage>();

            List<CapitalAccountLinkmp> calList = capitalAccountLinkmpDao
                    .findByProperty("capitalAccountId",
                            caPage.getCapitalAccountId());
            for (CapitalAccountLinkmp cal : calList) {
                CapitalAccountLinkmpPage caLinkmpPage = new CapitalAccountLinkmpPage();
                caLinkmpPage.setLinkMan(cal.getLinkMan());
                caLinkmpPage.setLinkPhone(cal.getLinkPhone());
                // 在委托对象中查找对象使用频率
                DetachedCriteria dc = DetachedCriteria
                        .forClass(EntrustInfo.class);
                dc.add(Property.forName("linkPhone").eq(cal.getLinkPhone()));
                dc.add(Property.forName("linkMan").eq(cal.getLinkMan()));
                List<EntrustInfo> eiList = entrustInfoDao.findByCriteria(dc);
                if (eiList != null && eiList.size() > 0) {
                    caLinkmpPage.setUsageRate(eiList.size());
                } else {
                    caLinkmpPage.setUsageRate(0);
                }
                caLinkmpPageList.add(caLinkmpPage);
            }

            Collections.sort(caLinkmpPageList,
                    new Comparator<CapitalAccountLinkmpPage>() {
                        public int compare(CapitalAccountLinkmpPage arg0,
                                           CapitalAccountLinkmpPage arg1) {
                            return Integer.valueOf(arg1.getUsageRate())
                                    .compareTo(arg0.getUsageRate());
                        }
                    });

            caPage.setCaLinkmpPageList(caLinkmpPageList);
        }

        return caPageList;
    }

    @Override
    public List<CapitalAccountPage> findEntrustCaList(CapitalAccountPage cap) {
        List<CapitalAccountPage> caPageList = capitalAccountDao
                .findEntrustCaListSQL(cap);
		/*for (CapitalAccountPage caPage : caPageList) {
			List<CapitalAccountLinkmpPage> caLinkmpPageList = new ArrayList<CapitalAccountLinkmpPage>();

			List<CapitalAccountLinkmp> calList = capitalAccountLinkmpDao
					.findByProperty("capitalAccountId",
							caPage.getCapitalAccountId());
			for (CapitalAccountLinkmp cal : calList) {
				CapitalAccountLinkmpPage caLinkmpPage = new CapitalAccountLinkmpPage();
				caLinkmpPage.setLinkMan(cal.getLinkMan());
				caLinkmpPage.setLinkPhone(cal.getLinkPhone());
				// 在委托对象中查找对象使用频率
				DetachedCriteria dc = DetachedCriteria
						.forClass(EntrustInfo.class);
				dc.add(Property.forName("linkPhone").eq(cal.getLinkPhone()));
				dc.add(Property.forName("linkMan").eq(cal.getLinkMan()));
				List<EntrustInfo> eiList = entrustInfoDao.findByCriteria(dc);
				if (eiList != null && eiList.size() > 0) {
					caLinkmpPage.setUsageRate(eiList.size());
				} else {
					caLinkmpPage.setUsageRate(0);
				}
				caLinkmpPageList.add(caLinkmpPage);
			}

			Collections.sort(caLinkmpPageList,
					new Comparator<CapitalAccountLinkmpPage>() {
						public int compare(CapitalAccountLinkmpPage arg0,
								CapitalAccountLinkmpPage arg1) {
							return Integer.valueOf(arg1.getUsageRate())
									.compareTo(arg0.getUsageRate());
						}
					});
			System.out.println("循环装数据完成" + CommonMethod.getTime() + "");
			caPage.setCaLinkmpPageList(caLinkmpPageList);
		}*/

        return caPageList;
    }

    private String findEntrustCompanyNo() {
        String entrustCompanyNo = "";
        String maxEntrustCompanyNo = entrustCompanyDao
                .findMaxEntrustCompanyNo();
        if ("0".equals(maxEntrustCompanyNo)) {
            entrustCompanyNo = "1";
        } else {
            Integer tmpEntrustCompanyNo = Integer.valueOf(maxEntrustCompanyNo) + 1;
            entrustCompanyNo = tmpEntrustCompanyNo.toString();
        }
        return entrustCompanyNo;
    }

    @Override
    public List<CapitalAccountLinkmpPage> findEntrustCaListInfo(String id) {
		/*List<CapitalAccountPage> caPageList = capitalAccountDao
				.findEntrustCaListSQL(cap);*/
        List<CapitalAccountLinkmpPage> caLinkmpPageList = new ArrayList<CapitalAccountLinkmpPage>();

        List<CapitalAccountLinkmp> calList = capitalAccountLinkmpDao
                .findByProperty("capitalAccountId",
                        id);
        for (CapitalAccountLinkmp cal : calList) {
            CapitalAccountLinkmpPage caLinkmpPage = new CapitalAccountLinkmpPage();
            caLinkmpPage.setLinkMan(cal.getLinkMan());
            caLinkmpPage.setLinkPhone(cal.getLinkPhone());
            // 在委托对象中查找对象使用频率
            DetachedCriteria dc = DetachedCriteria
                    .forClass(EntrustInfo.class);
            dc.add(Property.forName("linkPhone").eq(cal.getLinkPhone()));
            dc.add(Property.forName("linkMan").eq(cal.getLinkMan()));
            List<EntrustInfo> eiList = entrustInfoDao.findByCriteria(dc);
            if (eiList != null && eiList.size() > 0) {
                caLinkmpPage.setUsageRate(eiList.size());
            } else {
                caLinkmpPage.setUsageRate(0);
            }
            caLinkmpPageList.add(caLinkmpPage);
        }

        Collections.sort(caLinkmpPageList,
                new Comparator<CapitalAccountLinkmpPage>() {
                    public int compare(CapitalAccountLinkmpPage arg0,
                                       CapitalAccountLinkmpPage arg1) {
                        return Integer.valueOf(arg1.getUsageRate())
                                .compareTo(arg0.getUsageRate());
                    }
                });
        return caLinkmpPageList;
    }
}