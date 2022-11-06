package com.dhf.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色权限
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
//@Data
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("acl_role_permission")
//@ApiModel(value="RolePermission对象", description="角色权限")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String roleId;

    private String permissionId;

    //    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic(delval = "1", value = "0")
    private Boolean isDeleted;

    //    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    //    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public RolePermission() {
    }

    public RolePermission(String id, String roleId, String permissionId, Boolean isDeleted, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.isDeleted = isDeleted;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }
}
