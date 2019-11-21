package com.landasoft.demo.cloudfoundry.springcloudclientbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/** SpringBootApplication使用属性exclude禁用spring security **/
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,ManagementWebSecurityAutoConfiguration.class })
@EnableEurekaClient
public class SpringCloudClientBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudClientBackendApplication.class, args);
    }

}