package org.r1zhok.app.service;

import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GpioService {

    private final Context context;

    public void enablePin(int pinNumber) {
        DigitalOutputConfigBuilder config = DigitalOutput.newConfigBuilder(this.context)
                .id("my-digital-output-" + pinNumber)
                .name("My Digital Output " + pinNumber)
                .address(pinNumber)
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW);

        DigitalOutput output = this.context.create(config);
        output.high();
    }

    public void disablePin(int pinNumber) {
        DigitalOutputConfigBuilder config = DigitalOutput.newConfigBuilder(this.context)
                .id("my-digital-output-" + pinNumber)
                .name("My Digital Output " + pinNumber)
                .address(pinNumber)
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.HIGH);

        DigitalOutput output = this.context.create(config);
        output.low();
    }
}