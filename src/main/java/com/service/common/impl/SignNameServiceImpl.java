package com.service.common.impl;

import com.common.action.JsonPager;
import com.common.dao.BaseDao;
import com.common.service.BaseServiceImpl;
import com.model.SignName;
import com.service.common.SignNameService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("signNameService")
public class SignNameServiceImpl extends BaseServiceImpl<SignName> implements
        SignNameService {

    @Override
    @Resource(name = "signNameDao")
    protected void initBaseDAO(BaseDao<SignName> baseDao) {
        setBaseDao(baseDao);
    }

    @Override
    public JsonPager<SignName> findJsonPageByCriteria(JsonPager<SignName> jp,
                                                      SignName t) {
        return null;
    }

}