package com.fosu.study.business.controller;

import com.fosu.study.provider.api.EchoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 测试.
 * <p>
 * Description
 * </p>
 * @author miki
 * @version v1.0.0
 * @date 2019/08/21
 * @see com.fosu.study.business.controller
 *
 */
@RestController
@RequestMapping(value = "echo")
public class EchoController {

    @Reference(version = "1.0.0")
    private EchoService echoService;

    @GetMapping(value = "{string}")
    public String echo(@PathVariable String string){


        return echoService.echo(string);
    }
}
