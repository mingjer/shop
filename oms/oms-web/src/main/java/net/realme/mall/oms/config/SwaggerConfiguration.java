package net.realme.mall.oms.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 91000044
 */
@Profile(value = "!prod")
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${swagger.host}")
    private String swaggerHost;

    @Bean
    public Docket createRestApi() {
        Set<String> protocols = new HashSet<>();
        protocols.add("http");
        protocols.add("https");
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .protocols(protocols)
                .select()
                // 指定controller存放的目录路径
                .apis(RequestHandlerSelectors.basePackage("net.realme.mall.oms"))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
        if (swaggerHost != null && !"".equals(swaggerHost)) {
            docket.host(swaggerHost);
        }
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title("Realme OMS")
                // 文档描述
                .description("")
                .termsOfServiceUrl("")
                .version("v1")
                .build();
    }
}
