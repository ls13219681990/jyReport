package com.common;

import com.common.action.EasyPager;
import com.common.action.JsonPager;
import com.common.action.PaginationSupport;
import com.common.dao.BaseBean;
import com.common.jsonProcessor.CommonJsonConfig;
import com.common.jsonProcessor.TimestampMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.springframework.context.annotation.Scope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.util.*;

@Scope("prototype")

public abstract class QueryAction<T> {
    private final static HttpServletRequest REQUEST = null;
    public static final HttpServletResponse RESPONSE = null;
    public static final String ALLINFO = "allInfo";
    public static final String QUERY = "query";
    public static final String QUERYONLY = "queryOnly";
    public static final String ALLINFOONLY = "allInfoOnly";
    public static final String EDIT = "edit";
    public static final String SUCCESS = "success";
    private static final long serialVersionUID = 1L;
    protected HttpServletResponse response;
    //	@Autowired
//	private SysUserService sysUserService;
    private PaginationSupport<T> ps = new PaginationSupport<T>();
    protected JsonPager<T> jp = new JsonPager<T>();
    protected EasyPager<T> ep = new EasyPager<T>();
    private String[] IDS;
    private String url;
    private String json;
    private String node;
    private String start;
    private String limit;
    private Integer page = 1;
    private Integer rows = 10;
    private Map<String, TreeMap<String, String>> codeMap = new HashMap<String, TreeMap<String, String>>();
    private String tableName;
    private String columnName;
    private String userId;

    final public String query() {
        return QUERY;
    }

    public void jsonPrint(JsonPager<T> jp) {
        RESPONSE.setContentType("text/plain");
        RESPONSE.setContentType("UTF-8");
        //ServletActionContext.getResponse().setContentType("text/plain");
        //ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            out = RESPONSE.getWriter();
            CommonJsonConfig jsonConfig = new CommonJsonConfig();
            JSONObject jsonObj = JSONObject.fromObject(jp, jsonConfig);
            out.print(jsonObj);
            out.flush();
            out.close();
            ;
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("json流对象获取出错");
        }

    }

    public void jsonPrintJP(JsonPager jp) {
        RESPONSE.setContentType("text/plain");
        RESPONSE.setContentType("UTF-8");
        //ServletActionContext.getResponse().setContentType("text/plain");
        //ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            out = RESPONSE.getWriter();
            CommonJsonConfig jsonConfig = new CommonJsonConfig();
            JSONObject jsonObj = JSONObject.fromObject(jp, jsonConfig);
            out.print(jsonObj);
            out.flush();
            out.close();
            ;
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("json流对象获取出错");
        }

    }

    public void jsonPrint(EasyPager<T> ep) {
        RESPONSE.setContentType("text/plain");
        RESPONSE.setContentType("UTF-8");

        //ServletActionContext.getResponse().setContentType("text/plain");
        //ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            out = RESPONSE.getWriter();
            CommonJsonConfig jsonConfig = new CommonJsonConfig();
            JSONObject jsonObj = JSONObject.fromObject(ep, jsonConfig);
            out.print(jsonObj);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("json流对象获取出错");
        }

    }

    public void jsonPrint(JSONObject obj) {
        RESPONSE.setContentType("text/plain");
        RESPONSE.setContentType("UTF-8");
        //ServletActionContext.getResponse().setContentType("text/plain");
        //ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            out = RESPONSE.getWriter();
            out.print(obj);
            out.flush();
            out.close();
            ;
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("json流对象获取出错");
        }
    }

    public void jsonPrint(JSONArray arr) {
        RESPONSE.setContentType("text/plain");
        RESPONSE.setContentType("UTF-8");
        //ServletActionContext.getResponse().setContentType("text/plain");
        //ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            out = RESPONSE.getWriter();
            out.print(arr);
            out.flush();
            out.close();
            ;
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("json流对象获取出错");
        }

    }

    //	public void jsonPrint(List<?> list){
//		ServletActionContext.getResponse().setContentType("text/plain");
//		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
//		PrintWriter out;
//		try {
//			out = ServletActionContext.getResponse().getWriter();
//			CommonJsonConfig jsonConfig = new CommonJsonConfig();
//	        JSONArray jsonArr =  JSONArray.fromObject(list, jsonConfig);
//	        out.print(jsonArr);
//			out.flush();
//			out.close();;
//		} catch (IOException e) {
//			e.printStackTrace();
//			throw new BusinessException("json流对象获取出错");
//		}
//		
//	}
    public void jsonPrint(String str) {
        RESPONSE.setContentType("text/plain");
        RESPONSE.setContentType("UTF-8");
        //ServletActionContext.getResponse().setContentType("text/plain");
        //ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            out = RESPONSE.getWriter();
            out.print(str);
            out.flush();
            out.close();
            ;
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("json流对象获取出错");
        }
    }

    public void jsonPrintException(String str) {
        str = "{\"exception\":\"" + str + "\"}";
        jsonPrint(str);
    }

    public void jsonPrintSuccess(String str) {
        str = "{\"success\":\"" + str + "\"}";
        jsonPrint(str);
    }


    /**
     * 通过javabean转化为jsonobj并输出,形成json形如:'{user.name:1,user.code:2}'
     *
     * @param obj      javabean实体
     * @param beanname 要生成的json中的key的前缀
     */
    public void jsonPrint(BaseBean obj, String beanname) {
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONObject jsonObj = JSONObject.fromObject(obj, jsonConfig);
        JSONObject jsonObject = new JSONObject();
        for (Iterator iterator = jsonObj.keys(); iterator.hasNext(); ) {
            Object key = iterator.next();
            String value = jsonObj.get(key).toString();
            if (value == null || value.equals("null")) {
                jsonObject.put(beanname + "." + key, "");
            } else {
                jsonObject.put(beanname + "." + key, value);
            }
        }
        jsonPrint(jsonObject);
    }

    /**
     * 通过javabean转化为jsonobj并输出,形成json形如:'{user.name:1,user.code:2}'
     *
     * @param obj javabean实体
     */
    public void jsonPrint(BaseBean obj) {

        String beanname = obj.getClass().getSimpleName();
        beanname = beanname.substring(0, 1).toLowerCase() + beanname.substring(1);
        jsonPrint(obj, beanname);
    }

    /**
     * 通过javabean转化为jsonobj并输出,形成json形如:'{name:1,code:2}'
     *
     * @param obj
     */
    public void jsonPrintOnlyProperty(BaseBean obj) {
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONObject jsonObj = JSONObject.fromObject(obj, jsonConfig);
        JSONObject jsonObject = new JSONObject();
        for (Iterator iterator = jsonObj.keys(); iterator.hasNext(); ) {
            Object key = iterator.next();
            String value = jsonObj.get(key).toString();
            if (value == null || value.equals("null")) {
                jsonObject.put(key, "");
            } else {
                jsonObject.put(key, value);
            }
        }
        jsonPrint(jsonObject);
    }

    /**
     * 通过javabean转化为jsonobj并输出,形成json形如:'{user.name:1,user.code:2}'
     *
     * @param obj      javabean实体
     * @param beanname 要生成的json中的key的前缀
     */
    public void jsonPrint(HashMap<String, Object> map) {
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONObject jsonObj = JSONObject.fromObject(map, jsonConfig);
        jsonPrint(jsonObj);
    }


    public void outPrintString(String str) {
        HttpServletResponse response = null;
        response.setContentType("text/plain");
        response.setContentType("UTF-8");
        //ServletActionContext.getResponse().setContentType("text/plain");
        //ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.print(str);
            out.flush();
            out.close();
            ;
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("string对象获取出错");
        }
    }

    public Collection<T> getColl() {
        String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
        JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
        return JSONArray.toCollection(JSONArray.fromObject(getJson()), (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public Collection<T> getColl(String json) {
        String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
        JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
        return JSONArray.toCollection(JSONArray.fromObject(json), (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public Object toBean(String json, Class c) {
        return JSONObject.toBean(JSONObject.fromObject(json), c);
    }

    public static PrintWriter getOut() throws IOException {

        RESPONSE.setContentType("text/plain");
        RESPONSE.setContentType("UTF-8");


        //ServletActionContext.getResponse().setContentType("text/plain");
        //ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
        return RESPONSE.getWriter();
    }

    /**
     * 通过code找到name
     *
     * @param tableName   表名，和数据库一致
     * @param columnName  列名，和数据库一致
     * @param columnValue 需要转化为name字段的值
     * @return
     */
    public String getNameByCode(String tableName, String columnName, String columnValue) {
        return CodeListener.getNameByCode(tableName, columnName, columnValue);
    }

    /**
     * 通过表名和字段名，获取编码集合TreeMap
     *
     * @param tableName  表名，和数据库一致
     * @param columnName 列名，和数据库一致
     * @return
     */
    public TreeMap<String, String> getTreeMap(String tableName, String columnName) {
        return CodeListener.getTreeMap(tableName, columnName);
    }

//	/**
//	 * 通过表名和字段名，获取编码集合list,中对象为sysCode,只含有codeRelation,codeName,codeValue的值
//	 * @param tableName 表名，和数据库一致
//	 * @param columnName 列名，和数据库一致
//	 * @return
//	 */
//	public List<SysCode> getList(String tableName,String columnName){
//		return CodeListener.getList(tableName, columnName);
//	}

    /**
     * 通过表名和字段名，获取编码集合JSONArray
     *
     * @param tableName  表名，和数据库一致
     * @param columnName 列名，和数据库一致
     * @return
     */
    public JSONArray getJson(String tableName, String columnName) {
        return CodeListener.getDefaultJson(tableName, columnName);
    }

    /**
     * 通过表名和字段名，获取编码集合JSONArray
     *
     * @param tableName  表名，和数据库一致
     * @param columnName 列名，和数据库一致
     * @return
     */
    public void getSelectJson() {
        jsonPrint(getJson(tableName, columnName));
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        jp.setStart(start);
        this.start = start;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
        ep.setPageNumber(page);
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
        ep.setPageSize(rows);
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        jp.setLimit(limit);
        this.limit = limit;
    }

    public PaginationSupport<T> getPs() {
        return ps;
    }

    public String[] getIDS() {
        return IDS;
    }

    public void setIDS(String[] ids) {
        IDS = ids;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCurrentUserId() {
        return (Long) REQUEST.getSession().getAttribute("userId");
    }

    public Long getCurrentDeptId() {
        return (Long) REQUEST.getSession().getAttribute("deptId");
    }

    public Long getCurrentCompanyId() {
        return (Long) REQUEST.getSession().getAttribute("companyId");
    }

    //	public SysUser getCurrentUser() {
//		return sysUserService.findById(getCurrentUserId());
//	}
    public void setPs(PaginationSupport<T> ps) {
        this.ps = ps;
    }

    public JsonPager<T> getJp() {
        return jp;
    }

    public void setJp(JsonPager<T> jp) {
        this.jp = jp;
    }

    public EasyPager<T> getEp() {
        return ep;
    }

    public void setEp(EasyPager<T> ep) {
        this.ep = ep;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
