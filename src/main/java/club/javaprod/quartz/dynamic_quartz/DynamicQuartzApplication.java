package club.javaprod.quartz.dynamic_quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling //开启支持定时任务
public class DynamicQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicQuartzApplication.class, args);
    }
}
