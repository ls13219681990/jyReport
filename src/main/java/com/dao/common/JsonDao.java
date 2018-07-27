package com.dao.common;

/*import com.casit.czy.taglib.JsonA;
import com.casit.czy.taglib.JsonE;
import com.casit.czy.taglib.JsonO;
import com.casit.czy.taglib.Recursion;*/

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface JsonDao {

    /**
     * 直接执行sql返回一个JSONArray
     *
     * @param sql
     * @param unquote 标注封装到jsonarray中值不需要加引号的列名
     * @return
     */
    public JSONArray findSqlToJSONArray(final String sql, final String unquote);

    /**
     * 直接运行sql返回json对象
     *
     * @param sql     查询语句
     * @param idcol   主键
     * @param start   开始数（翻页）
     * @param limit   结束数（翻页）
     * @param unquote json中不用加引号字段
     * @return
     */
    public JSONObject findSqlToJSONObject(final String sql, final String idcol,
                                          final String start, final String limit, String unquote);

    /**
     * 保存EXT传回的JSONArray
     *
     * @param arr       数据集合
     * @param beanClass 实体类的类对象
     * @param id        主键名称(注意大小写)
     */
    public void saveJsonArray(JSONArray arr, Class<?> beanClass, String id);

    /**
     * 直接执行SQL
     *
     * @param sql
     */
    public void executeSql(final String sql);

    /**
     * 获取store数据
     *
     * @param sql     执行的sql语句
     * @param unquote 不增加引号的段
     * @return
     */
    public JSONObject getStore(String sql, String unquote);

    /**
     * 获取树
     *
     * @param //sql      sql语句
     * @param //parcol   父ID字段名
     * @param //idcol    ID字段名
     * @param //unquoted 不加引号数据
     * @return
     * @throws //JsonE
     */
    //public String getJsonTree(String sql, String parcol, String idcol, String unquoted) throws JsonE;
    public boolean isHaveRole(final String actionName, final Integer userId);
}
