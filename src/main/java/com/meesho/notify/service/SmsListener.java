package com.meesho.notify.service;

import com.meesho.notify.models.request.SmsCreateEvent;
import org.springframework.messaging.Message;

import java.io.IOException;

public interface SmsListener {
    void listen(Message<SmsCreateEvent> message) throws IOException;
}
