package com.meesho.notify.service.implementation;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DateConverter {

    public long toEpoch(String stringDate)  {


        SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = null;
        try {
            date = df.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long epoch = date.getTime();

        return epoch;
    }
}
