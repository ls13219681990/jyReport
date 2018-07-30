/**
 *
 */
package com.controller.sys;

import com.common.CommonMethod;
import com.common.MD5;
import com.common.QueryAction;
import com.dao.page.UserRolePage;
import com.model.SysUser;
import com.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author acer
 */
@Controller
@RequestMapping("LoginAction")
public class LoginController extends QueryAction<SysUser> {
    /**
     *
     */
    private final static HttpServletRequest REQUEST = null;

    private static final long serialVersionUID = 1L;

    private String strSysUser = "";

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("login.action")
    public void login() throws Exception {
        try {
            SysUser sUser = new SysUser();
            List<SysUser> sysuserlist = new ArrayList<SysUser>();
            if (CommonMethod.isNull(strSysUser)) {
                jsonPrint("fail,参数strSysUser不能为空");
                return;
            }
            SysUser su = (SysUser) toBean(strSysUser, SysUser.class);
            String userPassword = MD5.getMD5(su.getUserPassword());
            sysuserlist = sysUserService.findByProperty("userCode", su.getUserCode());
            if (sysuserlist.size() == 0) {
                //jsonPrintSuccess("没有该用户！");
                jsonPrint("fail:" + "没有该用户！");
                return;
            } else if ("00".equals(sysuserlist.get(0).getUserStatus())) {
                //jsonPrintSuccess("账户已被停用！");
                jsonPrint("fail:" + "账户已被停用！");
                return;
            } else if (!userPassword.equals(sysuserlist.get(0).getUserPassword())) {
                jsonPrint("fail:" + "密码错误，请重新输入！");
                return;
            }
            sUser = sysuserlist.get(0);
            UserRolePage urp = sysUserService.findUserRole(sUser);
            //jsonPrintOnlyProperty(urp);
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        }
    }

    @RequestMapping("logOut.action")
    public void logOut(HttpServletRequest request) {
		/*if(ActionContext.getContext()!=null && ActionContext.getContext().getSession()!=null){
			ActionContext.getContext().getSession().clear();
		}*/
        Enumeration<String> em = request.getSession().getAttributeNames();
        while (em.hasMoreElements()) {
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        request.getSession().invalidate();
        jsonPrint("true");
    }

    public String getStrSysUser() {
        return strSysUser;
    }

    public void setStrSysUser(String strSysUser) {
        this.strSysUser = strSysUser;
    }
}
