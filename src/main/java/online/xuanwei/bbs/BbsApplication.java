package online.xuanwei.bbs;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "online.xuanwei.bbs.mapper")
public class BbsApplication {

    public static void main(String[] args) {

        SpringApplication.run(BbsApplication.class, args);
    }

}
