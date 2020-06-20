package com.yaojinwei.demo.actuator.pushgateway;

import io.micrometer.core.instrument.MeterRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.PushGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//@Component
public class PushGatewayConfig {

    @Autowired
    MeterRegistry meterRegistry;

    @Value("${spring.application.name}")
    String applicationName;

    @Value("${prometheus.pushgateway.host:11.164.62.162:9091}")
    String pushHost;

    @Value("${prometheus.pushgateway.intervalInMillis:10000}")
    long intervalInMillis;

    @Autowired
    private CollectorRegistry collectorRegistry;

    @PostConstruct
    public void initialize() {

        PushGateway prometheusPush = new PushGateway(pushHost);

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            try {
                Map<String, String> groupingKey = new HashMap<>();
                groupingKey.put("application", "test");
                groupingKey.put("instance", "11");
                prometheusPush.push(collectorRegistry, applicationName, groupingKey);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, 5000, intervalInMillis, TimeUnit.MILLISECONDS);
    }
}