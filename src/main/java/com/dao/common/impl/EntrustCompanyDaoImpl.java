package com.dao.common.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.EntrustCompanyDao;
import com.model.EntrustCompany;
import com.dao.page.AdvanceMoneyPage;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("entrustCompanyDao")
public class EntrustCompanyDaoImpl extends BaseDaoImpl<EntrustCompany> implements EntrustCompanyDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<AdvanceMoneyPage> findEntrustAdvanceMoney(final String strEntrustCompanyId) {
        return (List<AdvanceMoneyPage>) getHibernateTemplate().execute(new HibernateCallback() {
            @SuppressWarnings("rawtypes")
            @Override
            public List<AdvanceMoneyPage> doInHibernate(Session session) throws HibernateException,
                    SQLException {
                StringBuffer sql = new StringBuffer();
                sql.append(" select ea.ENTRUST_ADVANCE_ID,ea.ENTRUST_COMPANY_ID,ec.ENTRUST_COMPANY_NAME,ec.ENTRUST_COMPANY_NO, ");
                sql.append(" pInfo.PROJECT_ID,pInfo.PROJECT_NAME,ea.ADVANCE_MONEY,ea.ADVANCE_STATUS,sCode.CODE_NAME as ADVANCE_STATUS_NAME, ");
                sql.append(" ea.REMARK,ea.INPUTER,sUser.USER_NAME as INPUTER_NAME,ea.INPUTE_TIME ");
                sql.append(" from ENTRUST_ADVANCE ea ");
                sql.append(" inner join ENTRUST_COMPANY ec on ea.ENTRUST_COMPANY_ID = ec.ENTRUST_COMPANY_ID ");
                sql.append(" inner join SYS_CODE sCode on ea.ADVANCE_STATUS = sCode.CODE_VALUE and sCode.CODE_RELATION = 'ADVANCE_STATUS' ");
                sql.append(" inner join SYS_USER sUser on ea.INPUTER = sUser.SYS_USER_ID ");
                sql.append(" left join PROJECT_INFO pInfo on ea.PROJECT_ID = pInfo.PROJECT_ID ");
                sql.append(" where ea.ENTRUST_COMPANY_ID = '");
                sql.append(strEntrustCompanyId);
                sql.append("'");

                Query query = session.createSQLQuery(sql.toString());


                query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                List l = query.list();

                List<AdvanceMoneyPage> list = new ArrayList<AdvanceMoneyPage>();

                for (Object obj : l) {

                    Map map = (Map) obj;
                    AdvanceMoneyPage aMoneyPage = new AdvanceMoneyPage();

                    aMoneyPage.setEntrustAdvanceId(map.get("ENTRUST_ADVANCE_ID") == null ? null : map.get("ENTRUST_ADVANCE_ID").toString());
                    aMoneyPage.setEntrustCompanyId(map.get("ENTRUST_COMPANY_ID") == null ? null : map.get("ENTRUST_COMPANY_ID").toString());
                    aMoneyPage.setEntrustCompanyName(map.get("ENTRUST_COMPANY_NAME") == null ? null : map.get("ENTRUST_COMPANY_NAME").toString());
                    aMoneyPage.setEntrustCompanyNo(map.get("ENTRUST_COMPANY_NO") == null ? null : map.get("ENTRUST_COMPANY_NO").toString());
                    aMoneyPage.setProjectId(map.get("PROJECT_ID") == null ? null : map.get("PROJECT_ID").toString());
                    aMoneyPage.setProjectName(map.get("PROJECT_NAME") == null ? null : map.get("PROJECT_NAME").toString());
                    aMoneyPage.setAdvanceMoney(map.get("ADVANCE_MONEY") == null ? 0 : Double.valueOf(map.get("ADVANCE_MONEY").toString()));
                    aMoneyPage.setAdvanceStatus(map.get("ADVANCE_STATUS") == null ? null : map.get("ADVANCE_STATUS").toString());
                    aMoneyPage.setAdvanceStatusName(map.get("ADVANCE_STATUS_NAME") == null ? null : map.get("ADVANCE_STATUS_NAME").toString());
                    aMoneyPage.setRemark(map.get("REMARK") == null ? null : map.get("REMARK").toString());
                    aMoneyPage.setInputer(map.get("INPUTER") == null ? null : map.get("INPUTER").toString());
                    aMoneyPage.setInputerName(map.get("INPUTER_NAME") == null ? null : map.get("INPUTER_NAME").toString());
                    aMoneyPage.setInputeTime(map.get("INPUTE_TIME") == null ? null : map.get("INPUTE_TIME").toString());

                    list.add(aMoneyPage);
                }
                return list;
            }
        });
    }

    @Override
    public String findMaxEntrustCompanyNo() {
        return (String) getHibernateTemplate().execute(new HibernateCallback() {
            @SuppressWarnings("rawtypes")
            @Override
            public String doInHibernate(Session session) throws HibernateException,
                    SQLException {
                StringBuffer sql = new StringBuffer();
                sql.append("select MAX(cast(ec.ENTRUST_COMPANY_NO as int)) as maxEntrustCompanyNo from ENTRUST_COMPANY ec ");

                Query query = session.createSQLQuery(sql.toString());


                query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                List l = query.list();

                String maxEntrustCompanyNo = "";

                for (Object obj : l) {

                    Map map = (Map) obj;

                    maxEntrustCompanyNo = map.get("maxEntrustCompanyNo") == null ? "0" : map.get("maxEntrustCompanyNo").toString();
                }
                return maxEntrustCompanyNo;
            }
        });
    }

    @Override
    public PaginationSupport<EntrustCompany> findPageByCriteria(PaginationSupport<EntrustCompany> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<EntrustCompany> findJsonPageByCriteria(JsonPager<EntrustCompany> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<EntrustCompany> findEasyPageByCriteria(EasyPager<EntrustCompany> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}