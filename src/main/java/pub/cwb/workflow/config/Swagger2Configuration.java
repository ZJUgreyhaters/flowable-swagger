package pub.cwb.workflow.config;


import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author athena
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    private static final Logger logger = LoggerFactory.getLogger(Swagger2Configuration.class);
    @Value("${server.port}")
    String port;

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return
     */
    @Profile({"dev", "st"})
    @ConditionalOnMissingBean
    @Bean
    public Docket createRestApi() {
        logger.info("Swagger - API DOC avaliable : " + "http://localhost:" + port + "/swagger-ui.html");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     * http://127.0.0.1:8080/swagger-ui.html
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RESTful APIs for Project [workflow].")
                .description("For more info : https://www.baidu.com")
                .termsOfServiceUrl("https://www.google.com")
                .contact(new Contact("wbchen", "", "3150103635@zju.edu.cn"))
                .version("1.0.0")
                .build();
    }

}
