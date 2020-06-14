package com.spring.login.shiro;

import com.spring.login.model.UserInfoBean;
import com.spring.login.service.UserService;
import com.spring.login.utils.Md5Util;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * 自定义账号,权限验证
 */
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     *  权限认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("------------------ 权限认证 ----------------------");
        return null;
    }

    /**
     *  设置加密机制
     * @param credentialsMatcher
     */
   @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 加密算法类型
        hashedCredentialsMatcher.setHashAlgorithmName(Md5Util.ENCYPT_TYPE);
        // 散列次数
        hashedCredentialsMatcher.setHashIterations(Md5Util.PASSWORD_HASH_NUM);
        super.setCredentialsMatcher(hashedCredentialsMatcher);
    }

    /**
     *  账号认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("----------- 账号认证 -----------");
        // 获取用户名称
        String userName = authenticationToken.getPrincipal().toString();
        // 根据用户名称查询用户信息
        UserInfoBean userInfo = userService.queryUser(userName);
        if ( userInfo != null ) {
            ByteSource byteSource = ByteSource.Util.bytes(userInfo.getSalt());
            return new SimpleAuthenticationInfo(userName,userInfo.getPassword(),byteSource,getName());
        }
        return null;
    }

}
