package com.service.common.impl;

import com.common.action.JsonPager;
import com.common.dao.BaseDao;
import com.common.service.BaseServiceImpl;
import com.model.PaymentType;
import com.service.common.PaymentTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("paymentTypeService")
public class PaymentTypeServiceImpl extends BaseServiceImpl<PaymentType> implements
        PaymentTypeService {

    @Override
    @Resource(name = "paymentTypeDao")
    protected void initBaseDAO(BaseDao<PaymentType> baseDao) {
        setBaseDao(baseDao);
    }

    @Override
    public JsonPager<PaymentType> findJsonPageByCriteria(JsonPager<PaymentType> jp,
                                                         PaymentType t) {
        return null;
    }

}