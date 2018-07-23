package com.dao.entrust.impl;

import com.common.action.EasyPager;
import com.common.action.JsonPager;
import com.common.action.PaginationSupport;
import com.common.dao.BaseDaoImpl;
import com.dao.entrust.SampleReportDao;
import com.model.SampleReport;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository("sampleReportDao")
public class SampleReportDaoImpl extends BaseDaoImpl<SampleReport> implements SampleReportDao {

    @Override
    public void deleteByEntrustId(final String entrustId) {
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {
                String sql = "delete SampleReport s where s.entrustId = :entrustId";
                Query query = session.createQuery(sql).setString("entrustId", entrustId);
                return query.executeUpdate();
            }
        });
    }

    public static void main(String[] args) {
        String sb = "bbbdsajjds";
        System.out.println(sb.substring(2));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SampleReport> showEntrustIdByOrder(final List<String> entrustIdList) {
        return (List<SampleReport>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public List<SampleReport> doInHibernate(Session session) throws HibernateException,
                    SQLException {
                StringBuffer sql = new StringBuffer();
                sql.append(" select sr.* from Sample_Report sr  inner join ");
                sql.append(" (select * from Entrust_Info t  ");
                sql.append(" where t.ENTRUST_ID in (");
                StringBuffer strEntrustId = new StringBuffer();
                for (String entrustId : entrustIdList) {
                    strEntrustId.append("'" + entrustId + "',");
                }
                String ids = strEntrustId.toString();
                ids = ids.substring(0, strEntrustId.length() - 1);
                sql.append(ids);
                sql.append(") )t on  sr.ENTRUST_ID = t.ENTRUST_ID ");
                sql.append(" order by t.INPUTE_TIME ");
                SQLQuery query = session.createSQLQuery(sql.toString());
                query.addEntity("sr", SampleReport.class);
                //query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                List<SampleReport> list = query.list();
                return list;
				/*
				Criteria criteria = session.createCriteria(SampleReport.class);
				criteria.add(Restrictions.in("entrustId", entrustIdList));
				criteria.addOrder(Order.asc("sampleNo"));
				return  criteria.list();*/
            }
        });
		/*StringBuilder str = new StringBuilder();
		for(String entrustId:entrustIdList){
			str.append(entrustId+",");
		}*/
    }

    @Override
    public PaginationSupport<SampleReport> findPageByCriteria(PaginationSupport<SampleReport> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<SampleReport> findJsonPageByCriteria(JsonPager<SampleReport> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<SampleReport> findEasyPageByCriteria(EasyPager<SampleReport> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}