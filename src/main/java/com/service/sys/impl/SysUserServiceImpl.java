package com.service.sys.impl;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.MD5;
import com.controller.common.JsonPager;
import com.dao.common.BaseDao;
import com.service.common.impl.BaseServiceImpl;
import com.dao.page.UserRolePage;
import com.dao.sys.SysRoleDao;
import com.dao.sys.SysUserDao;
import com.dao.sys.SysUserRoleDao;
import com.model.SysRole;
import com.model.SysUser;
import com.model.SysUserRole;
import com.service.sys.SysUserService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements
        SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;
	
	/*@Override
	@Resource(name = "sysUserDao")
	protected void initBaseDAO(BaseDao<SysUser> baseDao) {
		setBaseDao(baseDao);
	}

	@Override
	public JsonPager<SysUser> findJsonPageByCriteria(JsonPager<SysUser> jp,
			SysUser t) {
		return null;
	}*/

    @Override
    public List<UserRolePage> findSysUser() {
        List<UserRolePage> uRList = new ArrayList<UserRolePage>();
        //用户信息
        DetachedCriteria dc = DetachedCriteria.forClass(SysUser.class);
        List<SysUser> sUserList = sysUserDao.findByCriteria(dc);
        for (SysUser sysUser : sUserList) {
            UserRolePage userRolePage = new UserRolePage();
            DetachedCriteria dcUR = DetachedCriteria.forClass(SysUserRole.class);
            dcUR.add(Property.forName("sysUserId").eq(sysUser.getSysUserId()));
            List<SysUserRole> sURList = sysUserRoleDao.findByCriteria(dcUR);
            List<SysRole> sRoleList = new ArrayList<SysRole>();
            if (sURList != null && sURList.size() > 0) {
                //根据用户查找角色
                for (SysUserRole sUserRole : sURList) {
                    SysRole sRole = new SysRole();

                    SysRole sysRole = sysRoleDao.findById(sUserRole.getRoleId());
                    //角色ID
                    sRole.setRoleId(sysRole.getRoleId());
                    //角色CODE
                    sRole.setRoleCode(sysRole.getRoleCode());
                    //角色名称
                    sRole.setRoleName(sysRole.getRoleName());
                    sRoleList.add(sRole);
                }
            }
            //角色List
            userRolePage.setSRoles(sRoleList);
            //用户ID

            userRolePage.setSysUserId(sysUser.getSysUserId());
            //部门ID
            userRolePage.setDepartmentId(sysUser.getDepartmentId());
            //用户CODE
            userRolePage.setUserCode(sysUser.getUserCode());
            //用户姓名
            userRolePage.setUserName(sysUser.getUserName());
            //用户密码
            userRolePage.setUserPassword(sysUser.getUserPassword());
            //邮箱
            userRolePage.setMailbox(sysUser.getMailbox());
            //电话
            userRolePage.setPhoneNumber(sysUser.getPhoneNumber());
            //用户类型
            userRolePage.setUserType(sysUser.getUserType());
            //是否审核
            userRolePage.setIsAudit(sysUser.getIsAudit());
            //用户状态
            userRolePage.setUserStatus(sysUser.getUserStatus());
            //录入人
            userRolePage.setInputer(sysUser.getInputer());
            //录入时间
            userRolePage.setInputeTime(sysUser.getInputeTime());
            //更新人
            userRolePage.setUpdater(sysUser.getUpdater());
            //更新时间
            userRolePage.setUpdateTime(sysUser.getUpdateTime());

            uRList.add(userRolePage);
        }
        return uRList;
    }


    @Override
    public UserRolePage findUserRole(SysUser sysUser) {
        UserRolePage userRolePage = new UserRolePage();
        DetachedCriteria dcUR = DetachedCriteria.forClass(SysUserRole.class);
        dcUR.add(Property.forName("sysUserId").eq(sysUser.getSysUserId()));
        List<SysUserRole> sURList = sysUserRoleDao.findByCriteria(dcUR);
        List<SysRole> sRoleList = new ArrayList<SysRole>();
        if (sURList != null && sURList.size() > 0) {
            //根据用户查找角色
            for (SysUserRole sUserRole : sURList) {
                SysRole sRole = new SysRole();

                SysRole sysRole = sysRoleDao.findById(sUserRole.getRoleId());
                if ("01".equals(sysRole.getRoleStatus())) {
                    //角色ID
                    sRole.setRoleId(sysRole.getRoleId());
                    //角色CODE
                    sRole.setRoleCode(sysRole.getRoleCode());
                    //角色名称
                    sRole.setRoleName(sysRole.getRoleName());
                    sRoleList.add(sRole);
                }
            }
        }
        //角色List
        userRolePage.setSRoles(sRoleList);
        //用户ID
        userRolePage.setSysUserId(sysUser.getSysUserId());
        //部门ID
        userRolePage.setDepartmentId(sysUser.getDepartmentId());
        //用户CODE
        userRolePage.setUserCode(sysUser.getUserCode());
        //用户姓名
        userRolePage.setUserName(sysUser.getUserName());
        //用户密码
        userRolePage.setUserPassword(sysUser.getUserPassword());
        //邮箱
        userRolePage.setMailbox(sysUser.getMailbox());
        //电话
        userRolePage.setPhoneNumber(sysUser.getPhoneNumber());
        //用户类型
        userRolePage.setUserType(sysUser.getUserType());
        //是否审核
        userRolePage.setIsAudit(sysUser.getIsAudit());
        //用户状态
        userRolePage.setUserStatus(sysUser.getUserStatus());
        //录入人
        userRolePage.setInputer(sysUser.getInputer());
        //录入时间
        userRolePage.setInputeTime(sysUser.getInputeTime());
        //更新人
        userRolePage.setUpdater(sysUser.getUpdater());
        //更新时间
        userRolePage.setUpdateTime(sysUser.getUpdateTime());

        return userRolePage;
    }

    @Override
    public void saveSysUser(Collection<SysUser> coll, String userId) {
        for (SysUser sysUser : coll) {
            sysUser.setSysUserId(CommonMethod.getNewKey());
            sysUser.setInputeTime(CommonMethod.getDate());
            sysUser.setUserPassword(MD5.getMD5("000000"));
            sysUser.setInputer(userId);
            sysUserDao.save(sysUser);
        }
    }

    @Override
    public void updateSysUser(Collection<SysUser> coll, String userId) {
        for (SysUser sysUser : coll) {
            sysUser.setUpdater(userId);
//			sysUser.setUserPassword(MD5.getMD5(sysUser.getUserPassword()));
            sysUser.setUpdateTime(CommonMethod.getDate());
            sysUserDao.update(sysUser);
        }
    }

    @Override
    public void updateSysPassword(String oldPassword, String newPassword, String userId) {
        SysUser sysUser = sysUserDao.findById(userId);
        if (!CommonMethod.isNull(newPassword)) {
            if (!sysUser.getUserPassword().equals(MD5.getMD5(oldPassword))) {
                throw new BusinessException("旧密码与原密码不符，不能修改密码！", "");
            }
        } else {
            newPassword = "000000";
        }

        sysUser.setUpdater(userId);
        sysUser.setUserPassword(MD5.getMD5(newPassword));
        sysUser.setUpdateTime(CommonMethod.getDate());
        sysUserDao.update(sysUser);
    }

    @Override
    public void resetSysPassword(String userId) {
        SysUser sysUser = sysUserDao.findById(userId);
        sysUser.setUpdater(userId);
        sysUser.setUserPassword(MD5.getMD5("000000"));
        sysUser.setUpdateTime(CommonMethod.getDate());
        sysUserDao.update(sysUser);
    }

    @Override
    public List<UserRolePage> findSysUserValid() {
        List<UserRolePage> uRList = new ArrayList<UserRolePage>();
        DetachedCriteria dc = DetachedCriteria.forClass(SysUser.class);
        dc.add(Property.forName("userStatus").eq("01"));
        List<SysUser> sUserList = sysUserDao.findByCriteria(dc);
        for (SysUser sysUser : sUserList) {
            UserRolePage userRolePage = new UserRolePage();
            DetachedCriteria dcUR = DetachedCriteria.forClass(SysUserRole.class);
            dcUR.add(Property.forName("sysUserId").eq(sysUser.getSysUserId()));
            List<SysUserRole> sURList = sysUserRoleDao.findByCriteria(dcUR);
            List<SysRole> sRoleList = new ArrayList<SysRole>();
            if (sURList != null && sURList.size() > 0) {
                //根据用户查找角色
                for (SysUserRole sUserRole : sURList) {
                    SysRole sRole = new SysRole();

                    SysRole sysRole = sysRoleDao.findById(sUserRole.getRoleId());
                    //角色ID
                    sRole.setRoleId(sysRole.getRoleId());
                    //角色CODE
                    sRole.setRoleCode(sysRole.getRoleCode());
                    //角色名称
                    sRole.setRoleName(sysRole.getRoleName());
                    sRoleList.add(sRole);
                }
            }
            //角色List
            userRolePage.setSRoles(sRoleList);
            //用户ID
            userRolePage.setSysUserId(sysUser.getSysUserId());
            //部门ID
            userRolePage.setDepartmentId(sysUser.getDepartmentId());
            //用户CODE
            userRolePage.setUserCode(sysUser.getUserCode());
            //用户姓名
            userRolePage.setUserName(sysUser.getUserName());
            //用户密码
            userRolePage.setUserPassword(sysUser.getUserPassword());
            //邮箱
            userRolePage.setMailbox(sysUser.getMailbox());
            //电话
            userRolePage.setPhoneNumber(sysUser.getPhoneNumber());
            //用户类型
            userRolePage.setUserType(sysUser.getUserType());
            //是否审核
            userRolePage.setIsAudit(sysUser.getIsAudit());
            //用户状态
            userRolePage.setUserStatus(sysUser.getUserStatus());
            //录入人
            userRolePage.setInputer(sysUser.getInputer());
            //录入时间
            userRolePage.setInputeTime(sysUser.getInputeTime());
            //更新人
            userRolePage.setUpdater(sysUser.getUpdater());
            //更新时间
            userRolePage.setUpdateTime(sysUser.getUpdateTime());

            uRList.add(userRolePage);
        }
        return uRList;
    }

    @Override
    protected void initBaseDAO(BaseDao<SysUser> baseDao) {

    }

    @Override
    public JsonPager<SysUser> findJsonPageByCriteria(JsonPager<SysUser> jp, SysUser sysUser) {
        return null;
    }
}