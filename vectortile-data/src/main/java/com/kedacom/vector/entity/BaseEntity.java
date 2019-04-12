package com.kedacom.vector.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: YinPeng
 * @Date: 2019/2/28 0028 16:37
 * @Description:
 */
@Data
public class BaseEntity implements Serializable {
    @JSONField(serialize = false)
    private static final long serialVersionUID = 1L;

    @JSONField(name = "GID")
    private String gid;

    @JSONField(serialize = false)
    private GeometryEntity geom;
}
