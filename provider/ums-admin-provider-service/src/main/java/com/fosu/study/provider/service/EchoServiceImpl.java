package com.fosu.study.provider.service;

import com.fosu.study.provider.api.EchoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * 测试
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
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String string) {
        System.out.println("sdfsdfsdf");
        return "hello dubbo"+string;
    }
}
