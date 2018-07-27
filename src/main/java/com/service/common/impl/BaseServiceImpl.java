package com.service.common.impl;

import com.common.CusSession;
import com.controller.common.PaginationSupport;
import com.dao.common.BaseDao;
import com.service.common.BaseService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @param <T>
 * @author Administrator
 */
@Service("BaseService")
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    private BaseDao<T> baseDao;

    private final static HttpServletRequest REQUEST = null;
    // @Autowired
    // private SysTablePkService sysTablePkService;
    // @Autowired
    // private SysUserDao sysUserDao;
    // @Autowired
    // private SysParamDao sysParamDao;
    //
    //
    // public String findSysParam(String paramName){
    // return sysParamDao.findSysParam(paramName);
    // }
    //
    // public Long getNextKey(final String tablename,final int count){
    // synchronized (sysTablePkService) {
    // return sysTablePkService.trueGetNextKey(tablename.toUpperCase(), count);
    // }
    // }
    //
    // public String getNextIndentCode(String baseCode, String prefixCode){
    // synchronized (sysTablePkService) {
    // return sysTablePkService.trueGetNextIndentCode(baseCode,prefixCode);
    // }
    // }
    //
    // public String getNextHotelIndentCode(String baseCode, String prefixCode){
    // synchronized (sysTablePkService) {
    // return sysTablePkService.trueGetNextHotelIndentCode(baseCode,prefixCode);
    // }
    // }

    public Long getCurrentUserId() {


        if (REQUEST.getSession().getAttribute("userId") != null) {
            return (Long) REQUEST.getSession().getAttribute("userId");
        } else {
            return (Long) CusSession.getSession().get("userId");

        }
		/*if (ActionContext.getContext() != null) {
			return (Long) ActionContext.getContext().getSession().get("userId");
		} else {
			return (Long) CusSession.getSession().get("userId");

		}*/
    }

    public Long getCurrentDeptId() {
        return (Long) REQUEST.getSession().getAttribute("deptId");
        //return (Long) ActionContext.getContext().getSession().get("deptId");
    }

    public Long getCurrentCompanyId() {
        return (Long) REQUEST.getSession().getAttribute("companyId");
        //return (Long) ActionContext.getContext().getSession().get("companyId");
    }

    // public SysUser getSysUser(){
    // return sysUserDao.findById(getCurrentUserId());
    // }
    public void deletes(String[] IDS) {
        // TODO Auto-generated method stub
        for (String id : IDS) {
            baseDao.delete(baseDao.findById(Long.parseLong(id)));
        }
    }

    public List<T> findAll() {
        // TODO Auto-generated method stub
        return baseDao.findAll();
    }

    public List<T> findByExample(T t) {
        // TODO Auto-generated method stub
        return baseDao.findByExample(t);
    }

    public T findById(Long id) {
        // TODO Auto-generated method stub
        return baseDao.findById(id);
    }

    public T findById(String id) {
        // TODO Auto-generated method stub
        return baseDao.findById(id);
    }

    public List<T> findByProperty(String propertyName, Object propertyValue) {
        // TODO Auto-generated method stub
        return baseDao.findByProperty(propertyName, propertyValue);
    }

    public List<T> findByProperty(String propertyName, Object propertyValue,
                                  String orderPropertyName) {
        return baseDao.findByProperty(propertyName, propertyValue,
                orderPropertyName);
    }

    public PaginationSupport<T> findPageByCriteria(PaginationSupport<T> ps, T t) {
        throw new RuntimeException("请重写findPageByCriteria方法");
    }

    public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
        return baseDao.findByCriteria(detachedCriteria);
    }

    public List<T> findByCriteria(DetachedCriteria detachedCriteria,
                                  int firstResult, int maxResults) {
        return baseDao
                .findByCriteria(detachedCriteria, firstResult, maxResults);
    }

    // public JsonPager<T> findJsonPageByCriteria(
    // JsonPager<T> jp, TicketIndent t) {
    // return null;
    // }
    public void save(T t) {
        baseDao.save(t);
    }

    public void saveOrUpdate(T t) {
        baseDao.saveOrUpdate(t);
    }

    public void update(T t) {
        baseDao.update(t);
    }

    public void delete(T t) {
        baseDao.delete(t);
    }

    public void initList(T t) {
        throw new RuntimeException("请重写initList方法");
    }

    /**
     * 初始化basedao
     *
     * @param baseDao
     */
    abstract protected void initBaseDAO(BaseDao<T> baseDao);

    public BaseDao<T> getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }
}
