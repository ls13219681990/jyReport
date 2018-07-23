package com.service.sys.impl;

import com.dao.sys.SysTablePkDao;
import com.service.sys.SysTablePkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysTablePkService")
public class SysTablePkServiceImpl implements SysTablePkService {
    @Autowired
    private SysTablePkDao sysTablePkDao;

    @Override
    public Long trueGetNextKey(String tablename, int count) {
        return sysTablePkDao.getNextKey(tablename, count);
    }

}
