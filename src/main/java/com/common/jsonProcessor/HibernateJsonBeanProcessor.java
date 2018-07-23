/*
 *******************************************************************
 * 文件名:HibernateJsonBeanProcessor.java
 * 包名:action.sys.sysUser
 * 项目名:trms
 * 文件说明:
 * 作者:Administrator
 * 版权: Copyright (c) 2011  软通动力版权所有
 * 创建时间:2011-11-15 下午10:48:44
 *******************************************************************
 */
package com.common.jsonProcessor;

/**
 * 功能：
 *
 * @author lzp  email: zplie@isoftstone.com
 * @date 2011-11-15
 * <p>
 * 类名:action.sys.sysUser.HibernateJsonBeanProcessor <pre>
 * 描述:
 * 	基本思路:
 * 	public方法:
 * 	特别说明:
 * 编写者:李志鹏
 * 版权: Copyright (C) 2011  软通动力版权所有
 * 创建时间:2011-11-15 下午10:48:44
 * 修改说明: 类的修改说明
 * </pre>
 */
//******************************************************************
/**
 * 类名:action.sys.sysUser.HibernateJsonBeanProcessor <pre>
 * 描述:
 * 	基本思路:
 * 	public方法:
 * 	特别说明:
 * 编写者:李志鹏
 * 版权: Copyright (C) 2011  软通动力版权所有
 * 创建时间:2011-11-15 下午10:48:44
 * 修改说明: 类的修改说明
 * </pre>
 */
//******************************************************************

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.*;

public class HibernateJsonBeanProcessor implements JsonBeanProcessor {
    public JSONObject processBean(Object obj, JsonConfig jsonConfig) {
        return new JSONObject();
    }
}
