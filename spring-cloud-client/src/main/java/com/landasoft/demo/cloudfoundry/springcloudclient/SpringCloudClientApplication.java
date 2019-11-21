package com.landasoft.demo.cloudfoundry.springcloudclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/** SpringBootApplication使用属性exclude禁用spring security **/
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,ManagementWebSecurityAutoConfiguration.class })
public class SpringCloudClientApplication {
    /**
     * 走注册中心通过服务名称调用的restTemplate
     * @return
     */
    @Bean(name = {"loadBalancedRestTemplate"})
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    /**
     * 普通的restTemplate
     * @return
     */
    @Bean(name = {"originRestTemplate"})
    public RestTemplate RestTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudClientApplication.class, args);
    }

}
