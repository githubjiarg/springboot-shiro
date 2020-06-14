package com.spring.login.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 */
@Data
public class UserInfoBean implements Serializable {

    // 用户ID
    private String id;

    // 用户名
    private String userName;

    // 密码
    private String password;

    // 密码盐
    private String salt;

    // 创建时间
    private Date createTime;
}
