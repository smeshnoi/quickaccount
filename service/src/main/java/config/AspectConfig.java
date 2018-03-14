package config;

import com.quickaccount.service.aspect.ServiceLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {

    @Bean
    public ServiceLogger getServiceLogger() {
        return new ServiceLogger();
    }
}
