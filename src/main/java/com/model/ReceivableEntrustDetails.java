package com.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractReceivableAccountDetails entity provides the base persistence definition of the ReceivableAccountDetails entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RECEIVABLE_ENTRUST_DETAILS")
public class ReceivableEntrustDetails extends BaseBean implements java.io.Serializable {


    // Fields    

    private String receivableEntrustDetailId;
    private String entrustId;
    private String accountDetailId;
    private String invoiceDetailId;
    private String inputer;
    private String inputeTime;


    // Constructors

    /**
     * default constructor
     */
    public ReceivableEntrustDetails() {
    }

    /**
     * minimal constructor
     */
    public ReceivableEntrustDetails(String receivableEntrustDetailId) {
        this.receivableEntrustDetailId = receivableEntrustDetailId;
    }

    /**
     * full constructor
     */
    public ReceivableEntrustDetails(String receivableEntrustDetailId, String entrustId, String accountDetailId, String invoiceDetailId, String inputer, String inputeTime) {
        this.receivableEntrustDetailId = receivableEntrustDetailId;
        this.entrustId = entrustId;
        this.accountDetailId = accountDetailId;
        this.invoiceDetailId = invoiceDetailId;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
    }


    // Property accessors
    @Id

    @Column(name = "RECEIVABLE_ENTRUST_DETAIL_ID", unique = true, nullable = false, length = 32)

    public String getReceivableEntrustDetailId() {
        return this.receivableEntrustDetailId;
    }

    public void setReceivableEntrustDetailId(String receivableEntrustDetailId) {
        this.receivableEntrustDetailId = receivableEntrustDetailId;
    }

    @Column(name = "ENTRUST_ID", length = 32)

    public String getEntrustId() {
        return this.entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    @Column(name = "ACCOUNT_DETAIL_ID", length = 32)

    public String getAccountDetailId() {
        return this.accountDetailId;
    }

    public void setAccountDetailId(String accountDetailId) {
        this.accountDetailId = accountDetailId;
    }

    @Column(name = "INVOICE_DETAIL_ID", length = 32)

    public String getInvoiceDetailId() {
        return this.invoiceDetailId;
    }

    public void setInvoiceDetailId(String invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
    }

    @Column(name = "INPUTER", length = 32)

    public String getInputer() {
        return this.inputer;
    }

    public void setInputer(String inputer) {
        this.inputer = inputer;
    }

    @Column(name = "INPUTE_TIME", length = 19)

    public String getInputeTime() {
        return this.inputeTime;
    }

    public void setInputeTime(String inputeTime) {
        this.inputeTime = inputeTime;
    }
}