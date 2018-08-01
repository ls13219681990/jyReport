package com.controller.finance;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.TimestampMorpher;
import com.dao.page.AmReceivablePage;
import com.model.AmountReceivable;
import com.service.finance.AmountReceivableService;
import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("amountReceivableAction")
public class AmountReceivableController extends QueryAction<AmountReceivable> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*private String strAmountReceivable = "";*/

    @Autowired
    private AmountReceivableService aReceivableService;

    /*@Action(value = "/amountReceivableAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    @RequestMapping(value = "/hhbaseDepartmentAction")
    public String jumpSys() {
        return "/jsp/sys/sysCode";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("saveAmReceivable.action")
    @ResponseBody
    public String saveAmReceivable(String strAmountReceivable, String userId) {
        try {
            if (CommonMethod.isNull(strAmountReceivable)) {
                throw new BusinessException("fail,参数strAmountReceivable不能为空！", "");
            }
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
            Collection<AmReceivablePage> collPage = JSONArray.toCollection(JSONArray.fromObject(strAmountReceivable), AmReceivablePage.class);
            aReceivableService.saveAmReceivable(collPage, userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("updateAmReceivable.action")
    @ResponseBody
    public String updateAmReceivable(String strAmountReceivable, String userId) {
        try {
            if (CommonMethod.isNull(strAmountReceivable)) {
                throw new BusinessException("fail,参数strAmountReceivable不能为空！", "");
            }
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
            Collection<AmReceivablePage> collPage = JSONArray.toCollection(JSONArray.fromObject(strAmountReceivable), AmReceivablePage.class);
            aReceivableService.updateAmReceivable(collPage, userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("putInAmReceivable.action")
    @ResponseBody
    public String putInAmReceivable(String strAmountReceivable, String userId) {
        try {
            if (CommonMethod.isNull(strAmountReceivable)) {
                throw new BusinessException("fail,参数strAmountReceivable不能为空！", "");
            }
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
            Collection<AmReceivablePage> collPage = JSONArray.toCollection(JSONArray.fromObject(strAmountReceivable), AmReceivablePage.class);
            aReceivableService.saveAmReceivableTable(collPage, userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "true";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("findAmReceivable.action")
    @ResponseBody
    public List<AmReceivablePage> findAmReceivable(String strAmountReceivable) {
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
        return amList;
    }

    /*public String getStrAmountReceivable() {
        return strAmountReceivable;
    }

    public void setStrAmountReceivable(String strAmountReceivable) {
        this.strAmountReceivable = strAmountReceivable;
    }*/
}
