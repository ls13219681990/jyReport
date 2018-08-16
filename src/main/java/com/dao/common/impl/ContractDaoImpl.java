package com.dao.common.impl;

import com.common.CommonMethod;
import com.controller.common.EasyPager;
import com.controller.common.JsonPager;
import com.controller.common.PaginationSupport;
import com.dao.common.ContractDao;
import com.dao.page.ContractPage;
import com.model.Agreement;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("contractDao")
public class ContractDaoImpl extends BaseDaoImpl<Agreement> implements ContractDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<ContractPage> findContractListSQL(final ContractPage cap, final String strSort) {
        return (List<ContractPage>) getHibernateTemplate().execute(new HibernateCallback() {
            @SuppressWarnings("rawtypes")
            @Override
            public List<ContractPage> doInHibernate(Session session) throws HibernateException {

                StringBuffer querySQLCount = new StringBuffer();
                querySQLCount.append(" select count(distinct ca.CONTRACT_ID) as total ");
                querySQLCount.append(" from AGREEMENT ca inner join ENTRUST_COMPANY ec on ca.ENTRUST_COMPANY_ID = ec.ENTRUST_COMPANY_ID ");
                querySQLCount.append(" inner join PROJECT_INFO project on ca.PROJECT_ID = project.PROJECT_ID");
                querySQLCount.append(" inner join SYS_CODE sc on ca.CONTRACT_TYPE = sc.CODE_VALUE");
                querySQLCount.append(" inner join SYS_CODE as scode on ca.ACCOUNT_TYPE = scode.CODE_VALUE");
                querySQLCount.append(" left join BASE_DEPARTMENT bd on ca.MANAGEMENT_DEPARTMENT_ID = bd.DEPARTMENT_ID ");
                querySQLCount.append(" where sc.CODE_RELATION='CONTRACT_ACCOUNT_TYPE' and scode.CODE_RELATION = 'ACCOUNT_KINDS' ");
                if (cap != null) {
                    //委托单位名称不为空
                    if (!CommonMethod.isNull(cap.getEntrustCompanyName())) {
                        querySQLCount.append("and ec.ENTRUST_COMPANY_NAME like '%");
                        querySQLCount.append(cap.getEntrustCompanyName());
                        querySQLCount.append("%'");
                    }
                    //工程ID不为空
                    if (!CommonMethod.isNull(cap.getProjectId())) {
                        querySQLCount.append("and ca.PROJECT_ID = '");
                        querySQLCount.append(cap.getProjectId());
                        querySQLCount.append("'");
                    }
                    //工程名称不为空
                    if (!CommonMethod.isNull(cap.getProjectName())) {
                        querySQLCount.append("and project.PROJECT_NAME like '%");
                        querySQLCount.append(cap.getProjectName());
                        querySQLCount.append("%'");
                    }
                    //合同号不为空
                    if (!CommonMethod.isNull(cap.getContractCode())) {
                        querySQLCount.append("and ca.CONTRACT_CODE ='");
                        querySQLCount.append(cap.getContractCode());
                        querySQLCount.append("'");
                    }
                    //合同表工程名称不为空
                    if (!CommonMethod.isNull(cap.getProjectNameTag())) {
                        querySQLCount.append("and ca.PROJECT_NAME_TAG like '%");
                        querySQLCount.append(cap.getProjectNameTag());
                        querySQLCount.append("%'");
                    }
                }

                Query queryCount = session.createSQLQuery(querySQLCount.toString());
                int totalCount = Integer.parseInt(queryCount.list().get(0).toString());


                StringBuffer sql = new StringBuffer();
                sql.append(" select ca.CONTRACT_ID,ca.COPIES_COUNT,ca.CONTRACT_CODE,ca.ACCOUNT_TYPE,scode.CODE_NAME at,ca.CONTRACT_TYPE,sc.CODE_NAME ct,ca.CHECK_AREA,ca.PAYMENT_NODE,ca.CONTRACT_CHAPTER, ");
                sql.append(" ca.LINK_MAN,ca.LINK_PHONE,ca.PROJECT_MANAGER,ca.CONTRACT_IS_RETURN,ca.RECEIVED_MONEY,ca.PROJECT_NAME_TAG, ");
                sql.append(" ca.CONTRACT_MONEY,ca.REMARK,ca.INPUTER,ca.INPUTE_TIME,ca.UPDATER,ca.UPDATE_TIME,ec.ENTRUST_COMPANY_ID,ec.ENTRUST_COMPANY_NAME, ");
                sql.append(" project.PROJECT_ID,project.PROJECT_NAME,bd.DEPARTMENT_ID,bd.DEPARTMENT_NAME,sc.CODE_VALUE,sc.CODE_NAME,scode.CODE_NAME as CONTRACT_TYPE_NAME ");
                sql.append(" from AGREEMENT ca inner join ENTRUST_COMPANY ec on ca.ENTRUST_COMPANY_ID = ec.ENTRUST_COMPANY_ID ");
                sql.append(" inner join PROJECT_INFO project on ca.PROJECT_ID = project.PROJECT_ID");
                sql.append(" inner join SYS_CODE sc on ca.CONTRACT_TYPE = sc.CODE_VALUE");
                sql.append(" inner join SYS_CODE as scode on ca.ACCOUNT_TYPE = scode.CODE_VALUE");
                sql.append(" left join BASE_DEPARTMENT bd on ca.MANAGEMENT_DEPARTMENT_ID = bd.DEPARTMENT_ID ");
                sql.append(" where sc.CODE_RELATION='CONTRACT_ACCOUNT_TYPE' and scode.CODE_RELATION = 'ACCOUNT_KINDS' ");
                if (cap != null) {
                    //委托单位ID不为空
                    if (!CommonMethod.isNull(cap.getEntrustCompanyId())) {
                        sql.append("and ca.ENTRUST_COMPANY_ID = '");
                        sql.append(cap.getEntrustCompanyId());
                        sql.append("'");
                    }
                    //委托单位名称不为空
                    if (!CommonMethod.isNull(cap.getEntrustCompanyName())) {
                        sql.append("and ec.ENTRUST_COMPANY_NAME like '%");
                        sql.append(cap.getEntrustCompanyName());
                        sql.append("%'");
                    }
                    //工程ID不为空
                    if (!CommonMethod.isNull(cap.getProjectId())) {
                        sql.append("and ca.PROJECT_ID = '");
                        sql.append(cap.getProjectId());
                        sql.append("'");
                    }
                    //工程名称不为空
                    if (!CommonMethod.isNull(cap.getProjectName())) {
                        sql.append("and project.PROJECT_NAME like '%");
                        sql.append(cap.getProjectName());
                        sql.append("%'");
                    }
                    //合同号不为空
                    if (!CommonMethod.isNull(cap.getContractCode())) {
                        sql.append("and ca.CONTRACT_CODE ='");
                        sql.append(cap.getContractCode());
                        sql.append("'");
                    }

                    //合同表工程名称不为空
                    if (!CommonMethod.isNull(cap.getProjectNameTag())) {
                        sql.append("and ca.PROJECT_NAME_TAG like '%");
                        sql.append(cap.getProjectNameTag());
                        sql.append("%'");
                    }
                }
                if ("0".equals(strSort)) {
                    sql.append(" order by SUBSTRING(ca.CONTRACT_CODE,3,4),SUBSTRING(ca.CONTRACT_CODE,8,len(ca.CONTRACT_CODE)) ");
                } else {
                    sql.append(" order by SUBSTRING(ca.CONTRACT_CODE,3,4) desc,SUBSTRING(ca.CONTRACT_CODE,8,len(ca.CONTRACT_CODE)) desc ");
                }

                Query query = session.createSQLQuery(sql.toString());

                query.setFirstResult((cap.getPageNo() - 1) * cap.getPageSize());
                query.setMaxResults(cap.getPageSize());

                query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                List l = query.list();

                List<ContractPage> list = new ArrayList<ContractPage>();

                for (Object obj : l) {

                    Map map = (Map) obj;
                    ContractPage newCap = new ContractPage();
                    newCap.setTotalCount(totalCount);
                    newCap.setContractId(map.get("CONTRACT_ID") == null ? null : map.get("CONTRACT_ID").toString());
                    newCap.setContractCode(map.get("CONTRACT_CODE") == null ? null : map.get("CONTRACT_CODE").toString());
                    newCap.setEntrustCompanyId(map.get("ENTRUST_COMPANY_ID") == null ? null : map.get("ENTRUST_COMPANY_ID").toString());
                    newCap.setEntrustCompanyName(map.get("ENTRUST_COMPANY_NAME") == null ? null : map.get("ENTRUST_COMPANY_NAME").toString());
                    newCap.setProjectId(map.get("PROJECT_ID") == null ? null : map.get("PROJECT_ID").toString());
                    newCap.setProjectName(map.get("PROJECT_NAME") == null ? null : map.get("PROJECT_NAME").toString());
                    newCap.setCheckArea(map.get("CHECK_AREA") == null ? null : map.get("CHECK_AREA").toString());
//					newCap.setImageProgress(map.get("IMAGE_PROGRESS")==null?null:map.get("IMAGE_PROGRESS").toString());
                    newCap.setPaymentNode(map.get("PAYMENT_NODE") == null ? null : map.get("PAYMENT_NODE").toString());
                    newCap.setContractChapter(map.get("CONTRACT_CHAPTER") == null ? null : map.get("CONTRACT_CHAPTER").toString());
                    newCap.setLinkMan(map.get("LINK_MAN") == null ? null : map.get("LINK_MAN").toString());
                    newCap.setLinkPhone(map.get("LINK_PHONE") == null ? null : map.get("LINK_PHONE").toString());
                    newCap.setProjectManager(map.get("PROJECT_MANAGER") == null ? null : map.get("PROJECT_MANAGER").toString());
                    newCap.setContractIsReturn(map.get("CONTRACT_IS_RETURN") == null ? null : map.get("CONTRACT_IS_RETURN").toString());
                    newCap.setReceivedMoney(map.get("RECEIVED_MONEY") == null ? null : Double.valueOf(map.get("RECEIVED_MONEY").toString()));
                    newCap.setContractMoney(map.get("CONTRACT_MONEY") == null ? null : Double.valueOf(map.get("CONTRACT_MONEY").toString()));
                    newCap.setRemark(map.get("REMARK") == null ? null : map.get("REMARK").toString());
                    newCap.setInputer(map.get("INPUTER") == null ? null : map.get("INPUTER").toString());
                    newCap.setInputeTime(map.get("INPUTE_TIME") == null ? null : map.get("INPUTE_TIME").toString());
                    newCap.setUpdater(map.get("UPDATER") == null ? null : map.get("UPDATER").toString());
                    newCap.setUpdateTime(map.get("UPDATE_TIME") == null ? null : map.get("UPDATE_TIME").toString());
                    newCap.setCopiesCount(map.get("COPIES_COUNT") == null ? null : map.get("COPIES_COUNT").toString());
                    newCap.setProjectNameTag(map.get("PROJECT_NAME_TAG") == null ? null : map.get("PROJECT_NAME_TAG").toString());
                    newCap.setContractType(map.get("CONTRACT_TYPE") == null ? null : map.get("CONTRACT_TYPE").toString());
                    newCap.setContractTypeName(map.get("ct") == null ? null : map.get("ct").toString());
                    newCap.setAccountType(map.get("ACCOUNT_TYPE") == null ? null : map.get("ACCOUNT_TYPE").toString());
                    newCap.setAccountTypeName(map.get("at") == null ? null : map.get("at").toString());
                    newCap.setCopiesCount(map.get("COPIES_COUNT") == null ? null : map.get("COPIES_COUNT").toString());

                    list.add(newCap);
                }

                return list;
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ContractPage> findContractByIdListSQL(final ContractPage cap, final String strSort) {
        return (List<ContractPage>) getHibernateTemplate().execute(new HibernateCallback() {
            @SuppressWarnings("rawtypes")
            @Override
            public List<ContractPage> doInHibernate(Session session) throws HibernateException {
                //查询数据
                StringBuffer sql = new StringBuffer();
                sql.append(" select ca.CONTRACT_ID,ca.CONTRACT_CODE,ca.CHECK_AREA,ca.PAYMENT_NODE,ca.CONTRACT_CHAPTER, ");
                sql.append(" ca.LINK_MAN,ca.LINK_PHONE,ca.PROJECT_MANAGER,ca.CONTRACT_IS_RETURN,ca.CONTRACT_TYPE,ca.RECEIVED_MONEY,ca.COPIES_COUNT,ca.PROJECT_NAME_TAG, ");
                sql.append(" ca.CONTRACT_MONEY,ca.REMARK,ca.INPUTER,ca.INPUTE_TIME,ca.UPDATER,ca.UPDATE_TIME,ec.ENTRUST_COMPANY_ID,ec.ENTRUST_COMPANY_NAME, ");
                sql.append(" project.PROJECT_ID,project.PROJECT_NAME");
                sql.append(" from AGREEMENT ca inner join ENTRUST_COMPANY ec on ca.ENTRUST_COMPANY_ID = ec.ENTRUST_COMPANY_ID ");
                sql.append(" inner join PROJECT_INFO project on ca.PROJECT_ID = project.PROJECT_ID");
                if (cap != null) {
                    //委托单位ID不为空
                    if (!CommonMethod.isNull(cap.getEntrustCompanyId())) {
                        sql.append(" and ca.ENTRUST_COMPANY_ID = '");
                        sql.append(cap.getEntrustCompanyId());
                        sql.append("'");
                    }
                    //委托单位名称不为空
                    if (!CommonMethod.isNull(cap.getEntrustCompanyName())) {
                        sql.append(" and ec.ENTRUST_COMPANY_NAME like '%");
                        sql.append(cap.getEntrustCompanyName());
                        sql.append("%'");
                    }
                    //工程ID不为空
                    if (!CommonMethod.isNull(cap.getProjectId())) {
                        sql.append(" and ca.PROJECT_ID = '");
                        sql.append(cap.getProjectId());
                        sql.append("'");
                    }
                    //工程名称不为空
                    if (!CommonMethod.isNull(cap.getProjectName())) {
                        sql.append(" and project.PROJECT_NAME like '%");
                        sql.append(cap.getProjectName());
                        sql.append("%'");
                    }
                    //合同号不为空
                    if (!CommonMethod.isNull(cap.getContractCode())) {
                        sql.append(" and ca.CONTRACT_CODE ='");
                        sql.append(cap.getContractCode());
                        sql.append("'");
                    }
                }
                Query query = session.createSQLQuery(sql.toString());
                query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                List l = query.list();

                List<ContractPage> list = new ArrayList<ContractPage>();

                for (Object obj : l) {

                    Map map = (Map) obj;
                    ContractPage newCap = new ContractPage();
                    newCap.setContractId(map.get("CONTRACT_ID") == null ? null : map.get("CONTRACT_ID").toString());
                    newCap.setContractCode(map.get("CONTRACT_CODE") == null ? null : map.get("CONTRACT_CODE").toString());
                    newCap.setEntrustCompanyId(map.get("ENTRUST_COMPANY_ID") == null ? null : map.get("ENTRUST_COMPANY_ID").toString());
                    newCap.setEntrustCompanyName(map.get("ENTRUST_COMPANY_NAME") == null ? null : map.get("ENTRUST_COMPANY_NAME").toString());
                    newCap.setProjectId(map.get("PROJECT_ID") == null ? null : map.get("PROJECT_ID").toString());
                    newCap.setProjectName(map.get("PROJECT_NAME") == null ? null : map.get("PROJECT_NAME").toString());
                    newCap.setCheckArea(map.get("CHECK_AREA") == null ? null : map.get("CHECK_AREA").toString());
//					newCap.setImageProgress(map.get("IMAGE_PROGRESS")==null?null:map.get("IMAGE_PROGRESS").toString());
                    newCap.setPaymentNode(map.get("PAYMENT_NODE") == null ? null : map.get("PAYMENT_NODE").toString());
                    newCap.setContractChapter(map.get("CONTRACT_CHAPTER") == null ? null : map.get("CONTRACT_CHAPTER").toString());
                    newCap.setLinkMan(map.get("LINK_MAN") == null ? null : map.get("LINK_MAN").toString());
                    newCap.setLinkPhone(map.get("LINK_PHONE") == null ? null : map.get("LINK_PHONE").toString());
                    newCap.setProjectManager(map.get("PROJECT_MANAGER") == null ? null : map.get("PROJECT_MANAGER").toString());
                    newCap.setContractIsReturn(map.get("CONTRACT_IS_RETURN") == null ? null : map.get("CONTRACT_IS_RETURN").toString());
                    newCap.setManagementDepartmentId(map.get("DEPARTMENT_ID") == null ? null : map.get("DEPARTMENT_ID").toString());
                    newCap.setmDepartmentName(map.get("DEPARTMENT_NAME") == null ? null : map.get("DEPARTMENT_NAME").toString());
                    newCap.setContractType(map.get("CODE_VALUE") == null ? null : map.get("CODE_VALUE").toString());
                    newCap.setContractTypeName(map.get("CODE_NAME") == null ? null : map.get("CODE_NAME").toString());
                    newCap.setAccountType(map.get("CONTRACT_TYPE") == null ? null : map.get("CONTRACT_TYPE").toString());
                    newCap.setAccountTypeName(map.get("CONTRACT_TYPE_NAME") == null ? null : map.get("CONTRACT_TYPE_NAME").toString());
                    newCap.setReceivedMoney(map.get("RECEIVED_MONEY") == null ? null : Double.valueOf(map.get("RECEIVED_MONEY").toString()));
                    newCap.setContractMoney(map.get("CONTRACT_MONEY") == null ? null : Double.valueOf(map.get("CONTRACT_MONEY").toString()));
                    newCap.setRemark(map.get("REMARK") == null ? null : map.get("REMARK").toString());
                    newCap.setInputer(map.get("INPUTER") == null ? null : map.get("INPUTER").toString());
                    newCap.setInputeTime(map.get("INPUTE_TIME") == null ? null : map.get("INPUTE_TIME").toString());
                    newCap.setUpdater(map.get("UPDATER") == null ? null : map.get("UPDATER").toString());
                    newCap.setUpdateTime(map.get("UPDATE_TIME") == null ? null : map.get("UPDATE_TIME").toString());
                    newCap.setCopiesCount(map.get("COPIES_COUNT") == null ? null : map.get("COPIES_COUNT").toString());
                    newCap.setProjectNameTag(map.get("PROJECT_NAME_TAG") == null ? null : map.get("PROJECT_NAME_TAG").toString());
                    list.add(newCap);
                }

                return list;
            }
        });
    }

    @Override
    public PaginationSupport<Agreement> findPageByCriteria(PaginationSupport<Agreement> ps, Order order, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public JsonPager<Agreement> findJsonPageByCriteria(JsonPager<Agreement> jp, DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public EasyPager<Agreement> findEasyPageByCriteria(EasyPager<Agreement> jp, DetachedCriteria detachedCriteria) {
        return null;
    }
}