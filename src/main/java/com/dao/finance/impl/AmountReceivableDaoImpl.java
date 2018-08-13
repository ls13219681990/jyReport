package com.dao.finance.impl;

import com.common.CommonMethod;
import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.impl.BaseDaoImpl;
import com.dao.finance.AmountReceivableDao;
import com.dao.page.AmReceivablePage;
import com.model.AmountReceivable;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("amountReceivableDao")
public class AmountReceivableDaoImpl extends BaseDaoImpl<AmountReceivable> implements AmountReceivableDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<AmReceivablePage> findAmReceivableListSQL(final AmReceivablePage amp) {
        return (List<AmReceivablePage>) getHibernateTemplate().execute(new HibernateCallback() {
            @SuppressWarnings("rawtypes")
            @Override
            public List<AmReceivablePage> doInHibernate(Session session) throws HibernateException {
                StringBuffer sql = new StringBuffer();
                sql.append(" select ar.AMOUNT_RECEIVABLE_ID,ar.ENTRUST_COMPANY_ID,ar.PROJECT_ID, ");
                sql.append(" ar.INPUTER,ar.INPUTE_TIME,ar.UPDATER,ar.UPDATE_TIME,ar.REMARK,ec.ENTRUST_COMPANY_NAME,project.PROJECT_NAME ");
                sql.append(" from AMOUNT_RECEIVABLE ar ,ENTRUST_COMPANY ec,PROJECT_INFO project ");
                sql.append(" where ar.ENTRUST_COMPANY_ID = ec.ENTRUST_COMPANY_ID and ar.PROJECT_ID = project.PROJECT_ID ");
                if (amp != null) {
                    //委托单位名称不为空
                    if (!CommonMethod.isNull(amp.getEntrustCompanyName())) {
                        sql.append("and ec.ENTRUST_COMPANY_NAME like '%");
                        sql.append(amp.getEntrustCompanyName());
                        sql.append("%'");
                    }
                    //工程名称不为空
                    if (!CommonMethod.isNull(amp.getProjectName())) {
                        sql.append("and project.PROJECT_NAME like '%");
                        sql.append(amp.getProjectName());
                        sql.append("%'");
                    }
                    //合同号不为空
                    if (!CommonMethod.isNull(amp.getAmountReceivableId())) {
                        sql.append("and ar.AMOUNT_RECEIVABLE_ID ='");
                        sql.append(amp.getAmountReceivableId());
                        sql.append("'");
                    }
                }
                sql.append(" order by ar.INPUTE_TIME desc ");
                Query query = session.createSQLQuery(sql.toString());


                query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                List l = query.list();

                List<AmReceivablePage> list = new ArrayList<AmReceivablePage>();

                for (Object obj : l) {

                    Map map = (Map) obj;
                    AmReceivablePage newAmPage = new AmReceivablePage();

                    newAmPage.setAmountReceivableId(map.get("AMOUNT_RECEIVABLE_ID") == null ? null : map.get("AMOUNT_RECEIVABLE_ID").toString());
                    newAmPage.setEntrustCompanyId(map.get("ENTRUST_COMPANY_ID") == null ? null : map.get("ENTRUST_COMPANY_ID").toString());
                    newAmPage.setEntrustCompanyName(map.get("ENTRUST_COMPANY_NAME") == null ? null : map.get("ENTRUST_COMPANY_NAME").toString());
                    newAmPage.setProjectId(map.get("PROJECT_ID") == null ? null : map.get("PROJECT_ID").toString());
                    newAmPage.setProjectName(map.get("PROJECT_NAME") == null ? null : map.get("PROJECT_NAME").toString());
                    newAmPage.setReceivableMoney(map.get("CHECK_AREA") == null ? null : Double.valueOf(map.get("CHECK_AREA").toString()));
                    newAmPage.setRemark(map.get("REMARK") == null ? null : map.get("REMARK").toString());
                    newAmPage.setInputer(map.get("INPUTER") == null ? null : map.get("INPUTER").toString());
                    newAmPage.setInputeTime(map.get("INPUTE_TIME") == null ? null : map.get("INPUTE_TIME").toString());
                    newAmPage.setUpdater(map.get("UPDATER") == null ? null : map.get("UPDATER").toString());
                    newAmPage.setUpdateTime(map.get("UPDATE_TIME") == null ? null : map.get("UPDATE_TIME").toString());
                    list.add(newAmPage);
                }

                return list;
            }
        });
    }

    @Override
    public PaginationSupport<AmountReceivable> findPageByCriteria(PaginationSupport<AmountReceivable> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<AmountReceivable> findJsonPageByCriteria(JsonPager<AmountReceivable> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<AmountReceivable> findEasyPageByCriteria(EasyPager<AmountReceivable> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}