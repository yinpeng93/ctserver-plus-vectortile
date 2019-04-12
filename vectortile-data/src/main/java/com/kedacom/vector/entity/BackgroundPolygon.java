package com.kedacom.vector.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/4 0004 13:20
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BackgroundPolygon extends BaseEntity implements Serializable {
    @JSONField(serialize = false)
    private static final long serialVersionUID = 1L;

    @JSONField(name = "MapID")
    private String mapid;

    @JSONField(name = "ID")
    private String id;

    @JSONField(name = "Kind")
    private String kind;

    @JSONField(name = "AdminCode")
    private String admincode;

    @JSONField(name = "ZoneCode")
    private String zonecode;

    @JSONField(name = "DispClass")
    private String dispclass;

    @JSONField(name = "POI_ID")
    private String poiId;

    @JSONField(name = "DispLevel")
    private String displevel;
}
