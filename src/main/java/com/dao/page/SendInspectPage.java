package com.dao.page;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SendInspectPage implements
        java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SendInspectPage() {

    }

    Object sendinspect;

    public Object getSendinspect() {
        return sendinspect;
    }

    public void setSendinspect(Object sendinspect) {
        this.sendinspect = sendinspect;
    }
}
