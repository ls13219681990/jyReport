package com.dao.page;


/**
 * 用户画面用实体类
 */
public class EntrustParameterInfoPage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String testParameterId;//检测参数ID
    private String testParameterName;//检测参数名称

    public String getTestParameterId() {
        return testParameterId;
    }

    public void setTestParameterId(String testParameterId) {
        this.testParameterId = testParameterId;
    }

    public String getTestParameterName() {
        return testParameterName;
    }

    public void setTestParameterName(String testParameterName) {
        this.testParameterName = testParameterName;
    }
}
