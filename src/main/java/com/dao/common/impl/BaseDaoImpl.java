package com.dao.common.impl;

import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.BaseDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
    private Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        Class<T> myClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.persistentClass = myClass;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }


    @Autowired
    protected void initSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /**
     * 带有分页查询以及排序功能的条件查询
     */

    @SuppressWarnings("unchecked")
    public PaginationSupport<T> findPageByCriteria(final PaginationSupport<T> page, final Order order,
                                                   final DetachedCriteria detachedCriteria) {
        return (PaginationSupport<T>) this.getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        try {
                            Criteria criteria = detachedCriteria.getExecutableCriteria(session);
                            //查询条件
                            CriteriaImpl impl = (CriteriaImpl) criteria;
                            //先把Projection和OrderBy条件取出来,清空两者来执行Count操作
                            Projection projection = impl.getProjection();
                            criteria.setProjection(Projections.rowCount());
                            int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
                            criteria.setProjection(projection);
                            if (projection == null) {
                                criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
                            }
                            page.setTotalRows(totalCount);
                            if (page.getTotalRows() % page.getPageSize() == 0) {
                                page.setTotalPages(page.getTotalRows() / page.getPageSize());
                            } else {
                                page.setTotalPages(page.getTotalRows() / page.getPageSize() + 1);
                            }
                            if (page.getViewPage() > page.getTotalPages()) {
                                page.setCurrentPage(1);
                            } else {
                                page.setCurrentPage(page.getViewPage());
                            }
                            if (page.getTotalPages() > page.getCurrentPage()) {
                                page.setHasNext(true);
                            } else {
                                page.setHasNext(false);
                            }
                            if (page.getCurrentPage() > 1) {
                                page.setHasPrevious(true);
                            } else {
                                page.setHasPrevious(false);
                            }
                            if (order != null) {
                                criteria.addOrder(order);
                            }
                            criteria.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize());
                            criteria.setMaxResults(page.getPageSize());
                            page.setResults(criteria.list());
                            return page;
                        } catch (HibernateException e) {
                            e.printStackTrace();
                            logger.error("翻页查询", e);
                            throw e;
                        }
                    }
                });
    }

//	@Override
//	public JsonPager<T> findJsonPageByCriteria(JsonPager<T> jp,
//			DetachedCriteria detachedCriteria) {
//		// TODO Auto-generated method stub
//		return null;
//	}

    /**
     * 带有分页查询以及排序功能的条件查询
     */

    @Override
    @SuppressWarnings("unchecked")
    public JsonPager<T> findJsonPageByCriteria(final JsonPager<T> page, final DetachedCriteria detachedCriteria) {
        return (JsonPager<T>) this.getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        try {
                            Criteria criteria = detachedCriteria.getExecutableCriteria(session);
                            //查询条件
                            CriteriaImpl impl = (CriteriaImpl) criteria;
                            //先把Projection和OrderBy条件取出来,清空两者来执行Count操作
                            Projection projection = impl.getProjection();
                            criteria.setProjection(Projections.rowCount());
                            int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
                            criteria.setProjection(projection);
                            if (projection == null) {
                                criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
                            }
                            page.setTotal(totalCount);
                            criteria.setFirstResult(Integer.parseInt(page.getStart()));
                            criteria.setMaxResults(Integer.parseInt(page.getLimit()));
                            page.setRoot(criteria.list());
                            return page;
                        } catch (HibernateException e) {
                            e.printStackTrace();
                            logger.error("翻页查询", e);
                            throw e;
                        }
                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public EasyPager<T> findEasyPageByCriteria(final EasyPager<T> page,
                                               final DetachedCriteria detachedCriteria) {
        return (EasyPager<T>) this.getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        try {
                            Criteria criteria = detachedCriteria.getExecutableCriteria(session);
                            //查询条件
                            CriteriaImpl impl = (CriteriaImpl) criteria;
                            //先把Projection和OrderBy条件取出来,清空两者来执行Count操作
                            Projection projection = impl.getProjection();
                            criteria.setProjection(Projections.rowCount());
                            int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
                            criteria.setProjection(projection);
                            if (projection == null) {
                                criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
                            }
                            page.setTotal(totalCount);
                            criteria.setFirstResult(page.getPageSize() * (page.getPageNumber() - 1));
                            criteria.setMaxResults(page.getPageSize());

                            page.setRows(criteria.list());
                            return page;
                        } catch (HibernateException e) {
                            e.printStackTrace();
                            logger.error("翻页查询", e);
                            throw e;
                        }
                    }
                });
    }

    public void delete(T t) {
        logger.debug("deleting  " + t.getClass().getSimpleName() + "  instance");
        try {
            getHibernateTemplate().delete(t);
            logger.debug("delete successful");
        } catch (RuntimeException re) {
            logger.error("delete failed", re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        logger.debug("finding all " + persistentClass.getSimpleName() + " instances");
        try {
            String queryString = "from " + persistentClass.getSimpleName();
            return (List<T>) getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            logger.error("find all failed", re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> findByExample(T t) {
        logger.debug("finding " + persistentClass.getSimpleName() + " instance by example");
        try {
            List<T> results = getHibernateTemplate().findByExample(t);
            logger.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            logger.error("find by example failed", re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public T findById(Long id) {
        logger.debug("getting " + persistentClass.getSimpleName() + " instance with id: " + id);
        try {
            T instance = (T) getHibernateTemplate()
                    .get(getPersistentClass(), id);
            return instance;
        } catch (RuntimeException re) {
            logger.error("get failed", re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public T findById(String id) {
        logger.debug("getting " + persistentClass.getSimpleName() + " instance with id: " + id);
        try {
            T instance = (T) getHibernateTemplate()
                    .get(getPersistentClass(), id);
            return instance;
        } catch (RuntimeException re) {
            logger.error("get failed", re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> findByProperty(String propertyName, Object value) {
        logger.debug("finding " + persistentClass.getSimpleName() + " instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from " + persistentClass.getSimpleName() + " as model where model."
                    + propertyName + "= ?";
            return (List<T>) getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            logger.error("find by property name failed", re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> findByProperty(String propertyName, Object value, String orderName) {
        logger.debug("finding " + persistentClass.getSimpleName() + " instance with property: "
                + propertyName + ", value: " + value + " order by " + orderName);
        try {
            String queryString = "from " + persistentClass.getSimpleName() + " as model where model."
                    + propertyName + "= ?" + " order by " + orderName;
            return (List<T>) getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            logger.error("find by property name failed", re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public T merge(T t) {
        logger.debug("merging " + persistentClass.getSimpleName() + " instance");
        try {
            T result = (T) getHibernateTemplate().merge(t);
            logger.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            logger.error("merge failed", re);
            throw re;
        }
    }

    public void save(T t) {
        logger.debug("saving " + persistentClass.getSimpleName() + " instance");
        try {
            getHibernateTemplate().save(t);
            logger.debug("save successful");
        } catch (RuntimeException re) {
            logger.error("save failed", re);
            throw re;
        }
    }

    public void saveOrUpdate(T t) {
        logger.debug("saveOrUpdate " + t.getClass().getSimpleName() + " instance");
        try {
            getHibernateTemplate().saveOrUpdate(t);
            logger.debug("saveOrUpdate successful");
        } catch (RuntimeException re) {
            logger.error("saveOrUpdate failed", re);
            throw re;
        }
    }

    public void update(T t) {
        logger.debug("update " + t.getClass().getSimpleName() + " update");
        try {
            getHibernateTemplate().update(t);
            logger.debug("update successful");
        } catch (RuntimeException re) {
            logger.error("update failed", re);
            re.printStackTrace();
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
        return (List<T>) getHibernateTemplate().findByCriteria(detachedCriteria);
    }

    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(DetachedCriteria detachedCriteria, int firstResult, int maxResults) {
        return (List<T>) getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
    }

    @Override
    public String getNextCode(String tablename, String columName, String baseCode, String where) {
        String hql = "select max(" + columName + ") from " + tablename;
        if (where != null && !where.trim().equals("")) {
            hql += " where " + where;
        }
        List<String> list = (List<String>) getHibernateTemplate().find(hql);
        if (list == null || list.get(0) == null) {
            return baseCode;
        } else {
            String code = list.get(0);
            String str = String.format("%0" + baseCode.length() + "d", Integer.parseInt(code) + 1);
            return str;
        }
    }
}
