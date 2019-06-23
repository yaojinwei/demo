package com.yaojinwei.demo.swagger.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * 在配置文件中，application.yml中声明：
 * springfox.documentation.swagger.v2.path: /api-docs
 * http://localhost:8080/swagger-resources
 * http://localhost:8080/swagger-ui.html
 * http://localhost:8080/v2/api-docs  Swagger2Controller Swagger2DocumentationConfiguration
 * http://localhost:8080/swagger-resources/configuration/security  ApiResourceController
 * http://localhost:8080/swagger-resources/configuration/ui  ApiResourceController
 * @author jinwei.yjw
 * @date 2018/12/11 11:38
 */
@SpringBootApplication
@EnableSwagger2
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
