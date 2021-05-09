package com.meesho.notify.service.implementation;

import com.meesho.notify.models.request.SmsCreateEvent;
import com.meesho.notify.service.SmsEventEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SmsEventEmitterImpl implements SmsEventEmitter { //will send message to Kafka in the topic and servers mentioned in the application properties

    @Autowired
    KafkaTemplate<String, SmsCreateEvent> kafkaTemplate;

    @Value("${kafka.topic}")
    String topic;

    public void emit(SmsCreateEvent msg) {
        kafkaTemplate.send(topic, msg);
    }

}
