package com.model;

import com.common.dao.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractReceivableInvoiceDetails entity provides the base persistence definition of the ReceivableInvoiceDetails entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RECEIVABLE_INVOICE_DETAILS")
public class ReceivableInvoiceDetails extends BaseBean implements java.io.Serializable {


    // Fields    

    private String receivableInvoiceDetailId;
    private String invoiceDetailId;
    private String accountDetailId;


    // Constructors

    /**
     * default constructor
     */
    public ReceivableInvoiceDetails() {
    }

    /**
     * minimal constructor
     */
    public ReceivableInvoiceDetails(String receivableInvoiceDetailId) {
        this.receivableInvoiceDetailId = receivableInvoiceDetailId;
    }

    /**
     * full constructor
     */
    public ReceivableInvoiceDetails(String receivableInvoiceDetailId, String invoiceDetailId, String accountDetailId) {
        this.receivableInvoiceDetailId = receivableInvoiceDetailId;
        this.invoiceDetailId = invoiceDetailId;
        this.accountDetailId = accountDetailId;
    }


    // Property accessors
    @Id

    @Column(name = "RECEIVABLE_INVOICE_DETAIL_ID", unique = true, nullable = false, length = 32)

    public String getReceivableInvoiceDetailId() {
        return this.receivableInvoiceDetailId;
    }

    public void setReceivableInvoiceDetailId(String receivableInvoiceDetailId) {
        this.receivableInvoiceDetailId = receivableInvoiceDetailId;
    }

    @Column(name = "INVOICE_DETAIL_ID", length = 32)

    public String getInvoiceDetailId() {
        return this.invoiceDetailId;
    }

    public void setInvoiceDetailId(String invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
    }

    @Column(name = "ACCOUNT_DETAIL_ID", length = 32)

    public String getAccountDetailId() {
        return this.accountDetailId;
    }

    public void setAccountDetailId(String accountDetailId) {
        this.accountDetailId = accountDetailId;
    }


}