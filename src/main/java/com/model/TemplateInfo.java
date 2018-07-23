package com.model;

import com.common.dao.BaseBean;

import javax.persistence.*;


/**
 * AbstractTestReportInfo entity provides the base persistence definition of the TestReportInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TEMPLATE_INFO")
public class TemplateInfo extends BaseBean implements java.io.Serializable {


    // Fields    

    private String templateInfoId;
    private String sampleId;
    private String templateName;
    private String templatePath;
    private String snRule;
    private String inputer;
    private String inputeTime;
    private String templateViewName;
    private String updateFlag;

    // Constructors

    /**
     * default constructor
     */
    public TemplateInfo() {
    }

    /**
     * minimal constructor
     */
    public TemplateInfo(String templateInfoId) {
        this.templateInfoId = templateInfoId;
    }

    /**
     * full constructor
     */
    public TemplateInfo(String templateInfoId, String sampleId, String templateName,
                        String templatePath, String reportName, String snRule, String inputer,
                        String inputeTime, String templateViewName, String updateFlag) {
        this.templateInfoId = templateInfoId;
        this.sampleId = sampleId;
        this.templateName = templateName;
        this.templatePath = templatePath;
        this.snRule = snRule;
        this.inputer = inputer;
        this.inputeTime = inputeTime;
        this.templateViewName = templateViewName;
        this.updateFlag = updateFlag;
    }


    // Property accessors
    @Id

    @Column(name = "TEMPLATE_INFO_ID", unique = true, nullable = false, length = 32)

    public String getTemplateInfoId() {
        return this.templateInfoId;
    }

    public void setTemplateInfoId(String templateInfoId) {
        this.templateInfoId = templateInfoId;
    }

    @Column(name = "SAMPLE_ID", length = 32)
    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    @Column(name = "TEMPLATE_NAME", length = 100)
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Column(name = "TEMPLATE_PATH", length = 200)
    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    @Column(name = "SN_RULE", length = 50)
    public String getSnRule() {
        return this.snRule;
    }

    public void setSnRule(String snRule) {
        this.snRule = snRule;
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

    @Column(name = "TEMPLATE_VIEW_NAME", length = 100)
    public String getTemplateViewName() {
        return templateViewName;
    }

    public void setTemplateViewName(String templateViewName) {
        this.templateViewName = templateViewName;
    }

    @Column(name = "UPDATE_FLAG", length = 1)
    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }
}