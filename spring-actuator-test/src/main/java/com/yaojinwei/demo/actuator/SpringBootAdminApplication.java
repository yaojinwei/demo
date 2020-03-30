package com.yaojinwei.demo.actuator;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.boot.actuate.autoconfigure.metrics.OnlyOnceLoggingDenyMeterFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * http://127.0.0.1:8806/actuator/prometheus
 * http://127.0.0.1:8806/actuator/metrics/http.server.requests
 */
@Configuration
//@EnableAdminServer
@SpringBootApplication
public class SpringBootAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdminApplication.class, args);
    }

    @Bean
    public MeterRegistryBean meterRegistryBean(MeterRegistry meterRegistry){
        return new MeterRegistryBean(meterRegistry);
    }

    @Bean
    @Order(0)
    public MeterFilter metricsTestIdTagFilter(MetricsProperties properties) {
        String metricName = "TEST-gauge";
        MeterFilter denyFilter = new OnlyOnceLoggingDenyMeterFilter(() -> String
                .format("Reached the maximum number of URI tags for '%s'. Are you using 'uriVariables'?", metricName));
        return MeterFilter.maximumAllowableTags(metricName, "id", 10,
                denyFilter);
    }
}
