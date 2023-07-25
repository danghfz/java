package com.dhf.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
//@Data
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("acl_user")
//@ApiModel(value="User对象", description="用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    //    @ApiModelProperty(value = "会员id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    //    @ApiModelProperty(value = "微信openid")
    private String username;

    //    @ApiModelProperty(value = "密码")
    private String password;

    //    @ApiModelProperty(value = "昵称")
    private String nickName;

    //    @ApiModelProperty(value = "用户头像")
    private String salt;

    //    @ApiModelProperty(value = "用户签名")
    private String token;

    //    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic(value = "0", delval = "1")
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public User() {
    }

    public User(String id, String username, String password, String nickName, String salt, String token, Boolean isDeleted, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.salt = salt;
        this.token = token;
        this.isDeleted = isDeleted;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }
}
