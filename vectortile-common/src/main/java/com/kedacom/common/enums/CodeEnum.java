package com.kedacom.common.enums;

public enum CodeEnum {
    UNKNOWN("-1"),
    SUCCESS("0");

    private String value;

    private CodeEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    public String getValue() {
        return this.value;
    }
}
