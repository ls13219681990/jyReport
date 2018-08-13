package com.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractPaymentType entity provides the base persistence definition of the PaymentType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PAYMENT_TYPE")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PaymentType extends BaseBean implements java.io.Serializable {


    // Fields    

    private String paymentTypeId;
    private String paymentTypeCode;
    private String paymentTypeName;
    private String status;


    // Constructors

    /**
     * default constructor
     */
    public PaymentType() {
    }

    /**
     * minimal constructor
     */
    public PaymentType(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    /**
     * full constructor
     */
    public PaymentType(String paymentTypeId, String paymentTypeCode, String paymentTypeName, String status) {
        this.paymentTypeId = paymentTypeId;
        this.paymentTypeCode = paymentTypeCode;
        this.paymentTypeName = paymentTypeName;
        this.status = status;
    }


    // Property accessors
    @Id

    @Column(name = "PAYMENT_TYPE_ID", unique = true, nullable = false, length = 32)

    public String getPaymentTypeId() {
        return this.paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    @Column(name = "PAYMENT_TYPE_CODE", length = 10)

    public String getPaymentTypeCode() {
        return this.paymentTypeCode;
    }

    public void setPaymentTypeCode(String paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }

    @Column(name = "PAYMENT_TYPE_NAME", length = 30)

    public String getPaymentTypeName() {
        return this.paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    @Column(name = "STATUS", length = 2)

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}