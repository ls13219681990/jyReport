package com.dao.common;

import com.common.dao.BaseDao;
import com.model.Agreement;
import com.dao.page.ContractPage;

import java.util.List;

public interface ContractDao extends BaseDao<Agreement> {

    public List<ContractPage> findContractListSQL(ContractPage cap, String strSort);

    public List<ContractPage> findContractByIdListSQL(final ContractPage cap, final String strSort);
}