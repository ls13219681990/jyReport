package com.controller.finance;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.common.jsonProcessor.TimestampMorpher;
import com.dao.page.AmReceivablePage;
import com.model.AmountReceivable;
import com.service.finance.AmountReceivableService;
import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
public class AmountReceivableAction extends QueryAction<AmountReceivable> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String strAmountReceivable = "";

    @Autowired
    private AmountReceivableService aReceivableService;

    /*@Action(value = "/amountReceivableAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    @RequestMapping(value = "/hhbaseDepartmentAction")
    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    @SuppressWarnings("unchecked")
    public void saveAmReceivable() {
        try {
            if (CommonMethod.isNull(strAmountReceivable)) {
                jsonPrint("fail,参数strAmountReceivable不能为空");
                return;
            }
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
            Collection<AmReceivablePage> collPage = JSONArray.toCollection(JSONArray.fromObject(strAmountReceivable), AmReceivablePage.class);
            aReceivableService.saveAmReceivable(collPage, getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void updateAmReceivable() {
        try {
            if (CommonMethod.isNull(strAmountReceivable)) {
                jsonPrint("fail,参数strAmountReceivable不能为空");
                return;
            }
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
            Collection<AmReceivablePage> collPage = JSONArray.toCollection(JSONArray.fromObject(strAmountReceivable), AmReceivablePage.class);
            aReceivableService.updateAmReceivable(collPage, getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void putInAmReceivable() {
        try {
            if (CommonMethod.isNull(strAmountReceivable)) {
                jsonPrint("fail,参数strAmountReceivable不能为空");
                return;
            }
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
            Collection<AmReceivablePage> collPage = JSONArray.toCollection(JSONArray.fromObject(strAmountReceivable), AmReceivablePage.class);
            aReceivableService.saveAmReceivableTable(collPage, getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void findAmReceivable() {
        AmReceivablePage ar = new AmReceivablePage();
        if (!CommonMethod.isNull(strAmountReceivable)) {
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
            Collection<AmReceivablePage> collPage = JSONArray.toCollection(JSONArray.fromObject(strAmountReceivable), AmReceivablePage.class);

            for (AmReceivablePage newAm : collPage) {
                ar = newAm;
            }
        }
        List<AmReceivablePage> amList = aReceivableService.findAmReceivableList(ar);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(amList, jsonConfig);
        jsonPrint(jsonArr);
    }

    public String getStrAmountReceivable() {
        return strAmountReceivable;
    }

    public void setStrAmountReceivable(String strAmountReceivable) {
        this.strAmountReceivable = strAmountReceivable;
    }
}
