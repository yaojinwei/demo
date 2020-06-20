package com.yaojinwei.demo.actuator;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.binder.BaseUnits;
import org.springframework.util.StringUtils;

import java.lang.management.MemoryUsage;
import java.util.concurrent.TimeUnit;
import java.util.function.ToDoubleFunction;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class MeterRegistryBean {
    private MeterRegistry meterRegistry;

    public MeterRegistryBean(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        meterRegistry.config().commonTags("WAHAHA","444");

        Gauge.builder("TEST-gauge",()-> 1)
                .tags("id","1")
                .description("test gauge")
//                .baseUnit(BaseUnits.BYTES)
                .register(meterRegistry);

        Gauge.builder("TEST-gauge",()-> 3)
                .tags("id","2")
                .description("test gauge")
                .baseUnit(BaseUnits.BYTES)
                .register(meterRegistry);

        //metricsTestIdTagFilter限制，这里只能成功注册8个，最后的value是24
        for(int i=0;i<15;i++){
            Gauge.builder("TEST-gauge",()-> 3)
                    .tags("id",i+"")
                    .description("test gauge")
                    .baseUnit(BaseUnits.BYTES)
                    .register(meterRegistry);
        }

        meterRegistry.gauge("", "123", value -> Double.parseDouble(value));

        Timer timer = Timer
                .builder("my.timer")
                .description("a description of what this timer does") // 可选
                .tags("region", "test") // 可选
                .register(meterRegistry);
        timer.record(1, TimeUnit.MINUTES);
        timer.record(23, TimeUnit.SECONDS);
//        meterRegistry.gauge("TEST-gauge", 2);
//        meterRegistry.counter("TEST-gauge","","");
    }


}
