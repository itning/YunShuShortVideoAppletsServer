package top.itning.yunshuvideo.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author itning
 * @date 2019/4/19 11:35
 */
@SpringBootApplication(scanBasePackages = "top.itning.yunshuvideo")
@MapperScan("top.itning.yunshuvideo.mapper")
@EnableSwagger2
@EnableRedisHttpSession
public class YunShuVideoApplication {
    public static void main(String[] args) {
        SpringApplication.run(YunShuVideoApplication.class, args);
    }
}
