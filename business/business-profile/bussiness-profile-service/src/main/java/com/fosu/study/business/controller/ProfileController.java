package com.fosu.study.business.controller;

import com.fosu.study.commons.dto.ResponseResult;
import com.fosu.study.provider.api.UmsAdminService;
import com.fosu.study.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 个人信息管理
 * <p>
 * Description
 * </p>
 * @author miki
 * @version v1.0.0
 * @date 2019/08/24
 * @see com.fosu.study.business.controller
 *
 */
@RestController
@RequestMapping(value = "profile")
public class ProfileController {

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    @GetMapping(value = "info/{username}")
    public ResponseResult<UmsAdmin> info(@PathVariable String username){
        UmsAdmin umsAdmin = umsAdminService.get(username);
        return new ResponseResult<UmsAdmin>(ResponseResult.CodeStatus.OK,"查询用户信息",umsAdmin);

    }
}
