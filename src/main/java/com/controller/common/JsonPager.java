/*
 *******************************************************************
 * 文件名:JsonPager.java
 * 包名:common.action
 * 项目名:trms
 * 文件说明:
 * 作者:Administrator
 * 版权: Copyright (c) 2011  软通动力版权所有
 * 创建时间:2011-11-16 下午09:25:56
 *******************************************************************
 */
package com.controller.common;

import java.util.List;

/**
 * 功能：
 *
 * @author lzp  email: zplie@isoftstone.com
 * @date 2011-11-16
 */
//******************************************************************

/**
 * 类名:common.action.JsonPager
 *
 * <pre>
 * 描述:
 * 	基本思路:
 * 	public方法:
 * 	特别说明:
 * 编写者:李志鹏
 * 版权: Copyright (C) 2011  软通动力版权所有
 * 创建时间:2011-11-16 下午09:25:56
 * 修改说明: 类的修改说明
 * </pre>
 */
// ******************************************************************
public class JsonPager<T> {
    private String start = "0";
    private String limit = "10";
    private int total;
    private List<T> root;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRoot() {
        return root;
    }

    public void setRoot(List<T> root) {
        this.root = root;
    }

}
