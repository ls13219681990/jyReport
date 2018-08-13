/**
 *
 */
package com.controller.sys;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.MD5;
import com.common.QueryAction;
import com.dao.page.UserRolePage;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.model.SysUser;
import com.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author acer
 */
@Controller
@RequestMapping("loginAction")
public class LoginController extends QueryAction<SysUser> {
    /**
     *
     */
    private final static HttpServletRequest REQUEST = null;

    private static final long serialVersionUID = 1L;

    /* private String strSysUser = "";*/

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("login.action")
    @ResponseBody
    public UserRolePage login(String strSysUser) throws Exception {
        UserRolePage urp = new UserRolePage();
        try {
            SysUser sUser = new SysUser();
            List<SysUser> sysuserlist = new ArrayList<SysUser>();
            if (CommonMethod.isNull(strSysUser)) {
                throw new BusinessException("fail,参数strSysUser不能为空！", "");
            }
            SysUser su = (SysUser) toBean(strSysUser, SysUser.class);
            String userPassword = MD5.getMD5(su.getUserPassword());
            sysuserlist = sysUserService.findByProperty("userCode", su.getUserCode());
            if (sysuserlist.size() == 0) {
                //jsonPrintSuccess("没有该用户！");
                throw new BusinessException("fail:" + "没有该用户！");
            } else if ("00".equals(sysuserlist.get(0).getUserStatus())) {
                //jsonPrintSuccess("账户已被停用！");
                throw new BusinessException("fail:" + "账户已被停用！");
            } else if (!userPassword.equals(sysuserlist.get(0).getUserPassword())) {
                throw new BusinessException("fail:" + "密码错误，请重新输入！");
            }
            sUser = sysuserlist.get(0);
            urp = sysUserService.findUserRole(sUser);
            //jsonPrintOnlyProperty(urp);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return urp;
    }

    @RequestMapping("logOut.action")
    @ResponseBody
    public String logOut(HttpServletRequest request) {
		/*if(ActionContext.getContext()!=null && ActionContext.getContext().getSession()!=null){
			ActionContext.getContext().getSession().clear();
		}*/
        Enumeration<String> em = request.getSession().getAttributeNames();
        while (em.hasMoreElements()) {
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        request.getSession().invalidate();
        return "true";
    }

    /*public String getStrSysUser() {
        return strSysUser;
    }

    public void setStrSysUser(String strSysUser) {
        this.strSysUser = strSysUser;
    }*/
}
