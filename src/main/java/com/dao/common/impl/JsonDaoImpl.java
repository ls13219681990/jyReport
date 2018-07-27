package com.dao.common.impl;

/*import com.casit.czy.taglib.JsonA;
import com.casit.czy.taglib.JsonE;
import com.casit.czy.taglib.JsonO;
import com.casit.czy.taglib.Recursion;*/

import com.common.CommonMethod;
import com.dao.common.JsonDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository("jsonDao")
public class JsonDaoImpl extends HibernateDaoSupport implements JsonDao {
    @Autowired
    protected void initSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /**
     * 直接执行sql返回一个JSONArray
     *
     * @param sql
     * @param unquote 标注封装到jsonarray中值不需要加引号的列名
     * @return
     */
    public JSONArray findSqlToJSONArray(final String sql, final String unquote) {
        final String unquptenames = "," + unquote + ",";
//		System.out.println(unquptenames);
        List listView = getHibernateTemplate().executeFind(new HibernateCallback() {
            public JSONArray doInHibernate(Session session) throws HibernateException, SQLException {
                JSONArray jsonArray = null;
                Connection conn = null;
                Statement statement = null;
                ResultSet rs = null;
                try {
                    jsonArray = new JSONArray();
                    JSONObject object = new JSONObject();
                    //Connection conn = session.connection();
                    conn = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
                    String s = "[";
                    statement = conn.createStatement();
                    rs = statement.executeQuery(sql.toString());
                    ResultSetMetaData resultSetMetaData = rs.getMetaData();
                    int count = resultSetMetaData.getColumnCount();
                    String[] columnnames = new String[count];
                    for (int i = 0; i < count; i++) {
                        columnnames[i] = resultSetMetaData.getColumnLabel(i + 1).toLowerCase();
                    }
//					String[] names = sql.substring(sql.indexOf("select") + 6, sql.indexOf("from")).trim().split(",");
                    while (rs.next()) {
                        object = new JSONObject();
                        for (int j = 0; j < count; j++) {
                            if (unquptenames.indexOf("," + columnnames[j] + ",") != -1) {
                                String temStr = rs.getString(columnnames[j]);
                                if (CommonMethod.isNumber(temStr)) {
                                    object.put(columnnames[j], rs.getInt(columnnames[j]));
                                } else {
                                    object.put(columnnames[j], rs.getString(columnnames[j]).equals("false") ? false : true);
                                }
                            } else {
                                object.element(columnnames[j], rs.getString(columnnames[j]));
                            }
                        }
                        jsonArray.element(object);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                try {
                    if (rs != null) {
                        rs.close();
                        rs = null;
                    }
                    if (statement != null) {
                        statement.close();
                        statement = null;
                    }
                    if (conn != null) {
                        conn.close();
                        conn = null;
                    }
                } catch (Exception e) {
                    if (rs != null) {
                        rs.close();
                        rs = null;
                    }
                    if (statement != null) {
                        statement.close();
                        statement = null;
                    }
                    if (conn != null) {
                        conn.close();
                        conn = null;
                    }
                } finally {
                    if (rs != null) {
                        rs.close();
                        rs = null;
                    }
                    if (statement != null) {
                        statement.close();
                        statement = null;
                    }
                    if (conn != null) {
                        conn.close();
                        conn = null;
                    }
                }
                return jsonArray;
            }
        });
        return JSONArray.fromObject(listView);
    }

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
    public JSONObject findSqlToJSONObject(final String sql, final String idcol, final String start, final String limit,
                                          String unquote) {
        JSONObject returnobject = new JSONObject();
        final String unquptenames = "," + unquote + ",";
        List listView = getHibernateTemplate().executeFind(new HibernateCallback() {
            public JSONArray doInHibernate(Session session) throws HibernateException, SQLException {
                JSONArray jsonArray = new JSONArray();
                JSONObject object;
                //Connection conn = session.connection();
                Connection conn = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
                CallableStatement cstmt = conn.prepareCall("{call sp_pagination(?,?,?,?,?)}");
                cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
                cstmt.setString(1, sql);
                cstmt.setString(2, idcol);
                cstmt.setString(3, start);
                cstmt.setString(4, limit);
                ResultSet rs = cstmt.executeQuery();
                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                int count = resultSetMetaData.getColumnCount();
                String[] columnnames = new String[count];
                for (int i = 0; i < count; i++) {
                    columnnames[i] = resultSetMetaData.getColumnLabel(i + 1);
                }
                while (rs.next()) {
                    object = new JSONObject();
                    for (int j = 0; j < count; j++) {
                        if (unquptenames.indexOf("," + columnnames[j].toLowerCase() + ",") != -1) {
                            String temStr = rs.getString(columnnames[j]);
                            if (CommonMethod.isNumber(temStr)) {
                                object.put(columnnames[j].toLowerCase(), rs.getInt(columnnames[j]));
                            } else {
                                object.put(columnnames[j].toLowerCase(),
                                        rs.getString(columnnames[j]).equals("false") ? false : true);
                            }
                        } else {
                            object.element(columnnames[j].toLowerCase(), rs.getString(columnnames[j]));
                        }
                    }
                    jsonArray.element(object);
                }
                try {
                    if (rs != null) {
                        rs.close();
                        rs = null;
                    }
                    if (cstmt != null) {
                        cstmt.close();
                        cstmt = null;
                    }
                    if (conn != null) {
                        conn.close();
                        conn = null;
                    }
                } catch (Exception e) {
                    if (rs != null) {
                        rs.close();
                        rs = null;
                    }
                    if (cstmt != null) {
                        cstmt.close();
                        cstmt = null;
                    }
                    if (conn != null) {
                        conn.close();
                        conn = null;
                    }
                } finally {
                    if (rs != null) {
                        rs.close();
                        rs = null;
                    }
                    if (cstmt != null) {
                        cstmt.close();
                        cstmt = null;
                    }
                    if (conn != null) {
                        conn.close();
                        conn = null;
                    }
                }
                return jsonArray;
            }
        });
        returnobject.element("total", listView.size());
        returnobject.element("root", JSONArray.fromObject(listView));
        return returnobject;
    }

    /**
     * 保存EXT传回的JSONArray
     *
     * @param arr       数据集合
     * @param beanClass 实体类的类对象
     * @param id        主键名称(注意大小写)
     */
    public void saveJsonArray(JSONArray arr, Class<?> beanClass, String id) {
        for (int i = 1; i < arr.size(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            if (obj.getString(id).indexOf("insert") != -1) {
                obj.put(id, obj.getString(id).replace("insert_", ""));
                getHibernateTemplate().save(JSONObject.toBean(obj, beanClass));
            } else {
                getHibernateTemplate().update(JSONObject.toBean(obj, beanClass));
            }
        }
    }

    /**
     * 直接执行SQL
     *
     * @param sql
     */
    public void executeSql(final String sql) {
        try {
            getHibernateTemplate().execute(new HibernateCallback() {
                public Object doInHibernate(Session session)
                        throws HibernateException, SQLException {
                    //Connection conn = session.connection();
                    Connection conn = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
                    Statement statement = conn.createStatement();
                    statement.execute(sql.toString());
                    try {
                        if (statement != null) {
                            statement.close();
                            statement = null;
                        }
                        if (conn != null) {
                            conn.close();
                            conn = null;
                        }
                    } catch (Exception e) {
                        if (statement != null) {
                            statement.close();
                            statement = null;
                        }
                        if (conn != null) {
                            conn.close();
                            conn = null;
                        }
                    } finally {
                        if (statement != null) {
                            statement.close();
                            statement = null;
                        }
                        if (conn != null) {
                            conn.close();
                            conn = null;
                        }
                    }
                    return null;
                }
            });
        } catch (RuntimeException re) {
            throw re;
        }
    }

    /**
     * 获取store数据
     *
     * @param sql     执行的sql语句
     * @param unquote 不增加引号的段
     * @return
     */
    public JSONObject getStore(String sql, String unquote) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("root", findSqlToJSONArray(sql, unquote));
        return jsonObject;
    }

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
	/*public String getJsonTree(String sql,String parcol,String idcol,String unquoted)
	{
		class rectree implements Recursion
		{
			public void recursion(String id,JsonO parent, JsonA childs) {
				if(childs.size()>0){
					parent.put("children", childs);
					parent.putUnQuoted("leaf", "false");
				}else
					parent.putUnQuoted("leaf", "true");
			}
		}
		JsonO obj;
		try {
			obj = getRecursion(new rectree(),sql,parcol,idcol,unquoted);
			return obj.getJsonA("children").toString();
		} catch (JsonE e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}*/
	
	
	/*private JsonO getRecursion(Recursion rectree, String sql, String parcol,
			String idcol, String unquoted) throws JsonE {
		JsonA arr = new JsonA(findSqlToJSONArray(sql,unquoted).toString());
		JsonO obj = new JsonO();
		recfunc(rectree,arr,obj,"0",parcol,idcol);
		return obj;
	}*/
	
	
	/*private void recfunc(Recursion rectree, JsonA arr, JsonO node,String id, String parcol, String idcol) throws JsonE {
		JsonA childs = new JsonA();
		for(int i=0;i<arr.size();i++)
		{
			JsonO obj = arr.getJsonO(i);
			if(obj.getString(parcol).equals(id)){
				childs.add(obj);
				recfunc(rectree,arr,obj,obj.getString(idcol),parcol,idcol);
			}
		}
		rectree.recursion(id,node, childs);
		
	}*/
    public boolean isHaveRole(final String actionName, final Integer userId) {
        Object obj = getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                StringBuffer sql = new StringBuffer();
                sql.append("select count(*) FROM ");
                sql.append(" (select relatePage from sys_user_function left join sys_function on sys_user_function.functionId = sys_function.functionid where sys_user_function.userID = " + userId);
                sql.append(" union ");
                sql.append(" select relatePage from sys_role_function left join sys_function on sys_role_function.functionId = sys_function.functionid where sys_role_function.roleID in (select roleid from sys_user_role where sys_user_role.userID =" + userId + ") ");
                sql.append(" )A where  A.relatePage like '%" + actionName + "%' ");
                Query query = session.createSQLQuery(sql.toString());
                List list = query.list();
                Object obj = list.get(0);
                if (Integer.parseInt(obj.toString()) > 0) {
                    return true;
                }
                return false;
            }
        });
        if (obj.toString().equals("true")) {
            return true;
        } else {
            return false;
        }
    }
}
