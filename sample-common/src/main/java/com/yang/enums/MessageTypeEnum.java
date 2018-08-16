package com.yang.enums;

public enum MessageTypeEnum {
    LOGIN_REQ((byte)3),
    LOGIN_RESP((byte)4),
    HEARTBEAT_REQ((byte)5),
    HEARTBEAT_RESP((byte)6),
    ;

    public byte value;

    MessageTypeEnum(byte v){
        this.value = v;
    }

    public byte value(){
        return value;
    }
}
