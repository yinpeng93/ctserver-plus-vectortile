package com.kedacom.common.enums;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/20 0020 17:10
 * @Description:
 */
public enum StatusEnum {
    PROCESSING(0),
    FINISHED(1),
    FAILED(2);

    private Integer value;

    StatusEnum(Integer value){
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
