package com.kedacom.vector.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/1 0001 13:40
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Building extends BaseEntity implements Serializable {
    @JSONField(serialize = false)
    private static final long serialVersionUID = 1L;

    @JSONField(name = "MapID")
    private String mapid;

    @JSONField(name = "ID")
    private String id;

    @JSONField(name = "Class")
    private String level;

    @JSONField(name = "Show")
    private String show;

    @JSONField(name = "LandMark3D")
    private String landmark3d;

    @JSONField(name = "HEIGHT")
    private Double height;

    @JSONField(name = "Carto_ID")
    private String cartoId;

    @JSONField(name = "AdminCode")
    private String admincode;

    @JSONField(name = "Name")
    private String name;

    @JSONField(name = "Name_ID")
    private String nameId;

    @JSONField(name = "Src_Flag")
    private String srcFlag;
}