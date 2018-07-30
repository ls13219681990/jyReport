package com.controller.common;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.dao.page.SampleReportPage;
import com.dao.page.TestReportInfoPage;
import com.model.BaseDepartment;
import com.model.EntrustInfo;
import com.service.common.BaseDepartmentService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    private String strBaseDepartment = "";
    //是否为经营部门参数
    private String strIsManagement = "";

    @Autowired
    private BaseDepartmentService baseDepartmentService;

	/*@Action(value = "/baseDepartmentAction", results = {
			@Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/

    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    @RequestMapping("test")
    @ResponseBody
    public TestReportInfoPage sasveDepartment() {
        try {
            int a = 1;
            int b = 0 / 0;
            String time = "2011-11-1 17:00:00";
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = f.parse(time);
        } catch (Exception e) {

        }
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
    public void saveDepartment() {
        try {
            if (CommonMethod.isNull(strBaseDepartment)) {
                jsonPrint("fail,参数strBaseDepartment不能为空");
                return;
            }
            baseDepartmentService.saveDepartment(getColl(strBaseDepartment), getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @RequestMapping("updateDepartment.action")
    public void updateDepartment() {
        try {
            if (CommonMethod.isNull(strBaseDepartment)) {
                jsonPrint("fail,参数strBaseDepartment不能为空");
                return;
            }
            baseDepartmentService.updateDepartment(getColl(strBaseDepartment), getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @RequestMapping("findDepartment.action")
    public void findDepartment() {
        List<BaseDepartment> departmentList = baseDepartmentService.findDepartment(strIsManagement);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(departmentList, jsonConfig);
        jsonPrint(jsonArr);
    }

    public String getStrBaseDepartment() {
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
    }
}
