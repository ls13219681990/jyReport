package com.controller.common;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.CommonJsonConfig;
import com.common.jsonProcessor.TimestampMorpher;
import com.dao.page.CapitalAccountLinkmpPage;
import com.dao.page.CapitalAccountPage;
import com.model.CapitalAccount;
import com.service.common.CapitalAccountService;
import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.List;

@Controller
public class CapitalAccountAction extends QueryAction<CapitalAccount> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String strCapitalAccount = "";

    private String capitalAccountId;
    @Autowired
    private CapitalAccountService capitalAccountService;

    //private static Logger logger = Logger.getLogger("jyReportlogger");

    /*@Action(value = "/capitalAccountAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })
    */
    public String jompSys() {
        return "/jsp/sys/sysCode";
    }


    @SuppressWarnings("unchecked")
    public void saveCapitalAccount() {
        try {
            if (CommonMethod.isNull(strCapitalAccount)) {
                jsonPrint("fail,参数strCapitalAccount不能为空");
                return;
            }
            strCapitalAccount = strCapitalAccount.replace("OO", "#");
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
            Collection<CapitalAccountPage> collPage = JSONArray.toCollection(JSONArray.fromObject(strCapitalAccount), CapitalAccountPage.class);
            String capitalAccountId = capitalAccountService.saveCapitalAccount(collPage, getUserId());
            jsonPrint("true:" + capitalAccountId);
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            //logger.debug(e.getMessage());
            jsonPrint("error:" + e.getMessage());
        }
    }

    /**
     * 根据资金账号ID增加联系人和电话
     */
    public void saveCapitalAccountLinkmp() {
        try {
            if (CommonMethod.isNull(strCapitalAccount)) {
                jsonPrint("fail,参数strCapitalAccount不能为空");
                return;
            }
            CapitalAccountLinkmpPage calPage = new CapitalAccountLinkmpPage();
            calPage = (CapitalAccountLinkmpPage) toBean(strCapitalAccount, CapitalAccountLinkmpPage.class);
            capitalAccountService.saveCapitalAccountLinkmp(calPage);
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            //logger.debug(e.getMessage());
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            //logger.debug(e.getMessage());
            jsonPrint("error:" + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void updateCapitalAccount() {
        try {
            if (CommonMethod.isNull(strCapitalAccount)) {
                jsonPrint("fail,参数strCapitalAccount不能为空");
                return;
            }
            strCapitalAccount = strCapitalAccount.replace("OO", "#");
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
            //Collection<CapitalAccountPage> collPage = JSONArray.toCollection(JSONArray.fromObject(strCapitalAccount),CapitalAccountPage.class);
            CapitalAccountPage collPage = (CapitalAccountPage) toBean(strCapitalAccount, CapitalAccountPage.class);
            capitalAccountService.updateCapitalAccount(collPage, getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            //logger.debug(e.getMessage());
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            //logger.debug(e.getMessage());
            jsonPrint("error:" + e.getMessage());
        }
    }


    /***
     * 导入资金账号表
     */
    @SuppressWarnings("unchecked")
    public void saveCapitalAccountTable() {
        try {
            if (CommonMethod.isNull(strCapitalAccount)) {
                jsonPrint("fail,参数strCapitalAccount不能为空");
                return;
            }
            strCapitalAccount = strCapitalAccount.replace("OO", "#");
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
            Collection<CapitalAccountPage> collPage = JSONArray.toCollection(JSONArray.fromObject(strCapitalAccount), CapitalAccountPage.class);
            capitalAccountService.saveCapitalAccountTable(collPage, getUserId());
            jsonPrint("true");
        } catch (BusinessException e) {
            e.printStackTrace();
            //logger.debug(e.getMessage());
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            //logger.debug(e.getMessage());
            jsonPrint("error:" + e.getMessage());
        }
    }


    public void findNewCaCode() {
        String newCaCode = capitalAccountService.findNewCaCode();
        jsonPrint(newCaCode);
    }

    public void findCapitalAccount() {
        CapitalAccountPage ca = new CapitalAccountPage();
        if (!CommonMethod.isNull(strCapitalAccount)) {
            ca = (CapitalAccountPage) toBean(strCapitalAccount, CapitalAccountPage.class);
        }
        List<CapitalAccountPage> capList = capitalAccountService.findCaList(ca);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(capList, jsonConfig);
        jsonPrint(jsonArr);
    }

    /**
     * 委托时，查找资金账号信息
     */
    public void findEntrustCapitalAccount() {
        CapitalAccountPage ca = new CapitalAccountPage();
        if (!CommonMethod.isNull(strCapitalAccount)) {
            strCapitalAccount = strCapitalAccount.replace("OO", "#");
            ca = (CapitalAccountPage) toBean(strCapitalAccount, CapitalAccountPage.class);
        }
        List<CapitalAccountPage> capList = capitalAccountService.findEntrustCaList(ca);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(capList, jsonConfig);
        jsonPrint(jsonArr);
    }

    public String getStrCapitalAccount() {
        return strCapitalAccount;
    }

    public void setStrCapitalAccount(String strCapitalAccount) {
        this.strCapitalAccount = strCapitalAccount;
    }


    /**
     * 委托时
     */
    public void findEntrustLinkInfo() {
        if (CommonMethod.isNull(capitalAccountId)) {
            jsonPrint("fail,参数capitalAccountId不能为空");
            return;
        }
        List<CapitalAccountLinkmpPage> findEntrustCaListInfo = capitalAccountService.findEntrustCaListInfo(capitalAccountId);
        CommonJsonConfig jsonConfig = new CommonJsonConfig();
        JSONArray jsonArr = JSONArray.fromObject(findEntrustCaListInfo, jsonConfig);
        jsonPrint(jsonArr);
    }

    public String getCapitalAccountId() {
        return capitalAccountId;
    }

    public void setCapitalAccountId(String capitalAccountId) {
        this.capitalAccountId = capitalAccountId;
    }

}
