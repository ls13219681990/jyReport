/*
 *******************************************************************
 * 文件名:HibernateJsonBeanProcessorMatcher.java
 * 包名:action.sys.sysUser
 * 项目名:trms
 * 文件说明:
 * 作者:Administrator
 * 版权: Copyright (c) 2011  软通动力版权所有
 * 创建时间:2011-11-15 下午10:49:21
 *******************************************************************
 */
package com.common.jsonProcessor;

/**
 * 功能：
 *
 * @author lzp  email: zplie@isoftstone.com
 * @date 2011-11-15
 * <p>
 * 类名:action.sys.sysUser.HibernateJsonBeanProcessorMatcher <pre>
 * 描述:
 * 	基本思路:
 * 	public方法:
 * 	特别说明:
 * 编写者:李志鹏
 * 版权: Copyright (C) 2011  软通动力版权所有
 * 创建时间:2011-11-15 下午10:49:21
 * 修改说明: 类的修改说明
 * </pre>
 */
//******************************************************************
/**
 * 类名:action.sys.sysUser.HibernateJsonBeanProcessorMatcher <pre>
 * 描述:
 * 	基本思路:
 * 	public方法:
 * 	特别说明:
 * 编写者:李志鹏
 * 版权: Copyright (C) 2011  软通动力版权所有
 * 创建时间:2011-11-15 下午10:49:21
 * 修改说明: 类的修改说明
 * </pre>
 */
//******************************************************************

import java.util.Set;

import net.sf.json.processors.JsonBeanProcessorMatcher;

public class HibernateJsonBeanProcessorMatcher extends JsonBeanProcessorMatcher {

    @Override
    public Object getMatch(Class target, Set set) {
//		System.out.println(target.getName());
        if (target.getName().contains("$$EnhancerByCGLIB$$")) {
            return org.hibernate.proxy.HibernateProxy.class;
        } else if (target.getName().contains("$$_javassist")) {
            return org.hibernate.proxy.HibernateProxy.class;
        }
        return DEFAULT.getMatch(target, set);
    }
}
