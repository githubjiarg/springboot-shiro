package com.spring.login.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleBean implements Serializable {

    private String roleId;

    private String roleName;


}
