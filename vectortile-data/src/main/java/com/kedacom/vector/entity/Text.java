package com.kedacom.vector.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yinpeng
 * @since 2019-02-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Text extends BaseEntity implements Serializable {
    @JSONField(serialize = false)
    private static final long serialVersionUID = 1L;

    @JSONField(name = "MapID")
    private String mapid;

    @JSONField(name = "ID")
    private String id;

    @JSONField(name = "Kind")
    private String kind;

    @JSONField(name = "Class")
    private String level;

    @JSONField(name = "AdminCode")
    private String admincode;

    @JSONField(name = "FeatID")
    private String featid;

    @JSONField(name = "NameType")
    private String nametype;

    @JSONField(name = "Name")
    private String name;

    @JSONField(name = "PY")
    private String py;

    @JSONField(name = "KeyWord")
    private String keyword;

    @JSONField(name = "Seq_Nm")
    private String seqNm;

    @JSONField(name = "SignNumFlg")
    private String signnumflg;

    @JSONField(name = "SignNameTp")
    private String signnumtp;

    @JSONField(name = "Language")
    private String language;

    @JSONField(name = "NameFlag")
    private String nameflag;


}
