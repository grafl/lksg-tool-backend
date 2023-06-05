package grafl.msg.lksg.tool.backend.clients.configurations;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class IrsFeignClientConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


}
