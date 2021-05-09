package com.meesho.notify.service;

import com.meesho.notify.models.request.SmsCreateEvent;

public interface SmsEventEmitter {
    void emit(SmsCreateEvent msg);
}
