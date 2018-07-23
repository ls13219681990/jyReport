package com.service.finance.impl;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.action.JsonPager;
import com.common.dao.BaseDao;
import com.common.service.BaseServiceImpl;
import com.dao.common.EntrustCompanyDao;
import com.dao.common.ProjectInfoDao;
import com.dao.finance.AmountReceivableDao;
import com.dao.page.AmReceivablePage;
import com.model.AmountReceivable;
import com.model.EntrustCompany;
import com.model.ProjectInfo;
import com.service.finance.AmountReceivableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service("amountReceivableService")
public class AmountReceivableServiceImpl extends BaseServiceImpl<AmountReceivable> implements
        AmountReceivableService {

    @Autowired
    private AmountReceivableDao amountReceivableDao;

    @Autowired
    private EntrustCompanyDao entrustCompanyDao;

    @Autowired
    private ProjectInfoDao projectInfoDao;
	
	/*@Override
	@Resource(name = "amountReceivableDao")
	protected void initBaseDAO(BaseDao<AmountReceivable> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<AmountReceivable> findJsonPageByCriteria(JsonPager<AmountReceivable> jp,
			AmountReceivable t) {
		return null;
	}*/

    @Override
    public void saveAmReceivable(Collection<AmReceivablePage> coll, String userId) {
        for (AmReceivablePage ampage : coll) {
            AmountReceivable am = new AmountReceivable();
            am.setAmountReceivableId(CommonMethod.getNewKey());
            am.setEntrustCompanyId(ampage.getEntrustCompanyId());
            am.setProjectId(ampage.getProjectId());
            am.setReceivableMoney(ampage.getReceivableMoney());
            am.setRemark(ampage.getRemark());
            am.setInputer(userId);
            am.setInputeTime(CommonMethod.getDate());
            amountReceivableDao.save(am);
        }
    }

    @Override
    public void saveAmReceivableTable(Collection<AmReceivablePage> coll, String userId) {
        for (AmReceivablePage cap : coll) {
            //委托单位ID
            String eCompanyId = "";
            List<EntrustCompany> ecList = entrustCompanyDao.findByProperty("entrustCompanyName", cap.getEntrustCompanyName());
            if (ecList != null && ecList.size() > 0) {
                eCompanyId = ecList.get(0).getEntrustCompanyId();
            } else {
                continue;
            }

            //工程ID
            String pInfoId = "";
            List<ProjectInfo> pjList = projectInfoDao.findByProperty("projectName", cap.getProjectName());
            if (pjList != null && pjList.size() > 0) {
                pInfoId = pjList.get(0).getProjectId();
            } else {
                continue;
            }
            AmountReceivable ar = new AmountReceivable();
            ar.setAmountReceivableId(CommonMethod.getNewKey());
            ar.setEntrustCompanyId(eCompanyId);
            ar.setProjectId(pInfoId);
            ar.setReceivableMoney(cap.getReceivableMoney());
            ar.setInputer(userId);
            ar.setInputeTime(CommonMethod.getDate());
            amountReceivableDao.save(ar);
        }
    }

    @Override
    public void updateAmReceivable(Collection<AmReceivablePage> coll, String userId) {
        for (AmReceivablePage ampage : coll) {
            AmountReceivable am = amountReceivableDao.findById(ampage.getAmountReceivableId());
            if (am == null) {
                throw new BusinessException("以前应收账款ID" + ampage.getAmountReceivableId() + "不存在，请确认！", "");
            }
            am.setReceivableMoney(ampage.getReceivableMoney());
            am.setRemark(ampage.getRemark());
            am.setUpdater(userId);
            am.setUpdateTime(CommonMethod.getDate());
            amountReceivableDao.update(am);
        }
    }

    @Override
    public List<AmReceivablePage> findAmReceivableList(AmReceivablePage amp) {
        return amountReceivableDao.findAmReceivableListSQL(amp);
    }

    @Override
    protected void initBaseDAO(BaseDao<AmountReceivable> baseDao) {

    }

    @Override
    public JsonPager<AmountReceivable> findJsonPageByCriteria(JsonPager<AmountReceivable> jp, AmountReceivable amountReceivable) {
        return null;
    }
}