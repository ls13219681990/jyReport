package com.service.common.impl;

import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.model.PaymentType;
import com.service.common.PaymentTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
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