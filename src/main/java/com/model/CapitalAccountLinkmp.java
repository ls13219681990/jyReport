package com.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractCapitalAccount entity provides the base persistence definition of the CapitalAccount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CAPITAL_ACCOUNT_LINKMP")
public class CapitalAccountLinkmp extends BaseBean implements java.io.Serializable {


    // Fields    

    private String capitalAccountLinkmpId;
    private String capitalAccountId;
    private String linkMan;
    private String linkPhone;


    // Constructors

    /**
     * default constructor
     */
    public CapitalAccountLinkmp() {
    }

    /**
     * minimal constructor
     */
    public CapitalAccountLinkmp(String capitalAccountLinkmpId) {
        this.capitalAccountLinkmpId = capitalAccountLinkmpId;
    }

    /**
     * full constructor
     */
    public CapitalAccountLinkmp(String capitalAccountLinkmpId, String capitalAccountId, String linkMan, String linkPhone) {
        this.capitalAccountLinkmpId = capitalAccountLinkmpId;
        this.capitalAccountId = capitalAccountId;
        this.linkMan = linkMan;
        this.linkPhone = linkPhone;
    }


    // Property accessors
    @Id

    @Column(name = "CAPITAL_ACCOUNT_LINKMP_ID", unique = true, nullable = false, length = 32)

    public String getCapitalAccountLinkmpId() {
        return capitalAccountLinkmpId;
    }

    public void setCapitalAccountLinkmpId(String capitalAccountLinkmpId) {
        this.capitalAccountLinkmpId = capitalAccountLinkmpId;
    }

    @Column(name = "CAPITAL_ACCOUNT_ID", length = 32)
    public String getCapitalAccountId() {
        return this.capitalAccountId;
    }

    public void setCapitalAccountId(String capitalAccountId) {
        this.capitalAccountId = capitalAccountId;
    }

    @Column(name = "LINK_MAN", length = 32)

    public String getLinkMan() {
        return this.linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    @Column(name = "LINK_PHONE", length = 13)

    public String getLinkPhone() {
        return this.linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

}