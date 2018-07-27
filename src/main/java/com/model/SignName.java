package com.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractSignName entity provides the base persistence definition of the SignName entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SIGN_NAME")
public class SignName extends BaseBean implements java.io.Serializable {


    // Fields    

    private String id;
    private String userId;
    private String path;
    private String pathSupply;
    private String signatureName;


    // Constructors

    /**
     * default constructor
     */
    public SignName() {
    }

    /**
     * minimal constructor
     */
    public SignName(String id) {
        this.id = id;
    }

    /**
     * full constructor
     */
    public SignName(String id, String userId, String path, String pathSupply, String signatureName) {
        this.id = id;
        this.userId = userId;
        this.path = path;
        this.pathSupply = pathSupply;
        this.signatureName = signatureName;
    }


    // Property accessors
    @Id

    @Column(name = "ID", unique = true, nullable = false, length = 32)

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "USER_ID", length = 32)

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "PATH", length = 300)

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(name = "PATH_SUPPLY", length = 300)

    public String getPathSupply() {
        return this.pathSupply;
    }

    public void setPathSupply(String pathSupply) {
        this.pathSupply = pathSupply;
    }

    @Column(name = "SIGNATURE_NAME", length = 40)
    public String getSignatureName() {
        return signatureName;
    }

    public void setSignatureName(String signatureName) {
        this.signatureName = signatureName;
    }


}