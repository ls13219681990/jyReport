/*
 *******************************************************************
 * 文件名:DateJsonBeanProcessor.java
 * 包名:action.sys.sysUser
 * 项目名:trms
 * 文件说明:
 * 作者:Administrator
 * 版权: Copyright (c) 2011  软通动力版权所有
 * 创建时间:2011-11-16 上午09:17:29
 *******************************************************************
 */
package com.common.jsonProcessor;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonBeanProcessor;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 功能：
 *
 * @author lzp  email: zplie@isoftstone.com
 * @date 2011-11-16
 */
//******************************************************************

/**
 * 类名:action.sys.sysUser.DateJsonBeanProcessor
 *
 * <pre>
 * 描述:
 * 	基本思路:
 * 	public方法:
 * 	特别说明:
 * 编写者:李志鹏
 * 版权: Copyright (C) 2011  软通动力版权所有
 * 创建时间:2011-11-16 上午09:17:29
 * 修改说明: 类的修改说明
 * </pre>
 */
// ******************************************************************
public class DateJsonValueProcessor implements JsonValueProcessor {

    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    public Object processObjectValue(String key, Object value,
                                     JsonConfig jsonConfig) {
        return process(value);
    }

    private Object process(Object value) {
        return dateFormat.format((Date) value);
    }
}
