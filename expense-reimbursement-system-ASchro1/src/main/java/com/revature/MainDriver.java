package com.revature;
import com.revature.controller.RequestMapping;
import java.io.File;

import com.revature.util.Monitor;
import io.javalin.Javalin;
import io.javalin.plugin.metrics.MicrometerPlugin;
import io.micrometer.core.instrument.*;
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.system.DiskSpaceMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
public class MainDriver {
    public static void main(String[] args){
        Monitor monitor = new Monitor();

        Javalin myApp = Javalin.create(
                config -> {
                    config.registerPlugin(new MicrometerPlugin(monitor.getRegistry()));
                }
        ).start(8501);
        RequestMapping.configureRoutes(myApp, monitor);
    }
}
