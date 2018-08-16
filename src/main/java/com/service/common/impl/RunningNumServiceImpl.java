package com.service.common.impl;

import com.common.CommonMethod;
import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.dao.common.RunningNumDao;
import com.model.RunningNum;
import com.service.common.RunningNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Service("runningNumService")
public class RunningNumServiceImpl extends BaseServiceImpl<RunningNum> implements
        RunningNumService {

    @Autowired
    private RunningNumDao runningNumDao;

    @Override
    @Resource(name = "runningNumDao")
    protected void initBaseDAO(BaseDao<RunningNum> baseDao) {
        setBaseDao(baseDao);
    }

    @Override
    public JsonPager<RunningNum> findJsonPageByCriteria(JsonPager<RunningNum> jp,
                                                        RunningNum t) {
        return null;
    }


    public Long getNextNum(String item, int count) {

        RunningNum runingNum = new RunningNum();
        Long returnstr = Long.valueOf(0L);
        runingNum = runningNumDao.findById(item);
        if ((runingNum == null) || (CommonMethod.isNull(runingNum.getItem()))) {
            runingNum = new RunningNum();
            runingNum.setItem(item);
            runingNum.setCurrentKey(String.valueOf(1));
            runingNum.setReportYear(CommonMethod.getCurrentYear());
            runningNumDao.save(runingNum);
            return Long.valueOf(1L);
        }
        String oldYear = runingNum.getReportYear();
        if (!oldYear.equals(CommonMethod.getCurrentYear())) {
            runingNum.setCurrentKey("0");
            returnstr = Long.valueOf(Integer.parseInt(runingNum.getCurrentKey()) + count);
            runingNum.setCurrentKey(String.valueOf(returnstr));
            runingNum.setItem(item);
            runingNum.setReportYear(CommonMethod.getCurrentYear());
        } else {
            returnstr = Long.valueOf(Integer.parseInt(runingNum.getCurrentKey()) + count);
            runingNum.setCurrentKey(String.valueOf(returnstr));
            runingNum.setItem(item);
            runingNum.setReportYear(CommonMethod.getCurrentYear());
        }
        runningNumDao.update(runingNum);
        return returnstr;
    }
}