package org.r1zhok.app.controller;

import lombok.RequiredArgsConstructor;
import org.r1zhok.app.sender.MqttGateway;
import org.r1zhok.app.service.GpioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/611a/lab")
public class InputCommandsRestController {

    @Value("${mqtt.topicsToSend}")
    private String[] topicsToSend;

    private final GpioService gpioService;

    private final MqttGateway mqttGateway;

    @PostMapping("/off/{id}")
    public ResponseEntity<?> offRosette(@PathVariable int id) {
        //logic to off rosette
        this.gpioService.disablePin(id);
        //logic to send mqtt topic
        this.mqttGateway.sendToMqtt("Rosette №%d".formatted(id) + "disabled", this.topicsToSend[0]);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/on/{id}")
    public ResponseEntity<?> onRosette(@PathVariable int id) {
        //logic to off rosette
        this.gpioService.enablePin(id);
        //logic to send mqtt topic
        this.mqttGateway.sendToMqtt("Rosette №%d".formatted(id) + "enabled", this.topicsToSend[1]);
        return ResponseEntity.noContent().build();
    }
}