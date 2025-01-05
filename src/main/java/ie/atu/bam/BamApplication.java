package ie.atu.bam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BamApplication {

    public static void main(String[] args) {
        SpringApplication.run(BamApplication.class, args);
    }

}
