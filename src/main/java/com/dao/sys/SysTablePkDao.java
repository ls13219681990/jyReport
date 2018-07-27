package com.dao.sys;

import com.dao.common.BaseDao;
import com.model.SysTablePk;

public interface SysTablePkDao extends BaseDao<SysTablePk> {

    public Long getNextKey(String tablename, int count);

}
