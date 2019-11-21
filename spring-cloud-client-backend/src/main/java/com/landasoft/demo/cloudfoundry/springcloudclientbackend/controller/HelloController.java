package com.landasoft.demo.cloudfoundry.springcloudclientbackend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @author 伍林云
 * @date 2019/8/5  19:21
 */
@Api(description = "服务端用户管理接口")
@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);


    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    @GetMapping("/")
    @ApiOperation(value = "spring-cloud-client-backend接口信息")
    public String call(){
        logger.info("calling spring-cloud-client-backend");
        return "hello spring-cloud-client-backend "+new Date().toString();
    }

    @GetMapping("/call/{id}")
    @ApiOperation(value = "call返回接口信息")
    public String callHome(@PathVariable String id){
        logger.info("calling spring-cloud-client-backend "+id);
        return "hello"+id;
    }

}
