package com.fosu.study.provider.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 系统用户
 * <p>
 * Description
 * </p>
 * @author miki
 * @version v1.0.0
 * @date 2019/08/21
 * @see com.fosu.study.provider.domain
 *
 */
@Data
@Table(name = "tb_ums_admin")
public class UmsAdmin  implements Serializable {

    private static final long serialVersionUID = 2260868580778527216L;

    @Id
    private int id;
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    /**
     * 日期相关
     *
     * @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
     * @Column(name = "login_time")
     * private Date loginTime；
     *
     */
}
