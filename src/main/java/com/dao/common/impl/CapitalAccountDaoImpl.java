package com.dao.common.impl;

import com.common.CommonMethod;
import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.CapitalAccountDao;
import com.dao.page.CapitalAccountPage;
import com.model.CapitalAccount;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("capitalAccountDao")
public class CapitalAccountDaoImpl extends BaseDaoImpl<CapitalAccount> implements CapitalAccountDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<CapitalAccountPage> findCaListSQL(final CapitalAccountPage cap) {
        return (List<CapitalAccountPage>) getHibernateTemplate().execute(new HibernateCallback() {
            @SuppressWarnings("rawtypes")
            @Override
            public List<CapitalAccountPage> doInHibernate(Session session) throws HibernateException,
                    SQLException {

                StringBuffer querySQLCount = new StringBuffer();
                querySQLCount.append(" select count(distinct ca.CAPITAL_ACCOUNT_ID) as total ");
                querySQLCount.append(" from CAPITAL_ACCOUNT ca ");
                querySQLCount.append(" inner join ENTRUST_COMPANY ec on ca.ENTRUST_COMPANY_ID = ec.ENTRUST_COMPANY_ID ");
                querySQLCount.append(" inner join PROJECT_INFO project on ca.PROJECT_ID = project.PROJECT_ID ");
                querySQLCount.append(" left join SUPERVISION_UNIT su  on ca.SUPERVISION_UNIT_ID = su.SUPERVISION_UNIT_ID ");
                querySQLCount.append(" where 1=1 ");
                if (cap != null) {
                    //委托单位名称不为空
                    if (!CommonMethod.isNull(cap.getEntrustCompanyName())) {
                        querySQLCount.append("and ec.ENTRUST_COMPANY_NAME like '%");
                        querySQLCount.append(cap.getEntrustCompanyName());
                        querySQLCount.append("%'");
                    }
                    //工程名称不为空
                    if (!CommonMethod.isNull(cap.getProjectName())) {
                        querySQLCount.append("and project.PROJECT_NAME like '%");
                        querySQLCount.append(cap.getProjectName());
                        querySQLCount.append("%'");
                    }
                    //监理单位名称不为空
                    if (!CommonMethod.isNull(cap.getSupervisionUnitName())) {
                        querySQLCount.append("and su.SUPERVISION_UNIT_NAME like '%");
                        querySQLCount.append(cap.getSupervisionUnitName());
                        querySQLCount.append("%'");
                    }
                    //合同号不为空
                    if (!CommonMethod.isNull(cap.getContractCode())) {
                        querySQLCount.append("and ca.CONTRACT_CODE ='");
                        querySQLCount.append(cap.getContractCode());
                        querySQLCount.append("'");
                    }
                    //资金账号不为空
                    if (!CommonMethod.isNull(cap.getCapitalAccountCode())) {
                        querySQLCount.append("and ca.CAPITAL_ACCOUNT_CODE ='");
                        querySQLCount.append(cap.getCapitalAccountCode());
                        querySQLCount.append("'");
                    }
                    //委托单位ID不为空
                    if (!CommonMethod.isNull(cap.getEntrustCompanyId())) {
                        querySQLCount.append("and ca.ENTRUST_COMPANY_ID ='");
                        querySQLCount.append(cap.getEntrustCompanyId());
                        querySQLCount.append("'");
                    }
                    //工程ID不为空
                    if (!CommonMethod.isNull(cap.getProjectId())) {
                        querySQLCount.append("and ca.PROJECT_ID ='");
                        querySQLCount.append(cap.getProjectId());
                        querySQLCount.append("'");
                    }
                    //工程备份不为空
                    if (!CommonMethod.isNull(cap.getProjectRemark())) {
                        querySQLCount.append("and ca.PROJECT_REMARK ='");
                        querySQLCount.append(cap.getProjectRemark());
                        querySQLCount.append("'");
                    }
                    //资金账号ID
                    if (!CommonMethod.isNull(cap.getCapitalAccountId())) {
                        querySQLCount.append("and ca.CAPITAL_ACCOUNT_ID ='");
                        querySQLCount.append(cap.getCapitalAccountId());
                        querySQLCount.append("'");
                    }
                }

                Query queryCount = session.createSQLQuery(querySQLCount.toString());
                int totalCount = Integer.parseInt(queryCount.list().get(0).toString());


                StringBuffer sql = new StringBuffer();
                sql.append(" select ca.CAPITAL_ACCOUNT_ID,ca.CAPITAL_ACCOUNT_CODE,ca.ACCOUNT_TYPE2,ca.ACCOUNT_KINDS,ca.CONTRACT_CODE,ca.CONTRACT_ID, ");
                sql.append(" ca.REMARK,ca.INPUTER,ca.INPUTE_TIME,ca.UPDATER,ca.UPDATE_TIME,ec.ENTRUST_COMPANY_ID,ec.ENTRUST_COMPANY_NAME, ");
                sql.append(" ec.ENTRUST_COMPANY_NO,project.PROJECT_ID,project.PROJECT_NAME,project.LINK_MAN,project.LINK_PHONE, ");
                sql.append(" su.SUPERVISION_UNIT_ID,su.SUPERVISION_UNIT_NAME,su.WITNESSES,ca.PRINT_NUM,ca.LINK_MAN as ENTRUST_LINK_MAN,ca.LINK_PHONE as ENTRUST_LINK_PHONE,ca.PROJECT_REMARK ");
                sql.append(" from CAPITAL_ACCOUNT ca ");
                sql.append(" inner join ENTRUST_COMPANY ec on ca.ENTRUST_COMPANY_ID = ec.ENTRUST_COMPANY_ID ");
                sql.append(" inner join PROJECT_INFO project on ca.PROJECT_ID = project.PROJECT_ID ");
                sql.append(" left join SUPERVISION_UNIT su  on ca.SUPERVISION_UNIT_ID = su.SUPERVISION_UNIT_ID ");
                sql.append(" where 1=1 ");
                if (cap != null) {
                    //委托单位名称不为空
                    if (!CommonMethod.isNull(cap.getEntrustCompanyName())) {
                        sql.append("and ec.ENTRUST_COMPANY_NAME like '%");
                        sql.append(cap.getEntrustCompanyName());
                        sql.append("%'");
                    }
                    //工程名称不为空
                    if (!CommonMethod.isNull(cap.getProjectName())) {
                        sql.append("and project.PROJECT_NAME like '%");
                        sql.append(cap.getProjectName());
                        sql.append("%'");
                    }
                    //监理单位名称不为空
                    if (!CommonMethod.isNull(cap.getSupervisionUnitName())) {
                        sql.append("and su.SUPERVISION_UNIT_NAME like '%");
                        sql.append(cap.getSupervisionUnitName());
                        sql.append("%'");
                    }
                    //合同号不为空
                    if (!CommonMethod.isNull(cap.getContractCode())) {
                        sql.append("and ca.CONTRACT_CODE ='");
                        sql.append(cap.getContractCode());
                        sql.append("'");
                    }
                    //资金账号不为空
                    if (!CommonMethod.isNull(cap.getCapitalAccountCode())) {
                        sql.append("and ca.CAPITAL_ACCOUNT_CODE ='");
                        sql.append(cap.getCapitalAccountCode());
                        sql.append("'");
                    }
                    //委托单位ID不为空
                    if (!CommonMethod.isNull(cap.getEntrustCompanyId())) {
                        sql.append("and ca.ENTRUST_COMPANY_ID ='");
                        sql.append(cap.getEntrustCompanyId());
                        sql.append("'");
                    }
                    //工程ID不为空
                    if (!CommonMethod.isNull(cap.getProjectId())) {
                        sql.append("and ca.PROJECT_ID ='");
                        sql.append(cap.getProjectId());
                        sql.append("'");
                    }
                    //工程备份不为空
                    if (!CommonMethod.isNull(cap.getProjectRemark())) {
                        sql.append("and ca.PROJECT_REMARK ='");
                        sql.append(cap.getProjectRemark());
                        sql.append("'");
                    }
                    //资金账号ID
                    if (!CommonMethod.isNull(cap.getCapitalAccountId())) {
                        sql.append("and ca.CAPITAL_ACCOUNT_ID ='");
                        sql.append(cap.getCapitalAccountId());
                        sql.append("'");
                    }
                }
                sql.append(" order by cast(ca.CAPITAL_ACCOUNT_CODE as int) desc ");
                Query query = session.createSQLQuery(sql.toString());

                query.setFirstResult((cap.getPageNo() - 1) * cap.getPageSize());
                query.setMaxResults(cap.getPageSize());

                query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                List l = query.list();

                List<CapitalAccountPage> list = new ArrayList<CapitalAccountPage>();

                for (Object obj : l) {

                    Map map = (Map) obj;
                    CapitalAccountPage newCap = new CapitalAccountPage();
                    newCap.setTotalCount(totalCount);
                    newCap.setCapitalAccountId(map.get("CAPITAL_ACCOUNT_ID") == null ? null : map.get("CAPITAL_ACCOUNT_ID").toString());
                    newCap.setCapitalAccountCode(map.get("CAPITAL_ACCOUNT_CODE") == null ? null : map.get("CAPITAL_ACCOUNT_CODE").toString());
                    newCap.setAccountType2(map.get("ACCOUNT_TYPE2") == null ? null : map.get("ACCOUNT_TYPE2").toString());
                    newCap.setAccountKinds(map.get("ACCOUNT_KINDS") == null ? null : map.get("ACCOUNT_KINDS").toString());
                    newCap.setContractCode(map.get("CONTRACT_CODE") == null ? null : map.get("CONTRACT_CODE").toString());
                    newCap.setRemark(map.get("REMARK") == null ? null : map.get("REMARK").toString());
                    newCap.setInputer(map.get("INPUTER") == null ? null : map.get("INPUTER").toString());
                    newCap.setInputeTime(map.get("INPUTE_TIME") == null ? null : map.get("INPUTE_TIME").toString());
                    newCap.setUpdater(map.get("UPDATER") == null ? null : map.get("UPDATER").toString());
                    newCap.setUpdateTime(map.get("UPDATE_TIME") == null ? null : map.get("UPDATE_TIME").toString());
                    newCap.setEntrustCompanyId(map.get("ENTRUST_COMPANY_ID") == null ? null : map.get("ENTRUST_COMPANY_ID").toString());
                    newCap.setEntrustCompanyName(map.get("ENTRUST_COMPANY_NAME") == null ? null : map.get("ENTRUST_COMPANY_NAME").toString());
                    newCap.setEntrustCompanyNo(map.get("ENTRUST_COMPANY_NO") == null ? null : map.get("ENTRUST_COMPANY_NO").toString());
                    newCap.setProjectId(map.get("PROJECT_ID") == null ? null : map.get("PROJECT_ID").toString());
                    newCap.setProjectName(map.get("PROJECT_NAME") == null ? null : map.get("PROJECT_NAME").toString());
                    newCap.setLinkMan(map.get("LINK_MAN") == null ? null : map.get("LINK_MAN").toString());
                    newCap.setLinkPhone(map.get("LINK_PHONE") == null ? null : map.get("LINK_PHONE").toString());
                    newCap.setSupervisionUnitId(map.get("SUPERVISION_UNIT_ID") == null ? null : map.get("SUPERVISION_UNIT_ID").toString());
                    newCap.setSupervisionUnitName(map.get("SUPERVISION_UNIT_NAME") == null ? null : map.get("SUPERVISION_UNIT_NAME").toString());
                    newCap.setWitness(map.get("WITNESSES") == null ? null : map.get("WITNESSES").toString());
                    newCap.setPrintNum(map.get("PRINT_NUM") == null ? null : map.get("PRINT_NUM").toString());
                    newCap.setEntrustLinkMan(map.get("ENTRUST_LINK_MAN") == null ? null : map.get("ENTRUST_LINK_MAN").toString());
                    newCap.setEntrustLinkPhone(map.get("ENTRUST_LINK_PHONE") == null ? null : map.get("ENTRUST_LINK_PHONE").toString());
                    newCap.setProjectRemark(map.get("PROJECT_REMARK") == null ? null : map.get("PROJECT_REMARK").toString());
                    newCap.setContractId(map.get("CONTRACT_ID") == null ? null : map.get("CONTRACT_ID").toString());

                    list.add(newCap);
                }

                return list;
            }
        });
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<CapitalAccountPage> findEntrustCaListSQL(final CapitalAccountPage cap) {
        return (List<CapitalAccountPage>) getHibernateTemplate().execute(new HibernateCallback() {
            @SuppressWarnings("rawtypes")
            @Override
            public List<CapitalAccountPage> doInHibernate(Session session) throws HibernateException,
                    SQLException {
                StringBuffer sql = new StringBuffer();
                sql.append(" select ca.CAPITAL_ACCOUNT_ID,ca.CAPITAL_ACCOUNT_CODE,ca.ACCOUNT_TYPE2,ca.ACCOUNT_KINDS,ca.CONTRACT_CODE,ca.CONTRACT_ID, ");
                sql.append(" ca.REMARK,ca.INPUTER,ca.INPUTE_TIME,ca.UPDATER,ca.UPDATE_TIME,ec.ENTRUST_COMPANY_ID,ec.ENTRUST_COMPANY_NAME, ");
                sql.append(" ec.ENTRUST_COMPANY_NO,project.PROJECT_ID,project.PROJECT_NAME,project.LINK_MAN,project.LINK_PHONE, ");
                sql.append(" su.SUPERVISION_UNIT_ID,su.SUPERVISION_UNIT_NAME,su.WITNESSES,ca.PRINT_NUM,ca.LINK_MAN as ENTRUST_LINK_MAN,ca.LINK_PHONE as ENTRUST_LINK_PHONE,ca.PROJECT_REMARK ");
                sql.append(" from CAPITAL_ACCOUNT ca ");
                sql.append(" inner join ENTRUST_COMPANY ec on ca.ENTRUST_COMPANY_ID = ec.ENTRUST_COMPANY_ID ");
                sql.append(" inner join PROJECT_INFO project on ca.PROJECT_ID = project.PROJECT_ID ");
                sql.append(" left join SUPERVISION_UNIT su  on ca.SUPERVISION_UNIT_ID = su.SUPERVISION_UNIT_ID ");
                sql.append(" where 1=1 ");
                if (cap != null) {
                    //委托单位名称不为空或者工程名称不为空
                    if (!CommonMethod.isNull(cap.getEntrustCompanyName())) {
                        sql.append(" and ( ec.ENTRUST_COMPANY_NAME like '%");
                        sql.append(cap.getEntrustCompanyName());
                        sql.append("%'");
                        sql.append(" or project.PROJECT_NAME like '%");
                        sql.append(cap.getProjectName());
                        sql.append("%')");
                    }
                    //资金账号不为空
                    if (!CommonMethod.isNull(cap.getCapitalAccountCode())) {
                        sql.append(" and ca.CAPITAL_ACCOUNT_CODE = '");
                        sql.append(cap.getCapitalAccountCode());
                        sql.append("'");
                    }
                    //委托单位ID不为空
                    if (!CommonMethod.isNull(cap.getEntrustCompanyId())) {
                        sql.append("and ca.ENTRUST_COMPANY_ID ='");
                        sql.append(cap.getEntrustCompanyId());
                        sql.append("'");
                    }
                    //工程ID不为空
                    if (!CommonMethod.isNull(cap.getProjectId())) {
                        sql.append("and ca.PROJECT_ID ='");
                        sql.append(cap.getProjectId());
                        sql.append("'");
                    }
                    //工程备份不为空
                    if (!CommonMethod.isNull(cap.getProjectRemark())) {
                        sql.append("and ca.PROJECT_REMARK ='");
                        sql.append(cap.getProjectRemark());
                        sql.append("'");
                    }
                    //资金账号ID
                    if (!CommonMethod.isNull(cap.getCapitalAccountId())) {
                        sql.append("and ca.CAPITAL_ACCOUNT_ID ='");
                        sql.append(cap.getCapitalAccountId());
                        sql.append("'");
                    }
                }
                sql.append(" order by ca.CAPITAL_ACCOUNT_CODE desc ");
                Query query = session.createSQLQuery(sql.toString());


                query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                List l = query.list();

                List<CapitalAccountPage> list = new ArrayList<CapitalAccountPage>();

                for (Object obj : l) {

                    Map map = (Map) obj;
                    CapitalAccountPage newCap = new CapitalAccountPage();

                    newCap.setCapitalAccountId(map.get("CAPITAL_ACCOUNT_ID") == null ? null : map.get("CAPITAL_ACCOUNT_ID").toString());
                    newCap.setCapitalAccountCode(map.get("CAPITAL_ACCOUNT_CODE") == null ? null : map.get("CAPITAL_ACCOUNT_CODE").toString());
                    newCap.setAccountType2(map.get("ACCOUNT_TYPE2") == null ? null : map.get("ACCOUNT_TYPE2").toString());
                    newCap.setAccountKinds(map.get("ACCOUNT_KINDS") == null ? null : map.get("ACCOUNT_KINDS").toString());
                    newCap.setContractCode(map.get("CONTRACT_CODE") == null ? null : map.get("CONTRACT_CODE").toString());
                    newCap.setRemark(map.get("REMARK") == null ? null : map.get("REMARK").toString());
                    newCap.setInputer(map.get("INPUTER") == null ? null : map.get("INPUTER").toString());
                    newCap.setInputeTime(map.get("INPUTE_TIME") == null ? null : map.get("INPUTE_TIME").toString());
                    newCap.setUpdater(map.get("UPDATER") == null ? null : map.get("UPDATER").toString());
                    newCap.setUpdateTime(map.get("UPDATE_TIME") == null ? null : map.get("UPDATE_TIME").toString());
                    newCap.setEntrustCompanyId(map.get("ENTRUST_COMPANY_ID") == null ? null : map.get("ENTRUST_COMPANY_ID").toString());
                    newCap.setEntrustCompanyName(map.get("ENTRUST_COMPANY_NAME") == null ? null : map.get("ENTRUST_COMPANY_NAME").toString());
                    newCap.setEntrustCompanyNo(map.get("ENTRUST_COMPANY_NO") == null ? null : map.get("ENTRUST_COMPANY_NO").toString());
                    newCap.setProjectId(map.get("PROJECT_ID") == null ? null : map.get("PROJECT_ID").toString());
                    newCap.setProjectName(map.get("PROJECT_NAME") == null ? null : map.get("PROJECT_NAME").toString());
                    newCap.setLinkMan(map.get("LINK_MAN") == null ? null : map.get("LINK_MAN").toString());
                    newCap.setLinkPhone(map.get("LINK_PHONE") == null ? null : map.get("LINK_PHONE").toString());
                    newCap.setSupervisionUnitId(map.get("SUPERVISION_UNIT_ID") == null ? null : map.get("SUPERVISION_UNIT_ID").toString());
                    newCap.setSupervisionUnitName(map.get("SUPERVISION_UNIT_NAME") == null ? null : map.get("SUPERVISION_UNIT_NAME").toString());
                    newCap.setWitness(map.get("WITNESSES") == null ? null : map.get("WITNESSES").toString());
                    newCap.setPrintNum(map.get("PRINT_NUM") == null ? null : map.get("PRINT_NUM").toString());
                    newCap.setEntrustLinkMan(map.get("ENTRUST_LINK_MAN") == null ? null : map.get("ENTRUST_LINK_MAN").toString());
                    newCap.setEntrustLinkPhone(map.get("ENTRUST_LINK_PHONE") == null ? null : map.get("ENTRUST_LINK_PHONE").toString());
                    newCap.setProjectRemark(map.get("PROJECT_REMARK") == null ? null : map.get("PROJECT_REMARK").toString());
                    newCap.setContractId(map.get("CONTRACT_ID") == null ? null : map.get("CONTRACT_ID").toString());

                    list.add(newCap);
                }

                return list;
            }
        });
    }


    @Override
    public String findMaxCapitalAccountCode() {
        return (String) getHibernateTemplate().execute(new HibernateCallback() {
            @SuppressWarnings("rawtypes")
            @Override
            public String doInHibernate(Session session) throws HibernateException,
                    SQLException {
                StringBuffer sql = new StringBuffer();
                sql.append("select MAX(cast(ca.CAPITAL_ACCOUNT_CODE as int)) as maxCapitalAccountCode from CAPITAL_ACCOUNT ca ");

                Query query = session.createSQLQuery(sql.toString());


                query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                List l = query.list();

                String maxCapitalAccountCode = "";

                for (Object obj : l) {

                    Map map = (Map) obj;

                    maxCapitalAccountCode = map.get("maxCapitalAccountCode") == null ? "0" : map.get("maxCapitalAccountCode").toString();
                }
                return maxCapitalAccountCode;
            }
        });
    }

    public PaginationSupport<CapitalAccount> findPageByCriteria(PaginationSupport<CapitalAccount> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    public JsonPager<CapitalAccount> findJsonPageByCriteria(JsonPager<CapitalAccount> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    public EasyPager<CapitalAccount> findEasyPageByCriteria(EasyPager<CapitalAccount> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}