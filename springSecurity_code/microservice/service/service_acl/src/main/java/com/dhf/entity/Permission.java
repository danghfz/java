package com.dhf.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
//@Data
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("acl_permission")
//@ApiModel(value="Permission对象", description="权限")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    //    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    //    @ApiModelProperty(value = "所属上级")
    private String pid;

    //    @ApiModelProperty(value = "名称")
    private String name;

    //    @ApiModelProperty(value = "类型(1:菜单,2:按钮)")
    private Integer type;

    //    @ApiModelProperty(value = "权限值")
    private String permissionValue;

    //    @ApiModelProperty(value = "访问路径")
    private String path;

    //    @ApiModelProperty(value = "组件路径")
    private String component;

    //    @ApiModelProperty(value = "图标")
    private String icon;

    //    @ApiModelProperty(value = "状态(0:禁止,1:正常)")
    private Integer status;

    //    @ApiModelProperty(value = "层级")
    @TableField(exist = false)
    private Integer level;

    //    @ApiModelProperty(value = "下级")
    @TableField(exist = false)
    private List<Permission> children;

    //    @ApiModelProperty(value = "是否选中")
    @TableField(exist = false)
    private boolean isSelect;


    //    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic(value = "0", delval = "1")
    private Boolean isDeleted;

    //    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    //    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    public Permission() {
    }

    public Permission(String id, String pid, String name, Integer type, String permissionValue, String path, String component, String icon, Integer status, Integer level, List<Permission> children, boolean isSelect, Boolean isDeleted, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.type = type;
        this.permissionValue = permissionValue;
        this.path = path;
        this.component = component;
        this.icon = icon;
        this.status = status;
        this.level = level;
        this.children = children;
        this.isSelect = isSelect;
        this.isDeleted = isDeleted;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
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
}
