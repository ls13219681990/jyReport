package com.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AbstractSysRoleFunction entity provides the base persistence definition of the SysRoleFunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_ROLE_FUNCTION")
public class SysRoleFunction extends BaseBean implements java.io.Serializable {


    // Fields    

    private String roleFunctionId;
    private String roleId;
    private String functionId;


    // Constructors

    /**
     * default constructor
     */
    public SysRoleFunction() {
    }

    /**
     * minimal constructor
     */
    public SysRoleFunction(String roleFunctionId) {
        this.roleFunctionId = roleFunctionId;
    }

    /**
     * full constructor
     */
    public SysRoleFunction(String roleFunctionId, String roleId, String functionId) {
        this.roleFunctionId = roleFunctionId;
        this.roleId = roleId;
        this.functionId = functionId;
    }


    // Property accessors
    @Id

    @Column(name = "role_function_id", unique = true, nullable = false, length = 32)

    public String getRoleFunctionId() {
        return this.roleFunctionId;
    }

    public void setRoleFunctionId(String roleFunctionId) {
        this.roleFunctionId = roleFunctionId;
    }

    @Column(name = "ROLE_ID", length = 32)

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Column(name = "FUNCTION_ID", length = 32)

    public String getFunctionId() {
        return this.functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }


}