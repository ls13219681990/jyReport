package com.dao.page;


/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
public class CapitalAccountLinkmpPage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String capitalAccountLinkmpId;
    private String capitalAccountId;
    private String linkMan;
    private String linkPhone;
    private int usageRate;//使用频率


    public String getCapitalAccountLinkmpId() {
        return capitalAccountLinkmpId;
    }

    public void setCapitalAccountLinkmpId(String capitalAccountLinkmpId) {
        this.capitalAccountLinkmpId = capitalAccountLinkmpId;
    }

    public String getCapitalAccountId() {
        return capitalAccountId;
    }

    public void setCapitalAccountId(String capitalAccountId) {
        this.capitalAccountId = capitalAccountId;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public int getUsageRate() {
        return usageRate;
    }

    public void setUsageRate(int usageRate) {
        this.usageRate = usageRate;
    }

}
