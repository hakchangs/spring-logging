package org.example.tomcat._config;

import org.example.tomcat.filter.PayloadLoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
public class WebConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    PayloadLoggingFilter payloadLoggingFilter() {
        return new PayloadLoggingFilter();
    }

}
