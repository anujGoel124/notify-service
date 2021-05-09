package com.meesho.notify.service.implementation;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PhoneNumberUtil {

    public static boolean isValidPhoneNumber(String s) {
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        // Pattern class contains matcher() method
        // to find matching between given number
        // and regular expression
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }
}
