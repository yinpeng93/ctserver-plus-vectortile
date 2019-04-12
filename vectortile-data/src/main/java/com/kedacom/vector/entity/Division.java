package com.kedacom.vector.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/4 0004 11:28
 * @Description: 行政区划表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Division extends BaseEntity implements Serializable {
    @JSONField(serialize = false)
    private static final long serialVersionUID = 1L;

    @JSONField(name = "AdminCode")
    private String admincode;

    @JSONField(name = "Kind")
    private String kind;
}
