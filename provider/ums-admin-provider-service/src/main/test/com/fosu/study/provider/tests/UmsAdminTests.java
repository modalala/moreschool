package com.fosu.study.provider.tests;

import com.fosu.study.provider.api.UmsAdminService;
import com.fosu.study.provider.domain.UmsAdmin;
import com.fosu.study.provider.mapper.UmsAdminMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UmsAdminTests {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Resource
    private UmsAdminService umsAdminService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Test
    public void testSelectAll(){
        List<UmsAdmin> umsAdmin = umsAdminMapper.selectAll();
        umsAdmin.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setUsername("admin");
        umsAdmin.setPassword(bCryptPasswordEncoder.encode("123456"));
        int result = umsAdminService.insert(umsAdmin);
        Assert.assertEquals(result,1);
    }
}
