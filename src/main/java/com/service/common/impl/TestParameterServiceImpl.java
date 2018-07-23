package com.service.common.impl;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.action.JsonPager;
import com.common.dao.BaseDao;
import com.common.service.BaseServiceImpl;
import com.dao.common.TestParameterDao;
import com.dao.page.BaseSamplePage;
import com.model.TestParameter;
import com.service.common.BaseSampleService;
import com.service.common.TestParameterService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service("testParameterService")
public class TestParameterServiceImpl extends BaseServiceImpl<TestParameter> implements
        TestParameterService {

    @Autowired
    private TestParameterDao testParameterDao;
    @Autowired
    private BaseSampleService baseSampleService;

    @Override
    @Resource(name = "testParameterDao")
    protected void initBaseDAO(BaseDao<TestParameter> baseDao) {
        setBaseDao(baseDao);
    }

    @Override
    public JsonPager<TestParameter> findJsonPageByCriteria(JsonPager<TestParameter> jp,
                                                           TestParameter t) {
        return null;
    }

    @Override
    public List<TestParameter> findTeParameter(String strBaseSampleId) {
        DetachedCriteria dc = DetachedCriteria.forClass(TestParameter.class);
        if (!CommonMethod.isNull(strBaseSampleId)) {
            dc.add(Property.forName("sampleId").eq(strBaseSampleId));
        }
        return testParameterDao.findByCriteria(dc);
    }

    //根据科室查询所有样品下的所有参数
    public List<TestParameter> findAllParameter(String strDepartmentId) {
        List<BaseSamplePage> sampleList = baseSampleService.findSample(strDepartmentId);
        List<String> sampIdList = new ArrayList<String>();
        for (BaseSamplePage sampId : sampleList) {
            sampIdList.add(sampId.getSampleId());
        }
        DetachedCriteria dc = DetachedCriteria.forClass(TestParameter.class);
        dc.add(Property.forName("sampleId").in(sampIdList));
        return testParameterDao.findByCriteria(dc);
    }

    @Override
    public void saveTeParameter(Collection<TestParameter> coll, String userId) {
        for (TestParameter testParameter : coll) {
            if (CommonMethod.isNull(testParameter.getSampleId())) {
                throw new BusinessException("样品ID不能为空！", "");
            }
            DetachedCriteria dc = DetachedCriteria.forClass(TestParameter.class);
            dc.add(Property.forName("sampleId").eq(testParameter.getSampleId()));
            dc.add(Property.forName("testParameterName").eq(testParameter.getTestParameterName()));
            List<TestParameter> tpList = testParameterDao.findByCriteria(dc);
            if (tpList != null && tpList.size() > 0) {
                throw new BusinessException("该检测参数名称已经存在！", "");
            }
            testParameter.setTestParameterId(CommonMethod.getNewKey());
            testParameter.setInputeTime(CommonMethod.getDate());
            testParameter.setInputer(userId);
            testParameterDao.save(testParameter);
        }
    }

    @Override
    public void updateTeParameter(Collection<TestParameter> coll, String userId) {
        for (TestParameter testParameter : coll) {
            TestParameter teParameter = testParameterDao.findById(testParameter.getTestParameterId());
            if (teParameter == null) {
                throw new BusinessException("需要更新的检测参数不存在，请确认！", "");
            }
            if (CommonMethod.isNull(testParameter.getSampleId())) {
                throw new BusinessException("样品ID不能为空！", "");
            }
            DetachedCriteria dc = DetachedCriteria.forClass(TestParameter.class);
            dc.add(Property.forName("testParameterId").ne(testParameter.getTestParameterId()));
            dc.add(Property.forName("sampleId").eq(testParameter.getSampleId()));
            dc.add(Property.forName("testParameterName").eq(testParameter.getTestParameterName()));
            List<TestParameter> tpList = testParameterDao.findByCriteria(dc);
            if (tpList != null && tpList.size() > 0) {
                throw new BusinessException("该检测参数名称已经存在！", "");
            }
            teParameter.setTestParameterName(testParameter.getTestParameterName());
            teParameter.setSampleId(testParameter.getSampleId());
            teParameter.setUnit(testParameter.getUnit());
            teParameter.setUnitPrice(testParameter.getUnitPrice());
            teParameter.setParameterType(testParameter.getParameterType());
            teParameter.setSnRule(testParameter.getSnRule());
            teParameter.setParameterStatus(testParameter.getParameterStatus());
            teParameter.setRemark(testParameter.getRemark());
            teParameter.setUpdater(userId);
            teParameter.setUpdateTime(CommonMethod.getDate());
            testParameterDao.update(teParameter);
        }
    }

    @Override
    public void updateReportSn(String strBaseSampleId, String strReportSn, String userId) {
        List<TestParameter> tpList = testParameterDao.findByProperty("sampleId", strBaseSampleId);
        if (tpList != null && tpList.size() > 0) {
            for (TestParameter teParameter : tpList) {
                teParameter.setSnRule(strReportSn);
                teParameter.setUpdater(userId);
                teParameter.setUpdateTime(CommonMethod.getDate());
                testParameterDao.update(teParameter);
            }
        }
    }
}