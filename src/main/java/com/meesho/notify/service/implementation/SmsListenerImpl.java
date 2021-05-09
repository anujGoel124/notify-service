package com.meesho.notify.service.implementation;

import com.meesho.notify.dao.SmsDAO;
import com.meesho.notify.entity.SmsSearchData;
import com.meesho.notify.entity.Sms;
import com.meesho.notify.enums.SmsSendStatus;
import com.meesho.notify.exception.InternalServerException;
import com.meesho.notify.models.request.ExternalApiSmsCreateRequest.Channels;
import com.meesho.notify.models.request.ExternalApiSmsCreateRequest.Destination;
import com.meesho.notify.models.request.ExternalApiSmsCreateRequest.ImiSmsRequest;
import com.meesho.notify.models.request.ExternalApiSmsCreateRequest.Text;
import com.meesho.notify.models.request.SmsCreateEvent;
import com.meesho.notify.service.BlacklistService;
import com.meesho.notify.service.SmsSender;
import com.meesho.notify.service.SmsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class SmsListenerImpl implements SmsListener {

    @Autowired
    private SmsSender smsSender;
    @Autowired
    private BlacklistService blacklistService;
    @Autowired
    private SmsDAO smsDAO;

    @KafkaListener(topics = "notification.send_sms")
    public void listen(Message<SmsCreateEvent> message) throws IOException {
        String msg = message.getPayload().getReqId();
        Integer reqId = Integer.parseInt(msg);
        Optional<Sms> sms = smsDAO.findThroughId(reqId);
        if(sms.isPresent()) {
            if (!blacklistService.isBlacklisted(sms.get().getPhoneNumber())) {

                List<String> phoneNumbers = new ArrayList<>();
                phoneNumbers.add(sms.get().getPhoneNumber());
                Text text = Text.builder().text(sms.get().getMessage()).build();
                Channels channels = Channels.builder().sms(text).build();
                Destination destination = Destination.builder().correlationId(msg).msisdn(phoneNumbers).build();
                List<Destination> destinationList = new ArrayList<>();
                destinationList.add(destination);
                ImiSmsRequest finalRequest = ImiSmsRequest.builder().channels(channels).destination(destinationList).deliveryChannel("sms").build();

                SmsSearchData elasticSmsSearchData = SmsSearchData.builder().id(String.valueOf(reqId)).phoneNumber(sms.get().getPhoneNumber()).message(sms.get().getMessage()).createdAt(sms.get().getCreateDateTime()).build();
                log.info("saving " + elasticSmsSearchData + " to ES Repository");
                smsDAO.save(elasticSmsSearchData);
                boolean status = smsSender.sendSms(finalRequest);
                if (status) {
                    sms.get().setStatus(SmsSendStatus.PROCESSED.getCode());
                } else {
                    sms.get().setStatus(SmsSendStatus.FAILED.getCode());
                    sms.get().setFailureComments("3rd Party Api failed");
                    throw new InternalServerException("3rd Party Api failed");
                }
            } else {
                sms.get().setStatus(SmsSendStatus.BLOCKED.getCode());
                sms.get().setFailureComments("phone number is blocked");
            }
            smsDAO.saveSms(sms.get());
        }
    }
}
