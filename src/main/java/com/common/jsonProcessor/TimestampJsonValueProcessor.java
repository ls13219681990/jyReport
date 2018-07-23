/*
 *******************************************************************
 * 文件名:TimestampJsonValueProcessor.java
 * 包名:action.sys.sysUser
 * 项目名:trms
 * 文件说明:
 * 作者:Administrator
 * 版权: Copyright (c) 2011  软通动力版权所有
 * 创建时间:2011-11-16 上午10:09:45
 *******************************************************************
 */
package com.common.jsonProcessor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 功能：
 *
 * @author lzp  email: zplie@isoftstone.com
 * @date 2011-11-16
 */
//******************************************************************

/**
 * 类名:action.sys.sysUser.TimestampJsonValueProcessor <pre>
 * 描述:
 * 	基本思路:
 * 	public方法:
 * 	特别说明:
 * 编写者:李志鹏
 * 版权: Copyright (C) 2011  软通动力版权所有
 * 创建时间:2011-11-16 上午10:09:45
 * 修改说明: 类的修改说明
 * </pre>
 */
//******************************************************************
public class TimestampJsonValueProcessor implements JsonValueProcessor {

    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //**************************************************************************

    /**
     * TODO 覆盖方法processArrayValue简单说明 <br><pre>
     * 覆盖方法processArrayValue详细说明 <br>
     * 编写者：李志鹏
     * 创建时间：2011-11-16 上午10:10:11 </pre>
     * @param 参数类型 参数名 说明
     * @return 返回值类型 说明
     * @throws 异常类型 说明
     * @see net.sf.json.processors.JsonValueProcessor#processArrayValue(Object, net.sf.json.JsonConfig)
     */
    //**************************************************************************
    @Override
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }


    //**************************************************************************

    /**
     * TODO 覆盖方法processObjectValue简单说明 <br><pre>
     * 覆盖方法processObjectValue详细说明 <br>
     * 编写者：李志鹏
     * 创建时间：2011-11-16 上午10:10:11 </pre>
     * @param 参数类型 参数名 说明
     * @return 返回值类型 说明
     * @throws 异常类型 说明
     * @see net.sf.json.processors.JsonValueProcessor#processObjectValue(String, Object, net.sf.json.JsonConfig)
     */
    //**************************************************************************
    @Override
    public Object processObjectValue(String str, Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    private Object process(Object value) {
//		 bean = format.format(((java.sql.Timestamp) bean).getTime());
        if (value == null) {
            return "";
        }
        return dateFormat.format(((java.sql.Timestamp) value).getTime());
    }
}

