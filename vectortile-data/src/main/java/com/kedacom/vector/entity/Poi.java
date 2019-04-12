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
public class Poi extends BaseEntity implements Serializable {

    @JSONField(serialize = false)
    private static final long serialVersionUID = 1L;

    @JSONField(name = "MapID")
    private String mapid;

    @JSONField(name = "Kind")
    private String kind;

    @JSONField(name = "ZipCode")
    private String zipcode;

    @JSONField(name = "TelePhone")
    private String telephone;

    @JSONField(name = "AdminCode")
    private String admincode;

    @JSONField(name = "Display_X")
    private String displayX;

    @JSONField(name = "Display_Y")
    private String dispalyY;

    @JSONField(name = "POI_ID")
    private String poiId;

    @JSONField(name = "Importance")
    private String importance;

    @JSONField(name = "VAdmincode")
    private String vadmincode;

    @JSONField(name = "ChainCode")
    private String chaincode;

    @JSONField(name = "Prior_Auth")
    private String priorAuth;

    @JSONField(name = "LinkID")
    private String linkid;

    @JSONField(name = "Side")
    private String side;

    @JSONField(name = "PID")
    private String pid;

    @JSONField(name = "Tel_Type")
    private String telType;

    @JSONField(name = "Food_Type")
    private String foodType;

    @JSONField(name = "Airpt_Code")
    private String airptCode;

    @JSONField(name = "Open_24H")
    private String open24h;

    @JSONField(name = "Data_Src")
    private String dataSrc;

    @JSONField(name = "Mesh_ID")
    private String meshId;

    @JSONField(name = "POI_Flag")
    private String poiFlag;

    @JSONField(name = "Rating")
    private String rating;

    @JSONField(name = "TG_Type")
    private String tgType;

    @JSONField(name = "AccessFlag")
    private String accessflag;

    @JSONField(name = "TruckFlag")
    private String truckflag;

    @JSONField(name = "Spvenue")
    private String spvenue;

    @JSONField(name = "Gate")
    private String gate;

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
    private String signnametp;

    @JSONField(name = "Language")
    private String language;

    @JSONField(name = "NameFlag")
    private String nameflag;


}
