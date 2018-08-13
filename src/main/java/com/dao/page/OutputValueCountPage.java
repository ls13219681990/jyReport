package com.dao.page;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OutputValueCountPage implements java.io.Serializable {

    /**
     * 工程检测费用统计明细
     */
    private static final long serialVersionUID = 1L;

    private String departmentId;//检测科室ID
    private String departmentName;//检测科室名称
    private String janMoney;//一月检测费用
    private String febMoney;//二月检测费用
    private String marMoney;//三月检测费用
    private String aprMoney;//四月检测费用
    private String mayMoney;//五月检测费用
    private String junMoney;//六月检测费用
    private String julMoney;//七月检测费用
    private String augMoney;//八月检测费用
    private String sepMoney;//九月检测费用
    private String octMoney;//十月检测费用
    private String novMoney;//十一月检测费用
    private String decMoney;//十二月检测费用

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getJanMoney() {
        return janMoney;
    }

    public void setJanMoney(String janMoney) {
        this.janMoney = janMoney;
    }

    public String getFebMoney() {
        return febMoney;
    }

    public void setFebMoney(String febMoney) {
        this.febMoney = febMoney;
    }

    public String getMarMoney() {
        return marMoney;
    }

    public void setMarMoney(String marMoney) {
        this.marMoney = marMoney;
    }

    public String getAprMoney() {
        return aprMoney;
    }

    public void setAprMoney(String aprMoney) {
        this.aprMoney = aprMoney;
    }

    public String getMayMoney() {
        return mayMoney;
    }

    public void setMayMoney(String mayMoney) {
        this.mayMoney = mayMoney;
    }

    public String getJunMoney() {
        return junMoney;
    }

    public void setJunMoney(String junMoney) {
        this.junMoney = junMoney;
    }

    public String getJulMoney() {
        return julMoney;
    }

    public void setJulMoney(String julMoney) {
        this.julMoney = julMoney;
    }

    public String getAugMoney() {
        return augMoney;
    }

    public void setAugMoney(String augMoney) {
        this.augMoney = augMoney;
    }

    public String getSepMoney() {
        return sepMoney;
    }

    public void setSepMoney(String sepMoney) {
        this.sepMoney = sepMoney;
    }

    public String getOctMoney() {
        return octMoney;
    }

    public void setOctMoney(String octMoney) {
        this.octMoney = octMoney;
    }

    public String getNovMoney() {
        return novMoney;
    }

    public void setNovMoney(String novMoney) {
        this.novMoney = novMoney;
    }

    public String getDecMoney() {
        return decMoney;
    }

    public void setDecMoney(String decMoney) {
        this.decMoney = decMoney;
    }
}
