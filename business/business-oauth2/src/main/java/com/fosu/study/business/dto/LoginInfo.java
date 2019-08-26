package com.fosu.study.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录信息
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
public class LoginInfo implements Serializable {
    private String name;
    private String avatar;
}
