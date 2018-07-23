package com.service.common;

import com.common.service.BaseService;
import com.model.RunningNum;

public interface RunningNumService extends BaseService<RunningNum> {

    Long getNextNum(String item, int count);
}