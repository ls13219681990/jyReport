package com.controller.common;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.common.jsonProcessor.TimestampMorpher;
import com.dao.page.CapitalAccountLinkmpPage;
import com.dao.page.CapitalAccountPage;
import com.model.CapitalAccount;
import com.service.common.CapitalAccountService;
import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("capitalAccountAction")
public class CapitalAccountController extends QueryAction<CapitalAccount> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*private String strCapitalAccount = "";

    private String capitalAccountId;*/
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
    @RequestMapping("saveCapitalAccount.action")
    @ResponseBody
    public String saveCapitalAccount(String strCapitalAccount, String userId) {
        String capitalAccountId = null;
        try {
            if (CommonMethod.isNull(strCapitalAccount)) {
                throw new BusinessException("fail,参数strCapitalAccount不能为空");
            }
            strCapitalAccount = strCapitalAccount.replace("OO", "#");
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
            Collection<CapitalAccountPage> collPage = JSONArray.toCollection(JSONArray.fromObject(strCapitalAccount), CapitalAccountPage.class);
            capitalAccountId = capitalAccountService.saveCapitalAccount(collPage, userId);

        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            //logger.debug(e.getMessage());
            e.getMessage();
        }
        return "true" + capitalAccountId;
    }

    /**
     * 根据资金账号ID增加联系人和电话
     */
    @RequestMapping("saveCapitalAccountLinkmp.action")
    @ResponseBody
    public String saveCapitalAccountLinkmp(String strCapitalAccount) {
        try {
            if (CommonMethod.isNull(strCapitalAccount)) {
                throw new BusinessException("fail,参数strCapitalAccount不能为空");
            }
            CapitalAccountLinkmpPage calPage = new CapitalAccountLinkmpPage();
            calPage = (CapitalAccountLinkmpPage) toBean(strCapitalAccount, CapitalAccountLinkmpPage.class);
            capitalAccountService.saveCapitalAccountLinkmp(calPage);

        } catch (BusinessException e) {
            e.printStackTrace();
            //logger.debug(e.getMessage());
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            //logger.debug(e.getMessage());
            e.getMessage();
        }
        return "true";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("updateCapitalAccount.action")
    @ResponseBody
    public String updateCapitalAccount(String strCapitalAccount, String userId) {
        try {
            if (CommonMethod.isNull(strCapitalAccount)) {
                throw new BusinessException("fail,参数strCapitalAccount不能为空");
            }
            strCapitalAccount = strCapitalAccount.replace("OO", "#");
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
            //Collection<CapitalAccountPage> collPage = JSONArray.toCollection(JSONArray.fromObject(strCapitalAccount),CapitalAccountPage.class);
            CapitalAccountPage collPage = (CapitalAccountPage) toBean(strCapitalAccount, CapitalAccountPage.class);
            capitalAccountService.updateCapitalAccount(collPage, userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            //logger.debug(e.getMessage());
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            //logger.debug(e.getMessage());
            e.getMessage();
        }
        return "true";
    }


    /***
     * 导入资金账号表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("saveCapitalAccountTable.action")
    @ResponseBody
    public String saveCapitalAccountTable(String strCapitalAccount, String userId) {
        try {
            if (CommonMethod.isNull(strCapitalAccount)) {
                throw new BusinessException("fail,参数strCapitalAccount不能为空");
            }
            strCapitalAccount = strCapitalAccount.replace("OO", "#");
            String[] formats = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
            JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
            Collection<CapitalAccountPage> collPage = JSONArray.toCollection(JSONArray.fromObject(strCapitalAccount), CapitalAccountPage.class);
            capitalAccountService.saveCapitalAccountTable(collPage, userId);
        } catch (BusinessException e) {
            e.printStackTrace();
            //logger.debug(e.getMessage());
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            //logger.debug(e.getMessage());
            e.getMessage();
        }
        return "true";
    }

    @RequestMapping("findNewCaCode.action")
    @ResponseBody
    public String findNewCaCode() {
        String newCaCode = capitalAccountService.findNewCaCode();
        return newCaCode;
    }

    @RequestMapping("findCapitalAccount.action")
    @ResponseBody
    public List<CapitalAccountPage> findCapitalAccount(String strCapitalAccount) {
        CapitalAccountPage ca = new CapitalAccountPage();
        if (!CommonMethod.isNull(strCapitalAccount)) {
            ca = (CapitalAccountPage) toBean(strCapitalAccount, CapitalAccountPage.class);
        }
        List<CapitalAccountPage> capList = capitalAccountService.findCaList(ca);
        return capList;
    }

    /**
     * 委托时，查找资金账号信息
     */
    @RequestMapping("findEntrustCapitalAccount.action")
    @ResponseBody
    public List<CapitalAccountPage> findEntrustCapitalAccount(String strCapitalAccount) {
        CapitalAccountPage ca = new CapitalAccountPage();
        if (!CommonMethod.isNull(strCapitalAccount)) {
            strCapitalAccount = strCapitalAccount.replace("OO", "#");
            ca = (CapitalAccountPage) toBean(strCapitalAccount, CapitalAccountPage.class);
        }
        List<CapitalAccountPage> capList = capitalAccountService.findEntrustCaList(ca);
        return capList;
    }

   /* public String getStrCapitalAccount() {
        return strCapitalAccount;
    }

    public void setStrCapitalAccount(String strCapitalAccount) {
        this.strCapitalAccount = strCapitalAccount;
    }*/


    /**
     * 委托时
     */
    @RequestMapping("findEntrustLinkInfo.action")
    @ResponseBody
    public List<CapitalAccountLinkmpPage> findEntrustLinkInfo(String capitalAccountId) {
        if (CommonMethod.isNull(capitalAccountId)) {
            throw new BusinessException("fail,参数strCapitalAccount不能为空");
        }
        List<CapitalAccountLinkmpPage> findEntrustCaListInfo = capitalAccountService.findEntrustCaListInfo(capitalAccountId);
        return findEntrustCaListInfo;
    }

   /* public String getCapitalAccountId() {
        return capitalAccountId;
    }

    public void setCapitalAccountId(String capitalAccountId) {
        this.capitalAccountId = capitalAccountId;
    }*/

}
