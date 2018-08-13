package com.dao.page;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * CapitalAccount entity. @author MyEclipse Persistence Tools
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class EntrustInfoPage implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String entrustId;

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }
}
