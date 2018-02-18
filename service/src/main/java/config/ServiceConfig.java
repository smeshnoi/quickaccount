package config;

import com.quickaccount.config.DatabaseConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DatabaseConfig.class)
@ComponentScan(basePackages = "com.quickaccount")
public class ServiceConfig {
}
