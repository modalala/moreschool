package com.fosu.study.provider.service;


import com.fosu.study.provider.api.UmsAdminService;
import com.fosu.study.provider.domain.UmsAdmin;
import com.fosu.study.provider.mapper.UmsAdminMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * 用户管理服务
 * <p>
 * Description
 * </p>
 * @author miki
 * @version v1.0.0
 * @date 2019/08/21
 * @see com.fosu.study.provider.service
 *
 */
@Service(version = "1.0.0")
public class UmsServiceImpl implements UmsAdminService {

    /** @autowide 线程不安全*/
    @Resource
    private UmsAdminMapper umsAdminMapper;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public String umsTest(String string) {
        return string+"dubbo";
    }


    @Override
    public int insert(UmsAdmin umsAdmin) {

        //初始化用户对象
        initUmsAdmin(umsAdmin);

        System.out.println(umsAdmin.getUsername());
        System.out.println(umsAdmin.getPassword());
        return umsAdminMapper.insert(umsAdmin);
    }


    @Override
    public UmsAdmin get(String username) {

     //   UmsAdmin umsAdmin = new UmsAdmin();
     //   umsAdmin.setUsername(username);

        //初始化用户对象
     //   initUmsAdmin(umsAdmin);
        Example example = new Example(UmsAdmin.class);
        example.createCriteria().andEqualTo("username",username);
        return umsAdminMapper.selectOneByExample(example);
    }

    @Override
    public UmsAdmin get(UmsAdmin umsAdmin) {

        //初始化用户对象
      //  initUmsAdmin(umsAdmin);

        Example example = new Example(UmsAdmin.class);
        example.createCriteria().andEqualTo("username",umsAdmin.getUsername());

        return umsAdminMapper.selectOne(umsAdmin);
    }

    /**
     * 初始化用户对象
     * @param umsAdmin {@link UmsAdmin}
     */
    private void initUmsAdmin(UmsAdmin umsAdmin){
        //初始化时间
        //umsAdmin.setCreateTime(new Date());
        //umsAdmin.setLoginTime(new Date());

        //初始化状态
        /*
        if(umsAdmin.getStatus() == null ){
        umsAdmin.setStatus(0);
         */

        //密码加密
        umsAdmin.setPassword(bCryptPasswordEncoder.encode(umsAdmin.getPassword()));
    }
}
