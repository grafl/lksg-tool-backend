package grafl.msg.lksg.tool.backend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients("grafl.msg.lksg.tool.backend.clients")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@RestController
@Slf4j
public class LksgToolBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LksgToolBackendApplication.class, args);
    }

    @GetMapping(value = "/")
    public String root_context() {
        log.info("root_context()");
        return "Ready";
    }

}
