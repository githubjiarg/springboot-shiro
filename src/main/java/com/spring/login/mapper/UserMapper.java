package com.spring.login.mapper;

import com.spring.login.model.UserInfoBean;
import com.spring.login.model.UserRoleBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     *  查询用户信息
     * @param userName
     * @return
     */
    UserInfoBean queryUser(String userName);

    /**
     *  查询用户角色
     * @return
     */
    List<UserRoleBean> queryUserRoles(String userId);

    /**
     * 查询角色权限
     * @return
     */
    List<String> queryRoleRemission(String roleId);

    /**
     *  新增用户信息
     * @param userInfoBean
     */
    void insertUser(UserInfoBean userInfoBean);

}
