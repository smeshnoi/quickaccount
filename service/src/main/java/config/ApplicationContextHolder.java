package config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextHolder {
    private static final ApplicationContext APPLICATION_CONTEXT;

    static {
        APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(ServiceConfig.class);
    }

    public static <T> T getBean(Class<T> beanClass) {
        return APPLICATION_CONTEXT.getBean(beanClass);
    }
}
