package com.fosu.study.business.controller;


import com.fosu.study.commons.dto.ResponseResult;
import com.fosu.study.provider.api.UmsAdminService;
import com.fosu.study.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户注册.
 * <p>
 * Description
 * </p>
 * @author miki
 * @version v1.0.0
 * @date 2019/08/21
 * @see com.fosu.study.business.controller
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "reg")
public class RegController {

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;



    @GetMapping(value = "/test/{ba}")
    public String test(@PathVariable String ba){
        return umsAdminService.umsTest(ba);
    }
    /**
     * 注册
     * @param umsAdmin {@link UmsAdmin}
     * @return {@link ResponseResult}
     */
    @PostMapping(value = "")
    public ResponseResult<UmsAdmin> reg(@RequestBody UmsAdmin umsAdmin){
        String message = validateReg(umsAdmin);

        //通过验证
        if (message == null) {
            int result = umsAdminService.insert(umsAdmin);
            UmsAdmin admin = umsAdminService.get(umsAdmin.getUsername());


            //注册成功
            if(result > 0 ){
                return new ResponseResult<UmsAdmin>(HttpStatus.OK.value(),"用户注册成功",admin);
            }

            //注册失败  写只写一次 与下面else一致
        //    else{
        //        return new ResponseResult<UmsAdmin>(HttpStatus.NOT_ACCEPTABLE.value(),"用户注册失败");
        //    }
        }

        //没有通过验证
       // else{
       //
       // } 用三元表达式
        return new ResponseResult<UmsAdmin>(HttpStatus.NOT_ACCEPTABLE.value(),message !=null ? message:"用户注册失败");
    }

    /**
     * 注册信息验证
     * @param umsAdmin {@link UmsAdmin}
     * @return 验证结果
     */
    private String validateReg(UmsAdmin umsAdmin){
        UmsAdmin admin = umsAdminService.get(umsAdmin.getUsername());
        if (admin != null) {
            return "用户名已存在，请重新输入";
        }
        return null;
    }
}
