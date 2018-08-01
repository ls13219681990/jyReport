package com.controller.common;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.dao.page.SampleReportPage;
import com.dao.page.TestReportInfoPage;
import com.model.BaseDepartment;
import com.model.EntrustInfo;
import com.service.common.BaseDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门
 *
 * @author STKJ-12
 */

@Controller
@RequestMapping("baseDepartmentAction")
public class BaseDepartmentController extends QueryAction<BaseDepartment> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*private String strBaseDepartment = "";
    //是否为经营部门参数
    private String strIsManagement = "";*/

    @Autowired
    private BaseDepartmentService baseDepartmentService;

	/*@Action(value = "/baseDepartmentAction", results = {
			@Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/

    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    @RequestMapping("test")
    @ResponseBody
    public TestReportInfoPage sasveDepartment(String strBaseDepartment) {

        if (CommonMethod.isNull(strBaseDepartment)) {
            throw new BusinessException("fail,参数strBaseDepartment不能为空");
        }
        System.out.println("BaseDepartmentController.sasveDepartment");

        EntrustInfo s = new EntrustInfo();
        List<EntrustInfo> ed = new ArrayList<EntrustInfo>();
        ed.add(s);
        TestReportInfoPage a = new TestReportInfoPage();
        List<SampleReportPage> sampleReportList = a.getSampleReportList();
        SampleReportPage d = new SampleReportPage();
        sampleReportList.add(d);
        a.setSampleReportList(sampleReportList);
        return a;
    }


    @RequestMapping("saveDepartment.action")
    @ResponseBody
    public String saveDepartment(String strBaseDepartment, String userId) {
        try {
            if (CommonMethod.isNull(strBaseDepartment)) {
                throw new BusinessException("fail,参数strBaseDepartment不能为空");

            }
            baseDepartmentService.saveDepartment(getColl(strBaseDepartment), userId);

        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("updateDepartment.action")
    @ResponseBody
    public String updateDepartment(String strBaseDepartment, String userId) {
        try {
            if (CommonMethod.isNull(strBaseDepartment)) {
                throw new BusinessException("fail,参数strBaseDepartment不能为空");

            }
            baseDepartmentService.updateDepartment(getColl(strBaseDepartment), userId);

        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("findDepartment.action")
    @ResponseBody
    public List<BaseDepartment> findDepartment(String strIsManagement) {
        List<BaseDepartment> departmentList = baseDepartmentService.findDepartment(strIsManagement);
        return departmentList;
    }

   /* public String getStrBaseDepartment() {
        return strBaseDepartment;
    }

    public void setStrBaseDepartment(String strBaseDepartment) {
        this.strBaseDepartment = strBaseDepartment;
    }

    public String getStrIsManagement() {
        return strIsManagement;
    }

    public void setStrIsManagement(String strIsManagement) {
        this.strIsManagement = strIsManagement;
    }*/
}
