package com.fosu.study.business.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * 登录参数
 * <p>
 * Description
 * </p>
 * @author miki
 * @version v1.0.0
 * @date 2019/08/22
 * @see com.fosu.study.business.dto
 *
 */
@Data
public class LoginParam implements Serializable {
    private String username;
    private String password;
}
