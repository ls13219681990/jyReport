package com.controller.common;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller
public abstract class BaseController {
    private final static HttpServletRequest REQUEST = null;

    static {
        ConvertUtils.register(new SqlDateConverter(null), java.sql.Date.class);
        ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer userId;
    private String type = "";

    public BaseController() {
		/*Map session = ActionContext.getContext().getSession();
		if(session != null){
			userId = (Integer)session.get("userId");
		}*/
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
