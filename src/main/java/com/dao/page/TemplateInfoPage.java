package com.dao.page;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TemplateInfoPage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String templateInfoId;//模板信息ID
    private String sampleId;//样品ID
    private String templateName;//模板名称
    private String templatePath;//模板路径
    private String snRule;//报告编号规则
    private String inputer;//录入人
    private String inputeTime;//录入时间
    private String templateViewName;//画面显示的模板名称
    private String updateFlag;//模板更新状态


    public String getTemplateInfoId() {
        return templateInfoId;
    }

    public void setTemplateInfoId(String templateInfoId) {
        this.templateInfoId = templateInfoId;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getSnRule() {
        return snRule;
    }

    public void setSnRule(String snRule) {
        this.snRule = snRule;
    }

    public String getInputer() {
        return inputer;
    }

    public void setInputer(String inputer) {
        this.inputer = inputer;
    }

    public String getInputeTime() {
        return inputeTime;
    }

    public void setInputeTime(String inputeTime) {
        this.inputeTime = inputeTime;
    }

    public String getTemplateViewName() {
        return templateViewName;
    }

    public void setTemplateViewName(String templateViewName) {
        this.templateViewName = templateViewName;
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

}
