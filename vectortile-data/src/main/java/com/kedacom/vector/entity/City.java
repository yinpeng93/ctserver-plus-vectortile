package com.kedacom.vector.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/1 0001 16:45
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class City implements Serializable {
    private String adminCode;

    private String name;

    private String parentId;
}
