package com.meesho.notify.enums;

public enum SmsSendStatus {
    CREATED(0),
    PROCESSED(1),
    BLOCKED(2),
    FAILED(3);

    private int code;

    SmsSendStatus(int code){this.code=code;}

    public int getCode() {
        return code;
    }
}
