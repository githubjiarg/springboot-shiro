package com.spring.login.service;

import com.spring.login.mapper.UserMapper;
import com.spring.login.model.UserInfoBean;
import com.spring.login.model.UserRoleBean;
import com.spring.login.utils.Md5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     *  查询用户信息
     * @param userName
     * @return
     */
    public UserInfoBean queryUser(String userName){

        return userMapper.queryUser(userName);
    }

    /**
     *  查询用户角色信息
     * @return
     */
    public List<UserRoleBean> queryUserRoles(String userId){

        return userMapper.queryUserRoles(userId);
    }

    /**
     * 查询角色权限
     * @param roleId
     * @return
     */
    public List<String> queryRoleRemission(String roleId) {

        return userMapper.queryRoleRemission(roleId);
    }

    /**
     *  注册账号
     * @param userInfoBean
     */
    public void register(UserInfoBean userInfoBean){
        userInfoBean.setUserId(UUID.randomUUID().toString().replaceAll("-",""));
        userInfoBean.setSalt(Md5Util.getSal());
        userInfoBean.setPassword(Md5Util.encryptPwd(userInfoBean.getPassword(),userInfoBean.getSalt()));
        userInfoBean.setCreateTime(new Date());
        userMapper.insertUser(userInfoBean);
    }

}
