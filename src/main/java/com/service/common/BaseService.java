package com.service.common;

import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;


public interface BaseService<T> {

    /**
     * @param //实体对象
     * @param //翻页参数
     * @return
     * @throws Exception
     */
    public PaginationSupport<T> findPageByCriteria(PaginationSupport<T> ps, T t);

    /**
     * @author Administrator
     */
    public JsonPager<T> findJsonPageByCriteria(JsonPager<T> jp,
                                               T t);

    /**
     * 保存
     *
     * @param t
     */
    public void save(T t);

    /**
     * 保存或者修改
     *
     * @param t
     */
    public void saveOrUpdate(T t);

    /**
     * 修改
     *
     * @param t
     */
    public void update(T t);

    /**
     * 根据对象IDS删除
     *
     * @param //t 实体对象
     */
    public void deletes(String[] IDS);

    /**
     * 根据主键查找
     *
     * @param //t 含有主键值的实体对象
     * @return 实体对象
     */
    public T findById(Long id);

    /**
     * 根据主键查找
     *
     * @param //t 含有主键值的实体对象
     * @return 实体对象
     */
    public T findById(String id);

    /**
     * 查找所有实体
     *
     * @return
     */
    public List<T> findAll();

    /**
     * 根据对象查找
     *
     * @param t
     * @return 实体列表
     */
    public List<T> findByExample(T t);

    /**
     * 根据属性名查找
     *
     * @param propertyName  属性名称
     * @param propertyValue 属性值
     * @return 实体列表
     */
    public List<T> findByProperty(String propertyName, Object propertyValue);

    /**
     * 根据属性名查找并排充
     *
     * @param propertyName  属性名称
     * @param propertyValue 属性值
     * @param orderName     排序属性名称
     * @return
     */
    public List<T> findByProperty(String propertyName, Object propertyValue, String orderName);

    /**
     * 根据对象删除
     *
     * @param t 实体对象
     */
    public void delete(T t);

    public void initList(T t);

    /**
     * 通过条件查询
     *
     * @param detachedCriteria 条件
     * @return
     */
    public List<T> findByCriteria(DetachedCriteria detachedCriteria);

    /**
     * 带有条数的查询
     *
     * @param detachedCriteria
     * @param firstResult      开始行
     * @param maxResults       结束行
     * @return
     */
    public List<T> findByCriteria(DetachedCriteria detachedCriteria, int firstResult, int maxResults);

}
