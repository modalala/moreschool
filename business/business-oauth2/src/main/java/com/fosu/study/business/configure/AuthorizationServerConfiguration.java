package com.fosu.study.business.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


/**
 * 配置认证服务器
 * <p>
 * Description:
 * </p>
 * @author miki
 * @version v1.0.0
 * @date 2019/08/22
 * @see com.fosu.study.business.configure
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 注入用于支持 密码模式
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * token 存放在内存 jdbc的话改jdbc
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints
                //用于支持 密码模式 & token 存到内存中
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                //允许客户端访问 /oauth/check_token 检查token
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    /**
     * 配置客户端
     * @param clients 客户端
     * @throws Exception 异常
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                // 使用内存设置
                .inMemory()
                // client_id
                .withClient("client")
                // client_secret
                .secret(passwordEncoder.encode("secret"))
                // 授权类型，密码模式和刷新令牌
                .authorizedGrantTypes("password", "refresh_token")
                // 授权范围
                .scopes("backend")
                // 可以设置对哪些资源有访问权限，不设置则全部资源都可以访问
                .resourceIds("backend-resources")
                // 设置访问令牌的有效期，这里是 1 天
                .accessTokenValiditySeconds(60 * 60 * 24)
                // 设置刷新令牌的有效期，这里是 30 天
                .refreshTokenValiditySeconds(60 * 60 * 24 * 30);
    }
}
