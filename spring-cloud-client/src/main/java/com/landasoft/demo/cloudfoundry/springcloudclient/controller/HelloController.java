package com.landasoft.demo.cloudfoundry.springcloudclient.controller;
import com.landasoft.demo.cloudfoundry.springcloudclient.feign.ClientBackendFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @author 伍林云
 * @date 2019/8/5  18:45
 */
@RestController
@Api(description = "客户端用户管理接口")
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Value("${custom.restURL}")
    private String restURL;
    @Autowired
    @Qualifier("loadBalancedRestTemplate")
    private RestTemplate loadBalancedRestTemplate;

    @Autowired
    @Qualifier("originRestTemplate")
    private RestTemplate originRestTemplate;
    @Autowired
    private ClientBackendFeign springCloudClientBackendFeign;
    @GetMapping("/")
    @ApiOperation(value = "spring-cloud-client接口信息")
    public String call(){
        logger.info("calling spring-cloud-client-backend");
        return "hello spring-cloud-client-backend "+new Date().toString();
    }
    /**
     * 直接调用接口地址
     * @param id
     * @return
     */
    @ApiOperation(value = "直接调用spring-cloud-client-backend接口地址")
    @GetMapping("/call/{id}")
    public String callHome(@PathVariable String id){
        logger.info("calling from spring-cloud-client-backend");
        String result= this.originRestTemplate.getForObject(restURL + id, String.class);
        return result+" world";
    }

    /**
     * 通过feign调用接口地址
     * @param id
     * @return
     */
    @ApiOperation(value = "通过feign调用接口地址")
    @GetMapping("/call_f/{id}")
    public String callHomeF(@PathVariable String id){
        logger.info("calling from spring-cloud-client-backend by feign");
        String result = springCloudClientBackendFeign.call(id);
        return result+" world by feign ";
    }

    /**
     * 通过注册中心的服务名称调用接口
     * @param id
     * @return
     */
    @ApiOperation(value = "通过注册中心的服务名称调用接口")
    @GetMapping("/call_e/{id}")
    public String callHomeE(@PathVariable String id){
        logger.info("calling from spring-cloud-client-backend by eureka");
        //spring-cloud-client-backend的值为被调用服务中配置的spring.application.name的值
        String result= this.loadBalancedRestTemplate.getForObject("http://spring-cloud-client-backend/call/" + id, String.class);
        return result+" world by eureka";
    }
}
