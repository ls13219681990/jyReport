package com.service.common.impl;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.action.JsonPager;
import com.common.dao.BaseDao;
import com.common.service.BaseServiceImpl;
import com.dao.common.BaseDepartmentDao;
import com.dao.common.BaseSampleDao;
import com.dao.common.CapitalAccountDetailDao;
import com.dao.common.TestParameterDao;
import com.dao.page.CapitalAccountDetailPage;
import com.model.BaseDepartment;
import com.model.BaseSample;
import com.model.CapitalAccountDetail;
import com.model.TestParameter;
import com.service.common.CapitalAccountDetailService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service("capitalAccountDetailService")
public class CapitalAccountDetailServiceImpl extends
        BaseServiceImpl<CapitalAccountDetail> implements
        CapitalAccountDetailService {

    @Autowired
    private CapitalAccountDetailDao capitalAccountDetailDao;

    @Autowired
    private BaseSampleDao baseSampleDao;

    @Autowired
    private BaseDepartmentDao baseDepartmentDao;

    @Autowired
    private TestParameterDao testParameterDao;

    @Override
    @Resource(name = "capitalAccountDetailDao")
    protected void initBaseDAO(BaseDao<CapitalAccountDetail> baseDao) {
        setBaseDao(baseDao);
    }

    @Override
    public JsonPager<CapitalAccountDetail> findJsonPageByCriteria(
            JsonPager<CapitalAccountDetail> jp, CapitalAccountDetail t) {
        return null;
    }

    @Override
    public void saveCADetailTable(Collection<CapitalAccountDetailPage> coll,
                                  String userId) {
        String contractId = "";
        for (CapitalAccountDetailPage cap : coll) {
            contractId = cap.getContractId();
        }
        if (CommonMethod.isNull(contractId)) {
            throw new BusinessException("合同ID不能为空！", "");
        }
        capitalAccountDetailDao.deleteByCAId(contractId);
        for (CapitalAccountDetailPage cap : coll) {
            // //样品ID
            // String sampleId = "";
            // List<BaseSample> bsList=
            // baseSampleDao.findByProperty("sampleName",
            // cap.getTestProjectName());
            // if(bsList!=null && bsList.size()>0){
            // sampleId = bsList.get(0).getSampleId();
            // }else{
            // continue;
            // }
            //
            // //检测参数ID
            // String tParameterId = "";
            // List<TestParameter> tpList=
            // testParameterDao.findByProperty("testParameterName",
            // cap.getTestTypeName());
            // if(tpList!=null && tpList.size()>0){
            // tParameterId = tpList.get(0).getTestParameterId();
            // }else{
            // continue;
            // }
            if (CommonMethod.isNull(cap.getTestProject())) {
                throw new BusinessException("检测类别不能为空！", "");
            }
            /*
             * if(CommonMethod.isNull(cap.getTestType())){ throw new
             * BusinessException("检测项目不能为空！",""); }
             */
            // 资金账号明细
            CapitalAccountDetail caDetail = new CapitalAccountDetail();
            caDetail.setCapitalAccountDetailId(CommonMethod.getNewKey());
            caDetail.setContractId(cap.getContractId());
            caDetail.setTestProject(cap.getTestProject());
            caDetail.setTestType(cap.getTestType());
            caDetail.setComputingUnit(cap.getComputingUnit());
            caDetail.setPrice(Double.parseDouble(cap.getPrice()));
            caDetail.setRealPrice(cap.getRealPrice());
            caDetail.setRemark(cap.getRemark());
            caDetail.setInputer(userId);
            caDetail.setInputeTime(CommonMethod.getDate());
            capitalAccountDetailDao.save(caDetail);
        }
    }

    @Override
    public List<CapitalAccountDetail> findCADetails(String contractId,
                                                    String sampleId, String testParameterId) {
        DetachedCriteria dc = DetachedCriteria
                .forClass(CapitalAccountDetail.class);
        dc.add(Property.forName("contractId").eq(contractId));
        dc.add(Property.forName("testProject").eq(sampleId));
        dc.add(Property.forName("testType").eq(testParameterId));
        List<CapitalAccountDetail> caDetailList = capitalAccountDetailDao
                .findByCriteria(dc);
        return caDetailList;
    }

    @Override
    public List<CapitalAccountDetailPage> findCADetailList(
            String capitalAccountId) {
        List<CapitalAccountDetailPage> caDetailPageList = new ArrayList<CapitalAccountDetailPage>();
        List<CapitalAccountDetail> caDetailList = capitalAccountDetailDao
                .findByProperty("contractId", capitalAccountId);
        for (CapitalAccountDetail cad : caDetailList) {
            // 样品信息
            BaseSample bs = baseSampleDao.findById(cad.getTestProject());
            List<BaseDepartment> department = baseDepartmentDao.findByProperty(
                    "departmentId", bs.getDepartmentId());
            CapitalAccountDetailPage cadPage = new CapitalAccountDetailPage();
            // 参数信息
            if (cad.getTestType() != null && !cad.getTestType().equals("")) {
                TestParameter tp = testParameterDao.findById(cad.getTestType());
                cadPage.setTestTypeName(tp.getTestParameterName());
            }
            cadPage.setCapitalAccountDetailId(cad.getCapitalAccountDetailId());
            cadPage.setContractId(cad.getContractId());
            cadPage.setTestProject(cad.getTestProject());
            cadPage.setTestProjectName(bs.getSampleName());
            cadPage.setTestType(cad.getTestType());
            cadPage.setComputingUnit(cad.getComputingUnit());
            cadPage.setPrice(cad.getPrice().toString());
            cadPage.setRealPrice(cad.getRealPrice());
            cadPage.setRemark(cad.getRemark());
            cadPage.setDepartment(department.get(0).getDepartmentName());
            caDetailPageList.add(cadPage);

        }
        return caDetailPageList;
    }

    @Override
    public List<CapitalAccountDetail> findCASampleDetails(String contractId,
                                                          String sampleId) {
        DetachedCriteria dc = DetachedCriteria
                .forClass(CapitalAccountDetail.class);
        dc.add(Property.forName("contractId").eq(contractId));
        dc.add(Property.forName("testProject").eq(sampleId));
        dc.add(Property.forName("testType").isNull());
        List<CapitalAccountDetail> caDetailList = capitalAccountDetailDao
                .findByCriteria(dc);
        return caDetailList;
    }

    @Override
    public List<CapitalAccountDetailPage> findCASampleDetailsAll(
            String contractId) {

        List<CapitalAccountDetailPage> findCASampleDetailsAll = capitalAccountDetailDao
                .findCASampleDetailsAll();
        List<CapitalAccountDetail> findByProperty2 = capitalAccountDetailDao
                .findByProperty("contractId", contractId); // 拿到合同记录
        for (CapitalAccountDetail capitalAccountDetail2 : findByProperty2) {

            for (CapitalAccountDetailPage capitalAccountDetailPage : findCASampleDetailsAll) {

                // 拿到这条数据和 查出来的数据作比较 如果两边的ID 相等 那么就把修改后的ID
                // 设置给CapitalAccountDetailPage对象 if
                if (capitalAccountDetail2.getTestType().equals(
                        capitalAccountDetailPage.getTestType())) { // 设置执行价格
                    capitalAccountDetailPage
                            .setContractPrice(capitalAccountDetail2
                                    .getPrice().toString());
                    capitalAccountDetailPage.setRemark(capitalAccountDetail2.getRemark());
                }
            }
        }
        return findCASampleDetailsAll;
    }
}
