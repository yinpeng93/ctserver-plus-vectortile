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
public class Road extends BaseEntity implements Serializable {

    @JSONField(serialize = false)
    private static final long serialVersionUID = 1L;

    @JSONField(name = "MapID")
    private String mapid;

    @JSONField(name = "ID")
    private String id;

    @JSONField(name = "Kind_num")
    private String kindNum;

    @JSONField(name = "Kind")
    private String kind;

    @JSONField(name = "Width")
    private String width;

    @JSONField(name = "Direction")
    private String direction;

    @JSONField(name = "Toll")
    private String toll;

    @JSONField(name = "Const_St")
    private String constSt;

    @JSONField(name = "UndConCRID")
    private String unconcrid;

    @JSONField(name = "SnodeID")
    private String snodeid;

    @JSONField(name = "EnodeID")
    private String enodeid;

    @JSONField(name = "FuncClass")
    private String funcclass;

    @JSONField(name = "Length")
    private String length;

    @JSONField(name = "DetailCity")
    private String detailcity;

    @JSONField(name = "Through")
    private String through;

    @JSONField(name = "UnThruCRID")
    private String unthrucrid;

    @JSONField(name = "Ownership")
    private String ownership;

    @JSONField(name = "Road_Cond")
    private String roadCond;

    @JSONField(name = "Special")
    private String special;

    @JSONField(name = "AdminCodeL")
    private String admincodel;

    @JSONField(name = "AdminCodeR")
    private String admincoder;

    @JSONField(name = "Uflag")
    private String uflag;

    @JSONField(name = "OnewayCRID")
    private String onewaycrid;

    @JSONField(name = "AccessCRID")
    private String accesscrid;

    @JSONField(name = "SpeedClass")
    private String speedclass;

    @JSONField(name = "LaneNumS2E")
    private String lanenums2e;

    @JSONField(name = "LaneNumE2S")
    private String lanenume2s;

    @JSONField(name = "LaneNum")
    private String lanenum;

    @JSONField(name = "Vehcl_Type")
    private String vehclType;

    @JSONField(name = "Elevated")
    private String elevated;

    @JSONField(name = "Structure")
    private String structure;

    @JSONField(name = "UseFeeCRID")
    private String usefeecrid;

    @JSONField(name = "UseFeeType")
    private String usefeetype;

    @JSONField(name = "SpdLmtS2E")
    private String spdlmts2e;

    @JSONField(name = "SpdLmtE2S")
    private String spdlmte2s;

    @JSONField(name = "SpdSrcS2E")
    private String spdsrcs2e;

    @JSONField(name = "SpdSrcE2S")
    private String spdsrce2s;

    @JSONField(name = "DC_Type")
    private String dcType;

    @JSONField(name = "NoPassCRID")
    private String nopasscrid;

    @JSONField(name = "OutBanCRID")
    private String outbancrid;

    @JSONField(name = "NumBanCRID")
    private String numbancrid;

    @JSONField(name = "ParkFlag")
    private String parkflag;

    @JSONField(name = "PHYLaneS2E")
    private String phylanes2e;

    @JSONField(name = "PHYLaneE2S")
    private String phtlanee2s;

    @JSONField(name = "Route_ID")
    private String routeId;

    @JSONField(name = "Route_Kind")
    private String routeKind;

    @JSONField(name = "PathName")
    private String pathname;

    @JSONField(name = "PathPY")
    private String pathpy;

    @JSONField(name = "PreName")
    private String prename;

    @JSONField(name = "PrePY")
    private String prepy;

    @JSONField(name = "BaseName")
    private String basename;

    @JSONField(name = "BasePY")
    private String basepy;

    @JSONField(name = "StTpName")
    private String sttpname;

    @JSONField(name = "StTpPY")
    private String sttppy;

    @JSONField(name = "SurName")
    private String surname;

    @JSONField(name = "SurPY")
    private String surpy;

    @JSONField(name = "WavName")
    private String wavname;

    @JSONField(name = "Language")
    private String language;

    @JSONField(name = "StTpLoc")
    private String sttploc;

    @JSONField(name = "Kind_Level")
    private String kindLevel;
}
