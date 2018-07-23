package com.service.common.impl;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.action.JsonPager;
import com.common.dao.BaseDao;
import com.common.service.BaseServiceImpl;
import com.dao.common.EntrustCompanyDao;
import com.dao.finance.EntrustAdvanceDao;
import com.dao.page.AdvanceMoneyPage;
import com.model.EntrustAdvance;
import com.model.EntrustCompany;
import com.service.common.EntrustCompanyService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;


@Service("entrustCompanyService")
public class EntrustCompanyServiceImpl extends BaseServiceImpl<EntrustCompany> implements
        EntrustCompanyService {

    @Autowired
    private EntrustCompanyDao entrustCompanyDao;
    @Autowired
    private EntrustAdvanceDao entrustAdvanceDao;

    @Override
    @Resource(name = "entrustCompanyDao")
    protected void initBaseDAO(BaseDao<EntrustCompany> baseDao) {
        setBaseDao(baseDao);
    }

    @Override
    public JsonPager<EntrustCompany> findJsonPageByCriteria(JsonPager<EntrustCompany> jp,
                                                            EntrustCompany t) {
        return null;
    }

    @Override
    public List<EntrustCompany> findEntrustCompany(String strEntrustCompanyId, String strEntrustCompanyName, String strEntrustCompanyNo) {
        DetachedCriteria dc = DetachedCriteria.forClass(EntrustCompany.class);
        if (!CommonMethod.isNull(strEntrustCompanyId)) {
            dc.add(Property.forName("entrustCompanyId").eq(strEntrustCompanyId));
        }
        if (!CommonMethod.isNull(strEntrustCompanyNo)) {
            dc.add(Property.forName("entrustCompanyNo").eq(strEntrustCompanyNo));
        }
        if (!CommonMethod.isNull(strEntrustCompanyName)) {
            strEntrustCompanyName = strEntrustCompanyName.replace("OO", "#");
            dc.add(Property.forName("entrustCompanyName").like(strEntrustCompanyName, MatchMode.ANYWHERE));
        }
        dc.addOrder(Order.desc("inputeTime"));
        return entrustCompanyDao.findByCriteria(dc);
    }

    @Override
    public String saveEntrustCompany(Collection<EntrustCompany> coll, String userId) {
        String entrustCompanyId = "";
        for (EntrustCompany entrustCompany : coll) {
            List<EntrustCompany> ecList = entrustCompanyDao.findByProperty("entrustCompanyName", entrustCompany.getEntrustCompanyName());
            if (ecList != null && ecList.size() > 0) {
                throw new BusinessException("该委托单位名称已经存在！", "");
            }
//			List<EntrustCompany> ecnoList= entrustCompanyDao.findByProperty("entrustCompanyNo", entrustCompany.getEntrustCompanyNo());
//			if(ecnoList!=null && ecnoList.size()>0){
//				throw new BusinessException("该委托单位编号已经存在！","");
//			}
            entrustCompany.setEntrustCompanyId(CommonMethod.getNewKey());
            entrustCompany.setEntrustCompanyNo(findEntrustCompanyNo());
            entrustCompany.setInputer(userId);
            entrustCompany.setInputeTime(CommonMethod.getDate());
            entrustCompanyDao.save(entrustCompany);

            entrustCompanyId = entrustCompany.getEntrustCompanyId();
        }

        return entrustCompanyId;
    }

    @Override
    public void updateEntrustCompany(Collection<EntrustCompany> coll, String userId) {
        for (EntrustCompany entrustCompany : coll) {
            DetachedCriteria dc = DetachedCriteria.forClass(EntrustCompany.class);
            dc.add(Property.forName("entrustCompanyId").ne(entrustCompany.getEntrustCompanyId()));
            dc.add(Property.forName("entrustCompanyName").eq(entrustCompany.getEntrustCompanyName()));
            List<EntrustCompany> ecList = entrustCompanyDao.findByCriteria(dc);
            if (ecList != null && ecList.size() > 0) {
                throw new BusinessException("该委托单位名称已经存在！", "");
            }
            DetachedCriteria dcno = DetachedCriteria.forClass(EntrustCompany.class);
            dcno.add(Property.forName("entrustCompanyId").ne(entrustCompany.getEntrustCompanyId()));
            dcno.add(Property.forName("entrustCompanyNo").eq(entrustCompany.getEntrustCompanyNo()));
            List<EntrustCompany> ecnoList = entrustCompanyDao.findByCriteria(dcno);
            if (ecnoList != null && ecnoList.size() > 0) {
                throw new BusinessException("该委托单位编号已经存在！", "");
            }

            EntrustCompany eCompany = entrustCompanyDao.findById(entrustCompany.getEntrustCompanyId());
            eCompany.setEntrustCompanyName(entrustCompany.getEntrustCompanyName());
            eCompany.setAddress(entrustCompany.getAddress());
            eCompany.setRemark(entrustCompany.getRemark());
            eCompany.setUpdater(userId);
            eCompany.setUpdateTime(CommonMethod.getDate());
            entrustCompanyDao.update(eCompany);
        }
    }

    @Override
    public void saveEntrustAdvanceMoney(String strEntrustCompanyId, String strAdvanceMoney, String userId) {
        //委托单位
        EntrustCompany ec = entrustCompanyDao.findById(strEntrustCompanyId);
        if (ec == null) {
            throw new BusinessException("该委托单位不存在，请确认！", "");
        } else {
            EntrustAdvance ea = new EntrustAdvance();
            ea.setEntrustAdvanceId(CommonMethod.getNewKey());
            ea.setEntrustCompanyId(strEntrustCompanyId);
            ea.setAdvanceMoney(Double.valueOf(strAdvanceMoney));
            ea.setAdvanceStatus("01");//充值
            ea.setInputer(userId);
            ea.setInputeTime(CommonMethod.getDate());
            entrustAdvanceDao.save(ea);
            Double advancesReceived = 0D;
            if (ec.getAdvancesReceived() != null) {
                advancesReceived = ec.getAdvancesReceived();
            }
            //委托单位已有预收金额+充值到委托单位的预收金额
            Double advanceMoney = advancesReceived + Double.valueOf(strAdvanceMoney);
            ec.setAdvancesReceived(advanceMoney);
            entrustCompanyDao.update(ec);
        }
    }

    @Override
    public List<AdvanceMoneyPage> findEntrustAdvanceMoney(String strEntrustCompanyId) {
        List<AdvanceMoneyPage> amPageList = entrustCompanyDao.findEntrustAdvanceMoney(strEntrustCompanyId);
        return amPageList;
    }

    private String findEntrustCompanyNo() {
        String entrustCompanyNo = "";
        String maxEntrustCompanyNo = entrustCompanyDao.findMaxEntrustCompanyNo();
        if ("0".equals(maxEntrustCompanyNo)) {
            entrustCompanyNo = "1";
        } else {
            Integer tmpEntrustCompanyNo = Integer.valueOf(maxEntrustCompanyNo) + 1;
            entrustCompanyNo = tmpEntrustCompanyNo.toString();
        }
        return entrustCompanyNo;
    }
}