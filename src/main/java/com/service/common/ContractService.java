package com.service.common;

import com.common.service.BaseService;
import com.dao.page.ContractPage;
import com.model.Agreement;

import java.util.Collection;
import java.util.List;

public interface ContractService extends BaseService<Agreement> {

    void saveContract(Collection<ContractPage> coll, String userId);

    void updateContract(Collection<ContractPage> coll, String userId);

    public List<ContractPage> findCaList(ContractPage cap, String strSort);

    public List<ContractPage> findCaListById(ContractPage cap, String strSort);
}