package top.itning.yunshuvideo.core.config;

import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * @author itning
 * @date 2019/4/19 14:49
 */
@Configuration
public class BeansConfig {
    /**
     * <p>Add ModelMapper Bean</p>
     *
     * @return {@link ModelMapper}
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("云舒短视频")
                        .description("云舒短视频接口文档")
                        .license("Apache License 2.0")
                        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                        .termsOfServiceUrl("https://github.com/itning")
                        .version("1.0.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 使用上述配置后，
     * 我们可以将Spring Session默认的Cookie Key从SESSION替换为原生的JSESSIONID。
     * 而CookiePath设置为根路径且配置了相关的正则表达式，
     * 可以达到同父域下的单点登录的效果，
     * 在未涉及跨域的单点登录系统中，这是一个非常优雅的解决方案。
     * 如果我们的当前域名是<code>moe.cnkirito.moe</code>，
     * 该正则会将Cookie设置在父域<code>cnkirito.moe</code>中，
     * 如果有另一个相同父域的子域名<code>blog.cnkirito.moe</code>也会识别这个Cookie，
     * 便可以很方便的实现同父域下的单点登录。
     * <a href="http://blog.didispace.com/spring-session-xjf-3/">来源</a>
     *
     * @return CookieSerializer
     */
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("JSESSIONID");
        serializer.setCookiePath("/");
        serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
        return serializer;
    }
}
