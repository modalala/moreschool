package com.fosu.study.business.controller;

import com.fosu.study.business.dto.LoginInfo;
import com.fosu.study.business.dto.LoginParam;
import com.fosu.study.business.feign.ProfileFeign;
import com.fosu.study.commons.dto.ResponseResult;
import com.fosu.study.commons.utils.MapperUtils;
import com.fosu.study.commons.utils.OkHttpClientUtil;
import com.fosu.study.provider.domain.UmsAdmin;
import com.google.common.collect.Maps;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;


/**
 * 登录管理
 * <p>
 * Description
 * </p>
 * @author miki
 * @version v1.0.0
 * @date 2019/08/22
 * @see com.fosu.study.business.controller
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoginController<result> {

    private static final String URL_OAUTH_TOKEN = "http://localhost:9001/oauth/token";

    @Value(value = "${business.oauth2.grant_type}")
    public String oauth2GrantType;

    @Value(value = "${business.oauth2.client_id}")
    public String oauth2ClientId;

    @Value(value = "${business.oauth2.client_secret}")
    public String oauth2ClientSecret;

    @Resource(name = "userDetailsServiceBean")
    private UserDetailsService userDetailsService;

    @Resource
    private TokenStore tokenStore;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private ProfileFeign profileFeign;
    /**
     *登录服务
     */
    @PostMapping(value = "/user/login")
    public ResponseResult<Map<String,Object>> login(@RequestBody LoginParam loginParam){

        //封装返回的参数
        Map<String,Object> result = Maps.newHashMap();

        //验证账号和密码
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());

        if(userDetails == null || !bCryptPasswordEncoder.matches(loginParam.getPassword(),userDetails.getPassword())){
            return new ResponseResult<Map<String, Object>>(ResponseResult.CodeStatus.FAIL,"账号或密码错误",null);
        }

        Map<String,String> params = Maps.newHashMap();
        params.put("username", loginParam.getUsername());
        params.put("password", loginParam.getPassword());
        params.put("grant_type", oauth2GrantType);
        params.put("client_id", oauth2ClientId);
        params.put("client_secret", oauth2ClientSecret);


        try {
            Response response = OkHttpClientUtil.getInstance().postData(URL_OAUTH_TOKEN, params);
            String jsonString = Objects.requireNonNull(response.body()).string();
            Map<String, Object> jsonMap = MapperUtils.json2map(jsonString);
            String token = String.valueOf(jsonMap.get("access_token"));

            result.put("token",token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseResult<Map<String,Object>>(ResponseResult.CodeStatus.OK,"登录成功",result);
    }


    /**
     * 获取用户信息服务
     * @return
     */
    @GetMapping(value = "/user/info")
    public ResponseResult<LoginInfo> info() throws Exception {

        //获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String jsonString = profileFeign.info(authentication.getName());
        UmsAdmin umsAdmin = MapperUtils.json2pojoByTree(jsonString, "username", UmsAdmin.class);
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setName(authentication.getName());
        return new ResponseResult<LoginInfo>(ResponseResult.CodeStatus.OK,"获取用户信息",loginInfo);

    }

    /**
     * 注销
     * @return
     */
    @PostMapping(value = "/user/logout")
    public ResponseResult<Void> logout(HttpServletRequest request){
        String token = request.getParameter("access_token");
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);

        tokenStore.removeAccessToken(oAuth2AccessToken);

        return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"用户注销",null);
    }
}
