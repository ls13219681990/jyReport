package com.dao.sys;

import com.common.dao.BaseDao;
import com.model.SysTablePk;

public interface SysTablePkDao extends BaseDao<SysTablePk> {

    public Long getNextKey(String tablename, int count);

}
