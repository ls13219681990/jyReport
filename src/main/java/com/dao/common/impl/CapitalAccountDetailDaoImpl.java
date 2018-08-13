package com.dao.common.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.CapitalAccountDetailDao;
import com.dao.page.CapitalAccountDetailPage;
import com.model.CapitalAccountDetail;
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


@Repository("capitalAccountDetailDao")
public class CapitalAccountDetailDaoImpl extends BaseDaoImpl<CapitalAccountDetail> implements CapitalAccountDetailDao {
    @Override
    public void deleteByCAId(final String caId) {
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                String hql = "delete CapitalAccountDetail s where s.contractId = :contractId";
                return session.createQuery(hql)
                        .setString("contractId", caId)
                        .executeUpdate();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CapitalAccountDetailPage> findCASampleDetailsAll() {
        return (List<CapitalAccountDetailPage>) getHibernateTemplate().execute(
                new HibernateCallback() {

                    @Override
                    public Object doInHibernate(Session session)
                            throws HibernateException {

                        StringBuffer sql = new StringBuffer();
                        sql.append(" select bd.DEPARTMENT_NAME,bs.SAMPLE_NAME,tp.UNIT,bs.SAMPLE_ID,tp.TEST_PARAMETER_NAME,tp.TEST_PARAMETER_ID, ");
                        sql.append(" CONVERT(VARCHAR, bb.PRICE) price,tp.UNIT_PRICE from TEST_PARAMETER  tp ");
                        sql.append(" INNER JOIN BASE_SAMPLE bs  on bs.SAMPLE_ID = tp.SAMPLE_ID ");
                        sql.append(" INNER JOIN BASE_DEPARTMENT bd   on bs.DEPARTMENT_ID = bd.DEPARTMENT_ID  ");
                        sql.append(" left JOIN(select cad.PRICE,cad.TEST_PROJECT,cad.CONTRACT_ID from  CAPITAL_ACCOUNT_DETAIL cad  ");
                        sql.append(" ) bb on bb.TEST_PROJECT = bs.SAMPLE_ID	");
                        sql.append(" where tp.PARAMETER_STATUS='01'	");
                        sql.append(" Group by bd.DEPARTMENT_NAME,bs.SAMPLE_NAME,tp.UNIT,bs.SAMPLE_ID,tp.TEST_PARAMETER_NAME,tp.TEST_PARAMETER_ID,bb.PRICE,tp.UNIT_PRICE ");
                        Query query = session.createSQLQuery(sql.toString());

                        int totalCount = query.list().size();
                        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                        List l = query.list();
                        List<CapitalAccountDetailPage> list = new ArrayList<CapitalAccountDetailPage>();
                        for (Object obj : l) {
                            //Map m = new HashMap<K, V>
                            Map map = (Map) obj;
                            CapitalAccountDetailPage newCap = getCapitalAccountDetailPage(map,
                                    totalCount);
                            list.add(newCap);
                        }
                        return list;
                    }
                });
    }

    protected CapitalAccountDetailPage getCapitalAccountDetailPage(Map map,
                                                                   int totalCount) {
        CapitalAccountDetailPage cad = new CapitalAccountDetailPage();
        cad.setDepartment(map.get("DEPARTMENT_NAME") == null ? null
                : map.get("DEPARTMENT_NAME").toString());
        cad.setTestProject(map.get("SAMPLE_ID") == null ? null
                : map.get("SAMPLE_ID").toString());
        cad.setTestTypeName(map.get("TEST_PARAMETER_NAME") == null ? null
                : map.get("TEST_PARAMETER_NAME").toString());
        cad.setTestProjectName(map.get("SAMPLE_NAME") == null ? null
                : map.get("SAMPLE_NAME").toString());
        cad.setComputingUnit(map.get("UNIT") == null ? null
                : map.get("UNIT").toString());
        cad.setRealPrice(Double.parseDouble((map.get("UNIT_PRICE") == null ? null
                : map.get("UNIT_PRICE").toString())));
        String d = "0.00";
        cad.setPrice((String) ((map.get("price") == null ? d
                : map.get("price"))));
        cad.setTestType((map.get("TEST_PARAMETER_ID") == null ? null
                : map.get("TEST_PARAMETER_ID").toString()));
        return cad;
    }

    @Override
    public PaginationSupport<CapitalAccountDetail> findPageByCriteria(PaginationSupport<CapitalAccountDetail> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<CapitalAccountDetail> findJsonPageByCriteria(JsonPager<CapitalAccountDetail> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<CapitalAccountDetail> findEasyPageByCriteria(EasyPager<CapitalAccountDetail> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}