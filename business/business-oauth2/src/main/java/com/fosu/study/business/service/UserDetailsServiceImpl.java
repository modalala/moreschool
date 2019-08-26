package com.fosu.study.business.service;

import com.fosu.study.provider.api.UmsAdminService;
import com.fosu.study.provider.domain.UmsAdmin;
import com.google.common.collect.Lists;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 自定义认证与授权
 * @author miki
 * @version v1.0.0
 * @date 2019/08/22
 * @see com.fosu.study.business.service
 *
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
            //默认给每个用户授予User权限
            List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();

            //授权
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
            grantedAuthorities.add(grantedAuthority);

        UmsAdmin umsAdmin = umsAdminService.get(s);

        //账号存在
        if(umsAdmin != null){
            return new User(umsAdmin.getUsername(),umsAdmin.getPassword(),grantedAuthorities);
        }

        //账号不存在
        else{
            return null;
        }

    }
}
