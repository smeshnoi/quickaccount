package config;

import com.quickaccount.config.PersistenceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PersistenceConfig.class, AspectConfig.class})
@ComponentScan(basePackages = "com.quickaccount.service")
public class ServiceConfig {
}
