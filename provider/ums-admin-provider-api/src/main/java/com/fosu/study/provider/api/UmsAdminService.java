package com.fosu.study.provider.api;


import com.fosu.study.provider.domain.UmsAdmin;

/**
 * 用户管理服务
 * <p>
 * Description
 * </p>
 * @author miki
 * @version v1.0.0
 * @date 2019/08/21
 * @see com.fosu.study.provider.api
 *
 */
public interface UmsAdminService {

    /**
     * cce测试
     */
    String umsTest(String string);


    /**
     * 新增用户
     * @param umsAdmin {@link UmsAdmin}
     * @return 大于0表示添加成功
     */
    int insert(UmsAdmin umsAdmin);

    /**
     * 获取用户
     * @param username 用户名
     * @return {@link UmsAdmin}
     */
    UmsAdmin get (String username);

    /**
     * 获取用户
     * @param umsAdmin {@link UmsAdmin}
     * @return {@link UmsAdmin}
     */
    UmsAdmin get (UmsAdmin umsAdmin);

}
