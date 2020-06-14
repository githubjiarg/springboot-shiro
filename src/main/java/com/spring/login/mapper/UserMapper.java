package com.spring.login.mapper;

import com.spring.login.model.UserInfoBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     *  查询用户信息
     * @param userName
     * @return
     */
    UserInfoBean queryUser(String userName);

    /**
     *  新增用户信息
     * @param userInfoBean
     */
    void insertUser(UserInfoBean userInfoBean);

}
