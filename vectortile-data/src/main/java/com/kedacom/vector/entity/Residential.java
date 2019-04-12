package com.kedacom.vector.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/1 0001 14:30
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Residential extends BaseEntity implements Serializable {
    @JSONField(serialize = false)
    private static final long serialVersionUID = 1L;

    @JSONField(name = "ID")
    private String id;

    @JSONField(name = "POI_PID")
    private String poiPid;

    @JSONField(name = "Kind_Code")
    private String kindCode;

    @JSONField(name = "Name")
    private String name;

    @JSONField(name = "Name_Py")
    private String namePy;

    @JSONField(name = "City_Name")
    private String cityName;
}
