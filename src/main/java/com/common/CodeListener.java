package com.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class CodeListener implements ServletContextListener {
    private static WebApplicationContext springContext;
    private static Map<String, TreeMap<String, String>> codeMap = new HashMap<String, TreeMap<String, String>>();
    private static Map<Long, String> employeeMap = new HashMap<Long, String>();
    //实时出票数量，仅当天
    private static List<Map<String, Object>> ticketList = new ArrayList<Map<String, Object>>();

    @Override
    public void contextInitialized(ServletContextEvent event) {
        springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
//		SysCodeDao sysCodeDao = (SysCodeDao) springContext.getBean("sysCodeDao");
//		
//		//查询系统编码
//		DetachedCriteria dc =DetachedCriteria.forClass(SysCode.class);
//		dc.add(Property.forName("status").eq("11"));
//		dc.addOrder(Order.asc("codeRelation").asc("codeValue"));
//		List<SysCode> sysCodes = sysCodeDao.findByCriteria(dc);
//		for (int i = 0; i < sysCodes.size(); i++) {
//			SysCode sysCode = sysCodes.get(i);
//			String key1 = sysCode.getCodeRelation();
//			String key2 = sysCode.getCodeValue();
//			String value = sysCode.getCodeName();
//			TreeMap<String, String> treeMap = codeMap.get(key1);
//			if(treeMap==null){
//				treeMap = new TreeMap<String, String>();
//				treeMap.put(key2, value);
//				codeMap.put(key1, treeMap);
//			}
//			else{
//				treeMap.put(key2, value);
//			}
//		}
//		
//		//查询员工
//		SysUserDao sysUserDao = (SysUserDao) springContext.getBean("sysUserDao");
//		DetachedCriteria dcE =DetachedCriteria.forClass(SysUser.class);
//		dcE.add(Property.forName("userType").eq("01"));
//		dcE.createAlias("baseEmployee", "baseEmployee");
//		List<SysUser> sysUsers = sysUserDao.findByCriteria(dcE);
//		for (int i = 0; i < sysUsers.size(); i++) {
//			SysUser sysUser = sysUsers.get(i);
//			employeeMap.put(sysUser.getUserId(), sysUser.getBaseEmployee().getEmployeeName());
//		}
//		
//		//启动时默认数据为0
//		//用于排序
//		List<String> keyList = new ArrayList<String>();
//		keyList.add("00");
//		keyList.add("11");
//		keyList.add("22");
//		keyList.add("33");
//		
//		for(String key : keyList){
//			Map<String,Object> subMap = new HashMap<String,Object>();
//			String type = "平台购票";
//			if("00".equals(key)){
//				type = "现场售票";
//			}else if("11".equals(key)){
//				type = "平台购票";
//			}else if("22".equals(key)){
//				type = "自助购票";
//			}else if("33".equals(key)){
//				type = "移动终端购票";
//			}
//			subMap.put("payType", key);
//			subMap.put("type", type);
//			subMap.put("nums", 0);
//			subMap.put("sums", 0.0);
//			ticketList.add(subMap);
//		}
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }

    /**
     * 通过code找到name
     *
     * @param tableName   表名，和数据库一致
     * @param columnName  列名，和数据库一致
     * @param columnValue 需要转化为name字段的值
     * @return
     */
    public static String getNameByCode(String tableName, String columnName, String columnValue) {
        TreeMap<String, String> treeMap = codeMap.get(tableName.toUpperCase() + "|" + columnName.toUpperCase());
        if (columnValue != null && treeMap != null) {
            return treeMap.get(columnValue);
        } else {
            return "";
        }
    }

    /**
     * 通过表名和字段名，获取编码集合TreeMap
     *
     * @param tableName  表名，和数据库一致
     * @param columnName 列名，和数据库一致
     * @return
     */
    public static TreeMap<String, String> getTreeMap(String tableName, String columnName) {
        TreeMap<String, String> treeMap = codeMap.get(tableName.toUpperCase() + "|" + columnName.toUpperCase());
        return treeMap;
    }

//	/**
//	 * 通过表名和字段名，获取编码集合list,中对象为sysCode,只含有codeRelation,codeName,codeValue的值
//	 * @param tableName 表名，和数据库一致
//	 * @param columnName 列名，和数据库一致
//	 * @return
//	 */
//	public static List<SysCode> getList(String tableName,String columnName){
//		String codeRelation = tableName.toUpperCase()+"|"+columnName.toUpperCase();
//		TreeMap<String, String> treeMap = codeMap.get(codeRelation);
//		List<SysCode> sysCodes = new ArrayList<SysCode>();
//		Set<String> set = treeMap.keySet();
//		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
//			String codeValue = (String) iterator.next();
//			String codeName = treeMap.get(codeValue);
//			SysCode sysCode = new SysCode();
//			sysCode.setCodeRelation(codeRelation);
//			sysCode.setCodeValue(codeValue);
//			sysCode.setCodeName(codeName);
//			sysCodes.add(sysCode);
//		}
//		return sysCodes;
//	}

    /**
     * 通过表名和字段名，获取编码集合JSONArray[{id:1,text:2},{...}]形式
     *
     * @param tableName  表名，和数据库一致
     * @param columnName 列名，和数据库一致
     * @return
     */
    public static JSONArray getDefaultJson(String tableName, String columnName) {
        TreeMap<String, String> treeMap = codeMap.get(tableName.toUpperCase() + "|" + columnName.toUpperCase());
        Set<String> set = treeMap.keySet();
        JSONArray jsonArray = new JSONArray();
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            String codeValue = (String) iterator.next();
            String codeName = treeMap.get(codeValue);
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("id", codeValue);
            jsonObject.accumulate("text", codeName);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

//	/**
//	 * 更新map
//	 * @param codeRelation 表名|字段名,和数据库一致
//	 * @param sysCodes 编码值集合
//	 */
//	public static void updateMap(List<SysCode> sysCodes){
//		if(sysCodes!=null && sysCodes.size()>0){
//			TreeMap<String, String> treeMap = new TreeMap<String, String>();
//			String key1="code";
//			for (int i = 0; i < sysCodes.size(); i++) {
//				SysCode sysCode = sysCodes.get(i);
//				key1 = sysCode.getCodeRelation();
//				String key2 = sysCode.getCodeValue();
//				String value = sysCode.getCodeName();
//				treeMap.put(key2, value);
//			}
//			codeMap.put(key1, treeMap);
//		}
//	}

    /**
     * 更新map
     *
     * @param codeRelation 表名|字段名,和数据库一致
     * @param sysCodes     编码值集合
     */
    public static void updateEmployeeMap(long userId, String employeeName) {
        employeeMap.put(userId, employeeName);
    }

    /**
     * 通过表名和字段名，获取编码集合JSONArray[{id:1,text:2},{...}]形式
     *
     * @param tableName  表名，和数据库一致
     * @param columnName 列名，和数据库一致
     * @return
     */
    public static String getNameByUserId(long userId) {
        return employeeMap.get(userId);
    }

    public static List<Map<String, Object>> getTicketList() {
        return ticketList;
    }

    public static void setTicketList(List<Map<String, Object>> ticketList) {
        CodeListener.ticketList = ticketList;
    }


}
