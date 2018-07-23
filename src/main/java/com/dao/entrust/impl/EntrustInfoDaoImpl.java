package com.dao.entrust.impl;

import com.common.CommonMethod;
import com.common.action.EasyPager;
import com.common.action.JsonPager;
import com.common.action.PaginationSupport;
import com.common.dao.BaseDaoImpl;
import com.dao.entrust.EntrustInfoDao;
import com.dao.page.*;
import com.model.BaseDepartment;
import com.model.EntrustInfo;
import net.sf.json.JSONObject;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("entrustInfoDao")
public class EntrustInfoDaoImpl extends BaseDaoImpl<EntrustInfo> implements
        EntrustInfoDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<EntrustSavePage> findEntrustInfoListSQL(
            final EntrustSavePage esPage) {
        return (List<EntrustSavePage>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public List<EntrustSavePage> doInHibernate(Session session)
                            throws HibernateException, SQLException {

                        StringBuffer querySQLCount = new StringBuffer();
                        querySQLCount
                                .append(" select count(distinct ei.ENTRUST_ID) as total ");
                        querySQLCount.append(" from ENTRUST_INFO ei ");
                        querySQLCount
                                .append(" inner join ENTRUST_COMPANY ec on ei.ENTRUST_COMPANY_ID = ec.ENTRUST_COMPANY_ID ");
                        querySQLCount
                                .append(" inner join PROJECT_INFO project on ei.PROJECT_ID = project.PROJECT_ID ");
                        querySQLCount
                                .append(" left join SUPERVISION_UNIT su on ei.SUPERVISION_UNIT_ID = su.SUPERVISION_UNIT_ID ");
                        querySQLCount
                                .append(" inner join SYS_CODE as scode on ei.ACCOUNT_KINDS = scode.CODE_VALUE and scode.CODE_RELATION = 'ACCOUNT_KINDS' ");
                        querySQLCount
                                .append(" inner join SYS_USER as suser on ei.INPUTER = suser.SYS_USER_ID ");
                        querySQLCount
                                .append(" inner join SYS_CODE as sestatus on ei.ENTRUST_STATUS = sestatus.CODE_VALUE and sestatus.CODE_RELATION = 'ENTRUST_STATUS' ");
                        querySQLCount
                                .append(" left join SYS_CODE sc on ei.ACCOUNT_TYPE = sc.CODE_VALUE and sc.CODE_RELATION = 'ACCOUNT_TYPE' ");
                        querySQLCount
                                .append(" left join BASE_DEPARTMENT as mbd on ei.MANAGEMENT_DEPARTMENT_ID = mbd.DEPARTMENT_ID ");
                        querySQLCount.append(" where  1=1 ");
                        if (esPage != null) {
                            // 委托ID不为空
                            if (!CommonMethod.isNull(esPage.getEntrustId())) {
                                querySQLCount.append("and ei.ENTRUST_ID ='");
                                querySQLCount.append(esPage.getEntrustId());
                                querySQLCount.append("'");
                            }
                            // 委托单位ID不为空
                            if (!CommonMethod.isNull(esPage
                                    .getEntrustCompanyId())) {
                                querySQLCount
                                        .append("and ei.ENTRUST_COMPANY_ID = '");
                                querySQLCount.append(esPage
                                        .getEntrustCompanyId());
                                querySQLCount.append("'");
                            }
                            // 委托单位名称不为空
                            if (!CommonMethod.isNull(esPage
                                    .getEntrustCompanyName())) {
                                querySQLCount
                                        .append("and ec.ENTRUST_COMPANY_NAME like '%");
                                querySQLCount.append(esPage
                                        .getEntrustCompanyName());
                                querySQLCount.append("%'");
                            }
                            // 工程ID不为空
                            if (!CommonMethod.isNull(esPage.getProjectId())) {
                                querySQLCount.append("and ei.PROJECT_ID = '");
                                querySQLCount.append(esPage.getProjectId());
                                querySQLCount.append("'");
                            }
                            // 工程名称不为空
                            if (!CommonMethod.isNull(esPage.getProjectName())) {
                                querySQLCount
                                        .append("and project.PROJECT_NAME like '%");
                                querySQLCount.append(esPage.getProjectName());
                                querySQLCount.append("%'");
                            }
                            // 委托编号不为空
                            if (!CommonMethod.isNull(esPage.getEntrustSn())) {
                                querySQLCount
                                        .append("and ei.ENTRUST_SN like '%");
                                querySQLCount.append(esPage.getEntrustSn());
                                querySQLCount.append("%'");
                            }
                            // 单位编号不为空
                            if (!CommonMethod.isNull(esPage
                                    .getEntrustCompanyNo())) {
                                querySQLCount
                                        .append("and ec.ENTRUST_COMPANY_NO ='");
                                querySQLCount.append(esPage
                                        .getEntrustCompanyNo());
                                querySQLCount.append("'");
                            }
                            // 收费类别不为空
                            if (!CommonMethod.isNull(esPage
                                    .getAccountKindsName())) {
                                querySQLCount
                                        .append("and scode.CODE_NAME  like '%");
                                querySQLCount.append(esPage
                                        .getAccountKindsName());
                                querySQLCount.append("%'");
                            }
                            // 委托开始日期
                            if (!CommonMethod.isNull(esPage
                                    .getEntrustStartDate())) {
                                querySQLCount.append("and ei.ENTRUST_DATE >='");
                                querySQLCount.append(esPage
                                        .getEntrustStartDate());
                                querySQLCount.append("'");
                            }
                            // 委托结束日期
                            if (!CommonMethod.isNull(esPage.getEntrustEndDate())) {
                                querySQLCount.append("and ei.ENTRUST_DATE <='");
                                querySQLCount.append(esPage.getEntrustEndDate());
                                querySQLCount.append("'");
                            }
                            // 录入开始日期
                            if (!CommonMethod.isNull(esPage.getInputStartDate())) {
                                querySQLCount.append("and ei.INPUTE_TIME >='");
                                querySQLCount.append(esPage.getInputStartDate());
                                querySQLCount.append("'");
                            }
                            // 录入结束日期
                            if (!CommonMethod.isNull(esPage.getInputEndDate())) {
                                querySQLCount.append("and ei.INPUTE_TIME <='");
                                querySQLCount.append(esPage.getInputEndDate());
                                querySQLCount.append("'");
                            }
                            // 查询补报告信息
                            if (!CommonMethod.isNull(esPage
                                    .getIsComplementally())) {
                                querySQLCount
                                        .append("and ei.IS_COMPLEMENTALLY ='");
                                querySQLCount.append(esPage
                                        .getIsComplementally());
                                querySQLCount.append("'");
                            }
                        }

                        Query queryCount = session.createSQLQuery(querySQLCount
                                .toString());
                        int totalCount = Integer.parseInt(queryCount.list()
                                .get(0).toString());

                        StringBuffer sql = new StringBuffer();
                        sql.append(" select ei.ENTRUST_ID,ei.ENTRUST_SN,ei.ENTRUST_COMPANY_ID,ec.ENTRUST_COMPANY_NAME,ec.ENTRUST_COMPANY_NO,ei.PROJECT_ID,project.PROJECT_NAME, ");
                        sql.append(" ei.PROJECT_REMARK,ei.ENTRUST_DATE,ei.SUPERVISION_UNIT_ID,su.SUPERVISION_UNIT_NAME,ei.PROJECT_ADDRESS,ei.LINK_PHONE,ei.INVOLID_MONEY,ei.CONTRACT_ID,ei.SPECIAL_RULE, ");
                        sql.append(" ei.INSPECTION_MAN,ei.WITNESS_MAN,ei.LINK_MAN,ei.ACCOUNT_TYPE,sc.CODE_NAME,ei.ACCOUNT_KINDS,scode.CODE_NAME as ACCOUNT_KINDS_NAME,ei.CONTRACT_CODE, ");
                        sql.append(" ei.ACCOUNT_VALUE,ei.MANAGEMENT_DEPARTMENT_ID,mbd.DEPARTMENT_NAME,ei.IS_COMPLEMENTALLY,ei.ENTRUST_STATUS,ei.PRINT_NUM,ei.CAPITAL_ACCOUNT_ID, ");
                        sql.append(" ei.REMARK,ei.INPUTER,ei.INPUTE_TIME,ei.UPDATER,ei.UPDATE_TIME,suser.USER_NAME as INPUTER_NAME,sestatus.CODE_NAME as ENTRUST_STATUS_NAME  ");
                        sql.append(" from ENTRUST_INFO ei ");
                        sql.append(" inner join ENTRUST_COMPANY ec on ei.ENTRUST_COMPANY_ID = ec.ENTRUST_COMPANY_ID ");
                        sql.append(" inner join PROJECT_INFO project on ei.PROJECT_ID = project.PROJECT_ID ");
                        sql.append(" left join SUPERVISION_UNIT su on ei.SUPERVISION_UNIT_ID = su.SUPERVISION_UNIT_ID ");
                        sql.append(" inner join SYS_CODE as scode on ei.ACCOUNT_KINDS = scode.CODE_VALUE and scode.CODE_RELATION = 'ACCOUNT_KINDS' ");
                        sql.append(" inner join SYS_USER as suser on ei.INPUTER = suser.SYS_USER_ID ");
                        sql.append(" inner join SYS_CODE as sestatus on ei.ENTRUST_STATUS = sestatus.CODE_VALUE and sestatus.CODE_RELATION = 'ENTRUST_STATUS' ");
                        sql.append(" left join SYS_CODE sc on ei.ACCOUNT_TYPE = sc.CODE_VALUE and sc.CODE_RELATION = 'ACCOUNT_TYPE' ");
                        sql.append(" left join BASE_DEPARTMENT as mbd on ei.MANAGEMENT_DEPARTMENT_ID = mbd.DEPARTMENT_ID ");
                        sql.append(" where  1=1 ");
                        if (esPage != null) {
                            // 委托ID不为空
                            if (!CommonMethod.isNull(esPage.getEntrustId())) {
                                sql.append("and ei.ENTRUST_ID ='");
                                sql.append(esPage.getEntrustId());
                                sql.append("'");
                            }
                            // 委托录入人不为空
                            if (!CommonMethod.isNull(esPage.getInputer())) {
                                sql.append("and ei.INPUTER ='");
                                sql.append(esPage.getInputer());
                                sql.append("'");
                            }
                            // 委托单位ID不为空
                            if (!CommonMethod.isNull(esPage
                                    .getEntrustCompanyId())) {
                                sql.append("and ei.ENTRUST_COMPANY_ID = '");
                                sql.append(esPage.getEntrustCompanyId());
                                sql.append("'");
                            }
                            // 委托单位名称不为空
                            if (!CommonMethod.isNull(esPage
                                    .getEntrustCompanyName())) {
                                sql.append("and ec.ENTRUST_COMPANY_NAME like '%");
                                sql.append(esPage.getEntrustCompanyName());
                                sql.append("%'");
                            }
                            // 工程ID不为空
                            if (!CommonMethod.isNull(esPage.getProjectId())) {
                                sql.append("and ei.PROJECT_ID = '");
                                sql.append(esPage.getProjectId());
                                sql.append("'");
                            }
                            // 工程名称不为空
                            if (!CommonMethod.isNull(esPage.getProjectName())) {
                                sql.append("and project.PROJECT_NAME like '%");
                                sql.append(esPage.getProjectName());
                                sql.append("%'");
                            }
                            // 委托编号不为空
                            if (!CommonMethod.isNull(esPage.getEntrustSn())) {
                                sql.append("and ei.ENTRUST_SN like '%");
                                sql.append(esPage.getEntrustSn());
                                sql.append("%'");
                            }
                            // 单位编号不为空
                            if (!CommonMethod.isNull(esPage
                                    .getEntrustCompanyNo())) {
                                sql.append("and ec.ENTRUST_COMPANY_NO ='");
                                sql.append(esPage.getEntrustCompanyNo());
                                sql.append("'");
                            }
                            // 收费类别不为空
                            if (!CommonMethod.isNull(esPage.getAccountKinds())) {
                                sql.append("and ei.ACCOUNT_KINDS  = '");
                                sql.append(esPage.getAccountKinds());
                                sql.append("'");
                            }
                            // 委托开始日期
                            if (!CommonMethod.isNull(esPage
                                    .getEntrustStartDate())) {
                                sql.append("and ei.ENTRUST_DATE >='");
                                sql.append(esPage.getEntrustStartDate());
                                sql.append("'");
                            }
                            // 委托结束日期
                            if (!CommonMethod.isNull(esPage.getEntrustEndDate())) {
                                sql.append("and ei.ENTRUST_DATE <='");
                                sql.append(esPage.getEntrustEndDate());
                                sql.append("'");
                            }
                            // 录入开始日期
                            if (!CommonMethod.isNull(esPage.getInputStartDate())) {
                                sql.append("and ei.INPUTE_TIME >='");
                                sql.append(esPage.getInputStartDate());
                                sql.append("'");
                            }
                            // 录入结束日期
                            if (!CommonMethod.isNull(esPage.getInputEndDate())) {
                                sql.append("and ei.INPUTE_TIME <='");
                                sql.append(esPage.getInputEndDate());
                                sql.append("'");
                            }
                            // 查询补报告信息
                            if (!CommonMethod.isNull(esPage
                                    .getIsComplementally())) {
                                sql.append("and ei.IS_COMPLEMENTALLY ='");
                                sql.append(esPage.getIsComplementally());
                                sql.append("'");
                            }
                        }
                        sql.append(" order by ei.INPUTE_TIME desc ");
                        Query query = session.createSQLQuery(sql.toString());

                        query.setFirstResult((esPage.getPageNo() - 1)
                                * esPage.getPageSize());
                        query.setMaxResults(esPage.getPageSize());

                        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                        List l = query.list();

                        List<EntrustSavePage> list = new ArrayList<EntrustSavePage>();

                        for (Object obj : l) {

                            Map map = (Map) obj;
                            EntrustSavePage newCap = getEntrustPage(map,
                                    totalCount);
                            list.add(newCap);
                        }

                        return list;
                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EntrustSavePage> findEntrustInfoListSQLByAll(
            final EntrustSavePage esPage) {
        return (List<EntrustSavePage>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public List<EntrustSavePage> doInHibernate(Session session)
                            throws HibernateException, SQLException {

                        int totalCount = 0;

                        StringBuffer sql = new StringBuffer();
                        sql.append(" select ei.ENTRUST_ID  ");
                        sql.append(" from ENTRUST_INFO ei ");
                        sql.append(" inner join ENTRUST_COMPANY ec on ei.ENTRUST_COMPANY_ID = ec.ENTRUST_COMPANY_ID ");
                        sql.append(" inner join PROJECT_INFO project on ei.PROJECT_ID = project.PROJECT_ID ");
                        sql.append(" left join SUPERVISION_UNIT su on ei.SUPERVISION_UNIT_ID = su.SUPERVISION_UNIT_ID ");
                        sql.append(" inner join SYS_CODE as scode on ei.ACCOUNT_KINDS = scode.CODE_VALUE and scode.CODE_RELATION = 'ACCOUNT_KINDS' ");
                        sql.append(" inner join SYS_USER as suser on ei.INPUTER = suser.SYS_USER_ID ");
                        sql.append(" inner join SYS_CODE as sestatus on ei.ENTRUST_STATUS = sestatus.CODE_VALUE and sestatus.CODE_RELATION = 'ENTRUST_STATUS' ");
                        sql.append(" left join SYS_CODE sc on ei.ACCOUNT_TYPE = sc.CODE_VALUE and sc.CODE_RELATION = 'ACCOUNT_TYPE' ");
                        sql.append(" left join BASE_DEPARTMENT as mbd on ei.MANAGEMENT_DEPARTMENT_ID = mbd.DEPARTMENT_ID ");
                        sql.append(" where  1=1 ");
                        if (esPage != null) {
                            // 工程ID不为空
                            if (!CommonMethod.isNull(esPage.getProjectId())) {
                                sql.append("and ei.PROJECT_ID = '");
                                sql.append(esPage.getProjectId());
                                sql.append("'");
                            } else if (!CommonMethod.isNull(esPage
                                    .getProjectName())) {// 工程名称不为空
                                sql.append("and project.PROJECT_NAME like '%");
                                sql.append(esPage.getProjectName());
                                sql.append("%'");
                            }
                        }
                        sql.append(" order by ei.INPUTE_TIME desc ");
                        Query query = session.createSQLQuery(sql.toString());

                        query.setFirstResult((esPage.getPageNo() - 1)
                                * esPage.getPageSize());
                        query.setMaxResults(esPage.getPageSize());

                        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                        List l = query.list();

                        List<EntrustSavePage> list = new ArrayList<EntrustSavePage>();

                        for (Object obj : l) {
                            Map map = (Map) obj;
                            EntrustSavePage newCap = getEntrustPage(map,
                                    totalCount);
                            list.add(newCap);
                        }

                        return list;
                    }
                });
    }

    public EntrustSavePage getEntrustPage(Map map, int totalCount) {
        EntrustSavePage newCap = new EntrustSavePage();
        newCap.setTotalCount(totalCount);
        newCap.setEntrustId(map.get("ENTRUST_ID") == null ? null : map.get(
                "ENTRUST_ID").toString());
        newCap.setEntrustSn(map.get("ENTRUST_SN") == null ? null : map.get(
                "ENTRUST_SN").toString());
        newCap.setEntrustCompanyId(map.get("ENTRUST_COMPANY_ID") == null ? null
                : map.get("ENTRUST_COMPANY_ID").toString());
        newCap.setEntrustCompanyName(map.get("ENTRUST_COMPANY_NAME") == null ? null
                : map.get("ENTRUST_COMPANY_NAME").toString());
        newCap.setEntrustCompanyNo(map.get("ENTRUST_COMPANY_NO") == null ? null
                : map.get("ENTRUST_COMPANY_NO").toString());
        newCap.setProjectId(map.get("PROJECT_ID") == null ? null : map.get(
                "PROJECT_ID").toString());
        newCap.setProjectName(map.get("PROJECT_NAME") == null ? null : map.get(
                "PROJECT_NAME").toString());
        newCap.setProjectRemark(map.get("PROJECT_REMARK") == null ? null : map
                .get("PROJECT_REMARK").toString());
        newCap.setEntrustDate(map.get("ENTRUST_DATE") == null ? null : map.get(
                "ENTRUST_DATE").toString());
        newCap.setSupervisionUnitId(map.get("SUPERVISION_UNIT_ID") == null ? null
                : map.get("SUPERVISION_UNIT_ID").toString());
        newCap.setSupervisionUnitName(map.get("SUPERVISION_UNIT_NAME") == null ? null
                : map.get("SUPERVISION_UNIT_NAME").toString());
        newCap.setProjectAddress(map.get("PROJECT_ADDRESS") == null ? null
                : map.get("PROJECT_ADDRESS").toString());
        newCap.setLinkPhone(map.get("LINK_PHONE") == null ? null : map.get(
                "LINK_PHONE").toString());
        newCap.setInspectionMan(map.get("INSPECTION_MAN") == null ? null : map
                .get("INSPECTION_MAN").toString());
        newCap.setWitnessMan(map.get("WITNESS_MAN") == null ? null : map.get(
                "WITNESS_MAN").toString());
        newCap.setLinkMan(map.get("LINK_MAN") == null ? null : map.get(
                "LINK_MAN").toString());
        newCap.setAccountType(map.get("ACCOUNT_TYPE") == null ? null : map.get(
                "ACCOUNT_TYPE").toString());
        newCap.setAccountTypeName(map.get("CODE_NAME") == null ? null : map
                .get("CODE_NAME").toString());
        newCap.setAccountKinds(map.get("ACCOUNT_KINDS") == null ? null : map
                .get("ACCOUNT_KINDS").toString());
        newCap.setAccountKindsName(map.get("ACCOUNT_KINDS_NAME") == null ? null
                : map.get("ACCOUNT_KINDS_NAME").toString());
        newCap.setContractCode(map.get("CONTRACT_CODE") == null ? null : map
                .get("CONTRACT_CODE").toString());
        newCap.setAccountValue(map.get("ACCOUNT_VALUE") == null ? 0 : Double
                .valueOf(map.get("ACCOUNT_VALUE").toString()));
        newCap.setManagementDepartmentId(map.get("MANAGEMENT_DEPARTMENT_ID") == null ? null
                : map.get("MANAGEMENT_DEPARTMENT_ID").toString());
        newCap.setManagementDepartmentName(map.get("DEPARTMENT_NAME") == null ? null
                : map.get("DEPARTMENT_NAME").toString());
        newCap.setIsComplementally(map.get("IS_COMPLEMENTALLY") == null ? null
                : map.get("IS_COMPLEMENTALLY").toString());
        newCap.setEntrustStatus(map.get("ENTRUST_STATUS") == null ? null : map
                .get("ENTRUST_STATUS").toString());
        newCap.setRemark(map.get("REMARK") == null ? null : map.get("REMARK")
                .toString());
        newCap.setInputer(map.get("INPUTER") == null ? null : map
                .get("INPUTER").toString());
        newCap.setInputeTime(map.get("INPUTE_TIME") == null ? null : map.get(
                "INPUTE_TIME").toString());
        newCap.setUpdater(map.get("UPDATER") == null ? null : map
                .get("UPDATER").toString());
        newCap.setUpdateTime(map.get("UPDATE_TIME") == null ? null : map.get(
                "UPDATE_TIME").toString());
        newCap.setInputerName(map.get("INPUTER_NAME") == null ? null : map.get(
                "INPUTER_NAME").toString());
        newCap.setEntrustStatusName(map.get("ENTRUST_STATUS_NAME") == null ? null
                : map.get("ENTRUST_STATUS_NAME").toString());
        newCap.setPrintNum(map.get("PRINT_NUM") == null ? null : map.get(
                "PRINT_NUM").toString());
        newCap.setCapitalAccountId(map.get("CAPITAL_ACCOUNT_ID") == null ? null
                : map.get("CAPITAL_ACCOUNT_ID").toString());
        newCap.setInvolidMoney(map.get("INVOLID_MONEY") == null ? 0 : Double
                .valueOf(map.get("INVOLID_MONEY").toString()));
        newCap.setContractId(map.get("CONTRACT_ID") == null ? null : map.get(
                "CONTRACT_ID").toString());
        newCap.setSpecialRule(map.get("SPECIAL_RULE") == null ? null : map.get(
                "SPECIAL_RULE").toString());
        return newCap;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EntrustSavePage> findEMDInfoListSQL(final String strEntrustId) {
        return (List<EntrustSavePage>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public List<EntrustSavePage> doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        StringBuffer sql = new StringBuffer();
                        sql.append(" select ed.ENTRUST_DETAIL_ID,ed.ENTRUST_ID,ed.DEPARTMENT_ID,bd.DEPARTMENT_NAME,ed.SAMPLE_ID,bs.SAMPLE_NAME,bs.TEST_RESULT, ");
                        sql.append(" ed.STANDARDS,ed.REPORT_NUM,ed.PROCESS_STATUS,ed.WITNESS_MAN,ed.REMARK,ed.INPUTER,ed.INPUTE_TIME,ed.UPDATER,ed.UPDATE_TIME, ");
                        sql.append(" ed.REAL_TOTAL_PRICE,ed.SAMPLE_SOURCE,ed.SAMPLE_TYPE ");
                        sql.append(" from ENTRUST_DETAILS ed ");
                        sql.append(" inner join BASE_DEPARTMENT bd on ed.DEPARTMENT_ID = bd.DEPARTMENT_ID ");
                        sql.append(" inner join BASE_SAMPLE bs on ed.SAMPLE_ID = bs.SAMPLE_ID ");
                        sql.append(" where ed.ENTRUST_ID in ('");
                        sql.append(strEntrustId);
                        sql.append("')");
                        Query query = session.createSQLQuery(sql.toString());

                        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                        List l = query.list();

                        List<EntrustSavePage> list = new ArrayList<EntrustSavePage>();
                        EntrustSavePage esp = new EntrustSavePage();
                        List<EntrustDetailReportPage> edReportPageList = new ArrayList<EntrustDetailReportPage>();

                        for (Object obj : l) {

                            Map map = (Map) obj;
                            EntrustDetailReportPage edReportPage = new EntrustDetailReportPage();

                            edReportPage.setEntrustDetailId(map
                                    .get("ENTRUST_DETAIL_ID") == null ? null
                                    : map.get("ENTRUST_DETAIL_ID").toString());
                            edReportPage
                                    .setEntrustId(map.get("ENTRUST_ID") == null ? null
                                            : map.get("ENTRUST_ID").toString());
                            edReportPage.setDepartmentId(map
                                    .get("DEPARTMENT_ID") == null ? null : map
                                    .get("DEPARTMENT_ID").toString());
                            edReportPage.setDepartmentName(map
                                    .get("DEPARTMENT_NAME") == null ? null
                                    : map.get("DEPARTMENT_NAME").toString());
                            edReportPage
                                    .setSampleId(map.get("SAMPLE_ID") == null ? null
                                            : map.get("SAMPLE_ID").toString());
                            edReportPage
                                    .setSampleName(map.get("SAMPLE_NAME") == null ? null
                                            : map.get("SAMPLE_NAME").toString());
                            edReportPage
                                    .setStandards(map.get("STANDARDS") == null ? null
                                            : map.get("STANDARDS").toString());
                            edReportPage
                                    .setReportNum(map.get("REPORT_NUM") == null ? 0
                                            : Integer.valueOf(map.get(
                                            "REPORT_NUM").toString()));
                            edReportPage.setProcessStatus(map
                                    .get("PROCESS_STATUS") == null ? null : map
                                    .get("PROCESS_STATUS").toString());
                            edReportPage
                                    .setWitness(map.get("WITNESS_MAN") == null ? null
                                            : map.get("WITNESS_MAN").toString());
                            edReportPage.setRemark(map.get("REMARK") == null ? null
                                    : map.get("REMARK").toString());
                            edReportPage.setInputer(map.get("INPUTER") == null ? null
                                    : map.get("INPUTER").toString());
                            edReportPage
                                    .setInputeTime(map.get("INPUTE_TIME") == null ? null
                                            : map.get("INPUTE_TIME").toString());
                            edReportPage.setUpdater(map.get("UPDATER") == null ? null
                                    : map.get("UPDATER").toString());
                            edReportPage
                                    .setUpdateTime(map.get("UPDATE_TIME") == null ? null
                                            : map.get("UPDATE_TIME").toString());
                            edReportPage.setRealTotalPrice(map
                                    .get("REAL_TOTAL_PRICE") == null ? 0
                                    : Double.valueOf(map
                                    .get("REAL_TOTAL_PRICE").toString()));
                            edReportPage.setSampleSource(map
                                    .get("SAMPLE_SOURCE") == null ? null : map
                                    .get("SAMPLE_SOURCE").toString());
                            edReportPage
                                    .setSampleType(map.get("SAMPLE_TYPE") == null ? null
                                            : map.get("SAMPLE_TYPE").toString());
                            edReportPage
                                    .setTestResult(map.get("TEST_RESULT") == null ? null
                                            : map.get("TEST_RESULT").toString());

                            edReportPageList.add(edReportPage);
                        }
                        esp.setEdReport(edReportPageList);
                        list.add(esp);
                        return list;
                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EntrustSavePage> findERDInfoListSQL(final EntrustSavePage esPage) {
        return (List<EntrustSavePage>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public List<EntrustSavePage> doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        StringBuffer sql = new StringBuffer();
                        sql.append(" select ed.ENTRUST_DETAIL_ID,ed.DEPARTMENT_ID,bd.DEPARTMENT_NAME,ed.SAMPLE_ID,bs.SAMPLE_NAME,ei.ENTRUST_SN, ");
                        sql.append(" ed.TEST_PARAMETER_ID,ed.SAMPLE_NO,ed.SAMPLE_SOURCE,ed.PROJECT_PART,ed.MOLDING_DATE, ");
                        sql.append(" ed.SAMPLE_AGE,ed.REPORT_NUM,ed.PROCESS_STATUS,ed.REMARK,ed.INPUTER,ed.INPUTE_TIME,ed.UPDATER,ed.UPDATE_TIME,ed.SAMPLE_LEVEL ");
                        sql.append(" from ENTRUST_DETAILS ed ,BASE_DEPARTMENT bd ,BASE_SAMPLE bs ,ENTRUST_INFO ei");
                        sql.append(" where ed.DEPARTMENT_ID = bd.DEPARTMENT_ID and ed.SAMPLE_ID = bs.SAMPLE_ID and ed.ENTRUST_ID = ei.ENTRUST_ID ");
                        if (!CommonMethod.isNull(esPage.getEntrustId())) {
                            sql.append(" and ed.ENTRUST_ID = '");
                            sql.append(esPage.getEntrustId());
                            sql.append("'");
                        }
                        if (!CommonMethod.isNull(esPage.getEntrustSn())) {
                            sql.append(" and ei.ENTRUST_SN = '");
                            sql.append(esPage.getEntrustSn());
                            sql.append("'");
                        }
                        // 查询补报告信息
                        if (!CommonMethod.isNull(esPage.getIsComplementally())) {
                            sql.append("and ei.IS_COMPLEMENTALLY ='");
                            sql.append(esPage.getIsComplementally());
                            sql.append("'");
                        }

                        Query query = session.createSQLQuery(sql.toString());

                        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                        List l = query.list();

                        List<EntrustSavePage> list = new ArrayList<EntrustSavePage>();
                        EntrustSavePage esp = new EntrustSavePage();
                        List<EntrustDetailReportPage> edrList = new ArrayList<EntrustDetailReportPage>();

                        for (Object obj : l) {

                            Map map = (Map) obj;
                            EntrustDetailReportPage newCap = new EntrustDetailReportPage();

                            newCap.setEntrustDetailId(map
                                    .get("ENTRUST_DETAIL_ID") == null ? null
                                    : map.get("ENTRUST_DETAIL_ID").toString());
                            newCap.setDepartmentId(map.get("DEPARTMENT_ID") == null ? null
                                    : map.get("DEPARTMENT_ID").toString());
                            newCap.setDepartmentName(map.get("DEPARTMENT_NAME") == null ? null
                                    : map.get("DEPARTMENT_NAME").toString());
                            newCap.setSampleId(map.get("SAMPLE_ID") == null ? null
                                    : map.get("SAMPLE_ID").toString());
                            newCap.setSampleName(map.get("SAMPLE_NAME") == null ? null
                                    : map.get("SAMPLE_NAME").toString());
                            // newCap.setTestParameterId(map.get("TEST_PARAMETER_ID")==null?null:map.get("TEST_PARAMETER_ID").toString());
                            // newCap.setTestParameterName(map.get("TEST_PARAMETER_NAME")==null?null:map.get("TEST_PARAMETER_NAME").toString());
                            // newCap.setSampleNo(map.get("SAMPLE_NO")==null?null:map.get("SAMPLE_NO").toString());
                            newCap.setSampleSource(map.get("SAMPLE_SOURCE") == null ? null
                                    : map.get("SAMPLE_SOURCE").toString());
                            // newCap.setProjectPart(map.get("PROJECT_PART")==null?null:map.get("PROJECT_PART").toString());
                            // newCap.setMoldingDate(map.get("MOLDING_DATE")==null?null:map.get("MOLDING_DATE").toString());
                            // newCap.setSampleAge(map.get("SAMPLE_AGE")==null?0:Integer.valueOf(map.get("SAMPLE_AGE").toString()));
                            newCap.setReportNum(map.get("REPORT_NUM") == null ? 0
                                    : Integer.valueOf(map.get("REPORT_NUM")
                                    .toString()));
                            newCap.setProcessStatus(map.get("PROCESS_STATUS") == null ? null
                                    : map.get("PROCESS_STATUS").toString());
                            newCap.setRemark(map.get("REMARK") == null ? null
                                    : map.get("REMARK").toString());
                            newCap.setInputer(map.get("INPUTER") == null ? null
                                    : map.get("INPUTER").toString());
                            newCap.setInputeTime(map.get("INPUTE_TIME") == null ? null
                                    : map.get("INPUTE_TIME").toString());
                            newCap.setUpdater(map.get("UPDATER") == null ? null
                                    : map.get("UPDATER").toString());
                            newCap.setUpdateTime(map.get("UPDATE_TIME") == null ? null
                                    : map.get("UPDATE_TIME").toString());
                            // newCap.setSampleLevel(map.get("SAMPLE_LEVEL")==null?null:map.get("SAMPLE_LEVEL").toString());

                            edrList.add(newCap);
                        }
                        esp.setEntrustId(esPage.getEntrustId());
                        esp.setEdReport(edrList);
                        list.add(esp);
                        return list;
                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EntrustReportPage> findERDetailInfoListSQL(
            final EntrustReportPage erPage) {
        return (List<EntrustReportPage>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public List<EntrustReportPage> doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        StringBuffer sql = new StringBuffer();
                        sql.append("select ed.ENTRUST_DETAIL_ID,ei.ENTRUST_SN,ei.ENTRUST_DATE,bs.SAMPLE_NAME,ei.INPUTER as registrant, ");
                        sql.append("su.USER_NAME as registrantName,ei.INPUTE_TIME as registeredTime,ed.INPUTER,sUser.USER_NAME as inputeName, ");
                        sql.append("ed.INPUTE_TIME,ei.ENTRUST_STATUS,sc.CODE_NAME as entrustStatusName, ");
                        sql.append("ed.REPORT_NUM,ed.WITNESS_MAN ");
                        sql.append("from ENTRUST_DETAILS ed ,BASE_DEPARTMENT bd ,BASE_SAMPLE bs ,ENTRUST_INFO ei,SYS_USER su,SYS_USER sUser,SYS_CODE sc ");
                        sql.append("where ed.DEPARTMENT_ID = bd.DEPARTMENT_ID and ed.SAMPLE_ID = bs.SAMPLE_ID and ed.ENTRUST_ID = ei.ENTRUST_ID ");
                        sql.append("and ei.INPUTER = su.SYS_USER_ID and ed.INPUTER = sUser.SYS_USER_ID and ei.ENTRUST_STATUS = sc.CODE_VALUE and sc.CODE_RELATION = 'ENTRUST_STATUS' ");
                        if (!CommonMethod.isNull(erPage.getDepartmentId())) {
                            sql.append(" and ed.DEPARTMENT_ID = '");
                            sql.append(erPage.getDepartmentId());
                            sql.append("'");
                        }
                        if (!CommonMethod.isNull(erPage.getEntrustSn())) {
                            sql.append(" and ei.ENTRUST_SN like '%");
                            sql.append(erPage.getEntrustSn());
                            sql.append("%'");
                        }
                        if (!CommonMethod.isNull(erPage.getSampleName())) {
                            sql.append(" and bs.SAMPLE_NAME like '%");
                            sql.append(erPage.getSampleName());
                            sql.append("%'");
                        }
                        // 委托开始日期
                        if (!CommonMethod.isNull(erPage.getEntrustStartDate())) {
                            sql.append("and ei.ENTRUST_DATE >='");
                            sql.append(erPage.getEntrustStartDate());
                            sql.append("'");
                        }
                        // 委托结束日期
                        if (!CommonMethod.isNull(erPage.getEntrustEndDate())) {
                            sql.append("and ei.ENTRUST_DATE <='");
                            sql.append(erPage.getEntrustEndDate());
                            sql.append("'");
                        }
                        // 登记开始日期
                        if (!CommonMethod.isNull(erPage
                                .getRegistrantStartDate())) {
                            sql.append("and ei.INPUTE_TIME >='");
                            sql.append(erPage.getRegistrantStartDate());
                            sql.append("'");
                        }
                        // 登记结束日期
                        if (!CommonMethod.isNull(erPage.getRegistrantEndDate())) {
                            sql.append("and ei.INPUTE_TIME <='");
                            sql.append(erPage.getRegistrantEndDate());
                            sql.append("'");
                        }
                        // 录入开始日期
                        if (!CommonMethod.isNull(erPage.getInputeStartDate())) {
                            sql.append("and ed.INPUTE_TIME >='");
                            sql.append(erPage.getInputeStartDate());
                            sql.append("'");
                        }
                        // 录入结束日期
                        if (!CommonMethod.isNull(erPage.getInputeEndDate())) {
                            sql.append("and ed.INPUTE_TIME <='");
                            sql.append(erPage.getInputeEndDate());
                            sql.append("'");
                        }
                        // 查询补报告信息
                        if (!CommonMethod.isNull(erPage.getIsComplementally())) {
                            sql.append("and ei.IS_COMPLEMENTALLY ='");
                            sql.append(erPage.getIsComplementally());
                            sql.append("'");
                        }

                        Query query = session.createSQLQuery(sql.toString());

                        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                        List l = query.list();

                        List<EntrustReportPage> list = new ArrayList<EntrustReportPage>();

                        for (Object obj : l) {

                            Map map = (Map) obj;
                            EntrustReportPage erp = new EntrustReportPage();

                            erp.setEntrustDetailId(map.get("ENTRUST_DETAIL_ID") == null ? null
                                    : map.get("ENTRUST_DETAIL_ID").toString());
                            erp.setEntrustSn(map.get("ENTRUST_SN") == null ? null
                                    : map.get("ENTRUST_SN").toString());
                            erp.setEntrustDate(map.get("ENTRUST_DATE") == null ? null
                                    : map.get("ENTRUST_DATE").toString());
                            erp.setSampleName(map.get("SAMPLE_NAME") == null ? null
                                    : map.get("SAMPLE_NAME").toString() + "报告");
                            // erp.setSampleNo(map.get("SAMPLE_NO")==null?null:map.get("SAMPLE_NO").toString());
                            erp.setRegistrant(map.get("registrant") == null ? null
                                    : map.get("registrant").toString());
                            erp.setRegistrantName(map.get("registrantName") == null ? null
                                    : map.get("registrantName").toString());
                            erp.setRegisteredTime(map.get("registeredTime") == null ? null
                                    : map.get("registeredTime").toString());
                            erp.setInputer(map.get("INPUTER") == null ? null
                                    : map.get("INPUTER").toString());
                            erp.setInputeName(map.get("inputeName") == null ? null
                                    : map.get("inputeName").toString());
                            erp.setInputeTime(map.get("INPUTE_TIME") == null ? null
                                    : map.get("INPUTE_TIME").toString());
                            erp.setEntrustStatus(map.get("ENTRUST_STATUS") == null ? null
                                    : map.get("ENTRUST_STATUS").toString());
                            erp.setEntrustStatusName(map
                                    .get("entrustStatusName") == null ? null
                                    : map.get("entrustStatusName").toString());
                            erp.setTestParameterId(map.get("TEST_PARAMETER_ID") == null ? null
                                    : map.get("TEST_PARAMETER_ID").toString());
                            erp.setReportNum(map.get("REPORT_NUM") == null ? 0
                                    : Integer.valueOf(map.get("REPORT_NUM")
                                    .toString()));
                            // erp.setMoldingDate(map.get("MOLDING_DATE")==null?null:map.get("MOLDING_DATE").toString());
                            // erp.setSampleAge(map.get("SAMPLE_AGE")==null?0:Integer.valueOf(map.get("SAMPLE_AGE").toString()));
                            // erp.setProjectPart(map.get("PROJECT_PART")==null?null:map.get("PROJECT_PART").toString());
                            erp.setWitness(map.get("WITNESS_MAN") == null ? null
                                    : map.get("WITNESS_MAN").toString());
                            erp.setSampleLevel(map.get("SAMPLE_LEVEL") == null ? null
                                    : map.get("SAMPLE_LEVEL").toString());

                            list.add(erp);
                        }
                        return list;
                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProjectMoneyPage> findProjectMoney(
            final ProjectMoneyPage pMoneyPage) {
        return (List<ProjectMoneyPage>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public List<ProjectMoneyPage> doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        StringBuffer sql = new StringBuffer();
                        sql.append(" select testRecord.ENTRUST_COMPANY_ID,testRecord.PROJECT_ID,testRecord.testMoney,agRecord.agMoney, ");
                        sql.append(" arRecord.arMoney,eCompany.ENTRUST_COMPANY_NAME,projectInfo.PROJECT_NAME,eCompany.ENTRUST_COMPANY_NO ");
                        sql.append(" from ");
                        sql.append(" ( ");
                        sql.append(" select ei.ENTRUST_COMPANY_ID,ei.PROJECT_ID,sum(ei.ACCOUNT_VALUE) as testMoney ");
                        sql.append(" from ENTRUST_INFO ei,PROJECT_INFO pInfo,ENTRUST_COMPANY ec  ");
                        sql.append(" where ei.PROJECT_ID=pInfo.PROJECT_ID and ei.ENTRUST_COMPANY_ID = ec.ENTRUST_COMPANY_ID and ei.IS_COMPLEMENTALLY ='00' and ei.ACCOUNT_KINDS <>'05' ");
                        if (pMoneyPage != null) {
                            // 委托单位ID不为空
                            if (!CommonMethod.isNull(pMoneyPage
                                    .getEntrustCompanyId())) {
                                sql.append("and ei.ENTRUST_COMPANY_ID = '");
                                sql.append(pMoneyPage.getEntrustCompanyId());
                                sql.append("'");
                            }
                            // 委托单位名称不为空
                            if (!CommonMethod.isNull(pMoneyPage
                                    .getEntrustCompanyName())) {
                                sql.append("and ec.ENTRUST_COMPANY_NAME like '%");
                                sql.append(pMoneyPage.getEntrustCompanyName());
                                sql.append("%'");
                            }
                            // 工程ID不为空
                            if (!CommonMethod.isNull(pMoneyPage.getProjectId())) {
                                sql.append("and ei.PROJECT_ID = '");
                                sql.append(pMoneyPage.getProjectId());
                                sql.append("'");
                            }
                            // 工程名称不为空
                            if (!CommonMethod.isNull(pMoneyPage
                                    .getProjectName())) {
                                sql.append("and pInfo.PROJECT_NAME like '%");
                                sql.append(pMoneyPage.getProjectName());
                                sql.append("%'");
                            }
                            // 单位编号不为空
                            if (!CommonMethod.isNull(pMoneyPage
                                    .getEntrustCompanyNo())) {
                                sql.append("and ec.ENTRUST_COMPANY_NO ='");
                                sql.append(pMoneyPage.getEntrustCompanyNo());
                                sql.append("'");
                            }
                        }
                        sql.append(" group by ei.ENTRUST_COMPANY_ID,ei.PROJECT_ID) as testRecord ");
                        sql.append(" left join ");
                        sql.append(" (select ag.ENTRUST_COMPANY_ID,ag.PROJECT_ID,SUM(ag.CONTRACT_MONEY) as agMoney ");
                        sql.append(" from AGREEMENT ag group by ag.ENTRUST_COMPANY_ID,ag.PROJECT_ID) as agRecord ");
                        sql.append(" on testRecord.ENTRUST_COMPANY_ID = agRecord.ENTRUST_COMPANY_ID and testRecord.PROJECT_ID = agRecord.PROJECT_ID ");
                        sql.append(" left join ");
                        sql.append(" (select ar.ENTRUST_COMPANY_ID,ar.PROJECT_ID,SUM(ar.RECEIVABLE_MONEY) as arMoney ");
                        sql.append(" from AMOUNT_RECEIVABLE ar group by ar.ENTRUST_COMPANY_ID,ar.PROJECT_ID) as arRecord ");
                        sql.append(" on testRecord.ENTRUST_COMPANY_ID = arRecord.ENTRUST_COMPANY_ID and testRecord.PROJECT_ID = arRecord.PROJECT_ID ");
                        sql.append(" left join ENTRUST_COMPANY eCompany on testRecord.ENTRUST_COMPANY_ID = eCompany.ENTRUST_COMPANY_ID ");
                        sql.append(" left join PROJECT_INFO projectInfo on testRecord.PROJECT_ID = projectInfo.PROJECT_ID");

                        Query query = session.createSQLQuery(sql.toString());

                        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                        List l = query.list();

                        List<ProjectMoneyPage> list = new ArrayList<ProjectMoneyPage>();

                        for (Object obj : l) {

                            Map map = (Map) obj;
                            ProjectMoneyPage pMoneyPage = new ProjectMoneyPage();

                            pMoneyPage.setEntrustCompanyId(map
                                    .get("ENTRUST_COMPANY_ID") == null ? null
                                    : map.get("ENTRUST_COMPANY_ID").toString());
                            pMoneyPage.setEntrustCompanyName(map
                                    .get("ENTRUST_COMPANY_NAME") == null ? null
                                    : map.get("ENTRUST_COMPANY_NAME")
                                    .toString());
                            pMoneyPage.setEntrustCompanyNo(map
                                    .get("ENTRUST_COMPANY_NO") == null ? null
                                    : map.get("ENTRUST_COMPANY_NO").toString());
                            pMoneyPage
                                    .setProjectId(map.get("PROJECT_ID") == null ? null
                                            : map.get("PROJECT_ID").toString());
                            pMoneyPage
                                    .setProjectName(map.get("PROJECT_NAME") == null ? null
                                            : map.get("PROJECT_NAME")
                                            .toString());
                            pMoneyPage
                                    .setTestMoney(map.get("testMoney") == null ? "0"
                                            : map.get("testMoney").toString());
                            pMoneyPage.setAgMoney(map.get("agMoney") == null ? "0"
                                    : map.get("agMoney").toString());
                            pMoneyPage.setArMoney(map.get("arMoney") == null ? "0"
                                    : map.get("arMoney").toString());

                            list.add(pMoneyPage);
                        }
                        return list;
                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> findMaxSampleNo(final String entrustId) {
        return (List<String>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public List<String> doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        StringBuffer sql = new StringBuffer();
                        sql.append(" select sr.SAMPLE_NO as SAMPLE_NO from SAMPLE_REPORT sr ");
                        sql.append(" where sr.ENTRUST_ID = '");
                        sql.append(entrustId);
                        sql.append("'");

                        Query query = session.createSQLQuery(sql.toString());

                        List<String> smpleNoList = new ArrayList<String>();
                        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                        List l = query.list();
                        String maxSampleNo = "";
                        for (Object obj : l) {

                            Map map = (Map) obj;
                            maxSampleNo = map.get("SAMPLE_NO") == null ? null
                                    : map.get("SAMPLE_NO").toString();
                            smpleNoList.add(maxSampleNo);
                        }
                        return smpleNoList;
                    }
                });
    }

    @Override
    public ProjectTestMoneyPage findProjectTestMoney(final String strProjectId) {
        return (ProjectTestMoneyPage) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public ProjectTestMoneyPage doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        StringBuffer sql = new StringBuffer();
                        sql.append("select testRecord.PROJECT_ID,(case when pInfo.AMOUNT_RECEIVABLE<0 then 0 else pInfo.AMOUNT_RECEIVABLE end) amount,pInfo.PROJECT_NAME,testRecord.testMoney,invoiceRecord.invoiceMoney,receiptRecord.receiptMoney, ");
                        sql.append("raDetailRecord.accountMoney,(testRecord.testMoney-raDetailRecord.accountMoney) as uncollectedMoney ");
                        sql.append("from ");
                        sql.append("(select ei.PROJECT_ID,sum(ei.ACCOUNT_VALUE) as testMoney from ENTRUST_INFO ei ");
                        sql.append("where ei.PROJECT_ID = '");
                        sql.append(strProjectId);
                        sql.append("'");
                        sql.append("and ei.IS_COMPLEMENTALLY='00' and ei.ACCOUNT_KINDS <>'05' group by ei.PROJECT_ID) as testRecord ");
                        sql.append("left join  ");
                        sql.append("(select iDetail.PROJECT_ID,SUM(iDetail.INVOICE_VALUE) as invoiceMoney from INVOICE_DETAILS iDetail ");
                        sql.append("where iDetail.PROJECT_ID = '");
                        sql.append(strProjectId);
                        sql.append("'");
                        sql.append("and iDetail.INVOICE_TYPE in('01','02') group by iDetail.PROJECT_ID) as invoiceRecord ");
                        sql.append("on testRecord.PROJECT_ID = invoiceRecord.PROJECT_ID ");
                        sql.append("left join ");
                        sql.append("(select iDetail.PROJECT_ID,SUM(iDetail.INVOICE_VALUE) as receiptMoney from INVOICE_DETAILS iDetail ");
                        sql.append("where iDetail.PROJECT_ID = '");
                        sql.append(strProjectId);
                        sql.append("'");
                        sql.append("and iDetail.INVOICE_TYPE ='03' group by iDetail.PROJECT_ID) as receiptRecord ");
                        sql.append("on testRecord.PROJECT_ID = receiptRecord.PROJECT_ID ");
                        sql.append("left join ");
                        sql.append("(select raDetail.PROJECT_ID,SUM(raDetail.ACCOUNT_VALUE) as accountMoney from RECEIVABLE_ACCOUNT_DETAILS raDetail ");
                        sql.append("where raDetail.PROJECT_ID = '");
                        sql.append(strProjectId);
                        sql.append("'");
                        sql.append("group by raDetail.PROJECT_ID) as raDetailRecord ");
                        sql.append("on testRecord.PROJECT_ID = raDetailRecord.PROJECT_ID ");
                        sql.append("inner join PROJECT_INFO pInfo on testRecord.PROJECT_ID =pInfo.PROJECT_ID ");

                        Query query = session.createSQLQuery(sql.toString());

                        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                        List l = query.list();
                        ProjectTestMoneyPage ptmPage = new ProjectTestMoneyPage();
                        for (Object obj : l) {

                            Map map = (Map) obj;
                            ptmPage.setProjectId(map.get("PROJECT_ID") == null ? null
                                    : map.get("PROJECT_ID").toString());
                            ptmPage.setProjectName(map.get("PROJECT_NAME") == null ? null
                                    : map.get("PROJECT_NAME").toString());
                            ptmPage.setTestMoney(map.get("testMoney") == null ? "0"
                                    : String.format("%.2f",
                                    map.get("testMoney")).toString());
                            ptmPage.setInvoiceMoney(map.get("invoiceMoney") == null ? "0"
                                    : String.format("%.2f",
                                    map.get("invoiceMoney")).toString());
                            ptmPage.setReceiptMoney(map.get("receiptMoney") == null ? "0"
                                    : String.format("%.2f",
                                    map.get("receiptMoney")).toString());
                            ptmPage.setReceivedMoney(map.get("accountMoney") == null ? "0"
                                    : String.format("%.2f",
                                    map.get("accountMoney")).toString());
                            ptmPage.setUncollectedMoney(map
                                    .get("uncollectedMoney") == null ? "0"
                                    : String.format("%.2f",
                                    map.get("uncollectedMoney"))
                                    .toString());
                            ptmPage.setAmountReceivable(map.get("amount") == null ? null
                                    : map.get("amount").toString());
                        }
                        return ptmPage;
                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProjectTestMoneyDetailPage> findPTMDetailList(
            final String strProjectId) {
        return (List<ProjectTestMoneyDetailPage>) getHibernateTemplate()
                .execute(new HibernateCallback() {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public List<ProjectTestMoneyDetailPage> doInHibernate(
                            Session session) throws HibernateException,
                            SQLException {
                        StringBuffer sql = new StringBuffer();
                        sql.append("select ei.ENTRUST_COMPANY_ID,ec.ENTRUST_COMPANY_NAME,YEAR(ei.ENTRUST_DATE) as eYear,MONTH(ei.ENTRUST_DATE) as eMonth,SUM(ei.ACCOUNT_VALUE) as testMoney ");
                        sql.append("from ENTRUST_INFO ei,ENTRUST_COMPANY ec where ei.IS_COMPLEMENTALLY = '00' and ei.ENTRUST_COMPANY_ID=ec.ENTRUST_COMPANY_ID and ei.ACCOUNT_KINDS <>'05' and ei.PROJECT_ID = '");
                        sql.append(strProjectId);
                        sql.append("'");
                        sql.append("group by ei.ENTRUST_COMPANY_ID,ec.ENTRUST_COMPANY_NAME,YEAR(ei.ENTRUST_DATE),MONTH(ei.ENTRUST_DATE) ");

                        Query query = session.createSQLQuery(sql.toString());

                        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                        List l = query.list();

                        // List<String> ptmContainsList = new
                        // ArrayList<String>();

                        List<ProjectTestMoneyDetailPage> ptmPageList = new ArrayList<ProjectTestMoneyDetailPage>();

                        for (Object obj : l) {
                            boolean isExist = false;
                            List<ProjectTestMoneyYearPage> ptmyPageLidt = new ArrayList<ProjectTestMoneyYearPage>();
                            Map map = (Map) obj;
                            String entrustCompanyId = map
                                    .get("ENTRUST_COMPANY_ID") == null ? ""
                                    : map.get("ENTRUST_COMPANY_ID").toString();
                            String entrustYear = map.get("eYear") == null ? ""
                                    : map.get("eYear").toString();
                            if (ptmPageList != null && ptmPageList.size() > 0) {
                                for (ProjectTestMoneyDetailPage ptmDetail : ptmPageList) {
                                    if (entrustCompanyId.equals(ptmDetail
                                            .getEntrustCompanyId())
                                            && entrustYear.equals(ptmDetail
                                            .getEntrustYear())) {
                                        isExist = true;
                                        ptmyPageLidt = ptmDetail
                                                .getPtmYearPageLidt();
                                    }
                                }
                                if (isExist) {
                                    ProjectTestMoneyYearPage ptmYearPage = new ProjectTestMoneyYearPage();
                                    String entrustMonth = map.get("eMonth") == null ? ""
                                            : map.get("eMonth").toString();
                                    String testMoney = map.get("testMoney") == null ? ""
                                            : String.format("%.2f",
                                            map.get("testMoney"))
                                            .toString();
                                    if ("1".equals(entrustMonth)) {
                                        ptmYearPage.setJanMoney(testMoney);
                                    } else if ("2".equals(entrustMonth)) {
                                        ptmYearPage.setFebMoney(testMoney);
                                    } else if ("3".equals(entrustMonth)) {
                                        ptmYearPage.setMarMoney(testMoney);
                                    } else if ("4".equals(entrustMonth)) {
                                        ptmYearPage.setAprMoney(testMoney);
                                    } else if ("5".equals(entrustMonth)) {
                                        ptmYearPage.setMayMoney(testMoney);
                                    } else if ("6".equals(entrustMonth)) {
                                        ptmYearPage.setJunMoney(testMoney);
                                    } else if ("7".equals(entrustMonth)) {
                                        ptmYearPage.setJulMoney(testMoney);
                                    } else if ("8".equals(entrustMonth)) {
                                        ptmYearPage.setAugMoney(testMoney);
                                    } else if ("9".equals(entrustMonth)) {
                                        ptmYearPage.setSepMoney(testMoney);
                                    } else if ("10".equals(entrustMonth)) {
                                        ptmYearPage.setOctMoney(testMoney);
                                    } else if ("11".equals(entrustMonth)) {
                                        ptmYearPage.setNovMoney(testMoney);
                                    } else if ("12".equals(entrustMonth)) {
                                        ptmYearPage.setDecMoney(testMoney);
                                    }
                                    ptmyPageLidt.add(ptmYearPage);
                                } else {
                                    ProjectTestMoneyDetailPage ptmdPage = new ProjectTestMoneyDetailPage();
                                    ptmdPage.setEntrustCompanyId(map
                                            .get("ENTRUST_COMPANY_ID") == null ? ""
                                            : map.get("ENTRUST_COMPANY_ID")
                                            .toString());
                                    ptmdPage.setEntrustCompanyName(map
                                            .get("ENTRUST_COMPANY_NAME") == null ? ""
                                            : map.get("ENTRUST_COMPANY_NAME")
                                            .toString());
                                    ptmdPage.setEntrustYear(map.get("eYear") == null ? ""
                                            : map.get("eYear").toString());
                                    List<ProjectTestMoneyYearPage> ptmYearPageLidt = ptmdPage
                                            .getPtmYearPageLidt();
                                    ProjectTestMoneyYearPage ptmYearPage = new ProjectTestMoneyYearPage();
                                    String entrustMonth = map.get("eMonth") == null ? ""
                                            : map.get("eMonth").toString();
                                    String testMoney = map.get("testMoney") == null ? ""
                                            : String.format("%.2f",
                                            map.get("testMoney"))
                                            .toString();
                                    if ("1".equals(entrustMonth)) {
                                        ptmYearPage.setJanMoney(testMoney);
                                    } else if ("2".equals(entrustMonth)) {
                                        ptmYearPage.setFebMoney(testMoney);
                                    } else if ("3".equals(entrustMonth)) {
                                        ptmYearPage.setMarMoney(testMoney);
                                    } else if ("4".equals(entrustMonth)) {
                                        ptmYearPage.setAprMoney(testMoney);
                                    } else if ("5".equals(entrustMonth)) {
                                        ptmYearPage.setMayMoney(testMoney);
                                    } else if ("6".equals(entrustMonth)) {
                                        ptmYearPage.setJunMoney(testMoney);
                                    } else if ("7".equals(entrustMonth)) {
                                        ptmYearPage.setJulMoney(testMoney);
                                    } else if ("8".equals(entrustMonth)) {
                                        ptmYearPage.setAugMoney(testMoney);
                                    } else if ("9".equals(entrustMonth)) {
                                        ptmYearPage.setSepMoney(testMoney);
                                    } else if ("10".equals(entrustMonth)) {
                                        ptmYearPage.setOctMoney(testMoney);
                                    } else if ("11".equals(entrustMonth)) {
                                        ptmYearPage.setNovMoney(testMoney);
                                    } else if ("12".equals(entrustMonth)) {
                                        ptmYearPage.setDecMoney(testMoney);
                                    }
                                    ptmYearPageLidt.add(ptmYearPage);
                                    ptmdPage.setPtmYearPageLidt(ptmYearPageLidt);
                                    ptmPageList.add(ptmdPage);
                                }
                            } else {
                                ProjectTestMoneyDetailPage ptmdPage = new ProjectTestMoneyDetailPage();
                                ptmdPage.setEntrustCompanyId(map
                                        .get("ENTRUST_COMPANY_ID") == null ? ""
                                        : map.get("ENTRUST_COMPANY_ID")
                                        .toString());
                                ptmdPage.setEntrustCompanyName(map
                                        .get("ENTRUST_COMPANY_NAME") == null ? ""
                                        : map.get("ENTRUST_COMPANY_NAME")
                                        .toString());
                                ptmdPage.setEntrustYear(map.get("eYear") == null ? ""
                                        : map.get("eYear").toString());
                                List<ProjectTestMoneyYearPage> ptmYearPageLidt = ptmdPage
                                        .getPtmYearPageLidt();
                                ProjectTestMoneyYearPage ptmYearPage = new ProjectTestMoneyYearPage();
                                String entrustMonth = map.get("eMonth") == null ? ""
                                        : map.get("eMonth").toString();
                                String testMoney = map.get("testMoney") == null ? ""
                                        : String.format("%.2f",
                                        map.get("testMoney"))
                                        .toString();
                                if ("1".equals(entrustMonth)) {
                                    ptmYearPage.setJanMoney(testMoney);
                                } else if ("2".equals(entrustMonth)) {
                                    ptmYearPage.setFebMoney(testMoney);
                                } else if ("3".equals(entrustMonth)) {
                                    ptmYearPage.setMarMoney(testMoney);
                                } else if ("4".equals(entrustMonth)) {
                                    ptmYearPage.setAprMoney(testMoney);
                                } else if ("5".equals(entrustMonth)) {
                                    ptmYearPage.setMayMoney(testMoney);
                                } else if ("6".equals(entrustMonth)) {
                                    ptmYearPage.setJunMoney(testMoney);
                                } else if ("7".equals(entrustMonth)) {
                                    ptmYearPage.setJulMoney(testMoney);
                                } else if ("8".equals(entrustMonth)) {
                                    ptmYearPage.setAugMoney(testMoney);
                                } else if ("9".equals(entrustMonth)) {
                                    ptmYearPage.setSepMoney(testMoney);
                                } else if ("10".equals(entrustMonth)) {
                                    ptmYearPage.setOctMoney(testMoney);
                                } else if ("11".equals(entrustMonth)) {
                                    ptmYearPage.setNovMoney(testMoney);
                                } else if ("12".equals(entrustMonth)) {
                                    ptmYearPage.setDecMoney(testMoney);
                                }
                                ptmYearPageLidt.add(ptmYearPage);
                                ptmdPage.setPtmYearPageLidt(ptmYearPageLidt);
                                ptmPageList.add(ptmdPage);
                            }
                        }
                        return ptmPageList;
                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EntrustReportPage> findDepartmentDetailInfo(
            final EntrustReportPage erPage,
            final List<BaseDepartment> wtDepartmentId) {
        return (List<EntrustReportPage>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public List<EntrustReportPage> doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        StringBuffer sql = new StringBuffer();
                        /*
                         * sql.append(
                         * "select distinct ed.ENTRUST_DETAIL_ID,ei.ENTRUST_SN,ei.ENTRUST_DATE,bs.SAMPLE_NAME,ei.INPUTER as registrant, "
                         * ); sql.append(
                         * "su.USER_NAME as registrantName,ei.INPUTE_TIME as registeredTime,ed.INPUTER,sUser.USER_NAME as inputeName, "
                         * ); sql.append(
                         * "ed.INPUTE_TIME,ei.ENTRUST_STATUS,sc.CODE_NAME as entrustStatusName,ei.IS_COMPLEMENTALLY, "
                         * ); sql.append(
                         * "ed.REPORT_NUM ,ei.PRINT_NUM,ei.PROJECT_REMARK,bs.TEST_RESULT,ec.ENTRUST_COMPANY_NAME,pi.PROJECT_NAME "
                         * ); sql.append(
                         * "from ENTRUST_DETAILS ed ,BASE_DEPARTMENT bd ,BASE_SAMPLE bs ,ENTRUST_INFO ei,SYS_USER su,"
                         * ); sql.append(
                         * "SYS_USER sUser,SYS_CODE sc,TEST_REPORT_INFO tri,sample_report sr, ENTRUST_COMPANY ec,PROJECT_INFO pi "
                         * ); sql.append(
                         * "where ed.DEPARTMENT_ID = bd.DEPARTMENT_ID and ed.SAMPLE_ID = bs.SAMPLE_ID and ed.ENTRUST_ID = ei.ENTRUST_ID "
                         * ); sql.append(
                         * "and ei.INPUTER = su.SYS_USER_ID and ed.INPUTER = sUser.SYS_USER_ID and ei.ENTRUST_STATUS = sc.CODE_VALUE and sc.CODE_RELATION = 'ENTRUST_STATUS' "
                         * ); sql.append(
                         * "and ed.ENTRUST_DETAIL_ID = sr.ENTRUST_DETAIL_ID and sr.REPORT_ID = tri.REPORT_ID "
                         * ); sql.append(
                         * "and ei.ENTRUST_COMPANY_ID = ec.ENTRUST_COMPANY_ID and ei.PROJECT_ID = pi.PROJECT_ID "
                         * );
                         */

                        sql.append("  select * from ( select distinct ed.ENTRUST_DETAIL_ID,bs.SAMPLE_NAME, ");
                        sql.append(" ed.INPUTER,sUser.USER_NAME as inputeName, bs.TEST_RESULT,ed.INPUTE_TIME, ");
                        sql.append(" ed.REPORT_NUM ,sr.MOLDING_DATE,sr.PROJECT_PART,ei1.* from ENTRUST_DETAILS ed  ");
                        sql.append(" inner join BASE_SAMPLE bs on ed.SAMPLE_ID = bs.SAMPLE_ID ");
                        sql.append(" inner join SYS_USER sUser on ed.INPUTER = sUser.SYS_USER_ID ");
                        sql.append(" inner  join BASE_DEPARTMENT bd on ed.DEPARTMENT_ID = bd.DEPARTMENT_ID  ");
                        sql.append(" inner join sample_report sr on ed.ENTRUST_DETAIL_ID = sr.ENTRUST_DETAIL_ID ");
                        sql.append(" inner join TEST_REPORT_INFO tri on sr.REPORT_ID = tri.REPORT_ID  ");
                        sql.append(" left join  ");
                        sql.append(" (select ei.ENTRUST_ID,ei.ENTRUST_SN,ei.ENTRUST_DATE, ");
                        sql.append(" ei.INPUTER as registrant, ei.INPUTE_TIME as registeredTime, ");
                        sql.append(" sc.CODE_NAME as entrustStatusName,ei.ENTRUST_STATUS, ");
                        sql.append(" ei.IS_COMPLEMENTALLY, ei.PRINT_NUM,ei.PROJECT_REMARK, ");
                        sql.append(" su.USER_NAME as registrantName,ec.ENTRUST_COMPANY_NAME, ");
                        sql.append(" ed.DEPARTMENT_ID,pi.PROJECT_NAME from ENTRUST_INFO ei  ");
                        sql.append(" INNER JOIN ENTRUST_DETAILS ed ON ed.ENTRUST_ID = ei.ENTRUST_ID ");
                        sql.append(" inner join SYS_USER su on ei.INPUTER = su.SYS_USER_ID ");
                        sql.append(" inner join SYS_CODE sc on ei.ENTRUST_STATUS = sc.CODE_VALUE ");
                        sql.append(" inner join PROJECT_INFO pi on ei.PROJECT_ID = pi.PROJECT_ID   ");
                        sql.append(" inner join ENTRUST_COMPANY ec on ei.ENTRUST_COMPANY_ID = ec.ENTRUST_COMPANY_ID  ");
                        sql.append(" where sc.CODE_RELATION = 'ENTRUST_STATUS'   ");
                        sql.append(" )ei1 on ed.ENTRUST_ID = ei1.ENTRUST_ID ");

                        if ("01".equals(erPage.getMenuStatus())) {
                            sql.append(" and tri.REPORT_STATUS in ('01')");
                        } else if ("02".equals(erPage.getMenuStatus())) {
                            sql.append(" and tri.REPORT_STATUS in ('02','03')");
                        } else if ("03".equals(erPage.getMenuStatus())) {
                            sql.append(" and tri.REPORT_STATUS in ('04','06')");
                        } else if ("04".equals(erPage.getMenuStatus())) {
                            sql.append(" and tri.REPORT_STATUS ='05'");
                        }
                        StringBuffer dep = new StringBuffer();
                        for (BaseDepartment baseDepartment : wtDepartmentId) {
                            dep.append("'" + baseDepartment.getDepartmentId()
                                    + "'" + ",");
                        }
                        if (wtDepartmentId.size() > 0) {
                            sql.append(" and bd.DEPARTMENT_ID  IN(");
                            sql.append(dep.toString().substring(0,
                                    dep.length() - 1));
                            sql.append(")");
                        } else {
                            if (!CommonMethod.isNull(erPage.getDepartmentId())) {
                                sql.append(" and ed.DEPARTMENT_ID = '");
                                sql.append(erPage.getDepartmentId());
                                sql.append("'");
                            }
                        }
                        if (erPage.getSrPageList().size() > 0) {
                            StringBuffer moldingDate = new StringBuffer();
                            StringBuffer projectPart = new StringBuffer();
                            moldingDate.append(" and ");
                            projectPart.append(" and ");
                            for (Object ob : erPage.getSrPageList()) {
                                Map map = new HashMap();
                                map.put("MoldingDate", SampleReportPage.class);
                                JSONObject obj = JSONObject.fromObject(ob);
                                SampleReportPage sample = (SampleReportPage) JSONObject
                                        .toBean(obj, SampleReportPage.class,
                                                map);
                                if (!CommonMethod.isNull(sample
                                        .getMoldingDate())) {
                                    moldingDate.append(" sr.MOLDING_DATE ='");
                                    moldingDate.append(sample.getMoldingDate());
                                    moldingDate.append("'");
                                    moldingDate.append("  or ");
                                }
                                if (!CommonMethod.isNull(sample
                                        .getProjectPart())) {
                                    projectPart
                                            .append(" sr.PROJECT_PART like '%");
                                    projectPart.append(sample.getProjectPart());
                                    projectPart.append("%'");
                                    projectPart.append("  or ");
                                }
                            }

                            sql.append(moldingDate.delete(
                                    moldingDate.length() - 4,
                                    moldingDate.length()).toString());
                            sql.append(projectPart.delete(
                                    projectPart.length() - 4,
                                    projectPart.length()).toString());
                        }

                        /*
                         * if(!CommonMethod.isNull(erPage.getEntrustDate())){
                         * sql.append(" and ei1.ENTRUST_DATE ='");
                         * sql.append(erPage.getEntrustStartDate());
                         * sql.append("'"); }
                         */
                        if (!CommonMethod.isNull(erPage.getEntrustCompanyName())) {
                            sql.append(" and ei1.ENTRUST_COMPANY_NAME like '%");
                            sql.append(erPage.getEntrustCompanyName());
                            sql.append("%'");
                        }
                        if (!CommonMethod.isNull(erPage.getProjectName())) {
                            sql.append(" and ei1.PROJECT_NAME like '%");
                            sql.append(erPage.getProjectName());
                            sql.append("%'");
                        }
                        if (!CommonMethod.isNull(erPage.getEntrustSn())) {
                            sql.append(" and ei1.ENTRUST_SN like '%");
                            sql.append(erPage.getEntrustSn());
                            sql.append("%'");
                        }
                        if (!CommonMethod.isNull(erPage.getSampleName())) {
                            sql.append(" and bs.SAMPLE_NAME like '%");
                            sql.append(erPage.getSampleName());
                            sql.append("%'");
                        }
                        // 委托开始日期
                        if (!CommonMethod.isNull(erPage.getEntrustStartDate())) {
                            sql.append("and ei1.ENTRUST_DATE >='");
                            sql.append(erPage.getEntrustStartDate());
                            sql.append("'");
                        }
                        // 委托结束日期
                        if (!CommonMethod.isNull(erPage.getEntrustEndDate())) {
                            sql.append("and ei1.ENTRUST_DATE <='");
                            sql.append(erPage.getEntrustEndDate());
                            sql.append("'");
                        }
                        // 登记开始日期
                        if (!CommonMethod.isNull(erPage
                                .getRegistrantStartDate())) {
                            sql.append("and ei1.registeredTime >='");
                            sql.append(erPage.getRegistrantStartDate());
                            sql.append("'");
                        }
                        // 登记结束日期
                        if (!CommonMethod.isNull(erPage.getRegistrantEndDate())) {
                            sql.append("and ei1.registeredTime <='");
                            sql.append(erPage.getRegistrantEndDate());
                            sql.append("'");
                        }
                        // 录入开始日期
                        if (!CommonMethod.isNull(erPage.getInputeStartDate())) {
                            sql.append("and ed.INPUTE_TIME >='");
                            sql.append(erPage.getInputeStartDate());
                            sql.append("'");
                        }
                        // 录入结束日期
                        if (!CommonMethod.isNull(erPage.getInputeEndDate())) {
                            sql.append("and ed.INPUTE_TIME <='");
                            sql.append(erPage.getInputeEndDate());
                            sql.append("'");
                        }
                        // 查询补报告信息
                        if (!CommonMethod.isNull(erPage.getIsComplementally())) {
                            sql.append("and ei1.IS_COMPLEMENTALLY ='");
                            sql.append(erPage.getIsComplementally());
                            sql.append("'");
                        }
                        sql.append(" )test  where test.ENTRUST_ID is not null  ");
                        sql.append(" order by test.INPUTE_TIME desc");
                        Query query = session.createSQLQuery(sql.toString());
                        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                        List l = query.list();

                        List<EntrustReportPage> list = new ArrayList<EntrustReportPage>();

                        List<String> listById = new ArrayList<String>();
                        for (Object obj : l) {

                            Map map = (Map) obj;
                            EntrustReportPage erp = new EntrustReportPage();
                            if (map.get("ENTRUST_ID") != null) {
                                // 判断是否有重复报告
                                if (!listById.contains(map
                                        .get("ENTRUST_DETAIL_ID"))) {
                                    String entrustDetailId = map.get(
                                            "ENTRUST_DETAIL_ID").toString();
                                    listById.add(entrustDetailId);
                                    erp.setDepartmentId(map.get("DEPARTMENT_ID") == null ? null
                                            : map.get("DEPARTMENT_ID").toString());
                                    erp.setEntrustDetailId(map
                                            .get("ENTRUST_DETAIL_ID") == null ? null
                                            : map.get("ENTRUST_DETAIL_ID")
                                            .toString());
                                    erp.setEntrustSn(map.get("ENTRUST_SN") == null ? null
                                            : map.get("ENTRUST_SN").toString());
                                    erp.setEntrustDate(map.get("ENTRUST_DATE") == null ? null
                                            : map.get("ENTRUST_DATE")
                                            .toString());
                                    erp.setSampleName(map.get("SAMPLE_NAME") == null ? null
                                            : map.get("SAMPLE_NAME").toString()
                                            + "报告");
                                    // erp.setSampleNo(map.get("SAMPLE_NO")==null?null:map.get("SAMPLE_NO").toString());
                                    erp.setRegistrant(map.get("registrant") == null ? null
                                            : map.get("registrant").toString());
                                    erp.setRegistrantName(map
                                            .get("registrantName") == null ? null
                                            : map.get("registrantName")
                                            .toString());
                                    erp.setRegisteredTime(map
                                            .get("registeredTime") == null ? null
                                            : map.get("registeredTime")
                                            .toString());
                                    erp.setInputer(map.get("INPUTER") == null ? null
                                            : map.get("INPUTER").toString());
                                    erp.setInputeName(map.get("inputeName") == null ? null
                                            : map.get("inputeName").toString());
                                    erp.setInputeTime(map.get("INPUTE_TIME") == null ? null
                                            : map.get("INPUTE_TIME").toString());
                                    erp.setEntrustStatus(map
                                            .get("ENTRUST_STATUS") == null ? null
                                            : map.get("ENTRUST_STATUS")
                                            .toString());
                                    erp.setEntrustStatusName(map
                                            .get("entrustStatusName") == null ? null
                                            : map.get("entrustStatusName")
                                            .toString());
                                    // erp.setTestParameterId(map.get("TEST_PARAMETER_ID")==null?null:map.get("TEST_PARAMETER_ID").toString());
                                    erp.setReportNum(map.get("REPORT_NUM") == null ? 0
                                            : Integer.valueOf(map.get(
                                            "REPORT_NUM").toString()));
                                    // erp.setMoldingDate(map.get("MOLDING_DATE")==null?null:map.get("MOLDING_DATE").toString());
                                    // erp.setSampleAge(map.get("SAMPLE_AGE")==null?0:Integer.valueOf(map.get("SAMPLE_AGE").toString()));
                                    // erp.setProjectPart(map.get("PROJECT_PART")==null?null:map.get("PROJECT_PART").toString());
                                    erp.setPrintNum(map.get("PRINT_NUM") == null ? 0
                                            : Integer.valueOf(map.get(
                                            "PRINT_NUM").toString()));
                                    erp.setTestResult(map.get("TEST_RESULT") == null ? null
                                            : map.get("TEST_RESULT").toString());
                                    erp.setEntrustCompanyName(map
                                            .get("ENTRUST_COMPANY_NAME") == null ? null
                                            : map.get("ENTRUST_COMPANY_NAME")
                                            .toString());
                                    String projectName = map
                                            .get("PROJECT_NAME") == null ? ""
                                            : map.get("PROJECT_NAME")
                                            .toString();
                                    String projectNameRemark = map
                                            .get("PROJECT_REMARK") == null ? ""
                                            : map.get("PROJECT_REMARK")
                                            .toString();
                                    erp.setProjectName(projectName
                                            + projectNameRemark);
                                    erp.setIsComplementally(map
                                            .get("IS_COMPLEMENTALLY") == null ? null
                                            : map.get("IS_COMPLEMENTALLY")
                                            .toString());
                                    list.add(erp);
                                }
                                /*
                                 * List<SampleReportPage> samplist = new
                                 * ArrayList<SampleReportPage>();
                                 * System.out.println
                                 * (map.get("SAMPLE_REPORT_ID"));
                                 * if(map.get("SAMPLE_REPORT_ID") != null &&
                                 * !"".equals(map.get("SAMPLE_REPORT_ID"))){
                                 * SampleReportPage sample = new
                                 * SampleReportPage();
                                 * sample.setSampleReportId(map
                                 * .get("SAMPLE_REPORT_ID"
                                 * )==null?null:map.get("SAMPLE_REPORT_ID"
                                 * ).toString());
                                 * sample.setReportId(map.get("REPORT_ID"
                                 * )==null?
                                 * null:map.get("REPORT_ID").toString());
                                 * sample.
                                 * setEntrustDetailId(map.get("ENTRUST_DETAIL_ID"
                                 * )
                                 * ==null?null:map.get("ENTRUST_DETAIL_ID").toString
                                 * ());
                                 * sample.setSampleNo(map.get("SAMPLE_NO")==
                                 * null?null:map.get("SAMPLE_NO").toString());
                                 * sample
                                 * .setInvalidStatus(map.get("INVALID_STATUS"
                                 * )==null
                                 * ?null:map.get("INVALID_STATUS").toString());
                                 * sample
                                 * .setSampleId(map.get("SAMPLE_ID")==null?
                                 * null:map.get("SAMPLE_ID").toString());
                                 * sample.
                                 * setSampleName(map.get("SAMPLE_NAME")==null
                                 * ?null:map.get("SAMPLE_NAME").toString());
                                 * sample
                                 * .setProjectPart(map.get("PROJECT_PART")==
                                 * null?
                                 * null:map.get("PROJECT_PART").toString());
                                 * sample
                                 * .setMoldingDate(map.get("MOLDING_DATE")==
                                 * null?
                                 * null:map.get("MOLDING_DATE").toString());
                                 * sample
                                 * .setSampleAge(map.get("SAMPLE_AGE")==null
                                 * ?null:map.get("SAMPLE_AGE").toString());
                                 * sample
                                 * .setSampleLevel(map.get("SAMPLE_LEVEL")==
                                 * null?
                                 * null:map.get("SAMPLE_LEVEL").toString());
                                 * sample
                                 * .setSampleType(map.get("SAMPLE_TYPE")==null
                                 * ?null:map.get("SAMPLE_TYPE").toString());
                                 * sample
                                 * .setSampleSource(map.get("SAMPLE_SOURCE"
                                 * )==null
                                 * ?null:map.get("SAMPLE_SOURCE").toString());
                                 * sample
                                 * .setEntrustId(map.get("ENTRUST_ID")==null
                                 * ?null:map.get("ENTRUST_ID").toString());
                                 * sample
                                 * .setSampleState(map.get("SAMPLE_STATE")==
                                 * null?
                                 * null:map.get("SAMPLE_STATE").toString());
                                 * sample
                                 * .setSampleTestDate(map.get("SAMPLE_TEST_DATE"
                                 * )
                                 * ==null?null:map.get("SAMPLE_TEST_DATE").toString
                                 * ());
                                 *
                                 * samplist.add(sample); }
                                 * erp.setSrPageList(samplist); list.add(erp);
                                 */
                            }
                        }
                        return list;
                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EntrustReportPage> findDepartmentDetailInputInfo(
            final EntrustReportPage erPage,
            final List<BaseDepartment> wtDepartmentId) {
        return (List<EntrustReportPage>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public List<EntrustReportPage> doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        StringBuffer sql = new StringBuffer();
                        sql.append(" select distinct ed.ENTRUST_DETAIL_ID,ei.ENTRUST_SN,ei.ENTRUST_DATE,bs.SAMPLE_NAME,ei.INPUTER as registrant, ");
                        sql.append(" su.USER_NAME as registrantName,ei.INPUTE_TIME as registeredTime,ed.INPUTER,sUser.USER_NAME as inputeName, ");
                        sql.append(" ed.INPUTE_TIME,ei.ENTRUST_STATUS,sc.CODE_NAME as entrustStatusName,ei.IS_COMPLEMENTALLY, ");
                        sql.append(" ed.REPORT_NUM ,ei.PRINT_NUM,ei.PROJECT_REMARK,bs.TEST_RESULT,ec.ENTRUST_COMPANY_NAME,pi.PROJECT_NAME,bd.DEPARTMENT_ID ");
                        sql.append(" from ENTRUST_DETAILS ed ");
                        sql.append(" inner join BASE_DEPARTMENT bd on ed.DEPARTMENT_ID = bd.DEPARTMENT_ID");
                        sql.append(" inner join BASE_SAMPLE bs on ed.SAMPLE_ID = bs.SAMPLE_ID");
                        sql.append(" inner join ENTRUST_INFO ei on ed.ENTRUST_ID = ei.ENTRUST_ID");
                        sql.append(" inner join SYS_USER su on ed.INPUTER = su.SYS_USER_ID");
                        sql.append(" inner join SYS_USER sUser on ed.INPUTER = sUser.SYS_USER_ID");
                        sql.append(" inner join SYS_CODE sc on ei.ENTRUST_STATUS = sc.CODE_VALUE");
                        sql.append(" inner join sample_report sr on ed.ENTRUST_DETAIL_ID = sr.ENTRUST_DETAIL_ID");
                        sql.append(" inner join ENTRUST_COMPANY ec on ei.ENTRUST_COMPANY_ID = ec.ENTRUST_COMPANY_ID");
                        sql.append(" inner join PROJECT_INFO pi on ei.PROJECT_ID = pi.PROJECT_ID");
                        sql.append(" left join TEST_REPORT_INFO tri on sr.REPORT_ID = tri.REPORT_ID");
                        sql.append(" where  sc.CODE_RELATION = 'ENTRUST_STATUS' and ei.ENTRUST_STATUS='02' ");
                        if ("01".equals(erPage.getMenuStatus())) {

                            sql.append(" and (sr.REPORT_ID is null or (sr.REPORT_ID is not null and tri.REPORT_STATUS in ('01','03')))");
                            // sql.append(" and tri.REPORT_STATUS in ('01','03')");
                        } else if ("02".equals(erPage.getMenuStatus())) {
                            sql.append(" and (sr.REPORT_ID is null or (sr.REPORT_ID is not null and tri.REPORT_STATUS in ('02','06')))");
                            // sql.append(" and tri.REPORT_STATUS in ('02','06')");
                        } else if ("03".equals(erPage.getMenuStatus())) {
                            sql.append(" and (sr.REPORT_ID is null or (sr.REPORT_ID is not null and tri.REPORT_STATUS ='04'))");
                            // sql.append(" and tri.REPORT_STATUS ='04'");
                        } else if ("04".equals(erPage.getMenuStatus())) {
                            sql.append(" and (sr.REPORT_ID is null or (sr.REPORT_ID is not null and tri.REPORT_STATUS ='05'))");
                            // sql.append(" and tri.REPORT_STATUS ='05'");
                        }
                        StringBuffer dep = new StringBuffer();
                        for (BaseDepartment baseDepartment : wtDepartmentId) {
                            dep.append("'" + baseDepartment.getDepartmentId()
                                    + "'" + ",");
                        }
                        if (wtDepartmentId.size() > 0) {
                            sql.append(" and bd.DEPARTMENT_ID  IN(");
                            sql.append(dep.toString().substring(0,
                                    dep.length() - 1));
                            sql.append(")");
                        } else {
                            if (!CommonMethod.isNull(erPage.getDepartmentId())) {
                                sql.append(" and ed.DEPARTMENT_ID = '");
                                sql.append(erPage.getDepartmentId());
                                sql.append("'");
                            }
                        }

                        if (!CommonMethod.isNull(erPage.getEntrustSn())) {
                            sql.append(" and ei.ENTRUST_SN like '%");
                            sql.append(erPage.getEntrustSn());
                            sql.append("%'");
                        }
                        if (!CommonMethod.isNull(erPage.getSampleName())) {
                            sql.append(" and bs.SAMPLE_NAME like '%");
                            sql.append(erPage.getSampleName());
                            sql.append("%'");
                        }
                        // 委托开始日期
                        if (!CommonMethod.isNull(erPage.getEntrustStartDate())) {
                            sql.append("and ei.ENTRUST_DATE >='");
                            sql.append(erPage.getEntrustStartDate());
                            sql.append("'");
                        }
                        // 委托结束日期
                        if (!CommonMethod.isNull(erPage.getEntrustEndDate())) {
                            sql.append("and ei.ENTRUST_DATE <='");
                            sql.append(erPage.getEntrustEndDate());
                            sql.append("'");
                        }
                        // 登记开始日期
                        if (!CommonMethod.isNull(erPage
                                .getRegistrantStartDate())) {
                            sql.append("and ei.INPUTE_TIME >='");
                            sql.append(erPage.getRegistrantStartDate());
                            sql.append("'");
                        }
                        // 登记结束日期
                        if (!CommonMethod.isNull(erPage.getRegistrantEndDate())) {
                            sql.append("and ei.INPUTE_TIME <='");
                            sql.append(erPage.getRegistrantEndDate());
                            sql.append("'");
                        }
                        // 录入开始日期
                        if (!CommonMethod.isNull(erPage.getInputeStartDate())) {
                            sql.append("and ed.INPUTE_TIME >='");
                            sql.append(erPage.getInputeStartDate());
                            sql.append("'");
                        }
                        // 录入结束日期
                        if (!CommonMethod.isNull(erPage.getInputeEndDate())) {
                            sql.append("and ed.INPUTE_TIME <='");
                            sql.append(erPage.getInputeEndDate());
                            sql.append("'");
                        }
                        // 查询补报告信息
                        if (!CommonMethod.isNull(erPage.getIsComplementally())) {
                            sql.append("and ei.IS_COMPLEMENTALLY ='");
                            sql.append(erPage.getIsComplementally());
                            sql.append("'");
                        }
                        sql.append(" order by ed.INPUTE_TIME desc");
                        Query query = session.createSQLQuery(sql.toString());

                        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                        List l = query.list();

                        List<EntrustReportPage> list = new ArrayList<EntrustReportPage>();

                        for (Object obj : l) {

                            Map map = (Map) obj;
                            EntrustReportPage erp = new EntrustReportPage();
                            erp.setDepartmentId(map.get("DEPARTMENT_ID") == null ? null
                                    : map.get("DEPARTMENT_ID").toString());
                            erp.setEntrustDetailId(map.get("ENTRUST_DETAIL_ID") == null ? null
                                    : map.get("ENTRUST_DETAIL_ID").toString());
                            erp.setEntrustSn(map.get("ENTRUST_SN") == null ? null
                                    : map.get("ENTRUST_SN").toString());
                            erp.setEntrustDate(map.get("ENTRUST_DATE") == null ? null
                                    : map.get("ENTRUST_DATE").toString());
                            erp.setSampleName(map.get("SAMPLE_NAME") == null ? null
                                    : map.get("SAMPLE_NAME").toString() + "报告");
                            // erp.setSampleNo(map.get("SAMPLE_NO")==null?null:map.get("SAMPLE_NO").toString());
                            erp.setRegistrant(map.get("registrant") == null ? null
                                    : map.get("registrant").toString());
                            erp.setRegistrantName(map.get("registrantName") == null ? null
                                    : map.get("registrantName").toString());
                            erp.setRegisteredTime(map.get("registeredTime") == null ? null
                                    : map.get("registeredTime").toString());
                            erp.setInputer(map.get("INPUTER") == null ? null
                                    : map.get("INPUTER").toString());
                            erp.setInputeName(map.get("inputeName") == null ? null
                                    : map.get("inputeName").toString());
                            erp.setInputeTime(map.get("INPUTE_TIME") == null ? null
                                    : map.get("INPUTE_TIME").toString());
                            erp.setEntrustStatus(map.get("ENTRUST_STATUS") == null ? null
                                    : map.get("ENTRUST_STATUS").toString());
                            erp.setEntrustStatusName(map
                                    .get("entrustStatusName") == null ? null
                                    : map.get("entrustStatusName").toString());
                            // erp.setTestParameterId(map.get("TEST_PARAMETER_ID")==null?null:map.get("TEST_PARAMETER_ID").toString());
                            erp.setReportNum(map.get("REPORT_NUM") == null ? 0
                                    : Integer.valueOf(map.get("REPORT_NUM")
                                    .toString()));
                            // erp.setMoldingDate(map.get("MOLDING_DATE")==null?null:map.get("MOLDING_DATE").toString());
                            // erp.setSampleAge(map.get("SAMPLE_AGE")==null?0:Integer.valueOf(map.get("SAMPLE_AGE").toString()));
                            // erp.setProjectPart(map.get("PROJECT_PART")==null?null:map.get("PROJECT_PART").toString());
                            erp.setPrintNum(map.get("PRINT_NUM") == null ? 0
                                    : Integer.valueOf(map.get("PRINT_NUM")
                                    .toString()));
                            erp.setTestResult(map.get("TEST_RESULT") == null ? null
                                    : map.get("TEST_RESULT").toString());
                            erp.setEntrustCompanyName(map
                                    .get("ENTRUST_COMPANY_NAME") == null ? null
                                    : map.get("ENTRUST_COMPANY_NAME")
                                    .toString());
                            String projectName = map.get("PROJECT_NAME") == null ? ""
                                    : map.get("PROJECT_NAME").toString();
                            String projectNameRemark = map
                                    .get("PROJECT_REMARK") == null ? "" : map
                                    .get("PROJECT_REMARK").toString();
                            erp.setProjectName(projectName + projectNameRemark);
                            erp.setIsComplementally(map
                                    .get("IS_COMPLEMENTALLY") == null ? null
                                    : map.get("IS_COMPLEMENTALLY").toString());

                            list.add(erp);
                        }
                        return list;
                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<OutputValueCountPage> findOutputValueCount(
            final String strOutputValueYear) {
        return (List<OutputValueCountPage>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public List<OutputValueCountPage> doInHibernate(
                            Session session) throws HibernateException,
                            SQLException {
                        StringBuffer sql = new StringBuffer();
                        sql.append("select ed.DEPARTMENT_ID,bd.DEPARTMENT_NAME,MONTH(ei.ENTRUST_DATE) as eMonth,SUM(ed.REAL_TOTAL_PRICE) as testMoney ");
                        sql.append("from ENTRUST_MONEY_DETAILS emd ");
                        sql.append("inner join ENTRUST_DETAILS ed on ed.ENTRUST_DETAIL_ID = emd.ENTRUST_DETAIL_ID  ");
                        sql.append("inner join ENTRUST_INFO ei on ed.ENTRUST_ID = ei.ENTRUST_ID ");
                        sql.append("inner join BASE_DEPARTMENT bd on ed.DEPARTMENT_ID = bd.DEPARTMENT_ID ");
                        sql.append("where ei.IS_COMPLEMENTALLY = '00' and YEAR(ei.ENTRUST_DATE) = '");
                        sql.append(strOutputValueYear);
                        sql.append("' ");
                        sql.append("and ei.ACCOUNT_KINDS <>'05' ");
                        sql.append("group by ed.DEPARTMENT_ID,bd.DEPARTMENT_NAME,MONTH(ei.ENTRUST_DATE) ");
                        sql.append("order by ed.DEPARTMENT_ID,MONTH(ei.ENTRUST_DATE) ");

                        Query query = session.createSQLQuery(sql.toString());

                        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                        List l = query.list();

                        List<OutputValueCountPage> ovcPageList = new ArrayList<OutputValueCountPage>();

                        for (Object obj : l) {
                            boolean isExist = false;
                            Map map = (Map) obj;
                            String departmentId = map.get("DEPARTMENT_ID") == null ? ""
                                    : map.get("DEPARTMENT_ID").toString();
                            OutputValueCountPage ovcPage = new OutputValueCountPage();

                            if (ovcPageList != null && ovcPageList.size() > 0) {
                                for (OutputValueCountPage outputValueCountPage : ovcPageList) {
                                    if (departmentId
                                            .equals(outputValueCountPage
                                                    .getDepartmentId())) {
                                        isExist = true;
                                        ovcPage = outputValueCountPage;
                                    }
                                }
                                if (isExist) {
                                    String entrustMonth = map.get("eMonth") == null ? ""
                                            : map.get("eMonth").toString();
                                    String testMoney = map.get("testMoney") == null ? ""
                                            : String.format("%.2f",
                                            map.get("testMoney"))
                                            .toString();
                                    if ("1".equals(entrustMonth)) {
                                        ovcPage.setJanMoney(testMoney);
                                    } else if ("2".equals(entrustMonth)) {
                                        ovcPage.setFebMoney(testMoney);
                                    } else if ("3".equals(entrustMonth)) {
                                        ovcPage.setMarMoney(testMoney);
                                    } else if ("4".equals(entrustMonth)) {
                                        ovcPage.setAprMoney(testMoney);
                                    } else if ("5".equals(entrustMonth)) {
                                        ovcPage.setMayMoney(testMoney);
                                    } else if ("6".equals(entrustMonth)) {
                                        ovcPage.setJunMoney(testMoney);
                                    } else if ("7".equals(entrustMonth)) {
                                        ovcPage.setJulMoney(testMoney);
                                    } else if ("8".equals(entrustMonth)) {
                                        ovcPage.setAugMoney(testMoney);
                                    } else if ("9".equals(entrustMonth)) {
                                        ovcPage.setSepMoney(testMoney);
                                    } else if ("10".equals(entrustMonth)) {
                                        ovcPage.setOctMoney(testMoney);
                                    } else if ("11".equals(entrustMonth)) {
                                        ovcPage.setNovMoney(testMoney);
                                    } else if ("12".equals(entrustMonth)) {
                                        ovcPage.setDecMoney(testMoney);
                                    }
                                } else {
                                    ovcPage.setDepartmentId(map
                                            .get("DEPARTMENT_ID") == null ? ""
                                            : map.get("DEPARTMENT_ID")
                                            .toString());
                                    ovcPage.setDepartmentName(map
                                            .get("DEPARTMENT_NAME") == null ? ""
                                            : map.get("DEPARTMENT_NAME")
                                            .toString());
                                    String entrustMonth = map.get("eMonth") == null ? ""
                                            : map.get("eMonth").toString();
                                    String testMoney = map.get("testMoney") == null ? ""
                                            : String.format("%.2f",
                                            map.get("testMoney"))
                                            .toString();
                                    if ("1".equals(entrustMonth)) {
                                        ovcPage.setJanMoney(testMoney);
                                    } else if ("2".equals(entrustMonth)) {
                                        ovcPage.setFebMoney(testMoney);
                                    } else if ("3".equals(entrustMonth)) {
                                        ovcPage.setMarMoney(testMoney);
                                    } else if ("4".equals(entrustMonth)) {
                                        ovcPage.setAprMoney(testMoney);
                                    } else if ("5".equals(entrustMonth)) {
                                        ovcPage.setMayMoney(testMoney);
                                    } else if ("6".equals(entrustMonth)) {
                                        ovcPage.setJunMoney(testMoney);
                                    } else if ("7".equals(entrustMonth)) {
                                        ovcPage.setJulMoney(testMoney);
                                    } else if ("8".equals(entrustMonth)) {
                                        ovcPage.setAugMoney(testMoney);
                                    } else if ("9".equals(entrustMonth)) {
                                        ovcPage.setSepMoney(testMoney);
                                    } else if ("10".equals(entrustMonth)) {
                                        ovcPage.setOctMoney(testMoney);
                                    } else if ("11".equals(entrustMonth)) {
                                        ovcPage.setNovMoney(testMoney);
                                    } else if ("12".equals(entrustMonth)) {
                                        ovcPage.setDecMoney(testMoney);
                                    }
                                    ovcPageList.add(ovcPage);
                                }
                            } else {
                                ovcPage.setDepartmentId(map
                                        .get("DEPARTMENT_ID") == null ? ""
                                        : map.get("DEPARTMENT_ID").toString());
                                ovcPage.setDepartmentName(map
                                        .get("DEPARTMENT_NAME") == null ? ""
                                        : map.get("DEPARTMENT_NAME").toString());
                                String entrustMonth = map.get("eMonth") == null ? ""
                                        : map.get("eMonth").toString();
                                String testMoney = map.get("testMoney") == null ? ""
                                        : String.format("%.2f",
                                        map.get("testMoney"))
                                        .toString();
                                if ("1".equals(entrustMonth)) {
                                    ovcPage.setJanMoney(testMoney);
                                } else if ("2".equals(entrustMonth)) {
                                    ovcPage.setFebMoney(testMoney);
                                } else if ("3".equals(entrustMonth)) {
                                    ovcPage.setMarMoney(testMoney);
                                } else if ("4".equals(entrustMonth)) {
                                    ovcPage.setAprMoney(testMoney);
                                } else if ("5".equals(entrustMonth)) {
                                    ovcPage.setMayMoney(testMoney);
                                } else if ("6".equals(entrustMonth)) {
                                    ovcPage.setJunMoney(testMoney);
                                } else if ("7".equals(entrustMonth)) {
                                    ovcPage.setJulMoney(testMoney);
                                } else if ("8".equals(entrustMonth)) {
                                    ovcPage.setAugMoney(testMoney);
                                } else if ("9".equals(entrustMonth)) {
                                    ovcPage.setSepMoney(testMoney);
                                } else if ("10".equals(entrustMonth)) {
                                    ovcPage.setOctMoney(testMoney);
                                } else if ("11".equals(entrustMonth)) {
                                    ovcPage.setNovMoney(testMoney);
                                } else if ("12".equals(entrustMonth)) {
                                    ovcPage.setDecMoney(testMoney);
                                }
                                ovcPageList.add(ovcPage);
                            }
                        }
                        return ovcPageList;
                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TestReportInfoPage> findDepartmentReportInfo(
            final Integer pageNo, final Integer pageSize,
            final String wtDepartmentId, final String entrustSn,
            final String entrustCompanyName, final String projectName,
            final String startDate, final String endDate,
            final String inputTime, final String entrustDate,
            final String projectPart, final String sampleAge,
            final String reportStatus, final String reportNo,
            final String inputerName, final String auditorName,
            final String apprvoerName, final String printUserName) {
        return (List<TestReportInfoPage>) getHibernateTemplate().execute(
                new HibernateCallback() {

                    @Override
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        StringBuffer sql = new StringBuffer();
                        sql.append(" SELECT DISTINCT tri.*, bb.ENTRUST_STATUS,bb.IS_COMPLEMENTALLY,sc.CODE_NAME entrustStatusName ,APPROVER.USER_NAME approverName,");
                        sql.append(" AUDITOR.USER_NAME auditorName,INPUTER.USER_NAME inputeName,DISTRIBUTE.USER_NAME distributeName,bb.ENTRUST_SN entrustNumber");
                        sql.append(" FROM TEST_REPORT_INFO tri");
                        sql.append(" left JOIN (select sr.REPORT_ID,ei.ENTRUST_DATE,ei.INPUTE_TIME,pi.PROJECT_NAME,ec.ENTRUST_COMPANY_NAME,bs.DEPARTMENT_ID,ei.ENTRUST_SN,ei.ENTRUST_STATUS,ei.IS_COMPLEMENTALLY from SAMPLE_REPORT sr");
                        sql.append(" LEFT JOIN ENTRUST_INFO ei ON ei.ENTRUST_ID = sr.ENTRUST_ID");
                        sql.append(" LEFT JOIN ENTRUST_COMPANY ec ON ei.ENTRUST_COMPANY_ID = ec.ENTRUST_COMPANY_ID");
                        sql.append(" LEFT JOIN PROJECT_INFO pi ON ei.PROJECT_ID = pi.PROJECT_ID");
                        sql.append(" LEFT JOIN BASE_SAMPLE bs ON sr.SAMPLE_ID = bs.SAMPLE_ID) bb on bb.REPORT_ID =tri.REPORT_ID");
                        sql.append(" LEFT JOIN SYS_CODE sc ON tri.REPORT_STATUS = sc.CODE_VALUE AND sc.CODE_RELATION = 'REPORT_STATUS'");
                        sql.append(" LEFT JOIN SYS_USER APPROVER ON tri.APPROVER = APPROVER.SYS_USER_ID");
                        sql.append(" LEFT JOIN SYS_USER AUDITOR ON tri.AUDITOR = AUDITOR.SYS_USER_ID");
                        sql.append(" left JOIN SYS_USER INPUTER ON tri.APPROVER = INPUTER.SYS_USER_ID");
                        sql.append(" LEFT JOIN SYS_USER DISTRIBUTE  ON tri.DISTRIBUTE = DISTRIBUTE.SYS_USER_ID");
                        sql.append(" where  tri.REPORT_STATUS <> '08'");
                        if (!CommonMethod.isNull(wtDepartmentId)) {
                            sql.append("  and bb.DEPARTMENT_ID = '");
                            sql.append(wtDepartmentId);
                            sql.append("'");
                        }
                        // 按委托编号
                        if (!CommonMethod.isNull(entrustSn)) {
                            sql.append("  and bb.ENTRUST_SN like '%");
                            sql.append(entrustSn);
                            sql.append("%'");
                        }
                        // 按委托单位
                        if (!CommonMethod.isNull(entrustCompanyName)) {
                            sql.append("   and bb.ENTRUST_COMPANY_NAME like '%");
                            sql.append(entrustCompanyName);
                            sql.append("%'");
                        }
                        // 按工程名称
                        if (!CommonMethod.isNull(projectName)) {
                            sql.append("  and bb.PROJECT_NAME  like '%");
                            sql.append(projectName);
                            sql.append("%'");
                        }
                        // 按委托录入时间
                        if (!CommonMethod.isNull(inputTime)) {
                            sql.append("  and bb.INPUTE_TIME  >= '");
                            sql.append(startDate);
                            sql.append("'");
                        }

                        // 按委托时间
                        if (!CommonMethod.isNull(entrustDate)) {
                            sql.append("  and bb.ENTRUST_DATE  >= '");
                            sql.append(startDate);
                            sql.append("'");
                        }
                        // 结束时间
                        if (!CommonMethod.isNull(endDate)) {
                            sql.append("  and bb.ENTRUST_DATE <= '");
                            sql.append(endDate);
                            sql.append("'");
                        }
                        // 按工程部位
                        if (!CommonMethod.isNull(projectPart)) {
                            sql.append("  and bb.PROJECT_PART like '%");
                            sql.append(projectPart);
                            sql.append("%'");
                        }
                        // 按龄期
                        if (!CommonMethod.isNull(sampleAge)) {
                            sql.append("  and bb.SAMPLE_AGE like '%");
                            sql.append(sampleAge);
                            sql.append("%'");
                        }
                        // 按报告状态
                        if (!CommonMethod.isNull(reportStatus)) {
                            sql.append("  and tri.REPORT_STATUS ='");
                            sql.append(reportStatus);
                            sql.append("'");
                        }
                        // 按报告编号
                        if (!CommonMethod.isNull(reportNo)) {
                            sql.append("  and tri.REPORT_NO like '%");
                            sql.append(reportNo);
                            sql.append("%'");
                        }
                        // 按录入人姓名
                        if (!CommonMethod.isNull(inputerName)) {
                            sql.append("  and INPUTER.USER_NAME like '%");
                            sql.append(inputerName);
                            sql.append("%'");
                        }
                        // 按审核人姓名
                        if (!CommonMethod.isNull(auditorName)) {
                            sql.append(" and AUDITOR.USER_NAME like '%  ");
                            sql.append(auditorName);
                            sql.append("%'");
                        }
                        // 按批准人姓名
                        if (!CommonMethod.isNull(apprvoerName)) {
                            sql.append(" and APPROVER.USER_NAME like '% ");
                            sql.append(apprvoerName);
                            sql.append("%'");
                        }
                        // 按打印人姓名
                        if (!CommonMethod.isNull(printUserName)) {
                            sql.append("  and DISTRIBUTE.USER_NAME like '%");
                            sql.append(printUserName);
                            sql.append("%'");
                        }
                        sql.append(" ORDER by tri.INPUTE_TIME desc");
                        Query query = session.createSQLQuery(sql.toString());
                        // int totalCount = Integer.parseInt(query.list().get(0)
                        // .toString());
                        int totalCount = query.list().size();

                        query.setFirstResult((pageNo - 1) * pageSize);
                        query.setMaxResults(pageSize);
                        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                        List l = query.list();
                        List<TestReportInfoPage> list = new ArrayList<TestReportInfoPage>();
                        for (Object obj : l) {
                            Map map = (Map) obj;
                            TestReportInfoPage newCap = getTestReportInfoPage(
                                    map, totalCount);
                            list.add(newCap);
                        }
                        return list;
                    }

                    private TestReportInfoPage getTestReportInfoPage(Map map,
                                                                     int totalCount) {
                        TestReportInfoPage erp = new TestReportInfoPage();

                        erp.setReportStatusName(map.get("entrustStatusName") == null ? null
                                : map.get("entrustStatusName").toString());
                        erp.setApproverName(map.get("approverName") == null ? null
                                : map.get("approverName").toString());

                        erp.setAuditorName(map.get("auditorName") == null ? null
                                : map.get("auditorName").toString());

                        erp.setInputeName(map.get("inputeName") == null ? null
                                : map.get("inputeName").toString());
                        erp.setDistributeName(map.get("distributeName") == null ? null
                                : map.get("distributeName").toString());
                        erp.setTotalCount(totalCount);
                        erp.setReportId(map.get("REPORT_ID") == null ? null
                                : map.get("REPORT_ID").toString());
                        erp.setReportNo(map.get("REPORT_NO") == null ? null
                                : map.get("REPORT_NO").toString());

                        // erp.setEntrustDetailId(map
                        // .get("") == null ? null
                        // : map.get("")
                        // .toString());
                        //
                        erp.setReportDate(map.get("REPORT_DATE") == null ? null
                                : map.get("REPORT_DATE").toString());
                        erp.setReportName(map.get("REPORT_NAME") == null ? null
                                : map.get("REPORT_NAME").toString());
                        erp.setReportPath(map.get("REPORT_PATH") == null ? null
                                : map.get("REPORT_PATH").toString());
                        erp.setReportStatus(map.get("REPORT_STATUS") == null ? null
                                : map.get("REPORT_STATUS").toString());
                        // erp.setReportStatusName(map
                        // .get("") == null ? null
                        // : map.get("")
                        // .toString());
                        erp.setSampleNo(map.get("SAMPLE_NO") == null ? null
                                : map.get("SAMPLE_NO").toString());
                        erp.setProjectParts(map.get("PROJECT_PARTS") == null ? null
                                : map.get("PROJECT_PARTS").toString());
                        erp.setTestResult(map.get("TEST_RESULT") == null ? null
                                : map.get("TEST_RESULT").toString());
                        erp.setInputeTime(map.get("INPUTE_TIME") == null ? null
                                : map.get("INPUTE_TIME").toString());
                        erp.setPrintNum(map.get("PRINT_NUM") == null ? null
                                : Integer.parseInt(map.get("PRINT_NUM")
                                .toString()));
                        erp.setInputer(map.get("INPUTER") == null ? null : map
                                .get("INPUTER").toString());
                        // erp.setInputeName(map
                        // .get("") == null ? null
                        // : map.get("")
                        // .toString());
                        // erp.setUpdateName(map
                        // .get("") == null ? null
                        // : map.get("")
                        // .toString());
                        erp.setUpdateTime(map.get("UPDATE_TIME") == null ? null
                                : map.get("UPDATE_TIME").toString());
                        erp.setAuditor(map.get("AUDITOR") == null ? null : map
                                .get("AUDITOR").toString());
                        erp.setAuditTime(map.get("AUDIT_TIME") == null ? null
                                : map.get("AUDIT_TIME").toString());
                        erp.setApprover(map.get("APPROVER") == null ? null
                                : map.get("APPROVER").toString());

                        erp.setApproveTime(map.get("APPROVE_TIME") == null ? null
                                : map.get("APPROVE_TIME").toString());
                        erp.setDistribute(map.get("DISTRIBUTE") == null ? null
                                : map.get("DISTRIBUTE").toString());
                        // erp.setDistributeName(map
                        // .get("") == null ? null
                        // : map.get("")
                        // .toString());
                        // erp.setTemplateName(map
                        // .get("") == null ? null
                        // : map.get("")
                        // .toString());
                        // erp.setTemplatePath(map
                        // .get("") == null ? null
                        // : map.get("")
                        // .toString());
                        erp.setTwoDInfoUrl(map.get("TWO_D_INFO_URL") == null ? null
                                : map.get("TWO_D_INFO_URL").toString());
                        // erp.setApproverSignPath(map
                        // .get("") == null ? null
                        // : map.get("")
                        // .toString());
                        // erp.setAuditSignPath(map
                        // .get("") == null ? null
                        // : map.get("")
                        // .toString());
                        // erp.setInputerSignPath(map
                        // .get("") == null ? null
                        // : map.get("")
                        // .toString());
                        erp.setTester(map.get("TESTER") == null ? null : map
                                .get("TESTER").toString());
                        // erp.setTesterName(map
                        // .get("") == null ? null
                        // : map.get("")
                        // .toString());
                        // erp.setTesterSignPath(map
                        // .get("") == null ? null
                        // : map.get("REPORT_ID")
                        // .toString());
                        erp.setQcNumber(map.get("QC_NUMBER") == null ? null
                                : map.get("QC_NUMBER").toString());
                        erp.setReportProjectName(map.get("REPORT_PROJECT_NAME") == null ? null
                                : map.get("REPORT_PROJECT_NAME").toString());
                        erp.setReportTestData(map.get("REPORT_TEST_DATA") == null ? null
                                : map.get("REPORT_TEST_DATA").toString());
                        erp.setReportCompanyName(map.get("REPORT_COMPANY_NAME") == null ? null
                                : map.get("REPORT_COMPANY_NAME").toString());
                        erp.setReportRemark(map.get("REPORT_REMARK") == null ? null
                                : map.get("REPORT_REMARK").toString());
                        erp.setTemplateInfoId(map.get("TEMPLATE_INFO_ID") == null ? null
                                : map.get("TEMPLATE_INFO_ID").toString());
                        erp.setReportConclusion(map.get("REPORT_CONCLUSION") == null ? null
                                : map.get("REPORT_CONCLUSION").toString());
                        erp.setUpdater(map.get("UPDATER") == null ? null : map
                                .get("UPDATER").toString());
                        erp.setDistributeTime(map.get("DISTRIBUTE_TIME") == null ? null
                                : map.get("DISTRIBUTE_TIME").toString());
                        erp.setEntrustNumber(map.get("entrustNumber") == null ? null
                                : map.get("entrustNumber").toString());
                        erp.setEntrustStatus(map.get("ENTRUST_STATUS") == null ? null
                                : map.get("ENTRUST_STATUS").toString());
                        erp.setIsComplementally(map.get("IS_COMPLEMENTALLY") == null ? null
                                : map.get("IS_COMPLEMENTALLY").toString());

                        return erp;
                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AgreementPage> findReceivablesStatistics(final Integer pageNo,
                                                         final Integer pageSize, final String accountType,
                                                         final String contractNo, final String entrustCompanyName,
                                                         final String projectName) {
        return (List<AgreementPage>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public List<AgreementPage> doInHibernate(Session session)
                            throws HibernateException, SQLException {

                        StringBuffer sql = new StringBuffer();
                        sql.append(" select ag.CONTRACT_TYPE,ag.CONTRACT_MONEY money,ec.ENTRUST_COMPANY_NAME,ag.CONTRACT_CODE,pi.PROJECT_NAME ,(case when ag.RECEIVED_MONEY = '0' then ag.CONTRACT_MONEY END) receivable  ");
                        sql.append(" ,ei1.iv netReceipts,(case when ag.CONTRACT_MONEY-ei1.iv<='0'   then '0'else ag.CONTRACT_MONEY-ei1.iv end) uncollected  ");
                        sql.append(" from AGREEMENT ag ");
                        sql.append(" left JOIN PROJECT_INFO pi  ON ag.PROJECT_ID = pi.PROJECT_ID ");
                        sql.append(" left JOIn (select distinct ei.CONTRACT_CODE,red.INVOICE_VALUE iv ");
                        sql.append(" from ENTRUST_INFO ei ");
                        sql.append(" left JOIN ENTRUST_DETAILS ed ON ei.ENTRUST_ID = ed.ENTRUST_ID ");
                        sql.append(" left JOIn (select red.ENTRUST_ID, id.INVOICE_VALUE ");
                        sql.append(" from RECEIVABLE_ENTRUST_DETAILS red  ");
                        sql.append(" INNER JOIN RECEIVABLE_ACCOUNT_DETAILS rad on rad.ACCOUNT_DETAIL_ID = red.ACCOUNT_DETAIL_ID  ");
                        sql.append(" left JOIn INVOICE_DETAILS id on id.ENTRUST_COMPANY_ID = rad.ENTRUST_COMPANY_ID) red  on ed.ENTRUST_ID = red.ENTRUST_ID ");
                        sql.append(" ) ei1 on ei1.CONTRACT_CODE = ag.CONTRACT_CODE");
                        sql.append(" left JOIN ENTRUST_COMPANY ec on ag.ENTRUST_COMPANY_ID = ec.ENTRUST_COMPANY_ID  ");
                        sql.append(" where 1=1");
                        // 按合同编号
                        if (!CommonMethod.isNull(contractNo)) {
                            sql.append("  and ag.CONTRACT_CODE like '%");
                            sql.append(contractNo);
                            sql.append("%'");
                        }
                        // 收费类型
                        if (!CommonMethod.isNull(accountType)) {
                            sql.append("  and ag.ACCOUNT_TYPE like '%");
                            sql.append(accountType);
                            sql.append("%'");
                        }
                        // 单位名称
                        if (!CommonMethod.isNull(entrustCompanyName)) {
                            sql.append("  and ec.ENTRUST_COMPANY_NAME like '%");
                            sql.append(entrustCompanyName);
                            sql.append("%'");
                        }
                        // 工程名称
                        if (!CommonMethod.isNull(projectName)) {
                            sql.append("  and pi.PROJECT_NAME  like '%");
                            sql.append(projectName);
                            sql.append("%'");
                        }
                        sql.append(" order by ag.CONTRACT_CODE");
                        Query query = session.createSQLQuery(sql.toString());
                        int totalCount = query.list().size();
                        query.setFirstResult((pageNo - 1) * pageSize);
                        query.setMaxResults(pageSize);
                        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                        List l = query.list();
                        List<AgreementPage> list = new ArrayList<AgreementPage>();
                        for (Object obj : l) {
                            Map map = (Map) obj;
                            AgreementPage newCap = getAgreementPage(map,
                                    totalCount);
                            list.add(newCap);
                        }
                        return list;
                    }

                    private AgreementPage getAgreementPage(Map map,
                                                           int totalCount) {
                        AgreementPage ap = new AgreementPage();
                        ap.setTotalCount(totalCount);
                        ap.setContractCode(map.get("CONTRACT_CODE") == null ? null
                                : map.get("CONTRACT_CODE").toString());
                        ap.setContractType(map.get("CONTRACT_TYPE") == null ? null
                                : map.get("CONTRACT_TYPE").toString());
                        ap.setProjectName(map.get("PROJECT_NAME") == null ? null
                                : map.get("PROJECT_NAME").toString());
                        ap.setEntrustCompanyName(map
                                .get("ENTRUST_COMPANY_NAME") == null ? null
                                : map.get("ENTRUST_COMPANY_NAME").toString());
                        ap.setReceivable(map.get("receivable") == null ? null
                                : map.get("receivable").toString());
                        ap.setNetReceipts(map.get("netReceipts") == null ? null
                                : map.get("netReceipts").toString());
                        ap.setUncollected(map.get("uncollected") == null ? null
                                : map.get("uncollected").toString());
                        ap.setContractPrice(map.get("money") == null ? null
                                : map.get("money").toString());
                        return ap;
                    }
                });
    }

    @SuppressWarnings("unchecked")
    @Override
    // final
    public List<WaterAccountStatisticsPage> entrustWaterAccountStatisticsSql(
            final Integer pageNo, final Integer pageSize,
            final String entrustSn, final String startDate,
            final String endDate, final String entrustDate,
            final String inputTime, final String capitalAccountNo,
            final String inputerName, final String entrustCompanyName,
            final String projectName, final String entrustStatus,
            final String accountKinds, final String testDepartment,
            final String managmentDepartment, final String acceptanceMan,
            final String sampleName, final String accountType) {
        return (List<WaterAccountStatisticsPage>) getHibernateTemplate()
                .execute(new HibernateCallback() {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public List<WaterAccountStatisticsPage> doInHibernate(
                            Session session) throws HibernateException,
                            SQLException {
                        StringBuffer sql = new StringBuffer();
                        /*
                         * sql.append(
                         * " SELECT  bs.SAMPLE_NAME  sp,tp.TEST_PARAMETER_NAME  tpn, ed.REPORT_NUM  rn,ed.REAL_TOTAL_PRICE  rtp1,emd.PRICE  pr,bd.DEPARTMENT_NAME  dn,bb.*,"
                         * ); sql.append(
                         * " CONVERT (VARCHAR,( CASE WHEN CAST ( red.INVOICE_VALUE AS VARCHAR ( 50 )) = 'null' THEN '已收' ELSE '未收' END )) id "
                         * ); sql.append(
                         * " FROM ENTRUST_DETAILS ed LEFT JOIN ( SELECT red.ENTRUST_ID,id.INVOICE_VALUE "
                         * ); sql.append(
                         * " FROM RECEIVABLE_ENTRUST_DETAILS red INNER JOIN RECEIVABLE_ACCOUNT_DETAILS rad ON rad.ACCOUNT_DETAIL_ID = red.ACCOUNT_DETAIL_ID"
                         * ); sql.append(
                         * " LEFT JOIN INVOICE_DETAILS id ON id.ENTRUST_COMPANY_ID = rad.ENTRUST_COMPANY_ID ) red ON ed.ENTRUST_ID = red.ENTRUST_ID "
                         * ); sql.append(
                         * " INNER JOIN (SELECT DISTINCT ei.ENTRUST_ID,ei.REMARK,ei.ENTRUST_SN sn,ei.ENTRUST_STATUS dd,ei.IS_COMPLEMENTALLY ic,ei.ENTRUST_DATE ed,"
                         * ); sql.append(
                         * " ca.CAPITAL_ACCOUNT_CODE  cac,ec.ENTRUST_COMPANY_NAME  ecn,pi.PROJECT_NAME pn, ei.LINK_MAN  lm,ei.LINK_PHONE  lp,"
                         * ); sql.append(
                         * " ei.INSPECTION_MAN  im,sc.CODE_NAME  AT,ei.ACCOUNT_TYPE  mdn,INPUTER.USER_NAME  un,ei.ENTRUST_STATUS,ei.INPUTE_TIME "
                         * ); sql.append(" FROM");
                         * sql.append(" ENTRUST_INFO ei"); sql.append(
                         * " LEFT JOIN ENTRUST_COMPANY ec ON ei.ENTRUST_COMPANY_ID = ec.ENTRUST_COMPANY_ID"
                         * ); sql.append(
                         * " LEFT JOIN PROJECT_INFO pi ON ei.PROJECT_ID = pi.PROJECT_ID"
                         * ); sql.append(
                         * " LEFT JOIN CAPITAL_ACCOUNT ca ON ei.CAPITAL_ACCOUNT_ID = ca.CAPITAL_ACCOUNT_ID"
                         * ); sql.append(
                         * " LEFT JOIN SYS_USER INPUTER ON ei.INPUTER = INPUTER.SYS_USER_ID"
                         * ); sql.append(
                         * " LEFT JOIN BASE_DEPARTMENT db2 ON INPUTER.DEPARTMENT_ID = db2.DEPARTMENT_ID"
                         * ); sql.append(
                         * " LEFT JOIN AGREEMENT ag ON ei.CONTRACT_CODE = ag.CONTRACT_CODE"
                         * ); sql.append(
                         * " LEFT JOIN SYS_CODE sc ON ei.ACCOUNT_KINDS = sc.CODE_VALUE "
                         * ); sql.append(
                         * " AND sc.CODE_RELATION = 'ACCOUNT_KINDS' ) bb ON bb.ENTRUST_ID = ed.ENTRUST_ID"
                         * ); sql.append(
                         * " LEFT JOIN ( SELECT emd.ENTRUST_DETAIL_ID, SUM ( emd.REAL_UNIT_PRICE ) PRICE FROM ENTRUST_MONEY_DETAILS emd GROUP BY emd.ENTRUST_DETAIL_ID ) emd ON emd.ENTRUST_DETAIL_ID = ed.ENTRUST_DETAIL_ID"
                         * ); sql.append(
                         * " LEFT JOIN (SELECT SAMPLE_ID,TEST_PARAMETER_NAME = (Stuff(( SELECT ',' + TEST_PARAMETER_NAME FROM TEST_PARAMETER WHERE SAMPLE_ID = tp.SAMPLE_ID FOR XML PATH ( '' )), 1, 1, '' ) ) "
                         * ); sql.append(
                         * " FROM TEST_PARAMETER tp GROUP BY tp.SAMPLE_ID ) tp ON tp.SAMPLE_ID = ed.SAMPLE_ID"
                         * ); sql.append(
                         * " LEFT JOIN BASE_SAMPLE bs ON ed.SAMPLE_ID = bs.SAMPLE_ID INNER JOIN BASE_DEPARTMENT bd ON ed.DEPARTMENT_ID = bd.DEPARTMENT_ID"
                         * );
                         */
                        sql.append(" SELECT bs.SAMPLE_NAME sp,CONVERT(VARCHAR, tp.TEST_PARAMETER_NAME) tpn,ed.REPORT_NUM rn, ");
                        sql.append(" ed.REAL_TOTAL_PRICE  rtp1,emd.PRICE  pr,bd.DEPARTMENT_NAME dn,bb.*, CONVERT(VARCHAR,(case when  cast(red.INVOICE_VALUE as varchar(50))  = 'null' then '已收' else '未收'end))id  ");
                        sql.append(" FROM   ENTRUST_DETAILS ed  ");
                        sql.append(" left JOIn (select red.ENTRUST_ID,id.INVOICE_VALUE from");
                        sql.append(" RECEIVABLE_ENTRUST_DETAILS red INNER JOIN RECEIVABLE_ACCOUNT_DETAILS rad ");
                        sql.append(" on rad.ACCOUNT_DETAIL_ID = red.ACCOUNT_DETAIL_ID	");
                        sql.append(" left JOIn INVOICE_DETAILS id on id.ENTRUST_COMPANY_ID = rad.ENTRUST_COMPANY_ID	)red");
                        sql.append(" on ed.ENTRUST_ID = red.ENTRUST_ID");
                        sql.append(" INNER JOIN(select distinct ei.ENTRUST_ID,ei.REMARK,ei.ENTRUST_SN  sn,ei.ENTRUST_STATUS  dd,  ei.IS_COMPLEMENTALLY ic,ei.ENTRUST_DATE ed, ca.CAPITAL_ACCOUNT_CODE cac , ec.ENTRUST_COMPANY_NAME ecn, ");
                        sql.append(" pi.PROJECT_NAME pn, ei.LINK_MAN lm, ei.LINK_PHONE lp, ei.INSPECTION_MAN im, ");
                        sql.append("  sc.CODE_NAME  at,ei.ACCOUNT_TYPE mdn,INPUTER.USER_NAME un,ei.ENTRUST_STATUS,ei.INPUTE_TIME");
                        sql.append(" from ENTRUST_INFO ei ");
                        sql.append(" left JOIN ENTRUST_COMPANY ec ON ei.ENTRUST_COMPANY_ID = ec.ENTRUST_COMPANY_ID ");
                        sql.append(" left JOIN PROJECT_INFO pi ON ei.PROJECT_ID = pi.PROJECT_ID ");
                        sql.append(" left JOIN CAPITAL_ACCOUNT ca ON ei.CAPITAL_ACCOUNT_ID = ca.CAPITAL_ACCOUNT_ID ");
                        sql.append(" left JOIN SYS_USER INPUTER ON ei.INPUTER = INPUTER.SYS_USER_ID ");
                        sql.append(" left JOIN BASE_DEPARTMENT db2  ON INPUTER.DEPARTMENT_ID = db2.DEPARTMENT_ID ");
                        sql.append(" left   JOIN AGREEMENT ag on ei.CONTRACT_CODE = ag.CONTRACT_CODE ");
                        sql.append(" left JOIN SYS_CODE sc  ON ei.ACCOUNT_KINDS = sc.CODE_VALUE  AND sc.CODE_RELATION = 'ACCOUNT_KINDS')bb ON bb.ENTRUST_ID = ed.ENTRUST_ID  ");
                        sql.append(" left JOIN (SELECT emd.ENTRUST_DETAIL_ID,Sum(emd.REAL_UNIT_PRICE)PRICE FROM  ENTRUST_MONEY_DETAILS emd  GROUP  BY emd.ENTRUST_DETAIL_ID) emd ON emd.ENTRUST_DETAIL_ID = ed.ENTRUST_DETAIL_ID ");
                        sql.append(" left JOIN (SELECT SAMPLE_ID, TEST_PARAMETER_NAME = ( Stuff((SELECT ',' + TEST_PARAMETER_NAME FROM TEST_PARAMETER  WHERE SAMPLE_ID = tp.SAMPLE_ID  FOR XML PATH('')), 1, 1, '') )  FROM  TEST_PARAMETER tp GROUP  BY tp.SAMPLE_ID) tp ON tp.SAMPLE_ID = ed.SAMPLE_ID ");
                        sql.append(" left JOIN BASE_SAMPLE bs ON ed.SAMPLE_ID = bs.SAMPLE_ID INNER JOIN BASE_DEPARTMENT bd  ON ed.DEPARTMENT_ID = bd.DEPARTMENT_ID ");
                        sql.append(" where 1=1");
                        // 按委托编号
                        if (!CommonMethod.isNull(entrustSn)) {
                            sql.append("  and bb.sn like '%");
                            sql.append(entrustSn);
                            sql.append("%'");
                        }
                        // 按样品名称
                        if (!CommonMethod.isNull(sampleName)) {
                            sql.append("  and bs.SAMPLE_NAME like'%");
                            sql.append(sampleName);
                            sql.append("%'");
                        }
                        // 按工程名称
                        if (!CommonMethod.isNull(projectName)) {
                            sql.append("  and bb.pn like '%");
                            sql.append(projectName);
                            sql.append("%'");
                        }
                        // 按单位名称
                        if (!CommonMethod.isNull(entrustCompanyName)) {
                            sql.append("  and bb.ecn like '%");
                            sql.append(entrustCompanyName);
                            sql.append("%'");
                        }
                        // 按受理人
                        if (!CommonMethod.isNull(acceptanceMan)) {
                            sql.append("  and bb.im like '%");
                            sql.append(acceptanceMan);
                            sql.append("%'");
                        }
                        // 按经营部门
                        if (!CommonMethod.isNull(managmentDepartment)) {
                            sql.append("  and bb.mdn like '%");
                            sql.append(managmentDepartment);
                            sql.append("%'");
                        }
                        // 按资金账号
                        if (!CommonMethod.isNull(capitalAccountNo)) {
                            sql.append("   and bb.cac like '%");
                            sql.append(capitalAccountNo);
                            sql.append("%'");
                        }
                        // 按收费类别
                        if (!CommonMethod.isNull(accountKinds)) {
                            sql.append("  and bb.at like '%");
                            sql.append(accountKinds);
                            sql.append("%'");
                        }
                        // 按委托录入时间
                        if (!CommonMethod.isNull(inputTime)) {
                            sql.append("  and bb.INPUTE_TIME <= '");
                            sql.append(endDate + " 23:59:59");
                            sql.append("'");
                            sql.append("  and bb.INPUTE_TIME >= '");
                            sql.append(startDate + " 00:00:00");
                            sql.append("'");
                        }
                        // 按委托时间
                        if (!CommonMethod.isNull(entrustDate)) {
                            sql.append("  and bb.ed <= '");
                            sql.append(endDate + " 23:59:59");
                            sql.append("'");
                            sql.append("  and bb.ed  >= '");
                            sql.append(startDate + " 00:00:00");
                            sql.append("'");

                        }
                        // 按检测科室
                        if (!CommonMethod.isNull(testDepartment)) {
                            sql.append("  and bd.DEPARTMENT_NAME like '%");
                            sql.append(testDepartment);
                            sql.append("%'");
                        }
                        // 按委托状态
                        if (!CommonMethod.isNull(entrustStatus)) {
                            sql.append("  and bb.ENTRUST_STATUS = '");
                            sql.append(entrustStatus);
                            sql.append("'");
                        }
                        // 按录入人姓名
                        if (!CommonMethod.isNull(inputerName)) {
                            sql.append("  and bb.un like '%");
                            sql.append(inputerName);
                            sql.append("%'");
                        }
                        // 按经营部门
                        if (!CommonMethod.isNull(accountType)) {
                            sql.append("  and bb.mdn like '%");
                            sql.append(accountType);
                            sql.append("%'");
                        }
                        sql.append(" ORDER   BY bb.ed DESC");
                        Query query = session.createSQLQuery(sql.toString());
                        int totalCount = query.list().size();
                        query.setFirstResult((pageNo - 1) * pageSize);
                        query.setMaxResults(pageSize);
                        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                        List l = query.list();
                        List<WaterAccountStatisticsPage> list = new ArrayList<WaterAccountStatisticsPage>();
                        for (Object obj : l) {
                            Map map = (Map) obj;
                            WaterAccountStatisticsPage newCap = getWaterAccountStatisticsPage(
                                    map, totalCount);
                            list.add(newCap);

                        }
                        return list;
                    }

                    private WaterAccountStatisticsPage getWaterAccountStatisticsPage(
                            Map map, int totalCount) {
                        WaterAccountStatisticsPage wa = new WaterAccountStatisticsPage();
                        wa.setTotalCount(totalCount);
                        wa.setEntrustSn(map.get("sn") == null ? null : map.get(
                                "sn").toString());
                        wa.setEntrustDate(map.get("ed") == null ? null : map
                                .get("ed").toString());
                        wa.setCapitalAccountCode(map.get("cac") == null ? null
                                : map.get("cac").toString());
                        wa.setEntrustCompanyName(map.get("ecn") == null ? null
                                : map.get("ecn").toString());
                        wa.setProjectName(map.get("pn") == null ? null : map
                                .get("pn").toString());
                        wa.setSampleName(map.get("sp") == null ? null : map
                                .get("sp").toString());
                        wa.setTestParameterName(map.get("tpn") == null ? null
                                : map.get("tpn").toString());
                        wa.setReportNum(map.get("rn") == null ? null : map.get(
                                "rn").toString());
                        wa.setRealTotalPrice(map.get("rtp1") == null ? null
                                : map.get("rtp1").toString().substring(0, map.get("rtp1").toString().length() - 2));
                        wa.setRealUnitPrice(map.get("pr") == null ? null : map
                                .get("pr").toString().substring(0, map
                                        .get("pr").toString().length() - 2));
                        wa.setReceived(map.get("id") == null ? null : map.get(
                                "id").toString());
                        wa.setLinkman(map.get("lm") == null ? null : map.get(
                                "lm").toString());
                        wa.setLinkPhone(map.get("lp") == null ? null : map.get(
                                "lp").toString());
                        wa.setAcceptanceMan(map.get("im") == null ? null : map
                                .get("im").toString());
                        wa.setAccountType(map.get("at") == null ? null : map
                                .get("at").toString());
                        wa.setManagementDepartmentName(map.get("mdn") == null ? null
                                : map.get("mdn").toString());
                        wa.setDepartmentName(map.get("dn") == null ? null : map
                                .get("dn").toString());
                        wa.setInputName(map.get("un") == null ? null : map.get(
                                "un").toString());
                        wa.setEntrustStatus(map.get("dd") == null ? null : map
                                .get("dd").toString());
                        wa.setIsComplementally(map.get("ic") == null ? null
                                : map.get("ic").toString());
                        wa.setSampleNameRemark(map.get("REMARK") == null ? null
                                : map.get("REMARK").toString());
                        wa.setInputTime(map.get("INPUTE_TIME") == null ? null
                                : map.get("INPUTE_TIME").toString());
                        return wa;
                    }
                });
    }

    @Override
    public PaginationSupport<EntrustInfo> findPageByCriteria(PaginationSupport<EntrustInfo> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<EntrustInfo> findJsonPageByCriteria(JsonPager<EntrustInfo> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<EntrustInfo> findEasyPageByCriteria(EasyPager<EntrustInfo> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}
