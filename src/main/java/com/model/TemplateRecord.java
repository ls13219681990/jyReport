package com.model;

import com.common.dao.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractTemplateRecord entity provides the base persistence definition of the TemplateRecord entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "TEMPLATE_RECORD")
public class TemplateRecord extends BaseBean implements java.io.Serializable {


    // Fields    

    private String templateRecordId;
    private String templateName;
    private String templatePath;
    private String templateType;
    private String inputer;
    private String inputTime;


    // Constructors

    /**
     * default constructor
     */
    public TemplateRecord() {
    }

    /**
     * minimal constructor
     */
    public TemplateRecord(String templateRecordId) {
        this.templateRecordId = templateRecordId;
    }

    /**
     * full constructor
     */
    public TemplateRecord(String templateRecordId, String templateName, String templatePath, String templateType, String inputer, String inputTime) {
        this.templateRecordId = templateRecordId;
        this.templateName = templateName;
        this.templatePath = templatePath;
        this.templateType = templateType;
        this.inputer = inputer;
        this.inputTime = inputTime;
    }


    // Property accessors
    @Id

    @Column(name = "TEMPLATE_RECORD_ID", unique = true, nullable = false, length = 32)

    public String getTemplateRecordId() {
        return this.templateRecordId;
    }

    public void setTemplateRecordId(String templateRecordId) {
        this.templateRecordId = templateRecordId;
    }

    @Column(name = "TEMPLATE_NAME", length = 100)

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Column(name = "TEMPLATE_PATH", length = 200)

    public String getTemplatePath() {
        return this.templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    @Column(name = "TEMPLATE_TYPE", length = 2)

    public String getTemplateType() {
        return this.templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    @Column(name = "INPUTER", length = 32)

    public String getInputer() {
        return this.inputer;
    }

    public void setInputer(String inputer) {
        this.inputer = inputer;
    }

    @Column(name = "INPUT_TIME", length = 19)

    public String getInputTime() {
        return this.inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }


}