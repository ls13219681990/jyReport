package com.dao.common;

import com.common.dao.BaseDao;
import com.dao.page.CapitalAccountPage;
import com.model.CapitalAccount;

import java.util.List;


public interface CapitalAccountDao extends BaseDao<CapitalAccount> {

    public List<CapitalAccountPage> findCaListSQL(CapitalAccountPage cap);

    public List<CapitalAccountPage> findEntrustCaListSQL(CapitalAccountPage cap);

    String findMaxCapitalAccountCode();
}