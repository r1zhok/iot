package org.r1zhok.app.config;


import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GpioConfig {

    @Bean
    public Context gpioContext() {
        return Pi4J.newAutoContext();
    }
}