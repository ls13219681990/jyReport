package com.service.common;

import com.common.service.BaseService;
import com.model.SupervisionUnit;

import java.util.Collection;
import java.util.List;

public interface SupervisionUnitService extends BaseService<SupervisionUnit> {

    List<SupervisionUnit> findSupervisionUnit(String strSupervisionUnitId, String strSupervisionUnitName, String strWitnesses);

    List<SupervisionUnit> findSupervisionUnitName(String strSupervisionUnitId);

    String saveSupervisionUnit(Collection<SupervisionUnit> coll, String userId);

    void updateSupervisionUnit(Collection<SupervisionUnit> coll, String userId);
}