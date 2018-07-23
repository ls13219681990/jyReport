package com.service.sys;

import com.common.service.BaseService;
import com.model.SysCode;

import java.util.Collection;
import java.util.List;

public interface SysCodeService extends BaseService<SysCode> {

    List<SysCode> findSysCodes();

    List<SysCode> findPitchOncode(String codeRelation);

    String findCodeName(String codeRelation, String codeValue);

    void saveCode(Collection<SysCode> coll);

    void updateCode(Collection<SysCode> coll);
}