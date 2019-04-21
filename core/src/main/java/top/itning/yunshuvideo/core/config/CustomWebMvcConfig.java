package top.itning.yunshuvideo.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.itning.yunshuvideo.common.config.FileSpaceConfigProperties;

import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * MVC参数配置
 *
 * @author itning
 */
@Configuration
public class CustomWebMvcConfig implements WebMvcConfigurer {
    private final FileSpaceConfigProperties fileSpaceConfigProperties;

    public CustomWebMvcConfig(FileSpaceConfigProperties fileSpaceConfigProperties) {
        this.fileSpaceConfigProperties = fileSpaceConfigProperties;
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(new ThreadPoolExecutor(0, 500, 60L, TimeUnit.SECONDS, new SynchronousQueue<>())));
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("file:" + fileSpaceConfigProperties.getFaceImgDir())
                .addResourceLocations("file:" + fileSpaceConfigProperties.getBgmDir())
                .addResourceLocations("file:" + fileSpaceConfigProperties.getVideoDir())
                .addResourceLocations("classpath:/META-INF/resources/");
    }
}
