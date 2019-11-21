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
    @ApiOperation(value = "hello接口信息")
    public String call(@PathVariable String id){
        logger.info("calling hello demo backend");
        return "hello"+new Date().toString();
    }

    @GetMapping("/call/{id}")
    @ApiOperation(value = "call返回接口信息")
    public String callHome(@PathVariable String id){
        logger.info("calling trace demo backend");
        return "hello"+id;
    }
    @GetMapping("/call1/{id}")
    @ApiOperation(value = "call返回其它服务接口信息")
    public String callSpringBootDocker(@PathVariable String id){
        logger.info("calling from trace demo backend spring-docker-demo");
        String result= this.restTemplate.getForObject("http://localhost:8080/rest/image/listImageDatas?userId=" + id, String.class);
        return result+" world";
    }
}
