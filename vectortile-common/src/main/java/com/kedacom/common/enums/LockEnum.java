package com.kedacom.common.enums;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/15 0015 10:49
 * @Description:
 */
public enum LockEnum {
    UN_LOCKED(0),
    LOCKED(1);

    private Integer value;

    LockEnum(Integer value){
        this.value = value;
    }

    public Integer getValue(){
        return this.value;
    }
}
