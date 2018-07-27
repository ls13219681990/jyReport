package com.service.common;

import com.dao.page.CapitalAccountLinkmpPage;
import com.dao.page.CapitalAccountPage;
import com.model.CapitalAccount;

import java.util.Collection;
import java.util.List;

public interface CapitalAccountService extends BaseService<CapitalAccount> {

    String saveCapitalAccount(Collection<CapitalAccountPage> coll, String userId);

    void saveCapitalAccountTable(Collection<CapitalAccountPage> coll, String userId);

    void updateCapitalAccount(CapitalAccountPage coll, String userId);

    String findNewCaCode();

    public List<CapitalAccountPage> findCaList(CapitalAccountPage cap);

    public List<CapitalAccountPage> findEntrustCaList(CapitalAccountPage cap);

    void saveCapitalAccountLinkmp(CapitalAccountLinkmpPage calPage);

    String findByCompanyIdProjectId(String CompanyId, String projectId);

    List<CapitalAccountLinkmpPage> findEntrustCaListInfo(String capitalAccountId);


}